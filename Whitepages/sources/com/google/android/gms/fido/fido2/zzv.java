package com.google.android.gms.fido.fido2;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.fido.zzf;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;

final class zzv extends zzf {
    final /* synthetic */ TaskCompletionSource zza;

    zzv(Fido2PrivilegedApiClient fido2PrivilegedApiClient, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void zzb(List list) {
        this.zza.setResult(list);
    }

    public final void zzc(Status status) {
        this.zza.trySetException(new ApiException(status));
    }
}
