package com.facebook.appevents;

import android.content.Context;
import com.facebook.appevents.AppEventsLoggerImpl;

public final /* synthetic */ class AppEventsLoggerImpl$Companion$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ Context f$0;
    public final /* synthetic */ AppEventsLoggerImpl f$1;

    public /* synthetic */ AppEventsLoggerImpl$Companion$$ExternalSyntheticLambda1(Context context, AppEventsLoggerImpl appEventsLoggerImpl) {
        this.f$0 = context;
        this.f$1 = appEventsLoggerImpl;
    }

    public final void run() {
        AppEventsLoggerImpl.Companion.initializeLib$lambda$4(this.f$0, this.f$1);
    }
}
