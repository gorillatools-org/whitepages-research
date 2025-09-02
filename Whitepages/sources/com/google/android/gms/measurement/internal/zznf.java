package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

final class zznf implements Runnable {
    final /* synthetic */ zzmh zza;
    final /* synthetic */ zzny zzb;

    zznf(zzny zzny, zzmh zzmh) {
        this.zza = zzmh;
        this.zzb = zzny;
    }

    public final void run() {
        zzny zzny = this.zzb;
        zzgl zzi = zzny.zzb;
        if (zzi == null) {
            zzny.zzu.zzaW().zze().zza("Failed to send current screen to service");
            return;
        }
        try {
            zzmh zzmh = this.zza;
            if (zzmh == null) {
                zzi.zzw(0, (String) null, (String) null, zzny.zzu.zzaT().getPackageName());
            } else {
                zzi.zzw(zzmh.zzc, zzmh.zza, zzmh.zzb, zzny.zzu.zzaT().getPackageName());
            }
            zzny.zzag();
        } catch (RemoteException e) {
            this.zzb.zzu.zzaW().zze().zzb("Failed to send current screen to the service", e);
        }
    }
}
