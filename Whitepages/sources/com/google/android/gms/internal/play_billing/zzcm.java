package com.google.android.gms.internal.play_billing;

final class zzcm implements zzea {
    private static final zzcm zza = new zzcm();

    private zzcm() {
    }

    public static zzcm zza() {
        return zza;
    }

    public final zzdz zzb(Class cls) {
        Class<zzcs> cls2 = zzcs.class;
        if (cls2.isAssignableFrom(cls)) {
            try {
                return (zzdz) zzcs.zzj(cls.asSubclass(cls2)).zzx(3, (Object) null, (Object) null);
            } catch (Exception e) {
                throw new RuntimeException("Unable to get message info for ".concat(cls.getName()), e);
            }
        } else {
            throw new IllegalArgumentException("Unsupported message type: ".concat(cls.getName()));
        }
    }

    public final boolean zzc(Class cls) {
        return zzcs.class.isAssignableFrom(cls);
    }
}
