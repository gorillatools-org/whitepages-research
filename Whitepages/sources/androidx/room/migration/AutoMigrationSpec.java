package androidx.room.migration;

import androidx.sqlite.db.SupportSQLiteDatabase;

public interface AutoMigrationSpec {
    void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase);
}
