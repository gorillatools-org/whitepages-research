package com.google.android.gms.internal.common;

public class zzj {
    private final Class zza;
    private final Object zzb;

    private zzj(Class cls, Object obj) {
        this.zza = cls;
        this.zzb = obj;
    }

    public static zzj zzb(Class cls, Object obj) {
        return new zzj(cls, obj);
    }

    public final Class zzc() {
        return this.zza;
    }

    public final Object zzd() {
        return this.zzb;
    }
}
