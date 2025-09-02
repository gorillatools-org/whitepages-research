package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.firebase.transport.LogEventDropped;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

public final /* synthetic */ class SQLiteEventStore$$ExternalSyntheticLambda9 implements SQLiteEventStore.Function {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ LogEventDropped.Reason f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ SQLiteEventStore$$ExternalSyntheticLambda9(String str, LogEventDropped.Reason reason, long j) {
        this.f$0 = str;
        this.f$1 = reason;
        this.f$2 = j;
    }

    public final Object apply(Object obj) {
        return SQLiteEventStore.lambda$recordLogEventDropped$18(this.f$0, this.f$1, this.f$2, (SQLiteDatabase) obj);
    }
}
