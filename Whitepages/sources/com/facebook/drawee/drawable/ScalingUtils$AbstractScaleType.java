package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.Rect;

public abstract class ScalingUtils$AbstractScaleType implements ScalingUtils$ScaleType {
    public abstract void getTransformImpl(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4);

    public Matrix getTransform(Matrix matrix, Rect rect, int i, int i2, float f, float f2) {
        int i3 = i;
        int i4 = i2;
        getTransformImpl(matrix, rect, i3, i4, f, f2, ((float) rect.width()) / ((float) i3), ((float) rect.height()) / ((float) i4));
        return matrix;
    }
}
