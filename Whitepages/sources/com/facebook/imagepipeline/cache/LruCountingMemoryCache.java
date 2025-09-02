package com.facebook.imagepipeline.cache;

import android.os.SystemClock;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Predicate;
import com.facebook.common.internal.Supplier;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.cache.MemoryCache;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

public class LruCountingMemoryCache implements CountingMemoryCache, MemoryCache {
    private final MemoryCache.CacheTrimStrategy mCacheTrimStrategy;
    final CountingLruMap mCachedEntries;
    final CountingLruMap mExclusiveEntries;
    private final boolean mIgnoreSizeMismatch;
    private long mLastCacheParamsCheck;
    protected MemoryCacheParams mMemoryCacheParams;
    private final Supplier mMemoryCacheParamsSupplier;
    final Map mOtherEntries = new WeakHashMap();
    /* access modifiers changed from: private */
    public final boolean mStoreEntrySize;
    private final ValueDescriptor mValueDescriptor;

    private static void maybeNotifyExclusiveEntryInsertion(CountingMemoryCache.Entry entry) {
    }

    private static void maybeNotifyExclusiveEntryRemoval(CountingMemoryCache.Entry entry) {
    }

    public LruCountingMemoryCache(ValueDescriptor valueDescriptor, MemoryCache.CacheTrimStrategy cacheTrimStrategy, Supplier supplier, CountingMemoryCache.EntryStateObserver entryStateObserver, boolean z, boolean z2) {
        this.mValueDescriptor = valueDescriptor;
        this.mExclusiveEntries = new CountingLruMap(wrapValueDescriptor(valueDescriptor));
        this.mCachedEntries = new CountingLruMap(wrapValueDescriptor(valueDescriptor));
        this.mCacheTrimStrategy = cacheTrimStrategy;
        this.mMemoryCacheParamsSupplier = supplier;
        this.mMemoryCacheParams = (MemoryCacheParams) Preconditions.checkNotNull((MemoryCacheParams) supplier.get(), "mMemoryCacheParamsSupplier returned null");
        this.mLastCacheParamsCheck = SystemClock.uptimeMillis();
        this.mStoreEntrySize = z;
        this.mIgnoreSizeMismatch = z2;
    }

    private ValueDescriptor wrapValueDescriptor(final ValueDescriptor valueDescriptor) {
        return new ValueDescriptor() {
            public int getSizeInBytes(CountingMemoryCache.Entry entry) {
                if (LruCountingMemoryCache.this.mStoreEntrySize) {
                    return entry.size;
                }
                return valueDescriptor.getSizeInBytes(entry.valueRef.get());
            }
        };
    }

    public CloseableReference cache(Object obj, CloseableReference closeableReference) {
        return cache(obj, closeableReference, (CountingMemoryCache.EntryStateObserver) null);
    }

    public CloseableReference cache(Object obj, CloseableReference closeableReference, CountingMemoryCache.EntryStateObserver entryStateObserver) {
        CountingMemoryCache.Entry entry;
        CloseableReference closeableReference2;
        CloseableReference closeableReference3;
        CountingMemoryCache.Entry entry2;
        Preconditions.checkNotNull(obj);
        Preconditions.checkNotNull(closeableReference);
        maybeUpdateCacheParams();
        synchronized (this) {
            try {
                entry = (CountingMemoryCache.Entry) this.mExclusiveEntries.remove(obj);
                CountingMemoryCache.Entry entry3 = (CountingMemoryCache.Entry) this.mCachedEntries.remove(obj);
                closeableReference2 = null;
                if (entry3 != null) {
                    makeOrphan(entry3);
                    closeableReference3 = referenceToClose(entry3);
                } else {
                    closeableReference3 = null;
                }
                int sizeInBytes = this.mValueDescriptor.getSizeInBytes(closeableReference.get());
                if (canCacheNewValueOfSize(sizeInBytes)) {
                    if (this.mStoreEntrySize) {
                        entry2 = CountingMemoryCache.Entry.of(obj, closeableReference, sizeInBytes, entryStateObserver);
                    } else {
                        entry2 = CountingMemoryCache.Entry.of(obj, closeableReference, entryStateObserver);
                    }
                    this.mCachedEntries.put(obj, entry2);
                    closeableReference2 = newClientReference(entry2);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        CloseableReference.closeSafely(closeableReference3);
        maybeNotifyExclusiveEntryRemoval(entry);
        maybeEvictEntries();
        return closeableReference2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001c, code lost:
        if (getInUseSizeInBytes() <= (r3.mMemoryCacheParams.maxCacheSize - r4)) goto L_0x0022;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean canCacheNewValueOfSize(int r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            com.facebook.imagepipeline.cache.MemoryCacheParams r0 = r3.mMemoryCacheParams     // Catch:{ all -> 0x001f }
            int r0 = r0.maxCacheEntrySize     // Catch:{ all -> 0x001f }
            if (r4 > r0) goto L_0x0021
            int r0 = r3.getInUseCount()     // Catch:{ all -> 0x001f }
            com.facebook.imagepipeline.cache.MemoryCacheParams r1 = r3.mMemoryCacheParams     // Catch:{ all -> 0x001f }
            int r1 = r1.maxCacheEntries     // Catch:{ all -> 0x001f }
            r2 = 1
            int r1 = r1 - r2
            if (r0 > r1) goto L_0x0021
            int r0 = r3.getInUseSizeInBytes()     // Catch:{ all -> 0x001f }
            com.facebook.imagepipeline.cache.MemoryCacheParams r1 = r3.mMemoryCacheParams     // Catch:{ all -> 0x001f }
            int r1 = r1.maxCacheSize     // Catch:{ all -> 0x001f }
            int r1 = r1 - r4
            if (r0 > r1) goto L_0x0021
            goto L_0x0022
        L_0x001f:
            r4 = move-exception
            goto L_0x0024
        L_0x0021:
            r2 = 0
        L_0x0022:
            monitor-exit(r3)
            return r2
        L_0x0024:
            monitor-exit(r3)     // Catch:{ all -> 0x001f }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.cache.LruCountingMemoryCache.canCacheNewValueOfSize(int):boolean");
    }

    public CloseableReference get(Object obj) {
        CountingMemoryCache.Entry entry;
        CloseableReference newClientReference;
        Preconditions.checkNotNull(obj);
        synchronized (this) {
            try {
                entry = (CountingMemoryCache.Entry) this.mExclusiveEntries.remove(obj);
                CountingMemoryCache.Entry entry2 = (CountingMemoryCache.Entry) this.mCachedEntries.get(obj);
                newClientReference = entry2 != null ? newClientReference(entry2) : null;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        maybeNotifyExclusiveEntryRemoval(entry);
        maybeUpdateCacheParams();
        maybeEvictEntries();
        return newClientReference;
    }

    public void probe(Object obj) {
        Preconditions.checkNotNull(obj);
        synchronized (this) {
            try {
                CountingMemoryCache.Entry entry = (CountingMemoryCache.Entry) this.mExclusiveEntries.remove(obj);
                if (entry != null) {
                    this.mExclusiveEntries.put(obj, entry);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private synchronized CloseableReference newClientReference(final CountingMemoryCache.Entry entry) {
        increaseClientCount(entry);
        return CloseableReference.of(entry.valueRef.get(), (ResourceReleaser) new ResourceReleaser() {
            public void release(Object obj) {
                LruCountingMemoryCache.this.releaseClientReference(entry);
            }
        });
    }

    /* access modifiers changed from: private */
    public void releaseClientReference(CountingMemoryCache.Entry entry) {
        boolean maybeAddToExclusives;
        CloseableReference referenceToClose;
        Preconditions.checkNotNull(entry);
        synchronized (this) {
            decreaseClientCount(entry);
            maybeAddToExclusives = maybeAddToExclusives(entry);
            referenceToClose = referenceToClose(entry);
        }
        CloseableReference.closeSafely(referenceToClose);
        if (!maybeAddToExclusives) {
            entry = null;
        }
        maybeNotifyExclusiveEntryInsertion(entry);
        maybeUpdateCacheParams();
        maybeEvictEntries();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0016, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean maybeAddToExclusives(com.facebook.imagepipeline.cache.CountingMemoryCache.Entry r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r3.isOrphan     // Catch:{ all -> 0x0013 }
            if (r0 != 0) goto L_0x0015
            int r0 = r3.clientCount     // Catch:{ all -> 0x0013 }
            if (r0 != 0) goto L_0x0015
            com.facebook.imagepipeline.cache.CountingLruMap r0 = r2.mExclusiveEntries     // Catch:{ all -> 0x0013 }
            java.lang.Object r1 = r3.key     // Catch:{ all -> 0x0013 }
            r0.put(r1, r3)     // Catch:{ all -> 0x0013 }
            monitor-exit(r2)
            r3 = 1
            return r3
        L_0x0013:
            r3 = move-exception
            goto L_0x0018
        L_0x0015:
            monitor-exit(r2)
            r3 = 0
            return r3
        L_0x0018:
            monitor-exit(r2)     // Catch:{ all -> 0x0013 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.cache.LruCountingMemoryCache.maybeAddToExclusives(com.facebook.imagepipeline.cache.CountingMemoryCache$Entry):boolean");
    }

    public int removeAll(Predicate predicate) {
        ArrayList removeAll;
        ArrayList removeAll2;
        synchronized (this) {
            removeAll = this.mExclusiveEntries.removeAll(predicate);
            removeAll2 = this.mCachedEntries.removeAll(predicate);
            makeOrphans(removeAll2);
        }
        maybeClose(removeAll2);
        maybeNotifyExclusiveEntryRemoval(removeAll);
        maybeUpdateCacheParams();
        maybeEvictEntries();
        return removeAll2.size();
    }

    public synchronized boolean contains(Predicate predicate) {
        return !this.mCachedEntries.getMatchingEntries(predicate).isEmpty();
    }

    private synchronized void maybeUpdateCacheParams() {
        if (this.mLastCacheParamsCheck + this.mMemoryCacheParams.paramsCheckIntervalMs <= SystemClock.uptimeMillis()) {
            this.mLastCacheParamsCheck = SystemClock.uptimeMillis();
            this.mMemoryCacheParams = (MemoryCacheParams) Preconditions.checkNotNull((MemoryCacheParams) this.mMemoryCacheParamsSupplier.get(), "mMemoryCacheParamsSupplier returned null");
        }
    }

    public void maybeEvictEntries() {
        ArrayList trimExclusivelyOwnedEntries;
        synchronized (this) {
            MemoryCacheParams memoryCacheParams = this.mMemoryCacheParams;
            int min = Math.min(memoryCacheParams.maxEvictionQueueEntries, memoryCacheParams.maxCacheEntries - getInUseCount());
            MemoryCacheParams memoryCacheParams2 = this.mMemoryCacheParams;
            trimExclusivelyOwnedEntries = trimExclusivelyOwnedEntries(min, Math.min(memoryCacheParams2.maxEvictionQueueSize, memoryCacheParams2.maxCacheSize - getInUseSizeInBytes()));
            makeOrphans(trimExclusivelyOwnedEntries);
        }
        maybeClose(trimExclusivelyOwnedEntries);
        maybeNotifyExclusiveEntryRemoval(trimExclusivelyOwnedEntries);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003e, code lost:
        if (r3.mIgnoreSizeMismatch == false) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0040, code lost:
        r3.mExclusiveEntries.resetSize();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0046, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x006a, code lost:
        throw new java.lang.IllegalStateException(java.lang.String.format("key is null, but exclusiveEntries count: %d, size: %d", new java.lang.Object[]{java.lang.Integer.valueOf(r3.mExclusiveEntries.getCount()), java.lang.Integer.valueOf(r3.mExclusiveEntries.getSizeInBytes())}));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized java.util.ArrayList trimExclusivelyOwnedEntries(int r4, int r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            r0 = 0
            int r4 = java.lang.Math.max(r4, r0)     // Catch:{ all -> 0x001d }
            int r5 = java.lang.Math.max(r5, r0)     // Catch:{ all -> 0x001d }
            com.facebook.imagepipeline.cache.CountingLruMap r0 = r3.mExclusiveEntries     // Catch:{ all -> 0x001d }
            int r0 = r0.getCount()     // Catch:{ all -> 0x001d }
            if (r0 > r4) goto L_0x001f
            com.facebook.imagepipeline.cache.CountingLruMap r0 = r3.mExclusiveEntries     // Catch:{ all -> 0x001d }
            int r0 = r0.getSizeInBytes()     // Catch:{ all -> 0x001d }
            if (r0 > r5) goto L_0x001f
            monitor-exit(r3)
            r4 = 0
            return r4
        L_0x001d:
            r4 = move-exception
            goto L_0x007c
        L_0x001f:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x001d }
            r0.<init>()     // Catch:{ all -> 0x001d }
        L_0x0024:
            com.facebook.imagepipeline.cache.CountingLruMap r1 = r3.mExclusiveEntries     // Catch:{ all -> 0x001d }
            int r1 = r1.getCount()     // Catch:{ all -> 0x001d }
            if (r1 > r4) goto L_0x0034
            com.facebook.imagepipeline.cache.CountingLruMap r1 = r3.mExclusiveEntries     // Catch:{ all -> 0x001d }
            int r1 = r1.getSizeInBytes()     // Catch:{ all -> 0x001d }
            if (r1 <= r5) goto L_0x0045
        L_0x0034:
            com.facebook.imagepipeline.cache.CountingLruMap r1 = r3.mExclusiveEntries     // Catch:{ all -> 0x001d }
            java.lang.Object r1 = r1.getFirstKey()     // Catch:{ all -> 0x001d }
            if (r1 != 0) goto L_0x006b
            boolean r4 = r3.mIgnoreSizeMismatch     // Catch:{ all -> 0x001d }
            if (r4 == 0) goto L_0x0047
            com.facebook.imagepipeline.cache.CountingLruMap r4 = r3.mExclusiveEntries     // Catch:{ all -> 0x001d }
            r4.resetSize()     // Catch:{ all -> 0x001d }
        L_0x0045:
            monitor-exit(r3)
            return r0
        L_0x0047:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch:{ all -> 0x001d }
            java.lang.String r5 = "key is null, but exclusiveEntries count: %d, size: %d"
            com.facebook.imagepipeline.cache.CountingLruMap r0 = r3.mExclusiveEntries     // Catch:{ all -> 0x001d }
            int r0 = r0.getCount()     // Catch:{ all -> 0x001d }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x001d }
            com.facebook.imagepipeline.cache.CountingLruMap r1 = r3.mExclusiveEntries     // Catch:{ all -> 0x001d }
            int r1 = r1.getSizeInBytes()     // Catch:{ all -> 0x001d }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x001d }
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}     // Catch:{ all -> 0x001d }
            java.lang.String r5 = java.lang.String.format(r5, r0)     // Catch:{ all -> 0x001d }
            r4.<init>(r5)     // Catch:{ all -> 0x001d }
            throw r4     // Catch:{ all -> 0x001d }
        L_0x006b:
            com.facebook.imagepipeline.cache.CountingLruMap r2 = r3.mExclusiveEntries     // Catch:{ all -> 0x001d }
            r2.remove(r1)     // Catch:{ all -> 0x001d }
            com.facebook.imagepipeline.cache.CountingLruMap r2 = r3.mCachedEntries     // Catch:{ all -> 0x001d }
            java.lang.Object r1 = r2.remove(r1)     // Catch:{ all -> 0x001d }
            com.facebook.imagepipeline.cache.CountingMemoryCache$Entry r1 = (com.facebook.imagepipeline.cache.CountingMemoryCache.Entry) r1     // Catch:{ all -> 0x001d }
            r0.add(r1)     // Catch:{ all -> 0x001d }
            goto L_0x0024
        L_0x007c:
            monitor-exit(r3)     // Catch:{ all -> 0x001d }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.cache.LruCountingMemoryCache.trimExclusivelyOwnedEntries(int, int):java.util.ArrayList");
    }

    private void maybeClose(ArrayList arrayList) {
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                CloseableReference.closeSafely(referenceToClose((CountingMemoryCache.Entry) it.next()));
            }
        }
    }

    private void maybeNotifyExclusiveEntryRemoval(ArrayList arrayList) {
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                maybeNotifyExclusiveEntryRemoval((CountingMemoryCache.Entry) it.next());
            }
        }
    }

    private synchronized void makeOrphans(ArrayList arrayList) {
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                makeOrphan((CountingMemoryCache.Entry) it.next());
            }
        }
    }

    private synchronized void makeOrphan(CountingMemoryCache.Entry entry) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkState(!entry.isOrphan);
        entry.isOrphan = true;
    }

    private synchronized void increaseClientCount(CountingMemoryCache.Entry entry) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkState(!entry.isOrphan);
        entry.clientCount++;
    }

    private synchronized void decreaseClientCount(CountingMemoryCache.Entry entry) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkState(entry.clientCount > 0);
        entry.clientCount--;
    }

    private synchronized CloseableReference referenceToClose(CountingMemoryCache.Entry entry) {
        Preconditions.checkNotNull(entry);
        return (!entry.isOrphan || entry.clientCount != 0) ? null : entry.valueRef;
    }

    public synchronized int getInUseCount() {
        return this.mCachedEntries.getCount() - this.mExclusiveEntries.getCount();
    }

    public synchronized int getInUseSizeInBytes() {
        return this.mCachedEntries.getSizeInBytes() - this.mExclusiveEntries.getSizeInBytes();
    }
}
