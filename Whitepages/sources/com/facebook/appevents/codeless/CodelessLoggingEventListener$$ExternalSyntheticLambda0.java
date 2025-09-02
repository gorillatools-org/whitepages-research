package com.facebook.appevents.codeless;

import android.os.Bundle;

public final /* synthetic */ class CodelessLoggingEventListener$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ Bundle f$1;

    public /* synthetic */ CodelessLoggingEventListener$$ExternalSyntheticLambda0(String str, Bundle bundle) {
        this.f$0 = str;
        this.f$1 = bundle;
    }

    public final void run() {
        CodelessLoggingEventListener.logEvent$lambda$0(this.f$0, this.f$1);
    }
}
