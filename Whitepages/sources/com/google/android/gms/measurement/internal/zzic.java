package com.google.android.gms.measurement.internal;

import androidx.collection.LruCache;
import com.google.android.gms.common.internal.Preconditions;

final class zzic extends LruCache {
    final /* synthetic */ zzif zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzic(zzif zzif, int i) {
        super(20);
        this.zza = zzif;
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ Object create(Object obj) {
        String str = (String) obj;
        Preconditions.checkNotEmpty(str);
        zzif zzif = this.zza;
        if (zzif.zzu.zzf().zzx((String) null, zzgi.zzbn)) {
            return zzif.zze(zzif, str);
        }
        return zzif.zzd(zzif, str);
    }
}
