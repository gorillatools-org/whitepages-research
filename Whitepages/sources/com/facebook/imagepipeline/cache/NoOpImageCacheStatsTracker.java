package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;

public class NoOpImageCacheStatsTracker implements ImageCacheStatsTracker {
    private static NoOpImageCacheStatsTracker sInstance;

    public void onBitmapCacheHit(CacheKey cacheKey) {
    }

    public void onBitmapCacheMiss(CacheKey cacheKey) {
    }

    public void onBitmapCachePut(CacheKey cacheKey) {
    }

    public void onDiskCacheGetFail(CacheKey cacheKey) {
    }

    public void onDiskCacheHit(CacheKey cacheKey) {
    }

    public void onDiskCacheMiss(CacheKey cacheKey) {
    }

    public void onDiskCachePut(CacheKey cacheKey) {
    }

    public void onMemoryCacheHit(CacheKey cacheKey) {
    }

    public void onMemoryCacheMiss(CacheKey cacheKey) {
    }

    public void onMemoryCachePut(CacheKey cacheKey) {
    }

    public void onStagingAreaHit(CacheKey cacheKey) {
    }

    public void onStagingAreaMiss(CacheKey cacheKey) {
    }

    public void registerBitmapMemoryCache(MemoryCache memoryCache) {
    }

    public void registerEncodedMemoryCache(MemoryCache memoryCache) {
    }

    private NoOpImageCacheStatsTracker() {
    }

    public static synchronized NoOpImageCacheStatsTracker getInstance() {
        NoOpImageCacheStatsTracker noOpImageCacheStatsTracker;
        synchronized (NoOpImageCacheStatsTracker.class) {
            try {
                if (sInstance == null) {
                    sInstance = new NoOpImageCacheStatsTracker();
                }
                noOpImageCacheStatsTracker = sInstance;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return noOpImageCacheStatsTracker;
    }
}
