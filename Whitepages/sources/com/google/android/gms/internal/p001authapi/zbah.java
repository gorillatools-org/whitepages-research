package com.google.android.gms.internal.p001authapi;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.auth-api.zbah  reason: invalid package */
public final /* synthetic */ class zbah implements RemoteCall {
    public final /* synthetic */ zbap zba;

    public /* synthetic */ zbah(zbap zbap) {
        this.zba = zbap;
    }

    public final void accept(Object obj, Object obj2) {
        this.zba.zbb((zbaq) obj, (TaskCompletionSource) obj2);
    }
}
