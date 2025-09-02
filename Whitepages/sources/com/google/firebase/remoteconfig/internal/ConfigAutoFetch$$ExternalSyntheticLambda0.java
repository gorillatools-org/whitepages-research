package com.google.firebase.remoteconfig.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class ConfigAutoFetch$$ExternalSyntheticLambda0 implements Continuation {
    public final /* synthetic */ ConfigAutoFetch f$0;
    public final /* synthetic */ Task f$1;
    public final /* synthetic */ Task f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ int f$4;

    public /* synthetic */ ConfigAutoFetch$$ExternalSyntheticLambda0(ConfigAutoFetch configAutoFetch, Task task, Task task2, long j, int i) {
        this.f$0 = configAutoFetch;
        this.f$1 = task;
        this.f$2 = task2;
        this.f$3 = j;
        this.f$4 = i;
    }

    public final Object then(Task task) {
        return this.f$0.lambda$fetchLatestConfig$0(this.f$1, this.f$2, this.f$3, this.f$4, task);
    }
}
