package com.facebook.react;

import com.facebook.react.bridge.ReactApplicationContext;

public final /* synthetic */ class ReactInstanceManager$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ ReactInstanceManager f$0;
    public final /* synthetic */ ReactInstanceEventListener[] f$1;
    public final /* synthetic */ ReactApplicationContext f$2;

    public /* synthetic */ ReactInstanceManager$$ExternalSyntheticLambda4(ReactInstanceManager reactInstanceManager, ReactInstanceEventListener[] reactInstanceEventListenerArr, ReactApplicationContext reactApplicationContext) {
        this.f$0 = reactInstanceManager;
        this.f$1 = reactInstanceEventListenerArr;
        this.f$2 = reactApplicationContext;
    }

    public final void run() {
        this.f$0.lambda$setupReactContext$3(this.f$1, this.f$2);
    }
}
