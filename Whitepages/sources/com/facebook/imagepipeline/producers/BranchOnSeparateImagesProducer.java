package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;

public class BranchOnSeparateImagesProducer implements Producer {
    private final Producer mInputProducer1;
    /* access modifiers changed from: private */
    public final Producer mInputProducer2;

    public BranchOnSeparateImagesProducer(Producer producer, Producer producer2) {
        this.mInputProducer1 = producer;
        this.mInputProducer2 = producer2;
    }

    public void produceResults(Consumer consumer, ProducerContext producerContext) {
        this.mInputProducer1.produceResults(new OnFirstImageConsumer(consumer, producerContext), producerContext);
    }

    private class OnFirstImageConsumer extends DelegatingConsumer {
        private ProducerContext mProducerContext;

        private OnFirstImageConsumer(Consumer consumer, ProducerContext producerContext) {
            super(consumer);
            this.mProducerContext = producerContext;
        }

        /* access modifiers changed from: protected */
        public void onNewResultImpl(EncodedImage encodedImage, int i) {
            ImageRequest imageRequest = this.mProducerContext.getImageRequest();
            boolean isLast = BaseConsumer.isLast(i);
            boolean isImageBigEnough = ThumbnailSizeChecker.isImageBigEnough(encodedImage, imageRequest.getResizeOptions());
            if (encodedImage != null && (isImageBigEnough || imageRequest.getLocalThumbnailPreviewsEnabled())) {
                if (!isLast || !isImageBigEnough) {
                    getConsumer().onNewResult(encodedImage, BaseConsumer.turnOffStatusFlag(i, 1));
                } else {
                    getConsumer().onNewResult(encodedImage, i);
                }
            }
            if (isLast && !isImageBigEnough && !imageRequest.getLoadThumbnailOnlyForAndroidSdkAboveQ()) {
                EncodedImage.closeSafely(encodedImage);
                BranchOnSeparateImagesProducer.this.mInputProducer2.produceResults(getConsumer(), this.mProducerContext);
            }
        }

        /* access modifiers changed from: protected */
        public void onFailureImpl(Throwable th) {
            BranchOnSeparateImagesProducer.this.mInputProducer2.produceResults(getConsumer(), this.mProducerContext);
        }
    }
}
