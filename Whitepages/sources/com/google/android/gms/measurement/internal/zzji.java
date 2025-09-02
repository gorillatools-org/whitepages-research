package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

final class zzji implements Callable {
    final /* synthetic */ zzr zza;
    final /* synthetic */ zzjp zzb;

    zzji(zzjp zzjp, zzr zzr) {
        this.zza = zzr;
        this.zzb = zzjp;
    }

    public final /* bridge */ /* synthetic */ Object call() throws Exception {
        zzjp zzjp = this.zzb;
        zzjp.zza.zzL();
        return new zzap(zzjp.zza.zzd(this.zza.zza));
    }
}
