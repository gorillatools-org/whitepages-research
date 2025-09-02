package com.google.android.play.core.review.internal;

import com.google.android.gms.tasks.TaskCompletionSource;

final class zzm extends zzj {
    final /* synthetic */ zzj zza;
    final /* synthetic */ zzt zzb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzm(zzt zzt, TaskCompletionSource taskCompletionSource, zzj zzj) {
        super(taskCompletionSource);
        this.zzb = zzt;
        this.zza = zzj;
    }

    public final void zza() {
        zzt.zzm(this.zzb, this.zza);
    }
}
