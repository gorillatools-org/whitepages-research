package com.google.firebase.remoteconfig;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class FirebaseRemoteConfig$$ExternalSyntheticLambda2 implements Continuation {
    public final /* synthetic */ FirebaseRemoteConfig f$0;

    public /* synthetic */ FirebaseRemoteConfig$$ExternalSyntheticLambda2(FirebaseRemoteConfig firebaseRemoteConfig) {
        this.f$0 = firebaseRemoteConfig;
    }

    public final Object then(Task task) {
        return Boolean.valueOf(this.f$0.processActivatePutTask(task));
    }
}
