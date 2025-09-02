package com.facebook.react.views.safeareaview;

import android.view.View;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.WindowInsetsCompat;

public final /* synthetic */ class ReactSafeAreaView$$ExternalSyntheticLambda0 implements OnApplyWindowInsetsListener {
    public final /* synthetic */ ReactSafeAreaView f$0;

    public /* synthetic */ ReactSafeAreaView$$ExternalSyntheticLambda0(ReactSafeAreaView reactSafeAreaView) {
        this.f$0 = reactSafeAreaView;
    }

    public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        return ReactSafeAreaView.onAttachedToWindow$lambda$0(this.f$0, view, windowInsetsCompat);
    }
}
