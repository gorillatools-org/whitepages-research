package com.google.android.gms.measurement.internal;

import java.util.Map;
import java.util.Set;

final class zzae extends zzpg {
    private String zza;
    private Set zzb;
    private Map zzc;
    private Long zzd;
    private Long zze;

    zzae(zzpv zzpv) {
        super(zzpv);
    }

    private final zzy zzd(Integer num) {
        if (this.zzc.containsKey(num)) {
            return (zzy) this.zzc.get(num);
        }
        zzy zzy = new zzy(this, this.zza, (zzad) null);
        this.zzc.put(num, zzy);
        return zzy;
    }

    private final boolean zzf(int i, int i2) {
        zzy zzy = (zzy) this.zzc.get(Integer.valueOf(i));
        if (zzy == null) {
            return false;
        }
        return zzy.zze.get(i2);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x02ee, code lost:
        if (r5 != null) goto L_0x02c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:265:0x0676, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:267:0x0678, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:283:0x06b2, code lost:
        r5 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:285:0x06b6, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:286:0x06b8, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:287:0x06b9, code lost:
        r28 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:290:0x06c2, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:295:0x06e5, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:364:0x0873, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:367:0x0879, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:371:0x0896, code lost:
        if (r13 == null) goto L_0x0899;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:374:0x089f, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:409:0x09b4, code lost:
        if (r13 != false) goto L_0x09bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0170, code lost:
        if (r5 != null) goto L_0x014f;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x024c  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0256  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x025e  */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x03fc  */
    /* JADX WARNING: Removed duplicated region for block: B:237:0x05b8  */
    /* JADX WARNING: Removed duplicated region for block: B:265:0x0676 A[Catch:{ SQLiteException -> 0x0678, all -> 0x0676 }, ExcHandler: all (th java.lang.Throwable), Splitter:B:252:0x063f] */
    /* JADX WARNING: Removed duplicated region for block: B:285:0x06b6 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:247:0x0629] */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x06e5  */
    /* JADX WARNING: Removed duplicated region for block: B:299:0x06ef  */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x070b  */
    /* JADX WARNING: Removed duplicated region for block: B:322:0x079f  */
    /* JADX WARNING: Removed duplicated region for block: B:364:0x0873 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:330:0x07ea] */
    /* JADX WARNING: Removed duplicated region for block: B:374:0x089f  */
    /* JADX WARNING: Removed duplicated region for block: B:429:0x0a66  */
    /* JADX WARNING: Removed duplicated region for block: B:432:0x0a6e  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0175  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x01aa A[Catch:{ SQLiteException -> 0x01bb }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01c2 A[SYNTHETIC, Splitter:B:71:0x01c2] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List zza(java.lang.String r30, java.util.List r31, java.util.List r32, java.lang.Long r33, java.lang.Long r34, boolean r35) {
        /*
            r29 = this;
            r10 = r29
            java.lang.String r11 = "current_results"
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r30)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r31)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r32)
            r0 = r30
            r10.zza = r0
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            r10.zzb = r0
            androidx.collection.ArrayMap r0 = new androidx.collection.ArrayMap
            r0.<init>()
            r10.zzc = r0
            r0 = r33
            r10.zzd = r0
            r0 = r34
            r10.zze = r0
            java.util.Iterator r0 = r31.iterator()
        L_0x002b:
            boolean r1 = r0.hasNext()
            r12 = 0
            r13 = 1
            if (r1 == 0) goto L_0x0047
            java.lang.Object r1 = r0.next()
            com.google.android.gms.internal.measurement.zzhm r1 = (com.google.android.gms.internal.measurement.zzhm) r1
            java.lang.String r1 = r1.zzh()
            java.lang.String r2 = "_s"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x002b
            r1 = r13
            goto L_0x0048
        L_0x0047:
            r1 = r12
        L_0x0048:
            com.google.android.gms.internal.measurement.zzpq.zzb()
            com.google.android.gms.measurement.internal.zzio r0 = r10.zzu
            com.google.android.gms.measurement.internal.zzam r2 = r0.zzf()
            java.lang.String r3 = r10.zza
            com.google.android.gms.measurement.internal.zzgg r4 = com.google.android.gms.measurement.internal.zzgi.zzaE
            boolean r14 = r2.zzx(r3, r4)
            com.google.android.gms.internal.measurement.zzpq.zzb()
            com.google.android.gms.measurement.internal.zzam r0 = r0.zzf()
            java.lang.String r2 = r10.zza
            com.google.android.gms.measurement.internal.zzgg r3 = com.google.android.gms.measurement.internal.zzgi.zzaD
            boolean r15 = r0.zzx(r2, r3)
            if (r1 == 0) goto L_0x00ad
            com.google.android.gms.measurement.internal.zzpv r0 = r10.zzg
            com.google.android.gms.measurement.internal.zzaw r2 = r0.zzj()
            java.lang.String r3 = r10.zza
            r2.zzav()
            r2.zzg()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3)
            android.content.ContentValues r0 = new android.content.ContentValues
            r0.<init>()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r12)
            java.lang.String r5 = "current_session_count"
            r0.put(r5, r4)
            android.database.sqlite.SQLiteDatabase r4 = r2.zzj()     // Catch:{ SQLiteException -> 0x0099 }
            java.lang.String r5 = "events"
            java.lang.String r6 = "app_id = ?"
            java.lang.String[] r7 = new java.lang.String[]{r3}     // Catch:{ SQLiteException -> 0x0099 }
            r4.update(r5, r0, r6, r7)     // Catch:{ SQLiteException -> 0x0099 }
            goto L_0x00ad
        L_0x0099:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzio r2 = r2.zzu
            com.google.android.gms.measurement.internal.zzhe r2 = r2.zzaW()
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zze()
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzhe.zzn(r3)
            java.lang.String r4 = "Error resetting session-scoped event counts. appId"
            r2.zzc(r4, r3, r0)
        L_0x00ad:
            java.util.Map r0 = java.util.Collections.emptyMap()
            java.lang.String r9 = "Failed to merge filter. appId"
            java.lang.String r8 = "Database error querying filters. appId"
            java.lang.String r7 = "data"
            java.lang.String r6 = "audience_id"
            if (r15 == 0) goto L_0x0152
            if (r14 == 0) goto L_0x0152
            com.google.android.gms.measurement.internal.zzpv r0 = r10.zzg
            com.google.android.gms.measurement.internal.zzaw r2 = r0.zzj()
            java.lang.String r3 = r10.zza
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3)
            androidx.collection.ArrayMap r4 = new androidx.collection.ArrayMap
            r4.<init>()
            android.database.sqlite.SQLiteDatabase r16 = r2.zzj()
            java.lang.String r17 = "event_filters"
            java.lang.String[] r18 = new java.lang.String[]{r6, r7}     // Catch:{ SQLiteException -> 0x0156, all -> 0x0154 }
            java.lang.String r19 = "app_id=?"
            java.lang.String[] r20 = new java.lang.String[]{r3}     // Catch:{ SQLiteException -> 0x0156, all -> 0x0154 }
            r22 = 0
            r23 = 0
            r21 = 0
            android.database.Cursor r5 = r16.query(r17, r18, r19, r20, r21, r22, r23)     // Catch:{ SQLiteException -> 0x0156, all -> 0x0154 }
            boolean r0 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x0123 }
            if (r0 == 0) goto L_0x014b
        L_0x00ed:
            byte[] r0 = r5.getBlob(r13)     // Catch:{ SQLiteException -> 0x0123 }
            com.google.android.gms.internal.measurement.zzfi r13 = com.google.android.gms.internal.measurement.zzfj.zzc()     // Catch:{ IOException -> 0x012b }
            com.google.android.gms.internal.measurement.zzng r0 = com.google.android.gms.measurement.internal.zzqa.zzp(r13, r0)     // Catch:{ IOException -> 0x012b }
            com.google.android.gms.internal.measurement.zzfi r0 = (com.google.android.gms.internal.measurement.zzfi) r0     // Catch:{ IOException -> 0x012b }
            com.google.android.gms.internal.measurement.zzmd r0 = r0.zzba()     // Catch:{ IOException -> 0x012b }
            com.google.android.gms.internal.measurement.zzfj r0 = (com.google.android.gms.internal.measurement.zzfj) r0     // Catch:{ IOException -> 0x012b }
            boolean r13 = r0.zzo()     // Catch:{ SQLiteException -> 0x0123 }
            if (r13 != 0) goto L_0x0108
            goto L_0x013d
        L_0x0108:
            int r13 = r5.getInt(r12)     // Catch:{ SQLiteException -> 0x0123 }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)     // Catch:{ SQLiteException -> 0x0123 }
            java.lang.Object r16 = r4.get(r13)     // Catch:{ SQLiteException -> 0x0123 }
            java.util.List r16 = (java.util.List) r16     // Catch:{ SQLiteException -> 0x0123 }
            if (r16 != 0) goto L_0x0125
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0123 }
            r12.<init>()     // Catch:{ SQLiteException -> 0x0123 }
            r4.put(r13, r12)     // Catch:{ SQLiteException -> 0x0123 }
            goto L_0x0127
        L_0x0121:
            r0 = move-exception
            goto L_0x0173
        L_0x0123:
            r0 = move-exception
            goto L_0x015b
        L_0x0125:
            r12 = r16
        L_0x0127:
            r12.add(r0)     // Catch:{ SQLiteException -> 0x0123 }
            goto L_0x013d
        L_0x012b:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzio r12 = r2.zzu     // Catch:{ SQLiteException -> 0x0123 }
            com.google.android.gms.measurement.internal.zzhe r12 = r12.zzaW()     // Catch:{ SQLiteException -> 0x0123 }
            com.google.android.gms.measurement.internal.zzhc r12 = r12.zze()     // Catch:{ SQLiteException -> 0x0123 }
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzhe.zzn(r3)     // Catch:{ SQLiteException -> 0x0123 }
            r12.zzc(r9, r13, r0)     // Catch:{ SQLiteException -> 0x0123 }
        L_0x013d:
            boolean r0 = r5.moveToNext()     // Catch:{ SQLiteException -> 0x0123 }
            if (r0 != 0) goto L_0x0148
            r5.close()
            r12 = r4
            goto L_0x0179
        L_0x0148:
            r12 = 0
            r13 = 1
            goto L_0x00ed
        L_0x014b:
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x0123 }
        L_0x014f:
            r5.close()
        L_0x0152:
            r12 = r0
            goto L_0x0179
        L_0x0154:
            r0 = move-exception
            goto L_0x0158
        L_0x0156:
            r0 = move-exception
            goto L_0x015a
        L_0x0158:
            r5 = 0
            goto L_0x0173
        L_0x015a:
            r5 = 0
        L_0x015b:
            com.google.android.gms.measurement.internal.zzio r2 = r2.zzu     // Catch:{ all -> 0x0121 }
            com.google.android.gms.measurement.internal.zzhe r2 = r2.zzaW()     // Catch:{ all -> 0x0121 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zze()     // Catch:{ all -> 0x0121 }
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzhe.zzn(r3)     // Catch:{ all -> 0x0121 }
            r2.zzc(r8, r3, r0)     // Catch:{ all -> 0x0121 }
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ all -> 0x0121 }
            if (r5 == 0) goto L_0x0152
            goto L_0x014f
        L_0x0173:
            if (r5 == 0) goto L_0x0178
            r5.close()
        L_0x0178:
            throw r0
        L_0x0179:
            com.google.android.gms.measurement.internal.zzpv r0 = r10.zzg
            com.google.android.gms.measurement.internal.zzaw r2 = r0.zzj()
            java.lang.String r3 = r10.zza
            r2.zzav()
            r2.zzg()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3)
            android.database.sqlite.SQLiteDatabase r16 = r2.zzj()
            java.lang.String r17 = "audience_filter_values"
            java.lang.String[] r18 = new java.lang.String[]{r6, r11}     // Catch:{ SQLiteException -> 0x0229, all -> 0x0227 }
            java.lang.String r19 = "app_id=?"
            java.lang.String[] r20 = new java.lang.String[]{r3}     // Catch:{ SQLiteException -> 0x0229, all -> 0x0227 }
            r22 = 0
            r23 = 0
            r21 = 0
            android.database.Cursor r4 = r16.query(r17, r18, r19, r20, r21, r22, r23)     // Catch:{ SQLiteException -> 0x0229, all -> 0x0227 }
            boolean r0 = r4.moveToFirst()     // Catch:{ SQLiteException -> 0x01bb }
            if (r0 != 0) goto L_0x01c2
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x01bb }
            r4.close()
            r13 = r0
            r18 = r6
            r19 = r7
            goto L_0x0250
        L_0x01b8:
            r0 = move-exception
            goto L_0x0224
        L_0x01bb:
            r0 = move-exception
            r18 = r6
        L_0x01be:
            r19 = r7
            goto L_0x0233
        L_0x01c2:
            androidx.collection.ArrayMap r5 = new androidx.collection.ArrayMap     // Catch:{ SQLiteException -> 0x01bb }
            r5.<init>()     // Catch:{ SQLiteException -> 0x01bb }
        L_0x01c7:
            r13 = 0
            int r16 = r4.getInt(r13)     // Catch:{ SQLiteException -> 0x01bb }
            r13 = 1
            byte[] r0 = r4.getBlob(r13)     // Catch:{ SQLiteException -> 0x01bb }
            com.google.android.gms.internal.measurement.zzib r13 = com.google.android.gms.internal.measurement.zzic.zze()     // Catch:{ IOException -> 0x01ef }
            com.google.android.gms.internal.measurement.zzng r0 = com.google.android.gms.measurement.internal.zzqa.zzp(r13, r0)     // Catch:{ IOException -> 0x01ef }
            com.google.android.gms.internal.measurement.zzib r0 = (com.google.android.gms.internal.measurement.zzib) r0     // Catch:{ IOException -> 0x01ef }
            com.google.android.gms.internal.measurement.zzmd r0 = r0.zzba()     // Catch:{ IOException -> 0x01ef }
            com.google.android.gms.internal.measurement.zzic r0 = (com.google.android.gms.internal.measurement.zzic) r0     // Catch:{ IOException -> 0x01ef }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r16)     // Catch:{ SQLiteException -> 0x01bb }
            r5.put(r13, r0)     // Catch:{ SQLiteException -> 0x01bb }
            r17 = r5
            r18 = r6
            r19 = r7
            goto L_0x020d
        L_0x01ef:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzio r13 = r2.zzu     // Catch:{ SQLiteException -> 0x01bb }
            com.google.android.gms.measurement.internal.zzhe r13 = r13.zzaW()     // Catch:{ SQLiteException -> 0x01bb }
            com.google.android.gms.measurement.internal.zzhc r13 = r13.zze()     // Catch:{ SQLiteException -> 0x01bb }
            r17 = r5
            java.lang.String r5 = "Failed to merge filter results. appId, audienceId, error"
            r18 = r6
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzhe.zzn(r3)     // Catch:{ SQLiteException -> 0x0222 }
            r19 = r7
            java.lang.Integer r7 = java.lang.Integer.valueOf(r16)     // Catch:{ SQLiteException -> 0x0220 }
            r13.zzd(r5, r6, r7, r0)     // Catch:{ SQLiteException -> 0x0220 }
        L_0x020d:
            boolean r0 = r4.moveToNext()     // Catch:{ SQLiteException -> 0x0220 }
            if (r0 != 0) goto L_0x0219
            r4.close()
            r13 = r17
            goto L_0x0250
        L_0x0219:
            r5 = r17
            r6 = r18
            r7 = r19
            goto L_0x01c7
        L_0x0220:
            r0 = move-exception
            goto L_0x0233
        L_0x0222:
            r0 = move-exception
            goto L_0x01be
        L_0x0224:
            r5 = r4
            goto L_0x0a6c
        L_0x0227:
            r0 = move-exception
            goto L_0x022f
        L_0x0229:
            r0 = move-exception
            r18 = r6
            r19 = r7
            goto L_0x0232
        L_0x022f:
            r5 = 0
            goto L_0x0a6c
        L_0x0232:
            r4 = 0
        L_0x0233:
            com.google.android.gms.measurement.internal.zzio r2 = r2.zzu     // Catch:{ all -> 0x01b8 }
            com.google.android.gms.measurement.internal.zzhe r2 = r2.zzaW()     // Catch:{ all -> 0x01b8 }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zze()     // Catch:{ all -> 0x01b8 }
            java.lang.String r5 = "Database error querying filter results. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzhe.zzn(r3)     // Catch:{ all -> 0x01b8 }
            r2.zzc(r5, r3, r0)     // Catch:{ all -> 0x01b8 }
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ all -> 0x01b8 }
            if (r4 == 0) goto L_0x024f
            r4.close()
        L_0x024f:
            r13 = r0
        L_0x0250:
            boolean r0 = r13.isEmpty()
            if (r0 == 0) goto L_0x025e
            r12 = r8
            r13 = r9
            r27 = r18
            r28 = r19
            goto L_0x05aa
        L_0x025e:
            java.util.HashSet r2 = new java.util.HashSet
            java.util.Set r0 = r13.keySet()
            r2.<init>(r0)
            if (r1 == 0) goto L_0x0400
            java.lang.String r1 = r10.zza
            com.google.android.gms.measurement.internal.zzpv r0 = r10.zzg
            com.google.android.gms.measurement.internal.zzaw r3 = r0.zzj()
            java.lang.String r4 = r10.zza
            r3.zzav()
            r3.zzg()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r4)
            androidx.collection.ArrayMap r0 = new androidx.collection.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r5 = r3.zzj()
            java.lang.String r6 = "select audience_id, filter_id from event_filters where app_id = ? and session_scoped = 1 UNION select audience_id, filter_id from property_filters where app_id = ? and session_scoped = 1;"
            java.lang.String[] r7 = new java.lang.String[]{r4, r4}     // Catch:{ SQLiteException -> 0x02d1, all -> 0x02cf }
            android.database.Cursor r5 = r5.rawQuery(r6, r7)     // Catch:{ SQLiteException -> 0x02d1, all -> 0x02cf }
            boolean r6 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x02b3 }
            if (r6 == 0) goto L_0x02ca
        L_0x0295:
            r6 = 0
            int r7 = r5.getInt(r6)     // Catch:{ SQLiteException -> 0x02b3 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r7)     // Catch:{ SQLiteException -> 0x02b3 }
            java.lang.Object r7 = r0.get(r6)     // Catch:{ SQLiteException -> 0x02b3 }
            java.util.List r7 = (java.util.List) r7     // Catch:{ SQLiteException -> 0x02b3 }
            if (r7 != 0) goto L_0x02ae
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x02b3 }
            r7.<init>()     // Catch:{ SQLiteException -> 0x02b3 }
            r0.put(r6, r7)     // Catch:{ SQLiteException -> 0x02b3 }
        L_0x02ae:
            r6 = 1
            goto L_0x02b5
        L_0x02b0:
            r0 = move-exception
            goto L_0x03fa
        L_0x02b3:
            r0 = move-exception
            goto L_0x02d7
        L_0x02b5:
            int r16 = r5.getInt(r6)     // Catch:{ SQLiteException -> 0x02b3 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r16)     // Catch:{ SQLiteException -> 0x02b3 }
            r7.add(r6)     // Catch:{ SQLiteException -> 0x02b3 }
            boolean r6 = r5.moveToNext()     // Catch:{ SQLiteException -> 0x02b3 }
            if (r6 != 0) goto L_0x0295
        L_0x02c6:
            r5.close()
            goto L_0x02f1
        L_0x02ca:
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x02b3 }
            goto L_0x02c6
        L_0x02cf:
            r0 = move-exception
            goto L_0x02d3
        L_0x02d1:
            r0 = move-exception
            goto L_0x02d6
        L_0x02d3:
            r5 = 0
            goto L_0x03fa
        L_0x02d6:
            r5 = 0
        L_0x02d7:
            com.google.android.gms.measurement.internal.zzio r3 = r3.zzu     // Catch:{ all -> 0x02b0 }
            com.google.android.gms.measurement.internal.zzhe r3 = r3.zzaW()     // Catch:{ all -> 0x02b0 }
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zze()     // Catch:{ all -> 0x02b0 }
            java.lang.String r6 = "Database error querying scoped filters. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzhe.zzn(r4)     // Catch:{ all -> 0x02b0 }
            r3.zzc(r6, r4, r0)     // Catch:{ all -> 0x02b0 }
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ all -> 0x02b0 }
            if (r5 == 0) goto L_0x02f1
            goto L_0x02c6
        L_0x02f1:
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r1)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r13)
            androidx.collection.ArrayMap r1 = new androidx.collection.ArrayMap
            r1.<init>()
            boolean r3 = r13.isEmpty()
            if (r3 == 0) goto L_0x0306
        L_0x0302:
            r22 = r8
            goto L_0x03f8
        L_0x0306:
            java.util.Set r3 = r13.keySet()
            java.util.Iterator r3 = r3.iterator()
        L_0x030e:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0302
            java.lang.Object r4 = r3.next()
            java.lang.Integer r4 = (java.lang.Integer) r4
            r4.intValue()
            java.lang.Object r5 = r13.get(r4)
            com.google.android.gms.internal.measurement.zzic r5 = (com.google.android.gms.internal.measurement.zzic) r5
            java.lang.Object r6 = r0.get(r4)
            java.util.List r6 = (java.util.List) r6
            if (r6 == 0) goto L_0x0331
            boolean r7 = r6.isEmpty()
            if (r7 == 0) goto L_0x0339
        L_0x0331:
            r16 = r0
            r17 = r3
            r22 = r8
            goto L_0x03f4
        L_0x0339:
            com.google.android.gms.measurement.internal.zzpv r7 = r10.zzg
            r16 = r0
            com.google.android.gms.measurement.internal.zzqa r0 = r7.zzA()
            r17 = r3
            java.util.List r3 = r5.zzi()
            java.util.List r0 = r0.zzt(r3, r6)
            boolean r3 = r0.isEmpty()
            if (r3 != 0) goto L_0x03ee
            com.google.android.gms.internal.measurement.zzlz r3 = r5.zzch()
            com.google.android.gms.internal.measurement.zzib r3 = (com.google.android.gms.internal.measurement.zzib) r3
            r3.zzf()
            r3.zzb(r0)
            com.google.android.gms.measurement.internal.zzqa r0 = r7.zzA()
            java.util.List r7 = r5.zzk()
            java.util.List r0 = r0.zzt(r7, r6)
            r3.zzh()
            r3.zzd(r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.List r7 = r5.zzh()
            java.util.Iterator r7 = r7.iterator()
        L_0x037c:
            boolean r20 = r7.hasNext()
            if (r20 == 0) goto L_0x03a4
            java.lang.Object r20 = r7.next()
            r21 = r7
            r7 = r20
            com.google.android.gms.internal.measurement.zzhk r7 = (com.google.android.gms.internal.measurement.zzhk) r7
            int r20 = r7.zza()
            r22 = r8
            java.lang.Integer r8 = java.lang.Integer.valueOf(r20)
            boolean r8 = r6.contains(r8)
            if (r8 != 0) goto L_0x039f
            r0.add(r7)
        L_0x039f:
            r7 = r21
            r8 = r22
            goto L_0x037c
        L_0x03a4:
            r22 = r8
            r3.zze()
            r3.zza(r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.List r5 = r5.zzj()
            java.util.Iterator r5 = r5.iterator()
        L_0x03b9:
            boolean r7 = r5.hasNext()
            if (r7 == 0) goto L_0x03d7
            java.lang.Object r7 = r5.next()
            com.google.android.gms.internal.measurement.zzie r7 = (com.google.android.gms.internal.measurement.zzie) r7
            int r8 = r7.zzb()
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            boolean r8 = r6.contains(r8)
            if (r8 != 0) goto L_0x03b9
            r0.add(r7)
            goto L_0x03b9
        L_0x03d7:
            r3.zzg()
            r3.zzc(r0)
            com.google.android.gms.internal.measurement.zzmd r0 = r3.zzba()
            com.google.android.gms.internal.measurement.zzic r0 = (com.google.android.gms.internal.measurement.zzic) r0
            r1.put(r4, r0)
        L_0x03e6:
            r0 = r16
            r3 = r17
            r8 = r22
            goto L_0x030e
        L_0x03ee:
            r0 = r16
            r3 = r17
            goto L_0x030e
        L_0x03f4:
            r1.put(r4, r5)
            goto L_0x03e6
        L_0x03f8:
            r0 = r1
            goto L_0x0403
        L_0x03fa:
            if (r5 == 0) goto L_0x03ff
            r5.close()
        L_0x03ff:
            throw r0
        L_0x0400:
            r22 = r8
            r0 = r13
        L_0x0403:
            java.util.Iterator r16 = r2.iterator()
        L_0x0407:
            boolean r1 = r16.hasNext()
            if (r1 == 0) goto L_0x05a3
            java.lang.Object r1 = r16.next()
            r8 = r1
            java.lang.Integer r8 = (java.lang.Integer) r8
            r8.intValue()
            java.lang.Object r1 = r0.get(r8)
            com.google.android.gms.internal.measurement.zzic r1 = (com.google.android.gms.internal.measurement.zzic) r1
            java.util.BitSet r5 = new java.util.BitSet
            r5.<init>()
            java.util.BitSet r6 = new java.util.BitSet
            r6.<init>()
            androidx.collection.ArrayMap r7 = new androidx.collection.ArrayMap
            r7.<init>()
            if (r1 == 0) goto L_0x046b
            int r2 = r1.zza()
            if (r2 != 0) goto L_0x0435
            goto L_0x046b
        L_0x0435:
            java.util.List r2 = r1.zzh()
            java.util.Iterator r2 = r2.iterator()
        L_0x043d:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x046b
            java.lang.Object r3 = r2.next()
            com.google.android.gms.internal.measurement.zzhk r3 = (com.google.android.gms.internal.measurement.zzhk) r3
            boolean r4 = r3.zzh()
            if (r4 == 0) goto L_0x043d
            int r4 = r3.zza()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            boolean r17 = r3.zzg()
            if (r17 == 0) goto L_0x0466
            long r20 = r3.zzb()
            java.lang.Long r3 = java.lang.Long.valueOf(r20)
            goto L_0x0467
        L_0x0466:
            r3 = 0
        L_0x0467:
            r7.put(r4, r3)
            goto L_0x043d
        L_0x046b:
            androidx.collection.ArrayMap r4 = new androidx.collection.ArrayMap
            r4.<init>()
            if (r1 == 0) goto L_0x0478
            int r2 = r1.zzc()
            if (r2 != 0) goto L_0x047b
        L_0x0478:
            r20 = r0
            goto L_0x04bd
        L_0x047b:
            java.util.List r2 = r1.zzj()
            java.util.Iterator r2 = r2.iterator()
        L_0x0483:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0478
            java.lang.Object r3 = r2.next()
            com.google.android.gms.internal.measurement.zzie r3 = (com.google.android.gms.internal.measurement.zzie) r3
            boolean r17 = r3.zzi()
            if (r17 == 0) goto L_0x0483
            int r17 = r3.zza()
            if (r17 <= 0) goto L_0x0483
            int r17 = r3.zzb()
            r20 = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r17)
            int r17 = r3.zza()
            r21 = r2
            int r2 = r17 + -1
            long r2 = r3.zzc(r2)
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r4.put(r0, r2)
            r0 = r20
            r2 = r21
            goto L_0x0483
        L_0x04bd:
            if (r1 == 0) goto L_0x0506
            r0 = 0
        L_0x04c0:
            int r2 = r1.zzd()
            int r2 = r2 * 64
            if (r0 >= r2) goto L_0x0506
            java.util.List r2 = r1.zzk()
            boolean r2 = com.google.android.gms.measurement.internal.zzqa.zzy(r2, r0)
            if (r2 == 0) goto L_0x04f8
            com.google.android.gms.measurement.internal.zzio r2 = r10.zzu
            com.google.android.gms.measurement.internal.zzhe r2 = r2.zzaW()
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zzj()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r0)
            r17 = r9
            java.lang.String r9 = "Filter already evaluated. audience ID, filter ID"
            r2.zzc(r9, r8, r3)
            r6.set(r0)
            java.util.List r2 = r1.zzi()
            boolean r2 = com.google.android.gms.measurement.internal.zzqa.zzy(r2, r0)
            if (r2 == 0) goto L_0x04fa
            r5.set(r0)
            goto L_0x0501
        L_0x04f8:
            r17 = r9
        L_0x04fa:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)
            r7.remove(r2)
        L_0x0501:
            int r0 = r0 + 1
            r9 = r17
            goto L_0x04c0
        L_0x0506:
            r17 = r9
            java.lang.Object r0 = r13.get(r8)
            com.google.android.gms.internal.measurement.zzic r0 = (com.google.android.gms.internal.measurement.zzic) r0
            if (r15 == 0) goto L_0x056e
            if (r14 == 0) goto L_0x056e
            java.lang.Object r1 = r12.get(r8)
            java.util.List r1 = (java.util.List) r1
            if (r1 == 0) goto L_0x056e
            java.lang.Long r2 = r10.zze
            if (r2 == 0) goto L_0x056e
            java.lang.Long r2 = r10.zzd
            if (r2 != 0) goto L_0x0523
            goto L_0x056e
        L_0x0523:
            java.util.Iterator r1 = r1.iterator()
        L_0x0527:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x056e
            java.lang.Object r2 = r1.next()
            com.google.android.gms.internal.measurement.zzfj r2 = (com.google.android.gms.internal.measurement.zzfj) r2
            int r3 = r2.zzb()
            java.lang.Long r9 = r10.zze
            long r23 = r9.longValue()
            r25 = 1000(0x3e8, double:4.94E-321)
            long r23 = r23 / r25
            boolean r2 = r2.zzm()
            if (r2 == 0) goto L_0x054f
            java.lang.Long r2 = r10.zzd
            long r23 = r2.longValue()
            long r23 = r23 / r25
        L_0x054f:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)
            boolean r3 = r7.containsKey(r2)
            if (r3 == 0) goto L_0x0560
            java.lang.Long r3 = java.lang.Long.valueOf(r23)
            r7.put(r2, r3)
        L_0x0560:
            boolean r3 = r4.containsKey(r2)
            if (r3 == 0) goto L_0x0527
            java.lang.Long r3 = java.lang.Long.valueOf(r23)
            r4.put(r2, r3)
            goto L_0x0527
        L_0x056e:
            com.google.android.gms.measurement.internal.zzy r9 = new com.google.android.gms.measurement.internal.zzy
            java.lang.String r3 = r10.zza
            r21 = 0
            r1 = r9
            r2 = r29
            r23 = r4
            r4 = r0
            r27 = r18
            r28 = r19
            r0 = r8
            r18 = r12
            r12 = r22
            r8 = r23
            r30 = r13
            r13 = r17
            r17 = r14
            r14 = r9
            r9 = r21
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            java.util.Map r1 = r10.zzc
            r1.put(r0, r14)
            r9 = r13
            r14 = r17
            r12 = r18
            r0 = r20
            r18 = r27
            r13 = r30
            goto L_0x0407
        L_0x05a3:
            r13 = r9
            r27 = r18
            r28 = r19
            r12 = r22
        L_0x05aa:
            boolean r0 = r31.isEmpty()
            java.lang.String r1 = "Skipping failed audience ID"
            if (r0 == 0) goto L_0x05b8
        L_0x05b2:
            r24 = r11
            r11 = r27
            goto L_0x079d
        L_0x05b8:
            com.google.android.gms.measurement.internal.zzz r2 = new com.google.android.gms.measurement.internal.zzz
            r3 = 0
            r2.<init>(r10, r3)
            androidx.collection.ArrayMap r4 = new androidx.collection.ArrayMap
            r4.<init>()
            java.util.Iterator r5 = r31.iterator()
        L_0x05c7:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x05b2
            java.lang.Object r0 = r5.next()
            com.google.android.gms.internal.measurement.zzhm r0 = (com.google.android.gms.internal.measurement.zzhm) r0
            java.lang.String r6 = r10.zza
            com.google.android.gms.internal.measurement.zzhm r6 = r2.zza(r6, r0)
            if (r6 == 0) goto L_0x0799
            com.google.android.gms.measurement.internal.zzpv r7 = r10.zzg
            com.google.android.gms.measurement.internal.zzaw r8 = r7.zzj()
            java.lang.String r9 = r10.zza
            java.lang.String r14 = r6.zzh()
            com.google.android.gms.measurement.internal.zzbd r8 = r8.zzr(r9, r0, r14)
            com.google.android.gms.measurement.internal.zzaw r0 = r7.zzj()
            r0.zzV(r8)
            if (r35 != 0) goto L_0x0799
            long r14 = r8.zzc
            java.lang.String r9 = r6.zzh()
            java.lang.Object r0 = r4.get(r9)
            java.util.Map r0 = (java.util.Map) r0
            if (r0 != 0) goto L_0x06f3
            com.google.android.gms.measurement.internal.zzaw r7 = r7.zzj()
            java.lang.String r3 = r10.zza
            r7.zzav()
            r7.zzg()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r9)
            r30 = r2
            androidx.collection.ArrayMap r2 = new androidx.collection.ArrayMap
            r2.<init>()
            android.database.sqlite.SQLiteDatabase r16 = r7.zzj()
            java.lang.String r17 = "event_filters"
            r31 = r5
            r24 = r11
            r11 = r27
            r5 = r28
            java.lang.String[] r18 = new java.lang.String[]{r11, r5}     // Catch:{ SQLiteException -> 0x06bc, all -> 0x06b6 }
            java.lang.String r19 = "app_id=? AND event_name=?"
            java.lang.String[] r20 = new java.lang.String[]{r3, r9}     // Catch:{ SQLiteException -> 0x06bc, all -> 0x06b6 }
            r22 = 0
            r23 = 0
            r21 = 0
            r25 = r14
            android.database.Cursor r14 = r16.query(r17, r18, r19, r20, r21, r22, r23)     // Catch:{ SQLiteException -> 0x06b8, all -> 0x06b6 }
            boolean r0 = r14.moveToFirst()     // Catch:{ SQLiteException -> 0x0682, all -> 0x0676 }
            if (r0 == 0) goto L_0x06a8
        L_0x0645:
            r15 = 1
            byte[] r0 = r14.getBlob(r15)     // Catch:{ SQLiteException -> 0x0682, all -> 0x0676 }
            com.google.android.gms.internal.measurement.zzfi r15 = com.google.android.gms.internal.measurement.zzfj.zzc()     // Catch:{ IOException -> 0x0686 }
            com.google.android.gms.internal.measurement.zzng r0 = com.google.android.gms.measurement.internal.zzqa.zzp(r15, r0)     // Catch:{ IOException -> 0x0686 }
            com.google.android.gms.internal.measurement.zzfi r0 = (com.google.android.gms.internal.measurement.zzfi) r0     // Catch:{ IOException -> 0x0686 }
            com.google.android.gms.internal.measurement.zzmd r0 = r0.zzba()     // Catch:{ IOException -> 0x0686 }
            com.google.android.gms.internal.measurement.zzfj r0 = (com.google.android.gms.internal.measurement.zzfj) r0     // Catch:{ IOException -> 0x0686 }
            r15 = 0
            int r16 = r14.getInt(r15)     // Catch:{ SQLiteException -> 0x0682, all -> 0x0676 }
            java.lang.Integer r15 = java.lang.Integer.valueOf(r16)     // Catch:{ SQLiteException -> 0x0682, all -> 0x0676 }
            java.lang.Object r16 = r2.get(r15)     // Catch:{ SQLiteException -> 0x0682, all -> 0x0676 }
            java.util.List r16 = (java.util.List) r16     // Catch:{ SQLiteException -> 0x0682, all -> 0x0676 }
            if (r16 != 0) goto L_0x067a
            r28 = r5
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0678, all -> 0x0676 }
            r5.<init>()     // Catch:{ SQLiteException -> 0x0678, all -> 0x0676 }
            r2.put(r15, r5)     // Catch:{ SQLiteException -> 0x0678, all -> 0x0676 }
            goto L_0x067e
        L_0x0676:
            r0 = move-exception
            goto L_0x06b2
        L_0x0678:
            r0 = move-exception
            goto L_0x06b4
        L_0x067a:
            r28 = r5
            r5 = r16
        L_0x067e:
            r5.add(r0)     // Catch:{ SQLiteException -> 0x0678, all -> 0x0676 }
            goto L_0x069a
        L_0x0682:
            r0 = move-exception
            r28 = r5
            goto L_0x06b4
        L_0x0686:
            r0 = move-exception
            r28 = r5
            com.google.android.gms.measurement.internal.zzio r5 = r7.zzu     // Catch:{ SQLiteException -> 0x0678, all -> 0x0676 }
            com.google.android.gms.measurement.internal.zzhe r5 = r5.zzaW()     // Catch:{ SQLiteException -> 0x0678, all -> 0x0676 }
            com.google.android.gms.measurement.internal.zzhc r5 = r5.zze()     // Catch:{ SQLiteException -> 0x0678, all -> 0x0676 }
            java.lang.Object r15 = com.google.android.gms.measurement.internal.zzhe.zzn(r3)     // Catch:{ SQLiteException -> 0x0678, all -> 0x0676 }
            r5.zzc(r13, r15, r0)     // Catch:{ SQLiteException -> 0x0678, all -> 0x0676 }
        L_0x069a:
            boolean r0 = r14.moveToNext()     // Catch:{ SQLiteException -> 0x0678, all -> 0x0676 }
            if (r0 != 0) goto L_0x06a5
            r14.close()
            r0 = r2
            goto L_0x06e8
        L_0x06a5:
            r5 = r28
            goto L_0x0645
        L_0x06a8:
            r28 = r5
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x0678, all -> 0x0676 }
            r14.close()
            goto L_0x06e8
        L_0x06b2:
            r5 = r14
            goto L_0x06ed
        L_0x06b4:
            r5 = r14
            goto L_0x06ce
        L_0x06b6:
            r0 = move-exception
            goto L_0x06c2
        L_0x06b8:
            r0 = move-exception
            r28 = r5
            goto L_0x06cd
        L_0x06bc:
            r0 = move-exception
            r28 = r5
            r25 = r14
            goto L_0x06cd
        L_0x06c2:
            r5 = 0
            goto L_0x06ed
        L_0x06c4:
            r0 = move-exception
            r31 = r5
            r24 = r11
            r25 = r14
            r11 = r27
        L_0x06cd:
            r5 = 0
        L_0x06ce:
            com.google.android.gms.measurement.internal.zzio r2 = r7.zzu     // Catch:{ all -> 0x06ec }
            com.google.android.gms.measurement.internal.zzhe r2 = r2.zzaW()     // Catch:{ all -> 0x06ec }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zze()     // Catch:{ all -> 0x06ec }
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzhe.zzn(r3)     // Catch:{ all -> 0x06ec }
            r2.zzc(r12, r3, r0)     // Catch:{ all -> 0x06ec }
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ all -> 0x06ec }
            if (r5 == 0) goto L_0x06e8
            r5.close()
        L_0x06e8:
            r4.put(r9, r0)
            goto L_0x06fd
        L_0x06ec:
            r0 = move-exception
        L_0x06ed:
            if (r5 == 0) goto L_0x06f2
            r5.close()
        L_0x06f2:
            throw r0
        L_0x06f3:
            r30 = r2
            r31 = r5
            r24 = r11
            r25 = r14
            r11 = r27
        L_0x06fd:
            java.util.Set r2 = r0.keySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x0705:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x078e
            java.lang.Object r3 = r2.next()
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r5 = r3.intValue()
            java.util.Set r7 = r10.zzb
            boolean r7 = r7.contains(r3)
            if (r7 == 0) goto L_0x072b
            com.google.android.gms.measurement.internal.zzio r5 = r10.zzu
            com.google.android.gms.measurement.internal.zzhe r5 = r5.zzaW()
            com.google.android.gms.measurement.internal.zzhc r5 = r5.zzj()
            r5.zzb(r1, r3)
            goto L_0x0705
        L_0x072b:
            java.lang.Object r7 = r0.get(r3)
            java.util.List r7 = (java.util.List) r7
            java.util.Iterator r7 = r7.iterator()
            r9 = 1
        L_0x0736:
            boolean r14 = r7.hasNext()
            if (r14 == 0) goto L_0x077d
            java.lang.Object r9 = r7.next()
            com.google.android.gms.internal.measurement.zzfj r9 = (com.google.android.gms.internal.measurement.zzfj) r9
            com.google.android.gms.measurement.internal.zzaa r15 = new com.google.android.gms.measurement.internal.zzaa
            java.lang.String r14 = r10.zza
            r15.<init>(r10, r14, r5, r9)
            java.lang.Long r14 = r10.zzd
            r22 = r0
            java.lang.Long r0 = r10.zze
            int r9 = r9.zzb()
            boolean r21 = r10.zzf(r5, r9)
            r9 = r14
            r14 = r15
            r23 = r2
            r2 = r15
            r15 = r9
            r16 = r0
            r17 = r6
            r18 = r25
            r20 = r8
            boolean r9 = r14.zzd(r15, r16, r17, r18, r20, r21)
            if (r9 == 0) goto L_0x0777
            com.google.android.gms.measurement.internal.zzy r0 = r10.zzd(r3)
            r0.zzc(r2)
            r0 = r22
            r2 = r23
            goto L_0x0736
        L_0x0777:
            java.util.Set r0 = r10.zzb
            r0.add(r3)
            goto L_0x0781
        L_0x077d:
            r22 = r0
            r23 = r2
        L_0x0781:
            if (r9 != 0) goto L_0x0788
            java.util.Set r0 = r10.zzb
            r0.add(r3)
        L_0x0788:
            r0 = r22
            r2 = r23
            goto L_0x0705
        L_0x078e:
            r2 = r30
            r5 = r31
            r27 = r11
            r11 = r24
            r3 = 0
            goto L_0x05c7
        L_0x0799:
            r24 = r11
            goto L_0x05c7
        L_0x079d:
            if (r35 != 0) goto L_0x0a66
            boolean r0 = r32.isEmpty()
            if (r0 == 0) goto L_0x07a7
            goto L_0x09c1
        L_0x07a7:
            androidx.collection.ArrayMap r2 = new androidx.collection.ArrayMap
            r2.<init>()
            java.util.Iterator r3 = r32.iterator()
        L_0x07b0:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x09c1
            java.lang.Object r0 = r3.next()
            r4 = r0
            com.google.android.gms.internal.measurement.zzio r4 = (com.google.android.gms.internal.measurement.zzio) r4
            java.lang.String r5 = r4.zzg()
            java.lang.Object r0 = r2.get(r5)
            java.util.Map r0 = (java.util.Map) r0
            if (r0 != 0) goto L_0x08a3
            com.google.android.gms.measurement.internal.zzpv r0 = r10.zzg
            com.google.android.gms.measurement.internal.zzaw r6 = r0.zzj()
            java.lang.String r7 = r10.zza
            r6.zzav()
            r6.zzg()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r7)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r5)
            androidx.collection.ArrayMap r8 = new androidx.collection.ArrayMap
            r8.<init>()
            android.database.sqlite.SQLiteDatabase r13 = r6.zzj()
            java.lang.String r14 = "property_filters"
            r9 = r28
            java.lang.String[] r15 = new java.lang.String[]{r11, r9}     // Catch:{ SQLiteException -> 0x0875, all -> 0x0873 }
            java.lang.String r16 = "app_id=? AND property_name=?"
            java.lang.String[] r17 = new java.lang.String[]{r7, r5}     // Catch:{ SQLiteException -> 0x0875, all -> 0x0873 }
            r19 = 0
            r20 = 0
            r18 = 0
            android.database.Cursor r13 = r13.query(r14, r15, r16, r17, r18, r19, r20)     // Catch:{ SQLiteException -> 0x0875, all -> 0x0873 }
            boolean r0 = r13.moveToFirst()     // Catch:{ SQLiteException -> 0x0835 }
            if (r0 == 0) goto L_0x0867
        L_0x0804:
            r14 = 1
            byte[] r0 = r13.getBlob(r14)     // Catch:{ SQLiteException -> 0x0835 }
            com.google.android.gms.internal.measurement.zzfq r15 = com.google.android.gms.internal.measurement.zzfr.zzc()     // Catch:{ IOException -> 0x0841 }
            com.google.android.gms.internal.measurement.zzng r0 = com.google.android.gms.measurement.internal.zzqa.zzp(r15, r0)     // Catch:{ IOException -> 0x0841 }
            com.google.android.gms.internal.measurement.zzfq r0 = (com.google.android.gms.internal.measurement.zzfq) r0     // Catch:{ IOException -> 0x0841 }
            com.google.android.gms.internal.measurement.zzmd r0 = r0.zzba()     // Catch:{ IOException -> 0x0841 }
            com.google.android.gms.internal.measurement.zzfr r0 = (com.google.android.gms.internal.measurement.zzfr) r0     // Catch:{ IOException -> 0x0841 }
            r15 = 0
            int r16 = r13.getInt(r15)     // Catch:{ SQLiteException -> 0x0835 }
            java.lang.Integer r14 = java.lang.Integer.valueOf(r16)     // Catch:{ SQLiteException -> 0x0835 }
            java.lang.Object r16 = r8.get(r14)     // Catch:{ SQLiteException -> 0x0835 }
            java.util.List r16 = (java.util.List) r16     // Catch:{ SQLiteException -> 0x0835 }
            if (r16 != 0) goto L_0x0839
            java.util.ArrayList r15 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0835 }
            r15.<init>()     // Catch:{ SQLiteException -> 0x0835 }
            r8.put(r14, r15)     // Catch:{ SQLiteException -> 0x0835 }
            goto L_0x083b
        L_0x0833:
            r0 = move-exception
            goto L_0x0871
        L_0x0835:
            r0 = move-exception
            r30 = r3
            goto L_0x0881
        L_0x0839:
            r15 = r16
        L_0x083b:
            r15.add(r0)     // Catch:{ SQLiteException -> 0x0835 }
            r30 = r3
            goto L_0x0857
        L_0x0841:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzio r14 = r6.zzu     // Catch:{ SQLiteException -> 0x0835 }
            com.google.android.gms.measurement.internal.zzhe r14 = r14.zzaW()     // Catch:{ SQLiteException -> 0x0835 }
            com.google.android.gms.measurement.internal.zzhc r14 = r14.zze()     // Catch:{ SQLiteException -> 0x0835 }
            java.lang.String r15 = "Failed to merge filter"
            r30 = r3
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzhe.zzn(r7)     // Catch:{ SQLiteException -> 0x0865 }
            r14.zzc(r15, r3, r0)     // Catch:{ SQLiteException -> 0x0865 }
        L_0x0857:
            boolean r0 = r13.moveToNext()     // Catch:{ SQLiteException -> 0x0865 }
            if (r0 != 0) goto L_0x0862
            r13.close()
            r0 = r8
            goto L_0x0899
        L_0x0862:
            r3 = r30
            goto L_0x0804
        L_0x0865:
            r0 = move-exception
            goto L_0x0881
        L_0x0867:
            r30 = r3
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x0865 }
        L_0x086d:
            r13.close()
            goto L_0x0899
        L_0x0871:
            r5 = r13
            goto L_0x089d
        L_0x0873:
            r0 = move-exception
            goto L_0x0879
        L_0x0875:
            r0 = move-exception
            r30 = r3
            goto L_0x0880
        L_0x0879:
            r5 = 0
            goto L_0x089d
        L_0x087b:
            r0 = move-exception
            r30 = r3
            r9 = r28
        L_0x0880:
            r13 = 0
        L_0x0881:
            com.google.android.gms.measurement.internal.zzio r3 = r6.zzu     // Catch:{ all -> 0x0833 }
            com.google.android.gms.measurement.internal.zzhe r3 = r3.zzaW()     // Catch:{ all -> 0x0833 }
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zze()     // Catch:{ all -> 0x0833 }
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzhe.zzn(r7)     // Catch:{ all -> 0x0833 }
            r3.zzc(r12, r6, r0)     // Catch:{ all -> 0x0833 }
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ all -> 0x0833 }
            if (r13 == 0) goto L_0x0899
            goto L_0x086d
        L_0x0899:
            r2.put(r5, r0)
            goto L_0x08a7
        L_0x089d:
            if (r5 == 0) goto L_0x08a2
            r5.close()
        L_0x08a2:
            throw r0
        L_0x08a3:
            r30 = r3
            r9 = r28
        L_0x08a7:
            java.util.Set r3 = r0.keySet()
            java.util.Iterator r3 = r3.iterator()
        L_0x08af:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x08d4
            java.lang.Object r5 = r3.next()
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r6 = r5.intValue()
            java.util.Set r7 = r10.zzb
            boolean r7 = r7.contains(r5)
            if (r7 == 0) goto L_0x08da
            com.google.android.gms.measurement.internal.zzio r0 = r10.zzu
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzj()
            r0.zzb(r1, r5)
        L_0x08d4:
            r3 = r30
            r28 = r9
            goto L_0x07b0
        L_0x08da:
            java.lang.Object r7 = r0.get(r5)
            java.util.List r7 = (java.util.List) r7
            java.util.Iterator r7 = r7.iterator()
            r13 = 1
        L_0x08e5:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x09b0
            java.lang.Object r8 = r7.next()
            com.google.android.gms.internal.measurement.zzfr r8 = (com.google.android.gms.internal.measurement.zzfr) r8
            com.google.android.gms.measurement.internal.zzio r13 = r10.zzu
            com.google.android.gms.measurement.internal.zzhe r14 = r13.zzaW()
            java.lang.String r14 = r14.zzr()
            r15 = 2
            boolean r14 = android.util.Log.isLoggable(r14, r15)
            if (r14 == 0) goto L_0x0949
            com.google.android.gms.measurement.internal.zzhe r14 = r13.zzaW()
            com.google.android.gms.measurement.internal.zzhc r14 = r14.zzj()
            boolean r15 = r8.zzj()
            if (r15 == 0) goto L_0x091b
            int r15 = r8.zza()
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)
            r31 = r0
            goto L_0x091e
        L_0x091b:
            r31 = r0
            r15 = 0
        L_0x091e:
            com.google.android.gms.measurement.internal.zzgx r0 = r13.zzj()
            r16 = r1
            java.lang.String r1 = r8.zze()
            java.lang.String r0 = r0.zzf(r1)
            java.lang.String r1 = "Evaluating filter. audience, filter, property"
            r14.zzd(r1, r5, r15, r0)
            com.google.android.gms.measurement.internal.zzhe r0 = r13.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzj()
            com.google.android.gms.measurement.internal.zzpv r1 = r10.zzg
            com.google.android.gms.measurement.internal.zzqa r1 = r1.zzA()
            java.lang.String r1 = r1.zzs(r8)
            java.lang.String r14 = "Filter definition"
            r0.zzb(r14, r1)
            goto L_0x094d
        L_0x0949:
            r31 = r0
            r16 = r1
        L_0x094d:
            boolean r0 = r8.zzj()
            if (r0 == 0) goto L_0x0988
            int r0 = r8.zza()
            r1 = 256(0x100, float:3.59E-43)
            if (r0 <= r1) goto L_0x095c
            goto L_0x0988
        L_0x095c:
            com.google.android.gms.measurement.internal.zzac r0 = new com.google.android.gms.measurement.internal.zzac
            java.lang.String r1 = r10.zza
            r0.<init>(r10, r1, r6, r8)
            java.lang.Long r1 = r10.zzd
            java.lang.Long r13 = r10.zze
            int r8 = r8.zza()
            boolean r8 = r10.zzf(r6, r8)
            boolean r13 = r0.zzd(r1, r13, r4, r8)
            if (r13 == 0) goto L_0x0982
            com.google.android.gms.measurement.internal.zzy r1 = r10.zzd(r5)
            r1.zzc(r0)
            r0 = r31
            r1 = r16
            goto L_0x08e5
        L_0x0982:
            java.util.Set r0 = r10.zzb
            r0.add(r5)
            goto L_0x09b4
        L_0x0988:
            com.google.android.gms.measurement.internal.zzhe r0 = r13.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzk()
            java.lang.String r1 = r10.zza
            java.lang.Object r1 = com.google.android.gms.measurement.internal.zzhe.zzn(r1)
            boolean r6 = r8.zzj()
            if (r6 == 0) goto L_0x09a5
            int r6 = r8.zza()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            goto L_0x09a6
        L_0x09a5:
            r6 = 0
        L_0x09a6:
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.String r7 = "Invalid property filter ID. appId, id"
            r0.zzc(r7, r1, r6)
            goto L_0x09b6
        L_0x09b0:
            r31 = r0
            r16 = r1
        L_0x09b4:
            if (r13 != 0) goto L_0x09bb
        L_0x09b6:
            java.util.Set r0 = r10.zzb
            r0.add(r5)
        L_0x09bb:
            r0 = r31
            r1 = r16
            goto L_0x08af
        L_0x09c1:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Map r0 = r10.zzc
            java.util.Set r0 = r0.keySet()
            java.util.Set r2 = r10.zzb
            r0.removeAll(r2)
            java.util.Iterator r2 = r0.iterator()
        L_0x09d5:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x0a65
            java.lang.Object r0 = r2.next()
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r3 = r0.intValue()
            java.util.Map r4 = r10.zzc
            java.lang.Object r4 = r4.get(r0)
            com.google.android.gms.measurement.internal.zzy r4 = (com.google.android.gms.measurement.internal.zzy) r4
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r4)
            com.google.android.gms.internal.measurement.zzhi r3 = r4.zza(r3)
            r1.add(r3)
            com.google.android.gms.measurement.internal.zzpv r4 = r10.zzg
            com.google.android.gms.measurement.internal.zzaw r4 = r4.zzj()
            java.lang.String r5 = r10.zza
            com.google.android.gms.internal.measurement.zzic r3 = r3.zzd()
            r4.zzav()
            r4.zzg()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r5)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r3)
            byte[] r3 = r3.zzcd()
            android.content.ContentValues r6 = new android.content.ContentValues
            r6.<init>()
            java.lang.String r7 = "app_id"
            r6.put(r7, r5)
            r6.put(r11, r0)
            r7 = r24
            r6.put(r7, r3)
            android.database.sqlite.SQLiteDatabase r0 = r4.zzj()     // Catch:{ SQLiteException -> 0x0a4f }
            java.lang.String r3 = "audience_filter_values"
            r8 = 5
            r9 = 0
            long r12 = r0.insertWithOnConflict(r3, r9, r6, r8)     // Catch:{ SQLiteException -> 0x0a4d }
            r14 = -1
            int r0 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r0 != 0) goto L_0x0a4a
            com.google.android.gms.measurement.internal.zzio r0 = r4.zzu     // Catch:{ SQLiteException -> 0x0a4d }
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()     // Catch:{ SQLiteException -> 0x0a4d }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zze()     // Catch:{ SQLiteException -> 0x0a4d }
            java.lang.String r3 = "Failed to insert filter results (got -1). appId"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzhe.zzn(r5)     // Catch:{ SQLiteException -> 0x0a4d }
            r0.zzb(r3, r6)     // Catch:{ SQLiteException -> 0x0a4d }
        L_0x0a4a:
            r24 = r7
            goto L_0x09d5
        L_0x0a4d:
            r0 = move-exception
            goto L_0x0a51
        L_0x0a4f:
            r0 = move-exception
            r9 = 0
        L_0x0a51:
            com.google.android.gms.measurement.internal.zzio r3 = r4.zzu
            com.google.android.gms.measurement.internal.zzhe r3 = r3.zzaW()
            com.google.android.gms.measurement.internal.zzhc r3 = r3.zze()
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzhe.zzn(r5)
            java.lang.String r5 = "Error storing filter results. appId"
            r3.zzc(r5, r4, r0)
            goto L_0x0a4a
        L_0x0a65:
            return r1
        L_0x0a66:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            return r0
        L_0x0a6c:
            if (r5 == 0) goto L_0x0a71
            r5.close()
        L_0x0a71:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzae.zza(java.lang.String, java.util.List, java.util.List, java.lang.Long, java.lang.Long, boolean):java.util.List");
    }

    /* access modifiers changed from: protected */
    public final boolean zzb() {
        return false;
    }
}
