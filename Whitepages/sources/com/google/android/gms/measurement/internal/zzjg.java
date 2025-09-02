package com.google.android.gms.measurement.internal;

final class zzjg implements Runnable {
    final /* synthetic */ zzr zza;
    final /* synthetic */ zzjp zzb;

    zzjg(zzjp zzjp, zzr zzr) {
        this.zza = zzr;
        this.zzb = zzjp;
    }

    public final void run() {
        zzjp zzjp = this.zzb;
        zzjp.zza.zzL();
        zzjp.zza.zzah(this.zza);
    }
}
