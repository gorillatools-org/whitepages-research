package androidx.sqlite.db.framework;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import androidx.sqlite.db.SupportSQLiteQuery;

public final /* synthetic */ class FrameworkSQLiteDatabase$$ExternalSyntheticLambda0 implements SQLiteDatabase.CursorFactory {
    public final /* synthetic */ SupportSQLiteQuery f$0;

    public /* synthetic */ FrameworkSQLiteDatabase$$ExternalSyntheticLambda0(SupportSQLiteQuery supportSQLiteQuery) {
        this.f$0 = supportSQLiteQuery;
    }

    public final Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
        return FrameworkSQLiteDatabase.query$lambda$1(this.f$0, sQLiteDatabase, sQLiteCursorDriver, str, sQLiteQuery);
    }
}
