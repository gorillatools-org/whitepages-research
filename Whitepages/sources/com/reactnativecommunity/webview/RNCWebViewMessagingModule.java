package com.reactnativecommunity.webview;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.WritableMap;

public interface RNCWebViewMessagingModule extends JavaScriptModule {
    void onMessage(WritableMap writableMap);

    void onShouldStartLoadWithRequest(WritableMap writableMap);
}
