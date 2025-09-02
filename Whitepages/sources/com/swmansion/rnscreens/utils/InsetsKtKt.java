package com.swmansion.rnscreens.utils;

import android.view.View;
import android.view.WindowInsets;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import kotlin.jvm.internal.Intrinsics;

public abstract class InsetsKtKt {
    public static /* synthetic */ Insets resolveInsetsOrZero$default(View view, int i, WindowInsets windowInsets, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            windowInsets = view.getRootWindowInsets();
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return resolveInsetsOrZero(view, i, windowInsets, z);
    }

    public static final Insets resolveInsetsOrZero(View view, int i, WindowInsets windowInsets, boolean z) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        if (windowInsets == null) {
            Insets insets = Insets.NONE;
            Intrinsics.checkNotNullExpressionValue(insets, "NONE");
            return insets;
        }
        WindowInsetsCompat windowInsetsCompat = WindowInsetsCompat.toWindowInsetsCompat(windowInsets, view);
        Intrinsics.checkNotNullExpressionValue(windowInsetsCompat, "toWindowInsetsCompat(...)");
        if (!z) {
            Insets insets2 = windowInsetsCompat.getInsets(i);
            Intrinsics.checkNotNull(insets2);
            return insets2;
        }
        Insets insetsIgnoringVisibility = windowInsetsCompat.getInsetsIgnoringVisibility(i);
        Intrinsics.checkNotNull(insetsIgnoringVisibility);
        return insetsIgnoringVisibility;
    }
}
