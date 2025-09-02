package bolts;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class Task {
    public static final ExecutorService BACKGROUND_EXECUTOR = BoltsExecutors.background();
    private static final Executor IMMEDIATE_EXECUTOR = BoltsExecutors.immediate();
    private static Task TASK_CANCELLED = new Task(true);
    private static Task TASK_FALSE = new Task((Object) Boolean.FALSE);
    private static Task TASK_NULL = new Task((Object) null);
    private static Task TASK_TRUE = new Task((Object) Boolean.TRUE);
    public static final Executor UI_THREAD_EXECUTOR = AndroidExecutors.uiThread();
    private boolean cancelled;
    private boolean complete;
    private List continuations = new ArrayList();
    private Exception error;
    private boolean errorHasBeenObserved;
    private final Object lock = new Object();
    private Object result;
    private UnobservedErrorNotifier unobservedErrorNotifier;

    public interface UnobservedExceptionHandler {
    }

    public static UnobservedExceptionHandler getUnobservedExceptionHandler() {
        return null;
    }

    Task() {
    }

    private Task(Object obj) {
        trySetResult(obj);
    }

    private Task(boolean z) {
        if (z) {
            trySetCancelled();
        } else {
            trySetResult((Object) null);
        }
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

    public Object getResult() {
        Object obj;
        synchronized (this.lock) {
            obj = this.result;
        }
        return obj;
    }

    public Exception getError() {
        Exception exc;
        synchronized (this.lock) {
            try {
                if (this.error != null) {
                    this.errorHasBeenObserved = true;
                }
                exc = this.error;
            } catch (Throwable th) {
                throw th;
            }
        }
        return exc;
    }

    public static Task forResult(Object obj) {
        if (obj == null) {
            return TASK_NULL;
        }
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue() ? TASK_TRUE : TASK_FALSE;
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        taskCompletionSource.setResult(obj);
        return taskCompletionSource.getTask();
    }

    public static Task forError(Exception exc) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        taskCompletionSource.setError(exc);
        return taskCompletionSource.getTask();
    }

    public static Task call(Callable callable, Executor executor) {
        return call(callable, executor, (CancellationToken) null);
    }

    public static Task call(Callable callable, Executor executor, CancellationToken cancellationToken) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        try {
            executor.execute(new Runnable(cancellationToken, taskCompletionSource, callable) {
                final /* synthetic */ Callable val$callable;
                final /* synthetic */ TaskCompletionSource val$tcs;

                {
                    this.val$tcs = r2;
                    this.val$callable = r3;
                }

                public void run() {
                    try {
                        this.val$tcs.setResult(this.val$callable.call());
                    } catch (CancellationException unused) {
                        this.val$tcs.setCancelled();
                    } catch (Exception e) {
                        this.val$tcs.setError(e);
                    }
                }
            });
        } catch (Exception e) {
            taskCompletionSource.setError(new ExecutorException(e));
        }
        return taskCompletionSource.getTask();
    }

    public Task continueWith(Continuation continuation, Executor executor, CancellationToken cancellationToken) {
        boolean isCompleted;
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        synchronized (this.lock) {
            try {
                isCompleted = isCompleted();
                if (!isCompleted) {
                    this.continuations.add(new Continuation(taskCompletionSource, continuation, executor, cancellationToken) {
                        final /* synthetic */ Continuation val$continuation;
                        final /* synthetic */ Executor val$executor;
                        final /* synthetic */ TaskCompletionSource val$tcs;

                        {
                            this.val$tcs = r2;
                            this.val$continuation = r3;
                            this.val$executor = r4;
                        }

                        public Void then(Task task) {
                            Task.completeImmediately(this.val$tcs, this.val$continuation, task, this.val$executor, (CancellationToken) null);
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
            completeImmediately(taskCompletionSource, continuation, this, executor, cancellationToken);
        }
        return taskCompletionSource.getTask();
    }

    public Task continueWith(Continuation continuation) {
        return continueWith(continuation, IMMEDIATE_EXECUTOR, (CancellationToken) null);
    }

    /* access modifiers changed from: private */
    public static void completeImmediately(TaskCompletionSource taskCompletionSource, Continuation continuation, Task task, Executor executor, CancellationToken cancellationToken) {
        try {
            executor.execute(new Runnable(cancellationToken, taskCompletionSource, continuation, task) {
                final /* synthetic */ Continuation val$continuation;
                final /* synthetic */ Task val$task;
                final /* synthetic */ TaskCompletionSource val$tcs;

                {
                    this.val$tcs = r2;
                    this.val$continuation = r3;
                    this.val$task = r4;
                }

                public void run() {
                    try {
                        this.val$tcs.setResult(this.val$continuation.then(this.val$task));
                    } catch (CancellationException unused) {
                        this.val$tcs.setCancelled();
                    } catch (Exception e) {
                        this.val$tcs.setError(e);
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
    public boolean trySetResult(Object obj) {
        synchronized (this.lock) {
            try {
                if (this.complete) {
                    return false;
                }
                this.complete = true;
                this.result = obj;
                this.lock.notifyAll();
                runContinuations();
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0023, code lost:
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
            goto L_0x0024
        L_0x000c:
            r1 = 1
            r3.complete = r1     // Catch:{ all -> 0x000a }
            r3.error = r4     // Catch:{ all -> 0x000a }
            r3.errorHasBeenObserved = r2     // Catch:{ all -> 0x000a }
            java.lang.Object r4 = r3.lock     // Catch:{ all -> 0x000a }
            r4.notifyAll()     // Catch:{ all -> 0x000a }
            r3.runContinuations()     // Catch:{ all -> 0x000a }
            boolean r4 = r3.errorHasBeenObserved     // Catch:{ all -> 0x000a }
            if (r4 != 0) goto L_0x0022
            getUnobservedExceptionHandler()     // Catch:{ all -> 0x000a }
        L_0x0022:
            monitor-exit(r0)     // Catch:{ all -> 0x000a }
            return r1
        L_0x0024:
            monitor-exit(r0)     // Catch:{ all -> 0x000a }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: bolts.Task.trySetError(java.lang.Exception):boolean");
    }
}
