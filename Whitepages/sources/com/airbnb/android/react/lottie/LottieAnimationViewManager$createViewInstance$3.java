package com.airbnb.android.react.lottie;

import android.animation.Animator;
import com.airbnb.lottie.LottieAnimationView;
import kotlin.jvm.internal.Intrinsics;

public final class LottieAnimationViewManager$createViewInstance$3 implements Animator.AnimatorListener {
    final /* synthetic */ LottieAnimationView $view;

    public void onAnimationRepeat(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animation");
    }

    public void onAnimationStart(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animation");
    }

    LottieAnimationViewManager$createViewInstance$3(LottieAnimationView lottieAnimationView) {
        this.$view = lottieAnimationView;
    }

    public void onAnimationEnd(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animation");
        LottieAnimationViewManagerImpl.sendOnAnimationFinishEvent(this.$view, false);
    }

    public void onAnimationCancel(Animator animator) {
        Intrinsics.checkNotNullParameter(animator, "animation");
        LottieAnimationViewManagerImpl.sendOnAnimationFinishEvent(this.$view, true);
    }
}
