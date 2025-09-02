package com.facebook.react.devsupport;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.salesforce.marketingcloud.b;

class DebugOverlayController {
    /* access modifiers changed from: private */
    public FrameLayout mFPSDebugViewContainer;
    /* access modifiers changed from: private */
    public final ReactContext mReactContext;
    /* access modifiers changed from: private */
    public final WindowManager mWindowManager;

    public static void requestPermission(Context context) {
        if (!Settings.canDrawOverlays(context)) {
            Intent intent = new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + context.getPackageName()));
            intent.setFlags(268435456);
            FLog.w(ReactConstants.TAG, "Overlay permissions needs to be granted in order for react native apps to run in dev mode");
            if (canHandleIntent(context, intent)) {
                context.startActivity(intent);
            }
        }
    }

    /* access modifiers changed from: private */
    public static boolean permissionCheck(Context context) {
        return Settings.canDrawOverlays(context);
    }

    private static boolean hasPermission(Context context, String str) {
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), b.v).requestedPermissions;
            if (strArr != null) {
                for (String equals : strArr) {
                    if (equals.equals(str)) {
                        return true;
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            FLog.e(ReactConstants.TAG, "Error while retrieving package info", (Throwable) e);
        }
        return false;
    }

    private static boolean canHandleIntent(Context context, Intent intent) {
        return intent.resolveActivity(context.getPackageManager()) != null;
    }

    public DebugOverlayController(ReactContext reactContext) {
        this.mReactContext = reactContext;
        this.mWindowManager = (WindowManager) reactContext.getSystemService("window");
    }

    public void setFpsDebugViewVisible(final boolean z) {
        UiThreadUtil.runOnUiThread(new Runnable() {
            public void run() {
                if (!z || DebugOverlayController.this.mFPSDebugViewContainer != null) {
                    if (!z && DebugOverlayController.this.mFPSDebugViewContainer != null) {
                        DebugOverlayController.this.mFPSDebugViewContainer.removeAllViews();
                        DebugOverlayController.this.mWindowManager.removeView(DebugOverlayController.this.mFPSDebugViewContainer);
                        DebugOverlayController.this.mFPSDebugViewContainer = null;
                    }
                } else if (!DebugOverlayController.permissionCheck(DebugOverlayController.this.mReactContext)) {
                    FLog.d(ReactConstants.TAG, "Wait for overlay permission to be set");
                } else {
                    DebugOverlayController.this.mFPSDebugViewContainer = new FpsView(DebugOverlayController.this.mReactContext);
                    DebugOverlayController.this.mWindowManager.addView(DebugOverlayController.this.mFPSDebugViewContainer, new WindowManager.LayoutParams(-1, -1, WindowOverlayCompat.TYPE_SYSTEM_OVERLAY, 24, -3));
                }
            }
        });
    }
}
