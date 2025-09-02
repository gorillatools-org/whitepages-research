package com.facebook.react.modules.core;

public final /* synthetic */ class JavaTimerManager$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ JavaTimerManager f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ JavaTimerManager$$ExternalSyntheticLambda2(JavaTimerManager javaTimerManager, boolean z) {
        this.f$0 = javaTimerManager;
        this.f$1 = z;
    }

    public final void run() {
        JavaTimerManager.setSendIdleEvents$lambda$7(this.f$0, this.f$1);
    }
}
