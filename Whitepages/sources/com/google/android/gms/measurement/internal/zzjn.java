package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import java.util.concurrent.Callable;

final class zzjn implements Callable {
    final /* synthetic */ zzr zza;
    final /* synthetic */ Bundle zzb;
    final /* synthetic */ zzjp zzc;

    zzjn(zzjp zzjp, zzr zzr, Bundle bundle) {
        this.zza = zzr;
        this.zzb = bundle;
        this.zzc = zzjp;
    }

    public final /* bridge */ /* synthetic */ Object call() throws Exception {
        zzjp zzjp = this.zzc;
        zzjp.zza.zzL();
        return zzjp.zza.zzF(this.zza, this.zzb);
    }
}
