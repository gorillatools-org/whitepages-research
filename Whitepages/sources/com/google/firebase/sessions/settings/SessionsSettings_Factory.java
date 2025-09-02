package com.google.firebase.sessions.settings;

import com.google.firebase.FirebaseApp;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.sessions.dagger.internal.DaggerGenerated;
import com.google.firebase.sessions.dagger.internal.Factory;
import com.google.firebase.sessions.dagger.internal.QualifierMetadata;
import com.google.firebase.sessions.dagger.internal.ScopeMetadata;
import javax.inject.Provider;
import kotlin.coroutines.CoroutineContext;

@ScopeMetadata("javax.inject.Singleton")
@DaggerGenerated
@QualifierMetadata({"com.google.firebase.annotations.concurrent.Blocking", "com.google.firebase.annotations.concurrent.Background"})
public final class SessionsSettings_Factory implements Factory<SessionsSettings> {
    private final Provider backgroundDispatcherProvider;
    private final Provider blockingDispatcherProvider;
    private final Provider firebaseAppProvider;
    private final Provider firebaseInstallationsApiProvider;

    public SessionsSettings_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4) {
        this.firebaseAppProvider = provider;
        this.blockingDispatcherProvider = provider2;
        this.backgroundDispatcherProvider = provider3;
        this.firebaseInstallationsApiProvider = provider4;
    }

    public SessionsSettings get() {
        return newInstance((FirebaseApp) this.firebaseAppProvider.get(), (CoroutineContext) this.blockingDispatcherProvider.get(), (CoroutineContext) this.backgroundDispatcherProvider.get(), (FirebaseInstallationsApi) this.firebaseInstallationsApiProvider.get());
    }

    public static SessionsSettings_Factory create(Provider provider, Provider provider2, Provider provider3, Provider provider4) {
        return new SessionsSettings_Factory(provider, provider2, provider3, provider4);
    }

    public static SessionsSettings newInstance(FirebaseApp firebaseApp, CoroutineContext coroutineContext, CoroutineContext coroutineContext2, FirebaseInstallationsApi firebaseInstallationsApi) {
        return new SessionsSettings(firebaseApp, coroutineContext, coroutineContext2, firebaseInstallationsApi);
    }
}
