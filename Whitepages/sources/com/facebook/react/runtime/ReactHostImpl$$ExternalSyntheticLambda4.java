package com.facebook.react.runtime;

import com.facebook.react.runtime.ReactHostImpl;

public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda4 implements ReactHostImpl.ReactInstanceCalback {
    public final /* synthetic */ ReactHostImpl f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ ReactSurfaceImpl f$2;

    public /* synthetic */ ReactHostImpl$$ExternalSyntheticLambda4(ReactHostImpl reactHostImpl, String str, ReactSurfaceImpl reactSurfaceImpl) {
        this.f$0 = reactHostImpl;
        this.f$1 = str;
        this.f$2 = reactSurfaceImpl;
    }

    public final void then(ReactInstance reactInstance) {
        this.f$0.lambda$prerenderSurface$0(this.f$1, this.f$2, reactInstance);
    }
}
