package com.reactnativecommunity.webview;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.scroll.ScrollEventType;
import java.util.Map;

public class RNCWebViewManager extends ViewGroupManager<RNCWebViewWrapper> {
    private final RNCWebViewManagerImpl mRNCWebViewManagerImpl = new RNCWebViewManagerImpl();

    public String getName() {
        return "RNCWebView";
    }

    public RNCWebViewWrapper createViewInstance(ThemedReactContext themedReactContext) {
        return this.mRNCWebViewManagerImpl.createViewInstance(themedReactContext);
    }

    public RNCWebViewWrapper createViewInstance(ThemedReactContext themedReactContext, RNCWebView rNCWebView) {
        return this.mRNCWebViewManagerImpl.createViewInstance(themedReactContext, rNCWebView);
    }

    @ReactProp(name = "allowFileAccess")
    public void setAllowFileAccess(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setAllowFileAccess(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "allowFileAccessFromFileURLs")
    public void setAllowFileAccessFromFileURLs(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setAllowFileAccessFromFileURLs(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "allowUniversalAccessFromFileURLs")
    public void setAllowUniversalAccessFromFileURLs(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setAllowUniversalAccessFromFileURLs(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "allowsFullscreenVideo")
    public void setAllowsFullscreenVideo(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setAllowsFullscreenVideo(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "allowsProtectedMedia")
    public void setAllowsProtectedMedia(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setAllowsProtectedMedia(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "androidLayerType")
    public void setAndroidLayerType(RNCWebViewWrapper rNCWebViewWrapper, String str) {
        this.mRNCWebViewManagerImpl.setAndroidLayerType(rNCWebViewWrapper, str);
    }

    @ReactProp(name = "applicationNameForUserAgent")
    public void setApplicationNameForUserAgent(RNCWebViewWrapper rNCWebViewWrapper, String str) {
        this.mRNCWebViewManagerImpl.setApplicationNameForUserAgent(rNCWebViewWrapper, str);
    }

    @ReactProp(name = "basicAuthCredential")
    public void setBasicAuthCredential(RNCWebViewWrapper rNCWebViewWrapper, ReadableMap readableMap) {
        this.mRNCWebViewManagerImpl.setBasicAuthCredential(rNCWebViewWrapper, readableMap);
    }

    @ReactProp(name = "cacheEnabled")
    public void setCacheEnabled(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setCacheEnabled(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "cacheMode")
    public void setCacheMode(RNCWebViewWrapper rNCWebViewWrapper, String str) {
        this.mRNCWebViewManagerImpl.setCacheMode(rNCWebViewWrapper, str);
    }

    @ReactProp(name = "domStorageEnabled")
    public void setDomStorageEnabled(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setDomStorageEnabled(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "downloadingMessage")
    public void setDownloadingMessage(RNCWebViewWrapper rNCWebViewWrapper, String str) {
        this.mRNCWebViewManagerImpl.setDownloadingMessage(str);
    }

    @ReactProp(name = "forceDarkOn")
    public void setForceDarkOn(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setForceDarkOn(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "geolocationEnabled")
    public void setGeolocationEnabled(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setGeolocationEnabled(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "hasOnScroll")
    public void setHasOnScroll(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setHasOnScroll(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "incognito")
    public void setIncognito(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setIncognito(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "injectedJavaScript")
    public void setInjectedJavaScript(RNCWebViewWrapper rNCWebViewWrapper, String str) {
        this.mRNCWebViewManagerImpl.setInjectedJavaScript(rNCWebViewWrapper, str);
    }

    @ReactProp(name = "injectedJavaScriptBeforeContentLoaded")
    public void setInjectedJavaScriptBeforeContentLoaded(RNCWebViewWrapper rNCWebViewWrapper, String str) {
        this.mRNCWebViewManagerImpl.setInjectedJavaScriptBeforeContentLoaded(rNCWebViewWrapper, str);
    }

    @ReactProp(name = "injectedJavaScriptForMainFrameOnly")
    public void setInjectedJavaScriptForMainFrameOnly(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setInjectedJavaScriptForMainFrameOnly(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "injectedJavaScriptBeforeContentLoadedForMainFrameOnly")
    public void setInjectedJavaScriptBeforeContentLoadedForMainFrameOnly(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setInjectedJavaScriptBeforeContentLoadedForMainFrameOnly(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "injectedJavaScriptObject")
    public void setInjectedJavaScriptObject(RNCWebViewWrapper rNCWebViewWrapper, String str) {
        this.mRNCWebViewManagerImpl.setInjectedJavaScriptObject(rNCWebViewWrapper, str);
    }

    @ReactProp(name = "javaScriptCanOpenWindowsAutomatically")
    public void setJavaScriptCanOpenWindowsAutomatically(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setJavaScriptCanOpenWindowsAutomatically(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "javaScriptEnabled")
    public void setJavaScriptEnabled(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setJavaScriptEnabled(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "lackPermissionToDownloadMessage")
    public void setLackPermissionToDownloadMessage(RNCWebViewWrapper rNCWebViewWrapper, String str) {
        this.mRNCWebViewManagerImpl.setLackPermissionToDownloadMessage(str);
    }

    @ReactProp(name = "hasOnOpenWindowEvent")
    public void setHasOnOpenWindowEvent(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setHasOnOpenWindowEvent(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "mediaPlaybackRequiresUserAction")
    public void setMediaPlaybackRequiresUserAction(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setMediaPlaybackRequiresUserAction(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "messagingEnabled")
    public void setMessagingEnabled(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setMessagingEnabled(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "menuItems")
    public void setMenuCustomItems(RNCWebViewWrapper rNCWebViewWrapper, ReadableArray readableArray) {
        this.mRNCWebViewManagerImpl.setMenuCustomItems(rNCWebViewWrapper, readableArray);
    }

    @ReactProp(name = "messagingModuleName")
    public void setMessagingModuleName(RNCWebViewWrapper rNCWebViewWrapper, String str) {
        this.mRNCWebViewManagerImpl.setMessagingModuleName(rNCWebViewWrapper, str);
    }

    @ReactProp(name = "minimumFontSize")
    public void setMinimumFontSize(RNCWebViewWrapper rNCWebViewWrapper, int i) {
        this.mRNCWebViewManagerImpl.setMinimumFontSize(rNCWebViewWrapper, i);
    }

    @ReactProp(name = "mixedContentMode")
    public void setMixedContentMode(RNCWebViewWrapper rNCWebViewWrapper, String str) {
        this.mRNCWebViewManagerImpl.setMixedContentMode(rNCWebViewWrapper, str);
    }

    @ReactProp(name = "nestedScrollEnabled")
    public void setNestedScrollEnabled(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setNestedScrollEnabled(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "overScrollMode")
    public void setOverScrollMode(RNCWebViewWrapper rNCWebViewWrapper, String str) {
        this.mRNCWebViewManagerImpl.setOverScrollMode(rNCWebViewWrapper, str);
    }

    @ReactProp(name = "saveFormDataDisabled")
    public void setSaveFormDataDisabled(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setSaveFormDataDisabled(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "scalesPageToFit")
    public void setScalesPageToFit(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setScalesPageToFit(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "setBuiltInZoomControls")
    public void setSetBuiltInZoomControls(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setSetBuiltInZoomControls(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "setDisplayZoomControls")
    public void setSetDisplayZoomControls(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setSetDisplayZoomControls(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "setSupportMultipleWindows")
    public void setSetSupportMultipleWindows(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setSetSupportMultipleWindows(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "showsHorizontalScrollIndicator")
    public void setShowsHorizontalScrollIndicator(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setShowsHorizontalScrollIndicator(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "showsVerticalScrollIndicator")
    public void setShowsVerticalScrollIndicator(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setShowsVerticalScrollIndicator(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "source")
    public void setSource(RNCWebViewWrapper rNCWebViewWrapper, ReadableMap readableMap) {
        this.mRNCWebViewManagerImpl.setSource(rNCWebViewWrapper, readableMap);
    }

    @ReactProp(name = "textZoom")
    public void setTextZoom(RNCWebViewWrapper rNCWebViewWrapper, int i) {
        this.mRNCWebViewManagerImpl.setTextZoom(rNCWebViewWrapper, i);
    }

    @ReactProp(name = "thirdPartyCookiesEnabled")
    public void setThirdPartyCookiesEnabled(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setThirdPartyCookiesEnabled(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "webviewDebuggingEnabled")
    public void setWebviewDebuggingEnabled(RNCWebViewWrapper rNCWebViewWrapper, boolean z) {
        this.mRNCWebViewManagerImpl.setWebviewDebuggingEnabled(rNCWebViewWrapper, z);
    }

    @ReactProp(name = "userAgent")
    public void setUserAgent(RNCWebViewWrapper rNCWebViewWrapper, String str) {
        this.mRNCWebViewManagerImpl.setUserAgent(rNCWebViewWrapper, str);
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext themedReactContext, RNCWebViewWrapper rNCWebViewWrapper) {
        rNCWebViewWrapper.getWebView().setWebViewClient(new RNCWebViewClient());
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        Map<String, Object> exportedCustomDirectEventTypeConstants = super.getExportedCustomDirectEventTypeConstants();
        if (exportedCustomDirectEventTypeConstants == null) {
            exportedCustomDirectEventTypeConstants = MapBuilder.newHashMap();
        }
        exportedCustomDirectEventTypeConstants.put("topLoadingStart", MapBuilder.of("registrationName", "onLoadingStart"));
        exportedCustomDirectEventTypeConstants.put("topLoadingFinish", MapBuilder.of("registrationName", "onLoadingFinish"));
        exportedCustomDirectEventTypeConstants.put("topLoadingError", MapBuilder.of("registrationName", "onLoadingError"));
        exportedCustomDirectEventTypeConstants.put("topMessage", MapBuilder.of("registrationName", "onMessage"));
        exportedCustomDirectEventTypeConstants.put("topLoadingProgress", MapBuilder.of("registrationName", "onLoadingProgress"));
        exportedCustomDirectEventTypeConstants.put("topShouldStartLoadWithRequest", MapBuilder.of("registrationName", "onShouldStartLoadWithRequest"));
        exportedCustomDirectEventTypeConstants.put(ScrollEventType.getJSEventName(ScrollEventType.SCROLL), MapBuilder.of("registrationName", "onScroll"));
        exportedCustomDirectEventTypeConstants.put("topHttpError", MapBuilder.of("registrationName", "onHttpError"));
        exportedCustomDirectEventTypeConstants.put("topRenderProcessGone", MapBuilder.of("registrationName", "onRenderProcessGone"));
        exportedCustomDirectEventTypeConstants.put("topCustomMenuSelection", MapBuilder.of("registrationName", "onCustomMenuSelection"));
        exportedCustomDirectEventTypeConstants.put("topOpenWindow", MapBuilder.of("registrationName", "onOpenWindow"));
        return exportedCustomDirectEventTypeConstants;
    }

    public Map<String, Integer> getCommandsMap() {
        return this.mRNCWebViewManagerImpl.getCommandsMap();
    }

    public void receiveCommand(RNCWebViewWrapper rNCWebViewWrapper, String str, ReadableArray readableArray) {
        this.mRNCWebViewManagerImpl.receiveCommand(rNCWebViewWrapper, str, readableArray);
        super.receiveCommand(rNCWebViewWrapper, str, readableArray);
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(RNCWebViewWrapper rNCWebViewWrapper) {
        super.onAfterUpdateTransaction(rNCWebViewWrapper);
        this.mRNCWebViewManagerImpl.onAfterUpdateTransaction(rNCWebViewWrapper);
    }

    public void onDropViewInstance(RNCWebViewWrapper rNCWebViewWrapper) {
        this.mRNCWebViewManagerImpl.onDropViewInstance(rNCWebViewWrapper);
        super.onDropViewInstance(rNCWebViewWrapper);
    }
}
