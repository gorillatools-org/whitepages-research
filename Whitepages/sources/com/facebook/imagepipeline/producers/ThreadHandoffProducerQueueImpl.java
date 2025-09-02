package com.facebook.imagepipeline.producers;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;

public final class ThreadHandoffProducerQueueImpl implements ThreadHandoffProducerQueue {
    private final Executor executor;
    private boolean queueing;
    private final Deque runnableList = new ArrayDeque();

    public ThreadHandoffProducerQueueImpl(Executor executor2) {
        Intrinsics.checkNotNullParameter(executor2, "executor");
        this.executor = executor2;
    }

    public synchronized void addToQueueOrExecute(Runnable runnable) {
        try {
            Intrinsics.checkNotNullParameter(runnable, "runnable");
            if (this.queueing) {
                this.runnableList.add(runnable);
            } else {
                this.executor.execute(runnable);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public synchronized void remove(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        this.runnableList.remove(runnable);
    }
}
