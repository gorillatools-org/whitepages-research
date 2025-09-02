package com.facebook.react.devsupport;

public final /* synthetic */ class DevSupportManagerBase$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ DevSupportManagerBase f$0;
    public final /* synthetic */ Exception f$1;

    public /* synthetic */ DevSupportManagerBase$$ExternalSyntheticLambda1(DevSupportManagerBase devSupportManagerBase, Exception exc) {
        this.f$0 = devSupportManagerBase;
        this.f$1 = exc;
    }

    public final void run() {
        this.f$0.lambda$reportBundleLoadingFailure$11(this.f$1);
    }
}
