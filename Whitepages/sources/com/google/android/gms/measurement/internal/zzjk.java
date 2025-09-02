package com.google.android.gms.measurement.internal;

final class zzjk implements Runnable {
    final /* synthetic */ zzbh zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzjp zzc;

    zzjk(zzjp zzjp, zzbh zzbh, String str) {
        this.zza = zzbh;
        this.zzb = str;
        this.zzc = zzjp;
    }

    public final void run() {
        zzjp zzjp = this.zzc;
        zzjp.zza.zzL();
        zzjp.zza.zzT(this.zza, this.zzb);
    }
}
