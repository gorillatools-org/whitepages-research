package com.facebook.internal;

import com.facebook.internal.FetchedAppSettingsManager;

public final /* synthetic */ class FetchedAppSettingsManager$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ FetchedAppSettingsManager.FetchedAppSettingsCallback f$0;
    public final /* synthetic */ FetchedAppSettings f$1;

    public /* synthetic */ FetchedAppSettingsManager$$ExternalSyntheticLambda3(FetchedAppSettingsManager.FetchedAppSettingsCallback fetchedAppSettingsCallback, FetchedAppSettings fetchedAppSettings) {
        this.f$0 = fetchedAppSettingsCallback;
        this.f$1 = fetchedAppSettings;
    }

    public final void run() {
        FetchedAppSettingsManager.pollCallbacks$lambda$2(this.f$0, this.f$1);
    }
}
