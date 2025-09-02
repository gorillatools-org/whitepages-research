package com.swmansion.rnscreens.bottomsheet;

import android.animation.ValueAnimator;
import com.swmansion.rnscreens.bottomsheet.DimmingViewManager;

public final /* synthetic */ class DimmingViewManager$AnimateDimmingViewCallback$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ DimmingViewManager.AnimateDimmingViewCallback f$0;

    public /* synthetic */ DimmingViewManager$AnimateDimmingViewCallback$$ExternalSyntheticLambda0(DimmingViewManager.AnimateDimmingViewCallback animateDimmingViewCallback) {
        this.f$0 = animateDimmingViewCallback;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        DimmingViewManager.AnimateDimmingViewCallback.animator$lambda$1$lambda$0(this.f$0, valueAnimator);
    }
}
