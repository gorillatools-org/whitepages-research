package com.facebook.fbreact.specs;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;

public abstract class NativeReactDevToolsRuntimeSettingsModuleSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "ReactDevToolsRuntimeSettingsModule";

    @DoNotStrip
    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract WritableMap getReloadAndProfileConfig();

    @DoNotStrip
    @ReactMethod
    public abstract void setReloadAndProfileConfig(ReadableMap readableMap);

    public NativeReactDevToolsRuntimeSettingsModuleSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public String getName() {
        return "ReactDevToolsRuntimeSettingsModule";
    }
}
