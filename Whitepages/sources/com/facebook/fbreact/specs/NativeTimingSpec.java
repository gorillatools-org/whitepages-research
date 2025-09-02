package com.facebook.fbreact.specs;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;

public abstract class NativeTimingSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "Timing";

    @DoNotStrip
    @ReactMethod
    public abstract void createTimer(double d, double d2, double d3, boolean z);

    @DoNotStrip
    @ReactMethod
    public abstract void deleteTimer(double d);

    @DoNotStrip
    @ReactMethod
    public abstract void setSendIdleEvents(boolean z);

    public NativeTimingSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public String getName() {
        return "Timing";
    }
}
