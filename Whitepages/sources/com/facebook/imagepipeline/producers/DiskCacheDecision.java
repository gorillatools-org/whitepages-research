package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

public final class DiskCacheDecision {
    public static final DiskCacheDecision INSTANCE = new DiskCacheDecision();

    private DiskCacheDecision() {
    }

    public static final BufferedDiskCache chooseDiskCacheForRequest(ImageRequest imageRequest, BufferedDiskCache bufferedDiskCache, BufferedDiskCache bufferedDiskCache2, Map map) {
        String diskCacheId;
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        if (imageRequest.getCacheChoice() == ImageRequest.CacheChoice.SMALL) {
            return bufferedDiskCache;
        }
        if (imageRequest.getCacheChoice() == ImageRequest.CacheChoice.DEFAULT) {
            return bufferedDiskCache2;
        }
        if (imageRequest.getCacheChoice() != ImageRequest.CacheChoice.DYNAMIC || map == null || (diskCacheId = imageRequest.getDiskCacheId()) == null) {
            return null;
        }
        return (BufferedDiskCache) map.get(diskCacheId);
    }

    public static final class DiskCacheDecisionNoDiskCacheChosenException extends Exception {
        public DiskCacheDecisionNoDiskCacheChosenException(String str) {
            super(str);
        }
    }
}
