package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import com.facebook.common.internal.Sets;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

public final class DummyTrackingInUseBitmapPool implements BitmapPool {
    private final Set inUseValues;

    public DummyTrackingInUseBitmapPool() {
        Set newIdentityHashSet = Sets.newIdentityHashSet();
        Intrinsics.checkNotNullExpressionValue(newIdentityHashSet, "newIdentityHashSet(...)");
        this.inUseValues = newIdentityHashSet;
    }

    public Bitmap get(int i) {
        Bitmap createBitmap = Bitmap.createBitmap(1, (int) Math.ceil(((double) i) / 2.0d), Bitmap.Config.RGB_565);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        this.inUseValues.add(createBitmap);
        return createBitmap;
    }

    public void release(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "value");
        this.inUseValues.remove(bitmap);
        bitmap.recycle();
    }
}
