package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzln implements Runnable {
    final /* synthetic */ AtomicReference zza;
    final /* synthetic */ zzlw zzb;

    zzln(zzlw zzlw, AtomicReference atomicReference) {
        this.zza = atomicReference;
        this.zzb = zzlw;
    }

    public final void run() {
        AtomicReference atomicReference = this.zza;
        synchronized (atomicReference) {
            try {
                zzlw zzlw = this.zzb;
                atomicReference.set(Integer.valueOf(zzlw.zzu.zzf().zzh(zzlw.zzu.zzh().zzm(), zzgi.zzac)));
                this.zza.notify();
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
