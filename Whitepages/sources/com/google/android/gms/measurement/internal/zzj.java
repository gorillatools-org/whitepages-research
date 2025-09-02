package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzcy;

final class zzj implements Runnable {
    final /* synthetic */ zzcy zza;
    final /* synthetic */ AppMeasurementDynamiteService zzb;

    zzj(AppMeasurementDynamiteService appMeasurementDynamiteService, zzcy zzcy) {
        this.zza = zzcy;
        this.zzb = appMeasurementDynamiteService;
    }

    public final void run() {
        this.zzb.zza.zzu().zzD(this.zza);
    }
}
