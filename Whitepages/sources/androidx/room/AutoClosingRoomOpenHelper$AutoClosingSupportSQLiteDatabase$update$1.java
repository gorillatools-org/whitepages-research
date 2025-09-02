package androidx.room;

import android.content.ContentValues;
import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

final class AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$update$1 extends Lambda implements Function1 {
    final /* synthetic */ int $conflictAlgorithm;
    final /* synthetic */ String $table;
    final /* synthetic */ ContentValues $values;
    final /* synthetic */ Object[] $whereArgs;
    final /* synthetic */ String $whereClause;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$update$1(String str, int i, ContentValues contentValues, String str2, Object[] objArr) {
        super(1);
        this.$table = str;
        this.$conflictAlgorithm = i;
        this.$values = contentValues;
        this.$whereClause = str2;
        this.$whereArgs = objArr;
    }

    public final Integer invoke(SupportSQLiteDatabase supportSQLiteDatabase) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
        return Integer.valueOf(supportSQLiteDatabase.update(this.$table, this.$conflictAlgorithm, this.$values, this.$whereClause, this.$whereArgs));
    }
}
