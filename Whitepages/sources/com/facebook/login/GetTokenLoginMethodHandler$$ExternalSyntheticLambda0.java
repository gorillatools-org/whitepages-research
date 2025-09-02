package com.facebook.login;

import android.os.Bundle;
import com.facebook.internal.PlatformServiceClient;
import com.facebook.login.LoginClient;

public final /* synthetic */ class GetTokenLoginMethodHandler$$ExternalSyntheticLambda0 implements PlatformServiceClient.CompletedListener {
    public final /* synthetic */ GetTokenLoginMethodHandler f$0;
    public final /* synthetic */ LoginClient.Request f$1;

    public /* synthetic */ GetTokenLoginMethodHandler$$ExternalSyntheticLambda0(GetTokenLoginMethodHandler getTokenLoginMethodHandler, LoginClient.Request request) {
        this.f$0 = getTokenLoginMethodHandler;
        this.f$1 = request;
    }

    public final void completed(Bundle bundle) {
        GetTokenLoginMethodHandler.tryAuthorize$lambda$1(this.f$0, this.f$1, bundle);
    }
}
