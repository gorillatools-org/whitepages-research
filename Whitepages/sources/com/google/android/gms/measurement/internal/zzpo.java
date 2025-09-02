package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Callable;

final class zzpo implements Callable {
    final /* synthetic */ zzr zza;
    final /* synthetic */ zzpv zzb;

    zzpo(zzpv zzpv, zzr zzr) {
        this.zza = zzr;
        this.zzb = zzpv;
    }

    public final /* bridge */ /* synthetic */ Object call() throws Exception {
        zzr zzr = this.zza;
        zzpv zzpv = this.zzb;
        zzjx zzu = zzpv.zzu((String) Preconditions.checkNotNull(zzr.zza));
        zzjw zzjw = zzjw.ANALYTICS_STORAGE;
        if (zzu.zzr(zzjw) && zzjx.zzk(zzr.zzu, 100).zzr(zzjw)) {
            return zzpv.zzg(zzr).zzD();
        }
        zzpv.zzaW().zzj().zza("Analytics storage consent denied. Returning null app instance id");
        return null;
    }
}
