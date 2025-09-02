package com.facebook.imagepipeline.nativecode;

import android.graphics.Bitmap;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.Preconditions;

@DoNotStrip
public class Bitmaps {
    public static final /* synthetic */ int $r8$clinit = 0;

    @DoNotStrip
    private static native void nativeCopyBitmap(Bitmap bitmap, int i, Bitmap bitmap2, int i2, int i3);

    static {
        ImagePipelineNativeLoader.load();
    }

    @DoNotStrip
    public static void copyBitmap(Bitmap bitmap, Bitmap bitmap2) {
        boolean z = false;
        Preconditions.checkArgument(Boolean.valueOf(bitmap2.getConfig() == bitmap.getConfig()));
        Preconditions.checkArgument(Boolean.valueOf(bitmap.isMutable()));
        Preconditions.checkArgument(Boolean.valueOf(bitmap.getWidth() == bitmap2.getWidth()));
        if (bitmap.getHeight() == bitmap2.getHeight()) {
            z = true;
        }
        Preconditions.checkArgument(Boolean.valueOf(z));
        nativeCopyBitmap(bitmap, bitmap.getRowBytes(), bitmap2, bitmap2.getRowBytes(), bitmap.getHeight());
    }
}
