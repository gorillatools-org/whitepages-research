package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;

final class zzlk implements zzqe {
    final /* synthetic */ zzlw zza;

    zzlk(zzlw zzlw) {
        this.zza = zzlw;
    }

    public final void zza(String str, String str2, Bundle bundle) {
        if (!TextUtils.isEmpty(str)) {
            this.zza.zzQ("auto", "_err", bundle, str);
        } else {
            this.zza.zzO("auto", "_err", bundle);
        }
    }
}
