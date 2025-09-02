package com.google.android.gms.tasks;

final class zzm implements Runnable {
    final /* synthetic */ Task zza;
    final /* synthetic */ zzn zzb;

    zzm(zzn zzn, Task task) {
        this.zzb = zzn;
        this.zza = task;
    }

    public final void run() {
        synchronized (this.zzb.zzb) {
            try {
                zzn zzn = this.zzb;
                if (zzn.zzc != null) {
                    zzn.zzc.onSuccess(this.zza.getResult());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
