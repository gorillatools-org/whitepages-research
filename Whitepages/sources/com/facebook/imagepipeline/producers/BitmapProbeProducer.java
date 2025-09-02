package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Supplier;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.cache.BoundedLinkedHashSet;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.core.DiskCachesStore;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.Map;

public class BitmapProbeProducer implements Producer {
    private final CacheKeyFactory mCacheKeyFactory;
    private final BoundedLinkedHashSet mDiskCacheHistory;
    private final Supplier mDiskCachesStoreSupplier;
    private final MemoryCache mEncodedMemoryCache;
    private final BoundedLinkedHashSet mEncodedMemoryCacheHistory;
    private final Producer mInputProducer;

    public BitmapProbeProducer(MemoryCache memoryCache, Supplier supplier, CacheKeyFactory cacheKeyFactory, BoundedLinkedHashSet boundedLinkedHashSet, BoundedLinkedHashSet boundedLinkedHashSet2, Producer producer) {
        this.mEncodedMemoryCache = memoryCache;
        this.mDiskCachesStoreSupplier = supplier;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mEncodedMemoryCacheHistory = boundedLinkedHashSet;
        this.mDiskCacheHistory = boundedLinkedHashSet2;
        this.mInputProducer = producer;
    }

    public void produceResults(Consumer consumer, ProducerContext producerContext) {
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("BitmapProbeProducer#produceResults");
            }
            ProducerListener2 producerListener = producerContext.getProducerListener();
            producerListener.onProducerStart(producerContext, getProducerName());
            ProbeConsumer probeConsumer = new ProbeConsumer(consumer, producerContext, this.mEncodedMemoryCache, this.mDiskCachesStoreSupplier, this.mCacheKeyFactory, this.mEncodedMemoryCacheHistory, this.mDiskCacheHistory);
            producerListener.onProducerFinishWithSuccess(producerContext, "BitmapProbeProducer", (Map) null);
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("mInputProducer.produceResult");
            }
            this.mInputProducer.produceResults(probeConsumer, producerContext);
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

    private static class ProbeConsumer extends DelegatingConsumer {
        private final CacheKeyFactory mCacheKeyFactory;
        private final BoundedLinkedHashSet mDiskCacheHistory;
        private final Supplier mDiskCachesStoreSupplier;
        private final MemoryCache mEncodedMemoryCache;
        private final BoundedLinkedHashSet mEncodedMemoryCacheHistory;
        private final ProducerContext mProducerContext;

        public ProbeConsumer(Consumer consumer, ProducerContext producerContext, MemoryCache memoryCache, Supplier supplier, CacheKeyFactory cacheKeyFactory, BoundedLinkedHashSet boundedLinkedHashSet, BoundedLinkedHashSet boundedLinkedHashSet2) {
            super(consumer);
            this.mProducerContext = producerContext;
            this.mEncodedMemoryCache = memoryCache;
            this.mDiskCachesStoreSupplier = supplier;
            this.mCacheKeyFactory = cacheKeyFactory;
            this.mEncodedMemoryCacheHistory = boundedLinkedHashSet;
            this.mDiskCacheHistory = boundedLinkedHashSet2;
        }

        public void onNewResultImpl(CloseableReference closeableReference, int i) {
            BufferedDiskCache bufferedDiskCache;
            try {
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.beginSection("BitmapProbeProducer#onNewResultImpl");
                }
                if (!BaseConsumer.isNotLast(i) && closeableReference != null) {
                    if (!BaseConsumer.statusHasAnyFlag(i, 8)) {
                        ImageRequest imageRequest = this.mProducerContext.getImageRequest();
                        CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, this.mProducerContext.getCallerContext());
                        String str = (String) this.mProducerContext.getExtra("origin");
                        if (str != null && str.equals("memory_bitmap")) {
                            if (this.mProducerContext.getImagePipelineConfig().getExperiments().isEncodedMemoryCacheProbingEnabled() && !this.mEncodedMemoryCacheHistory.contains(encodedCacheKey)) {
                                this.mEncodedMemoryCache.probe(encodedCacheKey);
                                this.mEncodedMemoryCacheHistory.add(encodedCacheKey);
                            }
                            if (this.mProducerContext.getImagePipelineConfig().getExperiments().isDiskCacheProbingEnabled() && !this.mDiskCacheHistory.contains(encodedCacheKey)) {
                                boolean z = imageRequest.getCacheChoice() == ImageRequest.CacheChoice.SMALL;
                                DiskCachesStore diskCachesStore = (DiskCachesStore) this.mDiskCachesStoreSupplier.get();
                                if (z) {
                                    bufferedDiskCache = diskCachesStore.getSmallImageBufferedDiskCache();
                                } else {
                                    bufferedDiskCache = diskCachesStore.getMainBufferedDiskCache();
                                }
                                bufferedDiskCache.addKeyForAsyncProbing(encodedCacheKey);
                                this.mDiskCacheHistory.add(encodedCacheKey);
                            }
                        }
                        getConsumer().onNewResult(closeableReference, i);
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
    }

    /* access modifiers changed from: protected */
    public String getProducerName() {
        return "BitmapProbeProducer";
    }
}
