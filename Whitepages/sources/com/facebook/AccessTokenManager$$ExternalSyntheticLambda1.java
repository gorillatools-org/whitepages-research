package com.facebook;

import com.facebook.GraphRequest;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public final /* synthetic */ class AccessTokenManager$$ExternalSyntheticLambda1 implements GraphRequest.Callback {
    public final /* synthetic */ AtomicBoolean f$0;
    public final /* synthetic */ Set f$1;
    public final /* synthetic */ Set f$2;
    public final /* synthetic */ Set f$3;

    public /* synthetic */ AccessTokenManager$$ExternalSyntheticLambda1(AtomicBoolean atomicBoolean, Set set, Set set2, Set set3) {
        this.f$0 = atomicBoolean;
        this.f$1 = set;
        this.f$2 = set2;
        this.f$3 = set3;
    }

    public final void onCompleted(GraphResponse graphResponse) {
        AccessTokenManager.refreshCurrentAccessTokenImpl$lambda$1(this.f$0, this.f$1, this.f$2, this.f$3, graphResponse);
    }
}
