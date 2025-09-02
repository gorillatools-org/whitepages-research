package com.google.firebase.crashlytics.internal.common;

public final /* synthetic */ class CrashlyticsCore$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ CrashlyticsCore f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ CrashlyticsCore$$ExternalSyntheticLambda5(CrashlyticsCore crashlyticsCore, String str, String str2) {
        this.f$0 = crashlyticsCore;
        this.f$1 = str;
        this.f$2 = str2;
    }

    public final void run() {
        this.f$0.lambda$setInternalKey$7(this.f$1, this.f$2);
    }
}
