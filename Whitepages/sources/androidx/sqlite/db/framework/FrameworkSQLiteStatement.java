package androidx.sqlite.db.framework;

import android.database.sqlite.SQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import kotlin.jvm.internal.Intrinsics;

public final class FrameworkSQLiteStatement extends FrameworkSQLiteProgram implements SupportSQLiteStatement {
    private final SQLiteStatement delegate;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FrameworkSQLiteStatement(SQLiteStatement sQLiteStatement) {
        super(sQLiteStatement);
        Intrinsics.checkNotNullParameter(sQLiteStatement, "delegate");
        this.delegate = sQLiteStatement;
    }

    public int executeUpdateDelete() {
        return this.delegate.executeUpdateDelete();
    }

    public long executeInsert() {
        return this.delegate.executeInsert();
    }
}
