package com.facebook.react.modules.fresco;

import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.react.bridge.ReadableMap;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ReactNetworkImageRequest extends ImageRequest {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final ImageCacheControl cacheControl;
    private final ReadableMap headers;

    public /* synthetic */ ReactNetworkImageRequest(ImageRequestBuilder imageRequestBuilder, ReadableMap readableMap, ImageCacheControl imageCacheControl, DefaultConstructorMarker defaultConstructorMarker) {
        this(imageRequestBuilder, readableMap, imageCacheControl);
    }

    public static final ReactNetworkImageRequest fromBuilderWithHeaders(ImageRequestBuilder imageRequestBuilder, ReadableMap readableMap) {
        return Companion.fromBuilderWithHeaders(imageRequestBuilder, readableMap);
    }

    public static final ReactNetworkImageRequest fromBuilderWithHeaders(ImageRequestBuilder imageRequestBuilder, ReadableMap readableMap, ImageCacheControl imageCacheControl) {
        return Companion.fromBuilderWithHeaders(imageRequestBuilder, readableMap, imageCacheControl);
    }

    public final ReadableMap getHeaders$ReactAndroid_release() {
        return this.headers;
    }

    public final ImageCacheControl getCacheControl$ReactAndroid_release() {
        return this.cacheControl;
    }

    private ReactNetworkImageRequest(ImageRequestBuilder imageRequestBuilder, ReadableMap readableMap, ImageCacheControl imageCacheControl) {
        super(imageRequestBuilder);
        this.headers = readableMap;
        this.cacheControl = imageCacheControl;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ReactNetworkImageRequest fromBuilderWithHeaders(ImageRequestBuilder imageRequestBuilder, ReadableMap readableMap) {
            Intrinsics.checkNotNullParameter(imageRequestBuilder, "builder");
            return fromBuilderWithHeaders$default(this, imageRequestBuilder, readableMap, (ImageCacheControl) null, 4, (Object) null);
        }

        private Companion() {
        }

        public static /* synthetic */ ReactNetworkImageRequest fromBuilderWithHeaders$default(Companion companion, ImageRequestBuilder imageRequestBuilder, ReadableMap readableMap, ImageCacheControl imageCacheControl, int i, Object obj) {
            if ((i & 4) != 0) {
                imageCacheControl = ImageCacheControl.DEFAULT;
            }
            return companion.fromBuilderWithHeaders(imageRequestBuilder, readableMap, imageCacheControl);
        }

        public final ReactNetworkImageRequest fromBuilderWithHeaders(ImageRequestBuilder imageRequestBuilder, ReadableMap readableMap, ImageCacheControl imageCacheControl) {
            Intrinsics.checkNotNullParameter(imageRequestBuilder, "builder");
            Intrinsics.checkNotNullParameter(imageCacheControl, "cacheControl");
            return new ReactNetworkImageRequest(imageRequestBuilder, readableMap, imageCacheControl, (DefaultConstructorMarker) null);
        }
    }
}
