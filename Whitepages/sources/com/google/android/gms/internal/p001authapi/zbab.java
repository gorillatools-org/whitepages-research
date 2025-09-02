package com.google.android.gms.internal.p001authapi;

import com.google.android.gms.auth.api.identity.SavePasswordRequest;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.auth-api.zbab  reason: invalid package */
public final /* synthetic */ class zbab implements RemoteCall {
    public final /* synthetic */ zbaf zba;
    public final /* synthetic */ SavePasswordRequest zbb;

    public /* synthetic */ zbab(zbaf zbaf, SavePasswordRequest savePasswordRequest) {
        this.zba = zbaf;
        this.zbb = savePasswordRequest;
    }

    public final void accept(Object obj, Object obj2) {
        ((zbm) ((zbg) obj).getService()).zbd(new zbae(this.zba, (TaskCompletionSource) obj2), (SavePasswordRequest) Preconditions.checkNotNull(this.zbb));
    }
}
