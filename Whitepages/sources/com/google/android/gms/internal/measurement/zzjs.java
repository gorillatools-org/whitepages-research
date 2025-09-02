package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.common.base.Preconditions;

public final /* synthetic */ class zzjs implements zzjq {
    public final /* synthetic */ zzju zza;
    public final /* synthetic */ String zzb;

    public /* synthetic */ zzjs(zzju zzju, String str) {
        this.zza = zzju;
        this.zzb = str;
    }

    public final Object zza() {
        return zzjb.zza(((Context) Preconditions.checkNotNull(this.zza.zzb)).getContentResolver(), this.zzb, (String) null);
    }
}
