package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

final class zziv implements Callable {
    final /* synthetic */ String zza;
    final /* synthetic */ zzjp zzb;

    zziv(zzjp zzjp, String str) {
        this.zza = str;
        this.zzb = zzjp;
    }

    public final /* bridge */ /* synthetic */ Object call() throws Exception {
        zzjp zzjp = this.zzb;
        zzjp.zza.zzL();
        return zzjp.zza.zzj().zzE(this.zza);
    }
}
