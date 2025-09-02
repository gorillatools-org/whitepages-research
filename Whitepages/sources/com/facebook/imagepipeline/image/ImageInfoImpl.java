package com.facebook.imagepipeline.image;

import java.util.Map;

public class ImageInfoImpl implements ImageInfo {
    private final Map extras;
    private final int height;
    private final QualityInfo qualityInfo;
    private final int sizeInBytes;
    private final int width;

    public ImageInfoImpl(int i, int i2, int i3, QualityInfo qualityInfo2, Map map) {
        this.width = i;
        this.height = i2;
        this.sizeInBytes = i3;
        this.qualityInfo = qualityInfo2;
        this.extras = map;
    }

    public Map getExtras() {
        return this.extras;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
