package com.swmansion.rnscreens.ext;

public abstract class NumericExtKt {
    public static /* synthetic */ boolean equalWithRespectToEps$default(float f, float f2, float f3, int i, Object obj) {
        if ((i & 2) != 0) {
            f3 = 1.0E-4f;
        }
        return equalWithRespectToEps(f, f2, f3);
    }

    public static final boolean equalWithRespectToEps(float f, float f2, float f3) {
        return Math.abs(f - f2) <= f3;
    }
}
