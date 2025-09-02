package com.google.android.gms.measurement.internal;

public enum zzmf {
    GOOGLE_ANALYTICS(0),
    GOOGLE_SIGNAL(1),
    SGTM(2),
    SGTM_CLIENT(3),
    GOOGLE_SIGNAL_PENDING(4),
    UNKNOWN(99);
    
    private final int zzh;

    private zzmf(int i) {
        this.zzh = i;
    }

    public static zzmf zzb(int i) {
        for (zzmf zzmf : values()) {
            if (zzmf.zzh == i) {
                return zzmf;
            }
        }
        return UNKNOWN;
    }

    public final int zza() {
        return this.zzh;
    }
}
