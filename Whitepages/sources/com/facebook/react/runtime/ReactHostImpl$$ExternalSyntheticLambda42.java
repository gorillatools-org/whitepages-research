package com.facebook.react.runtime;

import com.facebook.react.bridge.queue.QueueThreadExceptionHandler;

public final /* synthetic */ class ReactHostImpl$$ExternalSyntheticLambda42 implements QueueThreadExceptionHandler {
    public final /* synthetic */ ReactHostImpl f$0;

    public /* synthetic */ ReactHostImpl$$ExternalSyntheticLambda42(ReactHostImpl reactHostImpl) {
        this.f$0 = reactHostImpl;
    }

    public final void handleException(Exception exc) {
        this.f$0.handleHostException(exc);
    }
}
