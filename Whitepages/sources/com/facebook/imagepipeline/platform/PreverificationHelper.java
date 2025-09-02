package com.facebook.imagepipeline.platform;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import com.facebook.soloader.DoNotOptimize;

@DoNotOptimize
public final class PreverificationHelper {
    @DoNotOptimize
    @TargetApi(26)
    public final boolean shouldUseHardwareBitmapConfig(Bitmap.Config config) {
        return config == Bitmap.Config.HARDWARE;
    }
}
