package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zznd implements Runnable {
    final /* synthetic */ zzr zza;
    final /* synthetic */ zzny zzb;

    zznd(zzny zzny, zzr zzr) {
        this.zza = zzr;
        this.zzb = zzny;
    }

    public final void run() {
        zzny zzny = this.zzb;
        zzgl zzi = zzny.zzb;
        if (zzi == null) {
            zzny.zzu.zzaW().zzk().zza("Failed to send app backgrounded");
            return;
        }
        try {
            zzr zzr = this.zza;
            Preconditions.checkNotNull(zzr);
            zzi.zzm(zzr);
            zzny.zzag();
        } catch (RemoteException e) {
            this.zzb.zzu.zzaW().zze().zzb("Failed to send app backgrounded to the service", e);
        }
    }
}
