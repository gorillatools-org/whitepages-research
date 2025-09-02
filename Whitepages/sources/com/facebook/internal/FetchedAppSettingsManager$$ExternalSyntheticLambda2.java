package com.facebook.internal;

import com.facebook.internal.FetchedAppSettingsManager;

public final /* synthetic */ class FetchedAppSettingsManager$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ FetchedAppSettingsManager.FetchedAppSettingsCallback f$0;

    public /* synthetic */ FetchedAppSettingsManager$$ExternalSyntheticLambda2(FetchedAppSettingsManager.FetchedAppSettingsCallback fetchedAppSettingsCallback) {
        this.f$0 = fetchedAppSettingsCallback;
    }

    public final void run() {
        FetchedAppSettingsManager.pollCallbacks$lambda$1(this.f$0);
    }
}
