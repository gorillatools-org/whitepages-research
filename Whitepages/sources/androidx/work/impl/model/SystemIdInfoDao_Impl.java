package androidx.work.impl.model;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import androidx.work.impl.model.SystemIdInfoDao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SystemIdInfoDao_Impl implements SystemIdInfoDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfSystemIdInfo;
    private final SharedSQLiteStatement __preparedStmtOfRemoveSystemIdInfo;
    private final SharedSQLiteStatement __preparedStmtOfRemoveSystemIdInfo_1;

    public SystemIdInfoDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfSystemIdInfo = new EntityInsertionAdapter(roomDatabase) {
            public String createQuery() {
                return "INSERT OR REPLACE INTO `SystemIdInfo` (`work_spec_id`,`generation`,`system_id`) VALUES (?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, SystemIdInfo systemIdInfo) {
                String str = systemIdInfo.workSpecId;
                if (str == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, str);
                }
                supportSQLiteStatement.bindLong(2, (long) systemIdInfo.getGeneration());
                supportSQLiteStatement.bindLong(3, (long) systemIdInfo.systemId);
            }
        };
        this.__preparedStmtOfRemoveSystemIdInfo = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM SystemIdInfo where work_spec_id=? AND generation=?";
            }
        };
        this.__preparedStmtOfRemoveSystemIdInfo_1 = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM SystemIdInfo where work_spec_id=?";
            }
        };
    }

    public void insertSystemIdInfo(SystemIdInfo systemIdInfo) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSystemIdInfo.insert(systemIdInfo);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void removeSystemIdInfo(String str, int i) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfRemoveSystemIdInfo.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, (long) i);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfRemoveSystemIdInfo.release(acquire);
        }
    }

    public void removeSystemIdInfo(String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfRemoveSystemIdInfo_1.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfRemoveSystemIdInfo_1.release(acquire);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: androidx.work.impl.model.SystemIdInfo} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r1v1 */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.work.impl.model.SystemIdInfo getSystemIdInfo(java.lang.String r6, int r7) {
        /*
            r5 = this;
            java.lang.String r0 = "SELECT * FROM SystemIdInfo WHERE work_spec_id=? AND generation=?"
            r1 = 2
            androidx.room.RoomSQLiteQuery r0 = androidx.room.RoomSQLiteQuery.acquire(r0, r1)
            r2 = 1
            if (r6 != 0) goto L_0x000e
            r0.bindNull(r2)
            goto L_0x0011
        L_0x000e:
            r0.bindString(r2, r6)
        L_0x0011:
            long r6 = (long) r7
            r0.bindLong(r1, r6)
            androidx.room.RoomDatabase r6 = r5.__db
            r6.assertNotSuspendingTransaction()
            androidx.room.RoomDatabase r6 = r5.__db
            r7 = 0
            r1 = 0
            android.database.Cursor r6 = androidx.room.util.DBUtil.query(r6, r0, r7, r1)
            java.lang.String r7 = "work_spec_id"
            int r7 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r6, r7)     // Catch:{ all -> 0x0054 }
            java.lang.String r2 = "generation"
            int r2 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r6, r2)     // Catch:{ all -> 0x0054 }
            java.lang.String r3 = "system_id"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r6, r3)     // Catch:{ all -> 0x0054 }
            boolean r4 = r6.moveToFirst()     // Catch:{ all -> 0x0054 }
            if (r4 == 0) goto L_0x0056
            boolean r4 = r6.isNull(r7)     // Catch:{ all -> 0x0054 }
            if (r4 == 0) goto L_0x0041
            goto L_0x0045
        L_0x0041:
            java.lang.String r1 = r6.getString(r7)     // Catch:{ all -> 0x0054 }
        L_0x0045:
            int r7 = r6.getInt(r2)     // Catch:{ all -> 0x0054 }
            int r2 = r6.getInt(r3)     // Catch:{ all -> 0x0054 }
            androidx.work.impl.model.SystemIdInfo r3 = new androidx.work.impl.model.SystemIdInfo     // Catch:{ all -> 0x0054 }
            r3.<init>(r1, r7, r2)     // Catch:{ all -> 0x0054 }
            r1 = r3
            goto L_0x0056
        L_0x0054:
            r7 = move-exception
            goto L_0x005d
        L_0x0056:
            r6.close()
            r0.release()
            return r1
        L_0x005d:
            r6.close()
            r0.release()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.SystemIdInfoDao_Impl.getSystemIdInfo(java.lang.String, int):androidx.work.impl.model.SystemIdInfo");
    }

    public List getWorkSpecIds() {
        String str;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT DISTINCT work_spec_id FROM SystemIdInfo", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(0)) {
                    str = null;
                } else {
                    str = query.getString(0);
                }
                arrayList.add(str);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public SystemIdInfo getSystemIdInfo(WorkGenerationalId workGenerationalId) {
        return SystemIdInfoDao.DefaultImpls.getSystemIdInfo(this, workGenerationalId);
    }

    public void removeSystemIdInfo(WorkGenerationalId workGenerationalId) {
        SystemIdInfoDao.DefaultImpls.removeSystemIdInfo(this, workGenerationalId);
    }

    public static List getRequiredConverters() {
        return Collections.emptyList();
    }
}
