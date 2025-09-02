package com.facebook.react.modules.core;

import android.view.Choreographer;

public final /* synthetic */ class ReactChoreographer$$ExternalSyntheticLambda0 implements Choreographer.FrameCallback {
    public final /* synthetic */ ReactChoreographer f$0;

    public /* synthetic */ ReactChoreographer$$ExternalSyntheticLambda0(ReactChoreographer reactChoreographer) {
        this.f$0 = reactChoreographer;
    }

    public final void doFrame(long j) {
        ReactChoreographer.frameCallback$lambda$1(this.f$0, j);
    }
}
