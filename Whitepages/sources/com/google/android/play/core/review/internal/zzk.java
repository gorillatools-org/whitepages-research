package com.google.android.play.core.review.internal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public final /* synthetic */ class zzk implements OnCompleteListener {
    public final /* synthetic */ zzt zza;
    public final /* synthetic */ TaskCompletionSource zzb;

    public /* synthetic */ zzk(zzt zzt, TaskCompletionSource taskCompletionSource) {
        this.zza = zzt;
        this.zzb = taskCompletionSource;
    }

    public final void onComplete(Task task) {
        this.zza.zzq(this.zzb, task);
    }
}
