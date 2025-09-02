package com.facebook.imagepipeline.nativecode;

import android.graphics.Bitmap;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.Preconditions;

@DoNotStrip
public class NativeBlurFilter {
    @DoNotStrip
    private static native void nativeIterativeBoxBlur(Bitmap bitmap, int i, int i2);

    static {
        NativeFiltersLoader.load();
    }

    public static void iterativeBoxBlur(Bitmap bitmap, int i, int i2) {
        Preconditions.checkNotNull(bitmap);
        boolean z = false;
        Preconditions.checkArgument(Boolean.valueOf(i > 0));
        if (i2 > 0) {
            z = true;
        }
        Preconditions.checkArgument(Boolean.valueOf(z));
        nativeIterativeBoxBlur(bitmap, i, i2);
    }
}
