package com.google.android.gms.internal.measurement;

import okhttp3.internal.http2.Settings;

final class zzlo {
    private final Object zza;
    private final int zzb;

    zzlo(Object obj, int i) {
        this.zza = obj;
        this.zzb = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzlo)) {
            return false;
        }
        zzlo zzlo = (zzlo) obj;
        if (this.zza == zzlo.zza && this.zzb == zzlo.zzb) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.zza) * Settings.DEFAULT_INITIAL_WINDOW_SIZE) + this.zzb;
    }
}
