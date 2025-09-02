package com.facebook.react.jstasks;

public final /* synthetic */ class HeadlessJsTaskContext$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ HeadlessJsTaskContext f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ HeadlessJsTaskContext$$ExternalSyntheticLambda1(HeadlessJsTaskContext headlessJsTaskContext, int i) {
        this.f$0 = headlessJsTaskContext;
        this.f$1 = i;
    }

    public final void run() {
        HeadlessJsTaskContext.scheduleTaskTimeout$lambda$5(this.f$0, this.f$1);
    }
}
