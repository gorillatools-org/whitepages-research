package com.google.android.gms.measurement.internal;

final class zznq implements Runnable {
    final /* synthetic */ zzgl zza;
    final /* synthetic */ zznx zzb;

    zznq(zznx zznx, zzgl zzgl) {
        this.zza = zzgl;
        this.zzb = zznx;
    }

    public final void run() {
        zznx zznx = this.zzb;
        synchronized (zznx) {
            try {
                zznx.zzb = false;
                zzny zzny = zznx.zza;
                if (!zzny.zzaa()) {
                    zzny.zzu.zzaW().zzj().zza("Connected to service");
                    zzny.zzW(this.zza);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
