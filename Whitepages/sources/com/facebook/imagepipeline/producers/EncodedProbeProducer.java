package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Supplier;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.cache.BoundedLinkedHashSet;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.core.DiskCachesStore;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.Map;

public class EncodedProbeProducer implements Producer {
    private final CacheKeyFactory mCacheKeyFactory;
    private final BoundedLinkedHashSet mDiskCacheHistory;
    private final Supplier mDiskCachesStoreSupplier;
    private final BoundedLinkedHashSet mEncodedMemoryCacheHistory;
    private final Producer mInputProducer;

    public EncodedProbeProducer(Supplier supplier, CacheKeyFactory cacheKeyFactory, BoundedLinkedHashSet boundedLinkedHashSet, BoundedLinkedHashSet boundedLinkedHashSet2, Producer producer) {
        this.mDiskCachesStoreSupplier = supplier;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mEncodedMemoryCacheHistory = boundedLinkedHashSet;
        this.mDiskCacheHistory = boundedLinkedHashSet2;
        this.mInputProducer = producer;
    }

    public void produceResults(Consumer consumer, ProducerContext producerContext) {
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("EncodedProbeProducer#produceResults");
            }
            ProducerListener2 producerListener = producerContext.getProducerListener();
            producerListener.onProducerStart(producerContext, getProducerName());
            ProbeConsumer probeConsumer = new ProbeConsumer(consumer, producerContext, this.mDiskCachesStoreSupplier, this.mCacheKeyFactory, this.mEncodedMemoryCacheHistory, this.mDiskCacheHistory);
            producerListener.onProducerFinishWithSuccess(producerContext, "EncodedProbeProducer", (Map) null);
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
        private final Supplier mDefaultBufferedDiskCache;
        private final BoundedLinkedHashSet mDiskCacheHistory;
        private final BoundedLinkedHashSet mEncodedMemoryCacheHistory;
        private final ProducerContext mProducerContext;

        public ProbeConsumer(Consumer consumer, ProducerContext producerContext, Supplier supplier, CacheKeyFactory cacheKeyFactory, BoundedLinkedHashSet boundedLinkedHashSet, BoundedLinkedHashSet boundedLinkedHashSet2) {
            super(consumer);
            this.mProducerContext = producerContext;
            this.mDefaultBufferedDiskCache = supplier;
            this.mCacheKeyFactory = cacheKeyFactory;
            this.mEncodedMemoryCacheHistory = boundedLinkedHashSet;
            this.mDiskCacheHistory = boundedLinkedHashSet2;
        }

        public void onNewResultImpl(EncodedImage encodedImage, int i) {
            BufferedDiskCache bufferedDiskCache;
            try {
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.beginSection("EncodedProbeProducer#onNewResultImpl");
                }
                if (!BaseConsumer.isNotLast(i) && encodedImage != null && !BaseConsumer.statusHasAnyFlag(i, 10)) {
                    if (encodedImage.getImageFormat() != ImageFormat.UNKNOWN) {
                        ImageRequest imageRequest = this.mProducerContext.getImageRequest();
                        CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, this.mProducerContext.getCallerContext());
                        this.mEncodedMemoryCacheHistory.add(encodedCacheKey);
                        if ("memory_encoded".equals(this.mProducerContext.getExtra("origin"))) {
                            if (!this.mDiskCacheHistory.contains(encodedCacheKey)) {
                                boolean z = imageRequest.getCacheChoice() == ImageRequest.CacheChoice.SMALL;
                                DiskCachesStore diskCachesStore = (DiskCachesStore) this.mDefaultBufferedDiskCache.get();
                                if (z) {
                                    bufferedDiskCache = diskCachesStore.getSmallImageBufferedDiskCache();
                                } else {
                                    bufferedDiskCache = diskCachesStore.getMainBufferedDiskCache();
                                }
                                bufferedDiskCache.addKeyForAsyncProbing(encodedCacheKey);
                                this.mDiskCacheHistory.add(encodedCacheKey);
                            }
                        } else if ("disk".equals(this.mProducerContext.getExtra("origin"))) {
                            this.mDiskCacheHistory.add(encodedCacheKey);
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

    /* access modifiers changed from: protected */
    public String getProducerName() {
        return "EncodedProbeProducer";
    }
}
