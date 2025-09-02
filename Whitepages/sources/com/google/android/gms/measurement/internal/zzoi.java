package com.google.android.gms.measurement.internal;

final class zzoi implements Runnable {
    final /* synthetic */ long zza;
    final /* synthetic */ zzop zzb;

    zzoi(zzop zzop, long j) {
        this.zza = j;
        this.zzb = zzop;
    }

    public final void run() {
        zzop.zzj(this.zzb, this.zza);
    }
}
