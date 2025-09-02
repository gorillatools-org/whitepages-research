package com.facebook.imagepipeline.postprocessors;

import android.graphics.Bitmap;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.nativecode.NativeBlurFilter;
import com.facebook.imagepipeline.request.BasePostprocessor;
import java.util.Locale;

public class IterativeBoxBlurPostProcessor extends BasePostprocessor {
    private final int mBlurRadius;
    private CacheKey mCacheKey;
    private final int mIterations;

    public IterativeBoxBlurPostProcessor(int i, int i2) {
        boolean z = false;
        Preconditions.checkArgument(Boolean.valueOf(i > 0));
        Preconditions.checkArgument(Boolean.valueOf(i2 > 0 ? true : z));
        this.mIterations = i;
        this.mBlurRadius = i2;
    }

    public void process(Bitmap bitmap) {
        NativeBlurFilter.iterativeBoxBlur(bitmap, this.mIterations, this.mBlurRadius);
    }

    public CacheKey getPostprocessorCacheKey() {
        if (this.mCacheKey == null) {
            this.mCacheKey = new SimpleCacheKey(String.format((Locale) null, "i%dr%d", new Object[]{Integer.valueOf(this.mIterations), Integer.valueOf(this.mBlurRadius)}));
        }
        return this.mCacheKey;
    }
}
