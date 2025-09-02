package com.facebook.imagepipeline.cache;

import android.net.Uri;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class BitmapMemoryCacheKey implements CacheKey {
    private Object callerContext;
    private final int hash;
    private final ImageDecodeOptions imageDecodeOptions;
    private final long inBitmapCacheSince;
    private final CacheKey postprocessorCacheKey;
    private final String postprocessorName;
    private final ResizeOptions resizeOptions;
    private final RotationOptions rotationOptions;
    private final String sourceString;

    public boolean isResourceIdForDebugging() {
        return false;
    }

    public String toString() {
        String str = this.sourceString;
        ResizeOptions resizeOptions2 = this.resizeOptions;
        RotationOptions rotationOptions2 = this.rotationOptions;
        ImageDecodeOptions imageDecodeOptions2 = this.imageDecodeOptions;
        CacheKey cacheKey = this.postprocessorCacheKey;
        String str2 = this.postprocessorName;
        return "BitmapMemoryCacheKey(sourceString=" + str + ", resizeOptions=" + resizeOptions2 + ", rotationOptions=" + rotationOptions2 + ", imageDecodeOptions=" + imageDecodeOptions2 + ", postprocessorCacheKey=" + cacheKey + ", postprocessorName=" + str2 + ")";
    }

    public BitmapMemoryCacheKey(String str, ResizeOptions resizeOptions2, RotationOptions rotationOptions2, ImageDecodeOptions imageDecodeOptions2, CacheKey cacheKey, String str2) {
        Intrinsics.checkNotNullParameter(str, "sourceString");
        Intrinsics.checkNotNullParameter(rotationOptions2, "rotationOptions");
        Intrinsics.checkNotNullParameter(imageDecodeOptions2, "imageDecodeOptions");
        this.sourceString = str;
        this.resizeOptions = resizeOptions2;
        this.rotationOptions = rotationOptions2;
        this.imageDecodeOptions = imageDecodeOptions2;
        this.postprocessorCacheKey = cacheKey;
        this.postprocessorName = str2;
        this.hash = (((((((((str.hashCode() * 31) + (resizeOptions2 != null ? resizeOptions2.hashCode() : 0)) * 31) + rotationOptions2.hashCode()) * 31) + imageDecodeOptions2.hashCode()) * 31) + (cacheKey != null ? cacheKey.hashCode() : 0)) * 31) + (str2 != null ? str2.hashCode() : 0);
        this.inBitmapCacheSince = RealtimeSinceBootClock.get().now();
    }

    public final void setCallerContext(Object obj) {
        this.callerContext = obj;
    }

    public int hashCode() {
        return this.hash;
    }

    public boolean containsUri(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        String uriString = getUriString();
        String uri2 = uri.toString();
        Intrinsics.checkNotNullExpressionValue(uri2, "toString(...)");
        return StringsKt.contains$default((CharSequence) uriString, (CharSequence) uri2, false, 2, (Object) null);
    }

    public String getUriString() {
        return this.sourceString;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) BitmapMemoryCacheKey.class, (Object) obj != null ? obj.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.facebook.imagepipeline.cache.BitmapMemoryCacheKey");
        BitmapMemoryCacheKey bitmapMemoryCacheKey = (BitmapMemoryCacheKey) obj;
        if (!Intrinsics.areEqual((Object) this.sourceString, (Object) bitmapMemoryCacheKey.sourceString) || !Intrinsics.areEqual((Object) this.resizeOptions, (Object) bitmapMemoryCacheKey.resizeOptions) || !Intrinsics.areEqual((Object) this.rotationOptions, (Object) bitmapMemoryCacheKey.rotationOptions) || !Intrinsics.areEqual((Object) this.imageDecodeOptions, (Object) bitmapMemoryCacheKey.imageDecodeOptions) || !Intrinsics.areEqual((Object) this.postprocessorCacheKey, (Object) bitmapMemoryCacheKey.postprocessorCacheKey) || !Intrinsics.areEqual((Object) this.postprocessorName, (Object) bitmapMemoryCacheKey.postprocessorName)) {
            return false;
        }
        return true;
    }
}
