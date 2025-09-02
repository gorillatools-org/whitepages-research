package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;

public abstract class InstrumentedMemoryCacheBitmapMemoryCacheFactory {
    public static InstrumentedMemoryCache get(MemoryCache memoryCache, final ImageCacheStatsTracker imageCacheStatsTracker) {
        imageCacheStatsTracker.registerBitmapMemoryCache(memoryCache);
        return new InstrumentedMemoryCache(memoryCache, new MemoryCacheTracker() {
            public void onCacheHit(CacheKey cacheKey) {
                ImageCacheStatsTracker.this.onBitmapCacheHit(cacheKey);
            }

            public void onCacheMiss(CacheKey cacheKey) {
                ImageCacheStatsTracker.this.onBitmapCacheMiss(cacheKey);
            }

            public void onCachePut(CacheKey cacheKey) {
                ImageCacheStatsTracker.this.onBitmapCachePut(cacheKey);
            }
        });
    }
}
