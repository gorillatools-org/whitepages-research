package com.google.android.gms.measurement.internal;

import android.os.Bundle;

final class zzmi implements Runnable {
    final /* synthetic */ Bundle zza;
    final /* synthetic */ zzmh zzb;
    final /* synthetic */ zzmh zzc;
    final /* synthetic */ long zzd;
    final /* synthetic */ zzmo zze;

    zzmi(zzmo zzmo, Bundle bundle, zzmh zzmh, zzmh zzmh2, long j) {
        this.zza = bundle;
        this.zzb = zzmh;
        this.zzc = zzmh2;
        this.zzd = j;
        this.zze = zzmo;
    }

    public final void run() {
        zzmo.zzq(this.zze, this.zza, this.zzb, this.zzc, this.zzd);
    }
}
