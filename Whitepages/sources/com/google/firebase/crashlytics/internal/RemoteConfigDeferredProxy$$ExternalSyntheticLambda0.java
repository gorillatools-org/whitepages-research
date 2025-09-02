package com.google.firebase.crashlytics.internal;

import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;

public final /* synthetic */ class RemoteConfigDeferredProxy$$ExternalSyntheticLambda0 implements Deferred.DeferredHandler {
    public final /* synthetic */ CrashlyticsRemoteConfigListener f$0;

    public /* synthetic */ RemoteConfigDeferredProxy$$ExternalSyntheticLambda0(CrashlyticsRemoteConfigListener crashlyticsRemoteConfigListener) {
        this.f$0 = crashlyticsRemoteConfigListener;
    }

    public final void handle(Provider provider) {
        RemoteConfigDeferredProxy.lambda$setupListener$0(this.f$0, provider);
    }
}
