package com.facebook.login;

import android.os.Bundle;
import com.facebook.FacebookException;
import com.facebook.internal.WebDialog;
import com.facebook.login.LoginClient;

public final class WebViewLoginMethodHandler$tryAuthorize$listener$1 implements WebDialog.OnCompleteListener {
    final /* synthetic */ LoginClient.Request $request;
    final /* synthetic */ WebViewLoginMethodHandler this$0;

    WebViewLoginMethodHandler$tryAuthorize$listener$1(WebViewLoginMethodHandler webViewLoginMethodHandler, LoginClient.Request request) {
        this.this$0 = webViewLoginMethodHandler;
        this.$request = request;
    }

    public void onComplete(Bundle bundle, FacebookException facebookException) {
        this.this$0.onWebDialogComplete(this.$request, bundle, facebookException);
    }
}
