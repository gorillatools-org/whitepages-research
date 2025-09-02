package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zzl implements zzq {
    private final Executor zza;
    /* access modifiers changed from: private */
    public final Object zzb = new Object();
    /* access modifiers changed from: private */
    public OnFailureListener zzc;

    public zzl(Executor executor, OnFailureListener onFailureListener) {
        this.zza = executor;
        this.zzc = onFailureListener;
    }

    public final void zzc() {
        synchronized (this.zzb) {
            this.zzc = null;
        }
    }

    public final void zzd(Task task) {
        if (!task.isSuccessful() && !task.isCanceled()) {
            synchronized (this.zzb) {
                try {
                    if (this.zzc != null) {
                        this.zza.execute(new zzk(this, task));
                    }
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
        }
    }
}
