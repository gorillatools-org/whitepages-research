package com.facebook.internal;

import android.content.Context;

public final /* synthetic */ class FetchedAppSettingsManager$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ Context f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ FetchedAppSettingsManager$$ExternalSyntheticLambda1(Context context, String str, String str2) {
        this.f$0 = context;
        this.f$1 = str;
        this.f$2 = str2;
    }

    public final void run() {
        FetchedAppSettingsManager.loadAppSettingsAsync$lambda$0(this.f$0, this.f$1, this.f$2);
    }
}
