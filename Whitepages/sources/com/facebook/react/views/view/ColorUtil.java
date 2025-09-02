package com.facebook.react.views.view;

import kotlin.math.MathKt;

public final class ColorUtil {
    public static final ColorUtil INSTANCE = new ColorUtil();

    private ColorUtil() {
    }

    public static final int normalize(double d, double d2, double d3, double d4) {
        ColorUtil colorUtil = INSTANCE;
        return (colorUtil.clamp255(d) << 16) | (colorUtil.clamp255(d4 * ((double) 255)) << 24) | (colorUtil.clamp255(d2) << 8) | colorUtil.clamp255(d3);
    }

    private final int clamp255(double d) {
        return Math.max(0, Math.min(255, MathKt.roundToInt(d)));
    }
}
