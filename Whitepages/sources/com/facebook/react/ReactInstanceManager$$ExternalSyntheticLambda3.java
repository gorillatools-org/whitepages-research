package com.facebook.react;

import com.facebook.react.bridge.ReactApplicationContext;

public final /* synthetic */ class ReactInstanceManager$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ ReactInstanceManager f$0;
    public final /* synthetic */ ReactApplicationContext f$1;

    public /* synthetic */ ReactInstanceManager$$ExternalSyntheticLambda3(ReactInstanceManager reactInstanceManager, ReactApplicationContext reactApplicationContext) {
        this.f$0 = reactInstanceManager;
        this.f$1 = reactApplicationContext;
    }

    public final void run() {
        this.f$0.lambda$runCreateReactContextOnNewThread$1(this.f$1);
    }
}
