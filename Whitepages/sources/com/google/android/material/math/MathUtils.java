package com.google.android.material.math;

public abstract class MathUtils {
    public static float lerp(float f, float f2, float f3) {
        return ((1.0f - f3) * f) + (f3 * f2);
    }
}
