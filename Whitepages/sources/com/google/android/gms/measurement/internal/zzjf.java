package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

final class zzjf implements Runnable {
    final /* synthetic */ zzr zza;
    final /* synthetic */ zzjp zzb;

    zzjf(zzjp zzjp, zzr zzr) {
        this.zza = zzr;
        this.zzb = zzjp;
    }

    public final void run() {
        zzjp zzjp = this.zzb;
        zzjp.zza.zzL();
        zzpv zzc = zzjp.zza;
        zzc.zzaX().zzg();
        zzc.zzM();
        zzr zzr = this.zza;
        Preconditions.checkNotEmpty(zzr.zza);
        zzc.zzg(zzr);
    }
}
