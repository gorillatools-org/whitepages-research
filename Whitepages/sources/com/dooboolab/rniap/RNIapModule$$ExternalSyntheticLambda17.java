package com.dooboolab.rniap;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;

public final /* synthetic */ class RNIapModule$$ExternalSyntheticLambda17 implements Callback {
    public final /* synthetic */ Promise f$0;

    public /* synthetic */ RNIapModule$$ExternalSyntheticLambda17(Promise promise) {
        this.f$0 = promise;
    }

    public final void invoke(Object[] objArr) {
        RNIapModule.ensureConnection$lambda$1(this.f$0, objArr);
    }
}
