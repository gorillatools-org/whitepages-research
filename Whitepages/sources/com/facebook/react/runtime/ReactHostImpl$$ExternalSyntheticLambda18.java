package com.facebook.react.runtime;

import com.facebook.react.bridge.Callback;
import com.facebook.react.runtime.ReactHostImpl;

public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda18 implements ReactHostImpl.ReactInstanceCalback {
    public final /* synthetic */ ReactHostImpl f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ Callback f$4;

    public /* synthetic */ ReactHostImpl$$ExternalSyntheticLambda18(ReactHostImpl reactHostImpl, String str, int i, String str2, Callback callback) {
        this.f$0 = reactHostImpl;
        this.f$1 = str;
        this.f$2 = i;
        this.f$3 = str2;
        this.f$4 = callback;
    }

    public final void then(ReactInstance reactInstance) {
        this.f$0.lambda$registerSegment$12(this.f$1, this.f$2, this.f$3, this.f$4, reactInstance);
    }
}
