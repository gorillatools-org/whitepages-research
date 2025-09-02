package androidx.room;

import androidx.sqlite.db.SupportSQLiteStatement;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeInsert$1 extends Lambda implements Function1 {
    public static final AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeInsert$1 INSTANCE = new AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeInsert$1();

    AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeInsert$1() {
        super(1);
    }

    public final Long invoke(SupportSQLiteStatement supportSQLiteStatement) {
        Intrinsics.checkNotNullParameter(supportSQLiteStatement, "obj");
        return Long.valueOf(supportSQLiteStatement.executeInsert());
    }
}
