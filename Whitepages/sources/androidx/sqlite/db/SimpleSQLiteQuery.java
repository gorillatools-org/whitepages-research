package androidx.sqlite.db;

import com.google.android.gms.actions.SearchIntents;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SimpleSQLiteQuery implements SupportSQLiteQuery {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Object[] bindArgs;
    private final String query;

    public SimpleSQLiteQuery(String str, Object[] objArr) {
        Intrinsics.checkNotNullParameter(str, SearchIntents.EXTRA_QUERY);
        this.query = str;
        this.bindArgs = objArr;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SimpleSQLiteQuery(String str) {
        this(str, (Object[]) null);
        Intrinsics.checkNotNullParameter(str, SearchIntents.EXTRA_QUERY);
    }

    public String getSql() {
        return this.query;
    }

    public void bindTo(SupportSQLiteProgram supportSQLiteProgram) {
        Intrinsics.checkNotNullParameter(supportSQLiteProgram, "statement");
        Companion.bind(supportSQLiteProgram, this.bindArgs);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void bind(SupportSQLiteProgram supportSQLiteProgram, Object[] objArr) {
            Intrinsics.checkNotNullParameter(supportSQLiteProgram, "statement");
            if (objArr != null) {
                int length = objArr.length;
                int i = 0;
                while (i < length) {
                    Object obj = objArr[i];
                    i++;
                    bind(supportSQLiteProgram, i, obj);
                }
            }
        }

        private final void bind(SupportSQLiteProgram supportSQLiteProgram, int i, Object obj) {
            if (obj == null) {
                supportSQLiteProgram.bindNull(i);
            } else if (obj instanceof byte[]) {
                supportSQLiteProgram.bindBlob(i, (byte[]) obj);
            } else if (obj instanceof Float) {
                supportSQLiteProgram.bindDouble(i, (double) ((Number) obj).floatValue());
            } else if (obj instanceof Double) {
                supportSQLiteProgram.bindDouble(i, ((Number) obj).doubleValue());
            } else if (obj instanceof Long) {
                supportSQLiteProgram.bindLong(i, ((Number) obj).longValue());
            } else if (obj instanceof Integer) {
                supportSQLiteProgram.bindLong(i, (long) ((Number) obj).intValue());
            } else if (obj instanceof Short) {
                supportSQLiteProgram.bindLong(i, (long) ((Number) obj).shortValue());
            } else if (obj instanceof Byte) {
                supportSQLiteProgram.bindLong(i, (long) ((Number) obj).byteValue());
            } else if (obj instanceof String) {
                supportSQLiteProgram.bindString(i, (String) obj);
            } else if (obj instanceof Boolean) {
                supportSQLiteProgram.bindLong(i, ((Boolean) obj).booleanValue() ? 1 : 0);
            } else {
                throw new IllegalArgumentException("Cannot bind " + obj + " at index " + i + " Supported types: Null, ByteArray, Float, Double, Long, Int, Short, Byte, String");
            }
        }
    }
}
