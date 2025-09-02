package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzll implements Runnable {
    final /* synthetic */ AtomicReference zza;
    final /* synthetic */ zzlw zzb;

    zzll(zzlw zzlw, AtomicReference atomicReference) {
        this.zza = atomicReference;
        this.zzb = zzlw;
    }

    public final void run() {
        AtomicReference atomicReference = this.zza;
        synchronized (atomicReference) {
            try {
                zzlw zzlw = this.zzb;
                atomicReference.set(zzlw.zzu.zzf().zzr(zzlw.zzu.zzh().zzm(), zzgi.zzaa));
                this.zza.notify();
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
