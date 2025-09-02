package com.salesforce.marketingcloud.messages.iam;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.core.view.DisplayCutoutCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.salesforce.marketingcloud.R;
import kotlin.jvm.internal.Intrinsics;

public final class IamFullImageFillActivity extends IamFullscreenActivity implements OnApplyWindowInsetsListener {
    private final void h() {
        requestWindowFeature(1);
        getWindow().setFlags(1536, 1536);
        getWindow().getDecorView().setSystemUiVisibility(4098);
        if (Build.VERSION.SDK_INT >= 28) {
            getWindow().getAttributes().layoutInDisplayCutoutMode = 1;
        }
    }

    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        DisplayCutoutCompat displayCutout;
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(windowInsetsCompat, "insets");
        if (!isFinishing() && windowInsetsCompat.hasInsets() && (displayCutout = windowInsetsCompat.getDisplayCutout()) != null) {
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.mcsdk_iam_fif_content_padding_top);
            int safeInsetTop = displayCutout.getSafeInsetTop();
            int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.mcsdk_iam_fif_content_padding_bottom);
            int safeInsetBottom = displayCutout.getSafeInsetBottom();
            View findViewById = view.findViewById(R.id.mcsdk_iam_container);
            if (safeInsetTop >= dimensionPixelSize) {
                dimensionPixelSize = safeInsetTop;
            }
            if (safeInsetBottom >= dimensionPixelSize2) {
                dimensionPixelSize2 = safeInsetBottom;
            }
            findViewById.setPadding(0, dimensionPixelSize, 0, dimensionPixelSize2);
        }
        WindowInsetsCompat consumeSystemWindowInsets = windowInsetsCompat.consumeSystemWindowInsets();
        Intrinsics.checkNotNullExpressionValue(consumeSystemWindowInsets, "consumeSystemWindowInsets(...)");
        return consumeSystemWindowInsets;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        h();
        super.onCreate(bundle);
        View view = this.f;
        if (view != null) {
            ViewCompat.setOnApplyWindowInsetsListener(view, this);
        }
    }
}
