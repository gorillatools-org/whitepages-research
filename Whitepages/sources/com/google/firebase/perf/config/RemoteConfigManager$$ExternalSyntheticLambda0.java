package com.google.firebase.perf.config;

import com.google.android.gms.tasks.OnSuccessListener;

public final /* synthetic */ class RemoteConfigManager$$ExternalSyntheticLambda0 implements OnSuccessListener {
    public final /* synthetic */ RemoteConfigManager f$0;

    public /* synthetic */ RemoteConfigManager$$ExternalSyntheticLambda0(RemoteConfigManager remoteConfigManager) {
        this.f$0 = remoteConfigManager;
    }

    public final void onSuccess(Object obj) {
        this.f$0.lambda$triggerFirebaseRemoteConfigFetchAndActivateOnSuccessfulFetch$0((Boolean) obj);
    }
}
