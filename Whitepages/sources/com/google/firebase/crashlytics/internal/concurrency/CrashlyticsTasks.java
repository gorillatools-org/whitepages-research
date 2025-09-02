package com.google.firebase.crashlytics.internal.concurrency;

import androidx.privacysandbox.ads.adservices.measurement.MeasurementManagerImplCommon$$ExternalSyntheticLambda3;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

public final class CrashlyticsTasks {
    private static final Executor DIRECT = new MeasurementManagerImplCommon$$ExternalSyntheticLambda3();

    public static <T> Task<T> race(Task<T> task, Task<T> task2) {
        CancellationTokenSource cancellationTokenSource = new CancellationTokenSource();
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource(cancellationTokenSource.getToken());
        CrashlyticsTasks$$ExternalSyntheticLambda0 crashlyticsTasks$$ExternalSyntheticLambda0 = new CrashlyticsTasks$$ExternalSyntheticLambda0(taskCompletionSource, new AtomicBoolean(false), cancellationTokenSource);
        Executor executor = DIRECT;
        task.continueWithTask(executor, crashlyticsTasks$$ExternalSyntheticLambda0);
        task2.continueWithTask(executor, crashlyticsTasks$$ExternalSyntheticLambda0);
        return taskCompletionSource.getTask();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Task lambda$race$0(TaskCompletionSource taskCompletionSource, AtomicBoolean atomicBoolean, CancellationTokenSource cancellationTokenSource, Task task) throws Exception {
        if (task.isSuccessful()) {
            taskCompletionSource.trySetResult(task.getResult());
        } else if (task.getException() != null) {
            taskCompletionSource.trySetException(task.getException());
        } else if (atomicBoolean.getAndSet(true)) {
            cancellationTokenSource.cancel();
        }
        return Tasks.forResult(null);
    }

    private CrashlyticsTasks() {
    }
}
