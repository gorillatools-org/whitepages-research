package com.facebook.common.executors;

import android.os.Handler;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledFutureImpl implements RunnableFuture, ScheduledFuture {
    private final Handler mHandler;
    private final FutureTask mListenableFuture;

    public ScheduledFutureImpl(Handler handler, Callable callable) {
        this.mHandler = handler;
        this.mListenableFuture = new FutureTask(callable);
    }

    public ScheduledFutureImpl(Handler handler, Runnable runnable, Object obj) {
        this.mHandler = handler;
        this.mListenableFuture = new FutureTask(runnable, obj);
    }

    public long getDelay(TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public int compareTo(Delayed delayed) {
        throw new UnsupportedOperationException();
    }

    public void run() {
        this.mListenableFuture.run();
    }

    public boolean cancel(boolean z) {
        return this.mListenableFuture.cancel(z);
    }

    public boolean isCancelled() {
        return this.mListenableFuture.isCancelled();
    }

    public boolean isDone() {
        return this.mListenableFuture.isDone();
    }

    public Object get() {
        return this.mListenableFuture.get();
    }

    public Object get(long j, TimeUnit timeUnit) {
        return this.mListenableFuture.get(j, timeUnit);
    }
}
