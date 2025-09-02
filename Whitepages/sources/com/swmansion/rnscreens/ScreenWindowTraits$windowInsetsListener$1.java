package com.swmansion.rnscreens;

import android.os.Build;
import android.view.View;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import kotlin.jvm.internal.Intrinsics;

public final class ScreenWindowTraits$windowInsetsListener$1 implements OnApplyWindowInsetsListener {
    ScreenWindowTraits$windowInsetsListener$1() {
    }

    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(windowInsetsCompat, "insets");
        WindowInsetsCompat onApplyWindowInsets = ViewCompat.onApplyWindowInsets(view, windowInsetsCompat);
        Intrinsics.checkNotNullExpressionValue(onApplyWindowInsets, "onApplyWindowInsets(...)");
        if (Build.VERSION.SDK_INT >= 30) {
            Insets insets = onApplyWindowInsets.getInsets(WindowInsetsCompat.Type.statusBars());
            Intrinsics.checkNotNullExpressionValue(insets, "getInsets(...)");
            WindowInsetsCompat build = new WindowInsetsCompat.Builder().setInsets(WindowInsetsCompat.Type.statusBars(), Insets.of(insets.left, 0, insets.right, insets.bottom)).build();
            Intrinsics.checkNotNullExpressionValue(build, "build(...)");
            return build;
        }
        WindowInsetsCompat replaceSystemWindowInsets = onApplyWindowInsets.replaceSystemWindowInsets(onApplyWindowInsets.getSystemWindowInsetLeft(), 0, onApplyWindowInsets.getSystemWindowInsetRight(), onApplyWindowInsets.getSystemWindowInsetBottom());
        Intrinsics.checkNotNullExpressionValue(replaceSystemWindowInsets, "replaceSystemWindowInsets(...)");
        return replaceSystemWindowInsets;
    }
}
