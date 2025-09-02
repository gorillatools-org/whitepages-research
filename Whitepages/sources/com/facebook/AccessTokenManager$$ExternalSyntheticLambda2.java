package com.facebook;

import com.facebook.AccessTokenManager;
import com.facebook.GraphRequest;

public final /* synthetic */ class AccessTokenManager$$ExternalSyntheticLambda2 implements GraphRequest.Callback {
    public final /* synthetic */ AccessTokenManager.RefreshResult f$0;

    public /* synthetic */ AccessTokenManager$$ExternalSyntheticLambda2(AccessTokenManager.RefreshResult refreshResult) {
        this.f$0 = refreshResult;
    }

    public final void onCompleted(GraphResponse graphResponse) {
        AccessTokenManager.refreshCurrentAccessTokenImpl$lambda$2(this.f$0, graphResponse);
    }
}
