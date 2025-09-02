package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

final class zzmw extends zzgn {
    final /* synthetic */ AtomicReference zza;

    zzmw(zzny zzny, AtomicReference atomicReference) {
        this.zza = atomicReference;
    }

    public final void zze(List list) {
        AtomicReference atomicReference = this.zza;
        synchronized (atomicReference) {
            atomicReference.set(list);
            atomicReference.notifyAll();
        }
    }
}
