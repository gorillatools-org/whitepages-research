package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.Arrays;

public abstract class RoundedDrawable extends Drawable implements Rounded, TransformAwareDrawable {
    final RectF mBitmapBounds = new RectF();
    protected int mBorderColor = 0;
    protected final Path mBorderPath = new Path();
    final float[] mBorderRadii = new float[8];
    protected float mBorderWidth = 0.0f;
    final Matrix mBoundsTransform = new Matrix();
    private final float[] mCornerRadii = new float[8];
    private final Drawable mDelegate;
    final RectF mDrawableBounds = new RectF();
    RectF mInsideBorderBounds;
    float[] mInsideBorderRadii;
    Matrix mInsideBorderTransform;
    final Matrix mInverseParentTransform = new Matrix();
    protected boolean mIsCircle = false;
    private boolean mIsPathDirty = true;
    protected boolean mIsShaderTransformDirty = true;
    private float mPadding = 0.0f;
    private boolean mPaintFilterBitmap = false;
    final Matrix mParentTransform = new Matrix();
    protected final Path mPath = new Path();
    final Matrix mPrevBoundsTransform = new Matrix();
    Matrix mPrevInsideBorderTransform;
    final Matrix mPrevParentTransform = new Matrix();
    final RectF mPrevRootBounds = new RectF();
    protected boolean mRadiiNonZero = false;
    final RectF mRootBounds = new RectF();
    private boolean mScaleDownInsideBorders = false;
    final Matrix mTransform = new Matrix();
    private TransformCallback mTransformCallback;

    public void setRepeatEdgePixels(boolean z) {
    }

    RoundedDrawable(Drawable drawable) {
        this.mDelegate = drawable;
    }

    public void setCircle(boolean z) {
        this.mIsCircle = z;
        this.mIsPathDirty = true;
        invalidateSelf();
    }

    public void setRadius(float f) {
        int i = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
        boolean z = false;
        Preconditions.checkState(i >= 0);
        Arrays.fill(this.mCornerRadii, f);
        if (i != 0) {
            z = true;
        }
        this.mRadiiNonZero = z;
        this.mIsPathDirty = true;
        invalidateSelf();
    }

    public void setRadii(float[] fArr) {
        if (fArr == null) {
            Arrays.fill(this.mCornerRadii, 0.0f);
            this.mRadiiNonZero = false;
        } else {
            Preconditions.checkArgument(fArr.length == 8, "radii should have exactly 8 values");
            System.arraycopy(fArr, 0, this.mCornerRadii, 0, 8);
            this.mRadiiNonZero = false;
            for (int i = 0; i < 8; i++) {
                this.mRadiiNonZero |= fArr[i] > 0.0f;
            }
        }
        this.mIsPathDirty = true;
        invalidateSelf();
    }

    public void setBorder(int i, float f) {
        if (this.mBorderColor != i || this.mBorderWidth != f) {
            this.mBorderColor = i;
            this.mBorderWidth = f;
            this.mIsPathDirty = true;
            invalidateSelf();
        }
    }

    public void setPadding(float f) {
        if (this.mPadding != f) {
            this.mPadding = f;
            this.mIsPathDirty = true;
            invalidateSelf();
        }
    }

    public void setScaleDownInsideBorders(boolean z) {
        if (this.mScaleDownInsideBorders != z) {
            this.mScaleDownInsideBorders = z;
            this.mIsPathDirty = true;
            invalidateSelf();
        }
    }

    public void setPaintFilterBitmap(boolean z) {
        if (this.mPaintFilterBitmap != z) {
            this.mPaintFilterBitmap = z;
            invalidateSelf();
        }
    }

    public boolean getPaintFilterBitmap() {
        return this.mPaintFilterBitmap;
    }

    public void setTransformCallback(TransformCallback transformCallback) {
        this.mTransformCallback = transformCallback;
    }

    /* access modifiers changed from: protected */
    public void updateTransform() {
        Matrix matrix;
        Matrix matrix2;
        TransformCallback transformCallback = this.mTransformCallback;
        if (transformCallback != null) {
            transformCallback.getTransform(this.mParentTransform);
            this.mTransformCallback.getRootBounds(this.mRootBounds);
        } else {
            this.mParentTransform.reset();
            this.mRootBounds.set(getBounds());
        }
        this.mBitmapBounds.set(0.0f, 0.0f, (float) getIntrinsicWidth(), (float) getIntrinsicHeight());
        this.mDrawableBounds.set(this.mDelegate.getBounds());
        Matrix matrix3 = this.mBoundsTransform;
        RectF rectF = this.mBitmapBounds;
        RectF rectF2 = this.mDrawableBounds;
        Matrix.ScaleToFit scaleToFit = Matrix.ScaleToFit.FILL;
        matrix3.setRectToRect(rectF, rectF2, scaleToFit);
        if (this.mScaleDownInsideBorders) {
            RectF rectF3 = this.mInsideBorderBounds;
            if (rectF3 == null) {
                this.mInsideBorderBounds = new RectF(this.mRootBounds);
            } else {
                rectF3.set(this.mRootBounds);
            }
            RectF rectF4 = this.mInsideBorderBounds;
            float f = this.mBorderWidth;
            rectF4.inset(f, f);
            if (this.mInsideBorderTransform == null) {
                this.mInsideBorderTransform = new Matrix();
            }
            this.mInsideBorderTransform.setRectToRect(this.mRootBounds, this.mInsideBorderBounds, scaleToFit);
        } else {
            Matrix matrix4 = this.mInsideBorderTransform;
            if (matrix4 != null) {
                matrix4.reset();
            }
        }
        if (!this.mParentTransform.equals(this.mPrevParentTransform) || !this.mBoundsTransform.equals(this.mPrevBoundsTransform) || ((matrix2 = this.mInsideBorderTransform) != null && !matrixEquals(matrix2, this.mPrevInsideBorderTransform))) {
            this.mIsShaderTransformDirty = true;
            this.mParentTransform.invert(this.mInverseParentTransform);
            this.mTransform.set(this.mParentTransform);
            if (this.mScaleDownInsideBorders && (matrix = this.mInsideBorderTransform) != null) {
                this.mTransform.postConcat(matrix);
            }
            this.mTransform.preConcat(this.mBoundsTransform);
            this.mPrevParentTransform.set(this.mParentTransform);
            this.mPrevBoundsTransform.set(this.mBoundsTransform);
            if (this.mScaleDownInsideBorders) {
                Matrix matrix5 = this.mPrevInsideBorderTransform;
                if (matrix5 == null) {
                    this.mPrevInsideBorderTransform = deepCopyMatrix(this.mInsideBorderTransform);
                } else {
                    matrix5.set(this.mInsideBorderTransform);
                }
            } else {
                Matrix matrix6 = this.mPrevInsideBorderTransform;
                if (matrix6 != null) {
                    matrix6.reset();
                }
            }
        }
        if (!this.mRootBounds.equals(this.mPrevRootBounds)) {
            this.mIsPathDirty = true;
            this.mPrevRootBounds.set(this.mRootBounds);
        }
    }

    /* access modifiers changed from: protected */
    public void updatePath() {
        float[] fArr;
        if (this.mIsPathDirty) {
            this.mBorderPath.reset();
            RectF rectF = this.mRootBounds;
            float f = this.mBorderWidth;
            rectF.inset(f / 2.0f, f / 2.0f);
            if (this.mIsCircle) {
                this.mBorderPath.addCircle(this.mRootBounds.centerX(), this.mRootBounds.centerY(), Math.min(this.mRootBounds.width(), this.mRootBounds.height()) / 2.0f, Path.Direction.CW);
            } else {
                int i = 0;
                while (true) {
                    fArr = this.mBorderRadii;
                    if (i >= fArr.length) {
                        break;
                    }
                    fArr[i] = (this.mCornerRadii[i] + this.mPadding) - (this.mBorderWidth / 2.0f);
                    i++;
                }
                this.mBorderPath.addRoundRect(this.mRootBounds, fArr, Path.Direction.CW);
            }
            RectF rectF2 = this.mRootBounds;
            float f2 = this.mBorderWidth;
            rectF2.inset((-f2) / 2.0f, (-f2) / 2.0f);
            this.mPath.reset();
            float f3 = this.mPadding + (this.mScaleDownInsideBorders ? this.mBorderWidth : 0.0f);
            this.mRootBounds.inset(f3, f3);
            if (this.mIsCircle) {
                this.mPath.addCircle(this.mRootBounds.centerX(), this.mRootBounds.centerY(), Math.min(this.mRootBounds.width(), this.mRootBounds.height()) / 2.0f, Path.Direction.CW);
            } else if (this.mScaleDownInsideBorders) {
                if (this.mInsideBorderRadii == null) {
                    this.mInsideBorderRadii = new float[8];
                }
                for (int i2 = 0; i2 < this.mBorderRadii.length; i2++) {
                    this.mInsideBorderRadii[i2] = this.mCornerRadii[i2] - this.mBorderWidth;
                }
                this.mPath.addRoundRect(this.mRootBounds, this.mInsideBorderRadii, Path.Direction.CW);
            } else {
                this.mPath.addRoundRect(this.mRootBounds, this.mCornerRadii, Path.Direction.CW);
            }
            float f4 = -f3;
            this.mRootBounds.inset(f4, f4);
            this.mPath.setFillType(Path.FillType.WINDING);
            this.mIsPathDirty = false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean shouldRound() {
        return this.mIsCircle || this.mRadiiNonZero || this.mBorderWidth > 0.0f;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        this.mDelegate.setBounds(rect);
    }

    public int getIntrinsicWidth() {
        return this.mDelegate.getIntrinsicWidth();
    }

    public int getIntrinsicHeight() {
        return this.mDelegate.getIntrinsicHeight();
    }

    public int getOpacity() {
        return this.mDelegate.getOpacity();
    }

    public void setColorFilter(int i, PorterDuff.Mode mode) {
        this.mDelegate.setColorFilter(i, mode);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mDelegate.setColorFilter(colorFilter);
    }

    public ColorFilter getColorFilter() {
        return this.mDelegate.getColorFilter();
    }

    public void clearColorFilter() {
        this.mDelegate.clearColorFilter();
    }

    public int getAlpha() {
        return this.mDelegate.getAlpha();
    }

    public void setAlpha(int i) {
        this.mDelegate.setAlpha(i);
    }

    public void draw(Canvas canvas) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("RoundedDrawable#draw");
        }
        this.mDelegate.draw(canvas);
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }

    private static boolean matrixEquals(Matrix matrix, Matrix matrix2) {
        if (matrix == null && matrix2 == null) {
            return true;
        }
        if (matrix == null || matrix2 == null) {
            return false;
        }
        return matrix.equals(matrix2);
    }

    private static Matrix deepCopyMatrix(Matrix matrix) {
        if (matrix == null) {
            return null;
        }
        return new Matrix(matrix);
    }
}
