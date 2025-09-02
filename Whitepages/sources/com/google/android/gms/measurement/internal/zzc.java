package com.google.android.gms.measurement.internal;

final class zzc implements Runnable {
    final /* synthetic */ long zza;
    final /* synthetic */ zzd zzb;

    zzc(zzd zzd, long j) {
        this.zza = j;
        this.zzb = zzd;
    }

    public final void run() {
        this.zzb.zzj(this.zza);
    }
}
