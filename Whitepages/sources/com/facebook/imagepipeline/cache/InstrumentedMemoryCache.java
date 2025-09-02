package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Predicate;
import com.facebook.common.references.CloseableReference;

public class InstrumentedMemoryCache implements MemoryCache {
    private final MemoryCache mDelegate;
    private final MemoryCacheTracker mTracker;

    public InstrumentedMemoryCache(MemoryCache memoryCache, MemoryCacheTracker memoryCacheTracker) {
        this.mDelegate = memoryCache;
        this.mTracker = memoryCacheTracker;
    }

    public CloseableReference get(Object obj) {
        CloseableReference closeableReference = this.mDelegate.get(obj);
        if (closeableReference == null) {
            this.mTracker.onCacheMiss(obj);
        } else {
            this.mTracker.onCacheHit(obj);
        }
        return closeableReference;
    }

    public void probe(Object obj) {
        this.mDelegate.probe(obj);
    }

    public CloseableReference cache(Object obj, CloseableReference closeableReference) {
        this.mTracker.onCachePut(obj);
        return this.mDelegate.cache(obj, closeableReference);
    }

    public int removeAll(Predicate predicate) {
        return this.mDelegate.removeAll(predicate);
    }

    public boolean contains(Predicate predicate) {
        return this.mDelegate.contains(predicate);
    }
}
