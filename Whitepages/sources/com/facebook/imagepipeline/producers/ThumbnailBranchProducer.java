package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;

public class ThumbnailBranchProducer implements Producer {
    private final ThumbnailProducer[] mThumbnailProducers;

    public ThumbnailBranchProducer(ThumbnailProducer... thumbnailProducerArr) {
        ThumbnailProducer[] thumbnailProducerArr2 = (ThumbnailProducer[]) Preconditions.checkNotNull(thumbnailProducerArr);
        this.mThumbnailProducers = thumbnailProducerArr2;
        Preconditions.checkElementIndex(0, thumbnailProducerArr2.length);
    }

    public void produceResults(Consumer consumer, ProducerContext producerContext) {
        if (producerContext.getImageRequest().getResizeOptions() == null) {
            consumer.onNewResult((Object) null, 1);
        } else if (!produceResultsFromThumbnailProducer(0, consumer, producerContext)) {
            consumer.onNewResult((Object) null, 1);
        }
    }

    private class ThumbnailConsumer extends DelegatingConsumer {
        private final ProducerContext mProducerContext;
        private final int mProducerIndex;
        private final ResizeOptions mResizeOptions;

        public ThumbnailConsumer(Consumer consumer, ProducerContext producerContext, int i) {
            super(consumer);
            this.mProducerContext = producerContext;
            this.mProducerIndex = i;
            this.mResizeOptions = producerContext.getImageRequest().getResizeOptions();
        }

        /* access modifiers changed from: protected */
        public void onNewResultImpl(EncodedImage encodedImage, int i) {
            if (encodedImage != null && (BaseConsumer.isNotLast(i) || ThumbnailSizeChecker.isImageBigEnough(encodedImage, this.mResizeOptions))) {
                getConsumer().onNewResult(encodedImage, i);
            } else if (BaseConsumer.isLast(i)) {
                EncodedImage.closeSafely(encodedImage);
                if (!ThumbnailBranchProducer.this.produceResultsFromThumbnailProducer(this.mProducerIndex + 1, getConsumer(), this.mProducerContext)) {
                    getConsumer().onNewResult((Object) null, 1);
                }
            }
        }

        /* access modifiers changed from: protected */
        public void onFailureImpl(Throwable th) {
            if (!ThumbnailBranchProducer.this.produceResultsFromThumbnailProducer(this.mProducerIndex + 1, getConsumer(), this.mProducerContext)) {
                getConsumer().onFailure(th);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean produceResultsFromThumbnailProducer(int i, Consumer consumer, ProducerContext producerContext) {
        int findFirstProducerForSize = findFirstProducerForSize(i, producerContext.getImageRequest().getResizeOptions());
        if (findFirstProducerForSize == -1) {
            return false;
        }
        this.mThumbnailProducers[findFirstProducerForSize].produceResults(new ThumbnailConsumer(consumer, producerContext, findFirstProducerForSize), producerContext);
        return true;
    }

    private int findFirstProducerForSize(int i, ResizeOptions resizeOptions) {
        while (true) {
            ThumbnailProducer[] thumbnailProducerArr = this.mThumbnailProducers;
            if (i >= thumbnailProducerArr.length) {
                return -1;
            }
            if (thumbnailProducerArr[i].canProvideImageForSize(resizeOptions)) {
                return i;
            }
            i++;
        }
    }
}
