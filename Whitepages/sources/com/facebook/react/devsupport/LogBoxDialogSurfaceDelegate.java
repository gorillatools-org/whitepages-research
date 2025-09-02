package com.facebook.react.devsupport;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.common.SurfaceDelegate;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.util.RNLog;
import kotlin.jvm.internal.Intrinsics;

public final class LogBoxDialogSurfaceDelegate implements SurfaceDelegate {
    private final DevSupportManager devSupportManager;
    private LogBoxDialog dialog;
    private View reactRootView;

    public LogBoxDialogSurfaceDelegate(DevSupportManager devSupportManager2) {
        Intrinsics.checkNotNullParameter(devSupportManager2, "devSupportManager");
        this.devSupportManager = devSupportManager2;
    }

    public void createContentView(String str) {
        Intrinsics.checkNotNullParameter(str, "appKey");
        Assertions.assertCondition(Intrinsics.areEqual((Object) str, (Object) "LogBox"), "This surface manager can only create LogBox React application");
        View createRootView = this.devSupportManager.createRootView("LogBox");
        this.reactRootView = createRootView;
        if (createRootView == null) {
            RNLog.e("Unable to launch logbox because react was unable to create the root view");
        }
    }

    public boolean isContentViewReady() {
        return this.reactRootView != null;
    }

    public void destroyContentView() {
        View view = this.reactRootView;
        if (view != null) {
            this.devSupportManager.destroyRootView(view);
            this.reactRootView = null;
        }
    }

    public void show() {
        if (!isShowing() && isContentViewReady()) {
            Activity currentActivity = this.devSupportManager.getCurrentActivity();
            if (currentActivity == null || currentActivity.isFinishing()) {
                RNLog.e("Unable to launch logbox because react activity is not available, here is the error that logbox would've displayed: ");
                return;
            }
            LogBoxDialog logBoxDialog = new LogBoxDialog(currentActivity, this.reactRootView);
            this.dialog = logBoxDialog;
            logBoxDialog.setCancelable(false);
            logBoxDialog.show();
        }
    }

    public void hide() {
        LogBoxDialog logBoxDialog;
        if (isShowing() && (logBoxDialog = this.dialog) != null) {
            logBoxDialog.dismiss();
        }
        View view = this.reactRootView;
        ViewGroup viewGroup = (ViewGroup) (view != null ? view.getParent() : null);
        if (viewGroup != null) {
            viewGroup.removeView(this.reactRootView);
        }
        this.dialog = null;
    }

    public boolean isShowing() {
        LogBoxDialog logBoxDialog = this.dialog;
        if (logBoxDialog != null) {
            return logBoxDialog.isShowing();
        }
        return false;
    }
}
