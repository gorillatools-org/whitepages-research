package androidx.work.impl.utils;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.Preference;
import kotlin.jvm.internal.Intrinsics;

public abstract class IdGeneratorKt {
    /* access modifiers changed from: private */
    public static final int nextId(WorkDatabase workDatabase, String str) {
        Long longValue = workDatabase.preferenceDao().getLongValue(str);
        int i = 0;
        int longValue2 = longValue != null ? (int) longValue.longValue() : 0;
        if (longValue2 != Integer.MAX_VALUE) {
            i = longValue2 + 1;
        }
        updatePreference(workDatabase, str, i);
        return longValue2;
    }

    /* access modifiers changed from: private */
    public static final void updatePreference(WorkDatabase workDatabase, String str, int i) {
        workDatabase.preferenceDao().insertPreference(new Preference(str, Long.valueOf((long) i)));
    }

    public static final void migrateLegacyIdGenerator(Context context, SupportSQLiteDatabase supportSQLiteDatabase) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "sqLiteDatabase");
        SharedPreferences sharedPreferences = context.getSharedPreferences("androidx.work.util.id", 0);
        if (sharedPreferences.contains("next_job_scheduler_id") || sharedPreferences.contains("next_job_scheduler_id")) {
            int i = sharedPreferences.getInt("next_job_scheduler_id", 0);
            int i2 = sharedPreferences.getInt("next_alarm_manager_id", 0);
            supportSQLiteDatabase.beginTransaction();
            try {
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO `Preference` (`key`, `long_value`) VALUES (@key, @long_value)", new Object[]{"next_job_scheduler_id", Integer.valueOf(i)});
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO `Preference` (`key`, `long_value`) VALUES (@key, @long_value)", new Object[]{"next_alarm_manager_id", Integer.valueOf(i2)});
                sharedPreferences.edit().clear().apply();
                supportSQLiteDatabase.setTransactionSuccessful();
            } finally {
                supportSQLiteDatabase.endTransaction();
            }
        }
    }
}
