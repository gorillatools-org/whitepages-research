package com.facebook.drawee.drawable;

import android.graphics.drawable.Drawable;

public final class DrawableUtils {
    public static final DrawableUtils INSTANCE = new DrawableUtils();

    public static final int getOpacityFromColor(int i) {
        int i2 = i >>> 24;
        if (i2 != 0) {
            return i2 != 255 ? -3 : -1;
        }
        return -2;
    }

    public static final int multiplyColorAlpha(int i, int i2) {
        if (i2 == 255) {
            return i;
        }
        if (i2 == 0) {
            return i & 16777215;
        }
        return (i & 16777215) | ((((i >>> 24) * (i2 + (i2 >> 7))) >> 8) << 24);
    }

    private DrawableUtils() {
    }

    public static final void copyProperties(Drawable drawable, Drawable drawable2) {
        if (drawable2 != null && drawable != null && drawable != drawable2) {
            drawable.setBounds(drawable2.getBounds());
            drawable.setChangingConfigurations(drawable2.getChangingConfigurations());
            drawable.setLevel(drawable2.getLevel());
            drawable.setVisible(drawable2.isVisible(), false);
            drawable.setState(drawable2.getState());
        }
    }

    public static final void setDrawableProperties(Drawable drawable, DrawableProperties drawableProperties) {
        if (drawable != null && drawableProperties != null) {
            drawableProperties.applyTo(drawable);
        }
    }

    public static final void setCallbacks(Drawable drawable, Drawable.Callback callback, TransformCallback transformCallback) {
        if (drawable != null) {
            drawable.setCallback(callback);
            TransformAwareDrawable transformAwareDrawable = drawable instanceof TransformAwareDrawable ? (TransformAwareDrawable) drawable : null;
            if (transformAwareDrawable != null) {
                transformAwareDrawable.setTransformCallback(transformCallback);
            }
        }
    }
}
