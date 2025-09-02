package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzmx extends zzgq {
    final /* synthetic */ AtomicReference zza;
    final /* synthetic */ zzny zzb;

    zzmx(zzny zzny, AtomicReference atomicReference) {
        this.zza = atomicReference;
        this.zzb = zzny;
    }

    public final void zze(zzpe zzpe) {
        AtomicReference atomicReference = this.zza;
        synchronized (atomicReference) {
            this.zzb.zzu.zzaW().zzj().zzb("[sgtm] Got upload batches from service. count", Integer.valueOf(zzpe.zza.size()));
            atomicReference.set(zzpe);
            atomicReference.notifyAll();
        }
    }
}
