package com.google.firebase.remoteconfig;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class FirebaseRemoteConfig$$ExternalSyntheticLambda0 implements Continuation {
    public final /* synthetic */ FirebaseRemoteConfig f$0;
    public final /* synthetic */ Task f$1;
    public final /* synthetic */ Task f$2;

    public /* synthetic */ FirebaseRemoteConfig$$ExternalSyntheticLambda0(FirebaseRemoteConfig firebaseRemoteConfig, Task task, Task task2) {
        this.f$0 = firebaseRemoteConfig;
        this.f$1 = task;
        this.f$2 = task2;
    }

    public final Object then(Task task) {
        return this.f$0.lambda$activate$2(this.f$1, this.f$2, task);
    }
}
