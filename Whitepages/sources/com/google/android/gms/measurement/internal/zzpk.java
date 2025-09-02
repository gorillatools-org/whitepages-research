package com.google.android.gms.measurement.internal;

final class zzpk implements Runnable {
    final /* synthetic */ zzpw zza;
    final /* synthetic */ zzpv zzb;

    zzpk(zzpv zzpv, zzpw zzpw) {
        this.zza = zzpw;
        this.zzb = zzpv;
    }

    public final void run() {
        zzpv zzpv = this.zzb;
        zzpv.zzH(zzpv, this.zza);
        zzpv.zzam();
    }
}
