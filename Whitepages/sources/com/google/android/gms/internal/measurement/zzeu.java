package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

abstract class zzeu implements Runnable {
    final long zzh;
    final long zzi;
    final boolean zzj;
    final /* synthetic */ zzff zzk;

    zzeu(zzff zzff, boolean z) {
        this.zzk = zzff;
        this.zzh = zzff.zza.currentTimeMillis();
        this.zzi = zzff.zza.elapsedRealtime();
        this.zzj = z;
    }

    public final void run() {
        if (this.zzk.zzh) {
            zzb();
            return;
        }
        try {
            zza();
        } catch (Exception e) {
            this.zzk.zzU(e, false, this.zzj);
            zzb();
        }
    }

    /* access modifiers changed from: package-private */
    public abstract void zza() throws RemoteException;

    /* access modifiers changed from: protected */
    public void zzb() {
    }
}
