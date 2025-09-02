package com.reactnativecommunity.asyncstorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ReactDatabaseSupplier extends SQLiteOpenHelper {
    private static ReactDatabaseSupplier sReactDatabaseSupplierInstance;
    private Context mContext;
    private SQLiteDatabase mDb;
    private long mMaximumDatabaseSize = (BuildConfig.AsyncStorage_db_size.longValue() * 1048576);

    private ReactDatabaseSupplier(Context context) {
        super(context, "RKStorage", (SQLiteDatabase.CursorFactory) null, 1);
        this.mContext = context;
    }

    public static ReactDatabaseSupplier getInstance(Context context) {
        if (sReactDatabaseSupplierInstance == null) {
            sReactDatabaseSupplierInstance = new ReactDatabaseSupplier(context.getApplicationContext());
        }
        return sReactDatabaseSupplierInstance;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE catalystLocalStorage (key TEXT PRIMARY KEY, value TEXT NOT NULL)");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i != i2) {
            deleteDatabase();
            onCreate(sQLiteDatabase);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:15|16|18|19|20|21|22|35|23) */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0031, code lost:
        continue;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x002a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean ensureDatabase() {
        /*
            r5 = this;
            monitor-enter(r5)
            android.database.sqlite.SQLiteDatabase r0 = r5.mDb     // Catch:{ all -> 0x000e }
            r1 = 1
            if (r0 == 0) goto L_0x0010
            boolean r0 = r0.isOpen()     // Catch:{ all -> 0x000e }
            if (r0 == 0) goto L_0x0010
            monitor-exit(r5)
            return r1
        L_0x000e:
            r0 = move-exception
            goto L_0x0040
        L_0x0010:
            r0 = 0
            r2 = 0
        L_0x0012:
            r3 = 2
            if (r2 >= r3) goto L_0x0034
            if (r2 <= 0) goto L_0x001d
            r5.deleteDatabase()     // Catch:{ SQLiteException -> 0x001b }
            goto L_0x001d
        L_0x001b:
            r0 = move-exception
            goto L_0x0024
        L_0x001d:
            android.database.sqlite.SQLiteDatabase r3 = r5.getWritableDatabase()     // Catch:{ SQLiteException -> 0x001b }
            r5.mDb = r3     // Catch:{ SQLiteException -> 0x001b }
            goto L_0x0034
        L_0x0024:
            r3 = 30
            java.lang.Thread.sleep(r3)     // Catch:{ InterruptedException -> 0x002a }
            goto L_0x0031
        L_0x002a:
            java.lang.Thread r3 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x000e }
            r3.interrupt()     // Catch:{ all -> 0x000e }
        L_0x0031:
            int r2 = r2 + 1
            goto L_0x0012
        L_0x0034:
            android.database.sqlite.SQLiteDatabase r2 = r5.mDb     // Catch:{ all -> 0x000e }
            if (r2 == 0) goto L_0x003f
            long r3 = r5.mMaximumDatabaseSize     // Catch:{ all -> 0x000e }
            r2.setMaximumSize(r3)     // Catch:{ all -> 0x000e }
            monitor-exit(r5)
            return r1
        L_0x003f:
            throw r0     // Catch:{ all -> 0x000e }
        L_0x0040:
            monitor-exit(r5)     // Catch:{ all -> 0x000e }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reactnativecommunity.asyncstorage.ReactDatabaseSupplier.ensureDatabase():boolean");
    }

    public synchronized SQLiteDatabase get() {
        ensureDatabase();
        return this.mDb;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0020, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0028, code lost:
        throw new java.lang.RuntimeException("Clearing and deleting database RKStorage failed");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0016, code lost:
        if (deleteDatabase() != false) goto L_0x0018;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
        com.facebook.common.logging.FLog.d(com.facebook.react.common.ReactConstants.TAG, "Deleted Local Database RKStorage");
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0012 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void clearAndCloseDatabase() {
        /*
            r2 = this;
            monitor-enter(r2)
            r2.clear()     // Catch:{ Exception -> 0x0012 }
            r2.closeDatabase()     // Catch:{ Exception -> 0x0012 }
            java.lang.String r0 = "ReactNative"
            java.lang.String r1 = "Cleaned RKStorage"
            com.facebook.common.logging.FLog.d(r0, r1)     // Catch:{ Exception -> 0x0012 }
            monitor-exit(r2)
            return
        L_0x0010:
            r0 = move-exception
            goto L_0x0029
        L_0x0012:
            boolean r0 = r2.deleteDatabase()     // Catch:{ all -> 0x0010 }
            if (r0 == 0) goto L_0x0021
            java.lang.String r0 = "ReactNative"
            java.lang.String r1 = "Deleted Local Database RKStorage"
            com.facebook.common.logging.FLog.d(r0, r1)     // Catch:{ all -> 0x0010 }
            monitor-exit(r2)
            return
        L_0x0021:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch:{ all -> 0x0010 }
            java.lang.String r1 = "Clearing and deleting database RKStorage failed"
            r0.<init>(r1)     // Catch:{ all -> 0x0010 }
            throw r0     // Catch:{ all -> 0x0010 }
        L_0x0029:
            monitor-exit(r2)     // Catch:{ all -> 0x0010 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reactnativecommunity.asyncstorage.ReactDatabaseSupplier.clearAndCloseDatabase():void");
    }

    /* access modifiers changed from: package-private */
    public synchronized void clear() {
        get().delete("catalystLocalStorage", (String) null, (String[]) null);
    }

    private synchronized boolean deleteDatabase() {
        closeDatabase();
        return this.mContext.deleteDatabase("RKStorage");
    }

    public synchronized void closeDatabase() {
        SQLiteDatabase sQLiteDatabase = this.mDb;
        if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
            this.mDb.close();
            this.mDb = null;
        }
    }
}
