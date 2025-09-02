package com.facebook.login;

import android.os.Bundle;
import com.facebook.login.LoginClient;

public final /* synthetic */ class NativeAppLoginMethodHandler$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ NativeAppLoginMethodHandler f$0;
    public final /* synthetic */ LoginClient.Request f$1;
    public final /* synthetic */ Bundle f$2;

    public /* synthetic */ NativeAppLoginMethodHandler$$ExternalSyntheticLambda0(NativeAppLoginMethodHandler nativeAppLoginMethodHandler, LoginClient.Request request, Bundle bundle) {
        this.f$0 = nativeAppLoginMethodHandler;
        this.f$1 = request;
        this.f$2 = bundle;
    }

    public final void run() {
        NativeAppLoginMethodHandler.processSuccessResponse$lambda$0(this.f$0, this.f$1, this.f$2);
    }
}
