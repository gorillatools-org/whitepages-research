package com.google.firebase.perf.injection.modules;

import com.google.firebase.remoteconfig.RemoteConfigComponent;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class FirebasePerformanceModule_ProvidesRemoteConfigComponentFactory implements Provider {
    private final FirebasePerformanceModule module;

    public FirebasePerformanceModule_ProvidesRemoteConfigComponentFactory(FirebasePerformanceModule firebasePerformanceModule) {
        this.module = firebasePerformanceModule;
    }

    public com.google.firebase.inject.Provider<RemoteConfigComponent> get() {
        return providesRemoteConfigComponent(this.module);
    }

    public static FirebasePerformanceModule_ProvidesRemoteConfigComponentFactory create(FirebasePerformanceModule firebasePerformanceModule) {
        return new FirebasePerformanceModule_ProvidesRemoteConfigComponentFactory(firebasePerformanceModule);
    }

    public static com.google.firebase.inject.Provider<RemoteConfigComponent> providesRemoteConfigComponent(FirebasePerformanceModule firebasePerformanceModule) {
        return (com.google.firebase.inject.Provider) Preconditions.checkNotNullFromProvides(firebasePerformanceModule.providesRemoteConfigComponent());
    }
}
