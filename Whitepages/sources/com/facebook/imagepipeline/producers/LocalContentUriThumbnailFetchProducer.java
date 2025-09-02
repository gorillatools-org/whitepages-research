package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imageutils.JfifUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.Executor;

public class LocalContentUriThumbnailFetchProducer extends LocalFetchProducer implements ThumbnailProducer {
    private static final Rect MICRO_THUMBNAIL_DIMENSIONS = new Rect(0, 0, 96, 96);
    private static final Rect MINI_THUMBNAIL_DIMENSIONS = new Rect(0, 0, 512, 384);
    private static final String[] PROJECTION = {"_id", "_data"};
    private static final Class TAG = LocalContentUriThumbnailFetchProducer.class;
    private static final String[] THUMBNAIL_PROJECTION = {"_data"};
    private final ContentResolver mContentResolver;

    public LocalContentUriThumbnailFetchProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, ContentResolver contentResolver) {
        super(executor, pooledByteBufferFactory);
        this.mContentResolver = contentResolver;
    }

    public boolean canProvideImageForSize(ResizeOptions resizeOptions) {
        Rect rect = MINI_THUMBNAIL_DIMENSIONS;
        return ThumbnailSizeChecker.isImageBigEnough(rect.width(), rect.height(), resizeOptions);
    }

    /* access modifiers changed from: protected */
    public EncodedImage getEncodedImage(ImageRequest imageRequest) {
        Uri sourceUri = imageRequest.getSourceUri();
        if (UriUtil.isLocalCameraUri(sourceUri)) {
            return getCameraImage(sourceUri, imageRequest.getResizeOptions());
        }
        return null;
    }

    private EncodedImage getCameraImage(Uri uri, ResizeOptions resizeOptions) {
        Cursor query;
        EncodedImage thumbnail;
        if (resizeOptions == null || (query = this.mContentResolver.query(uri, PROJECTION, (String) null, (String[]) null, (String) null)) == null) {
            return null;
        }
        try {
            if (!query.moveToFirst() || (thumbnail = getThumbnail(resizeOptions, query.getLong(query.getColumnIndex("_id")))) == null) {
                query.close();
                return null;
            }
            int columnIndex = query.getColumnIndex("_data");
            if (columnIndex >= 0) {
                thumbnail.setRotationAngle(getRotationAngle(query.getString(columnIndex)));
            }
            return thumbnail;
        } finally {
            query.close();
        }
    }

    private EncodedImage getThumbnail(ResizeOptions resizeOptions, long j) {
        Cursor queryMiniThumbnail;
        int columnIndex;
        int thumbnailKind = getThumbnailKind(resizeOptions);
        if (thumbnailKind == 0 || (queryMiniThumbnail = MediaStore.Images.Thumbnails.queryMiniThumbnail(this.mContentResolver, j, thumbnailKind, THUMBNAIL_PROJECTION)) == null) {
            return null;
        }
        try {
            if (queryMiniThumbnail.moveToFirst() && (columnIndex = queryMiniThumbnail.getColumnIndex("_data")) >= 0) {
                String str = (String) Preconditions.checkNotNull(queryMiniThumbnail.getString(columnIndex));
                if (new File(str).exists()) {
                    return getEncodedImage(new FileInputStream(str), getLength(str));
                }
            }
            queryMiniThumbnail.close();
            return null;
        } finally {
            queryMiniThumbnail.close();
        }
    }

    private static int getThumbnailKind(ResizeOptions resizeOptions) {
        Rect rect = MICRO_THUMBNAIL_DIMENSIONS;
        if (ThumbnailSizeChecker.isImageBigEnough(rect.width(), rect.height(), resizeOptions)) {
            return 3;
        }
        Rect rect2 = MINI_THUMBNAIL_DIMENSIONS;
        return ThumbnailSizeChecker.isImageBigEnough(rect2.width(), rect2.height(), resizeOptions) ? 1 : 0;
    }

    private static int getLength(String str) {
        if (str == null) {
            return -1;
        }
        return (int) new File(str).length();
    }

    /* access modifiers changed from: protected */
    public String getProducerName() {
        return "LocalContentUriThumbnailFetchProducer";
    }

    private static int getRotationAngle(String str) {
        if (str == null) {
            return 0;
        }
        try {
            return JfifUtil.getAutoRotateAngleFromOrientation(new ExifInterface(str).getAttributeInt("Orientation", 1));
        } catch (IOException e) {
            FLog.e(TAG, (Throwable) e, "Unable to retrieve thumbnail rotation for %s", str);
            return 0;
        }
    }
}
