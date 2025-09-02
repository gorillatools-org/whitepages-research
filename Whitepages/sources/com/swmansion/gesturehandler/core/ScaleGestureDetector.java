package com.swmansion.gesturehandler.core;

import android.content.Context;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

public class ScaleGestureDetector {
    /* access modifiers changed from: private */
    public int mAnchoredScaleMode;
    /* access modifiers changed from: private */
    public float mAnchoredScaleStartX;
    /* access modifiers changed from: private */
    public float mAnchoredScaleStartY;
    private final Context mContext;
    private float mCurrSpan;
    private float mCurrSpanX;
    private float mCurrSpanY;
    private long mCurrTime;
    private boolean mEventBeforeOrAboveStartingGestureEvent;
    private float mFocusX;
    private float mFocusY;
    private GestureDetector mGestureDetector;
    private final Handler mHandler;
    private boolean mInProgress;
    private float mInitialSpan;
    private final OnScaleGestureListener mListener;
    private int mMinSpan;
    private float mPrevSpan;
    private float mPrevSpanX;
    private float mPrevSpanY;
    private long mPrevTime;
    private boolean mQuickScaleEnabled;
    private int mSpanSlop;
    private boolean mStylusScaleEnabled;

    public interface OnScaleGestureListener {
        boolean onScale(ScaleGestureDetector scaleGestureDetector);

        boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector);

        void onScaleEnd(ScaleGestureDetector scaleGestureDetector);
    }

    public ScaleGestureDetector(Context context, OnScaleGestureListener onScaleGestureListener) {
        this(context, onScaleGestureListener, (Handler) null);
    }

    public ScaleGestureDetector(Context context, OnScaleGestureListener onScaleGestureListener, Handler handler) {
        this.mAnchoredScaleMode = 0;
        this.mContext = context;
        this.mListener = onScaleGestureListener;
        this.mSpanSlop = ViewConfiguration.get(context).getScaledTouchSlop() * 2;
        this.mMinSpan = 0;
        this.mHandler = handler;
        int i = context.getApplicationInfo().targetSdkVersion;
        if (i > 18) {
            setQuickScaleEnabled(true);
        }
        if (i > 22) {
            setStylusScaleEnabled(true);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float f;
        float f2;
        float f3;
        MotionEvent motionEvent2 = motionEvent;
        this.mCurrTime = motionEvent.getEventTime();
        int actionMasked = motionEvent.getActionMasked();
        if (this.mQuickScaleEnabled) {
            this.mGestureDetector.onTouchEvent(motionEvent2);
        }
        int pointerCount = motionEvent.getPointerCount();
        boolean z = (motionEvent.getButtonState() & 32) != 0;
        boolean z2 = this.mAnchoredScaleMode == 2 && !z;
        boolean z3 = actionMasked == 1 || actionMasked == 3 || z2;
        float f4 = 0.0f;
        if (actionMasked == 0 || z3) {
            if (this.mInProgress) {
                this.mListener.onScaleEnd(this);
                this.mInProgress = false;
                this.mInitialSpan = 0.0f;
                this.mAnchoredScaleMode = 0;
            } else if (inAnchoredScaleMode() && z3) {
                this.mInProgress = false;
                this.mInitialSpan = 0.0f;
                this.mAnchoredScaleMode = 0;
            }
            if (z3) {
                return true;
            }
        }
        if (!this.mInProgress && this.mStylusScaleEnabled && !inAnchoredScaleMode() && !z3 && z) {
            this.mAnchoredScaleStartX = motionEvent.getX();
            this.mAnchoredScaleStartY = motionEvent.getY();
            this.mAnchoredScaleMode = 2;
            this.mInitialSpan = 0.0f;
        }
        boolean z4 = actionMasked == 0 || actionMasked == 6 || actionMasked == 5 || z2;
        boolean z5 = actionMasked == 6;
        int actionIndex = z5 ? motionEvent.getActionIndex() : -1;
        int i = z5 ? pointerCount - 1 : pointerCount;
        if (inAnchoredScaleMode()) {
            f2 = this.mAnchoredScaleStartX;
            f = this.mAnchoredScaleStartY;
            if (motionEvent.getY() < f) {
                this.mEventBeforeOrAboveStartingGestureEvent = true;
            } else {
                this.mEventBeforeOrAboveStartingGestureEvent = false;
            }
        } else {
            float f5 = 0.0f;
            float f6 = 0.0f;
            for (int i2 = 0; i2 < pointerCount; i2++) {
                if (actionIndex != i2) {
                    f5 += motionEvent2.getX(i2);
                    f6 += motionEvent2.getY(i2);
                }
            }
            float f7 = (float) i;
            float f8 = f5 / f7;
            f = f6 / f7;
            f2 = f8;
        }
        float f9 = 0.0f;
        for (int i3 = 0; i3 < pointerCount; i3++) {
            if (actionIndex != i3) {
                f4 += Math.abs(motionEvent2.getX(i3) - f2);
                f9 += Math.abs(motionEvent2.getY(i3) - f);
            }
        }
        float f10 = (float) i;
        float f11 = (f4 / f10) * 2.0f;
        float f12 = (f9 / f10) * 2.0f;
        if (inAnchoredScaleMode()) {
            f3 = f12;
        } else {
            f3 = (float) Math.hypot((double) f11, (double) f12);
        }
        boolean z6 = this.mInProgress;
        this.mFocusX = f2;
        this.mFocusY = f;
        if (!inAnchoredScaleMode() && this.mInProgress && (f3 < ((float) this.mMinSpan) || z4)) {
            this.mListener.onScaleEnd(this);
            this.mInProgress = false;
            this.mInitialSpan = f3;
        }
        if (z4) {
            this.mCurrSpanX = f11;
            this.mPrevSpanX = f11;
            this.mCurrSpanY = f12;
            this.mPrevSpanY = f12;
            this.mCurrSpan = f3;
            this.mPrevSpan = f3;
            this.mInitialSpan = f3;
        }
        int i4 = inAnchoredScaleMode() ? this.mSpanSlop : this.mMinSpan;
        if (!this.mInProgress && f3 >= ((float) i4) && (z6 || Math.abs(f3 - this.mInitialSpan) > ((float) this.mSpanSlop))) {
            this.mCurrSpanX = f11;
            this.mPrevSpanX = f11;
            this.mCurrSpanY = f12;
            this.mPrevSpanY = f12;
            this.mCurrSpan = f3;
            this.mPrevSpan = f3;
            this.mPrevTime = this.mCurrTime;
            this.mInProgress = this.mListener.onScaleBegin(this);
        }
        if (actionMasked == 2) {
            this.mCurrSpanX = f11;
            this.mCurrSpanY = f12;
            this.mCurrSpan = f3;
            if (this.mInProgress ? this.mListener.onScale(this) : true) {
                this.mPrevSpanX = this.mCurrSpanX;
                this.mPrevSpanY = this.mCurrSpanY;
                this.mPrevSpan = this.mCurrSpan;
                this.mPrevTime = this.mCurrTime;
            }
        }
        return true;
    }

    private boolean inAnchoredScaleMode() {
        return this.mAnchoredScaleMode != 0;
    }

    public void setQuickScaleEnabled(boolean z) {
        this.mQuickScaleEnabled = z;
        if (z && this.mGestureDetector == null) {
            this.mGestureDetector = new GestureDetector(this.mContext, new GestureDetector.SimpleOnGestureListener() {
                public boolean onDoubleTap(MotionEvent motionEvent) {
                    float unused = ScaleGestureDetector.this.mAnchoredScaleStartX = motionEvent.getX();
                    float unused2 = ScaleGestureDetector.this.mAnchoredScaleStartY = motionEvent.getY();
                    int unused3 = ScaleGestureDetector.this.mAnchoredScaleMode = 1;
                    return true;
                }
            }, this.mHandler);
        }
    }

    public void setStylusScaleEnabled(boolean z) {
        this.mStylusScaleEnabled = z;
    }

    public float getFocusX() {
        return this.mFocusX;
    }

    public float getFocusY() {
        return this.mFocusY;
    }

    public float getCurrentSpan() {
        return this.mCurrSpan;
    }

    public float getScaleFactor() {
        if (inAnchoredScaleMode()) {
            boolean z = this.mEventBeforeOrAboveStartingGestureEvent;
            boolean z2 = (z && this.mCurrSpan < this.mPrevSpan) || (!z && this.mCurrSpan > this.mPrevSpan);
            float abs = Math.abs(1.0f - (this.mCurrSpan / this.mPrevSpan)) * 0.5f;
            if (this.mPrevSpan <= ((float) this.mSpanSlop)) {
                return 1.0f;
            }
            return z2 ? 1.0f + abs : 1.0f - abs;
        }
        float f = this.mPrevSpan;
        if (f > 0.0f) {
            return this.mCurrSpan / f;
        }
        return 1.0f;
    }

    public long getTimeDelta() {
        return this.mCurrTime - this.mPrevTime;
    }

    public double getTimeDeltaSeconds() {
        return ((double) getTimeDelta()) / 1000.0d;
    }
}
