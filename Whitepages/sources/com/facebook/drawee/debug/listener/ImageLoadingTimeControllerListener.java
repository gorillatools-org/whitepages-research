package com.facebook.drawee.debug.listener;

import android.graphics.drawable.Animatable;
import com.facebook.drawee.controller.BaseControllerListener;
import kotlin.jvm.internal.Intrinsics;

public final class ImageLoadingTimeControllerListener extends BaseControllerListener {
    private long finalImageSetTimeMs = -1;
    private final ImageLoadingTimeListener imageLoadingTimeListener;
    private long requestSubmitTimeMs = -1;

    public ImageLoadingTimeControllerListener(ImageLoadingTimeListener imageLoadingTimeListener2) {
        this.imageLoadingTimeListener = imageLoadingTimeListener2;
    }

    public void onSubmit(String str, Object obj) {
        Intrinsics.checkNotNullParameter(str, "id");
        this.requestSubmitTimeMs = System.currentTimeMillis();
    }

    public void onFinalImageSet(String str, Object obj, Animatable animatable) {
        Intrinsics.checkNotNullParameter(str, "id");
        long currentTimeMillis = System.currentTimeMillis();
        this.finalImageSetTimeMs = currentTimeMillis;
        ImageLoadingTimeListener imageLoadingTimeListener2 = this.imageLoadingTimeListener;
        if (imageLoadingTimeListener2 != null) {
            imageLoadingTimeListener2.onFinalImageSet(currentTimeMillis - this.requestSubmitTimeMs);
        }
    }
}
