package com.facebook.react.jstasks;

public final /* synthetic */ class HeadlessJsTaskContext$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ HeadlessJsTaskContext f$0;
    public final /* synthetic */ HeadlessJsTaskConfig f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ HeadlessJsTaskContext$$ExternalSyntheticLambda0(HeadlessJsTaskContext headlessJsTaskContext, HeadlessJsTaskConfig headlessJsTaskConfig, int i) {
        this.f$0 = headlessJsTaskContext;
        this.f$1 = headlessJsTaskConfig;
        this.f$2 = i;
    }

    public final void run() {
        HeadlessJsTaskContext.retryTask$lambda$3(this.f$0, this.f$1, this.f$2);
    }
}
