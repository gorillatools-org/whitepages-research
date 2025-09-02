package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.ReactConstants;
import com.horcrux.svg.SVGLength;

class Brush {
    private ReadableArray mColors;
    private Matrix mMatrix;
    private PatternView mPattern;
    private final SVGLength[] mPoints;
    private final BrushType mType;
    private boolean mUseContentObjectBoundingBoxUnits;
    private final boolean mUseObjectBoundingBox;
    private Rect mUserSpaceBoundingBox;

    enum BrushType {
        LINEAR_GRADIENT,
        RADIAL_GRADIENT,
        PATTERN
    }

    enum BrushUnits {
        OBJECT_BOUNDING_BOX,
        USER_SPACE_ON_USE
    }

    Brush(BrushType brushType, SVGLength[] sVGLengthArr, BrushUnits brushUnits) {
        this.mType = brushType;
        this.mPoints = sVGLengthArr;
        this.mUseObjectBoundingBox = brushUnits == BrushUnits.OBJECT_BOUNDING_BOX;
    }

    /* access modifiers changed from: package-private */
    public void setContentUnits(BrushUnits brushUnits) {
        this.mUseContentObjectBoundingBoxUnits = brushUnits == BrushUnits.OBJECT_BOUNDING_BOX;
    }

    /* access modifiers changed from: package-private */
    public void setPattern(PatternView patternView) {
        this.mPattern = patternView;
    }

    private static void parseGradientStops(ReadableArray readableArray, int i, float[] fArr, int[] iArr, float f) {
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 * 2;
            fArr[i2] = (float) readableArray.getDouble(i3);
            int i4 = readableArray.getInt(i3 + 1);
            iArr[i2] = (i4 & 16777215) | (Math.round(((float) (i4 >>> 24)) * f) << 24);
        }
    }

    /* access modifiers changed from: package-private */
    public void setUserSpaceBoundingBox(Rect rect) {
        this.mUserSpaceBoundingBox = rect;
    }

    /* access modifiers changed from: package-private */
    public void setGradientColors(ReadableArray readableArray) {
        this.mColors = readableArray;
    }

    /* access modifiers changed from: package-private */
    public void setGradientTransform(Matrix matrix) {
        this.mMatrix = matrix;
    }

    private RectF getPaintRect(RectF rectF) {
        float f;
        float f2;
        if (!this.mUseObjectBoundingBox) {
            rectF = new RectF(this.mUserSpaceBoundingBox);
        }
        float width = rectF.width();
        float height = rectF.height();
        if (this.mUseObjectBoundingBox) {
            f2 = rectF.left;
            f = rectF.top;
        } else {
            f2 = 0.0f;
            f = 0.0f;
        }
        return new RectF(f2, f, width + f2, height + f);
    }

    private double getVal(SVGLength sVGLength, double d, float f, float f2) {
        double d2;
        if (this.mUseObjectBoundingBox) {
            if (sVGLength.unit == SVGLength.UnitType.NUMBER) {
                d2 = d;
            }
            d2 = (double) f;
        } else {
            SVGLength sVGLength2 = sVGLength;
            d2 = (double) f;
        }
        return PropHelper.fromRelative(sVGLength, d, 0.0d, d2, (double) f2);
    }

    /* access modifiers changed from: package-private */
    public void setupPaint(Paint paint, RectF rectF, float f, float f2) {
        float[] fArr;
        int[] iArr;
        float[] fArr2;
        int[] iArr2;
        double d;
        Paint paint2 = paint;
        float f3 = f2;
        RectF paintRect = getPaintRect(rectF);
        float width = paintRect.width();
        float height = paintRect.height();
        float f4 = paintRect.left;
        float f5 = paintRect.top;
        float textSize = paint.getTextSize();
        if (this.mType == BrushType.PATTERN) {
            double d2 = (double) width;
            double val = getVal(this.mPoints[0], d2, f, textSize);
            double d3 = (double) height;
            double d4 = d3;
            double d5 = val;
            double val2 = getVal(this.mPoints[1], d3, f, textSize);
            double d6 = d2;
            double d7 = val2;
            float f6 = f;
            float f7 = textSize;
            double val3 = getVal(this.mPoints[2], d6, f6, f7);
            double val4 = getVal(this.mPoints[3], d4, f6, f7);
            if (val3 > 1.0d && val4 > 1.0d) {
                Bitmap createBitmap = Bitmap.createBitmap((int) val3, (int) val4, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                RectF viewBox = this.mPattern.getViewBox();
                if (viewBox != null && viewBox.width() > 0.0f && viewBox.height() > 0.0f) {
                    RectF rectF2 = new RectF((float) d5, (float) d7, (float) val3, (float) val4);
                    PatternView patternView = this.mPattern;
                    canvas.concat(ViewBox.getTransform(viewBox, rectF2, patternView.mAlign, patternView.mMeetOrSlice));
                }
                if (this.mUseContentObjectBoundingBoxUnits) {
                    canvas.scale(width / f, height / f);
                }
                this.mPattern.draw(canvas, new Paint(), f2);
                Matrix matrix = new Matrix();
                Matrix matrix2 = this.mMatrix;
                if (matrix2 != null) {
                    matrix.preConcat(matrix2);
                }
                Shader.TileMode tileMode = Shader.TileMode.REPEAT;
                BitmapShader bitmapShader = new BitmapShader(createBitmap, tileMode, tileMode);
                bitmapShader.setLocalMatrix(matrix);
                paint.setShader(bitmapShader);
                return;
            }
            return;
        }
        float f8 = f3;
        int size = this.mColors.size();
        if (size == 0) {
            FLog.w(ReactConstants.TAG, "Gradient contains no stops");
            return;
        }
        int i = size / 2;
        int[] iArr3 = new int[i];
        float[] fArr3 = new float[i];
        parseGradientStops(this.mColors, i, fArr3, iArr3, f8);
        if (i == 1) {
            int[] iArr4 = {iArr3[0], iArr3[0]};
            float[] fArr4 = {fArr3[0], fArr3[0]};
            FLog.w(ReactConstants.TAG, "Gradient contains only one stop");
            iArr = iArr4;
            fArr = fArr4;
        } else {
            iArr = iArr3;
            fArr = fArr3;
        }
        BrushType brushType = this.mType;
        if (brushType == BrushType.LINEAR_GRADIENT) {
            double d8 = (double) width;
            double d9 = d8;
            double val5 = getVal(this.mPoints[0], d8, f, textSize);
            double d10 = (double) f4;
            double d11 = (double) height;
            double d12 = val5 + d10;
            double d13 = d10;
            double d14 = (double) f5;
            double val6 = getVal(this.mPoints[1], d11, f, textSize) + d14;
            double d15 = val6;
            double val7 = getVal(this.mPoints[2], d9, f, textSize) + d13;
            double d16 = d11;
            LinearGradient linearGradient = new LinearGradient((float) d12, (float) d15, (float) val7, (float) (getVal(this.mPoints[3], d16, f, textSize) + d14), iArr, fArr, Shader.TileMode.CLAMP);
            if (this.mMatrix != null) {
                Matrix matrix3 = new Matrix();
                matrix3.preConcat(this.mMatrix);
                linearGradient.setLocalMatrix(matrix3);
            }
            paint.setShader(linearGradient);
            return;
        }
        int[] iArr5 = iArr;
        float[] fArr5 = fArr;
        if (brushType == BrushType.RADIAL_GRADIENT) {
            double d17 = (double) width;
            double d18 = d17;
            double d19 = d17;
            float f9 = f;
            float f10 = textSize;
            double val8 = getVal(this.mPoints[2], d18, f9, f10);
            double d20 = (double) height;
            double val9 = getVal(this.mPoints[3], d20, f9, f10);
            if (val8 <= 0.0d || val9 <= 0.0d) {
                float[] fArr6 = {fArr5[0], fArr5[fArr5.length - 1]};
                int[] iArr6 = iArr5;
                fArr2 = fArr6;
                iArr2 = new int[]{iArr6[iArr6.length - 1], iArr6[iArr6.length - 1]};
                val9 = d20;
                d = d19;
            } else {
                iArr2 = iArr5;
                fArr2 = fArr5;
                d = val8;
            }
            double d21 = val9 / d;
            double val10 = getVal(this.mPoints[4], d19, f, textSize) + ((double) f4);
            double d22 = d20 / d21;
            RadialGradient radialGradient = new RadialGradient((float) val10, (float) (getVal(this.mPoints[5], d22, f, textSize) + (((double) f5) / d21)), (float) d, iArr2, fArr2, Shader.TileMode.CLAMP);
            Matrix matrix4 = new Matrix();
            matrix4.preScale(1.0f, (float) d21);
            Matrix matrix5 = this.mMatrix;
            if (matrix5 != null) {
                matrix4.preConcat(matrix5);
            }
            radialGradient.setLocalMatrix(matrix4);
            paint.setShader(radialGradient);
        }
    }
}
