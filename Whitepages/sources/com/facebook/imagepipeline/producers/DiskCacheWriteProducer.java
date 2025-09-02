package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Supplier;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.core.DiskCachesStore;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.DiskCacheDecision;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.Map;

public class DiskCacheWriteProducer implements Producer {
    private final CacheKeyFactory mCacheKeyFactory;
    private final Supplier mDiskCachesStoreSupplier;
    private final Producer mInputProducer;

    public DiskCacheWriteProducer(Supplier supplier, CacheKeyFactory cacheKeyFactory, Producer producer) {
        this.mDiskCachesStoreSupplier = supplier;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mInputProducer = producer;
    }

    public void produceResults(Consumer consumer, ProducerContext producerContext) {
        maybeStartInputProducer(consumer, producerContext);
    }

    private void maybeStartInputProducer(Consumer consumer, ProducerContext producerContext) {
        if (producerContext.getLowestPermittedRequestLevel().getValue() >= ImageRequest.RequestLevel.DISK_CACHE.getValue()) {
            producerContext.putOriginExtra("disk", "nil-result_write");
            consumer.onNewResult((Object) null, 1);
            return;
        }
        if (producerContext.getImageRequest().isCacheEnabled(32)) {
            consumer = new DiskCacheWriteConsumer(consumer, producerContext, this.mDiskCachesStoreSupplier, this.mCacheKeyFactory);
        }
        this.mInputProducer.produceResults(consumer, producerContext);
    }

    private static class DiskCacheWriteConsumer extends DelegatingConsumer {
        private final CacheKeyFactory mCacheKeyFactory;
        private final Supplier mDiskCachesStoreSupplier;
        private final ProducerContext mProducerContext;

        private DiskCacheWriteConsumer(Consumer consumer, ProducerContext producerContext, Supplier supplier, CacheKeyFactory cacheKeyFactory) {
            super(consumer);
            this.mProducerContext = producerContext;
            this.mDiskCachesStoreSupplier = supplier;
            this.mCacheKeyFactory = cacheKeyFactory;
        }

        public void onNewResultImpl(EncodedImage encodedImage, int i) {
            this.mProducerContext.getProducerListener().onProducerStart(this.mProducerContext, "DiskCacheWriteProducer");
            if (BaseConsumer.isNotLast(i) || encodedImage == null || BaseConsumer.statusHasAnyFlag(i, 10) || encodedImage.getImageFormat() == ImageFormat.UNKNOWN) {
                this.mProducerContext.getProducerListener().onProducerFinishWithSuccess(this.mProducerContext, "DiskCacheWriteProducer", (Map) null);
                getConsumer().onNewResult(encodedImage, i);
                return;
            }
            ImageRequest imageRequest = this.mProducerContext.getImageRequest();
            CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, this.mProducerContext.getCallerContext());
            DiskCachesStore diskCachesStore = (DiskCachesStore) this.mDiskCachesStoreSupplier.get();
            BufferedDiskCache chooseDiskCacheForRequest = DiskCacheDecision.chooseDiskCacheForRequest(imageRequest, diskCachesStore.getSmallImageBufferedDiskCache(), diskCachesStore.getMainBufferedDiskCache(), diskCachesStore.getDynamicBufferedDiskCaches());
            if (chooseDiskCacheForRequest == null) {
                ProducerListener2 producerListener = this.mProducerContext.getProducerListener();
                ProducerContext producerContext = this.mProducerContext;
                producerListener.onProducerFinishWithFailure(producerContext, "DiskCacheWriteProducer", new DiskCacheDecision.DiskCacheDecisionNoDiskCacheChosenException("Got no disk cache for CacheChoice: " + Integer.valueOf(imageRequest.getCacheChoice().ordinal()).toString()), (Map) null);
                getConsumer().onNewResult(encodedImage, i);
                return;
            }
            chooseDiskCacheForRequest.put(encodedCacheKey, encodedImage);
            this.mProducerContext.getProducerListener().onProducerFinishWithSuccess(this.mProducerContext, "DiskCacheWriteProducer", (Map) null);
            getConsumer().onNewResult(encodedImage, i);
        }
    }
}
