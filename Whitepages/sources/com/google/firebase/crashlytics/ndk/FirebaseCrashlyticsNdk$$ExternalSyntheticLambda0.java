package com.google.firebase.crashlytics.ndk;

import com.google.firebase.crashlytics.internal.model.StaticSessionData;
import com.google.firebase.crashlytics.ndk.FirebaseCrashlyticsNdk;

public final /* synthetic */ class FirebaseCrashlyticsNdk$$ExternalSyntheticLambda0 implements FirebaseCrashlyticsNdk.SignalHandlerInstaller {
    public final /* synthetic */ FirebaseCrashlyticsNdk f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ StaticSessionData f$4;

    public /* synthetic */ FirebaseCrashlyticsNdk$$ExternalSyntheticLambda0(FirebaseCrashlyticsNdk firebaseCrashlyticsNdk, String str, String str2, long j, StaticSessionData staticSessionData) {
        this.f$0 = firebaseCrashlyticsNdk;
        this.f$1 = str;
        this.f$2 = str2;
        this.f$3 = j;
        this.f$4 = staticSessionData;
    }

    public final void installHandler() {
        this.f$0.lambda$prepareNativeSession$0(this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
