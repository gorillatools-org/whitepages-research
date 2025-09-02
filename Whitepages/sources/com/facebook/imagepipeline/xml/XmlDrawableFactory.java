package com.facebook.imagepipeline.xml;

import android.graphics.drawable.Drawable;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableXml;
import kotlin.jvm.internal.Intrinsics;

public final class XmlDrawableFactory implements DrawableFactory {
    public boolean supportsImageType(CloseableImage closeableImage) {
        Intrinsics.checkNotNullParameter(closeableImage, "image");
        return closeableImage instanceof CloseableXml;
    }

    public Drawable createDrawable(CloseableImage closeableImage) {
        Intrinsics.checkNotNullParameter(closeableImage, "image");
        CloseableXml closeableXml = closeableImage instanceof CloseableXml ? (CloseableXml) closeableImage : null;
        if (closeableXml != null) {
            return closeableXml.buildDrawable();
        }
        return null;
    }
}
