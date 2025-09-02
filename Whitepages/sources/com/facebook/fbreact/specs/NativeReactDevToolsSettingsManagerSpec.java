package com.facebook.fbreact.specs;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;

public abstract class NativeReactDevToolsSettingsManagerSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "ReactDevToolsSettingsManager";

    @DoNotStrip
    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract String getGlobalHookSettings();

    @DoNotStrip
    @ReactMethod
    public abstract void setGlobalHookSettings(String str);

    public NativeReactDevToolsSettingsManagerSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public String getName() {
        return "ReactDevToolsSettingsManager";
    }
}
