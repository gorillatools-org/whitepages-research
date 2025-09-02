package com.rnlineargradient;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.PixelUtil;

public class LinearGradientView extends View {
    private float mAngle = 45.0f;
    private float[] mAngleCenter = {0.5f, 0.5f};
    private float[] mBorderRadii = {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
    private int[] mColors;
    private float[] mEndPoint = {0.0f, 1.0f};
    private float[] mLocations;
    private final Paint mPaint = new Paint(1);
    private Path mPathForBorderRadius;
    private LinearGradient mShader;
    private int[] mSize = {0, 0};
    private float[] mStartPoint = {0.0f, 0.0f};
    private RectF mTempRectForBorderRadius;
    private boolean mUseAngle = false;

    public LinearGradientView(Context context) {
        super(context);
    }

    private static float[] getStartCornerToIntersect(float f, int[] iArr) {
        float f2 = ((float) iArr[0]) / 2.0f;
        float f3 = ((float) iArr[1]) / 2.0f;
        if (f < 90.0f) {
            return new float[]{-f2, -f3};
        } else if (f < 180.0f) {
            return new float[]{f2, -f3};
        } else if (f < 270.0f) {
            return new float[]{f2, f3};
        } else {
            return new float[]{-f2, f3};
        }
    }

    private static float[] getHorizontalOrVerticalStartPoint(float f, int[] iArr) {
        float f2 = ((float) iArr[0]) / 2.0f;
        float f3 = ((float) iArr[1]) / 2.0f;
        if (f == 0.0f) {
            return new float[]{-f2, 0.0f};
        } else if (f == 90.0f) {
            return new float[]{0.0f, -f3};
        } else if (f == 180.0f) {
            return new float[]{f2, 0.0f};
        } else {
            return new float[]{0.0f, f3};
        }
    }

    private static float[] getGradientStartPoint(float f, int[] iArr) {
        float f2 = f % 360.0f;
        if (f2 < 0.0f) {
            f2 += 360.0f;
        }
        if (f2 % 90.0f == 0.0f) {
            return getHorizontalOrVerticalStartPoint(f2, iArr);
        }
        float tan = (float) Math.tan((((double) f2) * 3.141592653589793d) / 180.0d);
        float f3 = -1.0f / tan;
        float[] startCornerToIntersect = getStartCornerToIntersect(f2, iArr);
        float f4 = (startCornerToIntersect[1] - (startCornerToIntersect[0] * f3)) / (tan - f3);
        return new float[]{f4, tan * f4};
    }

    public void setStartPoint(ReadableMap readableMap) {
        this.mStartPoint = new float[]{(float) readableMap.getDouble("x"), (float) readableMap.getDouble("y")};
        drawGradient();
    }

    public void setEndPoint(ReadableMap readableMap) {
        this.mEndPoint = new float[]{(float) readableMap.getDouble("x"), (float) readableMap.getDouble("y")};
        drawGradient();
    }

    public void setColors(ReadableArray readableArray) {
        int i;
        int size = readableArray.size();
        int[] iArr = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            if (readableArray.getType(i2) == ReadableType.Map) {
                i = ColorPropConverter.getColor(readableArray.getMap(i2), getContext()).intValue();
            } else {
                i = readableArray.getInt(i2);
            }
            iArr[i2] = i;
        }
        this.mColors = iArr;
        drawGradient();
    }

    public void setLocations(ReadableArray readableArray) {
        int size = readableArray.size();
        float[] fArr = new float[size];
        for (int i = 0; i < size; i++) {
            fArr[i] = (float) readableArray.getDouble(i);
        }
        this.mLocations = fArr;
        drawGradient();
    }

    public void setUseAngle(boolean z) {
        this.mUseAngle = z;
        drawGradient();
    }

    public void setAngleCenter(ReadableMap readableMap) {
        this.mAngleCenter = new float[]{(float) readableMap.getDouble("x"), (float) readableMap.getDouble("y")};
        drawGradient();
    }

    public void setAngle(float f) {
        this.mAngle = f;
        drawGradient();
    }

    public void setBorderRadii(ReadableArray readableArray) {
        int size = readableArray.size();
        float[] fArr = new float[size];
        for (int i = 0; i < size; i++) {
            fArr[i] = PixelUtil.toPixelFromDIP((float) readableArray.getDouble(i));
        }
        this.mBorderRadii = fArr;
        updatePath();
        drawGradient();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        this.mSize = new int[]{i, i2};
        updatePath();
        drawGradient();
    }

    private void drawGradient() {
        float[] fArr;
        float[] fArr2;
        int[] iArr = this.mColors;
        if (iArr != null) {
            float[] fArr3 = this.mLocations;
            if (fArr3 == null || iArr.length == fArr3.length) {
                if (!this.mUseAngle || this.mAngleCenter == null) {
                    float[] fArr4 = this.mStartPoint;
                    float f = fArr4[0];
                    int[] iArr2 = this.mSize;
                    int i = iArr2[0];
                    float f2 = fArr4[1];
                    int i2 = iArr2[1];
                    fArr = new float[]{f * ((float) i), f2 * ((float) i2)};
                    float[] fArr5 = this.mEndPoint;
                    fArr2 = new float[]{fArr5[0] * ((float) i), fArr5[1] * ((float) i2)};
                } else {
                    float[] gradientStartPoint = getGradientStartPoint(90.0f - this.mAngle, this.mSize);
                    float[] fArr6 = this.mAngleCenter;
                    float f3 = fArr6[0];
                    int[] iArr3 = this.mSize;
                    float[] fArr7 = {f3 * ((float) iArr3[0]), fArr6[1] * ((float) iArr3[1])};
                    fArr = new float[]{fArr7[0] + gradientStartPoint[0], fArr7[1] - gradientStartPoint[1]};
                    fArr2 = new float[]{fArr7[0] - gradientStartPoint[0], fArr7[1] + gradientStartPoint[1]};
                }
                LinearGradient linearGradient = new LinearGradient(fArr[0], fArr[1], fArr2[0], fArr2[1], this.mColors, this.mLocations, Shader.TileMode.CLAMP);
                this.mShader = linearGradient;
                this.mPaint.setShader(linearGradient);
                invalidate();
            }
        }
    }

    private void updatePath() {
        if (this.mPathForBorderRadius == null) {
            this.mPathForBorderRadius = new Path();
            this.mTempRectForBorderRadius = new RectF();
        }
        this.mPathForBorderRadius.reset();
        RectF rectF = this.mTempRectForBorderRadius;
        int[] iArr = this.mSize;
        rectF.set(0.0f, 0.0f, (float) iArr[0], (float) iArr[1]);
        this.mPathForBorderRadius.addRoundRect(this.mTempRectForBorderRadius, this.mBorderRadii, Path.Direction.CW);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = this.mPathForBorderRadius;
        if (path == null) {
            canvas.drawPaint(this.mPaint);
        } else {
            canvas.drawPath(path, this.mPaint);
        }
    }
}
