package com.google.firebase.sessions;

import com.google.android.datatransport.TransportFactory;
import com.google.firebase.sessions.dagger.internal.DaggerGenerated;
import com.google.firebase.sessions.dagger.internal.Factory;
import com.google.firebase.sessions.dagger.internal.QualifierMetadata;
import com.google.firebase.sessions.dagger.internal.ScopeMetadata;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@DaggerGenerated
@QualifierMetadata
public final class EventGDTLogger_Factory implements Factory<EventGDTLogger> {
    private final Provider transportFactoryProvider;

    public EventGDTLogger_Factory(Provider provider) {
        this.transportFactoryProvider = provider;
    }

    public EventGDTLogger get() {
        return newInstance((com.google.firebase.inject.Provider) this.transportFactoryProvider.get());
    }

    public static EventGDTLogger_Factory create(Provider provider) {
        return new EventGDTLogger_Factory(provider);
    }

    public static EventGDTLogger newInstance(com.google.firebase.inject.Provider<TransportFactory> provider) {
        return new EventGDTLogger(provider);
    }
}
