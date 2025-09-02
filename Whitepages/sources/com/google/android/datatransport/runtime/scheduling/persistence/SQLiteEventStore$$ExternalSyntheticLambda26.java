package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

public final /* synthetic */ class SQLiteEventStore$$ExternalSyntheticLambda26 implements SQLiteEventStore.Function {
    public final /* synthetic */ long f$0;

    public /* synthetic */ SQLiteEventStore$$ExternalSyntheticLambda26(long j) {
        this.f$0 = j;
    }

    public final Object apply(Object obj) {
        return ((Cursor) obj).moveToNext();
    }
}
