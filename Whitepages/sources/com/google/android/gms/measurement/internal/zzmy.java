package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

final class zzmy implements Runnable {
    final /* synthetic */ zzr zza;
    final /* synthetic */ boolean zzb;
    final /* synthetic */ zzqb zzc;
    final /* synthetic */ zzny zzd;

    zzmy(zzny zzny, zzr zzr, boolean z, zzqb zzqb) {
        this.zza = zzr;
        this.zzb = z;
        this.zzc = zzqb;
        this.zzd = zzny;
    }

    public final void run() {
        zzqb zzqb;
        zzny zzny = this.zzd;
        zzgl zzi = zzny.zzb;
        if (zzi == null) {
            zzny.zzu.zzaW().zze().zza("Discarding data. Failed to set user property");
            return;
        }
        zzr zzr = this.zza;
        Preconditions.checkNotNull(zzr);
        if (this.zzb) {
            zzqb = null;
        } else {
            zzqb = this.zzc;
        }
        zzny.zzP(zzi, zzqb, zzr);
        zzny.zzag();
    }
}
