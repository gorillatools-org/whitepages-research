package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import com.google.android.gms.measurement.internal.zzkc;

final class zzew extends zzdd {
    private final zzkc zza;

    zzew(zzkc zzkc) {
        this.zza = zzkc;
    }

    public final int zze() {
        return System.identityHashCode(this.zza);
    }

    public final void zzf(String str, String str2, Bundle bundle, long j) {
        this.zza.onEvent(str, str2, bundle, j);
    }
}
