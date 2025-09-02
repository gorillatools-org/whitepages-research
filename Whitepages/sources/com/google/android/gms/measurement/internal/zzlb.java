package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzlb implements Runnable {
    final /* synthetic */ AtomicReference zza;
    final /* synthetic */ zzlw zzb;

    zzlb(zzlw zzlw, AtomicReference atomicReference) {
        this.zza = atomicReference;
        this.zzb = zzlw;
    }

    public final void run() {
        AtomicReference atomicReference = this.zza;
        synchronized (atomicReference) {
            try {
                zzlw zzlw = this.zzb;
                atomicReference.set(Boolean.valueOf(zzlw.zzu.zzf().zzx(zzlw.zzu.zzh().zzm(), zzgi.zzZ)));
                this.zza.notify();
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
