package com.facebook.react.modules.devloading;

import com.facebook.fbreact.specs.NativeDevLoadingViewSpec;
import com.facebook.react.bridge.JSExceptionHandler;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.devsupport.DevSupportManagerBase;
import com.facebook.react.devsupport.StackTraceHelper;
import com.facebook.react.devsupport.interfaces.DevLoadingViewManager;
import com.facebook.react.module.annotations.ReactModule;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "DevLoadingView")
public final class DevLoadingModule extends NativeDevLoadingViewSpec {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String NAME = "DevLoadingView";
    private DevLoadingViewManager devLoadingViewManager;
    private final JSExceptionHandler jsExceptionHandler;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DevLoadingModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(reactApplicationContext, "reactContext");
        JSExceptionHandler jSExceptionHandler = reactApplicationContext.getJSExceptionHandler();
        this.jsExceptionHandler = jSExceptionHandler;
        if (jSExceptionHandler != null && (jSExceptionHandler instanceof DevSupportManagerBase)) {
            this.devLoadingViewManager = ((DevSupportManagerBase) jSExceptionHandler).getDevLoadingViewManager();
        }
    }

    /* access modifiers changed from: private */
    public static final void showMessage$lambda$0(DevLoadingModule devLoadingModule, String str) {
        DevLoadingViewManager devLoadingViewManager2 = devLoadingModule.devLoadingViewManager;
        if (devLoadingViewManager2 != null) {
            devLoadingViewManager2.showMessage(str);
        }
    }

    public void showMessage(String str, Double d, Double d2) {
        Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
        UiThreadUtil.runOnUiThread(new DevLoadingModule$$ExternalSyntheticLambda1(this, str));
    }

    /* access modifiers changed from: private */
    public static final void hide$lambda$1(DevLoadingModule devLoadingModule) {
        DevLoadingViewManager devLoadingViewManager2 = devLoadingModule.devLoadingViewManager;
        if (devLoadingViewManager2 != null) {
            devLoadingViewManager2.hide();
        }
    }

    public void hide() {
        UiThreadUtil.runOnUiThread(new DevLoadingModule$$ExternalSyntheticLambda0(this));
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
