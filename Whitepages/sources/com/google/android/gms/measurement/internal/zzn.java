package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzcy;

final class zzn implements Runnable {
    final /* synthetic */ zzcy zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ AppMeasurementDynamiteService zzd;

    zzn(AppMeasurementDynamiteService appMeasurementDynamiteService, zzcy zzcy, String str, String str2) {
        this.zza = zzcy;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = appMeasurementDynamiteService;
    }

    public final void run() {
        this.zzd.zza.zzu().zzF(this.zza, this.zzb, this.zzc);
    }
}
