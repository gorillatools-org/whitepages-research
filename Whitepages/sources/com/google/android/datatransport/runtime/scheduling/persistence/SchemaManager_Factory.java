package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import javax.inject.Provider;

public final class SchemaManager_Factory implements Factory {
    private final Provider contextProvider;
    private final Provider dbNameProvider;
    private final Provider schemaVersionProvider;

    public SchemaManager_Factory(Provider provider, Provider provider2, Provider provider3) {
        this.contextProvider = provider;
        this.dbNameProvider = provider2;
        this.schemaVersionProvider = provider3;
    }

    public SchemaManager get() {
        return newInstance((Context) this.contextProvider.get(), (String) this.dbNameProvider.get(), ((Integer) this.schemaVersionProvider.get()).intValue());
    }

    public static SchemaManager_Factory create(Provider provider, Provider provider2, Provider provider3) {
        return new SchemaManager_Factory(provider, provider2, provider3);
    }

    public static SchemaManager newInstance(Context context, String str, int i) {
        return new SchemaManager(context, str, i);
    }
}
