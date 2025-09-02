package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.facebook.react.uimanager.ViewProps;
import kotlin.jvm.internal.Intrinsics;

public final class OrientedDrawable extends ForwardingDrawable {
    private final int exifOrientation;
    public final Matrix mRotationMatrix = new Matrix();
    private final int rotationAngle;
    private final Matrix tempMatrix;
    private final RectF tempRectF;

    public OrientedDrawable(Drawable drawable, int i, int i2) {
        super(drawable);
        this.rotationAngle = i - (i % 90);
        this.exifOrientation = (i2 < 0 || i2 > 8) ? 0 : i2;
        this.tempMatrix = new Matrix();
        this.tempRectF = new RectF();
    }

    public void draw(Canvas canvas) {
        int i;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if (this.rotationAngle > 0 || !((i = this.exifOrientation) == 0 || i == 1)) {
            int save = canvas.save();
            canvas.concat(this.mRotationMatrix);
            super.draw(canvas);
            canvas.restoreToCount(save);
            return;
        }
        super.draw(canvas);
    }

    public int getIntrinsicWidth() {
        int i = this.exifOrientation;
        if (i == 5 || i == 7 || this.rotationAngle % 180 != 0) {
            return super.getIntrinsicHeight();
        }
        return super.getIntrinsicWidth();
    }

    public int getIntrinsicHeight() {
        int i = this.exifOrientation;
        if (i == 5 || i == 7 || this.rotationAngle % 180 != 0) {
            return super.getIntrinsicWidth();
        }
        return super.getIntrinsicHeight();
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        int i;
        Intrinsics.checkNotNullParameter(rect, "bounds");
        Drawable current = getCurrent();
        if (current != null) {
            int i2 = this.rotationAngle;
            if (i2 > 0 || !((i = this.exifOrientation) == 0 || i == 1)) {
                int i3 = this.exifOrientation;
                if (i3 == 2) {
                    this.mRotationMatrix.setScale(-1.0f, 1.0f);
                } else if (i3 == 7) {
                    this.mRotationMatrix.setRotate(270.0f, (float) rect.centerX(), (float) rect.centerY());
                    this.mRotationMatrix.postScale(-1.0f, 1.0f);
                } else if (i3 == 4) {
                    this.mRotationMatrix.setScale(1.0f, -1.0f);
                } else if (i3 != 5) {
                    this.mRotationMatrix.setRotate((float) i2, (float) rect.centerX(), (float) rect.centerY());
                } else {
                    this.mRotationMatrix.setRotate(270.0f, (float) rect.centerX(), (float) rect.centerY());
                    this.mRotationMatrix.postScale(1.0f, -1.0f);
                }
                this.tempMatrix.reset();
                this.mRotationMatrix.invert(this.tempMatrix);
                this.tempRectF.set(rect);
                this.tempMatrix.mapRect(this.tempRectF);
                RectF rectF = this.tempRectF;
                current.setBounds((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
                return;
            }
            current.setBounds(rect);
        }
    }

    public void getTransform(Matrix matrix) {
        Intrinsics.checkNotNullParameter(matrix, ViewProps.TRANSFORM);
        getParentTransform(matrix);
        if (!this.mRotationMatrix.isIdentity()) {
            matrix.preConcat(this.mRotationMatrix);
        }
    }
}
