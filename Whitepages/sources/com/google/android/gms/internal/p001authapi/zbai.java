package com.google.android.gms.internal.p001authapi;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.auth-api.zbai  reason: invalid package */
public final /* synthetic */ class zbai implements RemoteCall {
    public final /* synthetic */ zbap zba;
    public final /* synthetic */ BeginSignInRequest zbb;

    public /* synthetic */ zbai(zbap zbap, BeginSignInRequest beginSignInRequest) {
        this.zba = zbap;
        this.zbb = beginSignInRequest;
    }

    public final void accept(Object obj, Object obj2) {
        ((zbv) ((zbaq) obj).getService()).zbc(new zbal(this.zba, (TaskCompletionSource) obj2), (BeginSignInRequest) Preconditions.checkNotNull(this.zbb));
    }
}
