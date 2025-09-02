package com.facebook.appevents;

public final /* synthetic */ class AppEventQueue$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ AccessTokenAppIdPair f$0;
    public final /* synthetic */ SessionEventsState f$1;

    public /* synthetic */ AppEventQueue$$ExternalSyntheticLambda5(AccessTokenAppIdPair accessTokenAppIdPair, SessionEventsState sessionEventsState) {
        this.f$0 = accessTokenAppIdPair;
        this.f$1 = sessionEventsState;
    }

    public final void run() {
        AppEventQueue.handleResponse$lambda$5(this.f$0, this.f$1);
    }
}
