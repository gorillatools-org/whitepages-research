package com.facebook.react.modules.core;

import android.net.Uri;
import com.facebook.fbreact.specs.NativeDeviceEventManagerSpec;
import com.facebook.proguard.annotations.DoNotStripAny;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "DeviceEventManager")
public class DeviceEventManagerModule extends NativeDeviceEventManagerSpec {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String NAME = "DeviceEventManager";
    private final Runnable invokeDefaultBackPressRunnable;

    @DoNotStripAny
    public interface RCTDeviceEventEmitter extends JavaScriptModule {
        void emit(String str, Object obj);
    }

    public DeviceEventManagerModule(ReactApplicationContext reactApplicationContext, DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler) {
        super(reactApplicationContext);
        this.invokeDefaultBackPressRunnable = new DeviceEventManagerModule$$ExternalSyntheticLambda0(defaultHardwareBackBtnHandler);
    }

    /* access modifiers changed from: private */
    public static final void invokeDefaultBackPressRunnable$lambda$0(DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler) {
        UiThreadUtil.assertOnUiThread();
        if (defaultHardwareBackBtnHandler != null) {
            defaultHardwareBackBtnHandler.invokeDefaultOnBackPressed();
        }
    }

    public void emitHardwareBackPressed() {
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn != null) {
            reactApplicationContextIfActiveOrWarn.emitDeviceEvent("hardwareBackPress", (Object) null);
        }
    }

    public void emitNewIntentReceived(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        WritableMap createMap = Arguments.createMap();
        createMap.putString("url", uri.toString());
        if (reactApplicationContextIfActiveOrWarn != null) {
            reactApplicationContextIfActiveOrWarn.emitDeviceEvent("url", createMap);
        }
    }

    public void invokeDefaultBackPressHandler() {
        getReactApplicationContext().runOnUiQueueThread(this.invokeDefaultBackPressRunnable);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
