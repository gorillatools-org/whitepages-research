package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

public final /* synthetic */ class zzki implements Runnable {
    public final /* synthetic */ zzlw zza;
    public final /* synthetic */ AtomicReference zzb;

    public /* synthetic */ zzki(zzlw zzlw, AtomicReference atomicReference) {
        this.zza = zzlw;
        this.zzb = atomicReference;
    }

    public final void run() {
        zzlw zzlw = this.zza;
        zzlw.zzu.zzu().zzH(this.zzb, zzlw.zzu.zzm().zzi.zza());
    }
}
