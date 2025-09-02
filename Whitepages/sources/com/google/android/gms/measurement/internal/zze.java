package com.google.android.gms.measurement.internal;

import android.text.TextUtils;

final class zze {
    private final zzju zza;

    zze(zzju zzju) {
        this.zza = zzju;
    }

    static zze zza(String str) {
        zzju zzju;
        if (TextUtils.isEmpty(str) || str.length() > 1) {
            zzju = zzju.UNINITIALIZED;
        } else {
            zzju = zzjx.zzg(str.charAt(0));
        }
        return new zze(zzju);
    }

    /* access modifiers changed from: package-private */
    public final zzju zzb() {
        return this.zza;
    }

    /* access modifiers changed from: package-private */
    public final String zzc() {
        return String.valueOf(zzjx.zza(this.zza));
    }
}
