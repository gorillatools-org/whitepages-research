package com.facebook.drawee.backends.pipeline;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.facebook.drawee.drawable.OrientedDrawable;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.systrace.FrescoSystrace;

public class DefaultDrawableFactory implements DrawableFactory {
    private final DrawableFactory mAnimatedDrawableFactory;
    private final Resources mResources;
    private final DrawableFactory mXmlDrawableFactory;

    public boolean supportsImageType(CloseableImage closeableImage) {
        return true;
    }

    public DefaultDrawableFactory(Resources resources, DrawableFactory drawableFactory, DrawableFactory drawableFactory2) {
        this.mResources = resources;
        this.mAnimatedDrawableFactory = drawableFactory;
        this.mXmlDrawableFactory = drawableFactory2;
    }

    public Drawable createDrawable(CloseableImage closeableImage) {
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("DefaultDrawableFactory#createDrawable");
            }
            if (closeableImage instanceof CloseableStaticBitmap) {
                CloseableStaticBitmap closeableStaticBitmap = (CloseableStaticBitmap) closeableImage;
                BitmapDrawable bitmapDrawable = new BitmapDrawable(this.mResources, closeableStaticBitmap.getUnderlyingBitmap());
                if (hasTransformableRotationAngle(closeableStaticBitmap) || hasTransformableExifOrientation(closeableStaticBitmap)) {
                    OrientedDrawable orientedDrawable = new OrientedDrawable(bitmapDrawable, closeableStaticBitmap.getRotationAngle(), closeableStaticBitmap.getExifOrientation());
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                    }
                    return orientedDrawable;
                }
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return bitmapDrawable;
            }
            DrawableFactory drawableFactory = this.mAnimatedDrawableFactory;
            if (drawableFactory == null || !drawableFactory.supportsImageType(closeableImage)) {
                DrawableFactory drawableFactory2 = this.mXmlDrawableFactory;
                if (drawableFactory2 != null && drawableFactory2.supportsImageType(closeableImage)) {
                    Drawable createDrawable = this.mXmlDrawableFactory.createDrawable(closeableImage);
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                    }
                    return createDrawable;
                } else if (!FrescoSystrace.isTracing()) {
                    return null;
                } else {
                    FrescoSystrace.endSection();
                    return null;
                }
            } else {
                Drawable createDrawable2 = this.mAnimatedDrawableFactory.createDrawable(closeableImage);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return createDrawable2;
            }
        } catch (Throwable th) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            throw th;
        }
    }

    private static boolean hasTransformableRotationAngle(CloseableStaticBitmap closeableStaticBitmap) {
        return (closeableStaticBitmap.getRotationAngle() == 0 || closeableStaticBitmap.getRotationAngle() == -1) ? false : true;
    }

    private static boolean hasTransformableExifOrientation(CloseableStaticBitmap closeableStaticBitmap) {
        if (closeableStaticBitmap.getExifOrientation() == 1 || closeableStaticBitmap.getExifOrientation() == 0) {
            return false;
        }
        return true;
    }
}
