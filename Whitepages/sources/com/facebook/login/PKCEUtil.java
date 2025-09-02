package com.facebook.login;

import android.os.Bundle;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.HttpMethod;
import kotlin.jvm.internal.Intrinsics;

public final class PKCEUtil {
    public static final PKCEUtil INSTANCE = new PKCEUtil();

    private PKCEUtil() {
    }

    public static final GraphRequest createCodeExchangeRequest(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "authorizationCode");
        Intrinsics.checkNotNullParameter(str2, "redirectUri");
        Intrinsics.checkNotNullParameter(str3, "codeVerifier");
        Bundle bundle = new Bundle();
        bundle.putString("code", str);
        bundle.putString("client_id", FacebookSdk.getApplicationId());
        bundle.putString("redirect_uri", str2);
        bundle.putString("code_verifier", str3);
        GraphRequest newGraphPathRequest = GraphRequest.Companion.newGraphPathRequest((AccessToken) null, "oauth/access_token", (GraphRequest.Callback) null);
        newGraphPathRequest.setHttpMethod(HttpMethod.GET);
        newGraphPathRequest.setParameters(bundle);
        return newGraphPathRequest;
    }
}
