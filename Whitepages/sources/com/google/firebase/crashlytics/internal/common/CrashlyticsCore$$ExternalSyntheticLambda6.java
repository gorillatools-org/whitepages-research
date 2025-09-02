package com.google.firebase.crashlytics.internal.common;

import java.util.Map;

public final /* synthetic */ class CrashlyticsCore$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ CrashlyticsCore f$0;
    public final /* synthetic */ Throwable f$1;
    public final /* synthetic */ Map f$2;

    public /* synthetic */ CrashlyticsCore$$ExternalSyntheticLambda6(CrashlyticsCore crashlyticsCore, Throwable th, Map map) {
        this.f$0 = crashlyticsCore;
        this.f$1 = th;
        this.f$2 = map;
    }

    public final void run() {
        this.f$0.lambda$logException$1(this.f$1, this.f$2);
    }
}
