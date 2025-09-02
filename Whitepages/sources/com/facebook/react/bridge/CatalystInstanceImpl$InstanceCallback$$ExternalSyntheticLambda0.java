package com.facebook.react.bridge;

public final /* synthetic */ class CatalystInstanceImpl$InstanceCallback$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ CatalystInstanceImpl f$0;

    public /* synthetic */ CatalystInstanceImpl$InstanceCallback$$ExternalSyntheticLambda0(CatalystInstanceImpl catalystInstanceImpl) {
        this.f$0 = catalystInstanceImpl;
    }

    public final void run() {
        this.f$0.mNativeModuleRegistry.onBatchComplete();
    }
}
