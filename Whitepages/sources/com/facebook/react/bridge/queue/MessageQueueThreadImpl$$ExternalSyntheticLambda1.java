package com.facebook.react.bridge.queue;

import com.facebook.react.common.futures.SimpleSettableFuture;

public final /* synthetic */ class MessageQueueThreadImpl$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ SimpleSettableFuture f$0;

    public /* synthetic */ MessageQueueThreadImpl$$ExternalSyntheticLambda1(SimpleSettableFuture simpleSettableFuture) {
        this.f$0 = simpleSettableFuture;
    }

    public final void run() {
        MessageQueueThreadImpl.lambda$startNewBackgroundThread$2(this.f$0);
    }
}
