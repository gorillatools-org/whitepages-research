package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.Rect;

class ScalingUtils$ScaleTypeFitX extends ScalingUtils$AbstractScaleType {
    public static final ScalingUtils$ScaleType INSTANCE = new ScalingUtils$ScaleTypeFitX();

    private ScalingUtils$ScaleTypeFitX() {
    }

    public void getTransformImpl(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
        float height = ((float) rect.top) + ((((float) rect.height()) - (((float) i2) * f3)) * 0.5f);
        matrix.setScale(f3, f3);
        matrix.postTranslate((float) ((int) (((float) rect.left) + 0.5f)), (float) ((int) (height + 0.5f)));
    }

    public String toString() {
        return "fit_x";
    }
}
