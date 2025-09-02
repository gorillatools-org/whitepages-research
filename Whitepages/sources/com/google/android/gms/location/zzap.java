package com.google.android.gms.location;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.internal.location.zzaz;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zzap implements RemoteCall<zzaz, TaskCompletionSource<Boolean>> {
    private boolean zza = true;

    protected zzap() {
    }

    /* access modifiers changed from: protected */
    public final boolean zza() {
        return this.zza;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(boolean z) {
        this.zza = false;
    }
}
