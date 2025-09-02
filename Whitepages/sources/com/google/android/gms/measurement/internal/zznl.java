package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

final class zznl implements Runnable {
    final /* synthetic */ zzr zza;
    final /* synthetic */ boolean zzb;
    final /* synthetic */ zzbh zzc;
    final /* synthetic */ zzny zzd;

    zznl(zzny zzny, boolean z, zzr zzr, boolean z2, zzbh zzbh, String str) {
        this.zza = zzr;
        this.zzb = z2;
        this.zzc = zzbh;
        this.zzd = zzny;
    }

    public final void run() {
        zzbh zzbh;
        zzny zzny = this.zzd;
        zzgl zzi = zzny.zzb;
        if (zzi == null) {
            zzny.zzu.zzaW().zze().zza("Discarding data. Failed to send event to service");
            return;
        }
        zzr zzr = this.zza;
        Preconditions.checkNotNull(zzr);
        if (this.zzb) {
            zzbh = null;
        } else {
            zzbh = this.zzc;
        }
        zzny.zzP(zzi, zzbh, zzr);
        zzny.zzag();
    }
}
