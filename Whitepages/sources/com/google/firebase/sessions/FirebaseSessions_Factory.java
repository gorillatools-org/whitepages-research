package com.google.firebase.sessions;

import com.google.firebase.FirebaseApp;
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
public final class FirebaseSessions_Factory implements Factory<FirebaseSessions> {
    private final Provider backgroundDispatcherProvider;
    private final Provider firebaseAppProvider;
    private final Provider lifecycleServiceBinderProvider;
    private final Provider settingsProvider;

    public FirebaseSessions_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4) {
        this.firebaseAppProvider = provider;
        this.settingsProvider = provider2;
        this.backgroundDispatcherProvider = provider3;
        this.lifecycleServiceBinderProvider = provider4;
    }

    public FirebaseSessions get() {
        return newInstance((FirebaseApp) this.firebaseAppProvider.get(), (SessionsSettings) this.settingsProvider.get(), (CoroutineContext) this.backgroundDispatcherProvider.get(), (SessionLifecycleServiceBinder) this.lifecycleServiceBinderProvider.get());
    }

    public static FirebaseSessions_Factory create(Provider provider, Provider provider2, Provider provider3, Provider provider4) {
        return new FirebaseSessions_Factory(provider, provider2, provider3, provider4);
    }

    public static FirebaseSessions newInstance(FirebaseApp firebaseApp, SessionsSettings sessionsSettings, CoroutineContext coroutineContext, SessionLifecycleServiceBinder sessionLifecycleServiceBinder) {
        return new FirebaseSessions(firebaseApp, sessionsSettings, coroutineContext, sessionLifecycleServiceBinder);
    }
}
