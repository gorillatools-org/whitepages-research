package com.facebook.react.runtime.internal.bolts;

import com.facebook.react.interfaces.TaskInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class Task<TResult> implements TaskInterface<TResult> {
    public static final Executor IMMEDIATE_EXECUTOR = Executors.IMMEDIATE;
    private static Task<?> TASK_CANCELLED = new Task<>(true);
    private static Task<Boolean> TASK_FALSE = new Task<>(Boolean.FALSE);
    private static Task<?> TASK_NULL = new Task<>((Object) null);
    private static Task<Boolean> TASK_TRUE = new Task<>(Boolean.TRUE);
    public static final Executor UI_THREAD_EXECUTOR = Executors.UI_THREAD;
    private static volatile UnobservedExceptionHandler unobservedExceptionHandler;
    private boolean cancelled;
    private boolean complete;
    private List<Continuation<TResult, Void>> continuations = new ArrayList();
    private Exception error;
    private boolean errorHasBeenObserved;
    private final Object lock = new Object();
    private TResult result;
    private UnobservedErrorNotifier unobservedErrorNotifier;

    public interface UnobservedExceptionHandler {
        void unobservedException(Task<?> task, UnobservedTaskException unobservedTaskException);
    }

    public static UnobservedExceptionHandler getUnobservedExceptionHandler() {
        return unobservedExceptionHandler;
    }

    public static void setUnobservedExceptionHandler(UnobservedExceptionHandler unobservedExceptionHandler2) {
        unobservedExceptionHandler = unobservedExceptionHandler2;
    }

    Task() {
    }

    private Task(TResult tresult) {
        trySetResult(tresult);
    }

    private Task(boolean z) {
        if (z) {
            trySetCancelled();
        } else {
            trySetResult((Object) null);
        }
    }

    public static <TResult> TaskCompletionSource create() {
        new Task();
        return new TaskCompletionSource();
    }

    public boolean isCompleted() {
        boolean z;
        synchronized (this.lock) {
            z = this.complete;
        }
        return z;
    }

    public boolean isCancelled() {
        boolean z;
        synchronized (this.lock) {
            z = this.cancelled;
        }
        return z;
    }

    public boolean isFaulted() {
        boolean z;
        synchronized (this.lock) {
            z = getError() != null;
        }
        return z;
    }

    public TResult getResult() {
        TResult tresult;
        synchronized (this.lock) {
            tresult = this.result;
        }
        return tresult;
    }

    public Exception getError() {
        Exception exc;
        synchronized (this.lock) {
            try {
                if (this.error != null) {
                    this.errorHasBeenObserved = true;
                    UnobservedErrorNotifier unobservedErrorNotifier2 = this.unobservedErrorNotifier;
                    if (unobservedErrorNotifier2 != null) {
                        unobservedErrorNotifier2.setObserved();
                        this.unobservedErrorNotifier = null;
                    }
                }
                exc = this.error;
            } catch (Throwable th) {
                throw th;
            }
        }
        return exc;
    }

    public void waitForCompletion() throws InterruptedException {
        synchronized (this.lock) {
            try {
                if (!isCompleted()) {
                    this.lock.wait();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean waitForCompletion(long j, TimeUnit timeUnit) throws InterruptedException {
        boolean isCompleted;
        synchronized (this.lock) {
            try {
                if (!isCompleted()) {
                    this.lock.wait(timeUnit.toMillis(j));
                }
                isCompleted = isCompleted();
            } catch (Throwable th) {
                throw th;
            }
        }
        return isCompleted;
    }

    public static <TResult> Task<TResult> forResult(TResult tresult) {
        if (tresult == null) {
            return TASK_NULL;
        }
        if (tresult instanceof Boolean) {
            return ((Boolean) tresult).booleanValue() ? TASK_TRUE : TASK_FALSE;
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        taskCompletionSource.setResult(tresult);
        return taskCompletionSource.getTask();
    }

    public static <TResult> Task<TResult> forError(Exception exc) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        taskCompletionSource.setError(exc);
        return taskCompletionSource.getTask();
    }

    public static <TResult> Task<TResult> cancelled() {
        return TASK_CANCELLED;
    }

    public Task<Void> makeVoid() {
        return continueWithTask(new Continuation<TResult, Task<Void>>() {
            public Task<Void> then(Task<TResult> task) throws Exception {
                if (task.isCancelled()) {
                    return Task.cancelled();
                }
                if (task.isFaulted()) {
                    return Task.forError(task.getError());
                }
                return Task.forResult(null);
            }
        });
    }

    public static <TResult> Task<TResult> call(final Callable<TResult> callable, Executor executor) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        try {
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        TaskCompletionSource.this.setResult(callable.call());
                    } catch (CancellationException unused) {
                        TaskCompletionSource.this.setCancelled();
                    } catch (Exception e) {
                        TaskCompletionSource.this.setError(e);
                    }
                }
            });
        } catch (Exception e) {
            taskCompletionSource.setError(new ExecutorException(e));
        }
        return taskCompletionSource.getTask();
    }

    public static <TResult> Task<TResult> call(Callable<TResult> callable) {
        return call(callable, IMMEDIATE_EXECUTOR);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(final Continuation<TResult, TContinuationResult> continuation, final Executor executor) {
        boolean isCompleted;
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        synchronized (this.lock) {
            try {
                isCompleted = isCompleted();
                if (!isCompleted) {
                    this.continuations.add(new Continuation<TResult, Void>() {
                        public Void then(Task<TResult> task) {
                            Task.completeImmediately(taskCompletionSource, continuation, task, executor);
                            return null;
                        }
                    });
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (isCompleted) {
            completeImmediately(taskCompletionSource, continuation, this, executor);
        }
        return taskCompletionSource.getTask();
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<TResult, TContinuationResult> continuation) {
        return continueWith(continuation, IMMEDIATE_EXECUTOR);
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(final Continuation<TResult, Task<TContinuationResult>> continuation, final Executor executor) {
        boolean isCompleted;
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        synchronized (this.lock) {
            try {
                isCompleted = isCompleted();
                if (!isCompleted) {
                    this.continuations.add(new Continuation<TResult, Void>() {
                        public Void then(Task<TResult> task) {
                            Task.completeAfterTask(taskCompletionSource, continuation, task, executor);
                            return null;
                        }
                    });
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (isCompleted) {
            completeAfterTask(taskCompletionSource, continuation, this, executor);
        }
        return taskCompletionSource.getTask();
    }

    public <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<TResult, Task<TContinuationResult>> continuation) {
        return continueWithTask(continuation, IMMEDIATE_EXECUTOR);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccess(final Continuation<TResult, TContinuationResult> continuation, Executor executor) {
        return continueWithTask(new Continuation<TResult, Task<TContinuationResult>>() {
            public Task<TContinuationResult> then(Task<TResult> task) {
                if (task.isFaulted()) {
                    return Task.forError(task.getError());
                }
                if (task.isCancelled()) {
                    return Task.cancelled();
                }
                return task.continueWith(continuation);
            }
        }, executor);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccess(Continuation<TResult, TContinuationResult> continuation) {
        return onSuccess(continuation, IMMEDIATE_EXECUTOR);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(final Continuation<TResult, Task<TContinuationResult>> continuation, Executor executor) {
        return continueWithTask(new Continuation<TResult, Task<TContinuationResult>>() {
            public Task<TContinuationResult> then(Task<TResult> task) {
                if (task.isFaulted()) {
                    return Task.forError(task.getError());
                }
                if (task.isCancelled()) {
                    return Task.cancelled();
                }
                return task.continueWithTask(continuation);
            }
        }, executor);
    }

    public <TContinuationResult> Task<TContinuationResult> onSuccessTask(Continuation<TResult, Task<TContinuationResult>> continuation) {
        return onSuccessTask(continuation, IMMEDIATE_EXECUTOR);
    }

    /* access modifiers changed from: private */
    public static <TContinuationResult, TResult> void completeImmediately(final TaskCompletionSource<TContinuationResult> taskCompletionSource, final Continuation<TResult, TContinuationResult> continuation, final Task<TResult> task, Executor executor) {
        try {
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        taskCompletionSource.setResult(Continuation.this.then(task));
                    } catch (CancellationException unused) {
                        taskCompletionSource.setCancelled();
                    } catch (Exception e) {
                        taskCompletionSource.setError(e);
                    }
                }
            });
        } catch (Exception e) {
            taskCompletionSource.setError(new ExecutorException(e));
        }
    }

    /* access modifiers changed from: private */
    public static <TContinuationResult, TResult> void completeAfterTask(final TaskCompletionSource<TContinuationResult> taskCompletionSource, final Continuation<TResult, Task<TContinuationResult>> continuation, final Task<TResult> task, Executor executor) {
        try {
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        Task task = (Task) Continuation.this.then(task);
                        if (task == null) {
                            taskCompletionSource.setResult(null);
                        } else {
                            task.continueWith(new Continuation<TContinuationResult, Void>() {
                                public Void then(Task<TContinuationResult> task) {
                                    if (task.isCancelled()) {
                                        taskCompletionSource.setCancelled();
                                        return null;
                                    } else if (task.isFaulted()) {
                                        taskCompletionSource.setError(task.getError());
                                        return null;
                                    } else {
                                        taskCompletionSource.setResult(task.getResult());
                                        return null;
                                    }
                                }
                            });
                        }
                    } catch (CancellationException unused) {
                        taskCompletionSource.setCancelled();
                    } catch (Exception e) {
                        taskCompletionSource.setError(e);
                    }
                }
            });
        } catch (Exception e) {
            taskCompletionSource.setError(new ExecutorException(e));
        }
    }

    private void runContinuations() {
        synchronized (this.lock) {
            for (Continuation then : this.continuations) {
                try {
                    then.then(this);
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            }
            this.continuations = null;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean trySetCancelled() {
        synchronized (this.lock) {
            try {
                if (this.complete) {
                    return false;
                }
                this.complete = true;
                this.cancelled = true;
                this.lock.notifyAll();
                runContinuations();
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean trySetResult(TResult tresult) {
        synchronized (this.lock) {
            try {
                if (this.complete) {
                    return false;
                }
                this.complete = true;
                this.result = tresult;
                this.lock.notifyAll();
                runContinuations();
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002d, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean trySetError(java.lang.Exception r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.lock
            monitor-enter(r0)
            boolean r1 = r3.complete     // Catch:{ all -> 0x000a }
            r2 = 0
            if (r1 == 0) goto L_0x000c
            monitor-exit(r0)     // Catch:{ all -> 0x000a }
            return r2
        L_0x000a:
            r4 = move-exception
            goto L_0x002e
        L_0x000c:
            r1 = 1
            r3.complete = r1     // Catch:{ all -> 0x000a }
            r3.error = r4     // Catch:{ all -> 0x000a }
            r3.errorHasBeenObserved = r2     // Catch:{ all -> 0x000a }
            java.lang.Object r4 = r3.lock     // Catch:{ all -> 0x000a }
            r4.notifyAll()     // Catch:{ all -> 0x000a }
            r3.runContinuations()     // Catch:{ all -> 0x000a }
            boolean r4 = r3.errorHasBeenObserved     // Catch:{ all -> 0x000a }
            if (r4 != 0) goto L_0x002c
            com.facebook.react.runtime.internal.bolts.Task$UnobservedExceptionHandler r4 = getUnobservedExceptionHandler()     // Catch:{ all -> 0x000a }
            if (r4 == 0) goto L_0x002c
            com.facebook.react.runtime.internal.bolts.UnobservedErrorNotifier r4 = new com.facebook.react.runtime.internal.bolts.UnobservedErrorNotifier     // Catch:{ all -> 0x000a }
            r4.<init>(r3)     // Catch:{ all -> 0x000a }
            r3.unobservedErrorNotifier = r4     // Catch:{ all -> 0x000a }
        L_0x002c:
            monitor-exit(r0)     // Catch:{ all -> 0x000a }
            return r1
        L_0x002e:
            monitor-exit(r0)     // Catch:{ all -> 0x000a }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.runtime.internal.bolts.Task.trySetError(java.lang.Exception):boolean");
    }
}
