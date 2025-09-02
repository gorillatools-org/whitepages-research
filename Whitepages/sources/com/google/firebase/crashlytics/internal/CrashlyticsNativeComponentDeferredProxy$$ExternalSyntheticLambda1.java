package com.google.firebase.crashlytics.internal;

import com.google.firebase.crashlytics.internal.model.StaticSessionData;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;

public final /* synthetic */ class CrashlyticsNativeComponentDeferredProxy$$ExternalSyntheticLambda1 implements Deferred.DeferredHandler {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ StaticSessionData f$3;

    public /* synthetic */ CrashlyticsNativeComponentDeferredProxy$$ExternalSyntheticLambda1(String str, String str2, long j, StaticSessionData staticSessionData) {
        this.f$0 = str;
        this.f$1 = str2;
        this.f$2 = j;
        this.f$3 = staticSessionData;
    }

    public final void handle(Provider provider) {
        ((CrashlyticsNativeComponent) provider.get()).prepareNativeSession(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
