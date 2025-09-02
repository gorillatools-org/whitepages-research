package com.google.android.gms.measurement.internal;

final class zzm implements Runnable {
    final /* synthetic */ zzp zza;
    final /* synthetic */ AppMeasurementDynamiteService zzb;

    zzm(AppMeasurementDynamiteService appMeasurementDynamiteService, zzp zzp) {
        this.zza = zzp;
        this.zzb = appMeasurementDynamiteService;
    }

    public final void run() {
        this.zzb.zza.zzq().zzah(this.zza);
    }
}
