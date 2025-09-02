package com.facebook.imagepipeline.producers;

import java.util.Map;

public final class ThreadHandoffProducer$produceResults$1$statefulRunnable$1 extends StatefulProducerRunnable {
    final /* synthetic */ Consumer $consumer;
    final /* synthetic */ ProducerContext $context;
    final /* synthetic */ ProducerListener2 $producerListener;
    final /* synthetic */ ThreadHandoffProducer this$0;

    /* access modifiers changed from: protected */
    public void disposeResult(Object obj) {
    }

    /* access modifiers changed from: protected */
    public Object getResult() {
        return null;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ThreadHandoffProducer$produceResults$1$statefulRunnable$1(Consumer consumer, ProducerListener2 producerListener2, ProducerContext producerContext, ThreadHandoffProducer threadHandoffProducer) {
        super(consumer, producerListener2, producerContext, "BackgroundThreadHandoffProducer");
        this.$consumer = consumer;
        this.$producerListener = producerListener2;
        this.$context = producerContext;
        this.this$0 = threadHandoffProducer;
    }

    /* access modifiers changed from: protected */
    public void onSuccess(Object obj) {
        this.$producerListener.onProducerFinishWithSuccess(this.$context, "BackgroundThreadHandoffProducer", (Map) null);
        this.this$0.getInputProducer().produceResults(this.$consumer, this.$context);
    }
}
