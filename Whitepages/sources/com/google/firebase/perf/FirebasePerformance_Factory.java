package com.google.firebase.perf;

import com.google.android.datatransport.TransportFactory;
import com.google.firebase.FirebaseApp;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.perf.config.ConfigResolver;
import com.google.firebase.perf.config.RemoteConfigManager;
import com.google.firebase.perf.session.SessionManager;
import com.google.firebase.remoteconfig.RemoteConfigComponent;
import javax.inject.Provider;

public final class FirebasePerformance_Factory implements Provider {
    private final Provider configResolverProvider;
    private final Provider firebaseAppProvider;
    private final Provider firebaseInstallationsApiProvider;
    private final Provider firebaseRemoteConfigProvider;
    private final Provider remoteConfigManagerProvider;
    private final Provider sessionManagerProvider;
    private final Provider transportFactoryProvider;

    public FirebasePerformance_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7) {
        this.firebaseAppProvider = provider;
        this.firebaseRemoteConfigProvider = provider2;
        this.firebaseInstallationsApiProvider = provider3;
        this.transportFactoryProvider = provider4;
        this.remoteConfigManagerProvider = provider5;
        this.configResolverProvider = provider6;
        this.sessionManagerProvider = provider7;
    }

    public FirebasePerformance get() {
        return newInstance((FirebaseApp) this.firebaseAppProvider.get(), (com.google.firebase.inject.Provider) this.firebaseRemoteConfigProvider.get(), (FirebaseInstallationsApi) this.firebaseInstallationsApiProvider.get(), (com.google.firebase.inject.Provider) this.transportFactoryProvider.get(), (RemoteConfigManager) this.remoteConfigManagerProvider.get(), (ConfigResolver) this.configResolverProvider.get(), (SessionManager) this.sessionManagerProvider.get());
    }

    public static FirebasePerformance_Factory create(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7) {
        return new FirebasePerformance_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static FirebasePerformance newInstance(FirebaseApp firebaseApp, com.google.firebase.inject.Provider<RemoteConfigComponent> provider, FirebaseInstallationsApi firebaseInstallationsApi, com.google.firebase.inject.Provider<TransportFactory> provider2, RemoteConfigManager remoteConfigManager, ConfigResolver configResolver, SessionManager sessionManager) {
        return new FirebasePerformance(firebaseApp, provider, firebaseInstallationsApi, provider2, remoteConfigManager, configResolver, sessionManager);
    }
}
