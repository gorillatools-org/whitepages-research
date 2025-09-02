package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zas extends zaa {
    final /* synthetic */ TaskCompletionSource zaa;

    zas(zay zay, TaskCompletionSource taskCompletionSource) {
        this.zaa = taskCompletionSource;
    }

    public final void zab(Status status) {
        TaskUtil.trySetResultOrApiException(status, null, this.zaa);
    }
}
