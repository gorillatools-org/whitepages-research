package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.lang.Thread;

final class zzii implements Thread.UncaughtExceptionHandler {
    final /* synthetic */ zzil zza;
    private final String zzb;

    public zzii(zzil zzil, String str) {
        this.zza = zzil;
        Preconditions.checkNotNull(str);
        this.zzb = str;
    }

    public final synchronized void uncaughtException(Thread thread, Throwable th) {
        this.zza.zzu.zzaW().zze().zzb(this.zzb, th);
    }
}
