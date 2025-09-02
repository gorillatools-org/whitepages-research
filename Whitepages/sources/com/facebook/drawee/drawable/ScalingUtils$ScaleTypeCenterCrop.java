package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.Rect;

class ScalingUtils$ScaleTypeCenterCrop extends ScalingUtils$AbstractScaleType {
    public static final ScalingUtils$ScaleType INSTANCE = new ScalingUtils$ScaleTypeCenterCrop();

    private ScalingUtils$ScaleTypeCenterCrop() {
    }

    public void getTransformImpl(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
        float f5;
        float f6;
        if (f4 > f3) {
            f5 = ((float) rect.left) + ((((float) rect.width()) - (((float) i) * f4)) * 0.5f);
            f6 = (float) rect.top;
            f3 = f4;
        } else {
            f6 = ((((float) rect.height()) - (((float) i2) * f3)) * 0.5f) + ((float) rect.top);
            f5 = (float) rect.left;
        }
        matrix.setScale(f3, f3);
        matrix.postTranslate((float) ((int) (f5 + 0.5f)), (float) ((int) (f6 + 0.5f)));
    }

    public String toString() {
        return "center_crop";
    }
}
