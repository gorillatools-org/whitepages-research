package com.google.android.gms.measurement.internal;

final class zzb implements Runnable {
    final /* synthetic */ String zza;
    final /* synthetic */ long zzb;
    final /* synthetic */ zzd zzc;

    zzb(zzd zzd, String str, long j) {
        this.zza = str;
        this.zzb = j;
        this.zzc = zzd;
    }

    public final void run() {
        zzd.zzb(this.zzc, this.zza, this.zzb);
    }
}
