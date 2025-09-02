package com.facebook.imagepipeline.core;

import android.net.Uri;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Predicate;

public final /* synthetic */ class ImagePipeline$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ Uri f$0;

    public /* synthetic */ ImagePipeline$$ExternalSyntheticLambda0(Uri uri) {
        this.f$0 = uri;
    }

    public final boolean apply(Object obj) {
        return ImagePipeline.predicateForUri$lambda$16(this.f$0, (CacheKey) obj);
    }
}
