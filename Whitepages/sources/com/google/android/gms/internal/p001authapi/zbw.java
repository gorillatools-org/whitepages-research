package com.google.android.gms.internal.p001authapi;

import com.google.android.gms.auth.api.identity.AuthorizationRequest;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.auth-api.zbw  reason: invalid package */
public final /* synthetic */ class zbw implements RemoteCall {
    public final /* synthetic */ zbz zba;
    public final /* synthetic */ AuthorizationRequest zbb;

    public /* synthetic */ zbw(zbz zbz, AuthorizationRequest authorizationRequest) {
        this.zba = zbz;
        this.zbb = authorizationRequest;
    }

    public final void accept(Object obj, Object obj2) {
        ((zbj) ((zbf) obj).getService()).zbc(new zby(this.zba, (TaskCompletionSource) obj2), (AuthorizationRequest) Preconditions.checkNotNull(this.zbb));
    }
}
