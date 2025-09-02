package com.google.firebase.remoteconfig;

import java.util.concurrent.Callable;

public final /* synthetic */ class FirebaseRemoteConfig$$ExternalSyntheticLambda6 implements Callable {
    public final /* synthetic */ FirebaseRemoteConfig f$0;
    public final /* synthetic */ FirebaseRemoteConfigSettings f$1;

    public /* synthetic */ FirebaseRemoteConfig$$ExternalSyntheticLambda6(FirebaseRemoteConfig firebaseRemoteConfig, FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        this.f$0 = firebaseRemoteConfig;
        this.f$1 = firebaseRemoteConfigSettings;
    }

    public final Object call() {
        return this.f$0.lambda$setConfigSettingsAsync$5(this.f$1);
    }
}
