package com.facebook.appevents.gps.ara;

import com.facebook.appevents.AppEvent;

public final /* synthetic */ class GpsAraTriggersManager$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ AppEvent f$1;

    public /* synthetic */ GpsAraTriggersManager$$ExternalSyntheticLambda0(String str, AppEvent appEvent) {
        this.f$0 = str;
        this.f$1 = appEvent;
    }

    public final void run() {
        GpsAraTriggersManager.registerTriggerAsync$lambda$0(this.f$0, this.f$1);
    }
}
