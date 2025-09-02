package com.facebook.imagepipeline.producers;

import android.graphics.Bitmap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;

public class BitmapPrepareProducer implements Producer {
    private final Producer mInputProducer;
    private final int mMaxBitmapSizeBytes;
    private final int mMinBitmapSizeBytes;
    private final boolean mPreparePrefetch;

    public BitmapPrepareProducer(Producer producer, int i, int i2, boolean z) {
        Preconditions.checkArgument(Boolean.valueOf(i <= i2));
        this.mInputProducer = (Producer) Preconditions.checkNotNull(producer);
        this.mMinBitmapSizeBytes = i;
        this.mMaxBitmapSizeBytes = i2;
        this.mPreparePrefetch = z;
    }

    public void produceResults(Consumer consumer, ProducerContext producerContext) {
        if (!producerContext.isPrefetch() || this.mPreparePrefetch) {
            this.mInputProducer.produceResults(new BitmapPrepareConsumer(consumer, this.mMinBitmapSizeBytes, this.mMaxBitmapSizeBytes), producerContext);
        } else {
            this.mInputProducer.produceResults(consumer, producerContext);
        }
    }

    private static class BitmapPrepareConsumer extends DelegatingConsumer {
        private final int mMaxBitmapSizeBytes;
        private final int mMinBitmapSizeBytes;

        BitmapPrepareConsumer(Consumer consumer, int i, int i2) {
            super(consumer);
            this.mMinBitmapSizeBytes = i;
            this.mMaxBitmapSizeBytes = i2;
        }

        /* access modifiers changed from: protected */
        public void onNewResultImpl(CloseableReference closeableReference, int i) {
            internalPrepareBitmap(closeableReference);
            getConsumer().onNewResult(closeableReference, i);
        }

        private void internalPrepareBitmap(CloseableReference closeableReference) {
            CloseableImage closeableImage;
            Bitmap underlyingBitmap;
            int rowBytes;
            if (closeableReference != null && closeableReference.isValid() && (closeableImage = (CloseableImage) closeableReference.get()) != null && !closeableImage.isClosed() && (closeableImage instanceof CloseableStaticBitmap) && (underlyingBitmap = ((CloseableStaticBitmap) closeableImage).getUnderlyingBitmap()) != null && (rowBytes = underlyingBitmap.getRowBytes() * underlyingBitmap.getHeight()) >= this.mMinBitmapSizeBytes && rowBytes <= this.mMaxBitmapSizeBytes) {
                underlyingBitmap.prepareToDraw();
            }
        }
    }
}
