package com.google.firebase.concurrent;

import com.google.firebase.concurrent.DelegatingScheduledFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final /* synthetic */ class DelegatingScheduledExecutorService$$ExternalSyntheticLambda0 implements DelegatingScheduledFuture.Resolver {
    public final /* synthetic */ DelegatingScheduledExecutorService f$0;
    public final /* synthetic */ Runnable f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ TimeUnit f$3;

    public /* synthetic */ DelegatingScheduledExecutorService$$ExternalSyntheticLambda0(DelegatingScheduledExecutorService delegatingScheduledExecutorService, Runnable runnable, long j, TimeUnit timeUnit) {
        this.f$0 = delegatingScheduledExecutorService;
        this.f$1 = runnable;
        this.f$2 = j;
        this.f$3 = timeUnit;
    }

    public final ScheduledFuture addCompleter(DelegatingScheduledFuture.Completer completer) {
        return this.f$0.lambda$schedule$2(this.f$1, this.f$2, this.f$3, completer);
    }
}
