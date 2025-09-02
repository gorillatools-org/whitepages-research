package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.references.CloseableReference;
import com.facebook.hermes.intl.Constants;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.HasImageMetadata;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.Map;

public class BitmapMemoryCacheProducer implements Producer {
    private final CacheKeyFactory mCacheKeyFactory;
    private final Producer mInputProducer;
    /* access modifiers changed from: private */
    public final MemoryCache mMemoryCache;

    public BitmapMemoryCacheProducer(MemoryCache memoryCache, CacheKeyFactory cacheKeyFactory, Producer producer) {
        this.mMemoryCache = memoryCache;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mInputProducer = producer;
    }

    public void produceResults(Consumer consumer, ProducerContext producerContext) {
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("BitmapMemoryCacheProducer#produceResults");
            }
            ProducerListener2 producerListener = producerContext.getProducerListener();
            producerListener.onProducerStart(producerContext, getProducerName());
            CacheKey bitmapCacheKey = this.mCacheKeyFactory.getBitmapCacheKey(producerContext.getImageRequest(), producerContext.getCallerContext());
            Map map = null;
            CloseableReference closeableReference = producerContext.getImageRequest().isCacheEnabled(1) ? this.mMemoryCache.get(bitmapCacheKey) : null;
            if (closeableReference != null) {
                maybeSetExtrasFromCloseableImage((HasImageMetadata) closeableReference.get(), producerContext);
                boolean isOfFullQuality = ((CloseableImage) closeableReference.get()).getQualityInfo().isOfFullQuality();
                if (isOfFullQuality) {
                    producerListener.onProducerFinishWithSuccess(producerContext, getProducerName(), producerListener.requiresExtraMap(producerContext, getProducerName()) ? ImmutableMap.of("cached_value_found", "true") : null);
                    producerListener.onUltimateProducerReached(producerContext, getProducerName(), true);
                    producerContext.putOriginExtra("memory_bitmap", getOriginSubcategory());
                    consumer.onProgressUpdate(1.0f);
                }
                consumer.onNewResult(closeableReference, BaseConsumer.simpleStatusForIsLast(isOfFullQuality));
                closeableReference.close();
                if (isOfFullQuality) {
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                        return;
                    }
                    return;
                }
            }
            if (producerContext.getLowestPermittedRequestLevel().getValue() >= ImageRequest.RequestLevel.BITMAP_MEMORY_CACHE.getValue()) {
                producerListener.onProducerFinishWithSuccess(producerContext, getProducerName(), producerListener.requiresExtraMap(producerContext, getProducerName()) ? ImmutableMap.of("cached_value_found", Constants.CASEFIRST_FALSE) : null);
                producerListener.onUltimateProducerReached(producerContext, getProducerName(), false);
                producerContext.putOriginExtra("memory_bitmap", getOriginSubcategory());
                consumer.onNewResult((Object) null, 1);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                    return;
                }
                return;
            }
            Consumer wrapConsumer = wrapConsumer(consumer, bitmapCacheKey, producerContext.getImageRequest().isCacheEnabled(2));
            String producerName = getProducerName();
            if (producerListener.requiresExtraMap(producerContext, getProducerName())) {
                map = ImmutableMap.of("cached_value_found", Constants.CASEFIRST_FALSE);
            }
            producerListener.onProducerFinishWithSuccess(producerContext, producerName, map);
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("mInputProducer.produceResult");
            }
            this.mInputProducer.produceResults(wrapConsumer, producerContext);
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
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

    /* access modifiers changed from: protected */
    public Consumer wrapConsumer(Consumer consumer, final CacheKey cacheKey, final boolean z) {
        return new DelegatingConsumer(consumer) {
            public void onNewResultImpl(CloseableReference closeableReference, int i) {
                CloseableReference closeableReference2;
                CloseableReference closeableReference3;
                try {
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.beginSection("BitmapMemoryCacheProducer#onNewResultImpl");
                    }
                    boolean isLast = BaseConsumer.isLast(i);
                    closeableReference2 = null;
                    if (closeableReference == null) {
                        if (isLast) {
                            getConsumer().onNewResult((Object) null, i);
                        }
                        if (FrescoSystrace.isTracing()) {
                            FrescoSystrace.endSection();
                            return;
                        }
                        return;
                    }
                    if (!((CloseableImage) closeableReference.get()).isStateful()) {
                        if (!BaseConsumer.statusHasFlag(i, 8)) {
                            if (!isLast && (closeableReference3 = BitmapMemoryCacheProducer.this.mMemoryCache.get(cacheKey)) != null) {
                                QualityInfo qualityInfo = ((CloseableImage) closeableReference.get()).getQualityInfo();
                                QualityInfo qualityInfo2 = ((CloseableImage) closeableReference3.get()).getQualityInfo();
                                if (qualityInfo2.isOfFullQuality() || qualityInfo2.getQuality() >= qualityInfo.getQuality()) {
                                    getConsumer().onNewResult(closeableReference3, i);
                                    CloseableReference.closeSafely(closeableReference3);
                                    if (FrescoSystrace.isTracing()) {
                                        FrescoSystrace.endSection();
                                        return;
                                    }
                                    return;
                                }
                                CloseableReference.closeSafely(closeableReference3);
                            }
                            if (z) {
                                closeableReference2 = BitmapMemoryCacheProducer.this.mMemoryCache.cache(cacheKey, closeableReference);
                            }
                            if (isLast) {
                                getConsumer().onProgressUpdate(1.0f);
                            }
                            Consumer consumer = getConsumer();
                            if (closeableReference2 != null) {
                                closeableReference = closeableReference2;
                            }
                            consumer.onNewResult(closeableReference, i);
                            CloseableReference.closeSafely(closeableReference2);
                            if (FrescoSystrace.isTracing()) {
                                FrescoSystrace.endSection();
                                return;
                            }
                            return;
                        }
                    }
                    getConsumer().onNewResult(closeableReference, i);
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
        };
    }

    /* access modifiers changed from: protected */
    public String getProducerName() {
        return "BitmapMemoryCacheProducer";
    }

    private static void maybeSetExtrasFromCloseableImage(HasImageMetadata hasImageMetadata, ProducerContext producerContext) {
        producerContext.putExtras(hasImageMetadata.getExtras());
    }

    /* access modifiers changed from: protected */
    public String getOriginSubcategory() {
        return "pipe_bg";
    }
}
