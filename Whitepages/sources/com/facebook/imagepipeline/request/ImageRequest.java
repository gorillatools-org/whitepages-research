package com.facebook.imagepipeline.request;

import android.net.Uri;
import android.os.Build;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Fn;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.media.MediaUtils;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.core.DownsampleMode;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.memory.helper.HashCode;
import com.salesforce.marketingcloud.b;
import java.io.File;
import java.util.HashMap;

public class ImageRequest {
    public static final Fn REQUEST_TO_URI_FN = new Fn() {
        public Uri apply(ImageRequest imageRequest) {
            if (imageRequest != null) {
                return imageRequest.getSourceUri();
            }
            return null;
        }
    };
    private static boolean sCacheHashcode;
    private static boolean sUseCachedHashcodeInEquals;
    private final BytesRange mBytesRange;
    private final CacheChoice mCacheChoice;
    protected int mCachesDisabled;
    private final Boolean mDecodePrefetches;
    private final int mDelayMs;
    private final String mDiskCacheId;
    private final DownsampleMode mDownsampleOverride;
    private int mHashcode;
    private final ImageDecodeOptions mImageDecodeOptions;
    private final boolean mIsDiskCacheEnabled;
    private final boolean mIsMemoryCacheEnabled;
    private final boolean mLoadThumbnailOnly;
    private final boolean mLocalThumbnailPreviewsEnabled;
    private final RequestLevel mLowestPermittedRequestLevel;
    private final Postprocessor mPostprocessor;
    private final boolean mProgressiveRenderingEnabled;
    private final RequestListener mRequestListener;
    private final Priority mRequestPriority;
    private final ResizeOptions mResizeOptions;
    private final Boolean mResizingAllowedOverride;
    private final RotationOptions mRotationOptions;
    private File mSourceFile;
    private final Uri mSourceUri;
    private final int mSourceUriType;

    public enum CacheChoice {
        SMALL,
        DEFAULT,
        DYNAMIC
    }

    public static ImageRequest fromFile(File file) {
        if (file == null) {
            return null;
        }
        return fromUri(UriUtil.getUriForFile(file));
    }

    public static ImageRequest fromUri(Uri uri) {
        if (uri == null) {
            return null;
        }
        return ImageRequestBuilder.newBuilderWithSource(uri).build();
    }

    public static ImageRequest fromUri(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        return fromUri(Uri.parse(str));
    }

    protected ImageRequest(ImageRequestBuilder imageRequestBuilder) {
        RotationOptions rotationOptions;
        this.mCacheChoice = imageRequestBuilder.getCacheChoice();
        Uri sourceUri = imageRequestBuilder.getSourceUri();
        this.mSourceUri = sourceUri;
        this.mSourceUriType = getSourceUriType(sourceUri);
        this.mProgressiveRenderingEnabled = imageRequestBuilder.isProgressiveRenderingEnabled();
        this.mLocalThumbnailPreviewsEnabled = imageRequestBuilder.isLocalThumbnailPreviewsEnabled();
        this.mLoadThumbnailOnly = imageRequestBuilder.getLoadThumbnailOnly();
        this.mImageDecodeOptions = imageRequestBuilder.getImageDecodeOptions();
        this.mResizeOptions = imageRequestBuilder.getResizeOptions();
        if (imageRequestBuilder.getRotationOptions() == null) {
            rotationOptions = RotationOptions.autoRotate();
        } else {
            rotationOptions = imageRequestBuilder.getRotationOptions();
        }
        this.mRotationOptions = rotationOptions;
        this.mBytesRange = imageRequestBuilder.getBytesRange();
        this.mRequestPriority = imageRequestBuilder.getRequestPriority();
        this.mLowestPermittedRequestLevel = imageRequestBuilder.getLowestPermittedRequestLevel();
        boolean isDiskCacheEnabled = imageRequestBuilder.isDiskCacheEnabled();
        this.mIsDiskCacheEnabled = isDiskCacheEnabled;
        int cachesDisabled = imageRequestBuilder.getCachesDisabled();
        this.mCachesDisabled = !isDiskCacheEnabled ? cachesDisabled | 48 : cachesDisabled;
        this.mIsMemoryCacheEnabled = imageRequestBuilder.isMemoryCacheEnabled();
        this.mDecodePrefetches = imageRequestBuilder.shouldDecodePrefetches();
        this.mPostprocessor = imageRequestBuilder.getPostprocessor();
        this.mRequestListener = imageRequestBuilder.getRequestListener();
        this.mResizingAllowedOverride = imageRequestBuilder.getResizingAllowedOverride();
        this.mDownsampleOverride = imageRequestBuilder.getDownsampleOverride();
        this.mDelayMs = imageRequestBuilder.getDelayMs();
        this.mDiskCacheId = imageRequestBuilder.getDiskCacheId();
    }

    public CacheChoice getCacheChoice() {
        return this.mCacheChoice;
    }

    public Uri getSourceUri() {
        return this.mSourceUri;
    }

    public int getSourceUriType() {
        return this.mSourceUriType;
    }

    public int getPreferredWidth() {
        ResizeOptions resizeOptions = this.mResizeOptions;
        return resizeOptions != null ? resizeOptions.width : b.u;
    }

    public int getPreferredHeight() {
        ResizeOptions resizeOptions = this.mResizeOptions;
        return resizeOptions != null ? resizeOptions.height : b.u;
    }

    public ResizeOptions getResizeOptions() {
        return this.mResizeOptions;
    }

    public RotationOptions getRotationOptions() {
        return this.mRotationOptions;
    }

    @Deprecated
    public boolean getAutoRotateEnabled() {
        return this.mRotationOptions.useImageMetadata();
    }

    public BytesRange getBytesRange() {
        return this.mBytesRange;
    }

    public ImageDecodeOptions getImageDecodeOptions() {
        return this.mImageDecodeOptions;
    }

    public boolean getProgressiveRenderingEnabled() {
        return this.mProgressiveRenderingEnabled;
    }

    public boolean getLocalThumbnailPreviewsEnabled() {
        return this.mLocalThumbnailPreviewsEnabled;
    }

    public boolean getLoadThumbnailOnlyForAndroidSdkAboveQ() {
        return Build.VERSION.SDK_INT >= 29 && this.mLoadThumbnailOnly;
    }

    public Priority getPriority() {
        return this.mRequestPriority;
    }

    public RequestLevel getLowestPermittedRequestLevel() {
        return this.mLowestPermittedRequestLevel;
    }

    public int getCachesDisabled() {
        return this.mCachesDisabled;
    }

    public boolean isDiskCacheEnabled() {
        return this.mIsDiskCacheEnabled;
    }

    public boolean isCacheEnabled(int i) {
        return (i & getCachesDisabled()) == 0;
    }

    public boolean isMemoryCacheEnabled() {
        return this.mIsMemoryCacheEnabled;
    }

    public Boolean shouldDecodePrefetches() {
        return this.mDecodePrefetches;
    }

    public Boolean getResizingAllowedOverride() {
        return this.mResizingAllowedOverride;
    }

    public DownsampleMode getDownsampleOverride() {
        return this.mDownsampleOverride;
    }

    public int getDelayMs() {
        return this.mDelayMs;
    }

    public synchronized File getSourceFile() {
        try {
            if (this.mSourceFile == null) {
                Preconditions.checkNotNull(this.mSourceUri.getPath());
                this.mSourceFile = new File(this.mSourceUri.getPath());
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return this.mSourceFile;
    }

    public Postprocessor getPostprocessor() {
        return this.mPostprocessor;
    }

    public RequestListener getRequestListener() {
        return this.mRequestListener;
    }

    public String getDiskCacheId() {
        return this.mDiskCacheId;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ImageRequest)) {
            return false;
        }
        ImageRequest imageRequest = (ImageRequest) obj;
        if (sUseCachedHashcodeInEquals) {
            int i = this.mHashcode;
            int i2 = imageRequest.mHashcode;
            if (!(i == 0 || i2 == 0 || i == i2)) {
                return false;
            }
        }
        if (this.mLocalThumbnailPreviewsEnabled != imageRequest.mLocalThumbnailPreviewsEnabled || this.mIsDiskCacheEnabled != imageRequest.mIsDiskCacheEnabled || this.mIsMemoryCacheEnabled != imageRequest.mIsMemoryCacheEnabled || !Objects.equal(this.mSourceUri, imageRequest.mSourceUri) || !Objects.equal(this.mCacheChoice, imageRequest.mCacheChoice) || !Objects.equal(this.mDiskCacheId, imageRequest.mDiskCacheId) || !Objects.equal(this.mSourceFile, imageRequest.mSourceFile) || !Objects.equal(this.mBytesRange, imageRequest.mBytesRange) || !Objects.equal(this.mImageDecodeOptions, imageRequest.mImageDecodeOptions) || !Objects.equal(this.mResizeOptions, imageRequest.mResizeOptions) || !Objects.equal(this.mRequestPriority, imageRequest.mRequestPriority) || !Objects.equal(this.mLowestPermittedRequestLevel, imageRequest.mLowestPermittedRequestLevel) || !Objects.equal(Integer.valueOf(this.mCachesDisabled), Integer.valueOf(imageRequest.mCachesDisabled)) || !Objects.equal(this.mDecodePrefetches, imageRequest.mDecodePrefetches) || !Objects.equal(this.mResizingAllowedOverride, imageRequest.mResizingAllowedOverride) || !Objects.equal(this.mDownsampleOverride, imageRequest.mDownsampleOverride) || !Objects.equal(this.mRotationOptions, imageRequest.mRotationOptions) || this.mLoadThumbnailOnly != imageRequest.mLoadThumbnailOnly) {
            return false;
        }
        Postprocessor postprocessor = this.mPostprocessor;
        CacheKey cacheKey = null;
        CacheKey postprocessorCacheKey = postprocessor != null ? postprocessor.getPostprocessorCacheKey() : null;
        Postprocessor postprocessor2 = imageRequest.mPostprocessor;
        if (postprocessor2 != null) {
            cacheKey = postprocessor2.getPostprocessorCacheKey();
        }
        if (Objects.equal(postprocessorCacheKey, cacheKey) && this.mDelayMs == imageRequest.mDelayMs) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        boolean z = sCacheHashcode;
        int i = z ? this.mHashcode : 0;
        if (i == 0) {
            Postprocessor postprocessor = this.mPostprocessor;
            i = HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(HashCode.extend(0, this.mCacheChoice), this.mSourceUri), Boolean.valueOf(this.mLocalThumbnailPreviewsEnabled)), this.mBytesRange), this.mRequestPriority), this.mLowestPermittedRequestLevel), Integer.valueOf(this.mCachesDisabled)), Boolean.valueOf(this.mIsDiskCacheEnabled)), Boolean.valueOf(this.mIsMemoryCacheEnabled)), this.mImageDecodeOptions), this.mDecodePrefetches), this.mResizeOptions), this.mRotationOptions), postprocessor != null ? postprocessor.getPostprocessorCacheKey() : null), this.mResizingAllowedOverride), this.mDownsampleOverride), Integer.valueOf(this.mDelayMs)), Boolean.valueOf(this.mLoadThumbnailOnly));
            if (z) {
                this.mHashcode = i;
            }
        }
        return i;
    }

    public void recordHashCode(HashMap<String, Integer> hashMap) {
        Postprocessor postprocessor = this.mPostprocessor;
        CacheKey postprocessorCacheKey = postprocessor != null ? postprocessor.getPostprocessorCacheKey() : null;
        hashMap.put("ImageRequest", Integer.valueOf(hashCode()));
        hashMap.put("ImageRequest.mSourceUri", Integer.valueOf(getHashCodeHelper(this.mSourceUri)));
        hashMap.put("ImageRequest.mLocalThumbnailPreviewsEnabled", Integer.valueOf(getHashCodeHelper(Boolean.valueOf(this.mLocalThumbnailPreviewsEnabled))));
        hashMap.put("ImageRequest.mBytesRange", Integer.valueOf(getHashCodeHelper(this.mBytesRange)));
        hashMap.put("ImageRequest.mRequestPriority", Integer.valueOf(getHashCodeHelper(this.mRequestPriority)));
        hashMap.put("ImageRequest.mLowestPermittedRequestLevel", Integer.valueOf(getHashCodeHelper(this.mLowestPermittedRequestLevel)));
        hashMap.put("ImageRequest.mCachesDisabled", Integer.valueOf(getHashCodeHelper(Integer.valueOf(this.mCachesDisabled))));
        hashMap.put("ImageRequest.mIsDiskCacheEnabled", Integer.valueOf(getHashCodeHelper(Boolean.valueOf(this.mIsDiskCacheEnabled))));
        hashMap.put("ImageRequest.mIsMemoryCacheEnabled", Integer.valueOf(getHashCodeHelper(Boolean.valueOf(this.mIsMemoryCacheEnabled))));
        hashMap.put("ImageRequest.mImageDecodeOptions", Integer.valueOf(getHashCodeHelper(this.mImageDecodeOptions)));
        hashMap.put("ImageRequest.mDecodePrefetches", Integer.valueOf(getHashCodeHelper(this.mDecodePrefetches)));
        hashMap.put("ImageRequest.mSoumResizeOptionsrceUri", Integer.valueOf(getHashCodeHelper(this.mResizeOptions)));
        hashMap.put("ImageRequest.mRotationOptions", Integer.valueOf(getHashCodeHelper(this.mRotationOptions)));
        hashMap.put("ImageRequest.postprocessorCacheKey", Integer.valueOf(getHashCodeHelper(postprocessorCacheKey)));
        hashMap.put("ImageRequest.mResizingAllowedOverride", Integer.valueOf(getHashCodeHelper(this.mResizingAllowedOverride)));
        hashMap.put("ImageRequest.mDownsampleOverride", Integer.valueOf(getHashCodeHelper(this.mDownsampleOverride)));
        hashMap.put("ImageRequest.mDelayMs", Integer.valueOf(getHashCodeHelper(Integer.valueOf(this.mDelayMs))));
        hashMap.put("ImageRequest.mLoadThumbnailOnly", Integer.valueOf(getHashCodeHelper(Boolean.valueOf(this.mLoadThumbnailOnly))));
    }

    private static int getHashCodeHelper(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public String toString() {
        return Objects.toStringHelper(this).add("uri", (Object) this.mSourceUri).add("cacheChoice", (Object) this.mCacheChoice).add("decodeOptions", (Object) this.mImageDecodeOptions).add("postprocessor", (Object) this.mPostprocessor).add("priority", (Object) this.mRequestPriority).add("resizeOptions", (Object) this.mResizeOptions).add("rotationOptions", (Object) this.mRotationOptions).add("bytesRange", (Object) this.mBytesRange).add("resizingAllowedOverride", (Object) this.mResizingAllowedOverride).add("downsampleOverride", (Object) this.mDownsampleOverride).add("progressiveRenderingEnabled", this.mProgressiveRenderingEnabled).add("localThumbnailPreviewsEnabled", this.mLocalThumbnailPreviewsEnabled).add("loadThumbnailOnly", this.mLoadThumbnailOnly).add("lowestPermittedRequestLevel", (Object) this.mLowestPermittedRequestLevel).add("cachesDisabled", this.mCachesDisabled).add("isDiskCacheEnabled", this.mIsDiskCacheEnabled).add("isMemoryCacheEnabled", this.mIsMemoryCacheEnabled).add("decodePrefetches", (Object) this.mDecodePrefetches).add("delayMs", this.mDelayMs).toString();
    }

    public enum RequestLevel {
        FULL_FETCH(1),
        DISK_CACHE(2),
        ENCODED_MEMORY_CACHE(3),
        BITMAP_MEMORY_CACHE(4);
        
        private int mValue;

        private RequestLevel(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }

        public static RequestLevel getMax(RequestLevel requestLevel, RequestLevel requestLevel2) {
            return requestLevel.getValue() > requestLevel2.getValue() ? requestLevel : requestLevel2;
        }
    }

    private static int getSourceUriType(Uri uri) {
        if (uri == null) {
            return -1;
        }
        if (UriUtil.isNetworkUri(uri)) {
            return 0;
        }
        if (uri.getPath() != null && UriUtil.isLocalFileUri(uri)) {
            return MediaUtils.isVideo(MediaUtils.extractMime(uri.getPath())) ? 2 : 3;
        }
        if (UriUtil.isLocalContentUri(uri)) {
            return 4;
        }
        if (UriUtil.isLocalAssetUri(uri)) {
            return 5;
        }
        if (UriUtil.isLocalResourceUri(uri)) {
            return 6;
        }
        if (UriUtil.isDataUri(uri)) {
            return 7;
        }
        if (UriUtil.isQualifiedResourceUri(uri)) {
            return 8;
        }
        return -1;
    }

    public static void setUseCachedHashcodeInEquals(boolean z) {
        sUseCachedHashcodeInEquals = z;
    }

    public static void setCacheHashcode(boolean z) {
        sCacheHashcode = z;
    }
}
