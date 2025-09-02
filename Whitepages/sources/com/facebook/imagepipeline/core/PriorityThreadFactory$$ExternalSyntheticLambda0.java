package com.facebook.imagepipeline.core;

public final /* synthetic */ class PriorityThreadFactory$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ PriorityThreadFactory f$0;
    public final /* synthetic */ Runnable f$1;

    public /* synthetic */ PriorityThreadFactory$$ExternalSyntheticLambda0(PriorityThreadFactory priorityThreadFactory, Runnable runnable) {
        this.f$0 = priorityThreadFactory;
        this.f$1 = runnable;
    }

    public final void run() {
        PriorityThreadFactory.newThread$lambda$0(this.f$0, this.f$1);
    }
}
