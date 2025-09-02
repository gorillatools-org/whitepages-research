package com.google.android.gms.internal.auth;

final class zzes implements zzfv {
    private static final zzes zza = new zzes();

    private zzes() {
    }

    public static zzes zza() {
        return zza;
    }

    public final zzfu zzb(Class cls) {
        Class<zzev> cls2 = zzev.class;
        if (cls2.isAssignableFrom(cls)) {
            try {
                return (zzfu) zzev.zzb(cls.asSubclass(cls2)).zzn(3, (Object) null, (Object) null);
            } catch (Exception e) {
                throw new RuntimeException("Unable to get message info for ".concat(cls.getName()), e);
            }
        } else {
            throw new IllegalArgumentException("Unsupported message type: ".concat(cls.getName()));
        }
    }

    public final boolean zzc(Class cls) {
        return zzev.class.isAssignableFrom(cls);
    }
}
