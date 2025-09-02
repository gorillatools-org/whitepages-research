package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import kotlin.jvm.internal.Intrinsics;

public final class DummyBitmapPool implements BitmapPool {
    public Bitmap get(int i) {
        Bitmap createBitmap = Bitmap.createBitmap(1, (int) Math.ceil(((double) i) / 2.0d), Bitmap.Config.RGB_565);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        return createBitmap;
    }

    public void release(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "value");
        bitmap.recycle();
    }
}
