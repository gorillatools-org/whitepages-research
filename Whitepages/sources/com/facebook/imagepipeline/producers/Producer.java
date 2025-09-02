package com.facebook.imagepipeline.producers;

public interface Producer {
    void produceResults(Consumer consumer, ProducerContext producerContext);
}
