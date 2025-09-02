package com.facebook.imagepipeline.producers;

public final class ThreadHandoffProducer$produceResults$1$1 extends BaseProducerContextCallbacks {
    final /* synthetic */ StatefulProducerRunnable $statefulRunnable;
    final /* synthetic */ ThreadHandoffProducer this$0;

    ThreadHandoffProducer$produceResults$1$1(StatefulProducerRunnable statefulProducerRunnable, ThreadHandoffProducer threadHandoffProducer) {
        this.$statefulRunnable = statefulProducerRunnable;
        this.this$0 = threadHandoffProducer;
    }

    public void onCancellationRequested() {
        this.$statefulRunnable.cancel();
        this.this$0.getThreadHandoffProducerQueue().remove(this.$statefulRunnable);
    }
}
