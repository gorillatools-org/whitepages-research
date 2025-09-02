package com.google.android.gms.measurement.internal;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.gms.internal.measurement.zzbx;
import com.google.android.gms.internal.measurement.zzcc;
import java.io.File;

public final class zzax {
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0020, code lost:
        if (r2 == false) goto L_0x003d;
     */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0074 A[Catch:{ all -> 0x00cd, SQLiteException -> 0x009e }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a2 A[Catch:{ all -> 0x00cd, SQLiteException -> 0x009e }, LOOP:1: B:34:0x00a2->B:39:0x00b4, LOOP_START, PHI: r1 
      PHI: (r1v5 int) = (r1v4 int), (r1v6 int) binds: [B:33:0x00a0, B:39:0x00b4] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00bd A[Catch:{ all -> 0x00cd, SQLiteException -> 0x009e }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:59:? A[Catch:{  }, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void zza(com.google.android.gms.measurement.internal.zzhe r9, android.database.sqlite.SQLiteDatabase r10, java.lang.String r11, java.lang.String r12, java.lang.String r13, java.lang.String[] r14) throws android.database.sqlite.SQLiteException {
        /*
            if (r9 == 0) goto L_0x00e2
            r0 = 0
            java.lang.String r2 = "SQLITE_MASTER"
            java.lang.String r1 = "name"
            java.lang.String[] r3 = new java.lang.String[]{r1}     // Catch:{ SQLiteException -> 0x002c, all -> 0x0029 }
            java.lang.String r4 = "name=?"
            java.lang.String[] r5 = new java.lang.String[]{r11}     // Catch:{ SQLiteException -> 0x002c, all -> 0x0029 }
            r7 = 0
            r8 = 0
            r6 = 0
            r1 = r10
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x002c, all -> 0x0029 }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0027 }
            r1.close()
            if (r2 != 0) goto L_0x0040
            goto L_0x003d
        L_0x0023:
            r9 = move-exception
            r0 = r1
            goto L_0x00dc
        L_0x0027:
            r2 = move-exception
            goto L_0x002f
        L_0x0029:
            r9 = move-exception
            goto L_0x00dc
        L_0x002c:
            r1 = move-exception
            r2 = r1
            r1 = r0
        L_0x002f:
            com.google.android.gms.measurement.internal.zzhc r3 = r9.zzk()     // Catch:{ all -> 0x0023 }
            java.lang.String r4 = "Error querying for table"
            r3.zzc(r4, r11, r2)     // Catch:{ all -> 0x0023 }
            if (r1 == 0) goto L_0x003d
            r1.close()
        L_0x003d:
            r10.execSQL(r12)
        L_0x0040:
            java.util.HashSet r12 = new java.util.HashSet     // Catch:{ SQLiteException -> 0x009e }
            r12.<init>()     // Catch:{ SQLiteException -> 0x009e }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x009e }
            r1.<init>()     // Catch:{ SQLiteException -> 0x009e }
            java.lang.String r2 = "SELECT * FROM "
            r1.append(r2)     // Catch:{ SQLiteException -> 0x009e }
            r1.append(r11)     // Catch:{ SQLiteException -> 0x009e }
            java.lang.String r2 = " LIMIT 0"
            r1.append(r2)     // Catch:{ SQLiteException -> 0x009e }
            java.lang.String r1 = r1.toString()     // Catch:{ SQLiteException -> 0x009e }
            android.database.Cursor r0 = r10.rawQuery(r1, r0)     // Catch:{ SQLiteException -> 0x009e }
            java.lang.String[] r1 = r0.getColumnNames()     // Catch:{ all -> 0x00cd }
            java.util.Collections.addAll(r12, r1)     // Catch:{ all -> 0x00cd }
            r0.close()     // Catch:{ SQLiteException -> 0x009e }
            java.lang.String r0 = ","
            java.lang.String[] r13 = r13.split(r0)     // Catch:{ SQLiteException -> 0x009e }
            int r0 = r13.length     // Catch:{ SQLiteException -> 0x009e }
            r1 = 0
            r2 = r1
        L_0x0072:
            if (r2 >= r0) goto L_0x00a0
            r3 = r13[r2]     // Catch:{ SQLiteException -> 0x009e }
            boolean r4 = r12.remove(r3)     // Catch:{ SQLiteException -> 0x009e }
            if (r4 == 0) goto L_0x007f
            int r2 = r2 + 1
            goto L_0x0072
        L_0x007f:
            android.database.sqlite.SQLiteException r10 = new android.database.sqlite.SQLiteException     // Catch:{ SQLiteException -> 0x009e }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x009e }
            r12.<init>()     // Catch:{ SQLiteException -> 0x009e }
            java.lang.String r13 = "Table "
            r12.append(r13)     // Catch:{ SQLiteException -> 0x009e }
            r12.append(r11)     // Catch:{ SQLiteException -> 0x009e }
            java.lang.String r13 = " is missing required column: "
            r12.append(r13)     // Catch:{ SQLiteException -> 0x009e }
            r12.append(r3)     // Catch:{ SQLiteException -> 0x009e }
            java.lang.String r12 = r12.toString()     // Catch:{ SQLiteException -> 0x009e }
            r10.<init>(r12)     // Catch:{ SQLiteException -> 0x009e }
            throw r10     // Catch:{ SQLiteException -> 0x009e }
        L_0x009e:
            r10 = move-exception
            goto L_0x00d2
        L_0x00a0:
            if (r14 == 0) goto L_0x00b7
        L_0x00a2:
            int r13 = r14.length     // Catch:{ SQLiteException -> 0x009e }
            if (r1 >= r13) goto L_0x00b7
            r13 = r14[r1]     // Catch:{ SQLiteException -> 0x009e }
            boolean r13 = r12.remove(r13)     // Catch:{ SQLiteException -> 0x009e }
            if (r13 != 0) goto L_0x00b4
            int r13 = r1 + 1
            r13 = r14[r13]     // Catch:{ SQLiteException -> 0x009e }
            r10.execSQL(r13)     // Catch:{ SQLiteException -> 0x009e }
        L_0x00b4:
            int r1 = r1 + 2
            goto L_0x00a2
        L_0x00b7:
            boolean r10 = r12.isEmpty()     // Catch:{ SQLiteException -> 0x009e }
            if (r10 != 0) goto L_0x00cc
            com.google.android.gms.measurement.internal.zzhc r10 = r9.zzk()     // Catch:{ SQLiteException -> 0x009e }
            java.lang.String r13 = "Table has extra columns. table, columns"
            java.lang.String r14 = ", "
            java.lang.String r12 = android.text.TextUtils.join(r14, r12)     // Catch:{ SQLiteException -> 0x009e }
            r10.zzc(r13, r11, r12)     // Catch:{ SQLiteException -> 0x009e }
        L_0x00cc:
            return
        L_0x00cd:
            r10 = move-exception
            r0.close()     // Catch:{ SQLiteException -> 0x009e }
            throw r10     // Catch:{ SQLiteException -> 0x009e }
        L_0x00d2:
            com.google.android.gms.measurement.internal.zzhc r9 = r9.zze()
            java.lang.String r12 = "Failed to verify columns on table that was just created"
            r9.zzb(r12, r11)
            throw r10
        L_0x00dc:
            if (r0 == 0) goto L_0x00e1
            r0.close()
        L_0x00e1:
            throw r9
        L_0x00e2:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r10 = "Monitor must not be null"
            r9.<init>(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzax.zza(com.google.android.gms.measurement.internal.zzhe, android.database.sqlite.SQLiteDatabase, java.lang.String, java.lang.String, java.lang.String, java.lang.String[]):void");
    }

    static void zzb(zzhe zzhe, SQLiteDatabase sQLiteDatabase) {
        if (zzhe != null) {
            zzbx.zza();
            String path = sQLiteDatabase.getPath();
            int i = zzcc.zzb;
            File file = new File(path);
            if (!file.setReadable(false, false)) {
                zzhe.zzk().zza("Failed to turn off database read permission");
            }
            if (!file.setWritable(false, false)) {
                zzhe.zzk().zza("Failed to turn off database write permission");
            }
            if (!file.setReadable(true, true)) {
                zzhe.zzk().zza("Failed to turn on database read permission for owner");
            }
            if (!file.setWritable(true, true)) {
                zzhe.zzk().zza("Failed to turn on database write permission for owner");
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Monitor must not be null");
    }
}
