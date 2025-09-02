package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzlh implements Runnable {
    final /* synthetic */ AtomicReference zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ zzlw zzd;

    zzlh(zzlw zzlw, AtomicReference atomicReference, String str, String str2, String str3) {
        this.zza = atomicReference;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = zzlw;
    }

    public final void run() {
        this.zzd.zzu.zzu().zzG(this.zza, (String) null, this.zzb, this.zzc);
    }
}
