package com.facebook.appevents;

public final /* synthetic */ class AppEventQueue$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ AccessTokenAppIdPair f$0;
    public final /* synthetic */ AppEvent f$1;

    public /* synthetic */ AppEventQueue$$ExternalSyntheticLambda1(AccessTokenAppIdPair accessTokenAppIdPair, AppEvent appEvent) {
        this.f$0 = accessTokenAppIdPair;
        this.f$1 = appEvent;
    }

    public final void run() {
        AppEventQueue.add$lambda$3(this.f$0, this.f$1);
    }
}
