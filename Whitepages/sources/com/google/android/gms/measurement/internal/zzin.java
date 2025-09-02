package com.google.android.gms.measurement.internal;

final class zzin implements Runnable {
    final /* synthetic */ zzke zza;
    final /* synthetic */ zzio zzb;

    zzin(zzio zzio, zzke zzke) {
        this.zza = zzke;
        this.zzb = zzio;
    }

    public final void run() {
        zzio zzio = this.zzb;
        zzke zzke = this.zza;
        zzio.zzC(zzio, zzke);
        zzio.zzH(zzke.zzg);
    }
}
