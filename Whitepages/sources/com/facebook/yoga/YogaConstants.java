package com.facebook.yoga;

public abstract class YogaConstants {
    public static boolean isUndefined(float f) {
        return Float.compare(f, Float.NaN) == 0;
    }
}
