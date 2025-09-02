package com.facebook.imagepipeline.image;

import android.graphics.drawable.Drawable;

public final class DefaultCloseableXml extends DefaultCloseableImage implements CloseableXml {
    private boolean closed;
    private Drawable drawable;

    public DefaultCloseableXml(Drawable drawable2) {
        this.drawable = drawable2;
    }

    public int getSizeInBytes() {
        return getWidth() * getHeight() * 4;
    }

    public void close() {
        this.drawable = null;
        this.closed = true;
    }

    public boolean isClosed() {
        return this.closed;
    }

    public int getWidth() {
        Drawable drawable2 = this.drawable;
        if (drawable2 != null) {
            Integer valueOf = Integer.valueOf(drawable2.getIntrinsicWidth());
            if (valueOf.intValue() < 0) {
                valueOf = null;
            }
            if (valueOf != null) {
                return valueOf.intValue();
            }
        }
        return 0;
    }

    public int getHeight() {
        Drawable drawable2 = this.drawable;
        if (drawable2 != null) {
            Integer valueOf = Integer.valueOf(drawable2.getIntrinsicHeight());
            if (valueOf.intValue() < 0) {
                valueOf = null;
            }
            if (valueOf != null) {
                return valueOf.intValue();
            }
        }
        return 0;
    }

    public Drawable buildDrawable() {
        Drawable.ConstantState constantState;
        Drawable drawable2 = this.drawable;
        if (drawable2 == null || (constantState = drawable2.getConstantState()) == null) {
            return null;
        }
        return constantState.newDrawable();
    }
}
