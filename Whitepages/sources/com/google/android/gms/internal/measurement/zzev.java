package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import com.google.android.gms.measurement.internal.zzkb;

final class zzev extends zzdd {
    private final zzkb zza;

    zzev(zzkb zzkb) {
        this.zza = zzkb;
    }

    public final int zze() {
        return System.identityHashCode(this.zza);
    }

    public final void zzf(String str, String str2, Bundle bundle, long j) {
        this.zza.interceptEvent(str, str2, bundle, j);
    }
}
