package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public final class zzjh implements zzjd {
    /* access modifiers changed from: private */
    public final AtomicBoolean zza = new AtomicBoolean();
    private HashMap zzb = null;
    private final HashMap zzc = new HashMap(16, 1.0f);
    private final HashMap zzd = new HashMap(16, 1.0f);
    private final HashMap zze = new HashMap(16, 1.0f);
    private final HashMap zzf = new HashMap(16, 1.0f);
    private Object zzg = null;
    private boolean zzh = false;
    private final String[] zzi = new String[0];

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0070, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r4 = com.google.android.gms.internal.measurement.zzjc.zza;
        r10 = r10.acquireUnstableContentProviderClient(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0078, code lost:
        if (r10 == null) goto L_0x00e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r1 = r10.query(r4, (java.lang.String[]) null, (java.lang.String) null, new java.lang.String[]{r11}, (java.lang.String) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0086, code lost:
        if (r1 == null) goto L_0x00c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x008c, code lost:
        if (r1.moveToFirst() == false) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x008e, code lost:
        r0 = r1.getString(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        r10.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0099, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x009b, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x009d, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r10.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a5, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a6, code lost:
        if (r0 == null) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ac, code lost:
        if (r0.equals((java.lang.Object) null) == false) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00ae, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00af, code lost:
        monitor-enter(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00b2, code lost:
        if (r12 != r9.zzg) goto L_0x00bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00b4, code lost:
        r9.zzb.put(r11, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00ba, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00bc, code lost:
        monitor-exit(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00bd, code lost:
        if (r0 == null) goto L_0x00c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00bf, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00c0, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00c2, code lost:
        throw r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00ca, code lost:
        throw new com.google.android.gms.internal.measurement.zzjg("ContentProvider query returned null cursor");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00cb, code lost:
        if (r1 != null) goto L_0x00cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00d1, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:?, code lost:
        r11.addSuppressed(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00dd, code lost:
        throw new com.google.android.gms.internal.measurement.zzjg("ContentProvider query failed", r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:?, code lost:
        r10.release();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x00e1, code lost:
        throw r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x00e9, code lost:
        throw new com.google.android.gms.internal.measurement.zzjg("Unable to acquire ContentProviderClient");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x00ea, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zza(android.content.ContentResolver r10, java.lang.String r11, java.lang.String r12) {
        /*
            r9 = this;
            if (r10 == 0) goto L_0x00ed
            monitor-enter(r9)
            java.util.HashMap r12 = r9.zzb     // Catch:{ all -> 0x002c }
            r0 = 1
            r1 = 0
            r2 = 0
            if (r12 != 0) goto L_0x002f
            java.util.concurrent.atomic.AtomicBoolean r12 = r9.zza     // Catch:{ all -> 0x002c }
            r12.set(r1)     // Catch:{ all -> 0x002c }
            java.util.HashMap r12 = new java.util.HashMap     // Catch:{ all -> 0x002c }
            r1 = 16
            r3 = 1065353216(0x3f800000, float:1.0)
            r12.<init>(r1, r3)     // Catch:{ all -> 0x002c }
            r9.zzb = r12     // Catch:{ all -> 0x002c }
            java.lang.Object r12 = new java.lang.Object     // Catch:{ all -> 0x002c }
            r12.<init>()     // Catch:{ all -> 0x002c }
            r9.zzg = r12     // Catch:{ all -> 0x002c }
            android.net.Uri r12 = com.google.android.gms.internal.measurement.zzjc.zza     // Catch:{ all -> 0x002c }
            com.google.android.gms.internal.measurement.zzjf r1 = new com.google.android.gms.internal.measurement.zzjf     // Catch:{ all -> 0x002c }
            r1.<init>(r9, r2)     // Catch:{ all -> 0x002c }
            r10.registerContentObserver(r12, r0, r1)     // Catch:{ all -> 0x002c }
            goto L_0x0059
        L_0x002c:
            r10 = move-exception
            goto L_0x00eb
        L_0x002f:
            java.util.concurrent.atomic.AtomicBoolean r12 = r9.zza     // Catch:{ all -> 0x002c }
            boolean r12 = r12.getAndSet(r1)     // Catch:{ all -> 0x002c }
            if (r12 == 0) goto L_0x0059
            java.util.HashMap r12 = r9.zzb     // Catch:{ all -> 0x002c }
            r12.clear()     // Catch:{ all -> 0x002c }
            java.util.HashMap r12 = r9.zzc     // Catch:{ all -> 0x002c }
            r12.clear()     // Catch:{ all -> 0x002c }
            java.util.HashMap r12 = r9.zzd     // Catch:{ all -> 0x002c }
            r12.clear()     // Catch:{ all -> 0x002c }
            java.util.HashMap r12 = r9.zze     // Catch:{ all -> 0x002c }
            r12.clear()     // Catch:{ all -> 0x002c }
            java.util.HashMap r12 = r9.zzf     // Catch:{ all -> 0x002c }
            r12.clear()     // Catch:{ all -> 0x002c }
            java.lang.Object r12 = new java.lang.Object     // Catch:{ all -> 0x002c }
            r12.<init>()     // Catch:{ all -> 0x002c }
            r9.zzg = r12     // Catch:{ all -> 0x002c }
            r9.zzh = r1     // Catch:{ all -> 0x002c }
        L_0x0059:
            java.lang.Object r12 = r9.zzg     // Catch:{ all -> 0x002c }
            java.util.HashMap r1 = r9.zzb     // Catch:{ all -> 0x002c }
            boolean r1 = r1.containsKey(r11)     // Catch:{ all -> 0x002c }
            if (r1 == 0) goto L_0x0071
            java.util.HashMap r10 = r9.zzb     // Catch:{ all -> 0x002c }
            java.lang.Object r10 = r10.get(r11)     // Catch:{ all -> 0x002c }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ all -> 0x002c }
            if (r10 != 0) goto L_0x006e
            goto L_0x006f
        L_0x006e:
            r2 = r10
        L_0x006f:
            monitor-exit(r9)     // Catch:{ all -> 0x002c }
            return r2
        L_0x0071:
            monitor-exit(r9)     // Catch:{ all -> 0x002c }
            android.net.Uri r4 = com.google.android.gms.internal.measurement.zzjc.zza     // Catch:{ zzjg -> 0x00ea }
            android.content.ContentProviderClient r10 = r10.acquireUnstableContentProviderClient(r4)     // Catch:{ zzjg -> 0x00ea }
            if (r10 == 0) goto L_0x00e2
            java.lang.String[] r7 = new java.lang.String[]{r11}     // Catch:{ RemoteException -> 0x009b }
            r8 = 0
            r5 = 0
            r6 = 0
            r3 = r10
            android.database.Cursor r1 = r3.query(r4, r5, r6, r7, r8)     // Catch:{ RemoteException -> 0x009b }
            if (r1 == 0) goto L_0x00c3
            boolean r3 = r1.moveToFirst()     // Catch:{ all -> 0x009d }
            if (r3 == 0) goto L_0x009f
            java.lang.String r0 = r1.getString(r0)     // Catch:{ all -> 0x009d }
            r1.close()     // Catch:{ RemoteException -> 0x009b }
            r10.release()     // Catch:{ zzjg -> 0x00ea }
            goto L_0x00a6
        L_0x0099:
            r11 = move-exception
            goto L_0x00de
        L_0x009b:
            r11 = move-exception
            goto L_0x00d6
        L_0x009d:
            r11 = move-exception
            goto L_0x00cb
        L_0x009f:
            r1.close()     // Catch:{ RemoteException -> 0x009b }
            r10.release()     // Catch:{ zzjg -> 0x00ea }
            r0 = r2
        L_0x00a6:
            if (r0 == 0) goto L_0x00af
            boolean r10 = r0.equals(r2)
            if (r10 == 0) goto L_0x00af
            r0 = r2
        L_0x00af:
            monitor-enter(r9)
            java.lang.Object r10 = r9.zzg     // Catch:{ all -> 0x00ba }
            if (r12 != r10) goto L_0x00bc
            java.util.HashMap r10 = r9.zzb     // Catch:{ all -> 0x00ba }
            r10.put(r11, r0)     // Catch:{ all -> 0x00ba }
            goto L_0x00bc
        L_0x00ba:
            r10 = move-exception
            goto L_0x00c1
        L_0x00bc:
            monitor-exit(r9)     // Catch:{ all -> 0x00ba }
            if (r0 == 0) goto L_0x00c0
            return r0
        L_0x00c0:
            return r2
        L_0x00c1:
            monitor-exit(r9)     // Catch:{ all -> 0x00ba }
            throw r10
        L_0x00c3:
            com.google.android.gms.internal.measurement.zzjg r11 = new com.google.android.gms.internal.measurement.zzjg     // Catch:{ all -> 0x009d }
            java.lang.String r12 = "ContentProvider query returned null cursor"
            r11.<init>(r12)     // Catch:{ all -> 0x009d }
            throw r11     // Catch:{ all -> 0x009d }
        L_0x00cb:
            if (r1 == 0) goto L_0x00d5
            r1.close()     // Catch:{ all -> 0x00d1 }
            goto L_0x00d5
        L_0x00d1:
            r12 = move-exception
            r11.addSuppressed(r12)     // Catch:{ RemoteException -> 0x009b }
        L_0x00d5:
            throw r11     // Catch:{ RemoteException -> 0x009b }
        L_0x00d6:
            com.google.android.gms.internal.measurement.zzjg r12 = new com.google.android.gms.internal.measurement.zzjg     // Catch:{ all -> 0x0099 }
            java.lang.String r0 = "ContentProvider query failed"
            r12.<init>(r0, r11)     // Catch:{ all -> 0x0099 }
            throw r12     // Catch:{ all -> 0x0099 }
        L_0x00de:
            r10.release()     // Catch:{ zzjg -> 0x00ea }
            throw r11     // Catch:{ zzjg -> 0x00ea }
        L_0x00e2:
            com.google.android.gms.internal.measurement.zzjg r10 = new com.google.android.gms.internal.measurement.zzjg     // Catch:{ zzjg -> 0x00ea }
            java.lang.String r11 = "Unable to acquire ContentProviderClient"
            r10.<init>(r11)     // Catch:{ zzjg -> 0x00ea }
            throw r10     // Catch:{ zzjg -> 0x00ea }
        L_0x00ea:
            return r2
        L_0x00eb:
            monitor-exit(r9)     // Catch:{ all -> 0x002c }
            throw r10
        L_0x00ed:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "ContentResolver needed with GservicesDelegateSupplier.init()"
            r10.<init>(r11)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjh.zza(android.content.ContentResolver, java.lang.String, java.lang.String):java.lang.String");
    }
}
