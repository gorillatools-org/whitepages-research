package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;

public abstract class EventStoreModule {
    static EventStoreConfig storeConfig() {
        return EventStoreConfig.DEFAULT;
    }

    static int schemaVersion() {
        return SchemaManager.SCHEMA_VERSION;
    }

    static String dbName() {
        return "com.google.android.datatransport.events";
    }

    static String packageName(Context context) {
        return context.getPackageName();
    }
}
