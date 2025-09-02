package com.swmansion.gesturehandler.core;

import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class TapGestureHandler extends GestureHandler {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private int currentMaxNumberOfPointers = 1;
    private final Runnable failDelayed = new TapGestureHandler$$ExternalSyntheticLambda0(this);
    private Handler handler;
    private float lastX;
    private float lastY;
    private long maxDelayMs = 200;
    private float maxDeltaX = Float.MIN_VALUE;
    private float maxDeltaY = Float.MIN_VALUE;
    private float maxDistSq = Float.MIN_VALUE;
    private long maxDurationMs = 500;
    private int minNumberOfPointers = 1;
    private int numberOfTaps = 1;
    private float offsetX;
    private float offsetY;
    private float startX;
    private float startY;
    private int tapsSoFar;

    public TapGestureHandler() {
        setShouldCancelWhenOutside(true);
    }

    /* access modifiers changed from: private */
    public static final void failDelayed$lambda$0(TapGestureHandler tapGestureHandler) {
        tapGestureHandler.fail();
    }

    public void resetConfig() {
        super.resetConfig();
        this.maxDeltaX = Float.MIN_VALUE;
        this.maxDeltaY = Float.MIN_VALUE;
        this.maxDistSq = Float.MIN_VALUE;
        this.maxDurationMs = 500;
        this.maxDelayMs = 200;
        this.numberOfTaps = 1;
        this.minNumberOfPointers = 1;
    }

    public final TapGestureHandler setNumberOfTaps(int i) {
        this.numberOfTaps = i;
        return this;
    }

    public final TapGestureHandler setMaxDelayMs(long j) {
        this.maxDelayMs = j;
        return this;
    }

    public final TapGestureHandler setMaxDurationMs(long j) {
        this.maxDurationMs = j;
        return this;
    }

    public final TapGestureHandler setMaxDx(float f) {
        this.maxDeltaX = f;
        return this;
    }

    public final TapGestureHandler setMaxDy(float f) {
        this.maxDeltaY = f;
        return this;
    }

    public final TapGestureHandler setMaxDist(float f) {
        this.maxDistSq = f * f;
        return this;
    }

    public final TapGestureHandler setMinNumberOfPointers(int i) {
        this.minNumberOfPointers = i;
        return this;
    }

    private final void startTap() {
        Handler handler2 = this.handler;
        if (handler2 == null) {
            this.handler = new Handler(Looper.getMainLooper());
        } else {
            Intrinsics.checkNotNull(handler2);
            handler2.removeCallbacksAndMessages((Object) null);
        }
        Handler handler3 = this.handler;
        Intrinsics.checkNotNull(handler3);
        handler3.postDelayed(this.failDelayed, this.maxDurationMs);
    }

    private final void endTap() {
        Handler handler2 = this.handler;
        if (handler2 == null) {
            this.handler = new Handler(Looper.getMainLooper());
        } else {
            Intrinsics.checkNotNull(handler2);
            handler2.removeCallbacksAndMessages((Object) null);
        }
        int i = this.tapsSoFar + 1;
        this.tapsSoFar = i;
        if (i != this.numberOfTaps || this.currentMaxNumberOfPointers < this.minNumberOfPointers) {
            Handler handler3 = this.handler;
            Intrinsics.checkNotNull(handler3);
            handler3.postDelayed(this.failDelayed, this.maxDelayMs);
            return;
        }
        activate();
    }

    private final boolean shouldFail() {
        float f = (this.lastX - this.startX) + this.offsetX;
        if (this.maxDeltaX != Float.MIN_VALUE && Math.abs(f) > this.maxDeltaX) {
            return true;
        }
        float f2 = (this.lastY - this.startY) + this.offsetY;
        if (this.maxDeltaY != Float.MIN_VALUE && Math.abs(f2) > this.maxDeltaY) {
            return true;
        }
        float f3 = (f2 * f2) + (f * f);
        float f4 = this.maxDistSq;
        if (f4 != Float.MIN_VALUE && f3 > f4) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onHandle(MotionEvent motionEvent, MotionEvent motionEvent2) {
        Intrinsics.checkNotNullParameter(motionEvent, "event");
        Intrinsics.checkNotNullParameter(motionEvent2, "sourceEvent");
        if (shouldActivateWithMouse(motionEvent2)) {
            int state = getState();
            int actionMasked = motionEvent2.getActionMasked();
            if (state == 0) {
                this.offsetX = 0.0f;
                this.offsetY = 0.0f;
                GestureUtils gestureUtils = GestureUtils.INSTANCE;
                this.startX = gestureUtils.getLastPointerX(motionEvent2, true);
                this.startY = gestureUtils.getLastPointerY(motionEvent2, true);
            }
            if (actionMasked == 5 || actionMasked == 6) {
                this.offsetX += this.lastX - this.startX;
                this.offsetY += this.lastY - this.startY;
                GestureUtils gestureUtils2 = GestureUtils.INSTANCE;
                this.lastX = gestureUtils2.getLastPointerX(motionEvent2, true);
                float lastPointerY = gestureUtils2.getLastPointerY(motionEvent2, true);
                this.lastY = lastPointerY;
                this.startX = this.lastX;
                this.startY = lastPointerY;
            } else {
                GestureUtils gestureUtils3 = GestureUtils.INSTANCE;
                this.lastX = gestureUtils3.getLastPointerX(motionEvent2, true);
                this.lastY = gestureUtils3.getLastPointerY(motionEvent2, true);
            }
            if (this.currentMaxNumberOfPointers < motionEvent2.getPointerCount()) {
                this.currentMaxNumberOfPointers = motionEvent2.getPointerCount();
            }
            if (shouldFail()) {
                fail();
            } else if (state == 0) {
                if (actionMasked == 0 || actionMasked == 11) {
                    begin();
                }
                startTap();
            } else if (state == 2) {
                if (actionMasked != 0) {
                    if (actionMasked != 1) {
                        if (actionMasked != 11) {
                            if (actionMasked != 12) {
                                return;
                            }
                        }
                    }
                    endTap();
                    return;
                }
                startTap();
            }
        }
    }

    public void activate(boolean z) {
        super.activate(z);
        end();
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
        this.tapsSoFar = 0;
        this.currentMaxNumberOfPointers = 0;
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages((Object) null);
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
