package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.image.EncodedImage;

public class AddImageTransformMetaDataProducer implements Producer {
    private final Producer mInputProducer;

    public AddImageTransformMetaDataProducer(Producer producer) {
        this.mInputProducer = producer;
    }

    public void produceResults(Consumer consumer, ProducerContext producerContext) {
        this.mInputProducer.produceResults(new AddImageTransformMetaDataConsumer(consumer), producerContext);
    }

    private static class AddImageTransformMetaDataConsumer extends DelegatingConsumer {
        private AddImageTransformMetaDataConsumer(Consumer consumer) {
            super(consumer);
        }

        /* access modifiers changed from: protected */
        public void onNewResultImpl(EncodedImage encodedImage, int i) {
            if (encodedImage == null) {
                getConsumer().onNewResult((Object) null, i);
                return;
            }
            if (!EncodedImage.isMetaDataAvailable(encodedImage)) {
                encodedImage.parseMetaData();
            }
            getConsumer().onNewResult(encodedImage, i);
        }
    }
}
