package com.facebook.fbreact.specs;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;

public abstract class NativeFrameRateLoggerSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "FrameRateLogger";

    @DoNotStrip
    @ReactMethod
    public abstract void beginScroll();

    @DoNotStrip
    @ReactMethod
    public abstract void endScroll();

    @DoNotStrip
    @ReactMethod
    public abstract void setContext(String str);

    @DoNotStrip
    @ReactMethod
    public abstract void setGlobalOptions(ReadableMap readableMap);

    public NativeFrameRateLoggerSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public String getName() {
        return NAME;
    }
}
