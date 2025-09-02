package com.facebook.react.uimanager;

public final class FloatUtil {
    private static final float EPSILON = 1.0E-5f;
    public static final FloatUtil INSTANCE = new FloatUtil();

    private FloatUtil() {
    }

    public static final boolean floatsEqual(float f, float f2) {
        if (Float.isNaN(f) || Float.isNaN(f2)) {
            if (!Float.isNaN(f) || !Float.isNaN(f2)) {
                return false;
            }
        } else if (Math.abs(f2 - f) >= EPSILON) {
            return false;
        }
        return true;
    }

    public static final boolean floatsEqual(Float f, Float f2) {
        if (f == null) {
            return f2 == null;
        }
        if (f2 == null) {
            return false;
        }
        return floatsEqual(f.floatValue(), f2.floatValue());
    }
}
