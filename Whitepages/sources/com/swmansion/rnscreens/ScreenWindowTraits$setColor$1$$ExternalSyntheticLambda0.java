package com.swmansion.rnscreens;

import android.animation.ValueAnimator;
import android.view.Window;

public final /* synthetic */ class ScreenWindowTraits$setColor$1$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ Window f$0;

    public /* synthetic */ ScreenWindowTraits$setColor$1$$ExternalSyntheticLambda0(Window window) {
        this.f$0 = window;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        ScreenWindowTraits$setColor$1.runGuarded$lambda$0(this.f$0, valueAnimator);
    }
}
