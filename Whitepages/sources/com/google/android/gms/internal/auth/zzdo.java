package com.google.android.gms.internal.auth;

import java.io.Serializable;

public final class zzdo {
    public static zzdj zza(zzdj zzdj) {
        if ((zzdj instanceof zzdm) || (zzdj instanceof zzdk)) {
            return zzdj;
        }
        if (zzdj instanceof Serializable) {
            return new zzdk(zzdj);
        }
        return new zzdm(zzdj);
    }

    public static zzdj zzb(Object obj) {
        return new zzdn(obj);
    }
}
