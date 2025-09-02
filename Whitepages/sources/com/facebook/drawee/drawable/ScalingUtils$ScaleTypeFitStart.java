package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.Rect;

class ScalingUtils$ScaleTypeFitStart extends ScalingUtils$AbstractScaleType {
    public static final ScalingUtils$ScaleType INSTANCE = new ScalingUtils$ScaleTypeFitStart();

    private ScalingUtils$ScaleTypeFitStart() {
    }

    public void getTransformImpl(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
        float min = Math.min(f3, f4);
        matrix.setScale(min, min);
        matrix.postTranslate((float) ((int) (((float) rect.left) + 0.5f)), (float) ((int) (((float) rect.top) + 0.5f)));
    }

    public String toString() {
        return "fit_start";
    }
}
