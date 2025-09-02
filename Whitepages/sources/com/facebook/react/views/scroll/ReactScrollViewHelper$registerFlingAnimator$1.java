package com.facebook.react.views.scroll;

import android.animation.Animator;
import com.facebook.react.views.scroll.ReactScrollViewHelper;
import kotlin.jvm.internal.Intrinsics;

public final class ReactScrollViewHelper$registerFlingAnimator$1 implements Animator.AnimatorListener {
    final /* synthetic */ T $scrollView;

    public void onAnimationRepeat(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animator");
    }

    ReactScrollViewHelper$registerFlingAnimator$1(T t) {
        this.$scrollView = t;
    }

    public void onAnimationStart(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animator");
        ReactScrollViewHelper.ReactScrollViewScrollState reactScrollViewScrollState = ((ReactScrollViewHelper.HasScrollState) this.$scrollView).getReactScrollViewScrollState();
        reactScrollViewScrollState.setCanceled(false);
        reactScrollViewScrollState.setFinished(false);
    }

    public void onAnimationEnd(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animator");
        ((ReactScrollViewHelper.HasScrollState) this.$scrollView).getReactScrollViewScrollState().setFinished(true);
        ReactScrollViewHelper.updateFabricScrollState(this.$scrollView);
    }

    public void onAnimationCancel(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animator");
        ((ReactScrollViewHelper.HasScrollState) this.$scrollView).getReactScrollViewScrollState().setCanceled(true);
    }
}
