package com.facebook.imagepipeline.producers;

public interface ThreadHandoffProducerQueue {
    void addToQueueOrExecute(Runnable runnable);

    void remove(Runnable runnable);
}
