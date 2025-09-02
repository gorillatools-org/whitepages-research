package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import java.util.concurrent.Callable;

public final /* synthetic */ class FcmBroadcastProcessor$$ExternalSyntheticLambda1 implements Callable {
    public final /* synthetic */ Context f$0;
    public final /* synthetic */ Intent f$1;

    public /* synthetic */ FcmBroadcastProcessor$$ExternalSyntheticLambda1(Context context, Intent intent) {
        this.f$0 = context;
        this.f$1 = intent;
    }

    public final Object call() {
        return Integer.valueOf(ServiceStarter.getInstance().startMessagingService(this.f$0, this.f$1));
    }
}
