package com.google.android.gms.measurement.internal;

final class zzkv implements Runnable {
    final /* synthetic */ boolean zza;
    final /* synthetic */ zzlw zzb;

    zzkv(zzlw zzlw, boolean z) {
        this.zza = z;
        this.zzb = zzlw;
    }

    public final void run() {
        zzlw zzlw = this.zzb;
        zzio zzio = zzlw.zzu;
        boolean zzJ = zzio.zzJ();
        boolean zzI = zzio.zzI();
        boolean z = this.zza;
        zzio.zzF(z);
        if (zzI == z) {
            zzio.zzaW().zzj().zzb("Default data collection state already set to", Boolean.valueOf(z));
        }
        if (zzio.zzJ() == zzJ || zzio.zzJ() != zzio.zzI()) {
            zzio.zzaW().zzl().zzc("Default data collection is different than actual status", Boolean.valueOf(z), Boolean.valueOf(zzJ));
        }
        zzlw.zzat();
    }
}
