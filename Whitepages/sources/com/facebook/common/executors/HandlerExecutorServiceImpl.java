package com.facebook.common.executors;

import android.os.Handler;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public abstract class HandlerExecutorServiceImpl extends AbstractExecutorService implements ScheduledExecutorService {
    private final Handler mHandler;

    public boolean isShutdown() {
        return false;
    }

    public boolean isTerminated() {
        return false;
    }

    public HandlerExecutorServiceImpl(Handler handler) {
        this.mHandler = handler;
    }

    public void shutdown() {
        throw new UnsupportedOperationException();
    }

    public List shutdownNow() {
        throw new UnsupportedOperationException();
    }

    public boolean awaitTermination(long j, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public void execute(Runnable runnable) {
        this.mHandler.post(runnable);
    }

    /* access modifiers changed from: protected */
    public ScheduledFutureImpl newTaskFor(Runnable runnable, Object obj) {
        return new ScheduledFutureImpl(this.mHandler, runnable, obj);
    }

    /* access modifiers changed from: protected */
    public ScheduledFutureImpl newTaskFor(Callable callable) {
        return new ScheduledFutureImpl(this.mHandler, callable);
    }

    public ScheduledFuture submit(Runnable runnable) {
        return submit(runnable, (Object) null);
    }

    public ScheduledFuture submit(Runnable runnable, Object obj) {
        runnable.getClass();
        ScheduledFutureImpl newTaskFor = newTaskFor(runnable, obj);
        execute(newTaskFor);
        return newTaskFor;
    }

    public ScheduledFuture submit(Callable callable) {
        callable.getClass();
        ScheduledFutureImpl newTaskFor = newTaskFor(callable);
        execute(newTaskFor);
        return newTaskFor;
    }

    public ScheduledFuture schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        ScheduledFutureImpl newTaskFor = newTaskFor(runnable, (Object) null);
        this.mHandler.postDelayed(newTaskFor, timeUnit.toMillis(j));
        return newTaskFor;
    }

    public ScheduledFuture schedule(Callable callable, long j, TimeUnit timeUnit) {
        ScheduledFutureImpl newTaskFor = newTaskFor(callable);
        this.mHandler.postDelayed(newTaskFor, timeUnit.toMillis(j));
        return newTaskFor;
    }

    public ScheduledFuture scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public boolean isHandlerThread() {
        return Thread.currentThread() == this.mHandler.getLooper().getThread();
    }
}
