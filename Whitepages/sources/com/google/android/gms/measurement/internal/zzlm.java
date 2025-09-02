package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzlm implements Runnable {
    final /* synthetic */ AtomicReference zza;
    final /* synthetic */ zzlw zzb;

    zzlm(zzlw zzlw, AtomicReference atomicReference) {
        this.zza = atomicReference;
        this.zzb = zzlw;
    }

    public final void run() {
        AtomicReference atomicReference = this.zza;
        synchronized (atomicReference) {
            try {
                zzlw zzlw = this.zzb;
                atomicReference.set(Long.valueOf(zzlw.zzu.zzf().zzk(zzlw.zzu.zzh().zzm(), zzgi.zzab)));
                this.zza.notify();
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
