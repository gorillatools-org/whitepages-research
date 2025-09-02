package com.facebook.fbreact.specs;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;

public abstract class NativeShareModuleSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "ShareModule";

    @DoNotStrip
    @ReactMethod
    public abstract void share(ReadableMap readableMap, String str, Promise promise);

    public NativeShareModuleSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public String getName() {
        return "ShareModule";
    }
}
