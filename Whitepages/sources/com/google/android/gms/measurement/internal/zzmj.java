package com.google.android.gms.measurement.internal;

import android.os.Bundle;

final class zzmj implements Runnable {
    final /* synthetic */ zzmh zza;
    final /* synthetic */ zzmh zzb;
    final /* synthetic */ long zzc;
    final /* synthetic */ boolean zzd;
    final /* synthetic */ zzmo zze;

    zzmj(zzmo zzmo, zzmh zzmh, zzmh zzmh2, long j, boolean z) {
        this.zza = zzmh;
        this.zzb = zzmh2;
        this.zzc = j;
        this.zzd = z;
        this.zze = zzmo;
    }

    public final void run() {
        this.zze.zzB(this.zza, this.zzb, this.zzc, this.zzd, (Bundle) null);
    }
}
