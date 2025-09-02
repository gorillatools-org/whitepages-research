package com.facebook.react.runtime;

import com.facebook.react.runtime.internal.bolts.Continuation;
import com.facebook.react.runtime.internal.bolts.Task;

public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda19 implements Continuation {
    public final /* synthetic */ ReactHostImpl f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ ReactHostImpl$$ExternalSyntheticLambda19(ReactHostImpl reactHostImpl, int i, int i2) {
        this.f$0 = reactHostImpl;
        this.f$1 = i;
        this.f$2 = i2;
    }

    public final Object then(Task task) {
        return this.f$0.lambda$waitThenCallGetOrCreateReactInstanceTaskWithRetries$20(this.f$1, this.f$2, task);
    }
}
