package com.facebook;

import com.facebook.AccessToken;
import com.facebook.AccessTokenManager;
import com.facebook.GraphRequestBatch;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public final /* synthetic */ class AccessTokenManager$$ExternalSyntheticLambda3 implements GraphRequestBatch.Callback {
    public final /* synthetic */ AccessTokenManager.RefreshResult f$0;
    public final /* synthetic */ AccessToken f$1;
    public final /* synthetic */ AtomicBoolean f$3;
    public final /* synthetic */ Set f$4;
    public final /* synthetic */ Set f$5;
    public final /* synthetic */ Set f$6;
    public final /* synthetic */ AccessTokenManager f$7;

    public /* synthetic */ AccessTokenManager$$ExternalSyntheticLambda3(AccessTokenManager.RefreshResult refreshResult, AccessToken accessToken, AccessToken.AccessTokenRefreshCallback accessTokenRefreshCallback, AtomicBoolean atomicBoolean, Set set, Set set2, Set set3, AccessTokenManager accessTokenManager) {
        this.f$0 = refreshResult;
        this.f$1 = accessToken;
        this.f$3 = atomicBoolean;
        this.f$4 = set;
        this.f$5 = set2;
        this.f$6 = set3;
        this.f$7 = accessTokenManager;
    }

    public final void onBatchCompleted(GraphRequestBatch graphRequestBatch) {
        AccessTokenManager.refreshCurrentAccessTokenImpl$lambda$3(this.f$0, this.f$1, (AccessToken.AccessTokenRefreshCallback) null, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, graphRequestBatch);
    }
}
