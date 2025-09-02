package com.google.android.gms.measurement.internal;

final class zziw implements Runnable {
    final /* synthetic */ zzr zza;
    final /* synthetic */ zzjp zzb;

    zziw(zzjp zzjp, zzr zzr) {
        this.zza = zzr;
        this.zzb = zzjp;
    }

    public final void run() {
        zzjp zzjp = this.zzb;
        zzjp.zza.zzL();
        zzjp.zza.zzac(this.zza);
    }
}
