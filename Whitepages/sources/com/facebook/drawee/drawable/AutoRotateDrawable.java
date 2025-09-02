package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import com.facebook.common.internal.Preconditions;

public class AutoRotateDrawable extends ForwardingDrawable implements Runnable {
    private boolean mClockwise;
    private int mInterval;
    private boolean mIsScheduled;
    float mRotationAngle;

    public AutoRotateDrawable(Drawable drawable, int i) {
        this(drawable, i, true);
    }

    public AutoRotateDrawable(Drawable drawable, int i, boolean z) {
        super((Drawable) Preconditions.checkNotNull(drawable));
        this.mRotationAngle = 0.0f;
        this.mIsScheduled = false;
        this.mInterval = i;
        this.mClockwise = z;
    }

    public void draw(Canvas canvas) {
        int save = canvas.save();
        Rect bounds = getBounds();
        int i = bounds.right;
        int i2 = bounds.left;
        int i3 = i - i2;
        int i4 = bounds.bottom;
        int i5 = bounds.top;
        int i6 = i4 - i5;
        float f = this.mRotationAngle;
        if (!this.mClockwise) {
            f = 360.0f - f;
        }
        canvas.rotate(f, (float) (i2 + (i3 / 2)), (float) (i5 + (i6 / 2)));
        super.draw(canvas);
        canvas.restoreToCount(save);
        scheduleNextFrame();
    }

    public void run() {
        this.mIsScheduled = false;
        this.mRotationAngle += (float) getIncrement();
        invalidateSelf();
    }

    private void scheduleNextFrame() {
        if (!this.mIsScheduled) {
            this.mIsScheduled = true;
            scheduleSelf(this, SystemClock.uptimeMillis() + 20);
        }
    }

    private int getIncrement() {
        return (int) ((20.0f / ((float) this.mInterval)) * 360.0f);
    }
}
