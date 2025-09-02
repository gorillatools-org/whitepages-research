package kotlin.math;

import androidx.customview.widget.ExploreByTouchHelper;

abstract class MathKt__MathJVMKt extends MathKt__MathHKt {
    public static int roundToInt(double d) {
        if (Double.isNaN(d)) {
            throw new IllegalArgumentException("Cannot round NaN value.");
        } else if (d > 2.147483647E9d) {
            return Integer.MAX_VALUE;
        } else {
            if (d < -2.147483648E9d) {
                return ExploreByTouchHelper.INVALID_ID;
            }
            return (int) Math.round(d);
        }
    }

    public static int roundToInt(float f) {
        if (!Float.isNaN(f)) {
            return Math.round(f);
        }
        throw new IllegalArgumentException("Cannot round NaN value.");
    }

    public static int getSign(long j) {
        return Long.signum(j);
    }
}
