package com.google.firebase.sessions;

import android.content.Context;
import com.google.firebase.sessions.dagger.internal.DaggerGenerated;
import com.google.firebase.sessions.dagger.internal.Factory;
import com.google.firebase.sessions.dagger.internal.QualifierMetadata;
import com.google.firebase.sessions.dagger.internal.ScopeMetadata;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@DaggerGenerated
@QualifierMetadata
public final class SessionLifecycleServiceBinderImpl_Factory implements Factory<SessionLifecycleServiceBinderImpl> {
    private final Provider appContextProvider;

    public SessionLifecycleServiceBinderImpl_Factory(Provider provider) {
        this.appContextProvider = provider;
    }

    public SessionLifecycleServiceBinderImpl get() {
        return newInstance((Context) this.appContextProvider.get());
    }

    public static SessionLifecycleServiceBinderImpl_Factory create(Provider provider) {
        return new SessionLifecycleServiceBinderImpl_Factory(provider);
    }

    public static SessionLifecycleServiceBinderImpl newInstance(Context context) {
        return new SessionLifecycleServiceBinderImpl(context);
    }
}
