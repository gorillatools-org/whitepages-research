package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzcy;

final class zzk implements Runnable {
    final /* synthetic */ zzcy zza;
    final /* synthetic */ zzbh zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ AppMeasurementDynamiteService zzd;

    zzk(AppMeasurementDynamiteService appMeasurementDynamiteService, zzcy zzcy, zzbh zzbh, String str) {
        this.zza = zzcy;
        this.zzb = zzbh;
        this.zzc = str;
        this.zzd = appMeasurementDynamiteService;
    }

    public final void run() {
        this.zzd.zza.zzu().zzN(this.zza, this.zzb, this.zzc);
    }
}
