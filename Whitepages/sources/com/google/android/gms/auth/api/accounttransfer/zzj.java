package com.google.android.gms.auth.api.accounttransfer;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.auth.zzan;
import com.google.android.gms.tasks.TaskCompletionSource;

class zzj extends zzan {
    final zzl zzb;

    public zzj(zzl zzl) {
        this.zzb = zzl;
    }

    public final void zzd(Status status) {
        TaskCompletionSource taskCompletionSource = this.zzb.zzb;
        int i = AccountTransferClient.zza;
        taskCompletionSource.setException(new AccountTransferException(status));
    }
}
