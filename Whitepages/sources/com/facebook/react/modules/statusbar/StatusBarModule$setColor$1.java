package com.facebook.react.modules.statusbar;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.view.Window;
import androidx.customview.widget.ExploreByTouchHelper;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import kotlin.jvm.internal.Intrinsics;

public final class StatusBarModule$setColor$1 extends GuardedRunnable {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ boolean $animated;
    final /* synthetic */ int $color;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StatusBarModule$setColor$1(Activity activity, boolean z, int i, ReactApplicationContext reactApplicationContext) {
        super((ReactContext) reactApplicationContext);
        this.$activity = activity;
        this.$animated = z;
        this.$color = i;
    }

    public void runGuarded() {
        Window window = this.$activity.getWindow();
        if (window != null) {
            window.addFlags(ExploreByTouchHelper.INVALID_ID);
            if (this.$animated) {
                ValueAnimator ofObject = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(window.getStatusBarColor()), Integer.valueOf(this.$color)});
                ofObject.addUpdateListener(new StatusBarModule$setColor$1$$ExternalSyntheticLambda0(this.$activity));
                ofObject.setDuration(300).setStartDelay(0);
                ofObject.start();
                return;
            }
            window.setStatusBarColor(this.$color);
        }
    }

    /* access modifiers changed from: private */
    public static final void runGuarded$lambda$0(Activity activity, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(valueAnimator, "animator");
        Window window = activity.getWindow();
        if (window != null) {
            Object animatedValue = valueAnimator.getAnimatedValue();
            Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
            window.setStatusBarColor(((Integer) animatedValue).intValue());
        }
    }
}
