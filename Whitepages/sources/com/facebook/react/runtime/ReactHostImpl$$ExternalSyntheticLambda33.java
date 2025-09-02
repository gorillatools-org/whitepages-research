package com.facebook.react.runtime;

import com.facebook.react.runtime.internal.bolts.Continuation;
import com.facebook.react.runtime.internal.bolts.Task;

public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda33 implements Continuation {
    public final /* synthetic */ ReactHostImpl f$0;

    public /* synthetic */ ReactHostImpl$$ExternalSyntheticLambda33(ReactHostImpl reactHostImpl) {
        this.f$0 = reactHostImpl;
    }

    public final Object then(Task task) {
        return this.f$0.lambda$getOrCreateStartTask$15(task);
    }
}
