package com.google.firebase.tracing;

import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;

public final /* synthetic */ class ComponentMonitor$$ExternalSyntheticLambda0 implements ComponentFactory {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ Component f$1;

    public /* synthetic */ ComponentMonitor$$ExternalSyntheticLambda0(String str, Component component) {
        this.f$0 = str;
        this.f$1 = component;
    }

    public final Object create(ComponentContainer componentContainer) {
        return ComponentMonitor.lambda$processRegistrar$0(this.f$0, this.f$1, componentContainer);
    }
}
