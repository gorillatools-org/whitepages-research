package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

public final /* synthetic */ class zzkn implements zzly {
    public final /* synthetic */ zzlw zza;
    public final /* synthetic */ AtomicReference zzb;
    public final /* synthetic */ zzpa zzc;

    public /* synthetic */ zzkn(zzlw zzlw, AtomicReference atomicReference, zzpa zzpa) {
        this.zza = zzlw;
        this.zzb = atomicReference;
        this.zzc = zzpa;
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public final void zza(java.lang.String r10, int r11, java.lang.Throwable r12, byte[] r13, java.util.Map r14) {
        /*
            r9 = this;
            com.google.android.gms.measurement.internal.zzlw r10 = r9.zza
            r10.zzg()
            com.google.android.gms.measurement.internal.zzpa r13 = r9.zzc
            r14 = 200(0xc8, float:2.8E-43)
            if (r11 == r14) goto L_0x0014
            r14 = 204(0xcc, float:2.86E-43)
            if (r11 == r14) goto L_0x0014
            r14 = 304(0x130, float:4.26E-43)
            if (r11 != r14) goto L_0x002e
            r11 = r14
        L_0x0014:
            if (r12 != 0) goto L_0x002e
            com.google.android.gms.measurement.internal.zzio r11 = r10.zzu
            com.google.android.gms.measurement.internal.zzhe r11 = r11.zzaW()
            com.google.android.gms.measurement.internal.zzhc r11 = r11.zzj()
            long r0 = r13.zza
            java.lang.Long r12 = java.lang.Long.valueOf(r0)
            java.lang.String r14 = "[sgtm] Upload succeeded for row_id"
            r11.zzb(r14, r12)
            com.google.android.gms.measurement.internal.zzme r11 = com.google.android.gms.measurement.internal.zzme.zzb
            goto L_0x0069
        L_0x002e:
            com.google.android.gms.measurement.internal.zzio r14 = r10.zzu
            com.google.android.gms.measurement.internal.zzhe r14 = r14.zzaW()
            com.google.android.gms.measurement.internal.zzhc r14 = r14.zzk()
            long r0 = r13.zza
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r11)
            java.lang.String r2 = "[sgtm] Upload failed for row_id. response, exception"
            r14.zzd(r2, r0, r1, r12)
            com.google.android.gms.measurement.internal.zzgg r12 = com.google.android.gms.measurement.internal.zzgi.zzt
            r14 = 0
            java.lang.Object r12 = r12.zza(r14)
            java.lang.String r12 = (java.lang.String) r12
            java.lang.String r14 = ","
            java.lang.String[] r12 = r12.split(r14)
            java.util.List r12 = java.util.Arrays.asList(r12)
            java.lang.String r11 = java.lang.String.valueOf(r11)
            boolean r11 = r12.contains(r11)
            if (r11 == 0) goto L_0x0067
            com.google.android.gms.measurement.internal.zzme r11 = com.google.android.gms.measurement.internal.zzme.zzd
            goto L_0x0069
        L_0x0067:
            com.google.android.gms.measurement.internal.zzme r11 = com.google.android.gms.measurement.internal.zzme.zzc
        L_0x0069:
            java.util.concurrent.atomic.AtomicReference r12 = r9.zzb
            com.google.android.gms.measurement.internal.zzio r14 = r10.zzu
            com.google.android.gms.measurement.internal.zzny r14 = r14.zzu()
            com.google.android.gms.measurement.internal.zzag r6 = new com.google.android.gms.measurement.internal.zzag
            long r7 = r13.zza
            int r3 = r11.zza()
            long r4 = r13.zzf
            r0 = r6
            r1 = r7
            r0.<init>(r1, r3, r4)
            r14.zzZ(r6)
            com.google.android.gms.measurement.internal.zzio r10 = r10.zzu
            com.google.android.gms.measurement.internal.zzhe r10 = r10.zzaW()
            com.google.android.gms.measurement.internal.zzhc r10 = r10.zzj()
            java.lang.Long r13 = java.lang.Long.valueOf(r7)
            java.lang.String r14 = "[sgtm] Updated status for row_id"
            r10.zzc(r14, r13, r11)
            monitor-enter(r12)
            r12.set(r11)     // Catch:{ all -> 0x009f }
            r12.notifyAll()     // Catch:{ all -> 0x009f }
            monitor-exit(r12)     // Catch:{ all -> 0x009f }
            return
        L_0x009f:
            r10 = move-exception
            monitor-exit(r12)     // Catch:{ all -> 0x009f }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzkn.zza(java.lang.String, int, java.lang.Throwable, byte[], java.util.Map):void");
    }
}
