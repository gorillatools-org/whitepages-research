package com.facebook.react.devsupport;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.facebook.react.R;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

public final class LogBoxDialog extends Dialog {
    private final View reactRootView;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LogBoxDialog(Activity activity, View view) {
        super(activity, R.style.Theme_Catalyst_LogBox);
        Intrinsics.checkNotNullParameter(activity, "context");
        this.reactRootView = view;
        requestWindowFeature(1);
        if (view != null) {
            setContentView(view);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(-16777216));
        }
        View view = this.reactRootView;
        if (view != null) {
            ViewCompat.setOnApplyWindowInsetsListener(view, new LogBoxDialog$$ExternalSyntheticLambda1(new LogBoxDialog$$ExternalSyntheticLambda0(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout())));
        }
    }

    /* access modifiers changed from: private */
    public static final WindowInsetsCompat onCreate$lambda$3$lambda$1(int i, View view, WindowInsetsCompat windowInsetsCompat) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(windowInsetsCompat, "windowInsets");
        Insets insets = windowInsetsCompat.getInsets(i);
        Intrinsics.checkNotNullExpressionValue(insets, "getInsets(...)");
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type android.widget.FrameLayout.LayoutParams");
        ((FrameLayout.LayoutParams) layoutParams).setMargins(insets.left, insets.top, insets.right, insets.bottom);
        return WindowInsetsCompat.CONSUMED;
    }

    /* access modifiers changed from: private */
    public static final WindowInsetsCompat onCreate$lambda$3$lambda$2(Function2 function2, View view, WindowInsetsCompat windowInsetsCompat) {
        Intrinsics.checkNotNullParameter(view, "p0");
        Intrinsics.checkNotNullParameter(windowInsetsCompat, "p1");
        return (WindowInsetsCompat) function2.invoke(view, windowInsetsCompat);
    }
}
