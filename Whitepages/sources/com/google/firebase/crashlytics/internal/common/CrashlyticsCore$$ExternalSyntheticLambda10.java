package com.google.firebase.crashlytics.internal.common;

import java.util.Map;

public final /* synthetic */ class CrashlyticsCore$$ExternalSyntheticLambda10 implements Runnable {
    public final /* synthetic */ CrashlyticsCore f$0;
    public final /* synthetic */ Map f$1;

    public /* synthetic */ CrashlyticsCore$$ExternalSyntheticLambda10(CrashlyticsCore crashlyticsCore, Map map) {
        this.f$0 = crashlyticsCore;
        this.f$1 = map;
    }

    public final void run() {
        this.f$0.lambda$setCustomKeys$6(this.f$1);
    }
}
