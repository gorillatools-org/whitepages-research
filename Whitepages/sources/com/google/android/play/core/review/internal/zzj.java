package com.google.android.play.core.review.internal;

import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class zzj implements Runnable {
    private final TaskCompletionSource zza;

    zzj() {
        this.zza = null;
    }

    public zzj(TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void run() {
        try {
            zza();
        } catch (Exception e) {
            zzc(e);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zza();

    /* access modifiers changed from: package-private */
    public final TaskCompletionSource zzb() {
        return this.zza;
    }

    public final void zzc(Exception exc) {
        TaskCompletionSource taskCompletionSource = this.zza;
        if (taskCompletionSource != null) {
            taskCompletionSource.trySetException(exc);
        }
    }
}
