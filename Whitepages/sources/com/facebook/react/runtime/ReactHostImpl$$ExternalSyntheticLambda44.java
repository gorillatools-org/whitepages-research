package com.facebook.react.runtime;

import java.util.concurrent.Callable;

public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda44 implements Callable {
    public final /* synthetic */ ReactHostImpl f$0;

    public /* synthetic */ ReactHostImpl$$ExternalSyntheticLambda44(ReactHostImpl reactHostImpl) {
        this.f$0 = reactHostImpl;
    }

    public final Object call() {
        return this.f$0.getOrCreateStartTask();
    }
}
