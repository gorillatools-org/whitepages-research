package com.facebook.cache.disk;

import android.content.Context;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheEventListener;
import com.facebook.cache.common.NoOpCacheErrorLogger;
import com.facebook.cache.common.NoOpCacheEventListener;
import com.facebook.common.disk.DiskTrimmableRegistry;
import com.facebook.common.disk.NoOpDiskTrimmableRegistry;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import java.io.File;

public class DiskCacheConfig {
    private final String mBaseDirectoryName;
    private final Supplier mBaseDirectoryPathSupplier;
    private final CacheErrorLogger mCacheErrorLogger;
    private final CacheEventListener mCacheEventListener;
    /* access modifiers changed from: private */
    public final Context mContext;
    private final long mDefaultSizeLimit;
    private final DiskTrimmableRegistry mDiskTrimmableRegistry;
    private final EntryEvictionComparatorSupplier mEntryEvictionComparatorSupplier;
    private final boolean mIndexPopulateAtStartupEnabled;
    private final long mLowDiskSpaceSizeLimit;
    private final long mMinimumSizeLimit;
    private final int mVersion;

    protected DiskCacheConfig(Builder builder) {
        CacheErrorLogger cacheErrorLogger;
        CacheEventListener cacheEventListener;
        DiskTrimmableRegistry diskTrimmableRegistry;
        Context r0 = builder.mContext;
        this.mContext = r0;
        Preconditions.checkState((builder.mBaseDirectoryPathSupplier == null && r0 == null) ? false : true, "Either a non-null context or a base directory path or supplier must be provided.");
        if (builder.mBaseDirectoryPathSupplier == null && r0 != null) {
            builder.mBaseDirectoryPathSupplier = new Supplier() {
                public File get() {
                    Preconditions.checkNotNull(DiskCacheConfig.this.mContext);
                    return DiskCacheConfig.this.mContext.getApplicationContext().getCacheDir();
                }
            };
        }
        this.mVersion = builder.mVersion;
        this.mBaseDirectoryName = (String) Preconditions.checkNotNull(builder.mBaseDirectoryName);
        this.mBaseDirectoryPathSupplier = (Supplier) Preconditions.checkNotNull(builder.mBaseDirectoryPathSupplier);
        this.mDefaultSizeLimit = builder.mMaxCacheSize;
        this.mLowDiskSpaceSizeLimit = builder.mMaxCacheSizeOnLowDiskSpace;
        this.mMinimumSizeLimit = builder.mMaxCacheSizeOnVeryLowDiskSpace;
        this.mEntryEvictionComparatorSupplier = (EntryEvictionComparatorSupplier) Preconditions.checkNotNull(builder.mEntryEvictionComparatorSupplier);
        if (builder.mCacheErrorLogger == null) {
            cacheErrorLogger = NoOpCacheErrorLogger.getInstance();
        } else {
            cacheErrorLogger = builder.mCacheErrorLogger;
        }
        this.mCacheErrorLogger = cacheErrorLogger;
        if (builder.mCacheEventListener == null) {
            cacheEventListener = NoOpCacheEventListener.getInstance();
        } else {
            cacheEventListener = builder.mCacheEventListener;
        }
        this.mCacheEventListener = cacheEventListener;
        if (builder.mDiskTrimmableRegistry == null) {
            diskTrimmableRegistry = NoOpDiskTrimmableRegistry.getInstance();
        } else {
            diskTrimmableRegistry = builder.mDiskTrimmableRegistry;
        }
        this.mDiskTrimmableRegistry = diskTrimmableRegistry;
        this.mIndexPopulateAtStartupEnabled = builder.mIndexPopulateAtStartupEnabled;
    }

    public int getVersion() {
        return this.mVersion;
    }

    public String getBaseDirectoryName() {
        return this.mBaseDirectoryName;
    }

    public Supplier getBaseDirectoryPathSupplier() {
        return this.mBaseDirectoryPathSupplier;
    }

    public long getDefaultSizeLimit() {
        return this.mDefaultSizeLimit;
    }

    public long getLowDiskSpaceSizeLimit() {
        return this.mLowDiskSpaceSizeLimit;
    }

    public long getMinimumSizeLimit() {
        return this.mMinimumSizeLimit;
    }

    public EntryEvictionComparatorSupplier getEntryEvictionComparatorSupplier() {
        return this.mEntryEvictionComparatorSupplier;
    }

    public CacheErrorLogger getCacheErrorLogger() {
        return this.mCacheErrorLogger;
    }

    public CacheEventListener getCacheEventListener() {
        return this.mCacheEventListener;
    }

    public DiskTrimmableRegistry getDiskTrimmableRegistry() {
        return this.mDiskTrimmableRegistry;
    }

    public boolean getIndexPopulateAtStartupEnabled() {
        return this.mIndexPopulateAtStartupEnabled;
    }

    public static Builder newBuilder(Context context) {
        return new Builder(context);
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public String mBaseDirectoryName;
        /* access modifiers changed from: private */
        public Supplier mBaseDirectoryPathSupplier;
        /* access modifiers changed from: private */
        public CacheErrorLogger mCacheErrorLogger;
        /* access modifiers changed from: private */
        public CacheEventListener mCacheEventListener;
        /* access modifiers changed from: private */
        public final Context mContext;
        /* access modifiers changed from: private */
        public DiskTrimmableRegistry mDiskTrimmableRegistry;
        /* access modifiers changed from: private */
        public EntryEvictionComparatorSupplier mEntryEvictionComparatorSupplier;
        /* access modifiers changed from: private */
        public boolean mIndexPopulateAtStartupEnabled;
        /* access modifiers changed from: private */
        public long mMaxCacheSize;
        /* access modifiers changed from: private */
        public long mMaxCacheSizeOnLowDiskSpace;
        /* access modifiers changed from: private */
        public long mMaxCacheSizeOnVeryLowDiskSpace;
        /* access modifiers changed from: private */
        public int mVersion;

        private Builder(Context context) {
            this.mVersion = 1;
            this.mBaseDirectoryName = "image_cache";
            this.mMaxCacheSize = 41943040;
            this.mMaxCacheSizeOnLowDiskSpace = 10485760;
            this.mMaxCacheSizeOnVeryLowDiskSpace = 2097152;
            this.mEntryEvictionComparatorSupplier = new DefaultEntryEvictionComparatorSupplier();
            this.mContext = context;
        }

        public DiskCacheConfig build() {
            return new DiskCacheConfig(this);
        }
    }
}
