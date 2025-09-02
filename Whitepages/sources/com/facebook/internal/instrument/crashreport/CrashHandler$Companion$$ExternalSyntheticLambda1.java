package com.facebook.internal.instrument.crashreport;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.internal.instrument.crashreport.CrashHandler;
import java.util.List;

public final /* synthetic */ class CrashHandler$Companion$$ExternalSyntheticLambda1 implements GraphRequest.Callback {
    public final /* synthetic */ List f$0;

    public /* synthetic */ CrashHandler$Companion$$ExternalSyntheticLambda1(List list) {
        this.f$0 = list;
    }

    public final void onCompleted(GraphResponse graphResponse) {
        CrashHandler.Companion.sendExceptionReports$lambda$5(this.f$0, graphResponse);
    }
}
