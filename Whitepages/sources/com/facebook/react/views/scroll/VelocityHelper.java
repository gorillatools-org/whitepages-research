package com.facebook.react.views.scroll;

import android.view.MotionEvent;
import android.view.VelocityTracker;
import kotlin.jvm.internal.Intrinsics;

public final class VelocityHelper {
    private VelocityTracker velocityTracker;
    private float xVelocity;
    private float yVelocity;

    public final float getXVelocity() {
        return this.xVelocity;
    }

    public final float getYVelocity() {
        return this.yVelocity;
    }

    public final void calculateVelocity(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "ev");
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        VelocityTracker velocityTracker2 = this.velocityTracker;
        if (velocityTracker2 != null) {
            velocityTracker2.addMovement(motionEvent);
            int action = motionEvent.getAction() & 255;
            if (action == 1 || action == 3) {
                velocityTracker2.computeCurrentVelocity(1);
                this.xVelocity = velocityTracker2.getXVelocity();
                this.yVelocity = velocityTracker2.getYVelocity();
                velocityTracker2.recycle();
                this.velocityTracker = null;
            }
        }
    }
}
