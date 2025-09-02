package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import com.facebook.common.internal.Preconditions;
import com.facebook.fresco.ui.common.OnFadeListener;
import java.util.Arrays;

public class FadeDrawable extends ArrayDrawable {
    private final int mActualImageLayer;
    int mAlpha;
    int[] mAlphas;
    private final int mDefaultLayerAlpha;
    private final boolean mDefaultLayerIsOn;
    int mDurationMs;
    private boolean mIsFadingActualImage;
    boolean[] mIsLayerOn;
    private final Drawable[] mLayers;
    private boolean mMutateDrawables = true;
    private OnFadeListener mOnFadeListener;
    private boolean mOnFadeListenerShowImmediately;
    int mPreventInvalidateCount;
    int[] mStartAlphas;
    long mStartTimeMs;
    int mTransitionState;

    public FadeDrawable(Drawable[] drawableArr, boolean z, int i) {
        super(drawableArr);
        boolean z2 = true;
        int i2 = 0;
        Preconditions.checkState(drawableArr.length < 1 ? false : z2, "At least one layer required!");
        this.mLayers = drawableArr;
        this.mStartAlphas = new int[drawableArr.length];
        this.mAlphas = new int[drawableArr.length];
        this.mAlpha = 255;
        this.mIsLayerOn = new boolean[drawableArr.length];
        this.mPreventInvalidateCount = 0;
        this.mDefaultLayerIsOn = z;
        this.mDefaultLayerAlpha = z ? 255 : i2;
        this.mActualImageLayer = i;
        resetInternal();
    }

    public void invalidateSelf() {
        if (this.mPreventInvalidateCount == 0) {
            super.invalidateSelf();
        }
    }

    public void beginBatchMode() {
        this.mPreventInvalidateCount++;
    }

    public void endBatchMode() {
        this.mPreventInvalidateCount--;
        invalidateSelf();
    }

    public void setTransitionDuration(int i) {
        this.mDurationMs = i;
        if (this.mTransitionState == 1) {
            this.mTransitionState = 0;
        }
    }

    private void resetInternal() {
        this.mTransitionState = 2;
        Arrays.fill(this.mStartAlphas, this.mDefaultLayerAlpha);
        this.mStartAlphas[0] = 255;
        Arrays.fill(this.mAlphas, this.mDefaultLayerAlpha);
        this.mAlphas[0] = 255;
        Arrays.fill(this.mIsLayerOn, this.mDefaultLayerIsOn);
        this.mIsLayerOn[0] = true;
    }

    public void fadeInLayer(int i) {
        this.mTransitionState = 0;
        this.mIsLayerOn[i] = true;
        invalidateSelf();
    }

    public void fadeOutLayer(int i) {
        this.mTransitionState = 0;
        this.mIsLayerOn[i] = false;
        invalidateSelf();
    }

    public void fadeInAllLayers() {
        this.mTransitionState = 0;
        Arrays.fill(this.mIsLayerOn, true);
        invalidateSelf();
    }

    public void finishTransitionImmediately() {
        this.mTransitionState = 2;
        for (int i = 0; i < this.mLayers.length; i++) {
            this.mAlphas[i] = this.mIsLayerOn[i] ? 255 : 0;
        }
        invalidateSelf();
    }

    private boolean updateAlphas(float f) {
        boolean z = true;
        for (int i = 0; i < this.mLayers.length; i++) {
            boolean z2 = this.mIsLayerOn[i];
            int i2 = z2 ? 1 : -1;
            int[] iArr = this.mAlphas;
            int i3 = (int) (((float) this.mStartAlphas[i]) + (((float) (i2 * 255)) * f));
            iArr[i] = i3;
            if (i3 < 0) {
                iArr[i] = 0;
            }
            if (iArr[i] > 255) {
                iArr[i] = 255;
            }
            if (z2 && iArr[i] < 255) {
                z = false;
            }
            if (!z2 && iArr[i] > 0) {
                z = false;
            }
        }
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0057 A[LOOP:0: B:20:0x0052->B:22:0x0057, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0072 A[EDGE_INSN: B:26:0x0072->B:23:0x0072 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void draw(android.graphics.Canvas r9) {
        /*
            r8 = this;
            int r0 = r8.mTransitionState
            r1 = 2
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L_0x002c
            if (r0 == r3) goto L_0x000a
            goto L_0x0052
        L_0x000a:
            int r0 = r8.mDurationMs
            if (r0 <= 0) goto L_0x0010
            r0 = r3
            goto L_0x0011
        L_0x0010:
            r0 = r2
        L_0x0011:
            com.facebook.common.internal.Preconditions.checkState(r0)
            long r4 = r8.getCurrentTimeMs()
            long r6 = r8.mStartTimeMs
            long r4 = r4 - r6
            float r0 = (float) r4
            int r4 = r8.mDurationMs
            float r4 = (float) r4
            float r0 = r0 / r4
            boolean r0 = r8.updateAlphas(r0)
            if (r0 == 0) goto L_0x0027
            goto L_0x0028
        L_0x0027:
            r1 = r3
        L_0x0028:
            r8.mTransitionState = r1
        L_0x002a:
            r3 = r0
            goto L_0x0052
        L_0x002c:
            int[] r0 = r8.mAlphas
            int[] r4 = r8.mStartAlphas
            android.graphics.drawable.Drawable[] r5 = r8.mLayers
            int r5 = r5.length
            java.lang.System.arraycopy(r0, r2, r4, r2, r5)
            long r4 = r8.getCurrentTimeMs()
            r8.mStartTimeMs = r4
            int r0 = r8.mDurationMs
            if (r0 != 0) goto L_0x0043
            r0 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0044
        L_0x0043:
            r0 = 0
        L_0x0044:
            boolean r0 = r8.updateAlphas(r0)
            r8.maybeOnFadeStarted()
            if (r0 == 0) goto L_0x004e
            goto L_0x004f
        L_0x004e:
            r1 = r3
        L_0x004f:
            r8.mTransitionState = r1
            goto L_0x002a
        L_0x0052:
            android.graphics.drawable.Drawable[] r0 = r8.mLayers
            int r1 = r0.length
            if (r2 >= r1) goto L_0x0072
            r0 = r0[r2]
            int[] r1 = r8.mAlphas
            r1 = r1[r2]
            int r4 = r8.mAlpha
            int r1 = r1 * r4
            double r4 = (double) r1
            r6 = 4643176031446892544(0x406fe00000000000, double:255.0)
            double r4 = r4 / r6
            double r4 = java.lang.Math.ceil(r4)
            int r1 = (int) r4
            r8.drawDrawableWithAlpha(r9, r0, r1)
            int r2 = r2 + 1
            goto L_0x0052
        L_0x0072:
            if (r3 == 0) goto L_0x007b
            r8.maybeOnFadeFinished()
            r8.maybeOnImageShownImmediately()
            goto L_0x007e
        L_0x007b:
            r8.invalidateSelf()
        L_0x007e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.drawee.drawable.FadeDrawable.draw(android.graphics.Canvas):void");
    }

    private void maybeOnImageShownImmediately() {
        if (this.mOnFadeListenerShowImmediately && this.mTransitionState == 2 && this.mIsLayerOn[this.mActualImageLayer]) {
            OnFadeListener onFadeListener = this.mOnFadeListener;
            if (onFadeListener != null) {
                onFadeListener.onShownImmediately();
            }
            this.mOnFadeListenerShowImmediately = false;
        }
    }

    private void drawDrawableWithAlpha(Canvas canvas, Drawable drawable, int i) {
        if (drawable != null && i > 0) {
            this.mPreventInvalidateCount++;
            if (this.mMutateDrawables) {
                drawable.mutate();
            }
            drawable.setAlpha(i);
            this.mPreventInvalidateCount--;
            drawable.draw(canvas);
        }
    }

    public void setAlpha(int i) {
        if (this.mAlpha != i) {
            this.mAlpha = i;
            invalidateSelf();
        }
    }

    public int getAlpha() {
        return this.mAlpha;
    }

    /* access modifiers changed from: protected */
    public long getCurrentTimeMs() {
        return SystemClock.uptimeMillis();
    }

    private void maybeOnFadeStarted() {
        int i;
        if (!this.mIsFadingActualImage && (i = this.mActualImageLayer) >= 0) {
            boolean[] zArr = this.mIsLayerOn;
            if (i < zArr.length && zArr[i]) {
                this.mIsFadingActualImage = true;
                OnFadeListener onFadeListener = this.mOnFadeListener;
                if (onFadeListener != null) {
                    onFadeListener.onFadeStarted();
                }
            }
        }
    }

    private void maybeOnFadeFinished() {
        if (this.mIsFadingActualImage) {
            this.mIsFadingActualImage = false;
            OnFadeListener onFadeListener = this.mOnFadeListener;
            if (onFadeListener != null) {
                onFadeListener.onFadeFinished();
            }
        }
    }
}
