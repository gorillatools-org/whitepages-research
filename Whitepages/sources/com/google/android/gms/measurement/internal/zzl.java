package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzcy;

final class zzl implements Runnable {
    final /* synthetic */ zzcy zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ boolean zzd;
    final /* synthetic */ AppMeasurementDynamiteService zze;

    zzl(AppMeasurementDynamiteService appMeasurementDynamiteService, zzcy zzcy, String str, String str2, boolean z) {
        this.zza = zzcy;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = z;
        this.zze = appMeasurementDynamiteService;
    }

    public final void run() {
        this.zze.zza.zzu().zzK(this.zza, this.zzb, this.zzc, this.zzd);
    }
}
