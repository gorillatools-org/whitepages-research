package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzu;
import java.util.concurrent.Callable;

public final /* synthetic */ class zzhz implements Callable {
    public final /* synthetic */ zzif zza;
    public final /* synthetic */ String zzb;

    public /* synthetic */ zzhz(zzif zzif, String str) {
        this.zza = zzif;
        this.zzb = str;
    }

    public final Object call() {
        return new zzu("internal.appMetadata", new zzib(this.zza, this.zzb));
    }
}
