package com.facebook.react.runtime;

import com.facebook.react.runtime.internal.bolts.Continuation;
import com.facebook.react.runtime.internal.bolts.Task;

public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda30 implements Continuation {
    public final /* synthetic */ ReactHostImpl f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ ReactHostImpl$$ExternalSyntheticLambda30(ReactHostImpl reactHostImpl, String str) {
        this.f$0 = reactHostImpl;
        this.f$1 = str;
    }

    public final Object then(Task task) {
        return this.f$0.lambda$reload$3(this.f$1, task);
    }
}
