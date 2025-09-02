package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

public final /* synthetic */ class BufferedDiskCache$$ExternalSyntheticLambda1 implements Callable {
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ AtomicBoolean f$1;
    public final /* synthetic */ BufferedDiskCache f$2;
    public final /* synthetic */ CacheKey f$3;

    public /* synthetic */ BufferedDiskCache$$ExternalSyntheticLambda1(Object obj, AtomicBoolean atomicBoolean, BufferedDiskCache bufferedDiskCache, CacheKey cacheKey) {
        this.f$0 = obj;
        this.f$1 = atomicBoolean;
        this.f$2 = bufferedDiskCache;
        this.f$3 = cacheKey;
    }

    public final Object call() {
        return BufferedDiskCache.getAsync$lambda$4(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
