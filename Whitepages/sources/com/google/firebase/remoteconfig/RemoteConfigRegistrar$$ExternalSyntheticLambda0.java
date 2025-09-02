package com.google.firebase.remoteconfig;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.Qualified;

public final /* synthetic */ class RemoteConfigRegistrar$$ExternalSyntheticLambda0 implements ComponentFactory {
    public final /* synthetic */ Qualified f$0;

    public /* synthetic */ RemoteConfigRegistrar$$ExternalSyntheticLambda0(Qualified qualified) {
        this.f$0 = qualified;
    }

    public final Object create(ComponentContainer componentContainer) {
        return RemoteConfigRegistrar.lambda$getComponents$0(this.f$0, componentContainer);
    }
}
