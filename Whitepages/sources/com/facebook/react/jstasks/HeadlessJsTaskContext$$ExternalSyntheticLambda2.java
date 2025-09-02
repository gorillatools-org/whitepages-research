package com.facebook.react.jstasks;

public final /* synthetic */ class HeadlessJsTaskContext$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ HeadlessJsTaskContext f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ HeadlessJsTaskContext$$ExternalSyntheticLambda2(HeadlessJsTaskContext headlessJsTaskContext, int i) {
        this.f$0 = headlessJsTaskContext;
        this.f$1 = i;
    }

    public final void run() {
        HeadlessJsTaskContext.finishTask$lambda$4(this.f$0, this.f$1);
    }
}
