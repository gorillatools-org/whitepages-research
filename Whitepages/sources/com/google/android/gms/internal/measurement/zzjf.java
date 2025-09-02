package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

final class zzjf extends ContentObserver {
    final /* synthetic */ zzjh zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzjf(zzjh zzjh, Handler handler) {
        super((Handler) null);
        this.zza = zzjh;
    }

    public final void onChange(boolean z) {
        this.zza.zza.set(true);
    }
}
