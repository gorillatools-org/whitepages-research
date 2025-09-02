package com.google.android.gms.internal.measurement;

public final class zzje {
    private static zzjd zza;

    public static synchronized zzjd zza() {
        zzjd zzjd;
        synchronized (zzje.class) {
            try {
                if (zza == null) {
                    zzb(new zzjh());
                }
                zzjd = zza;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return zzjd;
    }

    public static synchronized void zzb(zzjd zzjd) {
        synchronized (zzje.class) {
            if (zza == null) {
                zza = zzjd;
            } else {
                throw new IllegalStateException("init() already called");
            }
        }
    }
}
