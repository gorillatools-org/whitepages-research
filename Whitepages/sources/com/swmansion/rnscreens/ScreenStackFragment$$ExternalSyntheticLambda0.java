package com.swmansion.rnscreens;

import android.animation.ValueAnimator;
import com.swmansion.rnscreens.bottomsheet.DimmingViewManager;

public final /* synthetic */ class ScreenStackFragment$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ DimmingViewManager f$0;

    public /* synthetic */ ScreenStackFragment$$ExternalSyntheticLambda0(DimmingViewManager dimmingViewManager) {
        this.f$0 = dimmingViewManager;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        ScreenStackFragment.onCreateAnimator$lambda$10$lambda$9(this.f$0, valueAnimator);
    }
}
