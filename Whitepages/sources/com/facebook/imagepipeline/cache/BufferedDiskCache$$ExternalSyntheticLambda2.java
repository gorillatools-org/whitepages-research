package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import java.util.concurrent.Callable;

public final /* synthetic */ class BufferedDiskCache$$ExternalSyntheticLambda2 implements Callable {
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ BufferedDiskCache f$1;
    public final /* synthetic */ CacheKey f$2;

    public /* synthetic */ BufferedDiskCache$$ExternalSyntheticLambda2(Object obj, BufferedDiskCache bufferedDiskCache, CacheKey cacheKey) {
        this.f$0 = obj;
        this.f$1 = bufferedDiskCache;
        this.f$2 = cacheKey;
    }

    public final Object call() {
        return BufferedDiskCache.remove$lambda$7(this.f$0, this.f$1, this.f$2);
    }
}
