package com.google.android.gms.internal.auth;

final class zzgk {
    public static final /* synthetic */ int zza = 0;
    private static final Class zzb;
    private static final zzgz zzc;
    private static final zzgz zzd = new zzhb();

    static {
        Class<?> cls;
        Class<?> cls2;
        zzgz zzgz = null;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        zzb = cls;
        try {
            cls2 = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused2) {
            cls2 = null;
        }
        if (cls2 != null) {
            try {
                zzgz = (zzgz) cls2.getConstructor((Class[]) null).newInstance((Object[]) null);
            } catch (Throwable unused3) {
            }
        }
        zzc = zzgz;
    }

    public static zzgz zza() {
        return zzc;
    }

    public static zzgz zzb() {
        return zzd;
    }

    static Object zzc(Object obj, int i, int i2, Object obj2, zzgz zzgz) {
        if (obj2 == null) {
            obj2 = zzgz.zza(obj);
        }
        zzgz.zzd(obj2, i, (long) i2);
        return obj2;
    }

    static void zzd(zzgz zzgz, Object obj, Object obj2) {
        zzgz.zzf(obj, zzgz.zzc(zzgz.zzb(obj), zzgz.zzb(obj2)));
    }

    public static void zze(Class cls) {
        Class cls2;
        if (!zzev.class.isAssignableFrom(cls) && (cls2 = zzb) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    static boolean zzf(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj != null) {
            return obj.equals(obj2);
        }
        return false;
    }
}
