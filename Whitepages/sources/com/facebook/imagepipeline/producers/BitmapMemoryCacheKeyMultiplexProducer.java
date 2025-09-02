package com.facebook.imagepipeline.producers;

import android.util.Pair;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.cache.CacheKeyFactory;

public class BitmapMemoryCacheKeyMultiplexProducer extends MultiplexProducer {
    private final CacheKeyFactory mCacheKeyFactory;

    public BitmapMemoryCacheKeyMultiplexProducer(CacheKeyFactory cacheKeyFactory, Producer producer) {
        super(producer, "BitmapMemoryCacheKeyMultiplexProducer", "multiplex_bmp_cnt");
        this.mCacheKeyFactory = cacheKeyFactory;
    }

    /* access modifiers changed from: protected */
    public Pair getKey(ProducerContext producerContext) {
        return Pair.create(this.mCacheKeyFactory.getBitmapCacheKey(producerContext.getImageRequest(), producerContext.getCallerContext()), producerContext.getLowestPermittedRequestLevel());
    }

    public CloseableReference cloneOrNull(CloseableReference closeableReference) {
        return CloseableReference.cloneOrNull(closeableReference);
    }
}
