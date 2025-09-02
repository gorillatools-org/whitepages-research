package com.facebook.react.common.futures;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import kotlin.jvm.internal.Intrinsics;

public final class SimpleSettableFuture<T> implements Future<T> {
    private Exception exception;
    private final CountDownLatch readyLatch = new CountDownLatch(1);
    private T result;

    public boolean isCancelled() {
        return false;
    }

    public final void set(T t) {
        checkNotSet();
        this.result = t;
        this.readyLatch.countDown();
    }

    public final void setException(Exception exc) {
        Intrinsics.checkNotNullParameter(exc, "exception");
        checkNotSet();
        this.exception = exc;
        this.readyLatch.countDown();
    }

    public boolean cancel(boolean z) {
        throw new UnsupportedOperationException();
    }

    public boolean isDone() {
        return this.readyLatch.getCount() == 0;
    }

    public T get() throws InterruptedException, ExecutionException {
        this.readyLatch.await();
        if (this.exception == null) {
            return this.result;
        }
        throw new ExecutionException(this.exception);
    }

    public T get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        Intrinsics.checkNotNullParameter(timeUnit, "unit");
        if (!this.readyLatch.await(j, timeUnit)) {
            throw new TimeoutException("Timed out waiting for result");
        } else if (this.exception == null) {
            return this.result;
        } else {
            throw new ExecutionException(this.exception);
        }
    }

    public final T getOrThrow() {
        try {
            return get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e2) {
            throw new RuntimeException(e2);
        }
    }

    public final T getOrThrow(long j, TimeUnit timeUnit) {
        Intrinsics.checkNotNullParameter(timeUnit, "unit");
        try {
            return get(j, timeUnit);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e2) {
            throw new RuntimeException(e2);
        } catch (TimeoutException e3) {
            throw new RuntimeException(e3);
        }
    }

    private final void checkNotSet() {
        if (this.readyLatch.getCount() == 0) {
            throw new RuntimeException("Result has already been set!");
        }
    }
}
