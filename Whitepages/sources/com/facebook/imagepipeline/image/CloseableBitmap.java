package com.facebook.imagepipeline.image;

import android.graphics.Bitmap;

public interface CloseableBitmap extends CloseableImage {
    Bitmap getUnderlyingBitmap();
}
