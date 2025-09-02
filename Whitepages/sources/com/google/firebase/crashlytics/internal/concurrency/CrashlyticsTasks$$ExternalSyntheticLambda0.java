package com.google.firebase.crashlytics.internal.concurrency;

import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.atomic.AtomicBoolean;

public final /* synthetic */ class CrashlyticsTasks$$ExternalSyntheticLambda0 implements Continuation {
    public final /* synthetic */ TaskCompletionSource f$0;
    public final /* synthetic */ AtomicBoolean f$1;
    public final /* synthetic */ CancellationTokenSource f$2;

    public /* synthetic */ CrashlyticsTasks$$ExternalSyntheticLambda0(TaskCompletionSource taskCompletionSource, AtomicBoolean atomicBoolean, CancellationTokenSource cancellationTokenSource) {
        this.f$0 = taskCompletionSource;
        this.f$1 = atomicBoolean;
        this.f$2 = cancellationTokenSource;
    }

    public final Object then(Task task) {
        return CrashlyticsTasks.lambda$race$0(this.f$0, this.f$1, this.f$2, task);
    }
}
