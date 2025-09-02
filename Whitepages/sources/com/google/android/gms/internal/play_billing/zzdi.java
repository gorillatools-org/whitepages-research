package com.google.android.gms.internal.play_billing;

public class zzdi {
    private static final zzcd zzb = zzcd.zza;
    protected volatile zzec zza;
    private volatile zzbq zzc;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzdi)) {
            return false;
        }
        zzdi zzdi = (zzdi) obj;
        zzec zzec = this.zza;
        zzec zzec2 = zzdi.zza;
        if (zzec == null && zzec2 == null) {
            return zzb().equals(zzdi.zzb());
        }
        if (zzec != null && zzec2 != null) {
            return zzec.equals(zzec2);
        }
        if (zzec != null) {
            zzdi.zzd(zzec.zzh());
            return zzec.equals(zzdi.zza);
        }
        zzd(zzec2.zzh());
        return this.zza.equals(zzec2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzc != null) {
            return ((zzbn) this.zzc).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzf();
        }
        return 0;
    }

    public final zzbq zzb() {
        if (this.zzc != null) {
            return this.zzc;
        }
        synchronized (this) {
            try {
                if (this.zzc != null) {
                    zzbq zzbq = this.zzc;
                    return zzbq;
                }
                if (this.zza == null) {
                    this.zzc = zzbq.zzb;
                } else {
                    this.zzc = this.zza.zzb();
                }
                zzbq zzbq2 = this.zzc;
                return zzbq2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final zzec zzc(zzec zzec) {
        zzec zzec2 = this.zza;
        this.zzc = null;
        this.zza = zzec;
        return zzec2;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:10|11|12|13) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x0015 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzd(com.google.android.gms.internal.play_billing.zzec r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.play_billing.zzec r0 = r1.zza
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            monitor-enter(r1)
            com.google.android.gms.internal.play_billing.zzec r0 = r1.zza     // Catch:{ all -> 0x000c }
            if (r0 == 0) goto L_0x000e
            monitor-exit(r1)     // Catch:{ all -> 0x000c }
            return
        L_0x000c:
            r2 = move-exception
            goto L_0x001d
        L_0x000e:
            r1.zza = r2     // Catch:{ zzdc -> 0x0015 }
            com.google.android.gms.internal.play_billing.zzbq r0 = com.google.android.gms.internal.play_billing.zzbq.zzb     // Catch:{ zzdc -> 0x0015 }
            r1.zzc = r0     // Catch:{ zzdc -> 0x0015 }
            goto L_0x001b
        L_0x0015:
            r1.zza = r2     // Catch:{ all -> 0x000c }
            com.google.android.gms.internal.play_billing.zzbq r2 = com.google.android.gms.internal.play_billing.zzbq.zzb     // Catch:{ all -> 0x000c }
            r1.zzc = r2     // Catch:{ all -> 0x000c }
        L_0x001b:
            monitor-exit(r1)     // Catch:{ all -> 0x000c }
            return
        L_0x001d:
            monitor-exit(r1)     // Catch:{ all -> 0x000c }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.play_billing.zzdi.zzd(com.google.android.gms.internal.play_billing.zzec):void");
    }
}
