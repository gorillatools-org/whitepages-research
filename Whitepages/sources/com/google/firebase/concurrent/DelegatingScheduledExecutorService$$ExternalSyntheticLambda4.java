package com.google.firebase.concurrent;

import com.google.firebase.concurrent.DelegatingScheduledFuture;

public final /* synthetic */ class DelegatingScheduledExecutorService$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ Runnable f$0;
    public final /* synthetic */ DelegatingScheduledFuture.Completer f$1;

    public /* synthetic */ DelegatingScheduledExecutorService$$ExternalSyntheticLambda4(Runnable runnable, DelegatingScheduledFuture.Completer completer) {
        this.f$0 = runnable;
        this.f$1 = completer;
    }

    public final void run() {
        DelegatingScheduledExecutorService.lambda$schedule$0(this.f$0, this.f$1);
    }
}
