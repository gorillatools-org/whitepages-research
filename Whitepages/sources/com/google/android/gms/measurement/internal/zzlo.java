package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzlo implements Runnable {
    final /* synthetic */ AtomicReference zza;
    final /* synthetic */ zzlw zzb;

    zzlo(zzlw zzlw, AtomicReference atomicReference) {
        this.zza = atomicReference;
        this.zzb = zzlw;
    }

    public final void run() {
        AtomicReference atomicReference = this.zza;
        synchronized (atomicReference) {
            try {
                zzlw zzlw = this.zzb;
                atomicReference.set(Double.valueOf(zzlw.zzu.zzf().zza(zzlw.zzu.zzh().zzm(), zzgi.zzad)));
                this.zza.notify();
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
