package com.facebook.react.uimanager;

import android.util.DisplayMetrics;
import android.util.TypedValue;

public final class PixelUtil {
    public static final PixelUtil INSTANCE = new PixelUtil();

    public static final float toPixelFromSP(float f) {
        return toPixelFromSP$default(f, 0.0f, 2, (Object) null);
    }

    private PixelUtil() {
    }

    public static final float toPixelFromDIP(float f) {
        if (Float.isNaN(f)) {
            return Float.NaN;
        }
        return TypedValue.applyDimension(1, f, DisplayMetricsHolder.getWindowDisplayMetrics());
    }

    public static final float toPixelFromDIP(double d) {
        return toPixelFromDIP((float) d);
    }

    public static /* synthetic */ float toPixelFromSP$default(float f, float f2, int i, Object obj) {
        if ((i & 2) != 0) {
            f2 = Float.NaN;
        }
        return toPixelFromSP(f, f2);
    }

    public static final float toPixelFromSP(float f, float f2) {
        if (Float.isNaN(f)) {
            return Float.NaN;
        }
        DisplayMetrics windowDisplayMetrics = DisplayMetricsHolder.getWindowDisplayMetrics();
        float applyDimension = TypedValue.applyDimension(2, f, windowDisplayMetrics);
        return f2 >= 1.0f ? Math.min(applyDimension, f * windowDisplayMetrics.density * f2) : applyDimension;
    }

    public static final float toPixelFromSP(double d) {
        return toPixelFromSP$default((float) d, 0.0f, 2, (Object) null);
    }

    public static final float toDIPFromPixel(float f) {
        if (Float.isNaN(f)) {
            return Float.NaN;
        }
        return f / DisplayMetricsHolder.getWindowDisplayMetrics().density;
    }

    public static final float getDisplayMetricDensity() {
        return DisplayMetricsHolder.getWindowDisplayMetrics().density;
    }

    public final float dpToPx(int i) {
        return toPixelFromDIP((float) i);
    }

    public final float dpToPx(long j) {
        return toPixelFromDIP((float) j);
    }

    public final float dpToPx(float f) {
        return toPixelFromDIP(f);
    }

    public final float dpToPx(double d) {
        return toPixelFromDIP((float) d);
    }

    public final float pxToDp(int i) {
        return toDIPFromPixel((float) i);
    }

    public final float pxToDp(long j) {
        return toDIPFromPixel((float) j);
    }

    public final float pxToDp(float f) {
        return toDIPFromPixel(f);
    }

    public final float pxToDp(double d) {
        return toDIPFromPixel((float) d);
    }
}
