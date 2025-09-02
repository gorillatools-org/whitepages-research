package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.references.CloseableReference;
import com.facebook.hermes.intl.Constants;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.Map;

public class EncodedMemoryCacheProducer implements Producer {
    private final CacheKeyFactory mCacheKeyFactory;
    private final Producer mInputProducer;
    private final MemoryCache mMemoryCache;

    public EncodedMemoryCacheProducer(MemoryCache memoryCache, CacheKeyFactory cacheKeyFactory, Producer producer) {
        this.mMemoryCache = memoryCache;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mInputProducer = producer;
    }

    public void produceResults(Consumer consumer, ProducerContext producerContext) {
        EncodedImage encodedImage;
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("EncodedMemoryCacheProducer#produceResults");
            }
            ProducerListener2 producerListener = producerContext.getProducerListener();
            producerListener.onProducerStart(producerContext, "EncodedMemoryCacheProducer");
            CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(producerContext.getImageRequest(), producerContext.getCallerContext());
            Map map = null;
            CloseableReference closeableReference = producerContext.getImageRequest().isCacheEnabled(4) ? this.mMemoryCache.get(encodedCacheKey) : null;
            if (closeableReference != null) {
                try {
                    encodedImage = new EncodedImage(closeableReference);
                    if (producerListener.requiresExtraMap(producerContext, "EncodedMemoryCacheProducer")) {
                        map = ImmutableMap.of("cached_value_found", "true");
                    }
                    producerListener.onProducerFinishWithSuccess(producerContext, "EncodedMemoryCacheProducer", map);
                    producerListener.onUltimateProducerReached(producerContext, "EncodedMemoryCacheProducer", true);
                    producerContext.putOriginExtra("memory_encoded");
                    consumer.onProgressUpdate(1.0f);
                    consumer.onNewResult(encodedImage, 1);
                    EncodedImage.closeSafely(encodedImage);
                    CloseableReference.closeSafely(closeableReference);
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                    }
                } catch (Throwable th) {
                    CloseableReference.closeSafely(closeableReference);
                    throw th;
                }
            } else if (producerContext.getLowestPermittedRequestLevel().getValue() >= ImageRequest.RequestLevel.ENCODED_MEMORY_CACHE.getValue()) {
                producerListener.onProducerFinishWithSuccess(producerContext, "EncodedMemoryCacheProducer", producerListener.requiresExtraMap(producerContext, "EncodedMemoryCacheProducer") ? ImmutableMap.of("cached_value_found", Constants.CASEFIRST_FALSE) : null);
                producerListener.onUltimateProducerReached(producerContext, "EncodedMemoryCacheProducer", false);
                producerContext.putOriginExtra("memory_encoded", "nil-result");
                consumer.onNewResult((Object) null, 1);
                CloseableReference.closeSafely(closeableReference);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
            } else {
                EncodedMemoryCacheConsumer encodedMemoryCacheConsumer = new EncodedMemoryCacheConsumer(consumer, this.mMemoryCache, encodedCacheKey, producerContext.getImageRequest().isCacheEnabled(8), producerContext.getImagePipelineConfig().getExperiments().isEncodedCacheEnabled());
                if (producerListener.requiresExtraMap(producerContext, "EncodedMemoryCacheProducer")) {
                    map = ImmutableMap.of("cached_value_found", Constants.CASEFIRST_FALSE);
                }
                producerListener.onProducerFinishWithSuccess(producerContext, "EncodedMemoryCacheProducer", map);
                this.mInputProducer.produceResults(encodedMemoryCacheConsumer, producerContext);
                CloseableReference.closeSafely(closeableReference);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
            }
        } catch (Throwable th2) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            throw th2;
        }
    }

    private static class EncodedMemoryCacheConsumer extends DelegatingConsumer {
        private final boolean mEncodedCacheEnabled;
        private final boolean mIsEncodedCacheEnabledForWrite;
        private final MemoryCache mMemoryCache;
        private final CacheKey mRequestedCacheKey;

        public EncodedMemoryCacheConsumer(Consumer consumer, MemoryCache memoryCache, CacheKey cacheKey, boolean z, boolean z2) {
            super(consumer);
            this.mMemoryCache = memoryCache;
            this.mRequestedCacheKey = cacheKey;
            this.mIsEncodedCacheEnabledForWrite = z;
            this.mEncodedCacheEnabled = z2;
        }

        public void onNewResultImpl(EncodedImage encodedImage, int i) {
            CloseableReference byteBufferRef;
            CloseableReference cache;
            EncodedImage encodedImage2;
            try {
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.beginSection("EncodedMemoryCacheProducer#onNewResultImpl");
                }
                if (!BaseConsumer.isNotLast(i) && encodedImage != null && !BaseConsumer.statusHasAnyFlag(i, 10)) {
                    if (encodedImage.getImageFormat() != ImageFormat.UNKNOWN) {
                        byteBufferRef = encodedImage.getByteBufferRef();
                        if (byteBufferRef != null) {
                            cache = (!this.mEncodedCacheEnabled || !this.mIsEncodedCacheEnabledForWrite) ? null : this.mMemoryCache.cache(this.mRequestedCacheKey, byteBufferRef);
                            CloseableReference.closeSafely(byteBufferRef);
                            if (cache != null) {
                                encodedImage2 = new EncodedImage(cache);
                                encodedImage2.copyMetaDataFrom(encodedImage);
                                CloseableReference.closeSafely(cache);
                                getConsumer().onProgressUpdate(1.0f);
                                getConsumer().onNewResult(encodedImage2, i);
                                EncodedImage.closeSafely(encodedImage2);
                                if (FrescoSystrace.isTracing()) {
                                    FrescoSystrace.endSection();
                                    return;
                                }
                                return;
                            }
                        }
                        getConsumer().onNewResult(encodedImage, i);
                        if (FrescoSystrace.isTracing()) {
                            FrescoSystrace.endSection();
                            return;
                        }
                        return;
                    }
                }
                getConsumer().onNewResult(encodedImage, i);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
            } catch (Throwable th) {
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                throw th;
            }
        }
    }
}
