package com.google.firebase.perf.injection.modules;

import com.google.firebase.perf.config.RemoteConfigManager;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class FirebasePerformanceModule_ProvidesRemoteConfigManagerFactory implements Provider {
    private final FirebasePerformanceModule module;

    public FirebasePerformanceModule_ProvidesRemoteConfigManagerFactory(FirebasePerformanceModule firebasePerformanceModule) {
        this.module = firebasePerformanceModule;
    }

    public RemoteConfigManager get() {
        return providesRemoteConfigManager(this.module);
    }

    public static FirebasePerformanceModule_ProvidesRemoteConfigManagerFactory create(FirebasePerformanceModule firebasePerformanceModule) {
        return new FirebasePerformanceModule_ProvidesRemoteConfigManagerFactory(firebasePerformanceModule);
    }

    public static RemoteConfigManager providesRemoteConfigManager(FirebasePerformanceModule firebasePerformanceModule) {
        return (RemoteConfigManager) Preconditions.checkNotNullFromProvides(firebasePerformanceModule.providesRemoteConfigManager());
    }
}
