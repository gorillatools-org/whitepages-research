package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zznj implements Runnable {
    final /* synthetic */ zzr zza;
    final /* synthetic */ zzny zzb;

    zznj(zzny zzny, zzr zzr) {
        this.zza = zzr;
        this.zzb = zzny;
    }

    public final void run() {
        zzny zzny = this.zzb;
        zzgl zzi = zzny.zzb;
        if (zzi == null) {
            zzny.zzu.zzaW().zze().zza("Failed to send measurementEnabled to service");
            return;
        }
        try {
            zzr zzr = this.zza;
            Preconditions.checkNotNull(zzr);
            zzi.zzz(zzr);
            zzny.zzag();
        } catch (RemoteException e) {
            this.zzb.zzu.zzaW().zze().zzb("Failed to send measurementEnabled to the service", e);
        }
    }
}
