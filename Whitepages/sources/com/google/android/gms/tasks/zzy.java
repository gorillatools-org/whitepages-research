package com.google.android.gms.tasks;

import com.google.android.gms.internal.tasks.zza;

public final /* synthetic */ class zzy implements OnCompleteListener {
    public final /* synthetic */ zza zza;
    public final /* synthetic */ TaskCompletionSource zzb;
    public final /* synthetic */ zzb zzc;

    public /* synthetic */ zzy(zza zza2, TaskCompletionSource taskCompletionSource, zzb zzb2) {
        this.zza = zza2;
        this.zzb = taskCompletionSource;
        this.zzc = zzb2;
    }

    public final void onComplete(Task task) {
        this.zza.removeCallbacksAndMessages((Object) null);
        TaskCompletionSource taskCompletionSource = this.zzb;
        if (task.isSuccessful()) {
            taskCompletionSource.trySetResult(task.getResult());
        } else if (task.isCanceled()) {
            this.zzc.zza();
        } else {
            Exception exception = task.getException();
            exception.getClass();
            taskCompletionSource.trySetException(exception);
        }
    }
}
