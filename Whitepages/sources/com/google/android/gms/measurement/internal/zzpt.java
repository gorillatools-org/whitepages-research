package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

final class zzpt {
    private final zzpv zza;
    private int zzb = 1;
    /* access modifiers changed from: private */
    public long zzc = zzd();

    public zzpt(zzpv zzpv) {
        this.zza = zzpv;
    }

    private final long zzd() {
        zzpv zzpv = this.zza;
        Preconditions.checkNotNull(zzpv);
        long longValue = ((Long) zzgi.zzu.zza((Object) null)).longValue();
        long longValue2 = ((Long) zzgi.zzv.zza((Object) null)).longValue();
        for (int i = 1; i < this.zzb; i++) {
            longValue += longValue;
            if (longValue >= longValue2) {
                break;
            }
        }
        return zzpv.zzaU().currentTimeMillis() + Math.min(longValue, longValue2);
    }

    public final void zzb() {
        this.zzb++;
        this.zzc = zzd();
    }

    public final boolean zzc() {
        return this.zza.zzaU().currentTimeMillis() >= this.zzc;
    }
}
