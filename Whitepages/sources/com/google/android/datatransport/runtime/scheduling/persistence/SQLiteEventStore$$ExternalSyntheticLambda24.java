package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

public final /* synthetic */ class SQLiteEventStore$$ExternalSyntheticLambda24 implements SQLiteEventStore.Function {
    public final /* synthetic */ long f$0;

    public /* synthetic */ SQLiteEventStore$$ExternalSyntheticLambda24(long j) {
        this.f$0 = j;
    }

    public final Object apply(Object obj) {
        return SQLiteEventStore.lambda$getTimeWindow$22(this.f$0, (SQLiteDatabase) obj);
    }
}
