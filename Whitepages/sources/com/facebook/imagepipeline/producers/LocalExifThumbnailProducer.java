package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.media.ExifInterface;
import android.net.Uri;
import android.util.Pair;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteBufferInputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imageutils.BitmapUtil;
import com.facebook.imageutils.JfifUtil;
import com.facebook.soloader.DoNotOptimize;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executor;

public class LocalExifThumbnailProducer implements ThumbnailProducer {
    private final ContentResolver mContentResolver;
    private final Executor mExecutor;
    /* access modifiers changed from: private */
    public final PooledByteBufferFactory mPooledByteBufferFactory;

    public LocalExifThumbnailProducer(Executor executor, PooledByteBufferFactory pooledByteBufferFactory, ContentResolver contentResolver) {
        this.mExecutor = executor;
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
        this.mContentResolver = contentResolver;
    }

    public boolean canProvideImageForSize(ResizeOptions resizeOptions) {
        return ThumbnailSizeChecker.isImageBigEnough(512, 512, resizeOptions);
    }

    public void produceResults(Consumer consumer, ProducerContext producerContext) {
        ProducerListener2 producerListener = producerContext.getProducerListener();
        final ImageRequest imageRequest = producerContext.getImageRequest();
        producerContext.putOriginExtra(ImagesContract.LOCAL, "exif");
        final AnonymousClass1 r0 = new StatefulProducerRunnable(consumer, producerListener, producerContext, "LocalExifThumbnailProducer") {
            /* access modifiers changed from: protected */
            public EncodedImage getResult() {
                ExifInterface exifInterface = LocalExifThumbnailProducer.this.getExifInterface(imageRequest.getSourceUri());
                if (exifInterface == null || !exifInterface.hasThumbnail()) {
                    return null;
                }
                return LocalExifThumbnailProducer.this.buildEncodedImage(LocalExifThumbnailProducer.this.mPooledByteBufferFactory.newByteBuffer((byte[]) Preconditions.checkNotNull(exifInterface.getThumbnail())), exifInterface);
            }

            /* access modifiers changed from: protected */
            public void disposeResult(EncodedImage encodedImage) {
                EncodedImage.closeSafely(encodedImage);
            }

            /* access modifiers changed from: protected */
            public Map getExtraMapOnSuccess(EncodedImage encodedImage) {
                return ImmutableMap.of("createdThumbnail", Boolean.toString(encodedImage != null));
            }
        };
        producerContext.addCallbacks(new BaseProducerContextCallbacks() {
            public void onCancellationRequested() {
                r0.cancel();
            }
        });
        this.mExecutor.execute(r0);
    }

    /* access modifiers changed from: package-private */
    public ExifInterface getExifInterface(Uri uri) {
        String realPathFromUri = UriUtil.getRealPathFromUri(this.mContentResolver, uri);
        if (realPathFromUri == null) {
            return null;
        }
        try {
            if (canReadAsFile(realPathFromUri)) {
                return new ExifInterface(realPathFromUri);
            }
            AssetFileDescriptor assetFileDescriptor = UriUtil.getAssetFileDescriptor(this.mContentResolver, uri);
            if (assetFileDescriptor != null) {
                ExifInterface exifInterface = new Api24Utils().getExifInterface(assetFileDescriptor.getFileDescriptor());
                assetFileDescriptor.close();
                return exifInterface;
            }
            return null;
        } catch (IOException unused) {
        } catch (StackOverflowError unused2) {
            FLog.e(LocalExifThumbnailProducer.class, "StackOverflowError in ExifInterface constructor");
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    public EncodedImage buildEncodedImage(PooledByteBuffer pooledByteBuffer, ExifInterface exifInterface) {
        Pair decodeDimensions = BitmapUtil.decodeDimensions(new PooledByteBufferInputStream(pooledByteBuffer));
        int rotationAngle = getRotationAngle(exifInterface);
        int i = -1;
        int intValue = decodeDimensions != null ? ((Integer) decodeDimensions.first).intValue() : -1;
        if (decodeDimensions != null) {
            i = ((Integer) decodeDimensions.second).intValue();
        }
        CloseableReference of = CloseableReference.of(pooledByteBuffer);
        try {
            EncodedImage encodedImage = new EncodedImage(of);
            CloseableReference.closeSafely(of);
            encodedImage.setImageFormat(DefaultImageFormats.JPEG);
            encodedImage.setRotationAngle(rotationAngle);
            encodedImage.setWidth(intValue);
            encodedImage.setHeight(i);
            return encodedImage;
        } catch (Throwable th) {
            CloseableReference.closeSafely(of);
            throw th;
        }
    }

    private int getRotationAngle(ExifInterface exifInterface) {
        return JfifUtil.getAutoRotateAngleFromOrientation(Integer.parseInt((String) Preconditions.checkNotNull(exifInterface.getAttribute("Orientation"))));
    }

    /* access modifiers changed from: package-private */
    public boolean canReadAsFile(String str) {
        if (str == null) {
            return false;
        }
        File file = new File(str);
        if (!file.exists() || !file.canRead()) {
            return false;
        }
        return true;
    }

    @DoNotOptimize
    private class Api24Utils {
        private Api24Utils() {
        }

        /* access modifiers changed from: package-private */
        public ExifInterface getExifInterface(FileDescriptor fileDescriptor) {
            return new ExifInterface(fileDescriptor);
        }
    }
}
