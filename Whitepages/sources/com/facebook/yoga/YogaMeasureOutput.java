package com.facebook.yoga;

public abstract class YogaMeasureOutput {
    public static long make(float f, float f2) {
        int floatToRawIntBits = Float.floatToRawIntBits(f);
        return ((long) Float.floatToRawIntBits(f2)) | (((long) floatToRawIntBits) << 32);
    }

    public static long make(int i, int i2) {
        return make((float) i, (float) i2);
    }
}
