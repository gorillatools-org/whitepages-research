package com.google.firebase.crashlytics.internal.common;

public final /* synthetic */ class CrashlyticsCore$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ CrashlyticsCore f$0;
    public final /* synthetic */ Throwable f$1;

    public /* synthetic */ CrashlyticsCore$$ExternalSyntheticLambda2(CrashlyticsCore crashlyticsCore, Throwable th) {
        this.f$0 = crashlyticsCore;
        this.f$1 = th;
    }

    public final void run() {
        this.f$0.lambda$logFatalException$8(this.f$1);
    }
}
