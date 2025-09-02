package com.swmansion.rnscreens;

import android.view.animation.Animation;
import com.swmansion.rnscreens.ScreenStackFragment;
import kotlin.jvm.internal.Intrinsics;

public final class ScreenStackFragment$ScreensCoordinatorLayout$animationListener$1 implements Animation.AnimationListener {
    final /* synthetic */ ScreenStackFragment.ScreensCoordinatorLayout this$0;

    public void onAnimationRepeat(Animation animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
    }

    ScreenStackFragment$ScreensCoordinatorLayout$animationListener$1(ScreenStackFragment.ScreensCoordinatorLayout screensCoordinatorLayout) {
        this.this$0 = screensCoordinatorLayout;
    }

    public void onAnimationStart(Animation animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        this.this$0.fragment.onViewAnimationStart();
    }

    public void onAnimationEnd(Animation animation) {
        Intrinsics.checkNotNullParameter(animation, "animation");
        this.this$0.fragment.onViewAnimationEnd();
    }
}
