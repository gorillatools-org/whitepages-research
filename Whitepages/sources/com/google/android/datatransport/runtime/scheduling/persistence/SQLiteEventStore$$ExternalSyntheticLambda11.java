package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.firebase.transport.ClientMetrics;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;
import java.util.Map;

public final /* synthetic */ class SQLiteEventStore$$ExternalSyntheticLambda11 implements SQLiteEventStore.Function {
    public final /* synthetic */ SQLiteEventStore f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Map f$2;
    public final /* synthetic */ ClientMetrics.Builder f$3;

    public /* synthetic */ SQLiteEventStore$$ExternalSyntheticLambda11(SQLiteEventStore sQLiteEventStore, String str, Map map, ClientMetrics.Builder builder) {
        this.f$0 = sQLiteEventStore;
        this.f$1 = str;
        this.f$2 = map;
        this.f$3 = builder;
    }

    public final Object apply(Object obj) {
        return this.f$0.lambda$loadClientMetrics$20(this.f$1, this.f$2, this.f$3, (SQLiteDatabase) obj);
    }
}
