package com.facebook.imagepipeline.producers;

public class SwallowResultProducer implements Producer {
    private final Producer mInputProducer;

    public SwallowResultProducer(Producer producer) {
        this.mInputProducer = producer;
    }

    public void produceResults(Consumer consumer, ProducerContext producerContext) {
        this.mInputProducer.produceResults(new DelegatingConsumer(consumer) {
            /* access modifiers changed from: protected */
            public void onNewResultImpl(Object obj, int i) {
                if (BaseConsumer.isLast(i)) {
                    getConsumer().onNewResult((Object) null, i);
                }
            }
        }, producerContext);
    }
}
