package com.facebook.react.modules.statusbar;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativeStatusBarManagerAndroidSpec;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.views.view.WindowUtilKt;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.util.Arrays;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@ReactModule(name = "StatusBarManager")
public final class StatusBarModule extends NativeStatusBarManagerAndroidSpec {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String DEFAULT_BACKGROUND_COLOR_KEY = "DEFAULT_BACKGROUND_COLOR";
    private static final String HEIGHT_KEY = "HEIGHT";
    public static final String NAME = "StatusBarManager";

    public StatusBarModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    /* access modifiers changed from: protected */
    public Map<String, Object> getTypedExportedConstants() {
        String str;
        Window window;
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null || (window = currentActivity.getWindow()) == null) {
            str = "black";
        } else {
            int statusBarColor = window.getStatusBarColor();
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            str = String.format("#%06X", Arrays.copyOf(new Object[]{Integer.valueOf(statusBarColor & 16777215)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        }
        return MapsKt.mapOf(TuplesKt.to(HEIGHT_KEY, Float.valueOf(PixelUtil.toDIPFromPixel(getStatusBarHeightPx()))), TuplesKt.to(DEFAULT_BACKGROUND_COLOR_KEY, str));
    }

    private final float getStatusBarHeightPx() {
        Window window;
        View decorView;
        WindowInsetsCompat rootWindowInsets;
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null || (window = currentActivity.getWindow()) == null || (decorView = window.getDecorView()) == null || (rootWindowInsets = ViewCompat.getRootWindowInsets(decorView)) == null) {
            return 0.0f;
        }
        return (float) rootWindowInsets.getInsets(WindowInsetsCompat.Type.statusBars() | WindowInsetsCompat.Type.navigationBars() | WindowInsetsCompat.Type.displayCutout()).top;
    }

    public void setColor(double d, boolean z) {
        int i = (int) d;
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            FLog.w(ReactConstants.TAG, "StatusBarModule: Ignored status bar change, current activity is null.");
        } else {
            UiThreadUtil.runOnUiThread(new StatusBarModule$setColor$1(currentActivity, z, i, getReactApplicationContext()));
        }
    }

    public void setTranslucent(boolean z) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            FLog.w(ReactConstants.TAG, "StatusBarModule: Ignored status bar change, current activity is null.");
        } else {
            UiThreadUtil.runOnUiThread(new StatusBarModule$setTranslucent$1(currentActivity, z, getReactApplicationContext()));
        }
    }

    public void setHidden(boolean z) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            FLog.w(ReactConstants.TAG, "StatusBarModule: Ignored status bar change, current activity is null.");
        } else {
            UiThreadUtil.runOnUiThread(new StatusBarModule$$ExternalSyntheticLambda0(currentActivity, z));
        }
    }

    /* access modifiers changed from: private */
    public static final void setHidden$lambda$1(Activity activity, boolean z) {
        Window window = activity.getWindow();
        if (window != null) {
            WindowUtilKt.setStatusBarVisibility(window, z);
        }
    }

    public void setStyle(String str) {
        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            FLog.w(ReactConstants.TAG, "StatusBarModule: Ignored status bar change, current activity is null.");
        } else {
            UiThreadUtil.runOnUiThread(new StatusBarModule$$ExternalSyntheticLambda1(currentActivity, str));
        }
    }

    /* access modifiers changed from: private */
    public static final void setStyle$lambda$2(Activity activity, String str) {
        Window window = activity.getWindow();
        if (window != null) {
            if (Build.VERSION.SDK_INT > 30) {
                WindowInsetsController m = window.getInsetsController();
                if (m != null) {
                    if (Intrinsics.areEqual((Object) "dark-content", (Object) str)) {
                        m.setSystemBarsAppearance(8, 8);
                    } else {
                        m.setSystemBarsAppearance(0, 8);
                    }
                }
            } else {
                View decorView = window.getDecorView();
                Intrinsics.checkNotNullExpressionValue(decorView, "getDecorView(...)");
                int systemUiVisibility = decorView.getSystemUiVisibility();
                decorView.setSystemUiVisibility(Intrinsics.areEqual((Object) "dark-content", (Object) str) ? systemUiVisibility | UserMetadata.MAX_INTERNAL_KEY_SIZE : systemUiVisibility & -8193);
            }
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
