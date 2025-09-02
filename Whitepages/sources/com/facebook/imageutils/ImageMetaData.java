package com.facebook.imageutils;

import android.graphics.ColorSpace;
import kotlin.Pair;

public final class ImageMetaData {
    private final ColorSpace colorSpace;
    private final Pair dimensions;

    public ImageMetaData(int i, int i2, ColorSpace colorSpace2) {
        this.colorSpace = colorSpace2;
        this.dimensions = (i == -1 || i2 == -1) ? null : new Pair(Integer.valueOf(i), Integer.valueOf(i2));
    }

    public final ColorSpace getColorSpace() {
        return this.colorSpace;
    }

    public final Pair getDimensions() {
        return this.dimensions;
    }
}
