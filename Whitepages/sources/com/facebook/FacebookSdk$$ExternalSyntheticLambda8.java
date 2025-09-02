package com.facebook;

import android.content.Context;

public final /* synthetic */ class FacebookSdk$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ Context f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ FacebookSdk$$ExternalSyntheticLambda8(Context context, String str) {
        this.f$0 = context;
        this.f$1 = str;
    }

    public final void run() {
        FacebookSdk.publishInstallAsync$lambda$15(this.f$0, this.f$1);
    }
}
