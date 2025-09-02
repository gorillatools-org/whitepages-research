package com.facebook.fbreact.specs;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;

public abstract class NativeDevSettingsSpec extends ReactContextBaseJavaModule implements TurboModule {
    public static final String NAME = "DevSettings";

    @DoNotStrip
    @ReactMethod
    public abstract void addListener(String str);

    @DoNotStrip
    @ReactMethod
    public abstract void addMenuItem(String str);

    @DoNotStrip
    @ReactMethod
    public void onFastRefresh() {
    }

    @DoNotStrip
    @ReactMethod
    public void openDebugger() {
    }

    @DoNotStrip
    @ReactMethod
    public abstract void reload();

    @DoNotStrip
    @ReactMethod
    public void reloadWithReason(String str) {
    }

    @DoNotStrip
    @ReactMethod
    public abstract void removeListeners(double d);

    @DoNotStrip
    @ReactMethod
    public abstract void setHotLoadingEnabled(boolean z);

    @DoNotStrip
    @ReactMethod
    public abstract void setIsDebuggingRemotely(boolean z);

    @DoNotStrip
    @ReactMethod
    public abstract void setIsShakeToShowDevMenuEnabled(boolean z);

    @DoNotStrip
    @ReactMethod
    public abstract void setProfilingEnabled(boolean z);

    @DoNotStrip
    @ReactMethod
    public abstract void toggleElementInspector();

    public NativeDevSettingsSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public String getName() {
        return NAME;
    }
}
