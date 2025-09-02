package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.cache.MemoryCache;

public interface BitmapMemoryCacheFactory {
    CountingMemoryCache create(Supplier supplier, MemoryTrimmableRegistry memoryTrimmableRegistry, MemoryCache.CacheTrimStrategy cacheTrimStrategy, boolean z, boolean z2, CountingMemoryCache.EntryStateObserver entryStateObserver);
}
