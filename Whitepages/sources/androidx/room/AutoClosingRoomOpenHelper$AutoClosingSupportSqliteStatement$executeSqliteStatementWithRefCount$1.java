package androidx.room;

import androidx.room.AutoClosingRoomOpenHelper;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteStatement;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeSqliteStatementWithRefCount$1 extends Lambda implements Function1 {
    final /* synthetic */ Function1 $block;
    final /* synthetic */ AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement$executeSqliteStatementWithRefCount$1(AutoClosingRoomOpenHelper.AutoClosingSupportSqliteStatement autoClosingSupportSqliteStatement, Function1 function1) {
        super(1);
        this.this$0 = autoClosingSupportSqliteStatement;
        this.$block = function1;
    }

    public final Object invoke(SupportSQLiteDatabase supportSQLiteDatabase) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
        SupportSQLiteStatement compileStatement = supportSQLiteDatabase.compileStatement(this.this$0.sql);
        this.this$0.doBinds(compileStatement);
        return this.$block.invoke(compileStatement);
    }
}
