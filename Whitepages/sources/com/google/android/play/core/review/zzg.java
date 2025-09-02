package com.google.android.play.core.review;

import android.os.Bundle;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.core.review.internal.zzi;
import com.google.android.play.core.review.internal.zzt;

abstract class zzg extends com.google.android.play.core.review.internal.zzg {
    final zzi zza;
    final TaskCompletionSource zzb;
    final /* synthetic */ zzi zzc;

    zzg(zzi zzi, zzi zzi2, TaskCompletionSource taskCompletionSource) {
        this.zzc = zzi;
        this.zza = zzi2;
        this.zzb = taskCompletionSource;
    }

    public void zzb(Bundle bundle) {
        zzt zzt = this.zzc.zza;
        if (zzt != null) {
            zzt.zzr(this.zzb);
        }
        this.zza.zzd("onGetLaunchReviewFlowInfo", new Object[0]);
    }
}
