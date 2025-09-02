package com.facebook.fbreact.specs;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;

public abstract class NativePermissionsAndroidSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "PermissionsAndroid";

    @DoNotStrip
    @ReactMethod
    public abstract void checkPermission(String str, Promise promise);

    @DoNotStrip
    @ReactMethod
    public abstract void requestMultiplePermissions(ReadableArray readableArray, Promise promise);

    @DoNotStrip
    @ReactMethod
    public abstract void requestPermission(String str, Promise promise);

    @DoNotStrip
    @ReactMethod
    public abstract void shouldShowRequestPermissionRationale(String str, Promise promise);

    public NativePermissionsAndroidSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public String getName() {
        return "PermissionsAndroid";
    }
}
