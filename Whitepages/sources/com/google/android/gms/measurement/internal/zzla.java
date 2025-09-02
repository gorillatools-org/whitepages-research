package com.google.android.gms.measurement.internal;

final class zzla implements Runnable {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ Object zzc;
    final /* synthetic */ long zzd;
    final /* synthetic */ zzlw zze;

    zzla(zzlw zzlw, String str, String str2, Object obj, long j) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = obj;
        this.zzd = j;
        this.zze = zzlw;
    }

    public final void run() {
        this.zze.zzan(this.zza, this.zzb, this.zzc, this.zzd);
    }
}
