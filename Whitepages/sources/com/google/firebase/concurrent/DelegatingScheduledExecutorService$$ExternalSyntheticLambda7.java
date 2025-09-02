package com.google.firebase.concurrent;

import com.google.firebase.concurrent.DelegatingScheduledFuture;
import java.util.concurrent.Callable;

public final /* synthetic */ class DelegatingScheduledExecutorService$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ Callable f$0;
    public final /* synthetic */ DelegatingScheduledFuture.Completer f$1;

    public /* synthetic */ DelegatingScheduledExecutorService$$ExternalSyntheticLambda7(Callable callable, DelegatingScheduledFuture.Completer completer) {
        this.f$0 = callable;
        this.f$1 = completer;
    }

    public final void run() {
        DelegatingScheduledExecutorService.lambda$schedule$3(this.f$0, this.f$1);
    }
}
