package com.google.android.gms.internal.measurement;

import java.util.List;
import java.util.concurrent.Callable;

public final class zzu extends zzai {
    private final Callable zza;

    public zzu(String str, Callable callable) {
        super("internal.appMetadata");
        this.zza = callable;
    }

    public final zzap zza(zzg zzg, List list) {
        try {
            return zzi.zzb(this.zza.call());
        } catch (Exception unused) {
            return zzap.zzf;
        }
    }
}
