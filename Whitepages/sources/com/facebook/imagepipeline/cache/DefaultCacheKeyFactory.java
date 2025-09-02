package com.facebook.imagepipeline.cache;

import android.net.Uri;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.Postprocessor;

public class DefaultCacheKeyFactory implements CacheKeyFactory {
    private static DefaultCacheKeyFactory sInstance = null;
    private static boolean sShouldRemoveCallerContextFromCacheKey = false;

    /* access modifiers changed from: protected */
    public Uri getCacheKeySourceUri(Uri uri) {
        return uri;
    }

    protected DefaultCacheKeyFactory() {
    }

    public static synchronized DefaultCacheKeyFactory getInstance() {
        DefaultCacheKeyFactory defaultCacheKeyFactory;
        synchronized (DefaultCacheKeyFactory.class) {
            try {
                if (sInstance == null) {
                    sInstance = new DefaultCacheKeyFactory();
                }
                defaultCacheKeyFactory = sInstance;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return defaultCacheKeyFactory;
    }

    public CacheKey getBitmapCacheKey(ImageRequest imageRequest, Object obj) {
        BitmapMemoryCacheKey bitmapMemoryCacheKey = new BitmapMemoryCacheKey(getCacheKeySourceUri(imageRequest.getSourceUri()).toString(), imageRequest.getResizeOptions(), imageRequest.getRotationOptions(), imageRequest.getImageDecodeOptions(), (CacheKey) null, (String) null);
        if (sShouldRemoveCallerContextFromCacheKey) {
            bitmapMemoryCacheKey.setCallerContext((Object) null);
        } else {
            bitmapMemoryCacheKey.setCallerContext(obj);
        }
        return bitmapMemoryCacheKey;
    }

    public CacheKey getPostprocessedBitmapCacheKey(ImageRequest imageRequest, Object obj) {
        String str;
        CacheKey cacheKey;
        Postprocessor postprocessor = imageRequest.getPostprocessor();
        if (postprocessor != null) {
            CacheKey postprocessorCacheKey = postprocessor.getPostprocessorCacheKey();
            str = postprocessor.getClass().getName();
            cacheKey = postprocessorCacheKey;
        } else {
            cacheKey = null;
            str = null;
        }
        BitmapMemoryCacheKey bitmapMemoryCacheKey = new BitmapMemoryCacheKey(getCacheKeySourceUri(imageRequest.getSourceUri()).toString(), imageRequest.getResizeOptions(), imageRequest.getRotationOptions(), imageRequest.getImageDecodeOptions(), cacheKey, str);
        if (sShouldRemoveCallerContextFromCacheKey) {
            bitmapMemoryCacheKey.setCallerContext((Object) null);
        } else {
            bitmapMemoryCacheKey.setCallerContext(obj);
        }
        return bitmapMemoryCacheKey;
    }

    public CacheKey getEncodedCacheKey(ImageRequest imageRequest, Object obj) {
        return getEncodedCacheKey(imageRequest, imageRequest.getSourceUri(), obj);
    }

    public CacheKey getEncodedCacheKey(ImageRequest imageRequest, Uri uri, Object obj) {
        return new SimpleCacheKey(getCacheKeySourceUri(uri).toString());
    }
}
