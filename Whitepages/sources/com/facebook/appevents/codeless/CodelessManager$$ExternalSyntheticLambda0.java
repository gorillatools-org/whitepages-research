package com.facebook.appevents.codeless;

import com.facebook.appevents.codeless.ViewIndexingTrigger;
import com.facebook.internal.FetchedAppSettings;

public final /* synthetic */ class CodelessManager$$ExternalSyntheticLambda0 implements ViewIndexingTrigger.OnShakeListener {
    public final /* synthetic */ FetchedAppSettings f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ CodelessManager$$ExternalSyntheticLambda0(FetchedAppSettings fetchedAppSettings, String str) {
        this.f$0 = fetchedAppSettings;
        this.f$1 = str;
    }

    public final void onShake() {
        CodelessManager.onActivityResumed$lambda$0(this.f$0, this.f$1);
    }
}
