package com.facebook.react;

import com.facebook.react.uimanager.ReactRoot;

public final /* synthetic */ class ReactInstanceManager$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ ReactRoot f$1;

    public /* synthetic */ ReactInstanceManager$$ExternalSyntheticLambda0(int i, ReactRoot reactRoot) {
        this.f$0 = i;
        this.f$1 = reactRoot;
    }

    public final void run() {
        ReactInstanceManager.lambda$attachRootViewToInstance$6(this.f$0, this.f$1);
    }
}
