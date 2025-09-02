package com.google.android.gms.measurement.internal;

public enum zzme {
    UNKNOWN(0),
    SUCCESS(1),
    FAILURE(2),
    BACKOFF(3);
    
    private final int zzf;

    private zzme(int i) {
        this.zzf = i;
    }

    public final int zza() {
        return this.zzf;
    }
}
