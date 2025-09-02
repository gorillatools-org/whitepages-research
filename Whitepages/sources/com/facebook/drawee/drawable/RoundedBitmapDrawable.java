package com.facebook.drawee.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.lang.ref.WeakReference;

public class RoundedBitmapDrawable extends RoundedDrawable {
    private static boolean sDefaultRepeatEdgePixels = false;
    private final Bitmap mBitmap;
    private RectF mBitmapClipRect = null;
    private final Paint mBorderPaint;
    private WeakReference mLastBitmap;
    private final Paint mPaint;
    private boolean mRepeatEdgePixels;

    public static boolean getDefaultRepeatEdgePixels() {
        return sDefaultRepeatEdgePixels;
    }

    public RoundedBitmapDrawable(Resources resources, Bitmap bitmap, Paint paint, boolean z) {
        super(new BitmapDrawable(resources, bitmap));
        Paint paint2 = new Paint();
        this.mPaint = paint2;
        Paint paint3 = new Paint(1);
        this.mBorderPaint = paint3;
        this.mBitmap = bitmap;
        if (paint != null) {
            paint2.set(paint);
        }
        paint2.setFlags(1);
        paint3.setStyle(Paint.Style.STROKE);
        this.mRepeatEdgePixels = z;
    }

    /* access modifiers changed from: protected */
    public void updateTransform() {
        super.updateTransform();
        if (!this.mRepeatEdgePixels) {
            if (this.mBitmapClipRect == null) {
                this.mBitmapClipRect = new RectF();
            }
            this.mTransform.mapRect(this.mBitmapClipRect, this.mBitmapBounds);
        }
    }

    public void draw(Canvas canvas) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("RoundedBitmapDrawable#draw");
        }
        if (!shouldRound()) {
            super.draw(canvas);
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
                return;
            }
            return;
        }
        updateTransform();
        updatePath();
        updatePaint();
        int save = canvas.save();
        canvas.concat(this.mInverseParentTransform);
        if (this.mRepeatEdgePixels || this.mBitmapClipRect == null) {
            canvas.drawPath(this.mPath, this.mPaint);
        } else {
            int save2 = canvas.save();
            canvas.clipRect(this.mBitmapClipRect);
            canvas.drawPath(this.mPath, this.mPaint);
            canvas.restoreToCount(save2);
        }
        float f = this.mBorderWidth;
        if (f > 0.0f) {
            this.mBorderPaint.setStrokeWidth(f);
            this.mBorderPaint.setColor(DrawableUtils.multiplyColorAlpha(this.mBorderColor, this.mPaint.getAlpha()));
            canvas.drawPath(this.mBorderPath, this.mBorderPaint);
        }
        canvas.restoreToCount(save);
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }

    private void updatePaint() {
        Shader shader;
        WeakReference weakReference = this.mLastBitmap;
        if (weakReference == null || weakReference.get() != this.mBitmap) {
            this.mLastBitmap = new WeakReference(this.mBitmap);
            if (this.mBitmap != null) {
                Paint paint = this.mPaint;
                Bitmap bitmap = this.mBitmap;
                Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                paint.setShader(new BitmapShader(bitmap, tileMode, tileMode));
                this.mIsShaderTransformDirty = true;
            }
        }
        if (this.mIsShaderTransformDirty && (shader = this.mPaint.getShader()) != null) {
            shader.setLocalMatrix(this.mTransform);
            this.mIsShaderTransformDirty = false;
        }
        this.mPaint.setFilterBitmap(getPaintFilterBitmap());
    }

    /* access modifiers changed from: package-private */
    public boolean shouldRound() {
        return super.shouldRound() && this.mBitmap != null;
    }

    public void setAlpha(int i) {
        super.setAlpha(i);
        if (i != this.mPaint.getAlpha()) {
            this.mPaint.setAlpha(i);
            super.setAlpha(i);
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
        this.mPaint.setColorFilter(colorFilter);
    }

    public void setRepeatEdgePixels(boolean z) {
        this.mRepeatEdgePixels = z;
    }
}
