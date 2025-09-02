package com.facebook.react.fabric.mounting;

import android.view.View;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.yoga.YogaMeasureMode;

public interface LayoutMetricsConversions {
    public static final Companion Companion = Companion.$$INSTANCE;

    static float getMaxSize(int i) {
        return Companion.getMaxSize(i);
    }

    static float getMinSize(int i) {
        return Companion.getMinSize(i);
    }

    static YogaMeasureMode getYogaMeasureMode(float f, float f2) {
        return Companion.getYogaMeasureMode(f, f2);
    }

    static float getYogaSize(float f, float f2) {
        return Companion.getYogaSize(f, f2);
    }

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final float getMinSize(int i) {
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            if (mode == 1073741824) {
                return (float) size;
            }
            return 0.0f;
        }

        public final float getMaxSize(int i) {
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            if (mode == 0) {
                return Float.POSITIVE_INFINITY;
            }
            return (float) size;
        }

        public final float getYogaSize(float f, float f2) {
            if (f == f2) {
                return PixelUtil.INSTANCE.dpToPx(f2);
            }
            if (Float.isInfinite(f2)) {
                return Float.POSITIVE_INFINITY;
            }
            return PixelUtil.INSTANCE.dpToPx(f2);
        }

        public final YogaMeasureMode getYogaMeasureMode(float f, float f2) {
            if (f == f2) {
                return YogaMeasureMode.EXACTLY;
            }
            if (Float.isInfinite(f2)) {
                return YogaMeasureMode.UNDEFINED;
            }
            return YogaMeasureMode.AT_MOST;
        }
    }
}
