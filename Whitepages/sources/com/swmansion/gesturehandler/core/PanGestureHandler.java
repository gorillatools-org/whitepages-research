package com.swmansion.gesturehandler.core;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class PanGestureHandler extends GestureHandler {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private long activateAfterLongPress;
    private final Runnable activateDelayed = new PanGestureHandler$$ExternalSyntheticLambda0(this);
    private float activeOffsetXEnd = Float.MIN_VALUE;
    private float activeOffsetXStart = Float.MAX_VALUE;
    private float activeOffsetYEnd = Float.MIN_VALUE;
    private float activeOffsetYStart = Float.MAX_VALUE;
    private boolean averageTouches;
    private final float defaultMinDistSq;
    private float failOffsetXEnd = Float.MAX_VALUE;
    private float failOffsetXStart = Float.MIN_VALUE;
    private float failOffsetYEnd = Float.MAX_VALUE;
    private float failOffsetYStart = Float.MIN_VALUE;
    private Handler handler;
    private float lastX;
    private float lastY;
    private int maxPointers = 10;
    private float minDistSq = Float.MIN_VALUE;
    private int minPointers = 1;
    private float minVelocitySq = Float.MAX_VALUE;
    private float minVelocityX = Float.MAX_VALUE;
    private float minVelocityY = Float.MAX_VALUE;
    private float offsetX;
    private float offsetY;
    private float startX;
    private float startY;
    private StylusData stylusData = new StylusData(0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 31, (DefaultConstructorMarker) null);
    private VelocityTracker velocityTracker;
    private float velocityX;
    private float velocityY;

    public PanGestureHandler(Context context) {
        Intrinsics.checkNotNull(context);
        int scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        float f = (float) (scaledTouchSlop * scaledTouchSlop);
        this.defaultMinDistSq = f;
        this.minDistSq = f;
    }

    public final float getVelocityX() {
        return this.velocityX;
    }

    public final float getVelocityY() {
        return this.velocityY;
    }

    public final float getTranslationX() {
        return (this.lastX - this.startX) + this.offsetX;
    }

    public final float getTranslationY() {
        return (this.lastY - this.startY) + this.offsetY;
    }

    /* access modifiers changed from: private */
    public static final void activateDelayed$lambda$0(PanGestureHandler panGestureHandler) {
        panGestureHandler.activate();
    }

    public final StylusData getStylusData() {
        return this.stylusData;
    }

    public void resetConfig() {
        super.resetConfig();
        this.activeOffsetXStart = Float.MAX_VALUE;
        this.activeOffsetXEnd = Float.MIN_VALUE;
        this.failOffsetXStart = Float.MIN_VALUE;
        this.failOffsetXEnd = Float.MAX_VALUE;
        this.activeOffsetYStart = Float.MAX_VALUE;
        this.activeOffsetYEnd = Float.MIN_VALUE;
        this.failOffsetYStart = Float.MIN_VALUE;
        this.failOffsetYEnd = Float.MAX_VALUE;
        this.minVelocityX = Float.MAX_VALUE;
        this.minVelocityY = Float.MAX_VALUE;
        this.minVelocitySq = Float.MAX_VALUE;
        this.minDistSq = this.defaultMinDistSq;
        this.minPointers = 1;
        this.maxPointers = 10;
        this.activateAfterLongPress = 0;
        this.averageTouches = false;
    }

    public final PanGestureHandler setActiveOffsetXStart(float f) {
        this.activeOffsetXStart = f;
        return this;
    }

    public final PanGestureHandler setActiveOffsetXEnd(float f) {
        this.activeOffsetXEnd = f;
        return this;
    }

    public final PanGestureHandler setFailOffsetXStart(float f) {
        this.failOffsetXStart = f;
        return this;
    }

    public final PanGestureHandler setFailOffsetXEnd(float f) {
        this.failOffsetXEnd = f;
        return this;
    }

    public final PanGestureHandler setActiveOffsetYStart(float f) {
        this.activeOffsetYStart = f;
        return this;
    }

    public final PanGestureHandler setActiveOffsetYEnd(float f) {
        this.activeOffsetYEnd = f;
        return this;
    }

    public final PanGestureHandler setFailOffsetYStart(float f) {
        this.failOffsetYStart = f;
        return this;
    }

    public final PanGestureHandler setFailOffsetYEnd(float f) {
        this.failOffsetYEnd = f;
        return this;
    }

    public final PanGestureHandler setMinDist(float f) {
        this.minDistSq = f * f;
        return this;
    }

    public final PanGestureHandler setMinPointers(int i) {
        this.minPointers = i;
        return this;
    }

    public final PanGestureHandler setMaxPointers(int i) {
        this.maxPointers = i;
        return this;
    }

    public final PanGestureHandler setAverageTouches(boolean z) {
        this.averageTouches = z;
        return this;
    }

    public final PanGestureHandler setActivateAfterLongPress(long j) {
        this.activateAfterLongPress = j;
        return this;
    }

    public final PanGestureHandler setMinVelocity(float f) {
        this.minVelocitySq = f * f;
        return this;
    }

    public final PanGestureHandler setMinVelocityX(float f) {
        this.minVelocityX = f;
        return this;
    }

    public final PanGestureHandler setMinVelocityY(float f) {
        this.minVelocityY = f;
        return this;
    }

    private final boolean shouldActivate() {
        float f = (this.lastX - this.startX) + this.offsetX;
        float f2 = this.activeOffsetXStart;
        if (f2 != Float.MAX_VALUE && f < f2) {
            return true;
        }
        float f3 = this.activeOffsetXEnd;
        if (f3 != Float.MIN_VALUE && f > f3) {
            return true;
        }
        float f4 = (this.lastY - this.startY) + this.offsetY;
        float f5 = this.activeOffsetYStart;
        if (f5 != Float.MAX_VALUE && f4 < f5) {
            return true;
        }
        float f6 = this.activeOffsetYEnd;
        if (f6 != Float.MIN_VALUE && f4 > f6) {
            return true;
        }
        float f7 = (f * f) + (f4 * f4);
        float f8 = this.minDistSq;
        if (f8 != Float.MAX_VALUE && f7 >= f8) {
            return true;
        }
        float f9 = this.velocityX;
        float f10 = this.minVelocityX;
        if (f10 != Float.MAX_VALUE && ((f10 < 0.0f && f9 <= f10) || (0.0f <= f10 && f10 <= f9))) {
            return true;
        }
        float f11 = this.velocityY;
        float f12 = this.minVelocityY;
        if (f12 != Float.MAX_VALUE && ((f12 < 0.0f && f9 <= f12) || (0.0f <= f12 && f12 <= f9))) {
            return true;
        }
        float f13 = (f9 * f9) + (f11 * f11);
        float f14 = this.minVelocitySq;
        if (f14 != Float.MAX_VALUE && f13 >= f14) {
            return true;
        }
        return false;
    }

    private final boolean shouldFail() {
        float f = (this.lastX - this.startX) + this.offsetX;
        float f2 = (this.lastY - this.startY) + this.offsetY;
        if (this.activateAfterLongPress <= 0 || (f * f) + (f2 * f2) <= this.defaultMinDistSq) {
            float f3 = this.failOffsetXStart;
            if (f3 != Float.MIN_VALUE && f < f3) {
                return true;
            }
            float f4 = this.failOffsetXEnd;
            if (f4 != Float.MAX_VALUE && f > f4) {
                return true;
            }
            float f5 = this.failOffsetYStart;
            if (f5 != Float.MIN_VALUE && f2 < f5) {
                return true;
            }
            float f6 = this.failOffsetYEnd;
            if (f6 != Float.MAX_VALUE && f2 > f6) {
                return true;
            }
            return false;
        }
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages((Object) null);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onHandle(MotionEvent motionEvent, MotionEvent motionEvent2) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        Intrinsics.checkNotNullParameter(motionEvent2, "sourceEvent");
        if (shouldActivateWithMouse(motionEvent2)) {
            if (motionEvent.getToolType(0) == 2) {
                this.stylusData = StylusData.Companion.fromEvent(motionEvent);
            }
            int state = getState();
            int actionMasked = motionEvent2.getActionMasked();
            if (actionMasked == 5 || actionMasked == 6) {
                this.offsetX += this.lastX - this.startX;
                this.offsetY += this.lastY - this.startY;
                GestureUtils gestureUtils = GestureUtils.INSTANCE;
                this.lastX = gestureUtils.getLastPointerX(motionEvent2, this.averageTouches);
                float lastPointerY = gestureUtils.getLastPointerY(motionEvent2, this.averageTouches);
                this.lastY = lastPointerY;
                this.startX = this.lastX;
                this.startY = lastPointerY;
            } else {
                GestureUtils gestureUtils2 = GestureUtils.INSTANCE;
                this.lastX = gestureUtils2.getLastPointerX(motionEvent2, this.averageTouches);
                this.lastY = gestureUtils2.getLastPointerY(motionEvent2, this.averageTouches);
            }
            if (state != 0 || motionEvent2.getPointerCount() < this.minPointers) {
                VelocityTracker velocityTracker2 = this.velocityTracker;
                if (velocityTracker2 != null) {
                    Companion.addVelocityMovement(velocityTracker2, motionEvent2);
                    VelocityTracker velocityTracker3 = this.velocityTracker;
                    Intrinsics.checkNotNull(velocityTracker3);
                    velocityTracker3.computeCurrentVelocity(1000);
                    VelocityTracker velocityTracker4 = this.velocityTracker;
                    Intrinsics.checkNotNull(velocityTracker4);
                    this.velocityX = velocityTracker4.getXVelocity();
                    VelocityTracker velocityTracker5 = this.velocityTracker;
                    Intrinsics.checkNotNull(velocityTracker5);
                    this.velocityY = velocityTracker5.getYVelocity();
                }
            } else {
                resetProgress();
                this.offsetX = 0.0f;
                this.offsetY = 0.0f;
                this.velocityX = 0.0f;
                this.velocityY = 0.0f;
                VelocityTracker obtain = VelocityTracker.obtain();
                this.velocityTracker = obtain;
                Companion.addVelocityMovement(obtain, motionEvent2);
                begin();
                if (this.activateAfterLongPress > 0) {
                    if (this.handler == null) {
                        this.handler = new Handler(Looper.getMainLooper());
                    }
                    Handler handler2 = this.handler;
                    Intrinsics.checkNotNull(handler2);
                    handler2.postDelayed(this.activateDelayed, this.activateAfterLongPress);
                }
            }
            if (actionMasked == 1 || actionMasked == 12) {
                if (state == 4) {
                    end();
                } else {
                    fail();
                }
            } else if (actionMasked != 5 || motionEvent2.getPointerCount() <= this.maxPointers) {
                if (actionMasked == 6 && state == 4 && motionEvent2.getPointerCount() < this.minPointers) {
                    fail();
                } else if (state != 2) {
                } else {
                    if (shouldFail()) {
                        fail();
                    } else if (shouldActivate()) {
                        activate();
                    }
                }
            } else if (state == 4) {
                cancel();
            } else {
                fail();
            }
        }
    }

    public void activate(boolean z) {
        if (getState() != 4) {
            resetProgress();
        }
        super.activate(z);
    }

    /* access modifiers changed from: protected */
    public void onCancel() {
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages((Object) null);
        }
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages((Object) null);
        }
        VelocityTracker velocityTracker2 = this.velocityTracker;
        if (velocityTracker2 != null) {
            velocityTracker2.recycle();
            this.velocityTracker = null;
        }
        this.stylusData = new StylusData(0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 31, (DefaultConstructorMarker) null);
    }

    public void resetProgress() {
        this.startX = this.lastX;
        this.startY = this.lastY;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final void addVelocityMovement(VelocityTracker velocityTracker, MotionEvent motionEvent) {
            float rawX = motionEvent.getRawX() - motionEvent.getX();
            float rawY = motionEvent.getRawY() - motionEvent.getY();
            motionEvent.offsetLocation(rawX, rawY);
            Intrinsics.checkNotNull(velocityTracker);
            velocityTracker.addMovement(motionEvent);
            motionEvent.offsetLocation(-rawX, -rawY);
        }
    }
}
