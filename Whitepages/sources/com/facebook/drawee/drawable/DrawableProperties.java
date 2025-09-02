package com.facebook.drawee.drawable;

import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class DrawableProperties {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private int alpha = -1;
    private ColorFilter colorFilter;
    private int dither = -1;
    private int filterBitmap = -1;
    private boolean isSetColorFilter;

    public final void setAlpha(int i) {
        this.alpha = i;
    }

    public final void setColorFilter(ColorFilter colorFilter2) {
        this.colorFilter = colorFilter2;
        this.isSetColorFilter = colorFilter2 != null;
    }

    public final void setDither(boolean z) {
        this.dither = z ? 1 : 0;
    }

    public final void setFilterBitmap(boolean z) {
        this.filterBitmap = z ? 1 : 0;
    }

    public final void applyTo(Drawable drawable) {
        if (drawable != null) {
            int i = this.alpha;
            if (i != -1) {
                drawable.setAlpha(i);
            }
            if (this.isSetColorFilter) {
                drawable.setColorFilter(this.colorFilter);
            }
            int i2 = this.dither;
            boolean z = false;
            if (i2 != -1) {
                drawable.setDither(i2 != 0);
            }
            int i3 = this.filterBitmap;
            if (i3 != -1) {
                if (i3 != 0) {
                    z = true;
                }
                drawable.setFilterBitmap(z);
            }
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
