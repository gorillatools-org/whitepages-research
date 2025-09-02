package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

final class zznm implements Runnable {
    final /* synthetic */ zzr zza;
    final /* synthetic */ boolean zzb;
    final /* synthetic */ zzai zzc;
    final /* synthetic */ zzny zzd;

    zznm(zzny zzny, boolean z, zzr zzr, boolean z2, zzai zzai, zzai zzai2) {
        this.zza = zzr;
        this.zzb = z2;
        this.zzc = zzai;
        this.zzd = zzny;
    }

    public final void run() {
        zzai zzai;
        zzny zzny = this.zzd;
        zzgl zzi = zzny.zzb;
        if (zzi == null) {
            zzny.zzu.zzaW().zze().zza("Discarding data. Failed to send conditional user property to service");
            return;
        }
        zzr zzr = this.zza;
        Preconditions.checkNotNull(zzr);
        if (this.zzb) {
            zzai = null;
        } else {
            zzai = this.zzc;
        }
        zzny.zzP(zzi, zzai, zzr);
        zzny.zzag();
    }
}
