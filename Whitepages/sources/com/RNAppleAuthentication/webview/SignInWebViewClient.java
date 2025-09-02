package com.RNAppleAuthentication.webview;

import android.os.Handler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.RNAppleAuthentication.SignInWithAppleService;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class SignInWebViewClient extends WebViewClient {
    private final SignInWithAppleService.AuthenticationAttempt attempt;
    private final String javascriptToInject;
    private Handler mainHandler = new Handler();

    public SignInWebViewClient(SignInWithAppleService.AuthenticationAttempt authenticationAttempt, String str) {
        Intrinsics.checkNotNullParameter(authenticationAttempt, "attempt");
        Intrinsics.checkNotNullParameter(str, "javascriptToInject");
        this.attempt = authenticationAttempt;
        this.javascriptToInject = str;
    }

    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        if (Intrinsics.areEqual((Object) webResourceRequest != null ? webResourceRequest.getMethod() : null, (Object) "POST")) {
            String uri = webResourceRequest.getUrl().toString();
            Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
            if (StringsKt.contains$default((CharSequence) uri, (CharSequence) this.attempt.getRedirectUri(), false, 2, (Object) null)) {
                try {
                    Thread.currentThread().interrupt();
                } catch (Exception unused) {
                }
                this.mainHandler.post(new SignInWebViewClient$$ExternalSyntheticLambda0(webView, this));
            }
        }
        return super.shouldInterceptRequest(webView, webResourceRequest);
    }

    /* access modifiers changed from: private */
    public static final void shouldInterceptRequest$lambda$0(WebView webView, SignInWebViewClient signInWebViewClient) {
        if (webView != null) {
            webView.stopLoading();
        }
        if (webView != null) {
            webView.loadUrl("javascript:" + signInWebViewClient.javascriptToInject);
        }
    }
}
