package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

public final /* synthetic */ class zap implements RemoteCall {
    public final /* synthetic */ zay zaa;
    public final /* synthetic */ ApiFeatureRequest zab;

    public /* synthetic */ zap(zay zay, ApiFeatureRequest apiFeatureRequest) {
        this.zaa = zay;
        this.zab = apiFeatureRequest;
    }

    public final void accept(Object obj, Object obj2) {
        ((zaf) ((zaz) obj).getService()).zag(new zas(this.zaa, (TaskCompletionSource) obj2), this.zab, (zah) null);
    }
}
