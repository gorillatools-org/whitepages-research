package com.google.android.gms.internal.play_billing;

import okhttp3.internal.http2.Settings;

final class zzcc {
    private final Object zza;
    private final int zzb;

    zzcc(Object obj, int i) {
        this.zza = obj;
        this.zzb = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzcc)) {
            return false;
        }
        zzcc zzcc = (zzcc) obj;
        if (this.zza == zzcc.zza && this.zzb == zzcc.zzb) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.zza) * Settings.DEFAULT_INITIAL_WINDOW_SIZE) + this.zzb;
    }
}
