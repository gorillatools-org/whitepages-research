package com.facebook.react.uimanager.drawable;

import kotlin.ranges.RangesKt;

public final class BoxShadowBorderRadiusKt {
    public static final float adjustRadiusForSpread(float f, float f2) {
        float f3;
        if (f < Math.abs(f2)) {
            float f4 = (float) 1;
            f3 = f4 + ((float) Math.pow((double) ((f / Math.abs(f2)) - f4), (double) 3));
        } else {
            f3 = 1.0f;
        }
        return RangesKt.coerceAtLeast(f + (f2 * f3), 0.0f);
    }
}
