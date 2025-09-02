package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.image.CloseableImage;

public class CountingLruBitmapMemoryCacheFactory implements BitmapMemoryCacheFactory {
    public CountingMemoryCache create(Supplier supplier, MemoryTrimmableRegistry memoryTrimmableRegistry, MemoryCache.CacheTrimStrategy cacheTrimStrategy, boolean z, boolean z2, CountingMemoryCache.EntryStateObserver entryStateObserver) {
        LruCountingMemoryCache lruCountingMemoryCache = new LruCountingMemoryCache(new ValueDescriptor() {
            public int getSizeInBytes(CloseableImage closeableImage) {
                return closeableImage.getSizeInBytes();
            }
        }, cacheTrimStrategy, supplier, entryStateObserver, z, z2);
        memoryTrimmableRegistry.registerMemoryTrimmable(lruCountingMemoryCache);
        return lruCountingMemoryCache;
    }
}
