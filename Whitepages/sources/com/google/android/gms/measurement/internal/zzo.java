package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzcy;

final class zzo implements Runnable {
    final /* synthetic */ zzcy zza;
    final /* synthetic */ AppMeasurementDynamiteService zzb;

    zzo(AppMeasurementDynamiteService appMeasurementDynamiteService, zzcy zzcy) {
        this.zza = zzcy;
        this.zzb = appMeasurementDynamiteService;
    }

    public final void run() {
        AppMeasurementDynamiteService appMeasurementDynamiteService = this.zzb;
        appMeasurementDynamiteService.zza.zzw().zzT(this.zza, appMeasurementDynamiteService.zza.zzI());
    }
}
