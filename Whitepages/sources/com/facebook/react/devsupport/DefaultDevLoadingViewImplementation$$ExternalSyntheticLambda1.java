package com.facebook.react.devsupport;

public final /* synthetic */ class DefaultDevLoadingViewImplementation$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ Integer f$0;
    public final /* synthetic */ Integer f$1;
    public final /* synthetic */ DefaultDevLoadingViewImplementation f$2;
    public final /* synthetic */ String f$3;

    public /* synthetic */ DefaultDevLoadingViewImplementation$$ExternalSyntheticLambda1(Integer num, Integer num2, DefaultDevLoadingViewImplementation defaultDevLoadingViewImplementation, String str) {
        this.f$0 = num;
        this.f$1 = num2;
        this.f$2 = defaultDevLoadingViewImplementation;
        this.f$3 = str;
    }

    public final void run() {
        DefaultDevLoadingViewImplementation.updateProgress$lambda$1(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
