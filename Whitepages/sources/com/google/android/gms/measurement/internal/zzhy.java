package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzn;
import java.util.concurrent.Callable;

public final /* synthetic */ class zzhy implements Callable {
    public final /* synthetic */ zzif zza;
    public final /* synthetic */ String zzb;

    public /* synthetic */ zzhy(zzif zzif, String str) {
        this.zza = zzif;
        this.zzb = str;
    }

    public final Object call() {
        return new zzn("internal.remoteConfig", new zzie(this.zza, this.zzb));
    }
}
