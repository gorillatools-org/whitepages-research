package com.reactnativecommunity.webview;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.HttpAuthHandler;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.UIManagerHelper;
import com.reactnativecommunity.webview.RNCWebView;
import com.reactnativecommunity.webview.events.TopHttpErrorEvent;
import com.reactnativecommunity.webview.events.TopLoadingErrorEvent;
import com.reactnativecommunity.webview.events.TopLoadingFinishEvent;
import com.reactnativecommunity.webview.events.TopLoadingStartEvent;
import com.reactnativecommunity.webview.events.TopRenderProcessGoneEvent;

public class RNCWebViewClient extends WebViewClient {
    private static String TAG = "RNCWebViewClient";
    protected RNCBasicAuthCredential basicAuthCredential = null;
    protected String ignoreErrFailedForThisURL = null;
    protected boolean mLastLoadFailed = false;
    protected RNCWebView.ProgressChangedFilter progressChangedFilter = null;

    public void setIgnoreErrFailedForThisURL(String str) {
        this.ignoreErrFailedForThisURL = str;
    }

    public void setBasicAuthCredential(RNCBasicAuthCredential rNCBasicAuthCredential) {
        this.basicAuthCredential = rNCBasicAuthCredential;
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        if (CookieManager.getInstance().getCookie(str) != null) {
            CookieManager.getInstance().flush();
        }
        if (!this.mLastLoadFailed) {
            ((RNCWebView) webView).callInjectedJavaScript();
            emitFinishEvent(webView, str);
        }
    }

    public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
        super.doUpdateVisitedHistory(webView, str, z);
        ((RNCWebView) webView).dispatchEvent(webView, new TopLoadingStartEvent(RNCWebViewWrapper.getReactTagFromWebView(webView), createWebViewEvent(webView, str)));
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        this.mLastLoadFailed = false;
        ((RNCWebView) webView).callInjectedJavaScriptBeforeContentLoaded();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006d, code lost:
        if (r1.get() != com.reactnativecommunity.webview.RNCWebViewModuleImpl.ShouldOverrideUrlLoadingLock.ShouldOverrideCallbackState.SHOULD_OVERRIDE) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0070, code lost:
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0071, code lost:
        com.reactnativecommunity.webview.RNCWebViewModuleImpl.shouldOverrideUrlLoadingLock.removeLock(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0076, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean shouldOverrideUrlLoading(android.webkit.WebView r11, java.lang.String r12) {
        /*
            r10 = this;
            r0 = r11
            com.reactnativecommunity.webview.RNCWebView r0 = (com.reactnativecommunity.webview.RNCWebView) r0
            com.facebook.react.bridge.ReactApplicationContext r1 = r0.getReactApplicationContext()
            com.facebook.react.bridge.JavaScriptContextHolder r1 = r1.getJavaScriptContextHolder()
            long r1 = r1.get()
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            r2 = 1
            if (r1 != 0) goto L_0x0017
            goto L_0x0087
        L_0x0017:
            com.reactnativecommunity.webview.RNCWebViewMessagingModule r1 = r0.mMessagingJSModule
            if (r1 == 0) goto L_0x0087
            com.reactnativecommunity.webview.RNCWebViewModuleImpl$ShouldOverrideUrlLoadingLock r1 = com.reactnativecommunity.webview.RNCWebViewModuleImpl.shouldOverrideUrlLoadingLock
            androidx.core.util.Pair r1 = r1.getNewLock()
            java.lang.Object r3 = r1.first
            java.lang.Double r3 = (java.lang.Double) r3
            double r4 = r3.doubleValue()
            java.lang.Object r1 = r1.second
            java.util.concurrent.atomic.AtomicReference r1 = (java.util.concurrent.atomic.AtomicReference) r1
            com.facebook.react.bridge.WritableMap r11 = r10.createWebViewEvent(r11, r12)
            java.lang.String r12 = "lockIdentifier"
            r11.putDouble(r12, r4)
            r0.dispatchDirectShouldStartLoadWithRequest(r11)
            r11 = 0
            monitor-enter(r1)     // Catch:{ InterruptedException -> 0x0079 }
            long r4 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x0060 }
        L_0x003f:
            java.lang.Object r12 = r1.get()     // Catch:{ all -> 0x0060 }
            com.reactnativecommunity.webview.RNCWebViewModuleImpl$ShouldOverrideUrlLoadingLock$ShouldOverrideCallbackState r0 = com.reactnativecommunity.webview.RNCWebViewModuleImpl.ShouldOverrideUrlLoadingLock.ShouldOverrideCallbackState.UNDECIDED     // Catch:{ all -> 0x0060 }
            if (r12 != r0) goto L_0x0066
            long r6 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x0060 }
            long r6 = r6 - r4
            r8 = 250(0xfa, double:1.235E-321)
            int r12 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r12 <= 0) goto L_0x0062
            java.lang.String r12 = TAG     // Catch:{ all -> 0x0060 }
            java.lang.String r0 = "Did not receive response to shouldOverrideUrlLoading in time, defaulting to allow loading."
            com.facebook.common.logging.FLog.w((java.lang.String) r12, (java.lang.String) r0)     // Catch:{ all -> 0x0060 }
            com.reactnativecommunity.webview.RNCWebViewModuleImpl$ShouldOverrideUrlLoadingLock r12 = com.reactnativecommunity.webview.RNCWebViewModuleImpl.shouldOverrideUrlLoadingLock     // Catch:{ all -> 0x0060 }
            r12.removeLock(r3)     // Catch:{ all -> 0x0060 }
            monitor-exit(r1)     // Catch:{ all -> 0x0060 }
            return r11
        L_0x0060:
            r12 = move-exception
            goto L_0x0077
        L_0x0062:
            r1.wait(r8)     // Catch:{ all -> 0x0060 }
            goto L_0x003f
        L_0x0066:
            monitor-exit(r1)     // Catch:{ all -> 0x0060 }
            java.lang.Object r12 = r1.get()
            com.reactnativecommunity.webview.RNCWebViewModuleImpl$ShouldOverrideUrlLoadingLock$ShouldOverrideCallbackState r0 = com.reactnativecommunity.webview.RNCWebViewModuleImpl.ShouldOverrideUrlLoadingLock.ShouldOverrideCallbackState.SHOULD_OVERRIDE
            if (r12 != r0) goto L_0x0070
            goto L_0x0071
        L_0x0070:
            r2 = r11
        L_0x0071:
            com.reactnativecommunity.webview.RNCWebViewModuleImpl$ShouldOverrideUrlLoadingLock r11 = com.reactnativecommunity.webview.RNCWebViewModuleImpl.shouldOverrideUrlLoadingLock
            r11.removeLock(r3)
            return r2
        L_0x0077:
            monitor-exit(r1)     // Catch:{ all -> 0x0060 }
            throw r12     // Catch:{ InterruptedException -> 0x0079 }
        L_0x0079:
            r12 = move-exception
            java.lang.String r0 = TAG
            java.lang.String r1 = "shouldOverrideUrlLoading was interrupted while waiting for result."
            com.facebook.common.logging.FLog.e((java.lang.String) r0, (java.lang.String) r1, (java.lang.Throwable) r12)
            com.reactnativecommunity.webview.RNCWebViewModuleImpl$ShouldOverrideUrlLoadingLock r12 = com.reactnativecommunity.webview.RNCWebViewModuleImpl.shouldOverrideUrlLoadingLock
            r12.removeLock(r3)
            return r11
        L_0x0087:
            java.lang.String r0 = TAG
            java.lang.String r1 = "Couldn't use blocking synchronous call for onShouldStartLoadWithRequest due to debugging or missing Catalyst instance, falling back to old event-and-load."
            com.facebook.common.logging.FLog.w((java.lang.String) r0, (java.lang.String) r1)
            com.reactnativecommunity.webview.RNCWebView$ProgressChangedFilter r0 = r10.progressChangedFilter
            r0.setWaitingForCommandLoadUrl(r2)
            int r0 = com.reactnativecommunity.webview.RNCWebViewWrapper.getReactTagFromWebView(r11)
            android.content.Context r1 = r11.getContext()
            com.facebook.react.bridge.ReactContext r1 = (com.facebook.react.bridge.ReactContext) r1
            com.facebook.react.uimanager.events.EventDispatcher r1 = com.facebook.react.uimanager.UIManagerHelper.getEventDispatcherForReactTag(r1, r0)
            com.reactnativecommunity.webview.events.TopShouldStartLoadWithRequestEvent r3 = new com.reactnativecommunity.webview.events.TopShouldStartLoadWithRequestEvent
            com.facebook.react.bridge.WritableMap r11 = r10.createWebViewEvent(r11, r12)
            r3.<init>(r0, r11)
            r1.dispatchEvent(r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reactnativecommunity.webview.RNCWebViewClient.shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String):boolean");
    }

    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        return shouldOverrideUrlLoading(webView, webResourceRequest.getUrl().toString());
    }

    public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
        RNCBasicAuthCredential rNCBasicAuthCredential = this.basicAuthCredential;
        if (rNCBasicAuthCredential != null) {
            httpAuthHandler.proceed(rNCBasicAuthCredential.username, rNCBasicAuthCredential.password);
        } else {
            super.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
        }
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        String str;
        String url = webView.getUrl();
        String url2 = sslError.getUrl();
        sslErrorHandler.cancel();
        if (!url.equalsIgnoreCase(url2)) {
            String str2 = TAG;
            Log.w(str2, "Resource blocked from loading due to SSL error. Blocked URL: " + url2);
            return;
        }
        int primaryError = sslError.getPrimaryError();
        if (primaryError == 0) {
            str = "The certificate is not yet valid";
        } else if (primaryError == 1) {
            str = "The certificate has expired";
        } else if (primaryError == 2) {
            str = "Hostname mismatch";
        } else if (primaryError == 3) {
            str = "The certificate authority is not trusted";
        } else if (primaryError == 4) {
            str = "The date of the certificate is invalid";
        } else if (primaryError != 5) {
            str = "Unknown SSL Error";
        } else {
            str = "A generic error occurred";
        }
        onReceivedError(webView, primaryError, "SSL error: " + str, url2);
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        String str3 = this.ignoreErrFailedForThisURL;
        if (str3 == null || !str2.equals(str3) || i != -1 || !str.equals("net::ERR_FAILED")) {
            super.onReceivedError(webView, i, str, str2);
            this.mLastLoadFailed = true;
            emitFinishEvent(webView, str2);
            WritableMap createWebViewEvent = createWebViewEvent(webView, str2);
            createWebViewEvent.putDouble("code", (double) i);
            createWebViewEvent.putString("description", str);
            int reactTagFromWebView = RNCWebViewWrapper.getReactTagFromWebView(webView);
            UIManagerHelper.getEventDispatcherForReactTag((ReactContext) webView.getContext(), reactTagFromWebView).dispatchEvent(new TopLoadingErrorEvent(reactTagFromWebView, createWebViewEvent));
            return;
        }
        setIgnoreErrFailedForThisURL((String) null);
    }

    public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
        super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
        if (webResourceRequest.isForMainFrame()) {
            WritableMap createWebViewEvent = createWebViewEvent(webView, webResourceRequest.getUrl().toString());
            createWebViewEvent.putInt("statusCode", webResourceResponse.getStatusCode());
            createWebViewEvent.putString("description", webResourceResponse.getReasonPhrase());
            int reactTagFromWebView = RNCWebViewWrapper.getReactTagFromWebView(webView);
            UIManagerHelper.getEventDispatcherForReactTag((ReactContext) webView.getContext(), reactTagFromWebView).dispatchEvent(new TopHttpErrorEvent(reactTagFromWebView, createWebViewEvent));
        }
    }

    public boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
        super.onRenderProcessGone(webView, renderProcessGoneDetail);
        if (renderProcessGoneDetail.didCrash()) {
            Log.e(TAG, "The WebView rendering process crashed.");
        } else {
            Log.w(TAG, "The WebView rendering process was killed by the system.");
        }
        if (webView == null) {
            return true;
        }
        WritableMap createWebViewEvent = createWebViewEvent(webView, webView.getUrl());
        createWebViewEvent.putBoolean("didCrash", renderProcessGoneDetail.didCrash());
        int reactTagFromWebView = RNCWebViewWrapper.getReactTagFromWebView(webView);
        UIManagerHelper.getEventDispatcherForReactTag((ReactContext) webView.getContext(), reactTagFromWebView).dispatchEvent(new TopRenderProcessGoneEvent(reactTagFromWebView, createWebViewEvent));
        return true;
    }

    /* access modifiers changed from: protected */
    public void emitFinishEvent(WebView webView, String str) {
        int reactTagFromWebView = RNCWebViewWrapper.getReactTagFromWebView(webView);
        UIManagerHelper.getEventDispatcherForReactTag((ReactContext) webView.getContext(), reactTagFromWebView).dispatchEvent(new TopLoadingFinishEvent(reactTagFromWebView, createWebViewEvent(webView, str)));
    }

    /* access modifiers changed from: protected */
    public WritableMap createWebViewEvent(WebView webView, String str) {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("target", (double) RNCWebViewWrapper.getReactTagFromWebView(webView));
        createMap.putString("url", str);
        createMap.putBoolean("loading", !this.mLastLoadFailed && webView.getProgress() != 100);
        createMap.putString("title", webView.getTitle());
        createMap.putBoolean("canGoBack", webView.canGoBack());
        createMap.putBoolean("canGoForward", webView.canGoForward());
        return createMap;
    }

    public void setProgressChangedFilter(RNCWebView.ProgressChangedFilter progressChangedFilter2) {
        this.progressChangedFilter = progressChangedFilter2;
    }
}
