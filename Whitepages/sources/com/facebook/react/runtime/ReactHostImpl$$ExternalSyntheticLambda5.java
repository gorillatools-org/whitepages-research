package com.facebook.react.runtime;

import com.facebook.react.runtime.internal.bolts.Continuation;
import com.facebook.react.runtime.internal.bolts.Task;

public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda5 implements Continuation {
    public final /* synthetic */ ReactHostImpl f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Exception f$2;

    public /* synthetic */ ReactHostImpl$$ExternalSyntheticLambda5(ReactHostImpl reactHostImpl, String str, Exception exc) {
        this.f$0 = reactHostImpl;
        this.f$1 = str;
        this.f$2 = exc;
    }

    public final Object then(Task task) {
        return this.f$0.lambda$destroy$6(this.f$1, this.f$2, task);
    }
}
