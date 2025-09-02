package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

public final /* synthetic */ class SQLiteEventStore$$ExternalSyntheticLambda13 implements SQLiteEventStore.Function {
    public final /* synthetic */ SQLiteEventStore f$0;

    public /* synthetic */ SQLiteEventStore$$ExternalSyntheticLambda13(SQLiteEventStore sQLiteEventStore) {
        this.f$0 = sQLiteEventStore;
    }

    public final Object apply(Object obj) {
        return this.f$0.lambda$recordFailure$3((Cursor) obj);
    }
}
