package com.google.firebase.crashlytics.internal.concurrency;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class CrashlyticsWorker$$ExternalSyntheticLambda0 implements Continuation {
    public final /* synthetic */ Runnable f$0;

    public /* synthetic */ CrashlyticsWorker$$ExternalSyntheticLambda0(Runnable runnable) {
        this.f$0 = runnable;
    }

    public final Object then(Task task) {
        return this.f$0.run();
    }
}
