package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zzj implements zzq {
    private final Executor zza;
    /* access modifiers changed from: private */
    public final Object zzb = new Object();
    /* access modifiers changed from: private */
    public OnCompleteListener zzc;

    public zzj(Executor executor, OnCompleteListener onCompleteListener) {
        this.zza = executor;
        this.zzc = onCompleteListener;
    }

    public final void zzc() {
        synchronized (this.zzb) {
            this.zzc = null;
        }
    }

    public final void zzd(Task task) {
        synchronized (this.zzb) {
            try {
                if (this.zzc != null) {
                    this.zza.execute(new zzi(this, task));
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }
}
