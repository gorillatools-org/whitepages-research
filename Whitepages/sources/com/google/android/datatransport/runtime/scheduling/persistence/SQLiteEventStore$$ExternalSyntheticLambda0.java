package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

public final /* synthetic */ class SQLiteEventStore$$ExternalSyntheticLambda0 implements SQLiteEventStore.Function {
    public final /* synthetic */ SQLiteEventStore f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ SQLiteEventStore$$ExternalSyntheticLambda0(SQLiteEventStore sQLiteEventStore, String str, String str2) {
        this.f$0 = sQLiteEventStore;
        this.f$1 = str;
        this.f$2 = str2;
    }

    public final Object apply(Object obj) {
        return this.f$0.lambda$recordFailure$4(this.f$1, this.f$2, (SQLiteDatabase) obj);
    }
}
