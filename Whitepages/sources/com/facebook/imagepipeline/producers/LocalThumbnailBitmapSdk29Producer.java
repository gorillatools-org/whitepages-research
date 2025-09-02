package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.CancellationSignal;
import android.util.Size;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.media.MediaUtils;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.bitmaps.SimpleBitmapReleaser;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.File;
import java.util.Map;
import java.util.concurrent.Executor;

public class LocalThumbnailBitmapSdk29Producer implements Producer {
    /* access modifiers changed from: private */
    public final ContentResolver mContentResolver;
    private final Executor mExecutor;

    public LocalThumbnailBitmapSdk29Producer(Executor executor, ContentResolver contentResolver) {
        this.mExecutor = executor;
        this.mContentResolver = contentResolver;
    }

    public void produceResults(Consumer consumer, ProducerContext producerContext) {
        final ProducerListener2 producerListener = producerContext.getProducerListener();
        final ImageRequest imageRequest = producerContext.getImageRequest();
        producerContext.putOriginExtra(ImagesContract.LOCAL, "thumbnail_bitmap");
        final CancellationSignal cancellationSignal = new CancellationSignal();
        final ProducerContext producerContext2 = producerContext;
        final AnonymousClass1 r0 = new StatefulProducerRunnable(consumer, producerListener, producerContext, "LocalThumbnailBitmapSdk29Producer") {
            /* access modifiers changed from: protected */
            public void onSuccess(CloseableReference closeableReference) {
                super.onSuccess(closeableReference);
                producerListener.onUltimateProducerReached(producerContext2, "LocalThumbnailBitmapSdk29Producer", closeableReference != null);
                producerContext2.putOriginExtra(ImagesContract.LOCAL, "thumbnail_bitmap");
            }

            /* access modifiers changed from: protected */
            public void onFailure(Exception exc) {
                super.onFailure(exc);
                producerListener.onUltimateProducerReached(producerContext2, "LocalThumbnailBitmapSdk29Producer", false);
                producerContext2.putOriginExtra(ImagesContract.LOCAL, "thumbnail_bitmap");
            }

            /* access modifiers changed from: protected */
            public CloseableReference getResult() {
                String str;
                Bitmap bitmap;
                Size size = new Size(imageRequest.getPreferredWidth(), imageRequest.getPreferredHeight());
                try {
                    str = LocalThumbnailBitmapSdk29Producer.this.getLocalFilePath(imageRequest);
                } catch (IllegalArgumentException unused) {
                    str = null;
                }
                if (str != null) {
                    bitmap = MediaUtils.isVideo(MediaUtils.extractMime(str)) ? ThumbnailUtils.createVideoThumbnail(new File(str), size, cancellationSignal) : ThumbnailUtils.createImageThumbnail(new File(str), size, cancellationSignal);
                } else {
                    bitmap = null;
                }
                if (bitmap == null) {
                    bitmap = LocalThumbnailBitmapSdk29Producer.this.mContentResolver.loadThumbnail(imageRequest.getSourceUri(), size, cancellationSignal);
                }
                if (bitmap == null) {
                    return null;
                }
                CloseableStaticBitmap of = CloseableStaticBitmap.of(bitmap, (ResourceReleaser) SimpleBitmapReleaser.getInstance(), ImmutableQualityInfo.FULL_QUALITY, 0);
                producerContext2.putExtra("image_format", "thumbnail");
                of.putExtras(producerContext2.getExtras());
                return CloseableReference.of(of);
            }

            /* access modifiers changed from: protected */
            public void onCancellation() {
                super.onCancellation();
                cancellationSignal.cancel();
            }

            /* access modifiers changed from: protected */
            public Map getExtraMapOnSuccess(CloseableReference closeableReference) {
                return ImmutableMap.of("createdThumbnail", String.valueOf(closeableReference != null));
            }

            /* access modifiers changed from: protected */
            public void disposeResult(CloseableReference closeableReference) {
                CloseableReference.closeSafely(closeableReference);
            }
        };
        producerContext.addCallbacks(new BaseProducerContextCallbacks() {
            public void onCancellationRequested() {
                r0.cancel();
            }
        });
        this.mExecutor.execute(r0);
    }

    /* access modifiers changed from: private */
    public String getLocalFilePath(ImageRequest imageRequest) {
        return UriUtil.getRealPathFromUri(this.mContentResolver, imageRequest.getSourceUri());
    }
}
