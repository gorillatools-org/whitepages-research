package com.google.firebase.concurrent;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

final class PausableExecutorServiceImpl implements PausableExecutorService {
    private final ExecutorService delegateService;
    private final PausableExecutor pausableDelegate;

    PausableExecutorServiceImpl(boolean z, ExecutorService executorService) {
        this.delegateService = executorService;
        this.pausableDelegate = new PausableExecutorImpl(z, executorService);
    }

    public void execute(Runnable runnable) {
        this.pausableDelegate.execute(runnable);
    }

    public void pause() {
        this.pausableDelegate.pause();
    }

    public void resume() {
        this.pausableDelegate.resume();
    }

    public boolean isPaused() {
        return this.pausableDelegate.isPaused();
    }

    public void shutdown() {
        throw new UnsupportedOperationException("Shutting down is not allowed.");
    }

    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException("Shutting down is not allowed.");
    }

    public boolean isShutdown() {
        return this.delegateService.isShutdown();
    }

    public boolean isTerminated() {
        return this.delegateService.isTerminated();
    }

    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.delegateService.awaitTermination(j, timeUnit);
    }

    public <T> Future<T> submit(Callable<T> callable) {
        FutureTask futureTask = new FutureTask(callable);
        execute(futureTask);
        return futureTask;
    }

    public <T> Future<T> submit(Runnable runnable, T t) {
        return submit(new PausableExecutorServiceImpl$$ExternalSyntheticLambda1(runnable, t));
    }

    public Future<?> submit(Runnable runnable) {
        return submit(new PausableExecutorServiceImpl$$ExternalSyntheticLambda0(runnable));
    }

    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
        return this.delegateService.invokeAll(collection);
    }

    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException {
        return this.delegateService.invokeAll(collection, j, timeUnit);
    }

    public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws ExecutionException, InterruptedException {
        return this.delegateService.invokeAny(collection);
    }

    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        return this.delegateService.invokeAny(collection, j, timeUnit);
    }
}
