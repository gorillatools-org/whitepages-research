package com.google.android.gms.internal.p001authapi;

import com.google.android.gms.auth.api.identity.GetSignInIntentRequest;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.auth-api.zbaj  reason: invalid package */
public final /* synthetic */ class zbaj implements RemoteCall {
    public final /* synthetic */ zbap zba;
    public final /* synthetic */ GetSignInIntentRequest zbb;

    public /* synthetic */ zbaj(zbap zbap, GetSignInIntentRequest getSignInIntentRequest) {
        this.zba = zbap;
        this.zbb = getSignInIntentRequest;
    }

    public final void accept(Object obj, Object obj2) {
        ((zbv) ((zbaq) obj).getService()).zbe(new zban(this.zba, (TaskCompletionSource) obj2), (GetSignInIntentRequest) Preconditions.checkNotNull(this.zbb));
    }
}
