package com.google.android.gms.measurement.internal;

import java.util.concurrent.Executor;

final class zzks implements Executor {
    final /* synthetic */ zzlw zza;

    zzks(zzlw zzlw) {
        this.zza = zzlw;
    }

    public final void execute(Runnable runnable) {
        this.zza.zzu.zzaX().zzq(runnable);
    }
}
