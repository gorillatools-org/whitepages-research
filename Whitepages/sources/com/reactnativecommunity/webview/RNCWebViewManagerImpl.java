package com.reactnativecommunity.webview;

import android.app.Activity;
import android.app.DownloadManager;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.webkit.WebSettingsCompat;
import androidx.webkit.WebViewFeature;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.build.ReactBuildConfig;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.views.scroll.ReactScrollViewHelper;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

public final class RNCWebViewManagerImpl {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final String BLANK_URL;
    private final int COMMAND_CLEAR_CACHE;
    private final int COMMAND_CLEAR_FORM_DATA;
    private final int COMMAND_CLEAR_HISTORY;
    private final int COMMAND_FOCUS;
    private final int COMMAND_GO_BACK;
    private final int COMMAND_GO_FORWARD;
    private final int COMMAND_INJECT_JAVASCRIPT;
    private final int COMMAND_LOAD_URL;
    private final int COMMAND_POST_MESSAGE;
    private final int COMMAND_RELOAD;
    private final int COMMAND_STOP_LOADING;
    private final String DEFAULT_DOWNLOADING_MESSAGE;
    private final String DEFAULT_LACK_PERMISSION_TO_DOWNLOAD_MESSAGE;
    private final String HTML_ENCODING;
    private final String HTML_MIME_TYPE;
    private final String HTTP_METHOD_POST;
    private final String TAG;
    private boolean mAllowsFullscreenVideo;
    private boolean mAllowsProtectedMedia;
    private String mDownloadingMessage;
    private boolean mHasOnOpenWindowEvent;
    private String mLackPermissionToDownloadMessage;
    private ReadableMap mPendingSource;
    private String mUserAgent;
    private String mUserAgentWithApplicationName;
    private RNCWebViewConfig mWebViewConfig;
    private final boolean newArch;

    public RNCWebViewManagerImpl() {
        this(false, 1, (DefaultConstructorMarker) null);
    }

    /* access modifiers changed from: private */
    public static final void mWebViewConfig$lambda$0(WebView webView) {
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public RNCWebViewManagerImpl(boolean z) {
        this.newArch = z;
        this.TAG = "RNCWebViewManagerImpl";
        this.mWebViewConfig = new RNCWebViewManagerImpl$$ExternalSyntheticLambda0();
        this.HTML_ENCODING = "UTF-8";
        this.HTML_MIME_TYPE = "text/html";
        this.HTTP_METHOD_POST = "POST";
        this.BLANK_URL = "about:blank";
        this.DEFAULT_DOWNLOADING_MESSAGE = "Downloading";
        this.DEFAULT_LACK_PERMISSION_TO_DOWNLOAD_MESSAGE = "Cannot download files as permission was denied. Please provide permission to write to storage, in order to download files.";
        this.COMMAND_GO_BACK = 1;
        this.COMMAND_GO_FORWARD = 2;
        this.COMMAND_RELOAD = 3;
        this.COMMAND_STOP_LOADING = 4;
        this.COMMAND_POST_MESSAGE = 5;
        this.COMMAND_INJECT_JAVASCRIPT = 6;
        this.COMMAND_LOAD_URL = 7;
        this.COMMAND_FOCUS = 8;
        this.COMMAND_CLEAR_FORM_DATA = 1000;
        this.COMMAND_CLEAR_CACHE = 1001;
        this.COMMAND_CLEAR_HISTORY = 1002;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RNCWebViewManagerImpl(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    public final RNCWebView createRNCWebViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        return new RNCWebView(themedReactContext);
    }

    public final RNCWebViewWrapper createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        return createViewInstance(themedReactContext, createRNCWebViewInstance(themedReactContext));
    }

    public final RNCWebViewWrapper createViewInstance(ThemedReactContext themedReactContext, RNCWebView rNCWebView) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        Intrinsics.checkNotNullParameter(rNCWebView, "webView");
        setupWebChromeClient(rNCWebView);
        themedReactContext.addLifecycleEventListener(rNCWebView);
        this.mWebViewConfig.configWebView(rNCWebView);
        WebSettings settings = rNCWebView.getSettings();
        Intrinsics.checkNotNullExpressionValue(settings, "getSettings(...)");
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setDomStorageEnabled(true);
        settings.setSupportMultipleWindows(true);
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);
        settings.setMixedContentMode(1);
        rNCWebView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        if (ReactBuildConfig.DEBUG) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        rNCWebView.setDownloadListener(new RNCWebViewManagerImpl$$ExternalSyntheticLambda1(rNCWebView, this));
        return new RNCWebViewWrapper(themedReactContext, rNCWebView);
    }

    /* access modifiers changed from: private */
    public static final void createViewInstance$lambda$1(RNCWebView rNCWebView, RNCWebViewManagerImpl rNCWebViewManagerImpl, String str, String str2, String str3, String str4, long j) {
        rNCWebView.setIgnoreErrFailedForThisURL(str);
        RNCWebViewModule rNCWebViewModule = (RNCWebViewModule) rNCWebView.getReactApplicationContext().getNativeModule(RNCWebViewModule.class);
        if (rNCWebViewModule != null) {
            try {
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
                String guessFileName = URLUtil.guessFileName(str, str3, str4);
                Intrinsics.checkNotNull(guessFileName);
                String replace = RNCWebViewManagerImplKt.getInvalidCharRegex().replace(guessFileName, "_");
                String str5 = "Downloading " + replace;
                try {
                    URL url = new URL(str);
                    request.addRequestHeader("Cookie", CookieManager.getInstance().getCookie(url.getProtocol() + "://" + url.getHost()));
                } catch (MalformedURLException e) {
                    Log.w(rNCWebViewManagerImpl.TAG, "Error getting cookie for DownloadManager", e);
                }
                request.addRequestHeader("User-Agent", str2);
                request.setTitle(replace);
                request.setDescription(str5);
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(1);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, replace);
                rNCWebViewModule.setDownloadRequest(request);
                if (rNCWebViewModule.grantFileDownloaderPermissions(rNCWebViewManagerImpl.getDownloadingMessageOrDefault(), rNCWebViewManagerImpl.getLackPermissionToDownloadMessageOrDefault())) {
                    rNCWebViewModule.downloadFile(rNCWebViewManagerImpl.getDownloadingMessageOrDefault());
                }
            } catch (IllegalArgumentException e2) {
                Log.w(rNCWebViewManagerImpl.TAG, "Unsupported URI, aborting download", e2);
            }
        }
    }

    private final void setupWebChromeClient(RNCWebView rNCWebView) {
        Activity currentActivity = rNCWebView.getThemedReactContext().getCurrentActivity();
        if (!this.mAllowsFullscreenVideo || currentActivity == null) {
            RNCWebChromeClient rNCWebChromeClient = (RNCWebChromeClient) rNCWebView.getWebChromeClient();
            if (rNCWebChromeClient != null) {
                rNCWebChromeClient.onHideCustomView();
            }
            RNCWebViewManagerImpl$setupWebChromeClient$1 rNCWebViewManagerImpl$setupWebChromeClient$1 = new RNCWebViewManagerImpl$setupWebChromeClient$1(rNCWebView);
            rNCWebViewManagerImpl$setupWebChromeClient$1.setAllowsProtectedMedia(this.mAllowsProtectedMedia);
            rNCWebViewManagerImpl$setupWebChromeClient$1.setHasOnOpenWindowEvent(this.mHasOnOpenWindowEvent);
            rNCWebView.setWebChromeClient(rNCWebViewManagerImpl$setupWebChromeClient$1);
            return;
        }
        RNCWebViewManagerImpl$setupWebChromeClient$webChromeClient$1 rNCWebViewManagerImpl$setupWebChromeClient$webChromeClient$1 = new RNCWebViewManagerImpl$setupWebChromeClient$webChromeClient$1(rNCWebView, currentActivity, currentActivity.getRequestedOrientation());
        rNCWebViewManagerImpl$setupWebChromeClient$webChromeClient$1.setAllowsProtectedMedia(this.mAllowsProtectedMedia);
        rNCWebViewManagerImpl$setupWebChromeClient$webChromeClient$1.setHasOnOpenWindowEvent(this.mHasOnOpenWindowEvent);
        rNCWebView.setWebChromeClient(rNCWebViewManagerImpl$setupWebChromeClient$webChromeClient$1);
    }

    public final void setUserAgent(RNCWebViewWrapper rNCWebViewWrapper, String str) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        this.mUserAgent = str;
        setUserAgentString(rNCWebViewWrapper);
    }

    public final void setApplicationNameForUserAgent(RNCWebViewWrapper rNCWebViewWrapper, String str) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        if (str != null) {
            String defaultUserAgent = WebSettings.getDefaultUserAgent(rNCWebViewWrapper.getWebView().getContext());
            this.mUserAgentWithApplicationName = defaultUserAgent + ' ' + str;
        } else {
            this.mUserAgentWithApplicationName = null;
        }
        setUserAgentString(rNCWebViewWrapper);
    }

    private final void setUserAgentString(RNCWebViewWrapper rNCWebViewWrapper) {
        RNCWebView webView = rNCWebViewWrapper.getWebView();
        if (this.mUserAgent != null) {
            webView.getSettings().setUserAgentString(this.mUserAgent);
        } else if (this.mUserAgentWithApplicationName != null) {
            webView.getSettings().setUserAgentString(this.mUserAgentWithApplicationName);
        } else {
            webView.getSettings().setUserAgentString(WebSettings.getDefaultUserAgent(webView.getContext()));
        }
    }

    public final void setBasicAuthCredential(RNCWebViewWrapper rNCWebViewWrapper, ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        rNCWebViewWrapper.getWebView().setBasicAuthCredential((readableMap == null || !readableMap.hasKey("username") || !readableMap.hasKey("password")) ? null : new RNCBasicAuthCredential(readableMap.getString("username"), readableMap.getString("password")));
    }

    public final void onAfterUpdateTransaction(RNCWebViewWrapper rNCWebViewWrapper) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        ReadableMap readableMap = this.mPendingSource;
        if (readableMap != null) {
            loadSource(rNCWebViewWrapper, readableMap);
        }
        this.mPendingSource = null;
    }

    public final void onDropViewInstance(RNCWebViewWrapper rNCWebViewWrapper) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        RNCWebView webView = rNCWebViewWrapper.getWebView();
        webView.getThemedReactContext().removeLifecycleEventListener(webView);
        webView.cleanupCallbacksAndDestroy();
        webView.mWebChromeClient = null;
    }

    public final Map getCommandsMap() {
        return MapBuilder.builder().put("goBack", Integer.valueOf(this.COMMAND_GO_BACK)).put("goForward", Integer.valueOf(this.COMMAND_GO_FORWARD)).put("reload", Integer.valueOf(this.COMMAND_RELOAD)).put("stopLoading", Integer.valueOf(this.COMMAND_STOP_LOADING)).put("postMessage", Integer.valueOf(this.COMMAND_POST_MESSAGE)).put("injectJavaScript", Integer.valueOf(this.COMMAND_INJECT_JAVASCRIPT)).put("loadUrl", Integer.valueOf(this.COMMAND_LOAD_URL)).put("requestFocus", Integer.valueOf(this.COMMAND_FOCUS)).put("clearFormData", Integer.valueOf(this.COMMAND_CLEAR_FORM_DATA)).put("clearCache", Integer.valueOf(this.COMMAND_CLEAR_CACHE)).put("clearHistory", Integer.valueOf(this.COMMAND_CLEAR_HISTORY)).build();
    }

    public final void receiveCommand(RNCWebViewWrapper rNCWebViewWrapper, String str, ReadableArray readableArray) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        Intrinsics.checkNotNullParameter(str, "commandId");
        Intrinsics.checkNotNullParameter(readableArray, "args");
        RNCWebView webView = rNCWebViewWrapper.getWebView();
        switch (str.hashCode()) {
            case -1241591313:
                if (str.equals("goBack")) {
                    webView.goBack();
                    return;
                }
                return;
            case -948122918:
                if (str.equals("stopLoading")) {
                    webView.stopLoading();
                    return;
                }
                return;
            case -934641255:
                if (str.equals("reload")) {
                    webView.reload();
                    return;
                }
                return;
            case -759238347:
                if (str.equals("clearCache")) {
                    webView.clearCache(readableArray.getBoolean(0));
                    return;
                }
                return;
            case -318289731:
                if (str.equals("goForward")) {
                    webView.goForward();
                    return;
                }
                return;
            case -265032709:
                if (str.equals("clearFormData")) {
                    webView.clearFormData();
                    return;
                }
                return;
            case 336631465:
                if (str.equals("loadUrl")) {
                    String string = readableArray.getString(0);
                    if (string != null) {
                        webView.progressChangedFilter.setWaitingForCommandLoadUrl(false);
                        webView.loadUrl(string);
                        return;
                    }
                    throw new RuntimeException("Arguments for loading an url are null!");
                }
                return;
            case 903120263:
                if (str.equals("clearHistory")) {
                    webView.clearHistory();
                    return;
                }
                return;
            case 1280029577:
                if (str.equals("requestFocus")) {
                    webView.requestFocus();
                    return;
                }
                return;
            case 1490029383:
                if (str.equals("postMessage")) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put(Constants.ScionAnalytics.MessageType.DATA_MESSAGE, readableArray.getString(0));
                        webView.evaluateJavascriptWithFallback("(function () {var event;var data = " + jSONObject + ";try {event = new MessageEvent('message', data);} catch (e) {event = document.createEvent('MessageEvent');event.initMessageEvent('message', true, true, data.data, data.origin, data.lastEventId, data.source);}document.dispatchEvent(event);})();");
                        return;
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    return;
                }
            case 2104576510:
                if (str.equals("injectJavaScript")) {
                    webView.evaluateJavascriptWithFallback(readableArray.getString(0));
                    return;
                }
                return;
            default:
                return;
        }
    }

    public final void setMixedContentMode(RNCWebViewWrapper rNCWebViewWrapper, String str) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        RNCWebView webView = rNCWebViewWrapper.getWebView();
        if (str == null || Intrinsics.areEqual((Object) ReactScrollViewHelper.OVER_SCROLL_NEVER, (Object) str)) {
            webView.getSettings().setMixedContentMode(1);
        } else if (Intrinsics.areEqual((Object) ReactScrollViewHelper.OVER_SCROLL_ALWAYS, (Object) str)) {
            webView.getSettings().setMixedContentMode(0);
        } else if (Intrinsics.areEqual((Object) "compatibility", (Object) str)) {
            webView.getSettings().setMixedContentMode(2);
        }
    }

    public final void setAllowUniversalAccessFromFileURLs(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        rNCWebViewWrapper.getWebView().getSettings().setAllowUniversalAccessFromFileURLs(z);
    }

    private final String getDownloadingMessageOrDefault() {
        String str = this.mDownloadingMessage;
        return str == null ? this.DEFAULT_DOWNLOADING_MESSAGE : str;
    }

    private final String getLackPermissionToDownloadMessageOrDefault() {
        String str = this.mLackPermissionToDownloadMessage;
        return str == null ? this.DEFAULT_LACK_PERMISSION_TO_DOWNLOAD_MESSAGE : str;
    }

    public final void setSource(RNCWebViewWrapper rNCWebViewWrapper, ReadableMap readableMap) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        this.mPendingSource = readableMap;
    }

    private final void loadSource(RNCWebViewWrapper rNCWebViewWrapper, ReadableMap readableMap) {
        byte[] bArr;
        String str;
        RNCWebView webView = rNCWebViewWrapper.getWebView();
        if (readableMap != null) {
            if (readableMap.hasKey("html")) {
                String string = readableMap.getString("html");
                if (readableMap.hasKey("baseUrl")) {
                    str = readableMap.getString("baseUrl");
                } else {
                    str = "";
                }
                Intrinsics.checkNotNull(string);
                webView.loadDataWithBaseURL(str, string, this.HTML_MIME_TYPE, this.HTML_ENCODING, (String) null);
                return;
            } else if (readableMap.hasKey("uri")) {
                String string2 = readableMap.getString("uri");
                String url = webView.getUrl();
                if (url != null && Intrinsics.areEqual((Object) url, (Object) string2)) {
                    return;
                }
                if (!readableMap.hasKey(FirebaseAnalytics.Param.METHOD) || !StringsKt.equals(readableMap.getString(FirebaseAnalytics.Param.METHOD), this.HTTP_METHOD_POST, true)) {
                    HashMap hashMap = new HashMap();
                    if (readableMap.hasKey("headers")) {
                        if (this.newArch) {
                            ReadableArray array = readableMap.getArray("headers");
                            Intrinsics.checkNotNull(array);
                            Iterator<Object> it = array.toArrayList().iterator();
                            Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
                            while (it.hasNext()) {
                                Object next = it.next();
                                Intrinsics.checkNotNull(next, "null cannot be cast to non-null type java.util.HashMap<kotlin.String, kotlin.String>");
                                HashMap hashMap2 = (HashMap) next;
                                String str2 = (String) hashMap2.get("name");
                                if (str2 == null) {
                                    str2 = "";
                                }
                                String str3 = (String) hashMap2.get("value");
                                if (str3 == null) {
                                    str3 = "";
                                }
                                Locale locale = Locale.ENGLISH;
                                Intrinsics.checkNotNullExpressionValue(locale, "ENGLISH");
                                String lowerCase = str2.toLowerCase(locale);
                                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                                if (Intrinsics.areEqual((Object) "user-agent", (Object) lowerCase)) {
                                    webView.getSettings().setUserAgentString(str3);
                                } else {
                                    hashMap.put(str2, str3);
                                }
                            }
                        } else {
                            ReadableMap map = readableMap.getMap("headers");
                            Intrinsics.checkNotNull(map);
                            ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
                            while (keySetIterator.hasNextKey()) {
                                String nextKey = keySetIterator.nextKey();
                                Locale locale2 = Locale.ENGLISH;
                                Intrinsics.checkNotNullExpressionValue(locale2, "ENGLISH");
                                String lowerCase2 = nextKey.toLowerCase(locale2);
                                Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                                if (Intrinsics.areEqual((Object) "user-agent", (Object) lowerCase2)) {
                                    webView.getSettings().setUserAgentString(map.getString(nextKey));
                                } else {
                                    hashMap.put(nextKey, map.getString(nextKey));
                                }
                            }
                        }
                    }
                    Intrinsics.checkNotNull(string2);
                    webView.loadUrl(string2, hashMap);
                    return;
                }
                if (readableMap.hasKey("body")) {
                    String string3 = readableMap.getString("body");
                    try {
                        Intrinsics.checkNotNull(string3);
                        Charset forName = Charset.forName("UTF-8");
                        Intrinsics.checkNotNullExpressionValue(forName, "forName(...)");
                        bArr = string3.getBytes(forName);
                        Intrinsics.checkNotNullExpressionValue(bArr, "getBytes(...)");
                    } catch (UnsupportedEncodingException unused) {
                        Intrinsics.checkNotNull(string3);
                        bArr = string3.getBytes(Charsets.UTF_8);
                        Intrinsics.checkNotNullExpressionValue(bArr, "getBytes(...)");
                    }
                } else {
                    bArr = null;
                }
                if (bArr == null) {
                    bArr = new byte[0];
                }
                Intrinsics.checkNotNull(string2);
                webView.postUrl(string2, bArr);
                return;
            }
        }
        webView.loadUrl(this.BLANK_URL);
    }

    public final void setMessagingModuleName(RNCWebViewWrapper rNCWebViewWrapper, String str) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        rNCWebViewWrapper.getWebView().messagingModuleName = str;
    }

    public final void setCacheEnabled(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        rNCWebViewWrapper.getWebView().getSettings().setCacheMode(z ? -1 : 2);
    }

    public final void setIncognito(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        RNCWebView webView = rNCWebViewWrapper.getWebView();
        if (z) {
            CookieManager.getInstance().removeAllCookies((ValueCallback) null);
            webView.getSettings().setCacheMode(2);
            webView.clearHistory();
            webView.clearCache(true);
            webView.clearFormData();
            webView.getSettings().setSavePassword(false);
            webView.getSettings().setSaveFormData(false);
        }
    }

    public final void setInjectedJavaScript(RNCWebViewWrapper rNCWebViewWrapper, String str) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        rNCWebViewWrapper.getWebView().injectedJS = str;
    }

    public final void setInjectedJavaScriptBeforeContentLoaded(RNCWebViewWrapper rNCWebViewWrapper, String str) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        rNCWebViewWrapper.getWebView().injectedJSBeforeContentLoaded = str;
    }

    public final void setInjectedJavaScriptForMainFrameOnly(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        rNCWebViewWrapper.getWebView().injectedJavaScriptForMainFrameOnly = z;
    }

    public final void setInjectedJavaScriptBeforeContentLoadedForMainFrameOnly(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        rNCWebViewWrapper.getWebView().injectedJavaScriptBeforeContentLoadedForMainFrameOnly = z;
    }

    public final void setInjectedJavaScriptObject(RNCWebViewWrapper rNCWebViewWrapper, String str) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        rNCWebViewWrapper.getWebView().setInjectedJavaScriptObject(str);
    }

    public final void setJavaScriptCanOpenWindowsAutomatically(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        rNCWebViewWrapper.getWebView().getSettings().setJavaScriptCanOpenWindowsAutomatically(z);
    }

    public final void setShowsVerticalScrollIndicator(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        rNCWebViewWrapper.getWebView().setVerticalScrollBarEnabled(z);
    }

    public final void setShowsHorizontalScrollIndicator(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        rNCWebViewWrapper.getWebView().setHorizontalScrollBarEnabled(z);
    }

    public final void setMessagingEnabled(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        rNCWebViewWrapper.getWebView().setMessagingEnabled(z);
    }

    public final void setMediaPlaybackRequiresUserAction(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        rNCWebViewWrapper.getWebView().getSettings().setMediaPlaybackRequiresUserGesture(z);
    }

    public final void setHasOnScroll(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        rNCWebViewWrapper.getWebView().setHasScrollEvent(z);
    }

    public final void setJavaScriptEnabled(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        rNCWebViewWrapper.getWebView().getSettings().setJavaScriptEnabled(z);
    }

    public final void setAllowFileAccess(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        rNCWebViewWrapper.getWebView().getSettings().setAllowFileAccess(z);
    }

    public final void setAllowFileAccessFromFileURLs(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        rNCWebViewWrapper.getWebView().getSettings().setAllowFileAccessFromFileURLs(z);
    }

    public final void setAllowsFullscreenVideo(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        RNCWebView webView = rNCWebViewWrapper.getWebView();
        this.mAllowsFullscreenVideo = z;
        setupWebChromeClient(webView);
    }

    public final void setAndroidLayerType(RNCWebViewWrapper rNCWebViewWrapper, String str) {
        int i;
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        RNCWebView webView = rNCWebViewWrapper.getWebView();
        if (Intrinsics.areEqual((Object) str, (Object) "hardware")) {
            i = 2;
        } else {
            i = Intrinsics.areEqual((Object) str, (Object) "software") ? 1 : 0;
        }
        webView.setLayerType(i, (Paint) null);
    }

    public final void setCacheMode(RNCWebViewWrapper rNCWebViewWrapper, String str) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        WebSettings settings = rNCWebViewWrapper.getWebView().getSettings();
        int i = -1;
        if (str != null) {
            switch (str.hashCode()) {
                case -2059164003:
                    if (str.equals("LOAD_NO_CACHE")) {
                        i = 2;
                        break;
                    }
                    break;
                case -1215135800:
                    boolean equals = str.equals("LOAD_DEFAULT");
                    break;
                case -873877826:
                    if (str.equals("LOAD_CACHE_ELSE_NETWORK")) {
                        i = 1;
                        break;
                    }
                    break;
                case 1548620642:
                    if (str.equals("LOAD_CACHE_ONLY")) {
                        i = 3;
                        break;
                    }
                    break;
            }
        }
        settings.setCacheMode(i);
    }

    public final void setDomStorageEnabled(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        rNCWebViewWrapper.getWebView().getSettings().setDomStorageEnabled(z);
    }

    public final void setDownloadingMessage(String str) {
        this.mDownloadingMessage = str;
    }

    public final void setForceDarkOn(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        RNCWebView webView = rNCWebViewWrapper.getWebView();
        if (Build.VERSION.SDK_INT > 28) {
            if (WebViewFeature.isFeatureSupported("FORCE_DARK")) {
                WebSettingsCompat.setForceDark(webView.getSettings(), z ? 2 : 0);
            }
            if (z && WebViewFeature.isFeatureSupported("FORCE_DARK_STRATEGY")) {
                WebSettingsCompat.setForceDarkStrategy(webView.getSettings(), 2);
            }
        }
    }

    public final void setGeolocationEnabled(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        rNCWebViewWrapper.getWebView().getSettings().setGeolocationEnabled(z);
    }

    public final void setLackPermissionToDownloadMessage(String str) {
        this.mLackPermissionToDownloadMessage = str;
    }

    public final void setHasOnOpenWindowEvent(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        RNCWebView webView = rNCWebViewWrapper.getWebView();
        this.mHasOnOpenWindowEvent = z;
        setupWebChromeClient(webView);
    }

    public final void setMinimumFontSize(RNCWebViewWrapper rNCWebViewWrapper, int i) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        rNCWebViewWrapper.getWebView().getSettings().setMinimumFontSize(i);
    }

    public final void setAllowsProtectedMedia(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        RNCWebView webView = rNCWebViewWrapper.getWebView();
        this.mAllowsProtectedMedia = z;
        WebChromeClient webChromeClient = webView.getWebChromeClient();
        if (webChromeClient != null && (webChromeClient instanceof RNCWebChromeClient)) {
            ((RNCWebChromeClient) webChromeClient).setAllowsProtectedMedia(z);
        }
    }

    public final void setMenuCustomItems(RNCWebViewWrapper rNCWebViewWrapper, ReadableArray readableArray) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        RNCWebView webView = rNCWebViewWrapper.getWebView();
        if (readableArray == null) {
            webView.setMenuCustomItems((List<Map<String, String>>) null);
            return;
        }
        ArrayList<Object> arrayList = readableArray.toArrayList();
        Intrinsics.checkNotNull(arrayList, "null cannot be cast to non-null type kotlin.collections.List<kotlin.collections.Map<kotlin.String, kotlin.String>>");
        webView.setMenuCustomItems(arrayList);
    }

    public final void setNestedScrollEnabled(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        rNCWebViewWrapper.getWebView().nestedScrollEnabled = z;
    }

    public final void setOverScrollMode(RNCWebViewWrapper rNCWebViewWrapper, String str) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        RNCWebView webView = rNCWebViewWrapper.getWebView();
        int i = 0;
        if (str != null) {
            int hashCode = str.hashCode();
            if (hashCode == -1414557169) {
                boolean equals = str.equals(ReactScrollViewHelper.OVER_SCROLL_ALWAYS);
            } else if (hashCode != 104712844) {
                if (hashCode == 951530617 && str.equals(FirebaseAnalytics.Param.CONTENT)) {
                    i = 1;
                }
            } else if (str.equals(ReactScrollViewHelper.OVER_SCROLL_NEVER)) {
                i = 2;
            }
        }
        webView.setOverScrollMode(i);
    }

    public final void setSaveFormDataDisabled(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        rNCWebViewWrapper.getWebView().getSettings().setSaveFormData(!z);
    }

    public final void setScalesPageToFit(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        RNCWebView webView = rNCWebViewWrapper.getWebView();
        webView.getSettings().setLoadWithOverviewMode(z);
        webView.getSettings().setUseWideViewPort(z);
    }

    public final void setSetBuiltInZoomControls(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        rNCWebViewWrapper.getWebView().getSettings().setBuiltInZoomControls(z);
    }

    public final void setSetDisplayZoomControls(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        rNCWebViewWrapper.getWebView().getSettings().setDisplayZoomControls(z);
    }

    public final void setSetSupportMultipleWindows(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        rNCWebViewWrapper.getWebView().getSettings().setSupportMultipleWindows(z);
    }

    public final void setTextZoom(RNCWebViewWrapper rNCWebViewWrapper, int i) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        rNCWebViewWrapper.getWebView().getSettings().setTextZoom(i);
    }

    public final void setThirdPartyCookiesEnabled(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        CookieManager.getInstance().setAcceptThirdPartyCookies(rNCWebViewWrapper.getWebView(), z);
    }

    public final void setWebviewDebuggingEnabled(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        Intrinsics.checkNotNullParameter(rNCWebViewWrapper, "viewWrapper");
        WebView.setWebContentsDebuggingEnabled(z);
    }
}
