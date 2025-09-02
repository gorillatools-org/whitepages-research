package com.facebook.react.interfaces;

import java.util.concurrent.TimeUnit;

public interface TaskInterface<TResult> {
    Exception getError();

    TResult getResult();

    boolean isCancelled();

    boolean isCompleted();

    boolean isFaulted();

    void waitForCompletion() throws InterruptedException;

    boolean waitForCompletion(long j, TimeUnit timeUnit) throws InterruptedException;
}
