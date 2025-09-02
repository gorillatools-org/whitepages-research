package com.swmansion.rnscreens;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.view.Window;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.JSExceptionHandler;
import kotlin.jvm.internal.Intrinsics;

public final class ScreenWindowTraits$setColor$1 extends GuardedRunnable {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ boolean $animated;
    final /* synthetic */ Integer $color;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ScreenWindowTraits$setColor$1(Activity activity, Integer num, boolean z, JSExceptionHandler jSExceptionHandler) {
        super(jSExceptionHandler);
        this.$activity = activity;
        this.$color = num;
        this.$animated = z;
    }

    public void runGuarded() {
        Window window = this.$activity.getWindow();
        ValueAnimator ofObject = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(window.getStatusBarColor()), this.$color});
        ofObject.addUpdateListener(new ScreenWindowTraits$setColor$1$$ExternalSyntheticLambda0(window));
        if (this.$animated) {
            ofObject.setDuration(300).setStartDelay(0);
        } else {
            ofObject.setDuration(0).setStartDelay(300);
        }
        ofObject.start();
    }

    /* access modifiers changed from: private */
    public static final void runGuarded$lambda$0(Window window, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(valueAnimator, "animator");
        Object animatedValue = valueAnimator.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        window.setStatusBarColor(((Integer) animatedValue).intValue());
    }
}
