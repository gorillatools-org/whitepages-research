package com.google.firebase.concurrent;

import com.google.firebase.concurrent.DelegatingScheduledFuture;

public final /* synthetic */ class DelegatingScheduledExecutorService$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ Runnable f$0;
    public final /* synthetic */ DelegatingScheduledFuture.Completer f$1;

    public /* synthetic */ DelegatingScheduledExecutorService$$ExternalSyntheticLambda9(Runnable runnable, DelegatingScheduledFuture.Completer completer) {
        this.f$0 = runnable;
        this.f$1 = completer;
    }

    public final void run() {
        DelegatingScheduledExecutorService.lambda$scheduleAtFixedRate$6(this.f$0, this.f$1);
    }
}
