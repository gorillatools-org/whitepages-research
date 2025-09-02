package com.facebook.fbreact.specs;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;

public abstract class NativeIntentAndroidSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "IntentAndroid";

    @DoNotStrip
    @ReactMethod
    public abstract void canOpenURL(String str, Promise promise);

    @DoNotStrip
    @ReactMethod
    public abstract void getInitialURL(Promise promise);

    @DoNotStrip
    @ReactMethod
    public abstract void openSettings(Promise promise);

    @DoNotStrip
    @ReactMethod
    public abstract void openURL(String str, Promise promise);

    @DoNotStrip
    @ReactMethod
    public abstract void sendIntent(String str, ReadableArray readableArray, Promise promise);

    public NativeIntentAndroidSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public String getName() {
        return "IntentAndroid";
    }
}
