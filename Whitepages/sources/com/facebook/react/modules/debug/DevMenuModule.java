package com.facebook.react.modules.debug;

import com.facebook.fbreact.specs.NativeDevMenuSpec;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.module.annotations.ReactModule;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "DevMenu")
public final class DevMenuModule extends NativeDevMenuSpec {
    private final DevSupportManager devSupportManager;

    public void setProfilingEnabled(boolean z) {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DevMenuModule(ReactApplicationContext reactApplicationContext, DevSupportManager devSupportManager2) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(devSupportManager2, "devSupportManager");
        this.devSupportManager = devSupportManager2;
    }

    public void show() {
        if (this.devSupportManager.getDevSupportEnabled()) {
            this.devSupportManager.showDevOptionsDialog();
        }
    }

    public void reload() {
        if (this.devSupportManager.getDevSupportEnabled()) {
            UiThreadUtil.runOnUiThread(new DevMenuModule$$ExternalSyntheticLambda0(this));
        }
    }

    /* access modifiers changed from: private */
    public static final void reload$lambda$0(DevMenuModule devMenuModule) {
        devMenuModule.devSupportManager.handleReloadJS();
    }

    public void debugRemotely(boolean z) {
        this.devSupportManager.setRemoteJSDebugEnabled(z);
    }

    public void setHotLoadingEnabled(boolean z) {
        this.devSupportManager.setHotModuleReplacementEnabled(z);
    }
}
