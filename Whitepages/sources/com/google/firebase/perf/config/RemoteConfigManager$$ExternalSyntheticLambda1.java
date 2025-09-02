package com.google.firebase.perf.config;

import com.google.android.gms.tasks.OnFailureListener;

public final /* synthetic */ class RemoteConfigManager$$ExternalSyntheticLambda1 implements OnFailureListener {
    public final /* synthetic */ RemoteConfigManager f$0;

    public /* synthetic */ RemoteConfigManager$$ExternalSyntheticLambda1(RemoteConfigManager remoteConfigManager) {
        this.f$0 = remoteConfigManager;
    }

    public final void onFailure(Exception exc) {
        this.f$0.lambda$triggerFirebaseRemoteConfigFetchAndActivateOnSuccessfulFetch$1(exc);
    }
}
