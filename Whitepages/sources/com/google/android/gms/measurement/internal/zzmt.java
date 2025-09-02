package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

public final /* synthetic */ class zzmt implements Runnable {
    public final /* synthetic */ zzny zza;
    public final /* synthetic */ AtomicReference zzb;
    public final /* synthetic */ zzr zzc;
    public final /* synthetic */ zzpc zzd;

    public /* synthetic */ zzmt(zzny zzny, AtomicReference atomicReference, zzr zzr, zzpc zzpc) {
        this.zza = zzny;
        this.zzb = atomicReference;
        this.zzc = zzr;
        this.zzd = zzpc;
    }

    public final void run() {
        zzny.zzq(this.zza, this.zzb, this.zzc, this.zzd);
    }
}
