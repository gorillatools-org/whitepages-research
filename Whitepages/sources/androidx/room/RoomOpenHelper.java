package androidx.room;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public class RoomOpenHelper extends SupportSQLiteOpenHelper.Callback {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private DatabaseConfiguration configuration;
    private final Delegate delegate;
    private final String identityHash;
    private final String legacyHash;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RoomOpenHelper(DatabaseConfiguration databaseConfiguration, Delegate delegate2, String str, String str2) {
        super(delegate2.version);
        Intrinsics.checkNotNullParameter(databaseConfiguration, "configuration");
        Intrinsics.checkNotNullParameter(delegate2, "delegate");
        Intrinsics.checkNotNullParameter(str, "identityHash");
        Intrinsics.checkNotNullParameter(str2, "legacyHash");
        this.configuration = databaseConfiguration;
        this.delegate = delegate2;
        this.identityHash = str;
        this.legacyHash = str2;
    }

    public void onConfigure(SupportSQLiteDatabase supportSQLiteDatabase) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
        super.onConfigure(supportSQLiteDatabase);
    }

    public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
        boolean hasEmptySchema$room_runtime_release = Companion.hasEmptySchema$room_runtime_release(supportSQLiteDatabase);
        this.delegate.createAllTables(supportSQLiteDatabase);
        if (!hasEmptySchema$room_runtime_release) {
            ValidationResult onValidateSchema = this.delegate.onValidateSchema(supportSQLiteDatabase);
            if (!onValidateSchema.isValid) {
                throw new IllegalStateException("Pre-packaged database has an invalid schema: " + onValidateSchema.expectedFoundMsg);
            }
        }
        updateIdentity(supportSQLiteDatabase);
        this.delegate.onCreate(supportSQLiteDatabase);
    }

    public void onUpgrade(SupportSQLiteDatabase supportSQLiteDatabase, int i, int i2) {
        List<Migration> findMigrationPath;
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
        DatabaseConfiguration databaseConfiguration = this.configuration;
        if (databaseConfiguration == null || (findMigrationPath = databaseConfiguration.migrationContainer.findMigrationPath(i, i2)) == null) {
            DatabaseConfiguration databaseConfiguration2 = this.configuration;
            if (databaseConfiguration2 == null || databaseConfiguration2.isMigrationRequired(i, i2)) {
                throw new IllegalStateException("A migration from " + i + " to " + i2 + " was required but not found. Please provide the necessary Migration path via RoomDatabase.Builder.addMigration(Migration ...) or allow for destructive migrations via one of the RoomDatabase.Builder.fallbackToDestructiveMigration* methods.");
            }
            this.delegate.dropAllTables(supportSQLiteDatabase);
            this.delegate.createAllTables(supportSQLiteDatabase);
            return;
        }
        this.delegate.onPreMigrate(supportSQLiteDatabase);
        for (Migration migrate : findMigrationPath) {
            migrate.migrate(supportSQLiteDatabase);
        }
        ValidationResult onValidateSchema = this.delegate.onValidateSchema(supportSQLiteDatabase);
        if (onValidateSchema.isValid) {
            this.delegate.onPostMigrate(supportSQLiteDatabase);
            updateIdentity(supportSQLiteDatabase);
            return;
        }
        throw new IllegalStateException("Migration didn't properly handle: " + onValidateSchema.expectedFoundMsg);
    }

    public void onDowngrade(SupportSQLiteDatabase supportSQLiteDatabase, int i, int i2) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
        onUpgrade(supportSQLiteDatabase, i, i2);
    }

    public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
        super.onOpen(supportSQLiteDatabase);
        checkIdentity(supportSQLiteDatabase);
        this.delegate.onOpen(supportSQLiteDatabase);
        this.configuration = null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0059, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005a, code lost:
        kotlin.io.CloseableKt.closeFinally(r4, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005d, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void checkIdentity(androidx.sqlite.db.SupportSQLiteDatabase r4) {
        /*
            r3 = this;
            androidx.room.RoomOpenHelper$Companion r0 = Companion
            boolean r0 = r0.hasRoomMasterTable$room_runtime_release(r4)
            if (r0 == 0) goto L_0x005e
            androidx.sqlite.db.SimpleSQLiteQuery r0 = new androidx.sqlite.db.SimpleSQLiteQuery
            java.lang.String r1 = "SELECT identity_hash FROM room_master_table WHERE id = 42 LIMIT 1"
            r0.<init>(r1)
            android.database.Cursor r4 = r4.query((androidx.sqlite.db.SupportSQLiteQuery) r0)
            boolean r0 = r4.moveToFirst()     // Catch:{ all -> 0x0020 }
            r1 = 0
            if (r0 == 0) goto L_0x0022
            r0 = 0
            java.lang.String r0 = r4.getString(r0)     // Catch:{ all -> 0x0020 }
            goto L_0x0023
        L_0x0020:
            r0 = move-exception
            goto L_0x0058
        L_0x0022:
            r0 = r1
        L_0x0023:
            kotlin.io.CloseableKt.closeFinally(r4, r1)
            java.lang.String r4 = r3.identityHash
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r0)
            if (r4 != 0) goto L_0x0070
            java.lang.String r4 = r3.legacyHash
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r0)
            if (r4 == 0) goto L_0x0037
            goto L_0x0070
        L_0x0037:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number. Expected identity hash: "
            r1.append(r2)
            java.lang.String r2 = r3.identityHash
            r1.append(r2)
            java.lang.String r2 = ", found: "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r4.<init>(r0)
            throw r4
        L_0x0058:
            throw r0     // Catch:{ all -> 0x0059 }
        L_0x0059:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r4, r0)
            throw r1
        L_0x005e:
            androidx.room.RoomOpenHelper$Delegate r0 = r3.delegate
            androidx.room.RoomOpenHelper$ValidationResult r0 = r0.onValidateSchema(r4)
            boolean r1 = r0.isValid
            if (r1 == 0) goto L_0x0071
            androidx.room.RoomOpenHelper$Delegate r0 = r3.delegate
            r0.onPostMigrate(r4)
            r3.updateIdentity(r4)
        L_0x0070:
            return
        L_0x0071:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Pre-packaged database has an invalid schema: "
            r1.append(r2)
            java.lang.String r0 = r0.expectedFoundMsg
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r4.<init>(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomOpenHelper.checkIdentity(androidx.sqlite.db.SupportSQLiteDatabase):void");
    }

    private final void updateIdentity(SupportSQLiteDatabase supportSQLiteDatabase) {
        createMasterTableIfNotExists(supportSQLiteDatabase);
        supportSQLiteDatabase.execSQL(RoomMasterTable.createInsertQuery(this.identityHash));
    }

    private final void createMasterTableIfNotExists(SupportSQLiteDatabase supportSQLiteDatabase) {
        supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
    }

    public static abstract class Delegate {
        public final int version;

        public abstract void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase);

        public abstract void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase);

        public abstract void onCreate(SupportSQLiteDatabase supportSQLiteDatabase);

        public abstract void onOpen(SupportSQLiteDatabase supportSQLiteDatabase);

        public abstract void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase);

        public abstract void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase);

        public abstract ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase);

        public Delegate(int i) {
            this.version = i;
        }
    }

    public static class ValidationResult {
        public final String expectedFoundMsg;
        public final boolean isValid;

        public ValidationResult(boolean z, String str) {
            this.isValid = z;
            this.expectedFoundMsg = str;
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0022, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0023, code lost:
            kotlin.io.CloseableKt.closeFinally(r3, r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0026, code lost:
            throw r1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean hasRoomMasterTable$room_runtime_release(androidx.sqlite.db.SupportSQLiteDatabase r3) {
            /*
                r2 = this;
                java.lang.String r0 = "db"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                java.lang.String r0 = "SELECT 1 FROM sqlite_master WHERE type = 'table' AND name='room_master_table'"
                android.database.Cursor r3 = r3.query((java.lang.String) r0)
                boolean r0 = r3.moveToFirst()     // Catch:{ all -> 0x001a }
                r1 = 0
                if (r0 == 0) goto L_0x001c
                int r0 = r3.getInt(r1)     // Catch:{ all -> 0x001a }
                if (r0 == 0) goto L_0x001c
                r1 = 1
                goto L_0x001c
            L_0x001a:
                r0 = move-exception
                goto L_0x0021
            L_0x001c:
                r0 = 0
                kotlin.io.CloseableKt.closeFinally(r3, r0)
                return r1
            L_0x0021:
                throw r0     // Catch:{ all -> 0x0022 }
            L_0x0022:
                r1 = move-exception
                kotlin.io.CloseableKt.closeFinally(r3, r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomOpenHelper.Companion.hasRoomMasterTable$room_runtime_release(androidx.sqlite.db.SupportSQLiteDatabase):boolean");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0022, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0023, code lost:
            kotlin.io.CloseableKt.closeFinally(r3, r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0026, code lost:
            throw r1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean hasEmptySchema$room_runtime_release(androidx.sqlite.db.SupportSQLiteDatabase r3) {
            /*
                r2 = this;
                java.lang.String r0 = "db"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                java.lang.String r0 = "SELECT count(*) FROM sqlite_master WHERE name != 'android_metadata'"
                android.database.Cursor r3 = r3.query((java.lang.String) r0)
                boolean r0 = r3.moveToFirst()     // Catch:{ all -> 0x001a }
                r1 = 0
                if (r0 == 0) goto L_0x001c
                int r0 = r3.getInt(r1)     // Catch:{ all -> 0x001a }
                if (r0 != 0) goto L_0x001c
                r1 = 1
                goto L_0x001c
            L_0x001a:
                r0 = move-exception
                goto L_0x0021
            L_0x001c:
                r0 = 0
                kotlin.io.CloseableKt.closeFinally(r3, r0)
                return r1
            L_0x0021:
                throw r0     // Catch:{ all -> 0x0022 }
            L_0x0022:
                r1 = move-exception
                kotlin.io.CloseableKt.closeFinally(r3, r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomOpenHelper.Companion.hasEmptySchema$room_runtime_release(androidx.sqlite.db.SupportSQLiteDatabase):boolean");
        }
    }
}
