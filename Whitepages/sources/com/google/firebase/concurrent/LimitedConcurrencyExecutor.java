package com.google.firebase.concurrent;

import com.google.firebase.components.Preconditions;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

class LimitedConcurrencyExecutor implements Executor {
    private final Executor delegate;
    private final LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
    private final Semaphore semaphore;

    LimitedConcurrencyExecutor(Executor executor, int i) {
        Preconditions.checkArgument(i > 0, "concurrency must be positive.");
        this.delegate = executor;
        this.semaphore = new Semaphore(i, true);
    }

    public void execute(Runnable runnable) {
        this.queue.offer(runnable);
        maybeEnqueueNext();
    }

    private void maybeEnqueueNext() {
        while (this.semaphore.tryAcquire()) {
            Runnable poll = this.queue.poll();
            if (poll != null) {
                this.delegate.execute(decorate(poll));
            } else {
                this.semaphore.release();
                return;
            }
        }
    }

    private Runnable decorate(Runnable runnable) {
        return new LimitedConcurrencyExecutor$$ExternalSyntheticLambda0(this, runnable);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$decorate$0(Runnable runnable) {
        try {
            runnable.run();
        } finally {
            this.semaphore.release();
            maybeEnqueueNext();
        }
    }
}
