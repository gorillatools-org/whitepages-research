package com.facebook.react.modules.debug;

import com.facebook.fbreact.specs.NativeDevSettingsSpec;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.module.annotations.ReactModule;
import com.salesforce.marketingcloud.config.a;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "DevSettings")
public final class DevSettingsModule extends NativeDevSettingsSpec {
    private final DevSupportManager devSupportManager;

    public void addListener(String str) {
        Intrinsics.checkNotNullParameter(str, a.h);
    }

    public void onFastRefresh() {
    }

    public void removeListeners(double d) {
    }

    public void setIsShakeToShowDevMenuEnabled(boolean z) {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DevSettingsModule(ReactApplicationContext reactApplicationContext, DevSupportManager devSupportManager2) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(devSupportManager2, "devSupportManager");
        this.devSupportManager = devSupportManager2;
    }

    public void reload() {
        if (this.devSupportManager.getDevSupportEnabled()) {
            UiThreadUtil.runOnUiThread(new DevSettingsModule$$ExternalSyntheticLambda1(this));
        }
    }

    /* access modifiers changed from: private */
    public static final void reload$lambda$0(DevSettingsModule devSettingsModule) {
        devSettingsModule.devSupportManager.handleReloadJS();
    }

    public void reloadWithReason(String str) {
        Intrinsics.checkNotNullParameter(str, "reason");
        reload();
    }

    public void setHotLoadingEnabled(boolean z) {
        this.devSupportManager.setHotModuleReplacementEnabled(z);
    }

    public void setIsDebuggingRemotely(boolean z) {
        this.devSupportManager.setRemoteJSDebugEnabled(z);
    }

    public void setProfilingEnabled(boolean z) {
        this.devSupportManager.setFpsDebugEnabled(z);
    }

    public void toggleElementInspector() {
        this.devSupportManager.toggleElementInspector();
    }

    public void addMenuItem(String str) {
        Intrinsics.checkNotNullParameter(str, "title");
        this.devSupportManager.addCustomDevOption(str, new DevSettingsModule$$ExternalSyntheticLambda0(str, this));
    }

    /* access modifiers changed from: private */
    public static final void addMenuItem$lambda$1(String str, DevSettingsModule devSettingsModule) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("title", str);
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = devSettingsModule.getReactApplicationContextIfActiveOrWarn();
        if (reactApplicationContextIfActiveOrWarn != null) {
            reactApplicationContextIfActiveOrWarn.emitDeviceEvent("didPressMenuItem", createMap);
        }
    }

    public void openDebugger() {
        this.devSupportManager.openDebugger();
    }
}
