package com.google.android.gms.measurement.internal;

final class zzkx implements Runnable {
    final /* synthetic */ long zza;
    final /* synthetic */ zzlw zzb;

    zzkx(zzlw zzlw, long j) {
        this.zza = j;
        this.zzb = zzlw;
    }

    public final void run() {
        zzio zzio = this.zzb.zzu;
        zzhp zzhp = zzio.zzm().zzf;
        long j = this.zza;
        zzhp.zzb(j);
        zzio.zzaW().zzd().zzb("Session timeout duration set", Long.valueOf(j));
    }
}
