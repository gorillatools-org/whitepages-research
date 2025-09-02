package com.facebook.react.runtime;

import com.facebook.react.runtime.ReactHostImpl;
import com.facebook.react.runtime.internal.bolts.Continuation;
import com.facebook.react.runtime.internal.bolts.Task;

public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda24 implements Continuation {
    public final /* synthetic */ ReactHostImpl f$0;
    public final /* synthetic */ ReactHostImpl.ReactInstanceTaskUnwrapper f$1;

    public /* synthetic */ ReactHostImpl$$ExternalSyntheticLambda24(ReactHostImpl reactHostImpl, ReactHostImpl.ReactInstanceTaskUnwrapper reactInstanceTaskUnwrapper) {
        this.f$0 = reactHostImpl;
        this.f$1 = reactInstanceTaskUnwrapper;
    }

    public final Object then(Task task) {
        return this.f$0.lambda$getOrCreateReloadTask$34(this.f$1, task);
    }
}
