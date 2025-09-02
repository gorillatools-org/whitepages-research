package com.facebook.imagepipeline.cache;

public interface MemoryCacheTracker {
    void onCacheHit(Object obj);

    void onCacheMiss(Object obj);

    void onCachePut(Object obj);
}
