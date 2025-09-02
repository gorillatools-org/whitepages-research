package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.Rect;

class ScalingUtils$ScaleTypeFitY extends ScalingUtils$AbstractScaleType {
    public static final ScalingUtils$ScaleType INSTANCE = new ScalingUtils$ScaleTypeFitY();

    private ScalingUtils$ScaleTypeFitY() {
    }

    public void getTransformImpl(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
        matrix.setScale(f4, f4);
        matrix.postTranslate((float) ((int) (((float) rect.left) + ((((float) rect.width()) - (((float) i) * f4)) * 0.5f) + 0.5f)), (float) ((int) (((float) rect.top) + 0.5f)));
    }

    public String toString() {
        return "fit_y";
    }
}
