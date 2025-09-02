package com.facebook.react.runtime;

import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.runtime.ReactHostImpl;

public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda13 implements ReactHostImpl.ReactInstanceCalback {
    public final /* synthetic */ ReactHostImpl f$0;
    public final /* synthetic */ JSBundleLoader f$1;

    public /* synthetic */ ReactHostImpl$$ExternalSyntheticLambda13(ReactHostImpl reactHostImpl, JSBundleLoader jSBundleLoader) {
        this.f$0 = reactHostImpl;
        this.f$1 = jSBundleLoader;
    }

    public final void then(ReactInstance reactInstance) {
        this.f$0.lambda$loadBundle$11(this.f$1, reactInstance);
    }
}
