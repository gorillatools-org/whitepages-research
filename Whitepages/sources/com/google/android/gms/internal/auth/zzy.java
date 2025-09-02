package com.google.android.gms.internal.auth;

import android.os.Bundle;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzy extends zzj {
    final /* synthetic */ TaskCompletionSource zza;

    zzy(zzab zzab, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void zzb(Status status, Bundle bundle) {
        zzab.zzf(status, bundle, this.zza);
    }
}
