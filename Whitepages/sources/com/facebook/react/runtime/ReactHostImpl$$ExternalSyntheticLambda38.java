package com.facebook.react.runtime;

import com.facebook.react.runtime.internal.bolts.Continuation;
import com.facebook.react.runtime.internal.bolts.Task;

public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda38 implements Continuation {
    public final /* synthetic */ ReactHostImpl f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ ReactHostImpl$$ExternalSyntheticLambda38(ReactHostImpl reactHostImpl, String str) {
        this.f$0 = reactHostImpl;
        this.f$1 = str;
    }

    public final Object then(Task task) {
        return this.f$0.lambda$getOrCreateDestroyTask$40(this.f$1, task);
    }
}
