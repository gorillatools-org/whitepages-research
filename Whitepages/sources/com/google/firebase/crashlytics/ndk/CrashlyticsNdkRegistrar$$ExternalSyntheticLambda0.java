package com.google.firebase.crashlytics.ndk;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;

public final /* synthetic */ class CrashlyticsNdkRegistrar$$ExternalSyntheticLambda0 implements ComponentFactory {
    public final /* synthetic */ CrashlyticsNdkRegistrar f$0;

    public /* synthetic */ CrashlyticsNdkRegistrar$$ExternalSyntheticLambda0(CrashlyticsNdkRegistrar crashlyticsNdkRegistrar) {
        this.f$0 = crashlyticsNdkRegistrar;
    }

    public final Object create(ComponentContainer componentContainer) {
        return this.f$0.buildCrashlyticsNdk(componentContainer);
    }
}
