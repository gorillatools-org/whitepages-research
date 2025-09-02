package com.google.firebase.concurrent;

public final /* synthetic */ class CustomThreadFactory$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ CustomThreadFactory f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ CustomThreadFactory$$ExternalSyntheticLambda0(CustomThreadFactory customThreadFactory, Runnable runnable) {
        this.f$0 = customThreadFactory;
        this.f$1 = runnable;
    }

    public final void run() {
        this.f$0.lambda$newThread$0(this.f$1);
    }
}
