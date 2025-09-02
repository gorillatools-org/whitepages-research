package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

final class zzjc implements Callable {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ zzjp zzd;

    zzjc(zzjp zzjp, String str, String str2, String str3) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = zzjp;
    }

    public final /* bridge */ /* synthetic */ Object call() throws Exception {
        zzjp zzjp = this.zzd;
        zzjp.zza.zzL();
        return zzjp.zza.zzj().zzF(this.zza, this.zzb, this.zzc);
    }
}
