package com.facebook.appevents.internal;

import android.content.Context;

public final /* synthetic */ class ActivityLifecycleTracker$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ long f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ Context f$2;

    public /* synthetic */ ActivityLifecycleTracker$$ExternalSyntheticLambda2(long j, String str, Context context) {
        this.f$0 = j;
        this.f$1 = str;
        this.f$2 = context;
    }

    public final void run() {
        ActivityLifecycleTracker.onActivityResumed$lambda$3(this.f$0, this.f$1, this.f$2);
    }
}
