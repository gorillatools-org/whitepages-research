package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

final class zzjl extends ContentObserver {
    final /* synthetic */ zzjm zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzjl(zzjm zzjm, Handler handler) {
        super((Handler) null);
        this.zza = zzjm;
    }

    public final void onChange(boolean z) {
        this.zza.zzf();
    }
}
