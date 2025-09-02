package com.facebook.imagepipeline.image;

import com.facebook.common.references.HasBitmap;
import com.facebook.fresco.middleware.HasExtraData;
import java.io.Closeable;

public interface CloseableImage extends Closeable, ImageInfo, HasBitmap, HasExtraData {
    void close();

    int getHeight();

    ImageInfo getImageInfo();

    QualityInfo getQualityInfo();

    int getSizeInBytes();

    int getWidth();

    boolean isClosed();

    boolean isStateful();
}
