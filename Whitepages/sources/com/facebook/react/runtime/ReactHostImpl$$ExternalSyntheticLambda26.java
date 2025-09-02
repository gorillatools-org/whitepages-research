package com.facebook.react.runtime;

import com.facebook.react.runtime.ReactHostImpl;
import com.facebook.react.runtime.internal.bolts.Continuation;
import com.facebook.react.runtime.internal.bolts.Task;

public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda26 implements Continuation {
    public final /* synthetic */ ReactHostImpl f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ ReactHostImpl.ReactInstanceCalback f$2;

    public /* synthetic */ ReactHostImpl$$ExternalSyntheticLambda26(ReactHostImpl reactHostImpl, String str, ReactHostImpl.ReactInstanceCalback reactInstanceCalback) {
        this.f$0 = reactHostImpl;
        this.f$1 = str;
        this.f$2 = reactInstanceCalback;
    }

    public final Object then(Task task) {
        return this.f$0.lambda$callWithExistingReactInstance$16(this.f$1, this.f$2, task);
    }
}
