package com.google.firebase.messaging;

import android.content.Context;
import com.google.android.gms.tasks.OnSuccessListener;

public final /* synthetic */ class ProxyNotificationPreferences$$ExternalSyntheticLambda0 implements OnSuccessListener {
    public final /* synthetic */ Context f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ ProxyNotificationPreferences$$ExternalSyntheticLambda0(Context context, boolean z) {
        this.f$0 = context;
        this.f$1 = z;
    }

    public final void onSuccess(Object obj) {
        ProxyNotificationPreferences.setProxyRetentionPreferences(this.f$0, this.f$1);
    }
}
