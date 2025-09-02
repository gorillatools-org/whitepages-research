package com.facebook.cache.disk;

import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheEventListener;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.CacheKeyUtil;
import com.facebook.cache.common.WriterCallback;
import com.facebook.cache.disk.DiskStorage;
import com.facebook.common.disk.DiskTrimmable;
import com.facebook.common.disk.DiskTrimmableRegistry;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.statfs.StatFsHelper;
import com.facebook.common.time.Clock;
import com.facebook.common.time.SystemClock;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class DiskStorageCache implements FileCache, DiskTrimmable {
    private static final long FILECACHE_SIZE_UPDATE_PERIOD_MS = TimeUnit.MINUTES.toMillis(30);
    private static final long FUTURE_TIMESTAMP_THRESHOLD_MS = TimeUnit.HOURS.toMillis(2);
    private static final Class TAG = DiskStorageCache.class;
    private final CacheErrorLogger mCacheErrorLogger;
    private final CacheEventListener mCacheEventListener;
    private long mCacheSizeLastUpdateTime;
    private long mCacheSizeLimit;
    private final long mCacheSizeLimitMinimum;
    private final CacheStats mCacheStats;
    private final Clock mClock;
    /* access modifiers changed from: private */
    public final CountDownLatch mCountDownLatch;
    private final long mDefaultCacheSizeLimit;
    private final EntryEvictionComparatorSupplier mEntryEvictionComparatorSupplier;
    private final boolean mIndexPopulateAtStartupEnabled;
    /* access modifiers changed from: private */
    public boolean mIndexReady;
    /* access modifiers changed from: private */
    public final Object mLock = new Object();
    private final long mLowDiskSpaceCacheSizeLimit;
    final Set mResourceIndex;
    private final StatFsHelper mStatFsHelper;
    private final DiskStorage mStorage;

    static class CacheStats {
        private long mCount = -1;
        private boolean mInitialized = false;
        private long mSize = -1;

        CacheStats() {
        }

        public synchronized boolean isInitialized() {
            return this.mInitialized;
        }

        public synchronized void reset() {
            this.mInitialized = false;
            this.mCount = -1;
            this.mSize = -1;
        }

        public synchronized void set(long j, long j2) {
            this.mCount = j2;
            this.mSize = j;
            this.mInitialized = true;
        }

        public synchronized void increment(long j, long j2) {
            if (this.mInitialized) {
                this.mSize += j;
                this.mCount += j2;
            }
        }

        public synchronized long getSize() {
            return this.mSize;
        }

        public synchronized long getCount() {
            return this.mCount;
        }
    }

    public static class Params {
        public final long mCacheSizeLimitMinimum;
        public final long mDefaultCacheSizeLimit;
        public final long mLowDiskSpaceCacheSizeLimit;

        public Params(long j, long j2, long j3) {
            this.mCacheSizeLimitMinimum = j;
            this.mLowDiskSpaceCacheSizeLimit = j2;
            this.mDefaultCacheSizeLimit = j3;
        }
    }

    public DiskStorageCache(DiskStorage diskStorage, EntryEvictionComparatorSupplier entryEvictionComparatorSupplier, Params params, CacheEventListener cacheEventListener, CacheErrorLogger cacheErrorLogger, DiskTrimmableRegistry diskTrimmableRegistry, Executor executor, boolean z) {
        this.mLowDiskSpaceCacheSizeLimit = params.mLowDiskSpaceCacheSizeLimit;
        long j = params.mDefaultCacheSizeLimit;
        this.mDefaultCacheSizeLimit = j;
        this.mCacheSizeLimit = j;
        this.mStatFsHelper = StatFsHelper.getInstance();
        this.mStorage = diskStorage;
        this.mEntryEvictionComparatorSupplier = entryEvictionComparatorSupplier;
        this.mCacheSizeLastUpdateTime = -1;
        this.mCacheEventListener = cacheEventListener;
        this.mCacheSizeLimitMinimum = params.mCacheSizeLimitMinimum;
        this.mCacheErrorLogger = cacheErrorLogger;
        this.mCacheStats = new CacheStats();
        this.mClock = SystemClock.get();
        this.mIndexPopulateAtStartupEnabled = z;
        this.mResourceIndex = new HashSet();
        if (diskTrimmableRegistry != null) {
            diskTrimmableRegistry.registerDiskTrimmable(this);
        }
        if (z) {
            this.mCountDownLatch = new CountDownLatch(1);
            executor.execute(new Runnable() {
                public void run() {
                    synchronized (DiskStorageCache.this.mLock) {
                        boolean unused = DiskStorageCache.this.maybeUpdateFileCacheSize();
                    }
                    DiskStorageCache.this.mIndexReady = true;
                    DiskStorageCache.this.mCountDownLatch.countDown();
                }
            });
            return;
        }
        this.mCountDownLatch = new CountDownLatch(0);
    }

    public BinaryResource getResource(CacheKey cacheKey) {
        BinaryResource binaryResource;
        SettableCacheEvent cacheKey2 = SettableCacheEvent.obtain().setCacheKey(cacheKey);
        try {
            synchronized (this.mLock) {
                List resourceIds = CacheKeyUtil.getResourceIds(cacheKey);
                int i = 0;
                String str = null;
                binaryResource = null;
                while (true) {
                    if (i >= resourceIds.size()) {
                        break;
                    }
                    str = (String) resourceIds.get(i);
                    cacheKey2.setResourceId(str);
                    binaryResource = this.mStorage.getResource(str, cacheKey);
                    if (binaryResource != null) {
                        break;
                    }
                    i++;
                }
                if (binaryResource == null) {
                    CacheEventListener cacheEventListener = this.mCacheEventListener;
                    if (cacheEventListener != null) {
                        cacheEventListener.onMiss(cacheKey2);
                    }
                    this.mResourceIndex.remove(str);
                } else {
                    Preconditions.checkNotNull(str);
                    CacheEventListener cacheEventListener2 = this.mCacheEventListener;
                    if (cacheEventListener2 != null) {
                        cacheEventListener2.onHit(cacheKey2);
                    }
                    this.mResourceIndex.add(str);
                }
            }
            cacheKey2.recycle();
            return binaryResource;
        } catch (IOException e) {
            try {
                this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.GENERIC_IO, TAG, "getResource", e);
                cacheKey2.setException(e);
                CacheEventListener cacheEventListener3 = this.mCacheEventListener;
                if (cacheEventListener3 != null) {
                    cacheEventListener3.onReadException(cacheKey2);
                }
                return null;
            } finally {
                cacheKey2.recycle();
            }
        } catch (Throwable th) {
            while (true) {
            }
            throw th;
        }
    }

    public boolean probe(CacheKey cacheKey) {
        String str;
        IOException e;
        Throwable th;
        String str2 = null;
        try {
            synchronized (this.mLock) {
                try {
                    List resourceIds = CacheKeyUtil.getResourceIds(cacheKey);
                    int i = 0;
                    while (i < resourceIds.size()) {
                        String str3 = (String) resourceIds.get(i);
                        try {
                            if (this.mStorage.touch(str3, cacheKey)) {
                                this.mResourceIndex.add(str3);
                                return true;
                            }
                            i++;
                            str2 = str3;
                        } catch (Throwable th2) {
                            th = th2;
                            try {
                                throw th;
                            } catch (IOException e2) {
                                e = e2;
                            }
                        }
                    }
                    return false;
                } catch (Throwable th3) {
                    str = str2;
                    th = th3;
                    throw th;
                }
            }
        } catch (IOException e3) {
            str = null;
            e = e3;
            SettableCacheEvent exception = SettableCacheEvent.obtain().setCacheKey(cacheKey).setResourceId(str).setException(e);
            CacheEventListener cacheEventListener = this.mCacheEventListener;
            if (cacheEventListener != null) {
                cacheEventListener.onReadException(exception);
            }
            exception.recycle();
            return false;
        }
    }

    private DiskStorage.Inserter startInsert(String str, CacheKey cacheKey) {
        maybeEvictFilesInCacheDir();
        return this.mStorage.insert(str, cacheKey);
    }

    private BinaryResource endInsert(DiskStorage.Inserter inserter, CacheKey cacheKey, String str) {
        BinaryResource commit;
        synchronized (this.mLock) {
            commit = inserter.commit(cacheKey);
            this.mResourceIndex.add(str);
            this.mCacheStats.increment(commit.size(), 1);
        }
        return commit;
    }

    public BinaryResource insert(CacheKey cacheKey, WriterCallback writerCallback) {
        String firstResourceId;
        DiskStorage.Inserter startInsert;
        SettableCacheEvent cacheKey2 = SettableCacheEvent.obtain().setCacheKey(cacheKey);
        CacheEventListener cacheEventListener = this.mCacheEventListener;
        if (cacheEventListener != null) {
            cacheEventListener.onWriteAttempt(cacheKey2);
        }
        synchronized (this.mLock) {
            firstResourceId = CacheKeyUtil.getFirstResourceId(cacheKey);
        }
        cacheKey2.setResourceId(firstResourceId);
        try {
            startInsert = startInsert(firstResourceId, cacheKey);
            startInsert.writeData(writerCallback, cacheKey);
            BinaryResource endInsert = endInsert(startInsert, cacheKey, firstResourceId);
            cacheKey2.setItemSize(endInsert.size()).setCacheSize(this.mCacheStats.getSize());
            CacheEventListener cacheEventListener2 = this.mCacheEventListener;
            if (cacheEventListener2 != null) {
                cacheEventListener2.onWriteSuccess(cacheKey2);
            }
            if (!startInsert.cleanUp()) {
                FLog.e(TAG, "Failed to delete temp file");
            }
            cacheKey2.recycle();
            return endInsert;
        } catch (IOException e) {
            try {
                cacheKey2.setException(e);
                CacheEventListener cacheEventListener3 = this.mCacheEventListener;
                if (cacheEventListener3 != null) {
                    cacheEventListener3.onWriteException(cacheKey2);
                }
                FLog.e(TAG, "Failed inserting a file into the cache", (Throwable) e);
                throw e;
            } catch (Throwable th) {
                cacheKey2.recycle();
                throw th;
            }
        } catch (Throwable th2) {
            if (!startInsert.cleanUp()) {
                FLog.e(TAG, "Failed to delete temp file");
            }
            throw th2;
        }
    }

    public void remove(CacheKey cacheKey) {
        synchronized (this.mLock) {
            try {
                List resourceIds = CacheKeyUtil.getResourceIds(cacheKey);
                for (int i = 0; i < resourceIds.size(); i++) {
                    String str = (String) resourceIds.get(i);
                    this.mStorage.remove(str);
                    this.mResourceIndex.remove(str);
                }
            } catch (IOException e) {
                CacheErrorLogger cacheErrorLogger = this.mCacheErrorLogger;
                CacheErrorLogger.CacheErrorCategory cacheErrorCategory = CacheErrorLogger.CacheErrorCategory.DELETE_FILE;
                Class cls = TAG;
                cacheErrorLogger.logError(cacheErrorCategory, cls, "delete: " + e.getMessage(), e);
            }
        }
    }

    private void maybeEvictFilesInCacheDir() {
        synchronized (this.mLock) {
            try {
                boolean maybeUpdateFileCacheSize = maybeUpdateFileCacheSize();
                updateFileCacheSizeLimit();
                long size = this.mCacheStats.getSize();
                if (size > this.mCacheSizeLimit && !maybeUpdateFileCacheSize) {
                    this.mCacheStats.reset();
                    maybeUpdateFileCacheSize();
                }
                long j = this.mCacheSizeLimit;
                if (size > j) {
                    evictAboveSize((j * 9) / 10, CacheEventListener.EvictionReason.CACHE_FULL);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void evictAboveSize(long j, CacheEventListener.EvictionReason evictionReason) {
        long j2 = j;
        try {
            Collection<DiskStorage.Entry> sortedEntries = getSortedEntries(this.mStorage.getEntries());
            long size = this.mCacheStats.getSize();
            long j3 = size - j2;
            int i = 0;
            long j4 = 0;
            for (DiskStorage.Entry entry : sortedEntries) {
                if (j4 > j3) {
                    break;
                }
                long remove = this.mStorage.remove(entry);
                this.mResourceIndex.remove(entry.getId());
                if (remove > 0) {
                    i++;
                    j4 += remove;
                    SettableCacheEvent cacheLimit = SettableCacheEvent.obtain().setResourceId(entry.getId()).setEvictionReason(evictionReason).setItemSize(remove).setCacheSize(size - j4).setCacheLimit(j2);
                    CacheEventListener cacheEventListener = this.mCacheEventListener;
                    if (cacheEventListener != null) {
                        cacheEventListener.onEviction(cacheLimit);
                    }
                    cacheLimit.recycle();
                } else {
                    CacheEventListener.EvictionReason evictionReason2 = evictionReason;
                }
            }
            this.mCacheStats.increment(-j4, (long) (-i));
            this.mStorage.purgeUnexpectedResources();
        } catch (IOException e) {
            CacheErrorLogger cacheErrorLogger = this.mCacheErrorLogger;
            CacheErrorLogger.CacheErrorCategory cacheErrorCategory = CacheErrorLogger.CacheErrorCategory.EVICTION;
            Class cls = TAG;
            cacheErrorLogger.logError(cacheErrorCategory, cls, "evictAboveSize: " + e.getMessage(), e);
            throw e;
        }
    }

    private Collection getSortedEntries(Collection collection) {
        long now = this.mClock.now() + FUTURE_TIMESTAMP_THRESHOLD_MS;
        ArrayList arrayList = new ArrayList(collection.size());
        ArrayList arrayList2 = new ArrayList(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            DiskStorage.Entry entry = (DiskStorage.Entry) it.next();
            if (entry.getTimestamp() > now) {
                arrayList.add(entry);
            } else {
                arrayList2.add(entry);
            }
        }
        Collections.sort(arrayList2, this.mEntryEvictionComparatorSupplier.get());
        arrayList.addAll(arrayList2);
        return arrayList;
    }

    private void updateFileCacheSizeLimit() {
        StatFsHelper.StorageType storageType;
        if (this.mStorage.isExternal()) {
            storageType = StatFsHelper.StorageType.EXTERNAL;
        } else {
            storageType = StatFsHelper.StorageType.INTERNAL;
        }
        if (this.mStatFsHelper.testLowDiskSpace(storageType, this.mDefaultCacheSizeLimit - this.mCacheStats.getSize())) {
            this.mCacheSizeLimit = this.mLowDiskSpaceCacheSizeLimit;
        } else {
            this.mCacheSizeLimit = this.mDefaultCacheSizeLimit;
        }
    }

    public void clearAll() {
        synchronized (this.mLock) {
            try {
                this.mStorage.clearAll();
                this.mResourceIndex.clear();
                CacheEventListener cacheEventListener = this.mCacheEventListener;
                if (cacheEventListener != null) {
                    cacheEventListener.onCleared();
                }
            } catch (IOException | NullPointerException e) {
                CacheErrorLogger cacheErrorLogger = this.mCacheErrorLogger;
                CacheErrorLogger.CacheErrorCategory cacheErrorCategory = CacheErrorLogger.CacheErrorCategory.EVICTION;
                Class cls = TAG;
                cacheErrorLogger.logError(cacheErrorCategory, cls, "clearAll: " + e.getMessage(), e);
            }
            this.mCacheStats.reset();
        }
    }

    public boolean hasKeySync(CacheKey cacheKey) {
        synchronized (this.mLock) {
            try {
                List resourceIds = CacheKeyUtil.getResourceIds(cacheKey);
                for (int i = 0; i < resourceIds.size(); i++) {
                    if (this.mResourceIndex.contains((String) resourceIds.get(i))) {
                        return true;
                    }
                }
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean hasKey(CacheKey cacheKey) {
        synchronized (this.mLock) {
            if (hasKeySync(cacheKey)) {
                return true;
            }
            try {
                List resourceIds = CacheKeyUtil.getResourceIds(cacheKey);
                for (int i = 0; i < resourceIds.size(); i++) {
                    String str = (String) resourceIds.get(i);
                    if (this.mStorage.contains(str, cacheKey)) {
                        this.mResourceIndex.add(str);
                        return true;
                    }
                }
                return false;
            } catch (IOException unused) {
                return false;
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean maybeUpdateFileCacheSize() {
        long now = this.mClock.now();
        if (this.mCacheStats.isInitialized()) {
            long j = this.mCacheSizeLastUpdateTime;
            if (j != -1 && now - j <= FILECACHE_SIZE_UPDATE_PERIOD_MS) {
                return false;
            }
        }
        return maybeUpdateFileCacheSizeAndIndex();
    }

    private boolean maybeUpdateFileCacheSizeAndIndex() {
        Set set;
        long j;
        long now = this.mClock.now();
        long j2 = FUTURE_TIMESTAMP_THRESHOLD_MS + now;
        if (!this.mIndexPopulateAtStartupEnabled || !this.mResourceIndex.isEmpty()) {
            set = this.mIndexPopulateAtStartupEnabled ? new HashSet() : null;
        } else {
            set = this.mResourceIndex;
        }
        try {
            long j3 = 0;
            long j4 = -1;
            int i = 0;
            boolean z = false;
            int i2 = 0;
            int i3 = 0;
            for (DiskStorage.Entry entry : this.mStorage.getEntries()) {
                i2++;
                j3 += entry.getSize();
                if (entry.getTimestamp() > j2) {
                    i3++;
                    i = (int) (((long) i) + entry.getSize());
                    j = j2;
                    j4 = Math.max(entry.getTimestamp() - now, j4);
                    z = true;
                } else {
                    j = j2;
                    if (this.mIndexPopulateAtStartupEnabled) {
                        Preconditions.checkNotNull(set);
                        set.add(entry.getId());
                    }
                }
                j2 = j;
            }
            if (z) {
                this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.READ_INVALID_ENTRY, TAG, "Future timestamp found in " + i3 + " files , with a total size of " + i + " bytes, and a maximum time delta of " + j4 + "ms", (Throwable) null);
            }
            long j5 = (long) i2;
            if (!(this.mCacheStats.getCount() == j5 && this.mCacheStats.getSize() == j3)) {
                if (this.mIndexPopulateAtStartupEnabled && this.mResourceIndex != set) {
                    Preconditions.checkNotNull(set);
                    this.mResourceIndex.clear();
                    this.mResourceIndex.addAll(set);
                }
                this.mCacheStats.set(j3, j5);
            }
            this.mCacheSizeLastUpdateTime = now;
            return true;
        } catch (IOException e) {
            this.mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.GENERIC_IO, TAG, "calcFileCacheSize: " + e.getMessage(), e);
            return false;
        }
    }
}
