package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.Rect;

class ScalingUtils$ScaleTypeFocusCrop extends ScalingUtils$AbstractScaleType {
    public static final ScalingUtils$ScaleType INSTANCE = new ScalingUtils$ScaleTypeFocusCrop();

    private ScalingUtils$ScaleTypeFocusCrop() {
    }

    public void getTransformImpl(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
        float f5;
        float f6;
        if (f4 > f3) {
            float f7 = ((float) i) * f4;
            f5 = ((float) rect.left) + Math.max(Math.min((((float) rect.width()) * 0.5f) - (f * f7), 0.0f), ((float) rect.width()) - f7);
            f6 = (float) rect.top;
            f3 = f4;
        } else {
            f5 = (float) rect.left;
            float f8 = ((float) i2) * f3;
            f6 = Math.max(Math.min((((float) rect.height()) * 0.5f) - (f2 * f8), 0.0f), ((float) rect.height()) - f8) + ((float) rect.top);
        }
        matrix.setScale(f3, f3);
        matrix.postTranslate((float) ((int) (f5 + 0.5f)), (float) ((int) (f6 + 0.5f)));
    }

    public String toString() {
        return "focus_crop";
    }
}
