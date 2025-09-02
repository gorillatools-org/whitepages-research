package com.facebook.react;

import com.facebook.react.ReactInstanceManager;

public final /* synthetic */ class ReactInstanceManager$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ ReactInstanceManager f$0;
    public final /* synthetic */ ReactInstanceManager.ReactContextInitParams f$1;

    public /* synthetic */ ReactInstanceManager$$ExternalSyntheticLambda1(ReactInstanceManager reactInstanceManager, ReactInstanceManager.ReactContextInitParams reactContextInitParams) {
        this.f$0 = reactInstanceManager;
        this.f$1 = reactContextInitParams;
    }

    public final void run() {
        this.f$0.lambda$runCreateReactContextOnNewThread$2(this.f$1);
    }
}
