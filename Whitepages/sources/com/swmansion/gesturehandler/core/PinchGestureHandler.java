package com.swmansion.gesturehandler.core;

import android.content.Context;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.swmansion.gesturehandler.core.ScaleGestureDetector;
import kotlin.jvm.internal.Intrinsics;

public final class PinchGestureHandler extends GestureHandler {
    private float focalPointX = Float.NaN;
    private float focalPointY = Float.NaN;
    private final ScaleGestureDetector.OnScaleGestureListener gestureListener = new PinchGestureHandler$gestureListener$1(this);
    /* access modifiers changed from: private */
    public double scale;
    private ScaleGestureDetector scaleGestureDetector;
    /* access modifiers changed from: private */
    public float spanSlop;
    /* access modifiers changed from: private */
    public float startingSpan;
    /* access modifiers changed from: private */
    public double velocity;

    public final double getScale() {
        return this.scale;
    }

    public final double getVelocity() {
        return this.velocity;
    }

    public final float getFocalPointX() {
        return this.focalPointX;
    }

    public final float getFocalPointY() {
        return this.focalPointY;
    }

    /* access modifiers changed from: protected */
    public void onHandle(MotionEvent motionEvent, MotionEvent motionEvent2) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        Intrinsics.checkNotNullParameter(motionEvent2, "sourceEvent");
        if (getState() == 0) {
            View view = getView();
            Intrinsics.checkNotNull(view);
            Context context = view.getContext();
            resetProgress();
            this.scaleGestureDetector = new ScaleGestureDetector(context, this.gestureListener);
            this.spanSlop = (float) ViewConfiguration.get(context).getScaledTouchSlop();
            this.focalPointX = motionEvent.getX();
            this.focalPointY = motionEvent.getY();
            begin();
        }
        ScaleGestureDetector scaleGestureDetector2 = this.scaleGestureDetector;
        if (scaleGestureDetector2 != null) {
            scaleGestureDetector2.onTouchEvent(motionEvent2);
        }
        ScaleGestureDetector scaleGestureDetector3 = this.scaleGestureDetector;
        if (scaleGestureDetector3 != null) {
            PointF transformPoint = transformPoint(new PointF(scaleGestureDetector3.getFocusX(), scaleGestureDetector3.getFocusY()));
            this.focalPointX = transformPoint.x;
            this.focalPointY = transformPoint.y;
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
        this.scaleGestureDetector = null;
        this.focalPointX = Float.NaN;
        this.focalPointY = Float.NaN;
        resetProgress();
    }

    public void resetProgress() {
        this.velocity = 0.0d;
        this.scale = 1.0d;
    }
}
