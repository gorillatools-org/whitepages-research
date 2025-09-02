package com.google.android.gms.internal.p001authapi;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.auth-api.zbam  reason: invalid package */
final class zbam extends IStatusCallback.Stub {
    final /* synthetic */ TaskCompletionSource zba;

    zbam(zbap zbap, TaskCompletionSource taskCompletionSource) {
        this.zba = taskCompletionSource;
    }

    public final void onResult(Status status) throws RemoteException {
        TaskUtil.setResultOrApiException(status, this.zba);
    }
}
