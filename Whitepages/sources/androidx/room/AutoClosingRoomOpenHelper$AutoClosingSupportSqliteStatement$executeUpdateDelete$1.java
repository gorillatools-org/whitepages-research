package androidx.room;

import androidx.sqlite.db.SupportSQLiteStatement;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeUpdateDelete$1 extends Lambda implements Function1 {
    public static final AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeUpdateDelete$1 INSTANCE = new AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeUpdateDelete$1();

    AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeUpdateDelete$1() {
        super(1);
    }

    public final Integer invoke(SupportSQLiteStatement supportSQLiteStatement) {
        Intrinsics.checkNotNullParameter(supportSQLiteStatement, "obj");
        return Integer.valueOf(supportSQLiteStatement.executeUpdateDelete());
    }
}
