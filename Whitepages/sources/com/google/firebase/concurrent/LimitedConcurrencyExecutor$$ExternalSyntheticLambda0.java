package com.google.firebase.concurrent;

public final /* synthetic */ class LimitedConcurrencyExecutor$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ LimitedConcurrencyExecutor f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ LimitedConcurrencyExecutor$$ExternalSyntheticLambda0(LimitedConcurrencyExecutor limitedConcurrencyExecutor, Runnable runnable) {
        this.f$0 = limitedConcurrencyExecutor;
        this.f$1 = runnable;
    }

    public final void run() {
        this.f$0.lambda$decorate$0(this.f$1);
    }
}
