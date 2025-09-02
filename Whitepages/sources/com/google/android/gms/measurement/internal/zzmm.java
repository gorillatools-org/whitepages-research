package com.google.android.gms.measurement.internal;

final class zzmm implements Runnable {
    final /* synthetic */ zzmh zza;
    final /* synthetic */ long zzb;
    final /* synthetic */ zzmo zzc;

    zzmm(zzmo zzmo, zzmh zzmh, long j) {
        this.zza = zzmh;
        this.zzb = j;
        this.zzc = zzmo;
    }

    public final void run() {
        zzmo zzmo = this.zzc;
        zzmo.zzC(this.zza, false, this.zzb);
        zzmo.zza = null;
        zzmo.zzu.zzu().zzS((zzmh) null);
    }
}
