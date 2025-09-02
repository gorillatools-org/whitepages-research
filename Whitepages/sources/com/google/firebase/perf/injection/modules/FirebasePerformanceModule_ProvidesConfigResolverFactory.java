package com.google.firebase.perf.injection.modules;

import com.google.firebase.perf.config.ConfigResolver;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class FirebasePerformanceModule_ProvidesConfigResolverFactory implements Provider {
    private final FirebasePerformanceModule module;

    public FirebasePerformanceModule_ProvidesConfigResolverFactory(FirebasePerformanceModule firebasePerformanceModule) {
        this.module = firebasePerformanceModule;
    }

    public ConfigResolver get() {
        return providesConfigResolver(this.module);
    }

    public static FirebasePerformanceModule_ProvidesConfigResolverFactory create(FirebasePerformanceModule firebasePerformanceModule) {
        return new FirebasePerformanceModule_ProvidesConfigResolverFactory(firebasePerformanceModule);
    }

    public static ConfigResolver providesConfigResolver(FirebasePerformanceModule firebasePerformanceModule) {
        return (ConfigResolver) Preconditions.checkNotNullFromProvides(firebasePerformanceModule.providesConfigResolver());
    }
}
