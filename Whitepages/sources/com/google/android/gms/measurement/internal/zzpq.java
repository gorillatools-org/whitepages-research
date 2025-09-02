package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;

final class zzpq implements zzqe {
    final /* synthetic */ zzpv zza;

    zzpq(zzpv zzpv) {
        this.zza = zzpv;
    }

    public final void zza(String str, String str2, Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            zzpv zzpv = this.zza;
            if (zzpv.zzn != null) {
                zzpv.zzn.zzaW().zze().zzb("AppId not known when logging event", str2);
                return;
            }
            return;
        }
        this.zza.zzaX().zzq(new zzpp(this, str, str2, bundle));
    }
}
