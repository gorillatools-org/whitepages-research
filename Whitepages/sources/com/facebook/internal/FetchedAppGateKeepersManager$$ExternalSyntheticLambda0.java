package com.facebook.internal;

import android.content.Context;

public final /* synthetic */ class FetchedAppGateKeepersManager$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ Context f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ FetchedAppGateKeepersManager$$ExternalSyntheticLambda0(String str, Context context, String str2) {
        this.f$0 = str;
        this.f$1 = context;
        this.f$2 = str2;
    }

    public final void run() {
        FetchedAppGateKeepersManager.loadAppGateKeepersAsync$lambda$0(this.f$0, this.f$1, this.f$2);
    }
}
