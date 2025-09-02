package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzlc implements Runnable {
    final /* synthetic */ AtomicReference zza;
    final /* synthetic */ boolean zzb;
    final /* synthetic */ zzlw zzc;

    zzlc(zzlw zzlw, AtomicReference atomicReference, boolean z) {
        this.zza = atomicReference;
        this.zzb = z;
        this.zzc = zzlw;
    }

    public final void run() {
        this.zzc.zzu.zzu().zzJ(this.zza, this.zzb);
    }
}
