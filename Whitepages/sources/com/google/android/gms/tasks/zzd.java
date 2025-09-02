package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zzd implements zzq {
    private final Executor zza;
    /* access modifiers changed from: private */
    public final Continuation zzb;
    /* access modifiers changed from: private */
    public final zzw zzc;

    public zzd(Executor executor, Continuation continuation, zzw zzw) {
        this.zza = executor;
        this.zzb = continuation;
        this.zzc = zzw;
    }

    public final void zzc() {
        throw new UnsupportedOperationException();
    }

    public final void zzd(Task task) {
        this.zza.execute(new zzc(this, task));
    }
}
