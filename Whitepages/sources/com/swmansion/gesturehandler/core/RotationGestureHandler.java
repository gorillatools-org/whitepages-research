package com.swmansion.gesturehandler.core;

import android.graphics.PointF;
import android.view.MotionEvent;
import com.swmansion.gesturehandler.core.RotationGestureDetector;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class RotationGestureHandler extends GestureHandler {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private float anchorX = Float.NaN;
    private float anchorY = Float.NaN;
    private final RotationGestureDetector.OnRotationGestureListener gestureListener;
    /* access modifiers changed from: private */
    public double rotation;
    private RotationGestureDetector rotationGestureDetector;
    /* access modifiers changed from: private */
    public double velocity;

    public RotationGestureHandler() {
        setShouldCancelWhenOutside(false);
        this.gestureListener = new RotationGestureHandler$gestureListener$1(this);
    }

    public final double getRotation() {
        return this.rotation;
    }

    public final double getVelocity() {
        return this.velocity;
    }

    public final float getAnchorX() {
        return this.anchorX;
    }

    public final float getAnchorY() {
        return this.anchorY;
    }

    /* access modifiers changed from: protected */
    public void onHandle(MotionEvent motionEvent, MotionEvent motionEvent2) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        Intrinsics.checkNotNullParameter(motionEvent2, "sourceEvent");
        if (getState() == 0) {
            resetProgress();
            this.rotationGestureDetector = new RotationGestureDetector(this.gestureListener);
            this.anchorX = motionEvent.getX();
            this.anchorY = motionEvent.getY();
            begin();
        }
        RotationGestureDetector rotationGestureDetector2 = this.rotationGestureDetector;
        if (rotationGestureDetector2 != null) {
            rotationGestureDetector2.onTouchEvent(motionEvent2);
        }
        RotationGestureDetector rotationGestureDetector3 = this.rotationGestureDetector;
        if (rotationGestureDetector3 != null) {
            PointF transformPoint = transformPoint(new PointF(rotationGestureDetector3.getAnchorX(), rotationGestureDetector3.getAnchorY()));
            this.anchorX = transformPoint.x;
            this.anchorY = transformPoint.y;
        }
        if (motionEvent2.getActionMasked() != 1) {
            return;
        }
        if (getState() == 4) {
            end();
        } else {
            fail();
        }
    }

    public void activate(boolean z) {
        if (getState() != 4) {
            resetProgress();
        }
        super.activate(z);
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        this.rotationGestureDetector = null;
        this.anchorX = Float.NaN;
        this.anchorY = Float.NaN;
        resetProgress();
    }

    public void resetProgress() {
        this.velocity = 0.0d;
        this.rotation = 0.0d;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
