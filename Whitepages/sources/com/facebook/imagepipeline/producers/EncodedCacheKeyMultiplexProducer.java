package com.facebook.imagepipeline.producers;

import android.util.Pair;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.image.EncodedImage;

public class EncodedCacheKeyMultiplexProducer extends MultiplexProducer {
    private final CacheKeyFactory mCacheKeyFactory;

    public EncodedCacheKeyMultiplexProducer(CacheKeyFactory cacheKeyFactory, boolean z, Producer producer) {
        super(producer, "EncodedCacheKeyMultiplexProducer", "multiplex_enc_cnt", z);
        this.mCacheKeyFactory = cacheKeyFactory;
    }

    /* access modifiers changed from: protected */
    public Pair getKey(ProducerContext producerContext) {
        return Pair.create(this.mCacheKeyFactory.getEncodedCacheKey(producerContext.getImageRequest(), producerContext.getCallerContext()), producerContext.getLowestPermittedRequestLevel());
    }

    public EncodedImage cloneOrNull(EncodedImage encodedImage) {
        return EncodedImage.cloneOrNull(encodedImage);
    }
}
