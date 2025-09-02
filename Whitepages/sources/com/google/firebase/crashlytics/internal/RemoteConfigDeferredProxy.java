package com.google.firebase.crashlytics.internal;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;
import com.google.firebase.remoteconfig.RemoteConfigComponent;
import com.google.firebase.remoteconfig.interop.FirebaseRemoteConfigInterop;

public class RemoteConfigDeferredProxy {
    private final Deferred<FirebaseRemoteConfigInterop> remoteConfigInteropDeferred;

    public RemoteConfigDeferredProxy(Deferred<FirebaseRemoteConfigInterop> deferred) {
        this.remoteConfigInteropDeferred = deferred;
    }

    public void setupListener(UserMetadata userMetadata) {
        if (userMetadata == null) {
            Logger.getLogger().w("Didn't successfully register with UserMetadata for rollouts listener");
            return;
        }
        this.remoteConfigInteropDeferred.whenAvailable(new RemoteConfigDeferredProxy$$ExternalSyntheticLambda0(new CrashlyticsRemoteConfigListener(userMetadata)));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setupListener$0(CrashlyticsRemoteConfigListener crashlyticsRemoteConfigListener, Provider provider) {
        ((FirebaseRemoteConfigInterop) provider.get()).registerRolloutsStateSubscriber(RemoteConfigComponent.DEFAULT_NAMESPACE, crashlyticsRemoteConfigListener);
        Logger.getLogger().d("Registering RemoteConfig Rollouts subscriber");
    }
}
