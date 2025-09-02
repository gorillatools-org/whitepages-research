package com.facebook.fbreact.specs;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;

public abstract class NativeVibrationSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "Vibration";

    @DoNotStrip
    @ReactMethod
    public abstract void cancel();

    @DoNotStrip
    @ReactMethod
    public abstract void vibrate(double d);

    @DoNotStrip
    @ReactMethod
    public abstract void vibrateByPattern(ReadableArray readableArray, double d);

    public NativeVibrationSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public String getName() {
        return "Vibration";
    }
}
