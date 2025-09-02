package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zax extends IStatusCallback.Stub {
    final /* synthetic */ TaskCompletionSource zaa;

    zax(zay zay, TaskCompletionSource taskCompletionSource) {
        this.zaa = taskCompletionSource;
    }

    public final void onResult(Status status) {
        TaskUtil.trySetResultOrApiException(status, null, this.zaa);
    }
}
