package com.google.common.util.concurrent;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

public abstract class Futures extends GwtFuturesCatchingSpecialization {
    public static void addCallback(ListenableFuture listenableFuture, FutureCallback futureCallback, Executor executor) {
        Preconditions.checkNotNull(futureCallback);
        listenableFuture.addListener(new CallbackListener(listenableFuture, futureCallback), executor);
    }

    private static final class CallbackListener implements Runnable {
        final FutureCallback callback;
        final Future future;

        CallbackListener(Future future2, FutureCallback futureCallback) {
            this.future = future2;
            this.callback = futureCallback;
        }

        public void run() {
            try {
                this.callback.onSuccess(Futures.getDone(this.future));
            } catch (ExecutionException e) {
                this.callback.onFailure(e.getCause());
            } catch (Error | RuntimeException e2) {
                this.callback.onFailure(e2);
            }
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).addValue(this.callback).toString();
        }
    }

    public static Object getDone(Future future) {
        Preconditions.checkState(future.isDone(), "Future was expected to be done: %s", future);
        return Uninterruptibles.getUninterruptibly(future);
    }
}
