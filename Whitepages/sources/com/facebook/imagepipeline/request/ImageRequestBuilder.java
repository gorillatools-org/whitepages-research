package com.facebook.imagepipeline.request;

import android.net.Uri;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.core.DownsampleMode;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.HashSet;
import java.util.Set;

public class ImageRequestBuilder {
    private static final Set CUSTOM_NETWORK_SCHEMES = new HashSet();
    private BytesRange mBytesRange = null;
    private ImageRequest.CacheChoice mCacheChoice = ImageRequest.CacheChoice.DEFAULT;
    private int mCachesDisabled = 0;
    private Boolean mDecodePrefetches = null;
    private int mDelayMs;
    private String mDiskCacheId = null;
    private DownsampleMode mDownsampleOverride = null;
    private ImageDecodeOptions mImageDecodeOptions = ImageDecodeOptions.defaults();
    private boolean mLoadThumbnailOnly = false;
    private boolean mLocalThumbnailPreviewsEnabled = false;
    private ImageRequest.RequestLevel mLowestPermittedRequestLevel = ImageRequest.RequestLevel.FULL_FETCH;
    private Postprocessor mPostprocessor = null;
    private boolean mProgressiveRenderingEnabled = ImagePipelineConfig.getDefaultImageRequestConfig().isProgressiveRenderingEnabled();
    private RequestListener mRequestListener;
    private Priority mRequestPriority = Priority.HIGH;
    private ResizeOptions mResizeOptions = null;
    private Boolean mResizingAllowedOverride = null;
    private RotationOptions mRotationOptions = null;
    private Uri mSourceUri = null;

    public static ImageRequestBuilder newBuilderWithSource(Uri uri) {
        return new ImageRequestBuilder().setSource(uri);
    }

    public static ImageRequestBuilder fromRequest(ImageRequest imageRequest) {
        return newBuilderWithSource(imageRequest.getSourceUri()).setImageDecodeOptions(imageRequest.getImageDecodeOptions()).setBytesRange(imageRequest.getBytesRange()).setCacheChoice(imageRequest.getCacheChoice()).setLocalThumbnailPreviewsEnabled(imageRequest.getLocalThumbnailPreviewsEnabled()).setLoadThumbnailOnly(imageRequest.getLoadThumbnailOnlyForAndroidSdkAboveQ()).setLowestPermittedRequestLevel(imageRequest.getLowestPermittedRequestLevel()).setCachesDisabled(imageRequest.getCachesDisabled()).setPostprocessor(imageRequest.getPostprocessor()).setProgressiveRenderingEnabled(imageRequest.getProgressiveRenderingEnabled()).setRequestPriority(imageRequest.getPriority()).setResizeOptions(imageRequest.getResizeOptions()).setRequestListener(imageRequest.getRequestListener()).setRotationOptions(imageRequest.getRotationOptions()).setShouldDecodePrefetches(imageRequest.shouldDecodePrefetches()).setDelayMs(imageRequest.getDelayMs()).setDiskCacheId(imageRequest.getDiskCacheId()).setDownsampleOverride(imageRequest.getDownsampleOverride()).setResizingAllowedOverride(imageRequest.getResizingAllowedOverride());
    }

    private ImageRequestBuilder() {
    }

    public ImageRequestBuilder setSource(Uri uri) {
        Preconditions.checkNotNull(uri);
        this.mSourceUri = uri;
        return this;
    }

    public Uri getSourceUri() {
        return this.mSourceUri;
    }

    public ImageRequestBuilder setLowestPermittedRequestLevel(ImageRequest.RequestLevel requestLevel) {
        this.mLowestPermittedRequestLevel = requestLevel;
        return this;
    }

    public ImageRequest.RequestLevel getLowestPermittedRequestLevel() {
        return this.mLowestPermittedRequestLevel;
    }

    private ImageRequestBuilder setCachesDisabled(int i) {
        this.mCachesDisabled = i;
        if (this.mCacheChoice != ImageRequest.CacheChoice.DYNAMIC) {
            this.mDiskCacheId = null;
        }
        return this;
    }

    public int getCachesDisabled() {
        return this.mCachesDisabled;
    }

    public ImageRequestBuilder setAutoRotateEnabled(boolean z) {
        if (z) {
            return setRotationOptions(RotationOptions.autoRotate());
        }
        return setRotationOptions(RotationOptions.disableRotation());
    }

    public ImageRequestBuilder setResizeOptions(ResizeOptions resizeOptions) {
        this.mResizeOptions = resizeOptions;
        return this;
    }

    public ResizeOptions getResizeOptions() {
        return this.mResizeOptions;
    }

    public ImageRequestBuilder setRotationOptions(RotationOptions rotationOptions) {
        this.mRotationOptions = rotationOptions;
        return this;
    }

    public RotationOptions getRotationOptions() {
        return this.mRotationOptions;
    }

    public ImageRequestBuilder setBytesRange(BytesRange bytesRange) {
        this.mBytesRange = bytesRange;
        return this;
    }

    public BytesRange getBytesRange() {
        return this.mBytesRange;
    }

    public ImageRequestBuilder setImageDecodeOptions(ImageDecodeOptions imageDecodeOptions) {
        this.mImageDecodeOptions = imageDecodeOptions;
        return this;
    }

    public ImageDecodeOptions getImageDecodeOptions() {
        return this.mImageDecodeOptions;
    }

    public ImageRequestBuilder setCacheChoice(ImageRequest.CacheChoice cacheChoice) {
        this.mCacheChoice = cacheChoice;
        return this;
    }

    public ImageRequest.CacheChoice getCacheChoice() {
        return this.mCacheChoice;
    }

    public ImageRequestBuilder setProgressiveRenderingEnabled(boolean z) {
        this.mProgressiveRenderingEnabled = z;
        return this;
    }

    public boolean isProgressiveRenderingEnabled() {
        return this.mProgressiveRenderingEnabled;
    }

    public ImageRequestBuilder setLocalThumbnailPreviewsEnabled(boolean z) {
        this.mLocalThumbnailPreviewsEnabled = z;
        return this;
    }

    public boolean isLocalThumbnailPreviewsEnabled() {
        return this.mLocalThumbnailPreviewsEnabled;
    }

    public ImageRequestBuilder setLoadThumbnailOnly(boolean z) {
        this.mLoadThumbnailOnly = z;
        return this;
    }

    public boolean getLoadThumbnailOnly() {
        return this.mLoadThumbnailOnly;
    }

    public static boolean isCustomNetworkUri(Uri uri) {
        Set<String> set = CUSTOM_NETWORK_SCHEMES;
        if (!(set == null || uri == null)) {
            for (String equals : set) {
                if (equals.equals(uri.getScheme())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isDiskCacheEnabled() {
        return (this.mCachesDisabled & 48) == 0 && (UriUtil.isNetworkUri(this.mSourceUri) || isCustomNetworkUri(this.mSourceUri));
    }

    public boolean isMemoryCacheEnabled() {
        return (this.mCachesDisabled & 15) == 0;
    }

    public ImageRequestBuilder setRequestPriority(Priority priority) {
        this.mRequestPriority = priority;
        return this;
    }

    public Priority getRequestPriority() {
        return this.mRequestPriority;
    }

    public ImageRequestBuilder setPostprocessor(Postprocessor postprocessor) {
        this.mPostprocessor = postprocessor;
        return this;
    }

    public Postprocessor getPostprocessor() {
        return this.mPostprocessor;
    }

    public ImageRequestBuilder setRequestListener(RequestListener requestListener) {
        this.mRequestListener = requestListener;
        return this;
    }

    public RequestListener getRequestListener() {
        return this.mRequestListener;
    }

    public ImageRequestBuilder setDiskCacheId(String str) {
        this.mDiskCacheId = str;
        return this;
    }

    public String getDiskCacheId() {
        return this.mDiskCacheId;
    }

    public ImageRequest build() {
        validate();
        return new ImageRequest(this);
    }

    public Boolean shouldDecodePrefetches() {
        return this.mDecodePrefetches;
    }

    public ImageRequestBuilder setShouldDecodePrefetches(Boolean bool) {
        this.mDecodePrefetches = bool;
        return this;
    }

    public ImageRequestBuilder setResizingAllowedOverride(Boolean bool) {
        this.mResizingAllowedOverride = bool;
        return this;
    }

    public Boolean getResizingAllowedOverride() {
        return this.mResizingAllowedOverride;
    }

    public ImageRequestBuilder setDownsampleOverride(DownsampleMode downsampleMode) {
        this.mDownsampleOverride = downsampleMode;
        return this;
    }

    public DownsampleMode getDownsampleOverride() {
        return this.mDownsampleOverride;
    }

    public int getDelayMs() {
        return this.mDelayMs;
    }

    public ImageRequestBuilder setDelayMs(int i) {
        this.mDelayMs = i;
        return this;
    }

    public static class BuilderException extends RuntimeException {
        public BuilderException(String str) {
            super("Invalid request builder: " + str);
        }
    }

    /* access modifiers changed from: protected */
    public void validate() {
        Uri uri = this.mSourceUri;
        if (uri != null) {
            if (UriUtil.isLocalResourceUri(uri)) {
                if (!this.mSourceUri.isAbsolute()) {
                    throw new BuilderException("Resource URI path must be absolute.");
                } else if (!this.mSourceUri.getPath().isEmpty()) {
                    try {
                        Integer.parseInt(this.mSourceUri.getPath().substring(1));
                    } catch (NumberFormatException unused) {
                        throw new BuilderException("Resource URI path must be a resource id.");
                    }
                } else {
                    throw new BuilderException("Resource URI must not be empty");
                }
            }
            if (UriUtil.isLocalAssetUri(this.mSourceUri) && !this.mSourceUri.isAbsolute()) {
                throw new BuilderException("Asset URI path must be absolute.");
            }
            return;
        }
        throw new BuilderException("Source must be set!");
    }
}
