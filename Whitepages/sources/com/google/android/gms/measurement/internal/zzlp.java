package com.google.android.gms.measurement.internal;

final class zzlp implements Runnable {
    final /* synthetic */ Boolean zza;
    final /* synthetic */ zzlw zzb;

    zzlp(zzlw zzlw, Boolean bool) {
        this.zza = bool;
        this.zzb = zzlw;
    }

    public final void run() {
        this.zzb.zzas(this.zza, true);
    }
}
