package com.google.firebase.crashlytics.internal.common;

public final /* synthetic */ class CrashlyticsCore$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ CrashlyticsCore f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ CrashlyticsCore$$ExternalSyntheticLambda9(CrashlyticsCore crashlyticsCore, long j, String str) {
        this.f$0 = crashlyticsCore;
        this.f$1 = j;
        this.f$2 = str;
    }

    public final void run() {
        this.f$0.lambda$log$3(this.f$1, this.f$2);
    }
}
