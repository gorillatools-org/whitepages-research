package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zzh implements zzq {
    private final Executor zza;
    /* access modifiers changed from: private */
    public final Object zzb = new Object();
    /* access modifiers changed from: private */
    public OnCanceledListener zzc;

    public zzh(Executor executor, OnCanceledListener onCanceledListener) {
        this.zza = executor;
        this.zzc = onCanceledListener;
    }

    public final void zzc() {
        synchronized (this.zzb) {
            this.zzc = null;
        }
    }

    public final void zzd(Task task) {
        if (task.isCanceled()) {
            synchronized (this.zzb) {
                try {
                    if (this.zzc != null) {
                        this.zza.execute(new zzg(this));
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
