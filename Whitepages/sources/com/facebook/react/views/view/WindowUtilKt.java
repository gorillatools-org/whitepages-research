package com.facebook.react.views.view;

import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import com.salesforce.marketingcloud.b;
import kotlin.jvm.internal.Intrinsics;

public final class WindowUtilKt {
    public static final void setStatusBarTranslucency(Window window, boolean z) {
        Intrinsics.checkNotNullParameter(window, "<this>");
        if (z) {
            window.getDecorView().setOnApplyWindowInsetsListener(new WindowUtilKt$$ExternalSyntheticLambda4());
        } else {
            window.getDecorView().setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) null);
        }
        ViewCompat.requestApplyInsets(window.getDecorView());
    }

    /* access modifiers changed from: private */
    public static final WindowInsets setStatusBarTranslucency$lambda$0(View view, WindowInsets windowInsets) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(windowInsets, "insets");
        WindowInsets onApplyWindowInsets = view.onApplyWindowInsets(windowInsets);
        return onApplyWindowInsets.replaceSystemWindowInsets(onApplyWindowInsets.getSystemWindowInsetLeft(), 0, onApplyWindowInsets.getSystemWindowInsetRight(), onApplyWindowInsets.getSystemWindowInsetBottom());
    }

    public static final void setStatusBarVisibility(Window window, boolean z) {
        Intrinsics.checkNotNullParameter(window, "<this>");
        if (z) {
            statusBarHide(window);
        } else {
            statusBarShow(window);
        }
    }

    private static final void statusBarHide(Window window) {
        if (Build.VERSION.SDK_INT >= 30) {
            window.getAttributes().layoutInDisplayCutoutMode = 1;
            window.setDecorFitsSystemWindows(false);
        }
        window.addFlags(1024);
        window.clearFlags(b.u);
    }

    private static final void statusBarShow(Window window) {
        if (Build.VERSION.SDK_INT >= 30) {
            window.getAttributes().layoutInDisplayCutoutMode = 0;
            window.setDecorFitsSystemWindows(true);
        }
        window.addFlags(b.u);
        window.clearFlags(1024);
    }

    public static final void setSystemBarsTranslucency(Window window, boolean z) {
        Intrinsics.checkNotNullParameter(window, "<this>");
        WindowCompat.setDecorFitsSystemWindows(window, !z);
        if (z) {
            int i = 0;
            int i2 = 1;
            boolean z2 = (window.getContext().getResources().getConfiguration().uiMode & 48) == 32;
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 29) {
                window.setStatusBarContrastEnforced(false);
                window.setNavigationBarContrastEnforced(true);
            }
            window.setStatusBarColor(0);
            if (i3 < 29) {
                if (i3 < 27 || z2) {
                    i = Color.argb(128, 27, 27, 27);
                } else {
                    i = Color.argb(230, 255, 255, 255);
                }
            }
            window.setNavigationBarColor(i);
            new WindowInsetsControllerCompat(window, window.getDecorView()).setAppearanceLightNavigationBars(!z2);
            if (i3 >= 28) {
                WindowManager.LayoutParams attributes = window.getAttributes();
                if (i3 >= 30) {
                    i2 = 3;
                }
                attributes.layoutInDisplayCutoutMode = i2;
            }
        }
    }
}
