package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.cache.MemoryCache;

public abstract class EncodedCountingMemoryCacheFactory {
    public static CountingMemoryCache get(Supplier supplier, MemoryTrimmableRegistry memoryTrimmableRegistry, MemoryCache.CacheTrimStrategy cacheTrimStrategy) {
        LruCountingMemoryCache lruCountingMemoryCache = new LruCountingMemoryCache(new ValueDescriptor() {
            public int getSizeInBytes(PooledByteBuffer pooledByteBuffer) {
                return pooledByteBuffer.size();
            }
        }, cacheTrimStrategy, supplier, (CountingMemoryCache.EntryStateObserver) null, false, false);
        memoryTrimmableRegistry.registerMemoryTrimmable(lruCountingMemoryCache);
        return lruCountingMemoryCache;
    }
}
