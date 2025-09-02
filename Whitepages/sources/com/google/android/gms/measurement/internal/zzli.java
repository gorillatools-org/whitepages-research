package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzli implements Runnable {
    final /* synthetic */ AtomicReference zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ boolean zzd;
    final /* synthetic */ zzlw zze;

    zzli(zzlw zzlw, AtomicReference atomicReference, String str, String str2, String str3, boolean z) {
        this.zza = atomicReference;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = z;
        this.zze = zzlw;
    }

    public final void run() {
        this.zze.zzu.zzu().zzL(this.zza, (String) null, this.zzb, this.zzc, this.zzd);
    }
}
