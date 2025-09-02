package com.facebook.appevents.cloudbridge;

import com.facebook.GraphRequest;

public final /* synthetic */ class AppEventsConversionsAPITransformerWebRequests$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ GraphRequest f$0;

    public /* synthetic */ AppEventsConversionsAPITransformerWebRequests$$ExternalSyntheticLambda0(GraphRequest graphRequest) {
        this.f$0 = graphRequest;
    }

    public final void run() {
        AppEventsConversionsAPITransformerWebRequests.transformGraphRequestAndSendToCAPIGEndPoint$lambda$0(this.f$0);
    }
}
