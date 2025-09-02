package com.google.firebase.sessions;

import com.google.firebase.FirebaseApp;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.sessions.dagger.internal.DaggerGenerated;
import com.google.firebase.sessions.dagger.internal.Factory;
import com.google.firebase.sessions.dagger.internal.QualifierMetadata;
import com.google.firebase.sessions.dagger.internal.ScopeMetadata;
import com.google.firebase.sessions.settings.SessionsSettings;
import javax.inject.Provider;
import kotlin.coroutines.CoroutineContext;

@ScopeMetadata("javax.inject.Singleton")
@DaggerGenerated
@QualifierMetadata({"com.google.firebase.annotations.concurrent.Background"})
public final class SessionFirelogPublisherImpl_Factory implements Factory<SessionFirelogPublisherImpl> {
    private final Provider backgroundDispatcherProvider;
    private final Provider eventGDTLoggerProvider;
    private final Provider firebaseAppProvider;
    private final Provider firebaseInstallationsProvider;
    private final Provider sessionSettingsProvider;

    public SessionFirelogPublisherImpl_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5) {
        this.firebaseAppProvider = provider;
        this.firebaseInstallationsProvider = provider2;
        this.sessionSettingsProvider = provider3;
        this.eventGDTLoggerProvider = provider4;
        this.backgroundDispatcherProvider = provider5;
    }

    public SessionFirelogPublisherImpl get() {
        return newInstance((FirebaseApp) this.firebaseAppProvider.get(), (FirebaseInstallationsApi) this.firebaseInstallationsProvider.get(), (SessionsSettings) this.sessionSettingsProvider.get(), (EventGDTLoggerInterface) this.eventGDTLoggerProvider.get(), (CoroutineContext) this.backgroundDispatcherProvider.get());
    }

    public static SessionFirelogPublisherImpl_Factory create(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5) {
        return new SessionFirelogPublisherImpl_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static SessionFirelogPublisherImpl newInstance(FirebaseApp firebaseApp, FirebaseInstallationsApi firebaseInstallationsApi, SessionsSettings sessionsSettings, EventGDTLoggerInterface eventGDTLoggerInterface, CoroutineContext coroutineContext) {
        return new SessionFirelogPublisherImpl(firebaseApp, firebaseInstallationsApi, sessionsSettings, eventGDTLoggerInterface, coroutineContext);
    }
}
