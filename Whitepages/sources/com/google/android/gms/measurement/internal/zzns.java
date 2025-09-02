package com.google.android.gms.measurement.internal;

final class zzns implements Runnable {
    final /* synthetic */ zzgl zza;
    final /* synthetic */ zznx zzb;

    zzns(zznx zznx, zzgl zzgl) {
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
                    zzny.zzu.zzaW().zzd().zza("Connected to remote service");
                    zzny.zzW(this.zza);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        zzny zzny2 = this.zzb.zza;
        if (zzny2.zzu.zzf().zzx((String) null, zzgi.zzbo) && zzny2.zze != null) {
            zzny2.zze.shutdownNow();
            zzny2.zze = null;
        }
    }
}
