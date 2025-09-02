package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzng implements Runnable {
    final /* synthetic */ zzr zza;
    final /* synthetic */ boolean zzb;
    final /* synthetic */ zzbf zzc;
    final /* synthetic */ Bundle zzd;
    final /* synthetic */ zzny zze;

    zzng(zzny zzny, boolean z, zzr zzr, boolean z2, zzbf zzbf, Bundle bundle) {
        this.zza = zzr;
        this.zzb = z2;
        this.zzc = zzbf;
        this.zzd = bundle;
        this.zze = zzny;
    }

    public final void run() {
        zzny zzny = this.zze;
        zzgl zzi = zzny.zzb;
        if (zzi == null) {
            zzny.zzu.zzaW().zze().zza("Failed to send default event parameters to service");
            return;
        }
        zzbf zzbf = null;
        if (zzny.zzu.zzf().zzx((String) null, zzgi.zzbl)) {
            zzr zzr = this.zza;
            Preconditions.checkNotNull(zzr);
            zzny zzny2 = this.zze;
            if (!this.zzb) {
                zzbf = this.zzc;
            }
            zzny2.zzP(zzi, zzbf, zzr);
            return;
        }
        try {
            zzr zzr2 = this.zza;
            Preconditions.checkNotNull(zzr2);
            zzi.zzx(this.zzd, zzr2);
            zzny.zzag();
        } catch (RemoteException e) {
            this.zze.zzu.zzaW().zze().zzb("Failed to send default event parameters to service", e);
        }
    }
}
