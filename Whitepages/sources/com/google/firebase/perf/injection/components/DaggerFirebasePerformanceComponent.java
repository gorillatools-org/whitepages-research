package com.google.firebase.perf.injection.components;

import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.FirebasePerformance_Factory;
import com.google.firebase.perf.injection.modules.FirebasePerformanceModule;
import com.google.firebase.perf.injection.modules.FirebasePerformanceModule_ProvidesConfigResolverFactory;
import com.google.firebase.perf.injection.modules.FirebasePerformanceModule_ProvidesFirebaseAppFactory;
import com.google.firebase.perf.injection.modules.FirebasePerformanceModule_ProvidesFirebaseInstallationsFactory;
import com.google.firebase.perf.injection.modules.FirebasePerformanceModule_ProvidesRemoteConfigComponentFactory;
import com.google.firebase.perf.injection.modules.FirebasePerformanceModule_ProvidesRemoteConfigManagerFactory;
import com.google.firebase.perf.injection.modules.FirebasePerformanceModule_ProvidesSessionManagerFactory;
import com.google.firebase.perf.injection.modules.FirebasePerformanceModule_ProvidesTransportFactoryProviderFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class DaggerFirebasePerformanceComponent {
    private DaggerFirebasePerformanceComponent() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private FirebasePerformanceModule firebasePerformanceModule;

        private Builder() {
        }

        public Builder firebasePerformanceModule(FirebasePerformanceModule firebasePerformanceModule2) {
            this.firebasePerformanceModule = (FirebasePerformanceModule) Preconditions.checkNotNull(firebasePerformanceModule2);
            return this;
        }

        public FirebasePerformanceComponent build() {
            Preconditions.checkBuilderRequirement(this.firebasePerformanceModule, FirebasePerformanceModule.class);
            return new FirebasePerformanceComponentImpl(this.firebasePerformanceModule);
        }
    }

    private static final class FirebasePerformanceComponentImpl implements FirebasePerformanceComponent {
        private final FirebasePerformanceComponentImpl firebasePerformanceComponentImpl;
        private Provider firebasePerformanceProvider;
        private Provider providesConfigResolverProvider;
        private Provider providesFirebaseAppProvider;
        private Provider providesFirebaseInstallationsProvider;
        private Provider providesRemoteConfigComponentProvider;
        private Provider providesRemoteConfigManagerProvider;
        private Provider providesSessionManagerProvider;
        private Provider providesTransportFactoryProvider;

        private FirebasePerformanceComponentImpl(FirebasePerformanceModule firebasePerformanceModule) {
            this.firebasePerformanceComponentImpl = this;
            initialize(firebasePerformanceModule);
        }

        private void initialize(FirebasePerformanceModule firebasePerformanceModule) {
            this.providesFirebaseAppProvider = FirebasePerformanceModule_ProvidesFirebaseAppFactory.create(firebasePerformanceModule);
            this.providesRemoteConfigComponentProvider = FirebasePerformanceModule_ProvidesRemoteConfigComponentFactory.create(firebasePerformanceModule);
            this.providesFirebaseInstallationsProvider = FirebasePerformanceModule_ProvidesFirebaseInstallationsFactory.create(firebasePerformanceModule);
            this.providesTransportFactoryProvider = FirebasePerformanceModule_ProvidesTransportFactoryProviderFactory.create(firebasePerformanceModule);
            this.providesRemoteConfigManagerProvider = FirebasePerformanceModule_ProvidesRemoteConfigManagerFactory.create(firebasePerformanceModule);
            this.providesConfigResolverProvider = FirebasePerformanceModule_ProvidesConfigResolverFactory.create(firebasePerformanceModule);
            FirebasePerformanceModule_ProvidesSessionManagerFactory create = FirebasePerformanceModule_ProvidesSessionManagerFactory.create(firebasePerformanceModule);
            this.providesSessionManagerProvider = create;
            this.firebasePerformanceProvider = DoubleCheck.provider(FirebasePerformance_Factory.create(this.providesFirebaseAppProvider, this.providesRemoteConfigComponentProvider, this.providesFirebaseInstallationsProvider, this.providesTransportFactoryProvider, this.providesRemoteConfigManagerProvider, this.providesConfigResolverProvider, create));
        }

        public FirebasePerformance getFirebasePerformance() {
            return (FirebasePerformance) this.firebasePerformanceProvider.get();
        }
    }
}
