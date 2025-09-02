package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

final class zzn implements DynamiteModule.VersionPolicy.IVersions {
    private final int zza;

    public zzn(int i, int i2) {
        this.zza = i;
    }

    public final int zza(Context context, String str) {
        return this.zza;
    }

    public final int zzb(Context context, String str, boolean z) {
        return 0;
    }
}
