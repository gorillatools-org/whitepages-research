package com.google.firebase.perf.injection.modules;

import com.google.firebase.installations.FirebaseInstallationsApi;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class FirebasePerformanceModule_ProvidesFirebaseInstallationsFactory implements Provider {
    private final FirebasePerformanceModule module;

    public FirebasePerformanceModule_ProvidesFirebaseInstallationsFactory(FirebasePerformanceModule firebasePerformanceModule) {
        this.module = firebasePerformanceModule;
    }

    public FirebaseInstallationsApi get() {
        return providesFirebaseInstallations(this.module);
    }

    public static FirebasePerformanceModule_ProvidesFirebaseInstallationsFactory create(FirebasePerformanceModule firebasePerformanceModule) {
        return new FirebasePerformanceModule_ProvidesFirebaseInstallationsFactory(firebasePerformanceModule);
    }

    public static FirebaseInstallationsApi providesFirebaseInstallations(FirebasePerformanceModule firebasePerformanceModule) {
        return (FirebaseInstallationsApi) Preconditions.checkNotNullFromProvides(firebasePerformanceModule.providesFirebaseInstallations());
    }
}
