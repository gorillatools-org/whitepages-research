package com.google.android.gms.tasks;

final class zzb extends CancellationToken {
    private final zzw zza = new zzw();

    zzb() {
    }

    public final boolean isCancellationRequested() {
        return this.zza.isComplete();
    }

    public final CancellationToken onCanceledRequested(OnTokenCanceledListener onTokenCanceledListener) {
        this.zza.addOnSuccessListener(TaskExecutors.MAIN_THREAD, new zza(this, onTokenCanceledListener));
        return this;
    }

    public final void zza() {
        this.zza.zze((Object) null);
    }
}
