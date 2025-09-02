package com.google.firebase.crashlytics;

import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;

public final /* synthetic */ class AnalyticsDeferredProxy$$ExternalSyntheticLambda2 implements Deferred.DeferredHandler {
    public final /* synthetic */ AnalyticsDeferredProxy f$0;

    public /* synthetic */ AnalyticsDeferredProxy$$ExternalSyntheticLambda2(AnalyticsDeferredProxy analyticsDeferredProxy) {
        this.f$0 = analyticsDeferredProxy;
    }

    public final void handle(Provider provider) {
        this.f$0.lambda$init$2(provider);
    }
}
