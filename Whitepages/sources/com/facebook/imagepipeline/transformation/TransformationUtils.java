package com.facebook.imagepipeline.transformation;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import kotlin.jvm.internal.Intrinsics;

public final class TransformationUtils {
    public static final TransformationUtils INSTANCE = new TransformationUtils();

    private TransformationUtils() {
    }

    public static final boolean maybeApplyTransformation(BitmapTransformation bitmapTransformation, CloseableReference closeableReference) {
        if (bitmapTransformation == null || closeableReference == null) {
            return false;
        }
        Object obj = closeableReference.get();
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        Bitmap bitmap = (Bitmap) obj;
        if (bitmapTransformation.modifiesTransparency()) {
            bitmap.setHasAlpha(true);
        }
        bitmapTransformation.transform(bitmap);
        return true;
    }
}
