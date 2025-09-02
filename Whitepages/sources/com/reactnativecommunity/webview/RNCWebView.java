package com.reactnativecommunity.webview;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.net.Uri;
import android.text.TextUtils;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.webkit.JavaScriptReplyProxy;
import androidx.webkit.WebMessageCompat;
import androidx.webkit.WebViewCompat;
import androidx.webkit.WebViewFeature;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.ContentSizeChangeEvent;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.views.scroll.OnScrollDispatchHelper;
import com.facebook.react.views.scroll.ScrollEvent;
import com.facebook.react.views.scroll.ScrollEventType;
import com.google.firebase.messaging.Constants;
import com.reactnativecommunity.webview.events.TopCustomMenuSelectionEvent;
import com.reactnativecommunity.webview.events.TopMessageEvent;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class RNCWebView extends WebView implements LifecycleEventListener {
    protected WebViewCompat.WebMessageListener bridgeListener = null;
    protected RNCWebViewBridge fallbackBridge;
    protected boolean hasScrollEvent = false;
    protected String injectedJS;
    protected String injectedJSBeforeContentLoaded;
    protected boolean injectedJavaScriptBeforeContentLoadedForMainFrameOnly = true;
    protected boolean injectedJavaScriptForMainFrameOnly = true;
    protected String injectedJavaScriptObject = null;
    protected RNCWebViewMessagingModule mMessagingJSModule = ((RNCWebViewMessagingModule) ((ThemedReactContext) getContext()).getReactApplicationContext().getJSModule(RNCWebViewMessagingModule.class));
    private OnScrollDispatchHelper mOnScrollDispatchHelper;
    protected RNCWebViewClient mRNCWebViewClient;
    WebChromeClient mWebChromeClient;
    protected List menuCustomItems;
    protected boolean messagingEnabled = false;
    protected String messagingModuleName;
    protected boolean nestedScrollEnabled = false;
    protected ProgressChangedFilter progressChangedFilter = new ProgressChangedFilter();
    protected boolean sendContentSizeChangeEvents = false;

    public void onHostPause() {
    }

    public void onHostResume() {
    }

    public RNCWebView(ThemedReactContext themedReactContext) {
        super(themedReactContext);
    }

    public void setIgnoreErrFailedForThisURL(String str) {
        this.mRNCWebViewClient.setIgnoreErrFailedForThisURL(str);
    }

    public void setBasicAuthCredential(RNCBasicAuthCredential rNCBasicAuthCredential) {
        this.mRNCWebViewClient.setBasicAuthCredential(rNCBasicAuthCredential);
    }

    public void setSendContentSizeChangeEvents(boolean z) {
        this.sendContentSizeChangeEvents = z;
    }

    public void setHasScrollEvent(boolean z) {
        this.hasScrollEvent = z;
    }

    public void setNestedScrollEnabled(boolean z) {
        this.nestedScrollEnabled = z;
    }

    public void onHostDestroy() {
        cleanupCallbacksAndDestroy();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.nestedScrollEnabled) {
            requestDisallowInterceptTouchEvent(true);
        }
        return super.onTouchEvent(motionEvent);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.sendContentSizeChangeEvents) {
            dispatchEvent(this, new ContentSizeChangeEvent(RNCWebViewWrapper.getReactTagFromWebView(this), i, i2));
        }
    }

    public void setMenuCustomItems(List<Map<String, String>> list) {
        this.menuCustomItems = list;
    }

    public ActionMode startActionMode(final ActionMode.Callback callback, int i) {
        if (this.menuCustomItems == null) {
            return super.startActionMode(callback, i);
        }
        return super.startActionMode(new ActionMode.Callback2() {
            public void onDestroyActionMode(ActionMode actionMode) {
            }

            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                for (int i = 0; i < RNCWebView.this.menuCustomItems.size(); i++) {
                    menu.add(0, i, i, (CharSequence) ((Map) RNCWebView.this.menuCustomItems.get(i)).get(Constants.ScionAnalytics.PARAM_LABEL));
                }
                return true;
            }

            public boolean onActionItemClicked(final ActionMode actionMode, final MenuItem menuItem) {
                final WritableMap createMap = Arguments.createMap();
                RNCWebView.this.evaluateJavascript("(function(){return {selection: window.getSelection().toString()} })()", new ValueCallback() {
                    public void onReceiveValue(String str) {
                        String str2;
                        Map map = (Map) RNCWebView.this.menuCustomItems.get(menuItem.getItemId());
                        createMap.putString(Constants.ScionAnalytics.PARAM_LABEL, (String) map.get(Constants.ScionAnalytics.PARAM_LABEL));
                        createMap.putString("key", (String) map.get("key"));
                        try {
                            str2 = new JSONObject(str).getString("selection");
                        } catch (JSONException unused) {
                            str2 = "";
                        }
                        createMap.putString("selectedText", str2);
                        RNCWebView rNCWebView = RNCWebView.this;
                        rNCWebView.dispatchEvent(rNCWebView, new TopCustomMenuSelectionEvent(RNCWebViewWrapper.getReactTagFromWebView(RNCWebView.this), createMap));
                        actionMode.finish();
                    }
                });
                return true;
            }

            public void onGetContentRect(ActionMode actionMode, View view, Rect rect) {
                ActionMode.Callback callback = callback;
                if (callback instanceof ActionMode.Callback2) {
                    ((ActionMode.Callback2) callback).onGetContentRect(actionMode, view, rect);
                } else {
                    super.onGetContentRect(actionMode, view, rect);
                }
            }
        }, i);
    }

    public void setWebViewClient(WebViewClient webViewClient) {
        super.setWebViewClient(webViewClient);
        if (webViewClient instanceof RNCWebViewClient) {
            RNCWebViewClient rNCWebViewClient = (RNCWebViewClient) webViewClient;
            this.mRNCWebViewClient = rNCWebViewClient;
            rNCWebViewClient.setProgressChangedFilter(this.progressChangedFilter);
        }
    }

    public void setWebChromeClient(WebChromeClient webChromeClient) {
        this.mWebChromeClient = webChromeClient;
        super.setWebChromeClient(webChromeClient);
        if (webChromeClient instanceof RNCWebChromeClient) {
            ((RNCWebChromeClient) webChromeClient).setProgressChangedFilter(this.progressChangedFilter);
        }
    }

    public WebChromeClient getWebChromeClient() {
        return this.mWebChromeClient;
    }

    public RNCWebViewClient getRNCWebViewClient() {
        return this.mRNCWebViewClient;
    }

    public boolean getMessagingEnabled() {
        return this.messagingEnabled;
    }

    /* access modifiers changed from: protected */
    public void createRNCWebViewBridge(RNCWebView rNCWebView) {
        if (WebViewFeature.isFeatureSupported("WEB_MESSAGE_LISTENER")) {
            if (this.bridgeListener == null) {
                this.bridgeListener = new WebViewCompat.WebMessageListener() {
                    public void onPostMessage(WebView webView, WebMessageCompat webMessageCompat, Uri uri, boolean z, JavaScriptReplyProxy javaScriptReplyProxy) {
                        RNCWebView.this.onMessage(webMessageCompat.getData(), uri.toString());
                    }
                };
                WebViewCompat.addWebMessageListener(rNCWebView, "ReactNativeWebView", RNCWebView$$ExternalSyntheticBackport1.m(new Object[]{"*"}), this.bridgeListener);
            }
        } else if (this.fallbackBridge == null) {
            RNCWebViewBridge rNCWebViewBridge = new RNCWebViewBridge(rNCWebView);
            this.fallbackBridge = rNCWebViewBridge;
            addJavascriptInterface(rNCWebViewBridge, "ReactNativeWebView");
        }
        injectJavascriptObject();
    }

    private void injectJavascriptObject() {
        String str;
        if (getSettings().getJavaScriptEnabled()) {
            StringBuilder sb = new StringBuilder();
            sb.append("(function(){\n    window.ReactNativeWebView = window.ReactNativeWebView || {};\n    window.ReactNativeWebView.injectedObjectJson = function () { return ");
            if (this.injectedJavaScriptObject == null) {
                str = null;
            } else {
                str = "`" + this.injectedJavaScriptObject + "`";
            }
            sb.append(str);
            sb.append("; };\n})();");
            evaluateJavascriptWithFallback(sb.toString());
        }
    }

    @SuppressLint({"AddJavascriptInterface"})
    public void setMessagingEnabled(boolean z) {
        if (this.messagingEnabled != z) {
            this.messagingEnabled = z;
            if (z) {
                createRNCWebViewBridge(this);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void evaluateJavascriptWithFallback(String str) {
        evaluateJavascript(str, (ValueCallback) null);
    }

    public void callInjectedJavaScript() {
        String str;
        if (getSettings().getJavaScriptEnabled() && (str = this.injectedJS) != null && !TextUtils.isEmpty(str)) {
            evaluateJavascriptWithFallback("(function() {\n" + this.injectedJS + ";\n})();");
            injectJavascriptObject();
        }
    }

    public void callInjectedJavaScriptBeforeContentLoaded() {
        String str;
        if (getSettings().getJavaScriptEnabled() && (str = this.injectedJSBeforeContentLoaded) != null && !TextUtils.isEmpty(str)) {
            evaluateJavascriptWithFallback("(function() {\n" + this.injectedJSBeforeContentLoaded + ";\n})();");
            injectJavascriptObject();
        }
    }

    public void setInjectedJavaScriptObject(String str) {
        this.injectedJavaScriptObject = str;
        injectJavascriptObject();
    }

    public void onMessage(final String str, final String str2) {
        getThemedReactContext();
        if (this.mRNCWebViewClient != null) {
            post(new Runnable() {
                public void run() {
                    RNCWebViewClient rNCWebViewClient = RNCWebView.this.mRNCWebViewClient;
                    if (rNCWebViewClient != null) {
                        WritableMap createWebViewEvent = rNCWebViewClient.createWebViewEvent(this, str2);
                        createWebViewEvent.putString(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, str);
                        RNCWebView rNCWebView = RNCWebView.this;
                        if (rNCWebView.mMessagingJSModule != null) {
                            rNCWebView.dispatchDirectMessage(createWebViewEvent);
                        } else {
                            rNCWebView.dispatchEvent(this, new TopMessageEvent(RNCWebViewWrapper.getReactTagFromWebView(this), createWebViewEvent));
                        }
                    }
                }
            });
            return;
        }
        WritableMap createMap = Arguments.createMap();
        createMap.putString(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, str);
        if (this.mMessagingJSModule != null) {
            dispatchDirectMessage(createMap);
        } else {
            dispatchEvent(this, new TopMessageEvent(RNCWebViewWrapper.getReactTagFromWebView(this), createMap));
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDirectMessage(WritableMap writableMap) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putMap("nativeEvent", writableMap);
        writableNativeMap.putString("messagingModuleName", this.messagingModuleName);
        this.mMessagingJSModule.onMessage(writableNativeMap);
    }

    /* access modifiers changed from: protected */
    public boolean dispatchDirectShouldStartLoadWithRequest(WritableMap writableMap) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putMap("nativeEvent", writableMap);
        writableNativeMap.putString("messagingModuleName", this.messagingModuleName);
        this.mMessagingJSModule.onShouldStartLoadWithRequest(writableNativeMap);
        return true;
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (this.hasScrollEvent) {
            if (this.mOnScrollDispatchHelper == null) {
                this.mOnScrollDispatchHelper = new OnScrollDispatchHelper();
            }
            if (this.mOnScrollDispatchHelper.onScrollChanged(i, i2)) {
                dispatchEvent(this, ScrollEvent.obtain(RNCWebViewWrapper.getReactTagFromWebView(this), ScrollEventType.SCROLL, (float) i, (float) i2, this.mOnScrollDispatchHelper.getXFlingVelocity(), this.mOnScrollDispatchHelper.getYFlingVelocity(), computeHorizontalScrollRange(), computeVerticalScrollRange(), getWidth(), getHeight()));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchEvent(WebView webView, Event event) {
        UIManagerHelper.getEventDispatcherForReactTag(getThemedReactContext(), RNCWebViewWrapper.getReactTagFromWebView(webView)).dispatchEvent(event);
    }

    /* access modifiers changed from: protected */
    public void cleanupCallbacksAndDestroy() {
        setWebViewClient((WebViewClient) null);
        destroy();
    }

    public void destroy() {
        WebChromeClient webChromeClient = this.mWebChromeClient;
        if (webChromeClient != null) {
            webChromeClient.onHideCustomView();
        }
        super.destroy();
    }

    public ThemedReactContext getThemedReactContext() {
        return (ThemedReactContext) getContext();
    }

    public ReactApplicationContext getReactApplicationContext() {
        return getThemedReactContext().getReactApplicationContext();
    }

    protected class RNCWebViewBridge {
        private String TAG = "RNCWebViewBridge";
        RNCWebView mWebView;

        RNCWebViewBridge(RNCWebView rNCWebView) {
            this.mWebView = rNCWebView;
        }

        @JavascriptInterface
        public void postMessage(String str) {
            if (this.mWebView.getMessagingEnabled()) {
                RNCWebView rNCWebView = this.mWebView;
                rNCWebView.onMessage(str, rNCWebView.getUrl());
                return;
            }
            FLog.w(this.TAG, "ReactNativeWebView.postMessage method was called but messaging is disabled. Pass an onMessage handler to the WebView.");
        }
    }

    protected static class ProgressChangedFilter {
        private boolean waitingForCommandLoadUrl = false;

        protected ProgressChangedFilter() {
        }

        public void setWaitingForCommandLoadUrl(boolean z) {
            this.waitingForCommandLoadUrl = z;
        }

        public boolean isWaitingForCommandLoadUrl() {
            return this.waitingForCommandLoadUrl;
        }
    }
}
