package com.facebook.react.views.scroll;

import android.animation.Animator;
import kotlin.jvm.internal.Intrinsics;

public final class ReactScrollViewHelper$dispatchMomentumEndOnAnimationEnd$1 implements Animator.AnimatorListener {
    final /* synthetic */ T $scrollView;

    public void onAnimationRepeat(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animator");
    }

    public void onAnimationStart(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animator");
    }

    ReactScrollViewHelper$dispatchMomentumEndOnAnimationEnd$1(T t) {
        this.$scrollView = t;
    }

    public void onAnimationEnd(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animator");
        ReactScrollViewHelper.emitScrollMomentumEndEvent(this.$scrollView);
        animator.removeListener(this);
    }

    public void onAnimationCancel(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animator");
        ReactScrollViewHelper.emitScrollMomentumEndEvent(this.$scrollView);
        animator.removeListener(this);
    }
}
