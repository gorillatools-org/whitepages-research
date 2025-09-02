package com.google.android.gms.fido.fido2;

import android.os.RemoteException;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.fido.zzd;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzj extends zzd {
    final /* synthetic */ TaskCompletionSource zza;

    zzj(Fido2ApiClient fido2ApiClient, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void zzb(boolean z) throws RemoteException {
        this.zza.setResult(Boolean.valueOf(z));
    }

    public final void zzc(Status status) throws RemoteException {
        this.zza.trySetException(new ApiException(status));
    }
}
