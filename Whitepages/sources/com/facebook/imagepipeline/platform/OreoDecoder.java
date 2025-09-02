package com.facebook.imagepipeline.platform;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.core.util.Pools$Pool;
import com.facebook.imagepipeline.memory.BitmapPool;
import com.facebook.imageutils.BitmapUtil;
import kotlin.jvm.internal.Intrinsics;

public final class OreoDecoder extends DefaultDecoder {
    private final PlatformDecoderOptions platformDecoderOptions;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OreoDecoder(BitmapPool bitmapPool, Pools$Pool pools$Pool, PlatformDecoderOptions platformDecoderOptions2) {
        super(bitmapPool, pools$Pool, platformDecoderOptions2);
        Intrinsics.checkNotNullParameter(bitmapPool, "bitmapPool");
        Intrinsics.checkNotNullParameter(pools$Pool, "decodeBuffers");
        Intrinsics.checkNotNullParameter(platformDecoderOptions2, "platformDecoderOptions");
        this.platformDecoderOptions = platformDecoderOptions2;
    }

    public int getBitmapSize(int i, int i2, BitmapFactory.Options options) {
        Intrinsics.checkNotNullParameter(options, "options");
        Bitmap.Config config = options.outConfig;
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        return BitmapUtil.getSizeInByteForBitmap(i, i2, config);
    }
}
