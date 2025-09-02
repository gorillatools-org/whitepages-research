package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

public final /* synthetic */ class SQLiteEventStore$$ExternalSyntheticLambda8 implements SQLiteEventStore.Function {
    public final /* synthetic */ SQLiteEventStore f$0;

    public /* synthetic */ SQLiteEventStore$$ExternalSyntheticLambda8(SQLiteEventStore sQLiteEventStore) {
        this.f$0 = sQLiteEventStore;
    }

    public final Object apply(Object obj) {
        return this.f$0.lambda$resetClientMetrics$23((SQLiteDatabase) obj);
    }
}
