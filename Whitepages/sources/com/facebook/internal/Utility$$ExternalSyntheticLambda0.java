package com.facebook.internal;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.internal.Utility;

public final /* synthetic */ class Utility$$ExternalSyntheticLambda0 implements GraphRequest.Callback {
    public final /* synthetic */ Utility.GraphMeRequestWithCacheCallback f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ Utility$$ExternalSyntheticLambda0(Utility.GraphMeRequestWithCacheCallback graphMeRequestWithCacheCallback, String str) {
        this.f$0 = graphMeRequestWithCacheCallback;
        this.f$1 = str;
    }

    public final void onCompleted(GraphResponse graphResponse) {
        Utility.getGraphMeRequestWithCacheAsync$lambda$3(this.f$0, this.f$1, graphResponse);
    }
}
