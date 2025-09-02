package com.facebook.react.modules.statusbar;

import android.animation.ValueAnimator;
import android.app.Activity;

public final /* synthetic */ class StatusBarModule$setColor$1$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ Activity f$0;

    public /* synthetic */ StatusBarModule$setColor$1$$ExternalSyntheticLambda0(Activity activity) {
        this.f$0 = activity;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        StatusBarModule$setColor$1.runGuarded$lambda$0(this.f$0, valueAnimator);
    }
}
