package com.google.android.play.core.review.internal;

import android.os.IBinder;

public final /* synthetic */ class zzl implements IBinder.DeathRecipient {
    public final /* synthetic */ zzt zza;

    public /* synthetic */ zzl(zzt zzt) {
        this.zza = zzt;
    }

    public final void binderDied() {
        zzt.zzh(this.zza);
    }
}
