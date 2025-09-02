package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

public final /* synthetic */ class zzkl implements Runnable {
    public final /* synthetic */ zzlw zza;
    public final /* synthetic */ AtomicReference zzb;

    public /* synthetic */ zzkl(zzlw zzlw, AtomicReference atomicReference) {
        this.zza = zzlw;
        this.zzb = atomicReference;
    }

    public final void run() {
        this.zza.zzu.zzu().zzI(this.zzb, zzpc.zza(zzmf.SGTM_CLIENT));
    }
}
