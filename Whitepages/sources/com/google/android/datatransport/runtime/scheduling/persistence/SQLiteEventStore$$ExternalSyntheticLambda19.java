package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

public final /* synthetic */ class SQLiteEventStore$$ExternalSyntheticLambda19 implements SQLiteEventStore.Function {
    public final /* synthetic */ SQLiteEventStore f$0;
    public final /* synthetic */ EventInternal f$1;
    public final /* synthetic */ TransportContext f$2;

    public /* synthetic */ SQLiteEventStore$$ExternalSyntheticLambda19(SQLiteEventStore sQLiteEventStore, EventInternal eventInternal, TransportContext transportContext) {
        this.f$0 = sQLiteEventStore;
        this.f$1 = eventInternal;
        this.f$2 = transportContext;
    }

    public final Object apply(Object obj) {
        return this.f$0.lambda$persist$1(this.f$1, this.f$2, (SQLiteDatabase) obj);
    }
}
