package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;

public abstract class EncodedMemoryCacheFactory {
    public static InstrumentedMemoryCache get(MemoryCache memoryCache, final ImageCacheStatsTracker imageCacheStatsTracker) {
        imageCacheStatsTracker.registerEncodedMemoryCache(memoryCache);
        return new InstrumentedMemoryCache(memoryCache, new MemoryCacheTracker() {
            public void onCacheHit(CacheKey cacheKey) {
                ImageCacheStatsTracker.this.onMemoryCacheHit(cacheKey);
            }

            public void onCacheMiss(CacheKey cacheKey) {
                ImageCacheStatsTracker.this.onMemoryCacheMiss(cacheKey);
            }

            public void onCachePut(CacheKey cacheKey) {
                ImageCacheStatsTracker.this.onMemoryCachePut(cacheKey);
            }
        });
    }
}
