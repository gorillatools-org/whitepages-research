package com.google.firebase.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

final class PausableExecutorImpl implements PausableExecutor {
    private final Executor delegate;
    private volatile boolean paused;
    final LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

    PausableExecutorImpl(boolean z, Executor executor) {
        this.paused = z;
        this.delegate = executor;
    }

    public void execute(Runnable runnable) {
        this.queue.offer(runnable);
        maybeEnqueueNext();
    }

    private void maybeEnqueueNext() {
        if (!this.paused) {
            Runnable poll = this.queue.poll();
            while (poll != null) {
                this.delegate.execute(poll);
                poll = !this.paused ? this.queue.poll() : null;
            }
        }
    }

    public void pause() {
        this.paused = true;
    }

    public void resume() {
        this.paused = false;
        maybeEnqueueNext();
    }

    public boolean isPaused() {
        return this.paused;
    }
}
