package com.google.android.gms.internal.auth;

import android.os.RemoteException;
import com.google.android.gms.auth.api.proxy.AuthApiStatusCodes;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbn extends zzbd {
    final /* synthetic */ TaskCompletionSource zza;

    zzbn(zzbo zzbo, TaskCompletionSource taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void zzc(String str) throws RemoteException {
        Status status;
        if (str != null) {
            status = Status.RESULT_SUCCESS;
        } else {
            status = new Status(AuthApiStatusCodes.AUTH_APP_CERT_ERROR);
        }
        TaskUtil.setResultOrApiException(status, str, this.zza);
    }
}
