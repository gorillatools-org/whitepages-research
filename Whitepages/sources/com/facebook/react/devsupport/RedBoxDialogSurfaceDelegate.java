package com.facebook.react.devsupport;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativeRedBoxSpec;
import com.facebook.react.R;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.SurfaceDelegate;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.devsupport.interfaces.RedBoxHandler;
import java.util.Objects;

class RedBoxDialogSurfaceDelegate implements SurfaceDelegate {
    /* access modifiers changed from: private */
    public final DevSupportManager mDevSupportManager;
    private Dialog mDialog;
    /* access modifiers changed from: private */
    public final DoubleTapReloadRecognizer mDoubleTapReloadRecognizer = new DoubleTapReloadRecognizer();
    /* access modifiers changed from: private */
    public RedBoxContentView mRedBoxContentView;

    public RedBoxDialogSurfaceDelegate(DevSupportManager devSupportManager) {
        this.mDevSupportManager = devSupportManager;
    }

    public void createContentView(String str) {
        RedBoxHandler redBoxHandler = this.mDevSupportManager.getRedBoxHandler();
        Activity currentActivity = this.mDevSupportManager.getCurrentActivity();
        if (currentActivity == null || currentActivity.isFinishing()) {
            String lastErrorTitle = this.mDevSupportManager.getLastErrorTitle();
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to launch redbox because react activity are not available, here is the error that redbox would've displayed: ");
            if (lastErrorTitle == null) {
                lastErrorTitle = "N/A";
            }
            sb.append(lastErrorTitle);
            FLog.e(ReactConstants.TAG, sb.toString());
            return;
        }
        RedBoxContentView redBoxContentView = new RedBoxContentView(currentActivity);
        this.mRedBoxContentView = redBoxContentView;
        redBoxContentView.setDevSupportManager(this.mDevSupportManager).setRedBoxHandler(redBoxHandler).init();
    }

    public boolean isContentViewReady() {
        return this.mRedBoxContentView != null;
    }

    public void destroyContentView() {
        this.mRedBoxContentView = null;
    }

    public void show() {
        String lastErrorTitle = this.mDevSupportManager.getLastErrorTitle();
        Activity currentActivity = this.mDevSupportManager.getCurrentActivity();
        if (currentActivity == null || currentActivity.isFinishing()) {
            ReactContext currentReactContext = this.mDevSupportManager.getCurrentReactContext();
            if (currentReactContext != null) {
                runAfterHostResume(currentReactContext, new RedBoxDialogSurfaceDelegate$$ExternalSyntheticLambda0(this));
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to launch redbox because react activity and react context is not available, here is the error that redbox would've displayed: ");
            if (lastErrorTitle == null) {
                lastErrorTitle = "N/A";
            }
            sb.append(lastErrorTitle);
            FLog.e(ReactConstants.TAG, sb.toString());
            return;
        }
        RedBoxContentView redBoxContentView = this.mRedBoxContentView;
        if (redBoxContentView == null || redBoxContentView.getContext() != currentActivity) {
            createContentView(NativeRedBoxSpec.NAME);
        }
        this.mRedBoxContentView.refreshContentView();
        if (this.mDialog == null) {
            AnonymousClass1 r0 = new Dialog(currentActivity, R.style.Theme_Catalyst_RedBox) {
                public boolean onKeyUp(int i, KeyEvent keyEvent) {
                    if (i == 82) {
                        RedBoxDialogSurfaceDelegate.this.mDevSupportManager.showDevOptionsDialog();
                        return true;
                    }
                    if (RedBoxDialogSurfaceDelegate.this.mDoubleTapReloadRecognizer.didDoubleTapR(i, getCurrentFocus())) {
                        RedBoxDialogSurfaceDelegate.this.mDevSupportManager.handleReloadJS();
                    }
                    return super.onKeyUp(i, keyEvent);
                }

                /* access modifiers changed from: protected */
                public void onCreate(Bundle bundle) {
                    Objects.requireNonNull(getWindow());
                    getWindow().setBackgroundDrawable(new ColorDrawable(-16777216));
                    ViewCompat.setOnApplyWindowInsetsListener(RedBoxDialogSurfaceDelegate.this.mRedBoxContentView, new RedBoxDialogSurfaceDelegate$1$$ExternalSyntheticLambda0(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout()));
                }

                /* access modifiers changed from: private */
                public static /* synthetic */ WindowInsetsCompat lambda$onCreate$0(int i, View view, WindowInsetsCompat windowInsetsCompat) {
                    Insets insets = windowInsetsCompat.getInsets(i);
                    ((FrameLayout.LayoutParams) view.getLayoutParams()).setMargins(insets.left, insets.top, insets.right, insets.bottom);
                    return WindowInsetsCompat.CONSUMED;
                }
            };
            this.mDialog = r0;
            r0.requestWindowFeature(1);
            this.mDialog.setContentView(this.mRedBoxContentView);
        }
        this.mDialog.show();
    }

    private static void runAfterHostResume(final ReactContext reactContext, final Runnable runnable) {
        reactContext.addLifecycleEventListener(new LifecycleEventListener() {
            public void onHostDestroy() {
            }

            public void onHostPause() {
            }

            public void onHostResume() {
                runnable.run();
                reactContext.removeLifecycleEventListener(this);
            }
        });
    }

    public void hide() {
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            try {
                dialog.dismiss();
            } catch (IllegalArgumentException e) {
                FLog.e(ReactConstants.TAG, "RedBoxDialogSurfaceDelegate: error while dismissing dialog: ", (Throwable) e);
            }
            destroyContentView();
            this.mDialog = null;
        }
    }

    public boolean isShowing() {
        Dialog dialog = this.mDialog;
        return dialog != null && dialog.isShowing();
    }
}
