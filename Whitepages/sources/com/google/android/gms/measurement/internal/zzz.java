package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzhm;

final class zzz {
    final /* synthetic */ zzae zza;
    private zzhm zzb;
    private Long zzc;
    private long zzd;

    /* synthetic */ zzz(zzae zzae, zzad zzad) {
        this.zza = zzae;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v1, resolved type: java.lang.String} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00f1, code lost:
        if (r4 == null) goto L_0x0099;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01c0  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x01d4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzhm zza(java.lang.String r18, com.google.android.gms.internal.measurement.zzhm r19) {
        /*
            r17 = this;
            r1 = r17
            r3 = r18
            r8 = r19
            java.lang.String r0 = r19.zzh()
            java.util.List r9 = r19.zzi()
            com.google.android.gms.measurement.internal.zzae r2 = r1.zza
            com.google.android.gms.measurement.internal.zzpv r4 = r2.zzg
            r4.zzA()
            java.lang.String r5 = "_eid"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzqa.zzH(r8, r5)
            java.lang.Long r6 = (java.lang.Long) r6
            if (r6 == 0) goto L_0x0218
            java.lang.String r7 = "_ep"
            boolean r7 = r0.equals(r7)
            r10 = 0
            if (r7 == 0) goto L_0x01d8
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r6)
            r4.zzA()
            java.lang.String r0 = "_en"
            java.lang.Object r0 = com.google.android.gms.measurement.internal.zzqa.zzH(r8, r0)
            r12 = r0
            java.lang.String r12 = (java.lang.String) r12
            boolean r0 = android.text.TextUtils.isEmpty(r12)
            r7 = 0
            if (r0 == 0) goto L_0x004f
            com.google.android.gms.measurement.internal.zzio r0 = r2.zzu
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzh()
            java.lang.String r2 = "Extra parameter without an event name. eventId"
            r0.zzb(r2, r6)
            return r7
        L_0x004f:
            com.google.android.gms.internal.measurement.zzhm r0 = r1.zzb
            if (r0 == 0) goto L_0x0065
            java.lang.Long r0 = r1.zzc
            if (r0 == 0) goto L_0x0065
            long r13 = r6.longValue()
            java.lang.Long r0 = r1.zzc
            long r15 = r0.longValue()
            int r0 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x011b
        L_0x0065:
            com.google.android.gms.measurement.internal.zzaw r2 = r4.zzj()
            r2.zzg()
            r2.zzav()
            android.database.sqlite.SQLiteDatabase r0 = r2.zzj()     // Catch:{ SQLiteException -> 0x00e0, all -> 0x00dd }
            java.lang.String r4 = "select main_event, children_to_process from main_event_params where app_id=? and event_id=?"
            java.lang.String r13 = r6.toString()     // Catch:{ SQLiteException -> 0x00e0, all -> 0x00dd }
            java.lang.String[] r13 = new java.lang.String[]{r3, r13}     // Catch:{ SQLiteException -> 0x00e0, all -> 0x00dd }
            android.database.Cursor r4 = r0.rawQuery(r4, r13)     // Catch:{ SQLiteException -> 0x00e0, all -> 0x00dd }
            boolean r0 = r4.moveToFirst()     // Catch:{ SQLiteException -> 0x009d }
            if (r0 != 0) goto L_0x009f
            com.google.android.gms.measurement.internal.zzio r0 = r2.zzu     // Catch:{ SQLiteException -> 0x009d }
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()     // Catch:{ SQLiteException -> 0x009d }
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzj()     // Catch:{ SQLiteException -> 0x009d }
            java.lang.String r13 = "Main event not found"
            r0.zza(r13)     // Catch:{ SQLiteException -> 0x009d }
        L_0x0096:
            r4.close()
        L_0x0099:
            r0 = r7
            goto L_0x00f4
        L_0x009b:
            r0 = move-exception
            goto L_0x00da
        L_0x009d:
            r0 = move-exception
            goto L_0x00e2
        L_0x009f:
            r0 = 0
            byte[] r0 = r4.getBlob(r0)     // Catch:{ SQLiteException -> 0x009d }
            r13 = 1
            long r13 = r4.getLong(r13)     // Catch:{ SQLiteException -> 0x009d }
            java.lang.Long r13 = java.lang.Long.valueOf(r13)     // Catch:{ SQLiteException -> 0x009d }
            com.google.android.gms.internal.measurement.zzhl r14 = com.google.android.gms.internal.measurement.zzhm.zze()     // Catch:{ IOException -> 0x00c5 }
            com.google.android.gms.internal.measurement.zzng r0 = com.google.android.gms.measurement.internal.zzqa.zzp(r14, r0)     // Catch:{ IOException -> 0x00c5 }
            com.google.android.gms.internal.measurement.zzhl r0 = (com.google.android.gms.internal.measurement.zzhl) r0     // Catch:{ IOException -> 0x00c5 }
            com.google.android.gms.internal.measurement.zzmd r0 = r0.zzba()     // Catch:{ IOException -> 0x00c5 }
            com.google.android.gms.internal.measurement.zzhm r0 = (com.google.android.gms.internal.measurement.zzhm) r0     // Catch:{ IOException -> 0x00c5 }
            android.util.Pair r0 = android.util.Pair.create(r0, r13)     // Catch:{ SQLiteException -> 0x009d }
            r4.close()
            goto L_0x00f4
        L_0x00c5:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzio r13 = r2.zzu     // Catch:{ SQLiteException -> 0x009d }
            com.google.android.gms.measurement.internal.zzhe r13 = r13.zzaW()     // Catch:{ SQLiteException -> 0x009d }
            com.google.android.gms.measurement.internal.zzhc r13 = r13.zze()     // Catch:{ SQLiteException -> 0x009d }
            java.lang.String r14 = "Failed to merge main event. appId, eventId"
            java.lang.Object r15 = com.google.android.gms.measurement.internal.zzhe.zzn(r18)     // Catch:{ SQLiteException -> 0x009d }
            r13.zzd(r14, r15, r6, r0)     // Catch:{ SQLiteException -> 0x009d }
            goto L_0x0096
        L_0x00da:
            r7 = r4
            goto L_0x01d2
        L_0x00dd:
            r0 = move-exception
            goto L_0x01d2
        L_0x00e0:
            r0 = move-exception
            r4 = r7
        L_0x00e2:
            com.google.android.gms.measurement.internal.zzio r2 = r2.zzu     // Catch:{ all -> 0x009b }
            com.google.android.gms.measurement.internal.zzhe r2 = r2.zzaW()     // Catch:{ all -> 0x009b }
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zze()     // Catch:{ all -> 0x009b }
            java.lang.String r13 = "Error selecting main event"
            r2.zzb(r13, r0)     // Catch:{ all -> 0x009b }
            if (r4 == 0) goto L_0x0099
            goto L_0x0096
        L_0x00f4:
            if (r0 == 0) goto L_0x01c0
            java.lang.Object r2 = r0.first
            if (r2 != 0) goto L_0x00fc
            goto L_0x01c0
        L_0x00fc:
            com.google.android.gms.internal.measurement.zzhm r2 = (com.google.android.gms.internal.measurement.zzhm) r2
            r1.zzb = r2
            java.lang.Object r0 = r0.second
            java.lang.Long r0 = (java.lang.Long) r0
            long r13 = r0.longValue()
            r1.zzd = r13
            com.google.android.gms.measurement.internal.zzae r0 = r1.zza
            com.google.android.gms.measurement.internal.zzpv r0 = r0.zzg
            r0.zzA()
            com.google.android.gms.internal.measurement.zzhm r0 = r1.zzb
            java.lang.Object r0 = com.google.android.gms.measurement.internal.zzqa.zzH(r0, r5)
            java.lang.Long r0 = (java.lang.Long) r0
            r1.zzc = r0
        L_0x011b:
            long r4 = r1.zzd
            r13 = -1
            long r4 = r4 + r13
            r1.zzd = r4
            int r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r0 > 0) goto L_0x015f
            com.google.android.gms.measurement.internal.zzae r0 = r1.zza
            com.google.android.gms.measurement.internal.zzpv r0 = r0.zzg
            com.google.android.gms.measurement.internal.zzaw r2 = r0.zzj()
            r2.zzg()
            com.google.android.gms.measurement.internal.zzio r0 = r2.zzu
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzj()
            java.lang.String r4 = "Clearing complex main event info. appId"
            r0.zzb(r4, r3)
            android.database.sqlite.SQLiteDatabase r0 = r2.zzj()     // Catch:{ SQLiteException -> 0x014e }
            java.lang.String r4 = "delete from main_event_params where app_id=?"
            java.lang.String[] r3 = new java.lang.String[]{r18}     // Catch:{ SQLiteException -> 0x014e }
            r0.execSQL(r4, r3)     // Catch:{ SQLiteException -> 0x014e }
            goto L_0x0172
        L_0x014e:
            r0 = move-exception
            com.google.android.gms.measurement.internal.zzio r2 = r2.zzu
            com.google.android.gms.measurement.internal.zzhe r2 = r2.zzaW()
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zze()
            java.lang.String r3 = "Error clearing complex main event"
            r2.zzb(r3, r0)
            goto L_0x0172
        L_0x015f:
            com.google.android.gms.measurement.internal.zzae r0 = r1.zza
            com.google.android.gms.measurement.internal.zzpv r0 = r0.zzg
            com.google.android.gms.measurement.internal.zzaw r2 = r0.zzj()
            long r10 = r1.zzd
            com.google.android.gms.internal.measurement.zzhm r7 = r1.zzb
            r3 = r18
            r4 = r6
            r5 = r10
            r2.zzaf(r3, r4, r5, r7)
        L_0x0172:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            com.google.android.gms.internal.measurement.zzhm r2 = r1.zzb
            java.util.List r2 = r2.zzi()
            java.util.Iterator r2 = r2.iterator()
        L_0x0181:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x01a2
            java.lang.Object r3 = r2.next()
            com.google.android.gms.internal.measurement.zzhq r3 = (com.google.android.gms.internal.measurement.zzhq) r3
            com.google.android.gms.measurement.internal.zzae r4 = r1.zza
            com.google.android.gms.measurement.internal.zzpv r4 = r4.zzg
            r4.zzA()
            java.lang.String r4 = r3.zzg()
            com.google.android.gms.internal.measurement.zzhq r4 = com.google.android.gms.measurement.internal.zzqa.zzG(r8, r4)
            if (r4 != 0) goto L_0x0181
            r0.add(r3)
            goto L_0x0181
        L_0x01a2:
            boolean r2 = r0.isEmpty()
            if (r2 != 0) goto L_0x01ad
            r0.addAll(r9)
            r9 = r0
            goto L_0x01be
        L_0x01ad:
            com.google.android.gms.measurement.internal.zzae r0 = r1.zza
            com.google.android.gms.measurement.internal.zzio r0 = r0.zzu
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzh()
            java.lang.String r2 = "No unique parameters in main event. eventName"
            r0.zzb(r2, r12)
        L_0x01be:
            r0 = r12
            goto L_0x0218
        L_0x01c0:
            com.google.android.gms.measurement.internal.zzae r0 = r1.zza
            com.google.android.gms.measurement.internal.zzio r0 = r0.zzu
            com.google.android.gms.measurement.internal.zzhe r0 = r0.zzaW()
            com.google.android.gms.measurement.internal.zzhc r0 = r0.zzh()
            java.lang.String r2 = "Extra parameter without existing main event. eventName, eventId"
            r0.zzc(r2, r12, r6)
            return r7
        L_0x01d2:
            if (r7 == 0) goto L_0x01d7
            r7.close()
        L_0x01d7:
            throw r0
        L_0x01d8:
            r1.zzc = r6
            r1.zzb = r8
            r4.zzA()
            java.lang.Long r5 = java.lang.Long.valueOf(r10)
            java.lang.String r7 = "_epc"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzqa.zzI(r8, r7, r5)
            java.lang.Long r5 = (java.lang.Long) r5
            long r12 = r5.longValue()
            r1.zzd = r12
            int r5 = (r12 > r10 ? 1 : (r12 == r10 ? 0 : -1))
            if (r5 > 0) goto L_0x0205
            com.google.android.gms.measurement.internal.zzio r2 = r2.zzu
            com.google.android.gms.measurement.internal.zzhe r2 = r2.zzaW()
            com.google.android.gms.measurement.internal.zzhc r2 = r2.zzh()
            java.lang.String r3 = "Complex event with zero extra param count. eventName"
            r2.zzb(r3, r0)
            goto L_0x0218
        L_0x0205:
            com.google.android.gms.measurement.internal.zzaw r2 = r4.zzj()
            java.lang.Object r4 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r6)
            java.lang.Long r4 = (java.lang.Long) r4
            long r5 = r1.zzd
            r3 = r18
            r7 = r19
            r2.zzaf(r3, r4, r5, r7)
        L_0x0218:
            com.google.android.gms.internal.measurement.zzlz r2 = r19.zzch()
            com.google.android.gms.internal.measurement.zzhl r2 = (com.google.android.gms.internal.measurement.zzhl) r2
            r2.zzi(r0)
            r2.zzg()
            r2.zzd(r9)
            com.google.android.gms.internal.measurement.zzmd r0 = r2.zzba()
            com.google.android.gms.internal.measurement.zzhm r0 = (com.google.android.gms.internal.measurement.zzhm) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzz.zza(java.lang.String, com.google.android.gms.internal.measurement.zzhm):com.google.android.gms.internal.measurement.zzhm");
    }
}
