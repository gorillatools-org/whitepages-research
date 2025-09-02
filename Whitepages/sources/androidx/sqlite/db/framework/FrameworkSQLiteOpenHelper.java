package androidx.sqlite.db.framework;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteCompat$Api16Impl;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.util.ProcessLock;
import java.io.File;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class FrameworkSQLiteOpenHelper implements SupportSQLiteOpenHelper {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public final boolean allowDataLossOnRecovery;
    /* access modifiers changed from: private */
    public final SupportSQLiteOpenHelper.Callback callback;
    /* access modifiers changed from: private */
    public final Context context;
    private final Lazy lazyDelegate = LazyKt.lazy(new FrameworkSQLiteOpenHelper$lazyDelegate$1(this));
    /* access modifiers changed from: private */
    public final String name;
    /* access modifiers changed from: private */
    public final boolean useNoBackupDirectory;
    /* access modifiers changed from: private */
    public boolean writeAheadLoggingEnabled;

    public FrameworkSQLiteOpenHelper(Context context2, String str, SupportSQLiteOpenHelper.Callback callback2, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(callback2, "callback");
        this.context = context2;
        this.name = str;
        this.callback = callback2;
        this.useNoBackupDirectory = z;
        this.allowDataLossOnRecovery = z2;
    }

    private final OpenHelper getDelegate() {
        return (OpenHelper) this.lazyDelegate.getValue();
    }

    public String getDatabaseName() {
        return this.name;
    }

    public void setWriteAheadLoggingEnabled(boolean z) {
        if (this.lazyDelegate.isInitialized()) {
            SupportSQLiteCompat$Api16Impl.setWriteAheadLoggingEnabled(getDelegate(), z);
        }
        this.writeAheadLoggingEnabled = z;
    }

    public SupportSQLiteDatabase getWritableDatabase() {
        return getDelegate().getSupportDatabase(true);
    }

    public void close() {
        if (this.lazyDelegate.isInitialized()) {
            getDelegate().close();
        }
    }

    private static final class OpenHelper extends SQLiteOpenHelper {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final boolean allowDataLossOnRecovery;
        private final SupportSQLiteOpenHelper.Callback callback;
        private final Context context;
        private final DBRefHolder dbRef;
        private final ProcessLock lock;
        private boolean migrated;
        private boolean opened;

        public enum CallbackName {
            ON_CONFIGURE,
            ON_CREATE,
            ON_UPGRADE,
            ON_DOWNGRADE,
            ON_OPEN
        }

        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            /* JADX WARNING: Can't wrap try/catch for region: R(13:0|1|2|3|4|5|6|7|8|9|10|11|13) */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
            static {
                /*
                    androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper$CallbackName[] r0 = androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.CallbackName.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper$CallbackName r1 = androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.CallbackName.ON_CONFIGURE     // Catch:{ NoSuchFieldError -> 0x0010 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                    r2 = 1
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                L_0x0010:
                    androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper$CallbackName r1 = androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.CallbackName.ON_CREATE     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r2 = 2
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper$CallbackName r1 = androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.CallbackName.ON_UPGRADE     // Catch:{ NoSuchFieldError -> 0x0022 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                    r2 = 3
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
                L_0x0022:
                    androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper$CallbackName r1 = androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.CallbackName.ON_DOWNGRADE     // Catch:{ NoSuchFieldError -> 0x002b }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                    r2 = 4
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
                L_0x002b:
                    androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper$CallbackName r1 = androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.CallbackName.ON_OPEN     // Catch:{ NoSuchFieldError -> 0x0034 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                    r2 = 5
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
                L_0x0034:
                    $EnumSwitchMapping$0 = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.WhenMappings.<clinit>():void");
            }
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public OpenHelper(Context context2, String str, DBRefHolder dBRefHolder, SupportSQLiteOpenHelper.Callback callback2, boolean z) {
            super(context2, str, (SQLiteDatabase.CursorFactory) null, callback2.version, new FrameworkSQLiteOpenHelper$OpenHelper$$ExternalSyntheticLambda0(callback2, dBRefHolder));
            Intrinsics.checkNotNullParameter(context2, "context");
            Intrinsics.checkNotNullParameter(dBRefHolder, "dbRef");
            Intrinsics.checkNotNullParameter(callback2, "callback");
            this.context = context2;
            this.dbRef = dBRefHolder;
            this.callback = callback2;
            this.allowDataLossOnRecovery = z;
            if (str == null) {
                str = UUID.randomUUID().toString();
                Intrinsics.checkNotNullExpressionValue(str, "randomUUID().toString()");
            }
            File cacheDir = context2.getCacheDir();
            Intrinsics.checkNotNullExpressionValue(cacheDir, "context.cacheDir");
            this.lock = new ProcessLock(str, cacheDir, false);
        }

        /* access modifiers changed from: private */
        public static final void _init_$lambda$0(SupportSQLiteOpenHelper.Callback callback2, DBRefHolder dBRefHolder, SQLiteDatabase sQLiteDatabase) {
            Intrinsics.checkNotNullParameter(callback2, "$callback");
            Intrinsics.checkNotNullParameter(dBRefHolder, "$dbRef");
            Companion companion = Companion;
            Intrinsics.checkNotNullExpressionValue(sQLiteDatabase, "dbObj");
            callback2.onCorruption(companion.getWrappedDb(dBRefHolder, sQLiteDatabase));
        }

        public final SupportSQLiteDatabase getSupportDatabase(boolean z) {
            try {
                this.lock.lock(!this.opened && getDatabaseName() != null);
                this.migrated = false;
                SQLiteDatabase innerGetDatabase = innerGetDatabase(z);
                if (this.migrated) {
                    close();
                    SupportSQLiteDatabase supportDatabase = getSupportDatabase(z);
                    this.lock.unlock();
                    return supportDatabase;
                }
                FrameworkSQLiteDatabase wrappedDb = getWrappedDb(innerGetDatabase);
                this.lock.unlock();
                return wrappedDb;
            } catch (Throwable th) {
                this.lock.unlock();
                throw th;
            }
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(7:11|10|12|13|14|15|16) */
        /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x003e */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private final android.database.sqlite.SQLiteDatabase innerGetDatabase(boolean r5) {
            /*
                r4 = this;
                java.lang.String r0 = r4.getDatabaseName()
                if (r0 == 0) goto L_0x0031
                android.content.Context r1 = r4.context
                java.io.File r1 = r1.getDatabasePath(r0)
                java.io.File r1 = r1.getParentFile()
                if (r1 == 0) goto L_0x0031
                r1.mkdirs()
                boolean r2 = r1.isDirectory()
                if (r2 != 0) goto L_0x0031
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = "Invalid database parent file, not a directory: "
                r2.append(r3)
                r2.append(r1)
                java.lang.String r1 = r2.toString()
                java.lang.String r2 = "SupportSQLite"
                android.util.Log.w(r2, r1)
            L_0x0031:
                android.database.sqlite.SQLiteDatabase r5 = r4.getWritableOrReadableDatabase(r5)     // Catch:{ all -> 0x0036 }
                return r5
            L_0x0036:
                super.close()
                r1 = 500(0x1f4, double:2.47E-321)
                java.lang.Thread.sleep(r1)     // Catch:{ InterruptedException -> 0x003e }
            L_0x003e:
                android.database.sqlite.SQLiteDatabase r5 = r4.getWritableOrReadableDatabase(r5)     // Catch:{ all -> 0x0043 }
                return r5
            L_0x0043:
                r1 = move-exception
                super.close()
                boolean r2 = r1 instanceof androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.CallbackException
                if (r2 == 0) goto L_0x0070
                androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper$CallbackException r1 = (androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.CallbackException) r1
                java.lang.Throwable r2 = r1.getCause()
                androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper$CallbackName r1 = r1.getCallbackName()
                int[] r3 = androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.WhenMappings.$EnumSwitchMapping$0
                int r1 = r1.ordinal()
                r1 = r3[r1]
                r3 = 1
                if (r1 == r3) goto L_0x006f
                r3 = 2
                if (r1 == r3) goto L_0x006f
                r3 = 3
                if (r1 == r3) goto L_0x006f
                r3 = 4
                if (r1 == r3) goto L_0x006f
                boolean r1 = r2 instanceof android.database.sqlite.SQLiteException
                if (r1 == 0) goto L_0x006e
                goto L_0x007a
            L_0x006e:
                throw r2
            L_0x006f:
                throw r2
            L_0x0070:
                boolean r2 = r1 instanceof android.database.sqlite.SQLiteException
                if (r2 == 0) goto L_0x008b
                if (r0 == 0) goto L_0x008a
                boolean r2 = r4.allowDataLossOnRecovery
                if (r2 == 0) goto L_0x008a
            L_0x007a:
                android.content.Context r1 = r4.context
                r1.deleteDatabase(r0)
                android.database.sqlite.SQLiteDatabase r5 = r4.getWritableOrReadableDatabase(r5)     // Catch:{ CallbackException -> 0x0084 }
                return r5
            L_0x0084:
                r5 = move-exception
                java.lang.Throwable r5 = r5.getCause()
                throw r5
            L_0x008a:
                throw r1
            L_0x008b:
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper.OpenHelper.innerGetDatabase(boolean):android.database.sqlite.SQLiteDatabase");
        }

        private final SQLiteDatabase getWritableOrReadableDatabase(boolean z) {
            if (z) {
                SQLiteDatabase writableDatabase = super.getWritableDatabase();
                Intrinsics.checkNotNullExpressionValue(writableDatabase, "{\n                super.…eDatabase()\n            }");
                return writableDatabase;
            }
            SQLiteDatabase readableDatabase = super.getReadableDatabase();
            Intrinsics.checkNotNullExpressionValue(readableDatabase, "{\n                super.…eDatabase()\n            }");
            return readableDatabase;
        }

        public final FrameworkSQLiteDatabase getWrappedDb(SQLiteDatabase sQLiteDatabase) {
            Intrinsics.checkNotNullParameter(sQLiteDatabase, "sqLiteDatabase");
            return Companion.getWrappedDb(this.dbRef, sQLiteDatabase);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            Intrinsics.checkNotNullParameter(sQLiteDatabase, "sqLiteDatabase");
            try {
                this.callback.onCreate(getWrappedDb(sQLiteDatabase));
            } catch (Throwable th) {
                throw new CallbackException(CallbackName.ON_CREATE, th);
            }
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            Intrinsics.checkNotNullParameter(sQLiteDatabase, "sqLiteDatabase");
            this.migrated = true;
            try {
                this.callback.onUpgrade(getWrappedDb(sQLiteDatabase), i, i2);
            } catch (Throwable th) {
                throw new CallbackException(CallbackName.ON_UPGRADE, th);
            }
        }

        public void onConfigure(SQLiteDatabase sQLiteDatabase) {
            Intrinsics.checkNotNullParameter(sQLiteDatabase, "db");
            try {
                this.callback.onConfigure(getWrappedDb(sQLiteDatabase));
            } catch (Throwable th) {
                throw new CallbackException(CallbackName.ON_CONFIGURE, th);
            }
        }

        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            Intrinsics.checkNotNullParameter(sQLiteDatabase, "db");
            this.migrated = true;
            try {
                this.callback.onDowngrade(getWrappedDb(sQLiteDatabase), i, i2);
            } catch (Throwable th) {
                throw new CallbackException(CallbackName.ON_DOWNGRADE, th);
            }
        }

        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            Intrinsics.checkNotNullParameter(sQLiteDatabase, "db");
            if (!this.migrated) {
                try {
                    this.callback.onOpen(getWrappedDb(sQLiteDatabase));
                } catch (Throwable th) {
                    throw new CallbackException(CallbackName.ON_OPEN, th);
                }
            }
            this.opened = true;
        }

        public void close() {
            try {
                ProcessLock.lock$default(this.lock, false, 1, (Object) null);
                super.close();
                this.dbRef.setDb((FrameworkSQLiteDatabase) null);
                this.opened = false;
            } finally {
                this.lock.unlock();
            }
        }

        private static final class CallbackException extends RuntimeException {
            private final CallbackName callbackName;
            private final Throwable cause;

            public final CallbackName getCallbackName() {
                return this.callbackName;
            }

            public Throwable getCause() {
                return this.cause;
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public CallbackException(CallbackName callbackName2, Throwable th) {
                super(th);
                Intrinsics.checkNotNullParameter(callbackName2, "callbackName");
                Intrinsics.checkNotNullParameter(th, "cause");
                this.callbackName = callbackName2;
                this.cause = th;
            }
        }

        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final FrameworkSQLiteDatabase getWrappedDb(DBRefHolder dBRefHolder, SQLiteDatabase sQLiteDatabase) {
                Intrinsics.checkNotNullParameter(dBRefHolder, "refHolder");
                Intrinsics.checkNotNullParameter(sQLiteDatabase, "sqLiteDatabase");
                FrameworkSQLiteDatabase db = dBRefHolder.getDb();
                if (db != null && db.isDelegate(sQLiteDatabase)) {
                    return db;
                }
                FrameworkSQLiteDatabase frameworkSQLiteDatabase = new FrameworkSQLiteDatabase(sQLiteDatabase);
                dBRefHolder.setDb(frameworkSQLiteDatabase);
                return frameworkSQLiteDatabase;
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

    private static final class DBRefHolder {
        private FrameworkSQLiteDatabase db;

        public DBRefHolder(FrameworkSQLiteDatabase frameworkSQLiteDatabase) {
            this.db = frameworkSQLiteDatabase;
        }

        public final FrameworkSQLiteDatabase getDb() {
            return this.db;
        }

        public final void setDb(FrameworkSQLiteDatabase frameworkSQLiteDatabase) {
            this.db = frameworkSQLiteDatabase;
        }
    }
}
