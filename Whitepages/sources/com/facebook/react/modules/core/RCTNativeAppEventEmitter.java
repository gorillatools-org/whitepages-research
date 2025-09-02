package com.facebook.react.modules.core;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.JavaScriptModule;

@DoNotStrip
public interface RCTNativeAppEventEmitter extends JavaScriptModule {
    void emit(String str, Object obj);
}
