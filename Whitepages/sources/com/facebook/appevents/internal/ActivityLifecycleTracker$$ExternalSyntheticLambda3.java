package com.facebook.appevents.internal;

public final /* synthetic */ class ActivityLifecycleTracker$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ long f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ ActivityLifecycleTracker$$ExternalSyntheticLambda3(long j, String str) {
        this.f$0 = j;
        this.f$1 = str;
    }

    public final void run() {
        ActivityLifecycleTracker.onActivityPaused$lambda$7(this.f$0, this.f$1);
    }
}
