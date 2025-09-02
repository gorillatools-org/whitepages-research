package com.google.android.gms.internal.p001authapi;

import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.AuthorizationResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.auth-api.zby  reason: invalid package */
final class zby extends zbh {
    final /* synthetic */ TaskCompletionSource zba;

    zby(zbz zbz, TaskCompletionSource taskCompletionSource) {
        this.zba = taskCompletionSource;
    }

    public final void zbb(Status status, AuthorizationResult authorizationResult) throws RemoteException {
        if (status.isSuccess()) {
            this.zba.setResult(authorizationResult);
        } else {
            this.zba.setException(ApiExceptionUtil.fromStatus(status));
        }
    }
}
