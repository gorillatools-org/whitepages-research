package com.google.android.gms.measurement.internal;

final class zzoh implements Runnable {
    final /* synthetic */ long zza;
    final /* synthetic */ zzop zzb;

    zzoh(zzop zzop, long j) {
        this.zza = j;
        this.zzb = zzop;
    }

    public final void run() {
        zzop.zzl(this.zzb, this.zza);
    }
}
