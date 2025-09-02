package com.facebook.imagepipeline.producers;

import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import kotlin.jvm.internal.Intrinsics;

public final class RemoveImageTransformMetaDataProducer implements Producer {
    private final Producer inputProducer;

    public RemoveImageTransformMetaDataProducer(Producer producer) {
        Intrinsics.checkNotNullParameter(producer, "inputProducer");
        this.inputProducer = producer;
    }

    public void produceResults(Consumer consumer, ProducerContext producerContext) {
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        Intrinsics.checkNotNullParameter(producerContext, "context");
        this.inputProducer.produceResults(new RemoveImageTransformMetaDataConsumer(this, consumer), producerContext);
    }

    private final class RemoveImageTransformMetaDataConsumer extends DelegatingConsumer {
        final /* synthetic */ RemoveImageTransformMetaDataProducer this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public RemoveImageTransformMetaDataConsumer(RemoveImageTransformMetaDataProducer removeImageTransformMetaDataProducer, Consumer consumer) {
            super(consumer);
            Intrinsics.checkNotNullParameter(consumer, "consumer");
            this.this$0 = removeImageTransformMetaDataProducer;
        }

        /* access modifiers changed from: protected */
        public void onNewResultImpl(EncodedImage encodedImage, int i) {
            CloseableReference closeableReference = null;
            try {
                if (EncodedImage.isValid(encodedImage) && encodedImage != null) {
                    closeableReference = encodedImage.getByteBufferRef();
                }
                getConsumer().onNewResult(closeableReference, i);
                CloseableReference.closeSafely(closeableReference);
            } catch (Throwable th) {
                CloseableReference.closeSafely((CloseableReference) null);
                throw th;
            }
        }
    }
}
