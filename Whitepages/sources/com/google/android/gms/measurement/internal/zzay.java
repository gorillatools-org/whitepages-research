package com.google.android.gms.measurement.internal;

final class zzay implements Runnable {
    final /* synthetic */ zzjs zza;
    final /* synthetic */ zzaz zzb;

    zzay(zzaz zzaz, zzjs zzjs) {
        this.zza = zzjs;
        this.zzb = zzaz;
    }

    public final void run() {
        zzjs zzjs = this.zza;
        zzjs.zzaV();
        if (zzaf.zza()) {
            zzjs.zzaX().zzq(this);
            return;
        }
        zzaz zzaz = this.zzb;
        boolean zze = zzaz.zze();
        zzaz.zzd = 0;
        if (zze) {
            zzaz.zzc();
        }
    }
}
