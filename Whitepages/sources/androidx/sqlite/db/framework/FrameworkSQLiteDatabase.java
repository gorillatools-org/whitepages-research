package androidx.sqlite.db.framework;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteStatement;
import android.os.CancellationSignal;
import android.text.TextUtils;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteCompat$Api16Impl;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.google.android.gms.actions.SearchIntents;
import java.util.List;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class FrameworkSQLiteDatabase implements SupportSQLiteDatabase {
    private static final String[] CONFLICT_VALUES = {"", " OR ROLLBACK ", " OR ABORT ", " OR FAIL ", " OR IGNORE ", " OR REPLACE "};
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    private final List attachedDbs;
    private final SQLiteDatabase delegate;

    public FrameworkSQLiteDatabase(SQLiteDatabase sQLiteDatabase) {
        Intrinsics.checkNotNullParameter(sQLiteDatabase, "delegate");
        this.delegate = sQLiteDatabase;
        this.attachedDbs = sQLiteDatabase.getAttachedDbs();
    }

    public SupportSQLiteStatement compileStatement(String str) {
        Intrinsics.checkNotNullParameter(str, "sql");
        SQLiteStatement compileStatement = this.delegate.compileStatement(str);
        Intrinsics.checkNotNullExpressionValue(compileStatement, "delegate.compileStatement(sql)");
        return new FrameworkSQLiteStatement(compileStatement);
    }

    public void beginTransaction() {
        this.delegate.beginTransaction();
    }

    public void beginTransactionNonExclusive() {
        this.delegate.beginTransactionNonExclusive();
    }

    public void endTransaction() {
        this.delegate.endTransaction();
    }

    public void setTransactionSuccessful() {
        this.delegate.setTransactionSuccessful();
    }

    public boolean inTransaction() {
        return this.delegate.inTransaction();
    }

    public Cursor query(String str) {
        Intrinsics.checkNotNullParameter(str, SearchIntents.EXTRA_QUERY);
        return query((SupportSQLiteQuery) new SimpleSQLiteQuery(str));
    }

    public Cursor query(SupportSQLiteQuery supportSQLiteQuery) {
        Intrinsics.checkNotNullParameter(supportSQLiteQuery, SearchIntents.EXTRA_QUERY);
        Cursor rawQueryWithFactory = this.delegate.rawQueryWithFactory(new FrameworkSQLiteDatabase$$ExternalSyntheticLambda1(new FrameworkSQLiteDatabase$query$cursorFactory$1(supportSQLiteQuery)), supportSQLiteQuery.getSql(), EMPTY_STRING_ARRAY, (String) null);
        Intrinsics.checkNotNullExpressionValue(rawQueryWithFactory, "delegate.rawQueryWithFac…EMPTY_STRING_ARRAY, null)");
        return rawQueryWithFactory;
    }

    /* access modifiers changed from: private */
    public static final Cursor query$lambda$0(Function4 function4, SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
        Intrinsics.checkNotNullParameter(function4, "$tmp0");
        return (Cursor) function4.invoke(sQLiteDatabase, sQLiteCursorDriver, str, sQLiteQuery);
    }

    public Cursor query(SupportSQLiteQuery supportSQLiteQuery, CancellationSignal cancellationSignal) {
        Intrinsics.checkNotNullParameter(supportSQLiteQuery, SearchIntents.EXTRA_QUERY);
        SQLiteDatabase sQLiteDatabase = this.delegate;
        String sql = supportSQLiteQuery.getSql();
        String[] strArr = EMPTY_STRING_ARRAY;
        Intrinsics.checkNotNull(cancellationSignal);
        return SupportSQLiteCompat$Api16Impl.rawQueryWithFactory(sQLiteDatabase, sql, strArr, (String) null, cancellationSignal, new FrameworkSQLiteDatabase$$ExternalSyntheticLambda0(supportSQLiteQuery));
    }

    /* access modifiers changed from: private */
    public static final Cursor query$lambda$1(SupportSQLiteQuery supportSQLiteQuery, SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
        Intrinsics.checkNotNullParameter(supportSQLiteQuery, "$query");
        Intrinsics.checkNotNull(sQLiteQuery);
        supportSQLiteQuery.bindTo(new FrameworkSQLiteProgram(sQLiteQuery));
        return new SQLiteCursor(sQLiteCursorDriver, str, sQLiteQuery);
    }

    public int update(String str, int i, ContentValues contentValues, String str2, Object[] objArr) {
        int i2;
        Intrinsics.checkNotNullParameter(str, "table");
        Intrinsics.checkNotNullParameter(contentValues, "values");
        if (contentValues.size() != 0) {
            int size = contentValues.size();
            if (objArr == null) {
                i2 = size;
            } else {
                i2 = objArr.length + size;
            }
            Object[] objArr2 = new Object[i2];
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ");
            sb.append(CONFLICT_VALUES[i]);
            sb.append(str);
            sb.append(" SET ");
            int i3 = 0;
            for (String next : contentValues.keySet()) {
                sb.append(i3 > 0 ? "," : "");
                sb.append(next);
                objArr2[i3] = contentValues.get(next);
                sb.append("=?");
                i3++;
            }
            if (objArr != null) {
                for (int i4 = size; i4 < i2; i4++) {
                    objArr2[i4] = objArr[i4 - size];
                }
            }
            if (!TextUtils.isEmpty(str2)) {
                sb.append(" WHERE ");
                sb.append(str2);
            }
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
            SupportSQLiteStatement compileStatement = compileStatement(sb2);
            SimpleSQLiteQuery.Companion.bind(compileStatement, objArr2);
            return compileStatement.executeUpdateDelete();
        }
        throw new IllegalArgumentException("Empty values");
    }

    public void execSQL(String str) {
        Intrinsics.checkNotNullParameter(str, "sql");
        this.delegate.execSQL(str);
    }

    public void execSQL(String str, Object[] objArr) {
        Intrinsics.checkNotNullParameter(str, "sql");
        Intrinsics.checkNotNullParameter(objArr, "bindArgs");
        this.delegate.execSQL(str, objArr);
    }

    public boolean isOpen() {
        return this.delegate.isOpen();
    }

    public String getPath() {
        return this.delegate.getPath();
    }

    public boolean isWriteAheadLoggingEnabled() {
        return SupportSQLiteCompat$Api16Impl.isWriteAheadLoggingEnabled(this.delegate);
    }

    public List getAttachedDbs() {
        return this.attachedDbs;
    }

    public void close() {
        this.delegate.close();
    }

    public final boolean isDelegate(SQLiteDatabase sQLiteDatabase) {
        Intrinsics.checkNotNullParameter(sQLiteDatabase, "sqLiteDatabase");
        return Intrinsics.areEqual((Object) this.delegate, (Object) sQLiteDatabase);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
