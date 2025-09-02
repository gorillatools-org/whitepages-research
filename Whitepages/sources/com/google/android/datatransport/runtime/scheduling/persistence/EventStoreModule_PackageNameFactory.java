package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;
import javax.inject.Provider;

public final class EventStoreModule_PackageNameFactory implements Factory {
    private final Provider contextProvider;

    public EventStoreModule_PackageNameFactory(Provider provider) {
        this.contextProvider = provider;
    }

    public String get() {
        return packageName((Context) this.contextProvider.get());
    }

    public static EventStoreModule_PackageNameFactory create(Provider provider) {
        return new EventStoreModule_PackageNameFactory(provider);
    }

    public static String packageName(Context context) {
        return (String) Preconditions.checkNotNullFromProvides(EventStoreModule.packageName(context));
    }
}
