package com.google.firebase.crashlytics.internal.concurrency;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CrashlyticsWorker implements Executor {
    private final ExecutorService executor;
    private Task<?> tail = Tasks.forResult(null);
    private final Object tailLock = new Object();

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$await$6() {
    }

    CrashlyticsWorker(ExecutorService executorService) {
        this.executor = executorService;
    }

    public ExecutorService getExecutor() {
        return this.executor;
    }

    public <T> Task<T> submit(Callable<T> callable) {
        Task<TContinuationResult> continueWithTask;
        synchronized (this.tailLock) {
            continueWithTask = this.tail.continueWithTask(this.executor, new CrashlyticsWorker$$ExternalSyntheticLambda1(callable));
            this.tail = continueWithTask;
        }
        return continueWithTask;
    }

    public Task<Void> submit(Runnable runnable) {
        Task<TContinuationResult> continueWithTask;
        synchronized (this.tailLock) {
            continueWithTask = this.tail.continueWithTask(this.executor, new CrashlyticsWorker$$ExternalSyntheticLambda0(runnable));
            this.tail = continueWithTask;
        }
        return continueWithTask;
    }

    public <T> Task<T> submitTask(Callable<Task<T>> callable) {
        Task<TContinuationResult> continueWithTask;
        synchronized (this.tailLock) {
            continueWithTask = this.tail.continueWithTask(this.executor, new CrashlyticsWorker$$ExternalSyntheticLambda3(callable));
            this.tail = continueWithTask;
        }
        return continueWithTask;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Task lambda$submitTask$2(Callable callable, Task task) throws Exception {
        return (Task) callable.call();
    }

    public <T, R> Task<R> submitTask(Callable<Task<T>> callable, Continuation<T, Task<R>> continuation) {
        Task<TContinuationResult> continueWithTask;
        synchronized (this.tailLock) {
            continueWithTask = this.tail.continueWithTask(this.executor, new CrashlyticsWorker$$ExternalSyntheticLambda4(callable)).continueWithTask(this.executor, continuation);
            this.tail = continueWithTask;
        }
        return continueWithTask;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Task lambda$submitTask$3(Callable callable, Task task) throws Exception {
        return (Task) callable.call();
    }

    public <T, R> Task<R> submitTaskOnSuccess(Callable<Task<T>> callable, SuccessContinuation<T, R> successContinuation) {
        Task<TContinuationResult> continueWithTask;
        synchronized (this.tailLock) {
            continueWithTask = this.tail.continueWithTask(this.executor, new CrashlyticsWorker$$ExternalSyntheticLambda5(callable)).continueWithTask(this.executor, new CrashlyticsWorker$$ExternalSyntheticLambda6(successContinuation));
            this.tail = continueWithTask;
        }
        return continueWithTask;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Task lambda$submitTaskOnSuccess$4(Callable callable, Task task) throws Exception {
        return (Task) callable.call();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Task lambda$submitTaskOnSuccess$5(SuccessContinuation successContinuation, Task task) throws Exception {
        if (task.isSuccessful()) {
            return successContinuation.then(task.getResult());
        }
        if (task.getException() != null) {
            return Tasks.forException(task.getException());
        }
        return Tasks.forCanceled();
    }

    public void execute(Runnable runnable) {
        this.executor.execute(runnable);
    }

    public void await() throws ExecutionException, InterruptedException, TimeoutException {
        Tasks.await(submit((Runnable) new CrashlyticsWorker$$ExternalSyntheticLambda2()), 30, TimeUnit.SECONDS);
        Thread.sleep(1);
    }
}
