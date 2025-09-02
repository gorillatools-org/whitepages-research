package com.facebook.internal;

import com.facebook.internal.FetchedAppGateKeepersManager;

public final /* synthetic */ class FetchedAppGateKeepersManager$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ FetchedAppGateKeepersManager.Callback f$0;

    public /* synthetic */ FetchedAppGateKeepersManager$$ExternalSyntheticLambda1(FetchedAppGateKeepersManager.Callback callback) {
        this.f$0 = callback;
    }

    public final void run() {
        FetchedAppGateKeepersManager.pollCallbacks$lambda$1(this.f$0);
    }
}
