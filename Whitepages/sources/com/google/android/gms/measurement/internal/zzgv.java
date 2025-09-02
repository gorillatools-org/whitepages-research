package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.Parcel;
import android.os.SystemClock;
import com.salesforce.marketingcloud.storage.db.i;
import com.salesforce.marketingcloud.storage.db.k;

public final class zzgv extends zzg {
    /* access modifiers changed from: private */
    public static final String[] zza = {k.a.q, "ALTER TABLE messages ADD COLUMN app_version TEXT;", "app_version_int", "ALTER TABLE messages ADD COLUMN app_version_int INTEGER;"};
    private final zzgt zzb;
    private boolean zzc;

    zzgv(zzio zzio) {
        super(zzio);
        Context zzaT = this.zzu.zzaT();
        this.zzu.zzf();
        this.zzb = new zzgt(this, zzaT, "google_app_measurement_local.db");
    }

    /* JADX WARNING: Removed duplicated region for block: B:56:0x00ff A[SYNTHETIC, Splitter:B:56:0x00ff] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x012c  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x015a  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x015f  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0152 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0152 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0152 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzs(int r18, byte[] r19) {
        /*
            r17 = this;
            r1 = r17
            r17.zzg()
            boolean r0 = r1.zzc
            r2 = 0
            if (r0 == 0) goto L_0x000b
            goto L_0x006a
        L_0x000b:
            com.google.android.gms.measurement.internal.zzio r3 = r1.zzu
            com.google.android.gms.measurement.internal.zzam r0 = r3.zzf()
            com.google.android.gms.measurement.internal.zzgg r4 = com.google.android.gms.measurement.internal.zzgi.zzbl
            r5 = 0
            boolean r0 = r0.zzx(r5, r4)
            if (r0 == 0) goto L_0x0025
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu
            com.google.android.gms.measurement.internal.zzgs r0 = r0.zzh()
            com.google.android.gms.measurement.internal.zzr r0 = r0.zzk(r5)
            goto L_0x0026
        L_0x0025:
            r0 = r5
        L_0x0026:
            android.content.ContentValues r6 = new android.content.ContentValues
            r6.<init>()
            java.lang.Integer r7 = java.lang.Integer.valueOf(r18)
            java.lang.String r8 = "type"
            r6.put(r8, r7)
            java.lang.String r7 = "entry"
            r8 = r19
            r6.put(r7, r8)
            com.google.android.gms.measurement.internal.zzam r7 = r3.zzf()
            boolean r4 = r7.zzx(r5, r4)
            if (r4 == 0) goto L_0x0059
            if (r0 == 0) goto L_0x0059
            java.lang.String r4 = "app_version"
            java.lang.String r7 = r0.zzc
            r6.put(r4, r7)
            long r7 = r0.zzj
            java.lang.Long r0 = java.lang.Long.valueOf(r7)
            java.lang.String r4 = "app_version_int"
            r6.put(r4, r0)
        L_0x0059:
            r3.zzf()
            r4 = 5
            r7 = r2
            r8 = r4
        L_0x005f:
            if (r7 >= r4) goto L_0x0163
            r9 = 1
            android.database.sqlite.SQLiteDatabase r10 = r17.zzh()     // Catch:{ SQLiteFullException -> 0x0135, SQLiteDatabaseLockedException -> 0x0122, SQLiteException -> 0x00fa, all -> 0x00f7 }
            if (r10 != 0) goto L_0x0074
            r1.zzc = r9     // Catch:{ SQLiteFullException -> 0x0071, SQLiteDatabaseLockedException -> 0x00f3, SQLiteException -> 0x006e, all -> 0x006b }
        L_0x006a:
            return r2
        L_0x006b:
            r0 = move-exception
            goto L_0x0158
        L_0x006e:
            r0 = move-exception
            goto L_0x00f1
        L_0x0071:
            r0 = move-exception
            goto L_0x00f5
        L_0x0074:
            r10.beginTransaction()     // Catch:{ SQLiteFullException -> 0x0071, SQLiteDatabaseLockedException -> 0x00f3, SQLiteException -> 0x006e, all -> 0x006b }
            java.lang.String r0 = "select count(1) from messages"
            android.database.Cursor r11 = r10.rawQuery(r0, r5)     // Catch:{ SQLiteFullException -> 0x0071, SQLiteDatabaseLockedException -> 0x00f3, SQLiteException -> 0x006e, all -> 0x006b }
            r12 = 0
            if (r11 == 0) goto L_0x0094
            boolean r0 = r11.moveToFirst()     // Catch:{ SQLiteFullException -> 0x0091, SQLiteDatabaseLockedException -> 0x0124, SQLiteException -> 0x008e }
            if (r0 == 0) goto L_0x0094
            long r12 = r11.getLong(r2)     // Catch:{ SQLiteFullException -> 0x0091, SQLiteDatabaseLockedException -> 0x0124, SQLiteException -> 0x008e }
            goto L_0x0094
        L_0x008c:
            r0 = move-exception
            goto L_0x00ee
        L_0x008e:
            r0 = move-exception
            goto L_0x00fd
        L_0x0091:
            r0 = move-exception
            goto L_0x0138
        L_0x0094:
            r14 = 100000(0x186a0, double:4.94066E-319)
            int r0 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            java.lang.String r14 = "messages"
            if (r0 < 0) goto L_0x00db
            com.google.android.gms.measurement.internal.zzhe r0 = r3.zzaW()     // Catch:{ SQLiteFullException -> 0x0091, SQLiteDatabaseLockedException -> 0x0124, SQLiteException -> 0x008e }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zze()     // Catch:{ SQLiteFullException -> 0x0091, SQLiteDatabaseLockedException -> 0x0124, SQLiteException -> 0x008e }
            java.lang.String r15 = "Data loss, local db full"
            r0.zza(r15)     // Catch:{ SQLiteFullException -> 0x0091, SQLiteDatabaseLockedException -> 0x0124, SQLiteException -> 0x008e }
            java.lang.String r0 = "rowid in (select rowid from messages order by rowid asc limit ?)"
            r15 = 100001(0x186a1, double:4.9407E-319)
            long r15 = r15 - r12
            java.lang.String r12 = java.lang.Long.toString(r15)     // Catch:{ SQLiteFullException -> 0x0091, SQLiteDatabaseLockedException -> 0x0124, SQLiteException -> 0x008e }
            java.lang.String[] r12 = new java.lang.String[]{r12}     // Catch:{ SQLiteFullException -> 0x0091, SQLiteDatabaseLockedException -> 0x0124, SQLiteException -> 0x008e }
            int r0 = r10.delete(r14, r0, r12)     // Catch:{ SQLiteFullException -> 0x0091, SQLiteDatabaseLockedException -> 0x0124, SQLiteException -> 0x008e }
            long r12 = (long) r0     // Catch:{ SQLiteFullException -> 0x0091, SQLiteDatabaseLockedException -> 0x0124, SQLiteException -> 0x008e }
            int r0 = (r12 > r15 ? 1 : (r12 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x00db
            com.google.android.gms.measurement.internal.zzhe r0 = r3.zzaW()     // Catch:{ SQLiteFullException -> 0x0091, SQLiteDatabaseLockedException -> 0x0124, SQLiteException -> 0x008e }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zze()     // Catch:{ SQLiteFullException -> 0x0091, SQLiteDatabaseLockedException -> 0x0124, SQLiteException -> 0x008e }
            java.lang.String r4 = "Different delete count than expected in local db. expected, received, difference"
            java.lang.Long r2 = java.lang.Long.valueOf(r15)     // Catch:{ SQLiteFullException -> 0x0091, SQLiteDatabaseLockedException -> 0x0124, SQLiteException -> 0x008e }
            java.lang.Long r9 = java.lang.Long.valueOf(r12)     // Catch:{ SQLiteFullException -> 0x0091, SQLiteDatabaseLockedException -> 0x0124, SQLiteException -> 0x008e }
            long r15 = r15 - r12
            java.lang.Long r12 = java.lang.Long.valueOf(r15)     // Catch:{ SQLiteFullException -> 0x0091, SQLiteDatabaseLockedException -> 0x0124, SQLiteException -> 0x008e }
            r0.zzd(r4, r2, r9, r12)     // Catch:{ SQLiteFullException -> 0x0091, SQLiteDatabaseLockedException -> 0x0124, SQLiteException -> 0x008e }
        L_0x00db:
            r10.insertOrThrow(r14, r5, r6)     // Catch:{ SQLiteFullException -> 0x0091, SQLiteDatabaseLockedException -> 0x0124, SQLiteException -> 0x008e }
            r10.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x0091, SQLiteDatabaseLockedException -> 0x0124, SQLiteException -> 0x008e }
            r10.endTransaction()     // Catch:{ SQLiteFullException -> 0x0091, SQLiteDatabaseLockedException -> 0x0124, SQLiteException -> 0x008e }
            if (r11 == 0) goto L_0x00e9
            r11.close()
        L_0x00e9:
            r10.close()
            r2 = 1
            return r2
        L_0x00ee:
            r5 = r11
            goto L_0x0158
        L_0x00f1:
            r11 = r5
            goto L_0x00fd
        L_0x00f3:
            r11 = r5
            goto L_0x0124
        L_0x00f5:
            r11 = r5
            goto L_0x0138
        L_0x00f7:
            r0 = move-exception
            r10 = r5
            goto L_0x0158
        L_0x00fa:
            r0 = move-exception
            r10 = r5
            r11 = r10
        L_0x00fd:
            if (r10 == 0) goto L_0x0108
            boolean r2 = r10.inTransaction()     // Catch:{ all -> 0x008c }
            if (r2 == 0) goto L_0x0108
            r10.endTransaction()     // Catch:{ all -> 0x008c }
        L_0x0108:
            com.google.android.gms.measurement.internal.zzio r2 = r1.zzu     // Catch:{ all -> 0x008c }
            com.google.android.gms.measurement.internal.zzhe r2 = r2.zzaW()     // Catch:{ all -> 0x008c }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zze()     // Catch:{ all -> 0x008c }
            java.lang.String r4 = "Error writing entry to local database"
            r2.zzb(r4, r0)     // Catch:{ all -> 0x008c }
            r2 = 1
            r1.zzc = r2     // Catch:{ all -> 0x008c }
            if (r11 == 0) goto L_0x011f
            r11.close()
        L_0x011f:
            if (r10 == 0) goto L_0x0152
            goto L_0x0131
        L_0x0122:
            r10 = r5
            r11 = r10
        L_0x0124:
            long r12 = (long) r8
            android.os.SystemClock.sleep(r12)     // Catch:{ all -> 0x008c }
            int r8 = r8 + 20
            if (r11 == 0) goto L_0x012f
            r11.close()
        L_0x012f:
            if (r10 == 0) goto L_0x0152
        L_0x0131:
            r10.close()
            goto L_0x0152
        L_0x0135:
            r0 = move-exception
            r10 = r5
            r11 = r10
        L_0x0138:
            com.google.android.gms.measurement.internal.zzio r2 = r1.zzu     // Catch:{ all -> 0x008c }
            com.google.android.gms.measurement.internal.zzhe r2 = r2.zzaW()     // Catch:{ all -> 0x008c }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zze()     // Catch:{ all -> 0x008c }
            java.lang.String r4 = "Error writing entry; local database full"
            r2.zzb(r4, r0)     // Catch:{ all -> 0x008c }
            r2 = 1
            r1.zzc = r2     // Catch:{ all -> 0x008c }
            if (r11 == 0) goto L_0x014f
            r11.close()
        L_0x014f:
            if (r10 == 0) goto L_0x0152
            goto L_0x0131
        L_0x0152:
            int r7 = r7 + 1
            r2 = 0
            r4 = 5
            goto L_0x005f
        L_0x0158:
            if (r5 == 0) goto L_0x015d
            r5.close()
        L_0x015d:
            if (r10 == 0) goto L_0x0162
            r10.close()
        L_0x0162:
            throw r0
        L_0x0163:
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzj()
            java.lang.String r2 = "Failed to write entry to local database"
            r0.zza(r2)
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzgv.zzs(int, byte[]):boolean");
    }

    /* access modifiers changed from: protected */
    public final boolean zzf() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public final SQLiteDatabase zzh() throws SQLiteException {
        if (this.zzc) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zzb.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzc = true;
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v23, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v24, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v36, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v37, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v40, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v44, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v3, resolved type: java.lang.String[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v51, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v52, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v53, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v59, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v60, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v61, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v62, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v63, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v64, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v65, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v66, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v67, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v68, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v69, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v70, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v71, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v72, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v73, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v74, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v75, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v76, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v77, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v78, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v79, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v80, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v81, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v82, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v83, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v84, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v85, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v86, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v87, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v88, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v89, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v90, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v91, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v92, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v93, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v94, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v5, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r6v0 */
    /* JADX WARNING: type inference failed for: r6v1, types: [java.util.List, java.lang.String] */
    /* JADX WARNING: type inference failed for: r6v3 */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:104|105|106|107) */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:120|121|122|123) */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:136|137|138|139) */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:90|91|92|93) */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x01a3, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:?, code lost:
        r1.zzu.zzaW().zze().zza("Failed to load user property from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x01b4, code lost:
        r8 = r8;
        r8 = r8;
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:?, code lost:
        r15.recycle();
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x01c3, code lost:
        r15.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x01c6, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x01e1, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:?, code lost:
        r1.zzu.zzaW().zze().zza("Failed to load conditional user property from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x01f2, code lost:
        r8 = r8;
        r8 = r8;
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:?, code lost:
        r15.recycle();
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x0202, code lost:
        r15.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x0205, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x0220, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:?, code lost:
        r1.zzu.zzaW().zze().zza("Failed to load default event parameters from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0231, code lost:
        r8 = r8;
        r8 = r8;
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:?, code lost:
        r15.recycle();
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x0241, code lost:
        r15.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0244, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x026c, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x026d, code lost:
        r8 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x0270, code lost:
        r8 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x0273, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0274, code lost:
        r8 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x02f0, code lost:
        if (r15.inTransaction() != false) goto L_0x02f2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x02f2, code lost:
        r15.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x02f6, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x030a, code lost:
        r13.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x031b, code lost:
        r13.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x0349, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:0x034e, code lost:
        r15.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0121, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0122, code lost:
        r12 = r28;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0162, code lost:
        r0 = e;
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0163, code lost:
        r12 = r28;
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0167, code lost:
        r12 = r28;
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:?, code lost:
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x016b, code lost:
        r0 = e;
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0170, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:?, code lost:
        r1.zzu.zzaW().zze().zza("Failed to load event from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0181, code lost:
        r8 = r8;
        r8 = r8;
        r8 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:?, code lost:
        r15.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0185, code lost:
        r15.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0188, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:104:0x01a5 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:120:0x01e3 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:136:0x0222 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:90:0x0172 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:182:0x02ca A[SYNTHETIC, Splitter:B:182:0x02ca] */
    /* JADX WARNING: Removed duplicated region for block: B:201:0x02ec A[SYNTHETIC, Splitter:B:201:0x02ec] */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x030a  */
    /* JADX WARNING: Removed duplicated region for block: B:218:0x031b  */
    /* JADX WARNING: Removed duplicated region for block: B:226:0x033a  */
    /* JADX WARNING: Removed duplicated region for block: B:230:0x0349  */
    /* JADX WARNING: Removed duplicated region for block: B:232:0x034e  */
    /* JADX WARNING: Removed duplicated region for block: B:239:0x02d4 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:240:0x0340 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:241:0x0340 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:243:0x0340 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0121 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:55:0x00f5] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List zzi(int r28) {
        /*
            r27 = this;
            r1 = r27
            java.lang.String r2 = "Error reading entries from local database"
            java.lang.String r3 = "entry"
            java.lang.String r4 = "type"
            java.lang.String r5 = "rowid"
            r27.zzg()
            boolean r0 = r1.zzc
            r6 = 0
            if (r0 == 0) goto L_0x0013
            return r6
        L_0x0013:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            boolean r0 = r27.zzl()
            if (r0 == 0) goto L_0x0363
            r8 = 5
            r9 = 0
            r11 = r8
            r10 = r9
        L_0x0022:
            if (r10 >= r8) goto L_0x0352
            r12 = 1
            android.database.sqlite.SQLiteDatabase r15 = r27.zzh()     // Catch:{ SQLiteFullException -> 0x0324, SQLiteDatabaseLockedException -> 0x0310, SQLiteException -> 0x02e6, all -> 0x02e2 }
            if (r15 != 0) goto L_0x0040
            r1.zzc = r12     // Catch:{ SQLiteFullException -> 0x003b, SQLiteDatabaseLockedException -> 0x0037, SQLiteException -> 0x0032, all -> 0x002e }
            return r6
        L_0x002e:
            r0 = move-exception
            r12 = r15
            goto L_0x02d5
        L_0x0032:
            r0 = move-exception
            r8 = r7
            r12 = r15
            goto L_0x02d9
        L_0x0037:
            r8 = r7
            r12 = r15
            goto L_0x02dc
        L_0x003b:
            r0 = move-exception
            r8 = r7
            r12 = r15
            goto L_0x02df
        L_0x0040:
            r15.beginTransaction()     // Catch:{ SQLiteFullException -> 0x003b, SQLiteDatabaseLockedException -> 0x0037, SQLiteException -> 0x0032, all -> 0x002e }
            java.lang.String r0 = "3"
            java.lang.String r14 = "messages"
            java.lang.String[] r16 = new java.lang.String[]{r5}     // Catch:{ all -> 0x02c4 }
            java.lang.String r17 = "type=?"
            java.lang.String[] r0 = new java.lang.String[]{r0}     // Catch:{ all -> 0x02c4 }
            java.lang.String r20 = "rowid desc"
            java.lang.String r21 = "1"
            r18 = 0
            r19 = 0
            r13 = r15
            r28 = r15
            r15 = r16
            r16 = r17
            r17 = r0
            android.database.Cursor r13 = r13.query(r14, r15, r16, r17, r18, r19, r20, r21)     // Catch:{ all -> 0x02bf }
            boolean r0 = r13.moveToFirst()     // Catch:{ all -> 0x008c }
            r22 = -1
            if (r0 == 0) goto L_0x0092
            long r14 = r13.getLong(r9)     // Catch:{ all -> 0x008c }
            r13.close()     // Catch:{ SQLiteFullException -> 0x0086, SQLiteDatabaseLockedException -> 0x0081, SQLiteException -> 0x007b, all -> 0x0076 }
            goto L_0x0097
        L_0x0076:
            r0 = move-exception
            r12 = r28
            goto L_0x02d5
        L_0x007b:
            r0 = move-exception
            r12 = r28
            r8 = r7
            goto L_0x02d9
        L_0x0081:
            r12 = r28
            r8 = r7
            goto L_0x02dc
        L_0x0086:
            r0 = move-exception
            r12 = r28
            r8 = r7
            goto L_0x02df
        L_0x008c:
            r0 = move-exception
            r12 = r28
            r8 = r7
            goto L_0x02c8
        L_0x0092:
            r13.close()     // Catch:{ SQLiteFullException -> 0x0086, SQLiteDatabaseLockedException -> 0x0081, SQLiteException -> 0x007b, all -> 0x0076 }
            r14 = r22
        L_0x0097:
            int r0 = (r14 > r22 ? 1 : (r14 == r22 ? 0 : -1))
            if (r0 == 0) goto L_0x00aa
            java.lang.String r0 = "rowid<?"
            java.lang.String[] r13 = new java.lang.String[r12]     // Catch:{ SQLiteFullException -> 0x0086, SQLiteDatabaseLockedException -> 0x0081, SQLiteException -> 0x007b, all -> 0x0076 }
            java.lang.String r14 = java.lang.String.valueOf(r14)     // Catch:{ SQLiteFullException -> 0x0086, SQLiteDatabaseLockedException -> 0x0081, SQLiteException -> 0x007b, all -> 0x0076 }
            r13[r9] = r14     // Catch:{ SQLiteFullException -> 0x0086, SQLiteDatabaseLockedException -> 0x0081, SQLiteException -> 0x007b, all -> 0x0076 }
            r16 = r0
            r17 = r13
            goto L_0x00ae
        L_0x00aa:
            r16 = r6
            r17 = r16
        L_0x00ae:
            java.lang.String[] r0 = new java.lang.String[]{r5, r4, r3}     // Catch:{ SQLiteFullException -> 0x0086, SQLiteDatabaseLockedException -> 0x0081, SQLiteException -> 0x007b, all -> 0x0076 }
            com.google.android.gms.measurement.internal.zzio r15 = r1.zzu     // Catch:{ SQLiteFullException -> 0x0086, SQLiteDatabaseLockedException -> 0x0081, SQLiteException -> 0x007b, all -> 0x0076 }
            com.google.android.gms.measurement.internal.zzam r13 = r15.zzf()     // Catch:{ SQLiteFullException -> 0x0086, SQLiteDatabaseLockedException -> 0x0081, SQLiteException -> 0x007b, all -> 0x0076 }
            com.google.android.gms.measurement.internal.zzgg r14 = com.google.android.gms.measurement.internal.zzgi.zzbl     // Catch:{ SQLiteFullException -> 0x0086, SQLiteDatabaseLockedException -> 0x0081, SQLiteException -> 0x007b, all -> 0x0076 }
            boolean r13 = r13.zzx(r6, r14)     // Catch:{ SQLiteFullException -> 0x0086, SQLiteDatabaseLockedException -> 0x0081, SQLiteException -> 0x007b, all -> 0x0076 }
            r6 = 3
            r14 = 2
            if (r13 == 0) goto L_0x00d5
            java.lang.String[] r0 = new java.lang.String[r8]     // Catch:{ SQLiteFullException -> 0x0086, SQLiteDatabaseLockedException -> 0x0081, SQLiteException -> 0x007b, all -> 0x0076 }
            r0[r9] = r5     // Catch:{ SQLiteFullException -> 0x0086, SQLiteDatabaseLockedException -> 0x0081, SQLiteException -> 0x007b, all -> 0x0076 }
            r0[r12] = r4     // Catch:{ SQLiteFullException -> 0x0086, SQLiteDatabaseLockedException -> 0x0081, SQLiteException -> 0x007b, all -> 0x0076 }
            r0[r14] = r3     // Catch:{ SQLiteFullException -> 0x0086, SQLiteDatabaseLockedException -> 0x0081, SQLiteException -> 0x007b, all -> 0x0076 }
            java.lang.String r13 = "app_version"
            r0[r6] = r13     // Catch:{ SQLiteFullException -> 0x0086, SQLiteDatabaseLockedException -> 0x0081, SQLiteException -> 0x007b, all -> 0x0076 }
            java.lang.String r13 = "app_version_int"
            r18 = 4
            r0[r18] = r13     // Catch:{ SQLiteFullException -> 0x0086, SQLiteDatabaseLockedException -> 0x0081, SQLiteException -> 0x007b, all -> 0x0076 }
            goto L_0x00d7
        L_0x00d5:
            r18 = 4
        L_0x00d7:
            java.lang.String r19 = "messages"
            java.lang.String r20 = "rowid asc"
            r13 = 100
            java.lang.String r21 = java.lang.Integer.toString(r13)     // Catch:{ SQLiteFullException -> 0x0086, SQLiteDatabaseLockedException -> 0x0081, SQLiteException -> 0x007b, all -> 0x0076 }
            r24 = 0
            r25 = 0
            r13 = r28
            r8 = r14
            r14 = r19
            r26 = r15
            r15 = r0
            r18 = r24
            r19 = r25
            android.database.Cursor r13 = r13.query(r14, r15, r16, r17, r18, r19, r20, r21)     // Catch:{ SQLiteFullException -> 0x0086, SQLiteDatabaseLockedException -> 0x0081, SQLiteException -> 0x007b, all -> 0x0076 }
        L_0x00f5:
            boolean r0 = r13.moveToNext()     // Catch:{ SQLiteFullException -> 0x0131, SQLiteDatabaseLockedException -> 0x012c, SQLiteException -> 0x0126, all -> 0x0121 }
            if (r0 == 0) goto L_0x0277
            long r22 = r13.getLong(r9)     // Catch:{ SQLiteFullException -> 0x0273, SQLiteDatabaseLockedException -> 0x0270, SQLiteException -> 0x026c, all -> 0x0121 }
            int r0 = r13.getInt(r12)     // Catch:{ SQLiteFullException -> 0x0273, SQLiteDatabaseLockedException -> 0x0270, SQLiteException -> 0x026c, all -> 0x0121 }
            byte[] r14 = r13.getBlob(r8)     // Catch:{ SQLiteFullException -> 0x0273, SQLiteDatabaseLockedException -> 0x0270, SQLiteException -> 0x026c, all -> 0x0121 }
            com.google.android.gms.measurement.internal.zzam r15 = r26.zzf()     // Catch:{ SQLiteFullException -> 0x0273, SQLiteDatabaseLockedException -> 0x0270, SQLiteException -> 0x026c, all -> 0x0121 }
            com.google.android.gms.measurement.internal.zzgg r8 = com.google.android.gms.measurement.internal.zzgi.zzbl     // Catch:{ SQLiteFullException -> 0x0273, SQLiteDatabaseLockedException -> 0x0270, SQLiteException -> 0x026c, all -> 0x0121 }
            r12 = 0
            boolean r8 = r15.zzx(r12, r8)     // Catch:{ SQLiteFullException -> 0x0273, SQLiteDatabaseLockedException -> 0x0270, SQLiteException -> 0x026c, all -> 0x0121 }
            if (r8 == 0) goto L_0x0137
            java.lang.String r12 = r13.getString(r6)     // Catch:{ SQLiteFullException -> 0x0131, SQLiteDatabaseLockedException -> 0x012c, SQLiteException -> 0x0126, all -> 0x0121 }
            r8 = 4
            long r18 = r13.getLong(r8)     // Catch:{ SQLiteFullException -> 0x0131, SQLiteDatabaseLockedException -> 0x012c, SQLiteException -> 0x0126, all -> 0x0121 }
            r8 = r7
            r6 = r18
            goto L_0x013d
        L_0x0121:
            r0 = move-exception
            r12 = r28
            goto L_0x02b3
        L_0x0126:
            r0 = move-exception
            r12 = r28
            r8 = r7
            goto L_0x02b7
        L_0x012c:
            r12 = r28
            r8 = r7
            goto L_0x02b9
        L_0x0131:
            r0 = move-exception
            r12 = r28
            r8 = r7
            goto L_0x02bc
        L_0x0137:
            r18 = 0
            r8 = r7
            r6 = r18
            r12 = 0
        L_0x013d:
            if (r0 != 0) goto L_0x0189
            android.os.Parcel r15 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            int r0 = r14.length     // Catch:{ ParseException -> 0x0172 }
            r15.unmarshall(r14, r9, r0)     // Catch:{ ParseException -> 0x0172 }
            r15.setDataPosition(r9)     // Catch:{ ParseException -> 0x0172 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzbh> r0 = com.google.android.gms.measurement.internal.zzbh.CREATOR     // Catch:{ ParseException -> 0x0172 }
            java.lang.Object r0 = r0.createFromParcel(r15)     // Catch:{ ParseException -> 0x0172 }
            com.google.android.gms.measurement.internal.zzbh r0 = (com.google.android.gms.measurement.internal.zzbh) r0     // Catch:{ ParseException -> 0x0172 }
            r15.recycle()     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            if (r0 == 0) goto L_0x015f
            com.google.android.gms.measurement.internal.zzgu r14 = new com.google.android.gms.measurement.internal.zzgu     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            r14.<init>(r0, r12, r6)     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            r8.add(r14)     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
        L_0x015f:
            r6 = 3
            goto L_0x0267
        L_0x0162:
            r0 = move-exception
        L_0x0163:
            r12 = r28
            goto L_0x02b7
        L_0x0167:
            r12 = r28
            goto L_0x02b9
        L_0x016b:
            r0 = move-exception
        L_0x016c:
            r12 = r28
            goto L_0x02bc
        L_0x0170:
            r0 = move-exception
            goto L_0x0185
        L_0x0172:
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu     // Catch:{ all -> 0x0170 }
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()     // Catch:{ all -> 0x0170 }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zze()     // Catch:{ all -> 0x0170 }
            java.lang.String r6 = "Failed to load event from local database"
            r0.zza(r6)     // Catch:{ all -> 0x0170 }
            r15.recycle()     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            goto L_0x015f
        L_0x0185:
            r15.recycle()     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            throw r0     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
        L_0x0189:
            r15 = 1
            if (r0 != r15) goto L_0x01c7
            android.os.Parcel r15 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            int r0 = r14.length     // Catch:{ ParseException -> 0x01a5 }
            r15.unmarshall(r14, r9, r0)     // Catch:{ ParseException -> 0x01a5 }
            r15.setDataPosition(r9)     // Catch:{ ParseException -> 0x01a5 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzqb> r0 = com.google.android.gms.measurement.internal.zzqb.CREATOR     // Catch:{ ParseException -> 0x01a5 }
            java.lang.Object r0 = r0.createFromParcel(r15)     // Catch:{ ParseException -> 0x01a5 }
            com.google.android.gms.measurement.internal.zzqb r0 = (com.google.android.gms.measurement.internal.zzqb) r0     // Catch:{ ParseException -> 0x01a5 }
            r15.recycle()     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            goto L_0x01b8
        L_0x01a3:
            r0 = move-exception
            goto L_0x01c3
        L_0x01a5:
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu     // Catch:{ all -> 0x01a3 }
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()     // Catch:{ all -> 0x01a3 }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zze()     // Catch:{ all -> 0x01a3 }
            java.lang.String r14 = "Failed to load user property from local database"
            r0.zza(r14)     // Catch:{ all -> 0x01a3 }
            r15.recycle()     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            r0 = 0
        L_0x01b8:
            if (r0 == 0) goto L_0x015f
            com.google.android.gms.measurement.internal.zzgu r14 = new com.google.android.gms.measurement.internal.zzgu     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            r14.<init>(r0, r12, r6)     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            r8.add(r14)     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            goto L_0x015f
        L_0x01c3:
            r15.recycle()     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            throw r0     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
        L_0x01c7:
            r15 = 2
            if (r0 != r15) goto L_0x0206
            android.os.Parcel r15 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            int r0 = r14.length     // Catch:{ ParseException -> 0x01e3 }
            r15.unmarshall(r14, r9, r0)     // Catch:{ ParseException -> 0x01e3 }
            r15.setDataPosition(r9)     // Catch:{ ParseException -> 0x01e3 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzai> r0 = com.google.android.gms.measurement.internal.zzai.CREATOR     // Catch:{ ParseException -> 0x01e3 }
            java.lang.Object r0 = r0.createFromParcel(r15)     // Catch:{ ParseException -> 0x01e3 }
            com.google.android.gms.measurement.internal.zzai r0 = (com.google.android.gms.measurement.internal.zzai) r0     // Catch:{ ParseException -> 0x01e3 }
            r15.recycle()     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            goto L_0x01f6
        L_0x01e1:
            r0 = move-exception
            goto L_0x0202
        L_0x01e3:
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu     // Catch:{ all -> 0x01e1 }
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()     // Catch:{ all -> 0x01e1 }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zze()     // Catch:{ all -> 0x01e1 }
            java.lang.String r14 = "Failed to load conditional user property from local database"
            r0.zza(r14)     // Catch:{ all -> 0x01e1 }
            r15.recycle()     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            r0 = 0
        L_0x01f6:
            if (r0 == 0) goto L_0x015f
            com.google.android.gms.measurement.internal.zzgu r14 = new com.google.android.gms.measurement.internal.zzgu     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            r14.<init>(r0, r12, r6)     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            r8.add(r14)     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            goto L_0x015f
        L_0x0202:
            r15.recycle()     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            throw r0     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
        L_0x0206:
            r15 = 4
            if (r0 != r15) goto L_0x0245
            android.os.Parcel r15 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            int r0 = r14.length     // Catch:{ ParseException -> 0x0222 }
            r15.unmarshall(r14, r9, r0)     // Catch:{ ParseException -> 0x0222 }
            r15.setDataPosition(r9)     // Catch:{ ParseException -> 0x0222 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzbf> r0 = com.google.android.gms.measurement.internal.zzbf.CREATOR     // Catch:{ ParseException -> 0x0222 }
            java.lang.Object r0 = r0.createFromParcel(r15)     // Catch:{ ParseException -> 0x0222 }
            com.google.android.gms.measurement.internal.zzbf r0 = (com.google.android.gms.measurement.internal.zzbf) r0     // Catch:{ ParseException -> 0x0222 }
            r15.recycle()     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            goto L_0x0235
        L_0x0220:
            r0 = move-exception
            goto L_0x0241
        L_0x0222:
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu     // Catch:{ all -> 0x0220 }
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()     // Catch:{ all -> 0x0220 }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zze()     // Catch:{ all -> 0x0220 }
            java.lang.String r14 = "Failed to load default event parameters from local database"
            r0.zza(r14)     // Catch:{ all -> 0x0220 }
            r15.recycle()     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            r0 = 0
        L_0x0235:
            if (r0 == 0) goto L_0x015f
            com.google.android.gms.measurement.internal.zzgu r14 = new com.google.android.gms.measurement.internal.zzgu     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            r14.<init>(r0, r12, r6)     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            r8.add(r14)     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            goto L_0x015f
        L_0x0241:
            r15.recycle()     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            throw r0     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
        L_0x0245:
            r6 = 3
            if (r0 != r6) goto L_0x0258
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzk()     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            java.lang.String r7 = "Skipping app launch break"
            r0.zza(r7)     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            goto L_0x0267
        L_0x0258:
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zze()     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            java.lang.String r7 = "Unknown record type in local database"
            r0.zza(r7)     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
        L_0x0267:
            r7 = r8
            r8 = 2
            r12 = 1
            goto L_0x00f5
        L_0x026c:
            r0 = move-exception
            r8 = r7
            goto L_0x0163
        L_0x0270:
            r8 = r7
            goto L_0x0167
        L_0x0273:
            r0 = move-exception
            r8 = r7
            goto L_0x016c
        L_0x0277:
            r8 = r7
            java.lang.String r0 = "messages"
            java.lang.String r6 = "rowid <= ?"
            java.lang.String r7 = java.lang.Long.toString(r22)     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            java.lang.String[] r7 = new java.lang.String[]{r7}     // Catch:{ SQLiteFullException -> 0x016b, SQLiteDatabaseLockedException -> 0x0167, SQLiteException -> 0x0162, all -> 0x0121 }
            r12 = r28
            int r0 = r12.delete(r0, r6, r7)     // Catch:{ SQLiteFullException -> 0x02a4, SQLiteDatabaseLockedException -> 0x02b9, SQLiteException -> 0x02a2, all -> 0x02a0 }
            int r6 = r8.size()     // Catch:{ SQLiteFullException -> 0x02a4, SQLiteDatabaseLockedException -> 0x02b9, SQLiteException -> 0x02a2, all -> 0x02a0 }
            if (r0 >= r6) goto L_0x02a6
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu     // Catch:{ SQLiteFullException -> 0x02a4, SQLiteDatabaseLockedException -> 0x02b9, SQLiteException -> 0x02a2, all -> 0x02a0 }
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()     // Catch:{ SQLiteFullException -> 0x02a4, SQLiteDatabaseLockedException -> 0x02b9, SQLiteException -> 0x02a2, all -> 0x02a0 }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zze()     // Catch:{ SQLiteFullException -> 0x02a4, SQLiteDatabaseLockedException -> 0x02b9, SQLiteException -> 0x02a2, all -> 0x02a0 }
            java.lang.String r6 = "Fewer entries removed from local database than expected"
            r0.zza(r6)     // Catch:{ SQLiteFullException -> 0x02a4, SQLiteDatabaseLockedException -> 0x02b9, SQLiteException -> 0x02a2, all -> 0x02a0 }
            goto L_0x02a6
        L_0x02a0:
            r0 = move-exception
            goto L_0x02b3
        L_0x02a2:
            r0 = move-exception
            goto L_0x02b7
        L_0x02a4:
            r0 = move-exception
            goto L_0x02bc
        L_0x02a6:
            r12.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x02a4, SQLiteDatabaseLockedException -> 0x02b9, SQLiteException -> 0x02a2, all -> 0x02a0 }
            r12.endTransaction()     // Catch:{ SQLiteFullException -> 0x02a4, SQLiteDatabaseLockedException -> 0x02b9, SQLiteException -> 0x02a2, all -> 0x02a0 }
            r13.close()
            r12.close()
            return r8
        L_0x02b3:
            r15 = r12
        L_0x02b4:
            r6 = r13
            goto L_0x0347
        L_0x02b7:
            r15 = r12
            goto L_0x02ea
        L_0x02b9:
            r15 = r12
            goto L_0x0313
        L_0x02bc:
            r15 = r12
            goto L_0x0328
        L_0x02bf:
            r0 = move-exception
            r12 = r28
            r8 = r7
            goto L_0x02c7
        L_0x02c4:
            r0 = move-exception
            r8 = r7
            r12 = r15
        L_0x02c7:
            r13 = 0
        L_0x02c8:
            if (r13 == 0) goto L_0x02d4
            r13.close()     // Catch:{ SQLiteFullException -> 0x02d2, SQLiteDatabaseLockedException -> 0x02dc, SQLiteException -> 0x02d0, all -> 0x02ce }
            goto L_0x02d4
        L_0x02ce:
            r0 = move-exception
            goto L_0x02d5
        L_0x02d0:
            r0 = move-exception
            goto L_0x02d9
        L_0x02d2:
            r0 = move-exception
            goto L_0x02df
        L_0x02d4:
            throw r0     // Catch:{ SQLiteFullException -> 0x02d2, SQLiteDatabaseLockedException -> 0x02dc, SQLiteException -> 0x02d0, all -> 0x02ce }
        L_0x02d5:
            r15 = r12
            r6 = 0
            goto L_0x0347
        L_0x02d9:
            r15 = r12
            r13 = 0
            goto L_0x02ea
        L_0x02dc:
            r15 = r12
            r13 = 0
            goto L_0x0313
        L_0x02df:
            r15 = r12
            r13 = 0
            goto L_0x0328
        L_0x02e2:
            r0 = move-exception
            r6 = 0
            r15 = 0
            goto L_0x0347
        L_0x02e6:
            r0 = move-exception
            r8 = r7
            r13 = 0
            r15 = 0
        L_0x02ea:
            if (r15 == 0) goto L_0x02f8
            boolean r6 = r15.inTransaction()     // Catch:{ all -> 0x02f6 }
            if (r6 == 0) goto L_0x02f8
            r15.endTransaction()     // Catch:{ all -> 0x02f6 }
            goto L_0x02f8
        L_0x02f6:
            r0 = move-exception
            goto L_0x02b4
        L_0x02f8:
            com.google.android.gms.measurement.internal.zzio r6 = r1.zzu     // Catch:{ all -> 0x02f6 }
            com.google.android.gms.measurement.internal.zzhe r6 = r6.zzaW()     // Catch:{ all -> 0x02f6 }
            com.google.android.gms.measurement.internal.zzhc r6 = r6.zze()     // Catch:{ all -> 0x02f6 }
            r6.zzb(r2, r0)     // Catch:{ all -> 0x02f6 }
            r6 = 1
            r1.zzc = r6     // Catch:{ all -> 0x02f6 }
            if (r13 == 0) goto L_0x030d
            r13.close()
        L_0x030d:
            if (r15 == 0) goto L_0x0340
            goto L_0x0320
        L_0x0310:
            r8 = r7
            r13 = 0
            r15 = 0
        L_0x0313:
            long r6 = (long) r11
            android.os.SystemClock.sleep(r6)     // Catch:{ all -> 0x02f6 }
            int r11 = r11 + 20
            if (r13 == 0) goto L_0x031e
            r13.close()
        L_0x031e:
            if (r15 == 0) goto L_0x0340
        L_0x0320:
            r15.close()
            goto L_0x0340
        L_0x0324:
            r0 = move-exception
            r8 = r7
            r13 = 0
            r15 = 0
        L_0x0328:
            com.google.android.gms.measurement.internal.zzio r6 = r1.zzu     // Catch:{ all -> 0x02f6 }
            com.google.android.gms.measurement.internal.zzhe r6 = r6.zzaW()     // Catch:{ all -> 0x02f6 }
            com.google.android.gms.measurement.internal.zzhc r6 = r6.zze()     // Catch:{ all -> 0x02f6 }
            r6.zzb(r2, r0)     // Catch:{ all -> 0x02f6 }
            r6 = 1
            r1.zzc = r6     // Catch:{ all -> 0x02f6 }
            if (r13 == 0) goto L_0x033d
            r13.close()
        L_0x033d:
            if (r15 == 0) goto L_0x0340
            goto L_0x0320
        L_0x0340:
            int r10 = r10 + 1
            r7 = r8
            r6 = 0
            r8 = 5
            goto L_0x0022
        L_0x0347:
            if (r6 == 0) goto L_0x034c
            r6.close()
        L_0x034c:
            if (r15 == 0) goto L_0x0351
            r15.close()
        L_0x0351:
            throw r0
        L_0x0352:
            com.google.android.gms.measurement.internal.zzio r0 = r1.zzu
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzk()
            java.lang.String r2 = "Failed to read events from database in reasonable time"
            r0.zza(r2)
            r2 = 0
            return r2
        L_0x0363:
            r8 = r7
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzgv.zzi(int):java.util.List");
    }

    public final void zzj() {
        int delete;
        zzg();
        try {
            SQLiteDatabase zzh = zzh();
            if (zzh != null && (delete = zzh.delete(i.e, (String) null, (String[]) null)) > 0) {
                this.zzu.zzaW().zzj().zzb("Reset local analytics data. records", Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            this.zzu.zzaW().zze().zzb("Error resetting local analytics data. error", e);
        }
    }

    public final boolean zzk() {
        return zzs(3, new byte[0]);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzl() {
        zzio zzio = this.zzu;
        Context zzaT = zzio.zzaT();
        zzio.zzf();
        return zzaT.getDatabasePath("google_app_measurement_local.db").exists();
    }

    public final boolean zzm() {
        zzg();
        if (!this.zzc && zzl()) {
            int i = 5;
            int i2 = 0;
            while (true) {
                if (i2 >= 5) {
                    this.zzu.zzaW().zzk().zza("Error deleting app launch break from local database in reasonable time");
                    break;
                }
                SQLiteDatabase sQLiteDatabase = null;
                try {
                    SQLiteDatabase zzh = zzh();
                    if (zzh == null) {
                        this.zzc = true;
                    } else {
                        zzh.beginTransaction();
                        zzh.delete(i.e, "type == ?", new String[]{Integer.toString(3)});
                        zzh.setTransactionSuccessful();
                        zzh.endTransaction();
                        zzh.close();
                        return true;
                    }
                } catch (SQLiteFullException e) {
                    this.zzu.zzaW().zze().zzb("Error deleting app launch break from local database", e);
                    this.zzc = true;
                    if (sQLiteDatabase == null) {
                    }
                    sQLiteDatabase.close();
                } catch (SQLiteDatabaseLockedException unused) {
                    SystemClock.sleep((long) i);
                    i += 20;
                    if (sQLiteDatabase == null) {
                    }
                    sQLiteDatabase.close();
                } catch (SQLiteException e2) {
                    if (sQLiteDatabase != null) {
                        if (sQLiteDatabase.inTransaction()) {
                            sQLiteDatabase.endTransaction();
                        }
                    }
                    this.zzu.zzaW().zze().zzb("Error deleting app launch break from local database", e2);
                    this.zzc = true;
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                } catch (Throwable th) {
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                    throw th;
                }
                i2++;
            }
        }
        return false;
    }

    public final boolean zzn(zzai zzai) {
        zzio zzio = this.zzu;
        byte[] zzay = zzio.zzw().zzay(zzai);
        if (zzay.length <= 131072) {
            return zzs(2, zzay);
        }
        zzio.zzaW().zzh().zza("Conditional user property too long for local database. Sending directly to service");
        return false;
    }

    public final boolean zzo(zzbf zzbf) {
        zzio zzio = this.zzu;
        byte[] zzay = zzio.zzw().zzay(zzbf);
        if (zzay == null) {
            zzio.zzaW().zzh().zza("Null default event parameters; not writing to database");
            return false;
        } else if (zzay.length <= 131072) {
            return zzs(4, zzay);
        } else {
            zzio.zzaW().zzh().zza("Default event parameters too long for local database. Sending directly to service");
            return false;
        }
    }

    public final boolean zzp(zzbh zzbh) {
        Parcel obtain = Parcel.obtain();
        zzbi.zza(zzbh, obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zzs(0, marshall);
        }
        this.zzu.zzaW().zzh().zza("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean zzq(zzqb zzqb) {
        Parcel obtain = Parcel.obtain();
        zzqc.zza(zzqb, obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zzs(1, marshall);
        }
        this.zzu.zzaW().zzh().zza("User property too long for local database. Sending directly to service");
        return false;
    }
}
