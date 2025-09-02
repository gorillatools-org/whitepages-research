package com.facebook.imagepipeline.core;

import com.facebook.common.internal.ImmutableMap;
import com.facebook.imagepipeline.cache.BufferedDiskCache;

public interface DiskCachesStore {
    ImmutableMap getDynamicBufferedDiskCaches();

    BufferedDiskCache getMainBufferedDiskCache();

    BufferedDiskCache getSmallImageBufferedDiskCache();
}
