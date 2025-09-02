package com.google.firebase.remoteconfig;

import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;

public final /* synthetic */ class FirebaseRemoteConfig$$ExternalSyntheticLambda9 implements SuccessContinuation {
    public final /* synthetic */ FirebaseRemoteConfig f$0;

    public /* synthetic */ FirebaseRemoteConfig$$ExternalSyntheticLambda9(FirebaseRemoteConfig firebaseRemoteConfig) {
        this.f$0 = firebaseRemoteConfig;
    }

    public final Task then(Object obj) {
        return this.f$0.lambda$fetchAndActivate$1((Void) obj);
    }
}
