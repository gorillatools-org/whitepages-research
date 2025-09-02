package com.google.firebase.concurrent;

import com.google.firebase.concurrent.DelegatingScheduledFuture;

public final /* synthetic */ class DelegatingScheduledExecutorService$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ DelegatingScheduledExecutorService f$0;
    public final /* synthetic */ Runnable f$1;
    public final /* synthetic */ DelegatingScheduledFuture.Completer f$2;

    public /* synthetic */ DelegatingScheduledExecutorService$$ExternalSyntheticLambda1(DelegatingScheduledExecutorService delegatingScheduledExecutorService, Runnable runnable, DelegatingScheduledFuture.Completer completer) {
        this.f$0 = delegatingScheduledExecutorService;
        this.f$1 = runnable;
        this.f$2 = completer;
    }

    public final void run() {
        this.f$0.lambda$scheduleAtFixedRate$7(this.f$1, this.f$2);
    }
}
