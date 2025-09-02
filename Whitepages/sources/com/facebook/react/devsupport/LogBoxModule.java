package com.facebook.react.devsupport;

import com.facebook.fbreact.specs.NativeLogBoxSpec;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.SurfaceDelegate;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.module.annotations.ReactModule;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "LogBox")
public final class LogBoxModule extends NativeLogBoxSpec {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String NAME = "LogBox";
    private final DevSupportManager devSupportManager;
    private final SurfaceDelegate surfaceDelegate;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LogBoxModule(ReactApplicationContext reactApplicationContext, DevSupportManager devSupportManager2) {
        super(reactApplicationContext);
        Intrinsics.checkNotNullParameter(devSupportManager2, "devSupportManager");
        this.devSupportManager = devSupportManager2;
        SurfaceDelegate createSurfaceDelegate = devSupportManager2.createSurfaceDelegate("LogBox");
        this.surfaceDelegate = createSurfaceDelegate == null ? new LogBoxDialogSurfaceDelegate(devSupportManager2) : createSurfaceDelegate;
    }

    public void show() {
        UiThreadUtil.runOnUiThread(new LogBoxModule$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: private */
    public static final void show$lambda$0(LogBoxModule logBoxModule) {
        if (!logBoxModule.surfaceDelegate.isContentViewReady()) {
            logBoxModule.surfaceDelegate.createContentView("LogBox");
        }
        logBoxModule.surfaceDelegate.show();
    }

    /* access modifiers changed from: private */
    public static final void hide$lambda$1(LogBoxModule logBoxModule) {
        logBoxModule.surfaceDelegate.hide();
    }

    public void hide() {
        UiThreadUtil.runOnUiThread(new LogBoxModule$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    public static final void invalidate$lambda$2(LogBoxModule logBoxModule) {
        logBoxModule.surfaceDelegate.destroyContentView();
    }

    public void invalidate() {
        UiThreadUtil.runOnUiThread(new LogBoxModule$$ExternalSyntheticLambda1(this));
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
