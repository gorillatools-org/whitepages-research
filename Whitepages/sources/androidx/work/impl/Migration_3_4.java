package androidx.work.impl;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.jvm.internal.Intrinsics;

public final class Migration_3_4 extends Migration {
    public static final Migration_3_4 INSTANCE = new Migration_3_4();

    private Migration_3_4() {
        super(3, 4);
    }

    public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
        supportSQLiteDatabase.execSQL("\n    UPDATE workspec SET schedule_requested_at = 0\n    WHERE state NOT IN (2, 3, 5)\n        AND schedule_requested_at = -1\n        AND interval_duration <> 0\n    ");
    }
}
