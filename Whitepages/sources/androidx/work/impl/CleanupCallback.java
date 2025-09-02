package androidx.work.impl;

import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.jvm.internal.Intrinsics;

public final class CleanupCallback extends RoomDatabase.Callback {
    public static final CleanupCallback INSTANCE = new CleanupCallback();

    private CleanupCallback() {
    }

    private final String getPruneSQL() {
        return "DELETE FROM workspec WHERE state IN (2, 3, 5) AND (last_enqueue_time + minimum_retention_duration) < " + getPruneDate() + " AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))";
    }

    public final long getPruneDate() {
        return System.currentTimeMillis() - WorkDatabaseKt.PRUNE_THRESHOLD_MILLIS;
    }

    public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
        super.onOpen(supportSQLiteDatabase);
        supportSQLiteDatabase.beginTransaction();
        try {
            supportSQLiteDatabase.execSQL(getPruneSQL());
            supportSQLiteDatabase.setTransactionSuccessful();
        } finally {
            supportSQLiteDatabase.endTransaction();
        }
    }
}
