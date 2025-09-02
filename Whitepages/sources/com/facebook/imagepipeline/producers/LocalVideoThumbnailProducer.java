package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.bitmaps.SimpleBitmapReleaser;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.Map;
import java.util.concurrent.Executor;

public class LocalVideoThumbnailProducer implements Producer {
    /* access modifiers changed from: private */
    public final ContentResolver mContentResolver;
    private final Executor mExecutor;

    public LocalVideoThumbnailProducer(Executor executor, ContentResolver contentResolver) {
        this.mExecutor = executor;
        this.mContentResolver = contentResolver;
    }

    public void produceResults(Consumer consumer, ProducerContext producerContext) {
        final ProducerListener2 producerListener = producerContext.getProducerListener();
        final ImageRequest imageRequest = producerContext.getImageRequest();
        producerContext.putOriginExtra(ImagesContract.LOCAL, "video");
        final ProducerContext producerContext2 = producerContext;
        final AnonymousClass1 r0 = new StatefulProducerRunnable(consumer, producerListener, producerContext, "VideoThumbnailProducer") {
            /* access modifiers changed from: protected */
            public void onSuccess(CloseableReference closeableReference) {
                super.onSuccess(closeableReference);
                producerListener.onUltimateProducerReached(producerContext2, "VideoThumbnailProducer", closeableReference != null);
                producerContext2.putOriginExtra(ImagesContract.LOCAL, "video");
            }

            /* access modifiers changed from: protected */
            public void onFailure(Exception exc) {
                super.onFailure(exc);
                producerListener.onUltimateProducerReached(producerContext2, "VideoThumbnailProducer", false);
                producerContext2.putOriginExtra(ImagesContract.LOCAL, "video");
            }

            /* access modifiers changed from: protected */
            public CloseableReference getResult() {
                String str;
                try {
                    str = LocalVideoThumbnailProducer.this.getLocalFilePath(imageRequest);
                } catch (IllegalArgumentException unused) {
                    str = null;
                }
                Bitmap createVideoThumbnail = str != null ? ThumbnailUtils.createVideoThumbnail(str, LocalVideoThumbnailProducer.calculateKind(imageRequest)) : null;
                if (createVideoThumbnail == null) {
                    createVideoThumbnail = LocalVideoThumbnailProducer.createThumbnailFromContentProvider(LocalVideoThumbnailProducer.this.mContentResolver, imageRequest.getSourceUri());
                }
                if (createVideoThumbnail == null) {
                    return null;
                }
                CloseableStaticBitmap of = CloseableStaticBitmap.of(createVideoThumbnail, (ResourceReleaser) SimpleBitmapReleaser.getInstance(), ImmutableQualityInfo.FULL_QUALITY, 0);
                producerContext2.putExtra("image_format", "thumbnail");
                of.putExtras(producerContext2.getExtras());
                return CloseableReference.of(of);
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
    public static int calculateKind(ImageRequest imageRequest) {
        return (imageRequest.getPreferredWidth() > 96 || imageRequest.getPreferredHeight() > 96) ? 1 : 3;
    }

    /* access modifiers changed from: private */
    public String getLocalFilePath(ImageRequest imageRequest) {
        return UriUtil.getRealPathFromUri(this.mContentResolver, imageRequest.getSourceUri());
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0029 A[SYNTHETIC, Splitter:B:15:0x0029] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x002f A[SYNTHETIC, Splitter:B:21:0x002f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap createThumbnailFromContentProvider(android.content.ContentResolver r3, android.net.Uri r4) {
        /*
            r0 = 0
            java.lang.String r1 = "r"
            android.os.ParcelFileDescriptor r3 = r3.openFileDescriptor(r4, r1)     // Catch:{ FileNotFoundException -> 0x0025, all -> 0x0023 }
            com.facebook.common.internal.Preconditions.checkNotNull(r3)     // Catch:{ FileNotFoundException -> 0x0025, all -> 0x0023 }
            android.media.MediaMetadataRetriever r4 = new android.media.MediaMetadataRetriever     // Catch:{ FileNotFoundException -> 0x0025, all -> 0x0023 }
            r4.<init>()     // Catch:{ FileNotFoundException -> 0x0025, all -> 0x0023 }
            java.io.FileDescriptor r3 = r3.getFileDescriptor()     // Catch:{ FileNotFoundException -> 0x002d, all -> 0x0020 }
            r4.setDataSource(r3)     // Catch:{ FileNotFoundException -> 0x002d, all -> 0x0020 }
            r1 = -1
            android.graphics.Bitmap r3 = r4.getFrameAtTime(r1)     // Catch:{ FileNotFoundException -> 0x002d, all -> 0x0020 }
            r4.release()     // Catch:{ IOException -> 0x001f }
        L_0x001f:
            return r3
        L_0x0020:
            r3 = move-exception
            r0 = r4
            goto L_0x0027
        L_0x0023:
            r3 = move-exception
            goto L_0x0027
        L_0x0025:
            r4 = r0
            goto L_0x002d
        L_0x0027:
            if (r0 == 0) goto L_0x002c
            r0.release()     // Catch:{ IOException -> 0x002c }
        L_0x002c:
            throw r3
        L_0x002d:
            if (r4 == 0) goto L_0x0032
            r4.release()     // Catch:{ IOException -> 0x0032 }
        L_0x0032:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer.createThumbnailFromContentProvider(android.content.ContentResolver, android.net.Uri):android.graphics.Bitmap");
    }
}
