package com.google.firebase.crashlytics;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;

public final /* synthetic */ class CrashlyticsRegistrar$$ExternalSyntheticLambda0 implements ComponentFactory {
    public final /* synthetic */ CrashlyticsRegistrar f$0;

    public /* synthetic */ CrashlyticsRegistrar$$ExternalSyntheticLambda0(CrashlyticsRegistrar crashlyticsRegistrar) {
        this.f$0 = crashlyticsRegistrar;
    }

    public final Object create(ComponentContainer componentContainer) {
        return this.f$0.buildCrashlytics(componentContainer);
    }
}
