package androidx.work.impl;

import android.content.Context;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.work.impl.WorkDatabase;

public final /* synthetic */ class WorkDatabase$Companion$$ExternalSyntheticLambda0 implements SupportSQLiteOpenHelper.Factory {
    public final /* synthetic */ Context f$0;

    public /* synthetic */ WorkDatabase$Companion$$ExternalSyntheticLambda0(Context context) {
        this.f$0 = context;
    }

    public final SupportSQLiteOpenHelper create(SupportSQLiteOpenHelper.Configuration configuration) {
        return WorkDatabase.Companion.create$lambda$0(this.f$0, configuration);
    }
}
