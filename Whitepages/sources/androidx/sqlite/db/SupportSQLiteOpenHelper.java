package androidx.sqlite.db;

import android.content.Context;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public interface SupportSQLiteOpenHelper extends Closeable {

    public interface Factory {
        SupportSQLiteOpenHelper create(Configuration configuration);
    }

    void close();

    String getDatabaseName();

    SupportSQLiteDatabase getWritableDatabase();

    void setWriteAheadLoggingEnabled(boolean z);

    public static abstract class Callback {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        public final int version;

        public void onConfigure(SupportSQLiteDatabase supportSQLiteDatabase) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
        }

        public abstract void onCreate(SupportSQLiteDatabase supportSQLiteDatabase);

        public abstract void onDowngrade(SupportSQLiteDatabase supportSQLiteDatabase, int i, int i2);

        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
            Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
        }

        public abstract void onUpgrade(SupportSQLiteDatabase supportSQLiteDatabase, int i, int i2);

        public Callback(int i) {
            this.version = i;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x003e, code lost:
            if (r1 != null) goto L_0x0040;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0040, code lost:
            r4 = r1.iterator();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x004a, code lost:
            if (r4.hasNext() != false) goto L_0x004c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x004c, code lost:
            r1 = ((android.util.Pair) r4.next()).second;
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, "p.second");
            deleteDatabaseFile((java.lang.String) r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x005d, code lost:
            r4 = r4.getPath();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0061, code lost:
            if (r4 != null) goto L_0x0063;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0063, code lost:
            deleteDatabaseFile(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0066, code lost:
            throw r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0038, code lost:
            r2 = move-exception;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x003a */
        /* JADX WARNING: Removed duplicated region for block: B:22:0x0069  */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x0086  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0038 A[ExcHandler: all (r2v3 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r1 
          PHI: (r1v12 java.util.List) = (r1v4 java.util.List), (r1v5 java.util.List), (r1v5 java.util.List) binds: [B:6:0x0033, B:9:0x003a, B:10:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:6:0x0033] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onCorruption(androidx.sqlite.db.SupportSQLiteDatabase r4) {
            /*
                r3 = this;
                java.lang.String r0 = "p.second"
                java.lang.String r1 = "db"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r1)
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "Corruption reported by sqlite on database: "
                r1.append(r2)
                r1.append(r4)
                java.lang.String r2 = ".path"
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                java.lang.String r2 = "SupportSQLite"
                android.util.Log.e(r2, r1)
                boolean r1 = r4.isOpen()
                if (r1 != 0) goto L_0x0032
                java.lang.String r4 = r4.getPath()
                if (r4 == 0) goto L_0x0031
                r3.deleteDatabaseFile(r4)
            L_0x0031:
                return
            L_0x0032:
                r1 = 0
                java.util.List r1 = r4.getAttachedDbs()     // Catch:{ SQLiteException -> 0x003a, all -> 0x0038 }
                goto L_0x003a
            L_0x0038:
                r2 = move-exception
                goto L_0x003e
            L_0x003a:
                r4.close()     // Catch:{ IOException -> 0x0067, all -> 0x0038 }
                goto L_0x0067
            L_0x003e:
                if (r1 == 0) goto L_0x005d
                java.lang.Iterable r1 = (java.lang.Iterable) r1
                java.util.Iterator r4 = r1.iterator()
            L_0x0046:
                boolean r1 = r4.hasNext()
                if (r1 == 0) goto L_0x0066
                java.lang.Object r1 = r4.next()
                android.util.Pair r1 = (android.util.Pair) r1
                java.lang.Object r1 = r1.second
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)
                java.lang.String r1 = (java.lang.String) r1
                r3.deleteDatabaseFile(r1)
                goto L_0x0046
            L_0x005d:
                java.lang.String r4 = r4.getPath()
                if (r4 == 0) goto L_0x0066
                r3.deleteDatabaseFile(r4)
            L_0x0066:
                throw r2
            L_0x0067:
                if (r1 == 0) goto L_0x0086
                java.lang.Iterable r1 = (java.lang.Iterable) r1
                java.util.Iterator r4 = r1.iterator()
            L_0x006f:
                boolean r1 = r4.hasNext()
                if (r1 == 0) goto L_0x008f
                java.lang.Object r1 = r4.next()
                android.util.Pair r1 = (android.util.Pair) r1
                java.lang.Object r1 = r1.second
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)
                java.lang.String r1 = (java.lang.String) r1
                r3.deleteDatabaseFile(r1)
                goto L_0x006f
            L_0x0086:
                java.lang.String r4 = r4.getPath()
                if (r4 == 0) goto L_0x008f
                r3.deleteDatabaseFile(r4)
            L_0x008f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.sqlite.db.SupportSQLiteOpenHelper.Callback.onCorruption(androidx.sqlite.db.SupportSQLiteDatabase):void");
        }

        private final void deleteDatabaseFile(String str) {
            if (!StringsKt.equals(str, ":memory:", true)) {
                int length = str.length() - 1;
                int i = 0;
                boolean z = false;
                while (i <= length) {
                    boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i : length), 32) <= 0;
                    if (!z) {
                        if (!z2) {
                            z = true;
                        } else {
                            i++;
                        }
                    } else if (!z2) {
                        break;
                    } else {
                        length--;
                    }
                }
                if (str.subSequence(i, length + 1).toString().length() != 0) {
                    Log.w("SupportSQLite", "deleting the database file: " + str);
                    try {
                        SupportSQLiteCompat$Api16Impl.deleteDatabase(new File(str));
                    } catch (Exception e) {
                        Log.w("SupportSQLite", "delete failed: ", e);
                    }
                }
            }
        }

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }
    }

    public static final class Configuration {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        public final boolean allowDataLossOnRecovery;
        public final Callback callback;
        public final Context context;
        public final String name;
        public final boolean useNoBackupDirectory;

        public static final Builder builder(Context context2) {
            return Companion.builder(context2);
        }

        public Configuration(Context context2, String str, Callback callback2, boolean z, boolean z2) {
            Intrinsics.checkNotNullParameter(context2, "context");
            Intrinsics.checkNotNullParameter(callback2, "callback");
            this.context = context2;
            this.name = str;
            this.callback = callback2;
            this.useNoBackupDirectory = z;
            this.allowDataLossOnRecovery = z2;
        }

        public static class Builder {
            private boolean allowDataLossOnRecovery;
            private Callback callback;
            private final Context context;
            private String name;
            private boolean useNoBackupDirectory;

            public Builder(Context context2) {
                Intrinsics.checkNotNullParameter(context2, "context");
                this.context = context2;
            }

            public Configuration build() {
                String str;
                Callback callback2 = this.callback;
                if (callback2 == null) {
                    throw new IllegalArgumentException("Must set a callback to create the configuration.");
                } else if (!this.useNoBackupDirectory || ((str = this.name) != null && str.length() != 0)) {
                    return new Configuration(this.context, this.name, callback2, this.useNoBackupDirectory, this.allowDataLossOnRecovery);
                } else {
                    throw new IllegalArgumentException("Must set a non-null database name to a configuration that uses the no backup directory.");
                }
            }

            public Builder name(String str) {
                this.name = str;
                return this;
            }

            public Builder callback(Callback callback2) {
                Intrinsics.checkNotNullParameter(callback2, "callback");
                this.callback = callback2;
                return this;
            }

            public Builder noBackupDirectory(boolean z) {
                this.useNoBackupDirectory = z;
                return this;
            }

            public Builder allowDataLossOnRecovery(boolean z) {
                this.allowDataLossOnRecovery = z;
                return this;
            }
        }

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final Builder builder(Context context) {
                Intrinsics.checkNotNullParameter(context, "context");
                return new Builder(context);
            }
        }
    }
}
