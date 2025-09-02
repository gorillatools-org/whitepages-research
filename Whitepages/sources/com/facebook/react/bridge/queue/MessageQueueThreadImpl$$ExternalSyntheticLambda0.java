package com.facebook.react.bridge.queue;

import com.facebook.react.common.futures.SimpleSettableFuture;
import java.util.concurrent.Callable;

public final /* synthetic */ class MessageQueueThreadImpl$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ SimpleSettableFuture f$0;
    public final /* synthetic */ Callable f$1;

    public /* synthetic */ MessageQueueThreadImpl$$ExternalSyntheticLambda0(SimpleSettableFuture simpleSettableFuture, Callable callable) {
        this.f$0 = simpleSettableFuture;
        this.f$1 = callable;
    }

    public final void run() {
        MessageQueueThreadImpl.lambda$callOnQueue$0(this.f$0, this.f$1);
    }
}
