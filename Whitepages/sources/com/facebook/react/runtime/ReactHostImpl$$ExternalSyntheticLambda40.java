package com.facebook.react.runtime;

import com.facebook.react.devsupport.interfaces.PackagerStatusCallback;
import com.facebook.react.runtime.internal.bolts.TaskCompletionSource;

public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda40 implements PackagerStatusCallback {
    public final /* synthetic */ ReactHostImpl f$0;
    public final /* synthetic */ TaskCompletionSource f$1;

    public /* synthetic */ ReactHostImpl$$ExternalSyntheticLambda40(ReactHostImpl reactHostImpl, TaskCompletionSource taskCompletionSource) {
        this.f$0 = reactHostImpl;
        this.f$1 = taskCompletionSource;
    }

    public final void onPackagerStatusFetched(boolean z) {
        this.f$0.lambda$isMetroRunning$28(this.f$1, z);
    }
}
