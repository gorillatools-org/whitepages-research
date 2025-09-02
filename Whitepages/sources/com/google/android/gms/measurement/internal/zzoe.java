package com.google.android.gms.measurement.internal;

final class zzoe implements Runnable {
    final /* synthetic */ zzpv zza;
    final /* synthetic */ Runnable zzb;

    zzoe(zzog zzog, zzpv zzpv, Runnable runnable) {
        this.zza = zzpv;
        this.zzb = runnable;
    }

    public final void run() {
        zzpv zzpv = this.zza;
        zzpv.zzL();
        zzpv.zzK(this.zzb);
        zzpv.zzat();
    }
}
