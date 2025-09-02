package com.google.firebase.crashlytics.internal.concurrency;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class CrashlyticsWorker$$ExternalSyntheticLambda6 implements Continuation {
    public final /* synthetic */ SuccessContinuation f$0;

    public /* synthetic */ CrashlyticsWorker$$ExternalSyntheticLambda6(SuccessContinuation successContinuation) {
        this.f$0 = successContinuation;
    }

    public final Object then(Task task) {
        return CrashlyticsWorker.lambda$submitTaskOnSuccess$5(this.f$0, task);
    }
}
