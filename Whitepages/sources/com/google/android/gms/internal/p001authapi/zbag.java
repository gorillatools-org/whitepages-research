package com.google.android.gms.internal.p001authapi;

import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/* renamed from: com.google.android.gms.internal.auth-api.zbag  reason: invalid package */
public final /* synthetic */ class zbag implements RemoteCall {
    public final /* synthetic */ zbap zba;
    public final /* synthetic */ GetPhoneNumberHintIntentRequest zbb;

    public /* synthetic */ zbag(zbap zbap, GetPhoneNumberHintIntentRequest getPhoneNumberHintIntentRequest) {
        this.zba = zbap;
        this.zbb = getPhoneNumberHintIntentRequest;
    }

    public final void accept(Object obj, Object obj2) {
        this.zba.zba(this.zbb, (zbaq) obj, (TaskCompletionSource) obj2);
    }
}
