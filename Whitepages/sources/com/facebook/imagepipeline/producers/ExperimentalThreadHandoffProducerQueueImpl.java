package com.facebook.imagepipeline.producers;

import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;

public final class ExperimentalThreadHandoffProducerQueueImpl implements ThreadHandoffProducerQueue {
    private final Executor executor;

    public void remove(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
    }

    public ExperimentalThreadHandoffProducerQueueImpl(Executor executor2) {
        if (executor2 != null) {
            this.executor = executor2;
            return;
        }
        throw new IllegalStateException("Required value was null.");
    }

    public void addToQueueOrExecute(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        this.executor.execute(runnable);
    }
}
