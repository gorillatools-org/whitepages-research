package com.google.firebase.concurrent;

import java.util.concurrent.Callable;

public final /* synthetic */ class LimitedConcurrencyExecutorService$$ExternalSyntheticLambda1 implements Callable {
    public final /* synthetic */ Runnable f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ LimitedConcurrencyExecutorService$$ExternalSyntheticLambda1(Runnable runnable, Object obj) {
        this.f$0 = runnable;
        this.f$1 = obj;
    }

    public final Object call() {
        return this.f$0.run();
    }
}
