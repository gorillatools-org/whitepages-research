package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zznk implements Runnable {
    final /* synthetic */ zzr zza;
    final /* synthetic */ zzny zzb;

    zznk(zzny zzny, zzr zzr) {
        this.zza = zzr;
        this.zzb = zzny;
    }

    public final void run() {
        zzny zzny = this.zzb;
        zzgl zzi = zzny.zzb;
        if (zzi == null) {
            zzny.zzu.zzaW().zze().zza("Failed to send consent settings to service");
            return;
        }
        try {
            zzr zzr = this.zza;
            Preconditions.checkNotNull(zzr);
            zzi.zzv(zzr);
            zzny.zzag();
        } catch (RemoteException e) {
            this.zzb.zzu.zzaW().zze().zzb("Failed to send consent settings to the service", e);
        }
    }
}
