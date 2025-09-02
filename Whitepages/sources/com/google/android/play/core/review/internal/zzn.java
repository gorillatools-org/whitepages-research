package com.google.android.play.core.review.internal;

final class zzn extends zzj {
    final /* synthetic */ zzt zza;

    zzn(zzt zzt) {
        this.zza = zzt;
    }

    public final void zza() {
        zzt zzt = this.zza;
        if (zzt.zzn != null) {
            zzt.zzc.zzd("Unbind from service.", new Object[0]);
            zzt zzt2 = this.zza;
            zzt2.zzb.unbindService(zzt2.zzm);
            this.zza.zzh = false;
            this.zza.zzn = null;
            this.zza.zzm = null;
        }
        this.zza.zzt();
    }
}
