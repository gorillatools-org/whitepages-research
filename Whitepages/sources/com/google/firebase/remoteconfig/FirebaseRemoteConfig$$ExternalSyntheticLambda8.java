package com.google.firebase.remoteconfig;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class FirebaseRemoteConfig$$ExternalSyntheticLambda8 implements Continuation {
    public final /* synthetic */ Task f$0;

    public /* synthetic */ FirebaseRemoteConfig$$ExternalSyntheticLambda8(Task task) {
        this.f$0 = task;
    }

    public final Object then(Task task) {
        return FirebaseRemoteConfig.lambda$ensureInitialized$0(this.f$0, task);
    }
}
