package com.facebook.login;

import android.content.Context;
import android.os.Bundle;
import com.facebook.internal.PlatformServiceClient;
import com.facebook.login.LoginClient;
import com.google.firebase.messaging.Constants;
import kotlin.jvm.internal.Intrinsics;

public final class GetTokenClient extends PlatformServiceClient {
    /* access modifiers changed from: protected */
    public void populateRequestBundle(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GetTokenClient(Context context, LoginClient.Request request) {
        super(context, 65536, 65537, 20121101, request.getApplicationId(), request.getNonce());
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(request, "request");
    }
}
