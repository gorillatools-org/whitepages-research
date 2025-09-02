package androidx.core.graphics;

import android.graphics.Bitmap;

public abstract class BitmapCompat {
    public static int getAllocationByteCount(Bitmap bitmap) {
        return bitmap.getAllocationByteCount();
    }
}
