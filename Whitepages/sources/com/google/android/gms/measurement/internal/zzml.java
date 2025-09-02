package com.google.android.gms.measurement.internal;

final class zzml implements Runnable {
    final /* synthetic */ long zza;
    final /* synthetic */ zzmo zzb;

    zzml(zzmo zzmo, long j) {
        this.zza = j;
        this.zzb = zzmo;
    }

    public final void run() {
        zzmo zzmo = this.zzb;
        zzmo.zzu.zzd().zzf(this.zza);
        zzmo.zza = null;
    }
}
