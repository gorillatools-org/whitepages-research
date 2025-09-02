package com.google.android.gms.measurement.internal;

final class zziy implements Runnable {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ long zzd;
    final /* synthetic */ zzjp zze;

    zziy(zzjp zzjp, String str, String str2, String str3, long j) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = j;
        this.zze = zzjp;
    }

    public final void run() {
        String str = this.zza;
        if (str == null) {
            zzjp zzjp = this.zze;
            zzjp.zza.zzaj(this.zzb, (zzmh) null);
            return;
        }
        zzmh zzmh = new zzmh(this.zzc, str, this.zzd);
        zzjp zzjp2 = this.zze;
        zzjp2.zza.zzaj(this.zzb, zzmh);
    }
}
