package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.references.CloseableReference;
import com.facebook.hermes.intl.Constants;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.Postprocessor;
import java.util.Map;

public class PostprocessedBitmapMemoryCacheProducer implements Producer {
    private final CacheKeyFactory mCacheKeyFactory;
    private final Producer mInputProducer;
    private final MemoryCache mMemoryCache;

    public PostprocessedBitmapMemoryCacheProducer(MemoryCache memoryCache, CacheKeyFactory cacheKeyFactory, Producer producer) {
        this.mMemoryCache = memoryCache;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mInputProducer = producer;
    }

    public void produceResults(Consumer consumer, ProducerContext producerContext) {
        ProducerListener2 producerListener = producerContext.getProducerListener();
        ImageRequest imageRequest = producerContext.getImageRequest();
        Object callerContext = producerContext.getCallerContext();
        Postprocessor postprocessor = imageRequest.getPostprocessor();
        if (postprocessor == null || postprocessor.getPostprocessorCacheKey() == null) {
            this.mInputProducer.produceResults(consumer, producerContext);
            return;
        }
        producerListener.onProducerStart(producerContext, getProducerName());
        CacheKey postprocessedBitmapCacheKey = this.mCacheKeyFactory.getPostprocessedBitmapCacheKey(imageRequest, callerContext);
        Map map = null;
        CloseableReference closeableReference = producerContext.getImageRequest().isCacheEnabled(1) ? this.mMemoryCache.get(postprocessedBitmapCacheKey) : null;
        if (closeableReference != null) {
            String producerName = getProducerName();
            if (producerListener.requiresExtraMap(producerContext, getProducerName())) {
                map = ImmutableMap.of("cached_value_found", "true");
            }
            producerListener.onProducerFinishWithSuccess(producerContext, producerName, map);
            producerListener.onUltimateProducerReached(producerContext, "PostprocessedBitmapMemoryCacheProducer", true);
            producerContext.putOriginExtra("memory_bitmap", "postprocessed");
            consumer.onProgressUpdate(1.0f);
            consumer.onNewResult(closeableReference, 1);
            closeableReference.close();
            return;
        }
        CachedPostprocessorConsumer cachedPostprocessorConsumer = new CachedPostprocessorConsumer(consumer, postprocessedBitmapCacheKey, false, this.mMemoryCache, producerContext.getImageRequest().isCacheEnabled(2));
        String producerName2 = getProducerName();
        if (producerListener.requiresExtraMap(producerContext, getProducerName())) {
            map = ImmutableMap.of("cached_value_found", Constants.CASEFIRST_FALSE);
        }
        producerListener.onProducerFinishWithSuccess(producerContext, producerName2, map);
        this.mInputProducer.produceResults(cachedPostprocessorConsumer, producerContext);
    }

    public static class CachedPostprocessorConsumer extends DelegatingConsumer {
        private final CacheKey mCacheKey;
        private final boolean mIsBitmapCacheEnabledForWrite;
        private final boolean mIsRepeatedProcessor;
        private final MemoryCache mMemoryCache;

        public CachedPostprocessorConsumer(Consumer consumer, CacheKey cacheKey, boolean z, MemoryCache memoryCache, boolean z2) {
            super(consumer);
            this.mCacheKey = cacheKey;
            this.mIsRepeatedProcessor = z;
            this.mMemoryCache = memoryCache;
            this.mIsBitmapCacheEnabledForWrite = z2;
        }

        /* access modifiers changed from: protected */
        public void onNewResultImpl(CloseableReference closeableReference, int i) {
            CloseableReference closeableReference2 = null;
            if (closeableReference == null) {
                if (BaseConsumer.isLast(i)) {
                    getConsumer().onNewResult(closeableReference2, i);
                }
            } else if (!BaseConsumer.isNotLast(i) || this.mIsRepeatedProcessor) {
                if (this.mIsBitmapCacheEnabledForWrite) {
                    closeableReference2 = this.mMemoryCache.cache(this.mCacheKey, closeableReference);
                }
                try {
                    getConsumer().onProgressUpdate(1.0f);
                    Consumer consumer = getConsumer();
                    if (closeableReference2 != null) {
                        closeableReference = closeableReference2;
                    }
                    consumer.onNewResult(closeableReference, i);
                } finally {
                    CloseableReference.closeSafely(closeableReference2);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public String getProducerName() {
        return "PostprocessedBitmapMemoryCacheProducer";
    }
}
