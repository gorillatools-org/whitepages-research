package com.swmansion.gesturehandler.react;

import com.swmansion.gesturehandler.core.GestureHandler;

public final /* synthetic */ class RNGestureHandlerRegistry$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ GestureHandler f$0;

    public /* synthetic */ RNGestureHandlerRegistry$$ExternalSyntheticLambda0(GestureHandler gestureHandler) {
        this.f$0 = gestureHandler;
    }

    public final void run() {
        RNGestureHandlerRegistry.detachHandler$lambda$4(this.f$0);
    }
}
