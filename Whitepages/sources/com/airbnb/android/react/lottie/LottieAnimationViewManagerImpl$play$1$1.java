package com.airbnb.android.react.lottie;

import android.view.View;
import com.airbnb.lottie.LottieAnimationView;
import kotlin.jvm.internal.Intrinsics;

public final class LottieAnimationViewManagerImpl$play$1$1 implements View.OnAttachStateChangeListener {
    final /* synthetic */ LottieAnimationView $view;
    final /* synthetic */ boolean $withCustomFrames;

    LottieAnimationViewManagerImpl$play$1$1(boolean z, LottieAnimationView lottieAnimationView) {
        this.$withCustomFrames = z;
        this.$view = lottieAnimationView;
    }

    public void onViewAttachedToWindow(View view) {
        Intrinsics.checkNotNullParameter(view, "v");
        LottieAnimationView lottieAnimationView = (LottieAnimationView) view;
        if (this.$withCustomFrames) {
            this.$view.playAnimation();
        } else {
            this.$view.resumeAnimation();
        }
        lottieAnimationView.removeOnAttachStateChangeListener(this);
    }

    public void onViewDetachedFromWindow(View view) {
        Intrinsics.checkNotNullParameter(view, "v");
        ((LottieAnimationView) view).removeOnAttachStateChangeListener(this);
    }
}
