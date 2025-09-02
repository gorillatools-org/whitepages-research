package androidx.work.impl;

import android.content.Context;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.jvm.internal.Intrinsics;

public final class RescheduleMigration extends Migration {
    private final Context mContext;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RescheduleMigration(Context context, int i, int i2) {
        super(i, i2);
        Intrinsics.checkNotNullParameter(context, "mContext");
        this.mContext = context;
    }

    public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
        if (this.endVersion >= 10) {
            supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO `Preference` (`key`, `long_value`) VALUES (@key, @long_value)", new Object[]{"reschedule_needed", 1});
        } else {
            this.mContext.getSharedPreferences("androidx.work.util.preferences", 0).edit().putBoolean("reschedule_needed", true).apply();
        }
    }
}
