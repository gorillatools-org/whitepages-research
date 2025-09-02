package com.facebook.imagepipeline.producers;

public final /* synthetic */ class DelayProducer$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ DelayProducer f$0;
    public final /* synthetic */ Consumer f$1;
    public final /* synthetic */ ProducerContext f$2;

    public /* synthetic */ DelayProducer$$ExternalSyntheticLambda0(DelayProducer delayProducer, Consumer consumer, ProducerContext producerContext) {
        this.f$0 = delayProducer;
        this.f$1 = consumer;
        this.f$2 = producerContext;
    }

    public final void run() {
        DelayProducer.produceResults$lambda$0(this.f$0, this.f$1, this.f$2);
    }
}
