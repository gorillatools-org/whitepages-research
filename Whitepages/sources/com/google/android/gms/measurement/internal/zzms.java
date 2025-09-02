package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import java.util.concurrent.atomic.AtomicReference;

public final /* synthetic */ class zzms implements Runnable {
    public final /* synthetic */ zzny zza;
    public final /* synthetic */ AtomicReference zzb;
    public final /* synthetic */ zzr zzc;
    public final /* synthetic */ Bundle zzd;

    public /* synthetic */ zzms(zzny zzny, AtomicReference atomicReference, zzr zzr, Bundle bundle) {
        this.zza = zzny;
        this.zzb = atomicReference;
        this.zzc = zzr;
        this.zzd = bundle;
    }

    public final void run() {
        zzny.zzr(this.zza, this.zzb, this.zzc, this.zzd);
    }
}
