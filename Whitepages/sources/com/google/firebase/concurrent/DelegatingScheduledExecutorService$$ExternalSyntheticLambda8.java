package com.google.firebase.concurrent;

import com.google.firebase.concurrent.DelegatingScheduledFuture;
import java.util.concurrent.Callable;

public final /* synthetic */ class DelegatingScheduledExecutorService$$ExternalSyntheticLambda8 implements Callable {
    public final /* synthetic */ DelegatingScheduledExecutorService f$0;
    public final /* synthetic */ Callable f$1;
    public final /* synthetic */ DelegatingScheduledFuture.Completer f$2;

    public /* synthetic */ DelegatingScheduledExecutorService$$ExternalSyntheticLambda8(DelegatingScheduledExecutorService delegatingScheduledExecutorService, Callable callable, DelegatingScheduledFuture.Completer completer) {
        this.f$0 = delegatingScheduledExecutorService;
        this.f$1 = callable;
        this.f$2 = completer;
    }

    public final Object call() {
        return this.f$0.lambda$schedule$4(this.f$1, this.f$2);
    }
}
