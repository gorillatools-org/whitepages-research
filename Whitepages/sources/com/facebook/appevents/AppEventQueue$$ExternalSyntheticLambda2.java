package com.facebook.appevents;

public final /* synthetic */ class AppEventQueue$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ FlushReason f$0;

    public /* synthetic */ AppEventQueue$$ExternalSyntheticLambda2(FlushReason flushReason) {
        this.f$0 = flushReason;
    }

    public final void run() {
        AppEventQueue.flush$lambda$2(this.f$0);
    }
}
