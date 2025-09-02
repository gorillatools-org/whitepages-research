package com.facebook.react.modules.debug;

public final /* synthetic */ class FpsDebugFrameCallback$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ FpsDebugFrameCallback f$0;

    public /* synthetic */ FpsDebugFrameCallback$$ExternalSyntheticLambda1(FpsDebugFrameCallback fpsDebugFrameCallback) {
        this.f$0 = fpsDebugFrameCallback;
    }

    public final void run() {
        FpsDebugFrameCallback.stop$lambda$1(this.f$0);
    }
}
