package com.google.android.gms.tasks;

final class zzg implements Runnable {
    final /* synthetic */ zzh zza;

    zzg(zzh zzh) {
        this.zza = zzh;
    }

    public final void run() {
        synchronized (this.zza.zzb) {
            try {
                zzh zzh = this.zza;
                if (zzh.zzc != null) {
                    zzh.zzc.onCanceled();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
