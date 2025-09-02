package com.google.android.gms.measurement.internal;

final class zzjj implements Runnable {
    final /* synthetic */ zzbh zza;
    final /* synthetic */ zzr zzb;
    final /* synthetic */ zzjp zzc;

    zzjj(zzjp zzjp, zzbh zzbh, zzr zzr) {
        this.zza = zzbh;
        this.zzb = zzr;
        this.zzc = zzjp;
    }

    public final void run() {
        zzbh zzbh = this.zza;
        zzr zzr = this.zzb;
        zzjp zzjp = this.zzc;
        zzjp.zzJ(zzjp.zzb(zzbh, zzr), zzr);
    }
}
