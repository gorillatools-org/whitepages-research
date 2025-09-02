package com.google.android.gms.internal.auth;

final class zzdm implements zzdj {
    private static final zzdj zza = zzdl.zza;
    private volatile zzdj zzb;
    private Object zzc;

    zzdm(zzdj zzdj) {
        this.zzb = zzdj;
    }

    public final String toString() {
        Object obj = this.zzb;
        if (obj == zza) {
            obj = "<supplier that returned " + String.valueOf(this.zzc) + ">";
        }
        return "Suppliers.memoize(" + String.valueOf(obj) + ")";
    }

    public final Object zza() {
        zzdj zzdj = this.zzb;
        zzdj zzdj2 = zza;
        if (zzdj != zzdj2) {
            synchronized (this) {
                try {
                    if (this.zzb != zzdj2) {
                        Object zza2 = this.zzb.zza();
                        this.zzc = zza2;
                        this.zzb = zzdj2;
                        return zza2;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return this.zzc;
    }
}
