package androidx.sqlite.db.framework;

import android.database.sqlite.SQLiteProgram;
import androidx.sqlite.db.SupportSQLiteProgram;
import kotlin.jvm.internal.Intrinsics;

public class FrameworkSQLiteProgram implements SupportSQLiteProgram {
    private final SQLiteProgram delegate;

    public FrameworkSQLiteProgram(SQLiteProgram sQLiteProgram) {
        Intrinsics.checkNotNullParameter(sQLiteProgram, "delegate");
        this.delegate = sQLiteProgram;
    }

    public void bindNull(int i) {
        this.delegate.bindNull(i);
    }

    public void bindLong(int i, long j) {
        this.delegate.bindLong(i, j);
    }

    public void bindDouble(int i, double d) {
        this.delegate.bindDouble(i, d);
    }

    public void bindString(int i, String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        this.delegate.bindString(i, str);
    }

    public void bindBlob(int i, byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "value");
        this.delegate.bindBlob(i, bArr);
    }

    public void close() {
        this.delegate.close();
    }
}
