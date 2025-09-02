package com.swmansion.gesturehandler.core;

import android.view.MotionEvent;
import kotlin.jvm.internal.Intrinsics;

public final class RotationGestureDetector {
    private float anchorX;
    private float anchorY;
    private long currentTime;
    private final OnRotationGestureListener gestureListener;
    private boolean isInProgress;
    private boolean isPaused;
    private final int[] pointerIds = new int[2];
    private double previousAngle;
    private long previousTime;
    private double rotation;

    public interface OnRotationGestureListener {
        boolean onRotation(RotationGestureDetector rotationGestureDetector);

        boolean onRotationBegin(RotationGestureDetector rotationGestureDetector);

        void onRotationEnd(RotationGestureDetector rotationGestureDetector);
    }

    public RotationGestureDetector(OnRotationGestureListener onRotationGestureListener) {
        this.gestureListener = onRotationGestureListener;
    }

    public final double getRotation() {
        return this.rotation;
    }

    public final float getAnchorX() {
        return this.anchorX;
    }

    public final float getAnchorY() {
        return this.anchorY;
    }

    public final long getTimeDelta() {
        return this.currentTime - this.previousTime;
    }

    private final void updateCurrent(MotionEvent motionEvent) {
        double d;
        this.previousTime = this.currentTime;
        this.currentTime = motionEvent.getEventTime();
        int findPointerIndex = motionEvent.findPointerIndex(this.pointerIds[0]);
        int findPointerIndex2 = motionEvent.findPointerIndex(this.pointerIds[1]);
        if (findPointerIndex != -1 && findPointerIndex2 != -1) {
            float x = motionEvent.getX(findPointerIndex);
            float y = motionEvent.getY(findPointerIndex);
            float x2 = motionEvent.getX(findPointerIndex2);
            float y2 = motionEvent.getY(findPointerIndex2);
            float f = y2 - y;
            this.anchorX = (x + x2) * 0.5f;
            this.anchorY = (y + y2) * 0.5f;
            double d2 = -Math.atan2((double) f, (double) (x2 - x));
            tryUnpause(d2);
            if (Double.isNaN(this.previousAngle)) {
                d = 0.0d;
            } else {
                d = this.previousAngle - d2;
            }
            this.rotation = d;
            this.previousAngle = d2;
            if (d > 3.141592653589793d) {
                this.rotation = d - 3.141592653589793d;
            } else if (d < -3.141592653589793d) {
                this.rotation = d + 3.141592653589793d;
            }
            double d3 = this.rotation;
            if (d3 > 1.5707963267948966d) {
                this.rotation = d3 - 3.141592653589793d;
            } else if (d3 < -1.5707963267948966d) {
                this.rotation = d3 + 3.141592653589793d;
            }
        }
    }

    private final void tryPause() {
        if (!this.isPaused) {
            this.isPaused = true;
        }
    }

    private final void tryUnpause(double d) {
        if (this.isPaused) {
            this.previousAngle = d;
            this.isPaused = false;
        }
    }

    private final void finish() {
        if (this.isInProgress) {
            this.isPaused = false;
            this.isInProgress = false;
            OnRotationGestureListener onRotationGestureListener = this.gestureListener;
            if (onRotationGestureListener != null) {
                onRotationGestureListener.onRotationEnd(this);
            }
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        OnRotationGestureListener onRotationGestureListener;
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.isInProgress = false;
            this.pointerIds[0] = motionEvent.getPointerId(motionEvent.getActionIndex());
            this.pointerIds[1] = -1;
        } else if (actionMasked == 1) {
            finish();
        } else if (actionMasked != 2) {
            if (actionMasked == 5) {
                if (!this.isInProgress || this.isPaused) {
                    this.pointerIds[1] = motionEvent.getPointerId(motionEvent.getActionIndex());
                    updateCurrent(motionEvent);
                }
                if (!this.isInProgress) {
                    this.isInProgress = true;
                    this.previousTime = motionEvent.getEventTime();
                    this.previousAngle = Double.NaN;
                    OnRotationGestureListener onRotationGestureListener2 = this.gestureListener;
                    if (onRotationGestureListener2 != null) {
                        onRotationGestureListener2.onRotationBegin(this);
                    }
                }
            } else if (actionMasked == 6 && this.isInProgress) {
                int pointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
                int[] iArr = this.pointerIds;
                if (pointerId == iArr[0]) {
                    iArr[0] = iArr[1];
                    iArr[1] = -1;
                    tryPause();
                } else if (pointerId == iArr[1]) {
                    iArr[1] = -1;
                    tryPause();
                }
            }
        } else if (this.isInProgress) {
            updateCurrent(motionEvent);
            if (!this.isPaused && (onRotationGestureListener = this.gestureListener) != null) {
                onRotationGestureListener.onRotation(this);
            }
        }
        return true;
    }
}
