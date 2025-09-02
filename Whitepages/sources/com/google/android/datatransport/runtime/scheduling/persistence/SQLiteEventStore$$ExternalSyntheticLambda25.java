package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

public final /* synthetic */ class SQLiteEventStore$$ExternalSyntheticLambda25 implements SQLiteEventStore.Function {
    public final /* synthetic */ SQLiteEventStore f$0;

    public /* synthetic */ SQLiteEventStore$$ExternalSyntheticLambda25(SQLiteEventStore sQLiteEventStore) {
        this.f$0 = sQLiteEventStore;
    }

    public final Object apply(Object obj) {
        return this.f$0.lambda$cleanUp$11((Cursor) obj);
    }
}
