package com.google.firebase.sessions;

import android.content.Context;
import com.google.firebase.sessions.dagger.internal.DaggerGenerated;
import com.google.firebase.sessions.dagger.internal.Factory;
import com.google.firebase.sessions.dagger.internal.QualifierMetadata;
import com.google.firebase.sessions.dagger.internal.ScopeMetadata;
import javax.inject.Provider;
import kotlin.coroutines.CoroutineContext;

@ScopeMetadata("javax.inject.Singleton")
@DaggerGenerated
@QualifierMetadata({"com.google.firebase.annotations.concurrent.Background"})
public final class SessionDatastoreImpl_Factory implements Factory<SessionDatastoreImpl> {
    private final Provider appContextProvider;
    private final Provider backgroundDispatcherProvider;

    public SessionDatastoreImpl_Factory(Provider provider, Provider provider2) {
        this.appContextProvider = provider;
        this.backgroundDispatcherProvider = provider2;
    }

    public SessionDatastoreImpl get() {
        return newInstance((Context) this.appContextProvider.get(), (CoroutineContext) this.backgroundDispatcherProvider.get());
    }

    public static SessionDatastoreImpl_Factory create(Provider provider, Provider provider2) {
        return new SessionDatastoreImpl_Factory(provider, provider2);
    }

    public static SessionDatastoreImpl newInstance(Context context, CoroutineContext coroutineContext) {
        return new SessionDatastoreImpl(context, coroutineContext);
    }
}
