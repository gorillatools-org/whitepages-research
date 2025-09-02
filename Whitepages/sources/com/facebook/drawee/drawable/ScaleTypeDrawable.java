package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.Objects;
import com.facebook.react.uimanager.ViewProps;
import kotlin.jvm.internal.Intrinsics;

public final class ScaleTypeDrawable extends ForwardingDrawable {
    public Matrix mDrawMatrix;
    public PointF mFocusPoint;
    private ScalingUtils$ScaleType mScaleType;
    public Object mScaleTypeState;
    public int mUnderlyingHeight;
    public int mUnderlyingWidth;
    private final Matrix tempMatrix = new Matrix();

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ScaleTypeDrawable(Drawable drawable, ScalingUtils$ScaleType scalingUtils$ScaleType) {
        super(drawable);
        Intrinsics.checkNotNullParameter(scalingUtils$ScaleType, "scaleType");
        this.mScaleType = scalingUtils$ScaleType;
    }

    public Drawable setCurrent(Drawable drawable) {
        Drawable current = super.setCurrent(drawable);
        configureBounds();
        return current;
    }

    public final ScalingUtils$ScaleType getScaleType() {
        return this.mScaleType;
    }

    public final void setScaleType(ScalingUtils$ScaleType scalingUtils$ScaleType) {
        Intrinsics.checkNotNullParameter(scalingUtils$ScaleType, "scaleType");
        if (!Objects.equal(this.mScaleType, scalingUtils$ScaleType)) {
            this.mScaleType = scalingUtils$ScaleType;
            this.mScaleTypeState = null;
            configureBounds();
            invalidateSelf();
        }
    }

    public final PointF getFocusPoint() {
        return this.mFocusPoint;
    }

    public final void setFocusPoint(PointF pointF) {
        if (!Objects.equal(this.mFocusPoint, pointF)) {
            if (pointF == null) {
                this.mFocusPoint = null;
            } else {
                if (this.mFocusPoint == null) {
                    this.mFocusPoint = new PointF();
                }
                PointF pointF2 = this.mFocusPoint;
                Intrinsics.checkNotNull(pointF2);
                pointF2.set(pointF);
            }
            configureBounds();
            invalidateSelf();
        }
    }

    public void draw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        configureBoundsIfUnderlyingChanged();
        if (this.mDrawMatrix != null) {
            int save = canvas.save();
            canvas.clipRect(getBounds());
            canvas.concat(this.mDrawMatrix);
            super.draw(canvas);
            canvas.restoreToCount(save);
            return;
        }
        super.draw(canvas);
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "bounds");
        configureBounds();
    }

    private final void configureBoundsIfUnderlyingChanged() {
        Drawable current = getCurrent();
        if (current != null) {
            if (this.mUnderlyingWidth != current.getIntrinsicWidth() || this.mUnderlyingHeight != current.getIntrinsicHeight()) {
                configureBounds();
            }
        }
    }

    public final void configureBounds() {
        float f;
        float f2;
        Drawable current = getCurrent();
        if (current == null) {
            this.mUnderlyingHeight = 0;
            this.mUnderlyingWidth = 0;
            this.mDrawMatrix = null;
            return;
        }
        Rect bounds = getBounds();
        Intrinsics.checkNotNullExpressionValue(bounds, "getBounds(...)");
        int width = bounds.width();
        int height = bounds.height();
        int intrinsicWidth = current.getIntrinsicWidth();
        this.mUnderlyingWidth = intrinsicWidth;
        int intrinsicHeight = current.getIntrinsicHeight();
        this.mUnderlyingHeight = intrinsicHeight;
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            current.setBounds(bounds);
            this.mDrawMatrix = null;
        } else if (intrinsicWidth == width && intrinsicHeight == height) {
            current.setBounds(bounds);
            this.mDrawMatrix = null;
        } else if (this.mScaleType == ScalingUtils$ScaleType.FIT_XY) {
            current.setBounds(bounds);
            this.mDrawMatrix = null;
        } else {
            current.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
            this.tempMatrix.reset();
            ScalingUtils$ScaleType scalingUtils$ScaleType = this.mScaleType;
            Matrix matrix = this.tempMatrix;
            PointF pointF = this.mFocusPoint;
            if (pointF != null) {
                Intrinsics.checkNotNull(pointF);
                f = pointF.x;
            } else {
                f = 0.5f;
            }
            PointF pointF2 = this.mFocusPoint;
            if (pointF2 != null) {
                Intrinsics.checkNotNull(pointF2);
                f2 = pointF2.y;
            } else {
                f2 = 0.5f;
            }
            scalingUtils$ScaleType.getTransform(matrix, bounds, intrinsicWidth, intrinsicHeight, f, f2);
            this.mDrawMatrix = this.tempMatrix;
        }
    }

    public void getTransform(Matrix matrix) {
        Intrinsics.checkNotNullParameter(matrix, ViewProps.TRANSFORM);
        getParentTransform(matrix);
        configureBoundsIfUnderlyingChanged();
        Matrix matrix2 = this.mDrawMatrix;
        if (matrix2 != null) {
            matrix.preConcat(matrix2);
        }
    }
}
