package com.google.android.gms.internal.measurement;

final class zzly implements zznf {
    private static final zzly zza = new zzly();

    private zzly() {
    }

    public static zzly zza() {
        return zza;
    }

    public final zzne zzb(Class cls) {
        Class<zzmd> cls2 = zzmd.class;
        if (cls2.isAssignableFrom(cls)) {
            try {
                return (zzne) zzmd.zzci(cls.asSubclass(cls2)).zzl(3, (Object) null, (Object) null);
            } catch (Exception e) {
                throw new RuntimeException("Unable to get message info for ".concat(cls.getName()), e);
            }
        } else {
            throw new IllegalArgumentException("Unsupported message type: ".concat(cls.getName()));
        }
    }

    public final boolean zzc(Class cls) {
        return zzmd.class.isAssignableFrom(cls);
    }
}
