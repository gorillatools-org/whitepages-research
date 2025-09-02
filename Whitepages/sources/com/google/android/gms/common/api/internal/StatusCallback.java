package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.IStatusCallback;

@KeepForSdk
public class StatusCallback extends IStatusCallback.Stub {
    @KeepForSdk
    private final BaseImplementation.ResultHolder<Status> resultHolder;

    @KeepForSdk
    public StatusCallback(BaseImplementation.ResultHolder<Status> resultHolder2) {
        this.resultHolder = resultHolder2;
    }

    @KeepForSdk
    public void onResult(Status status) {
        this.resultHolder.setResult(status);
    }
}
