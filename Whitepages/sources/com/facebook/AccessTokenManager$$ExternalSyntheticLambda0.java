package com.facebook;

import com.facebook.AccessToken;

public final /* synthetic */ class AccessTokenManager$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ AccessTokenManager f$0;

    public /* synthetic */ AccessTokenManager$$ExternalSyntheticLambda0(AccessTokenManager accessTokenManager, AccessToken.AccessTokenRefreshCallback accessTokenRefreshCallback) {
        this.f$0 = accessTokenManager;
    }

    public final void run() {
        AccessTokenManager.refreshCurrentAccessToken$lambda$0(this.f$0, (AccessToken.AccessTokenRefreshCallback) null);
    }
}
