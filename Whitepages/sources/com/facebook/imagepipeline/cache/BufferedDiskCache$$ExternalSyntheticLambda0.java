package com.facebook.imagepipeline.cache;

import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.image.EncodedImage;

public final /* synthetic */ class BufferedDiskCache$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ BufferedDiskCache f$1;
    public final /* synthetic */ CacheKey f$2;
    public final /* synthetic */ EncodedImage f$3;

    public /* synthetic */ BufferedDiskCache$$ExternalSyntheticLambda0(Object obj, BufferedDiskCache bufferedDiskCache, CacheKey cacheKey, EncodedImage encodedImage) {
        this.f$0 = obj;
        this.f$1 = bufferedDiskCache;
        this.f$2 = cacheKey;
        this.f$3 = encodedImage;
    }

    public final void run() {
        BufferedDiskCache.put$lambda$6$lambda$5(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
