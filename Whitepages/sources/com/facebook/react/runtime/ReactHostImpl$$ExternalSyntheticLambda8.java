package com.facebook.react.runtime;

import com.facebook.react.runtime.ReactHostImpl;
import com.facebook.react.runtime.internal.bolts.Continuation;
import com.facebook.react.runtime.internal.bolts.Task;

public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda8 implements Continuation {
    public final Object then(Task task) {
        return ((ReactHostImpl.CreationResult) task.getResult()).mInstance;
    }
}
