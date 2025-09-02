package com.facebook.imagepipeline.platform;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.graphics.Rect;
import androidx.core.util.Pools$Pool;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import com.facebook.common.streams.LimitedInputStream;
import com.facebook.common.streams.TailAppendingInputStream;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.BitmapPool;
import com.facebook.imagepipeline.memory.DummyBitmapPool;
import java.io.IOException;
import java.io.InputStream;

public abstract class DefaultDecoder implements PlatformDecoder {
    private static final byte[] EOI_TAIL = {-1, -39};
    private static final Class TAG = DefaultDecoder.class;
    private boolean mAvoidPoolGet;
    private boolean mAvoidPoolRelease;
    private final BitmapPool mBitmapPool;
    final Pools$Pool mDecodeBuffers;
    private final PreverificationHelper mPreverificationHelper = new PreverificationHelper();

    public abstract int getBitmapSize(int i, int i2, BitmapFactory.Options options);

    public DefaultDecoder(BitmapPool bitmapPool, Pools$Pool pools$Pool, PlatformDecoderOptions platformDecoderOptions) {
        this.mBitmapPool = bitmapPool;
        if (bitmapPool instanceof DummyBitmapPool) {
            this.mAvoidPoolGet = platformDecoderOptions.getAvoidPoolGet();
            this.mAvoidPoolRelease = platformDecoderOptions.getAvoidPoolRelease();
        }
        this.mDecodeBuffers = pools$Pool;
    }

    public CloseableReference decodeFromEncodedImageWithColorSpace(EncodedImage encodedImage, Bitmap.Config config, Rect rect, ColorSpace colorSpace) {
        BitmapFactory.Options decodeOptionsForStream = getDecodeOptionsForStream(encodedImage, config, this.mAvoidPoolGet);
        boolean z = decodeOptionsForStream.inPreferredConfig != Bitmap.Config.ARGB_8888;
        try {
            return decodeFromStream((InputStream) Preconditions.checkNotNull(encodedImage.getInputStream()), decodeOptionsForStream, rect, colorSpace);
        } catch (RuntimeException e) {
            if (z) {
                return decodeFromEncodedImageWithColorSpace(encodedImage, Bitmap.Config.ARGB_8888, rect, colorSpace);
            }
            throw e;
        }
    }

    public CloseableReference decodeJPEGFromEncodedImageWithColorSpace(EncodedImage encodedImage, Bitmap.Config config, Rect rect, int i, ColorSpace colorSpace) {
        boolean isCompleteAt = encodedImage.isCompleteAt(i);
        BitmapFactory.Options decodeOptionsForStream = getDecodeOptionsForStream(encodedImage, config, this.mAvoidPoolGet);
        TailAppendingInputStream inputStream = encodedImage.getInputStream();
        Preconditions.checkNotNull(inputStream);
        if (encodedImage.getSize() > i) {
            inputStream = new LimitedInputStream(inputStream, i);
        }
        if (!isCompleteAt) {
            inputStream = new TailAppendingInputStream(inputStream, EOI_TAIL);
        }
        boolean z = decodeOptionsForStream.inPreferredConfig != Bitmap.Config.ARGB_8888;
        try {
            CloseableReference decodeFromStream = decodeFromStream(inputStream, decodeOptionsForStream, rect, colorSpace);
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return decodeFromStream;
        } catch (RuntimeException e2) {
            if (z) {
                CloseableReference decodeJPEGFromEncodedImageWithColorSpace = decodeJPEGFromEncodedImageWithColorSpace(encodedImage, Bitmap.Config.ARGB_8888, rect, i, colorSpace);
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                return decodeJPEGFromEncodedImageWithColorSpace;
            }
            throw e2;
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:0x008e, code lost:
        r8 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x008f, code lost:
        r5 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        com.facebook.common.logging.FLog.e(TAG, "Could not decode region %s, decoding full bitmap instead.", r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00ad, code lost:
        if (r0 != null) goto L_0x00af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        r0.recycle();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:52:0x00a2 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:91:0x0114 */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0089 A[SYNTHETIC, Splitter:B:38:0x0089] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0094 A[SYNTHETIC, Splitter:B:44:0x0094] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00b5 A[Catch:{ IllegalArgumentException -> 0x009d, RuntimeException -> 0x009b, all -> 0x0098, IOException -> 0x0114 }] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00bc A[Catch:{ IllegalArgumentException -> 0x009d, RuntimeException -> 0x009b, all -> 0x0098, IOException -> 0x0114 }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00c7 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x00e6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.facebook.common.references.CloseableReference decodeFromStream(java.io.InputStream r7, android.graphics.BitmapFactory.Options r8, android.graphics.Rect r9, android.graphics.ColorSpace r10) {
        /*
            r6 = this;
            com.facebook.common.internal.Preconditions.checkNotNull(r7)
            int r0 = r8.outWidth
            int r1 = r8.outHeight
            if (r9 == 0) goto L_0x0017
            int r0 = r9.width()
            int r1 = r8.inSampleSize
            int r0 = r0 / r1
            int r1 = r9.height()
            int r2 = r8.inSampleSize
            int r1 = r1 / r2
        L_0x0017:
            com.facebook.imagepipeline.platform.PreverificationHelper r2 = r6.mPreverificationHelper
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0027
            android.graphics.Bitmap$Config r5 = r8.inPreferredConfig
            boolean r2 = r2.shouldUseHardwareBitmapConfig(r5)
            if (r2 == 0) goto L_0x0027
            r2 = r4
            goto L_0x0028
        L_0x0027:
            r2 = r3
        L_0x0028:
            r5 = 0
            if (r9 != 0) goto L_0x0030
            if (r2 == 0) goto L_0x0030
            r8.inMutable = r3
            goto L_0x0053
        L_0x0030:
            if (r9 == 0) goto L_0x0038
            if (r2 == 0) goto L_0x0038
            android.graphics.Bitmap$Config r2 = android.graphics.Bitmap.Config.ARGB_8888
            r8.inPreferredConfig = r2
        L_0x0038:
            boolean r2 = r6.mAvoidPoolGet
            if (r2 != 0) goto L_0x0053
            int r2 = r6.getBitmapSize(r0, r1, r8)
            com.facebook.imagepipeline.memory.BitmapPool r3 = r6.mBitmapPool
            java.lang.Object r2 = r3.get(r2)
            android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2
            if (r2 == 0) goto L_0x004b
            goto L_0x0054
        L_0x004b:
            java.lang.NullPointerException r7 = new java.lang.NullPointerException
            java.lang.String r8 = "BitmapPool.get returned null"
            r7.<init>(r8)
            throw r7
        L_0x0053:
            r2 = r5
        L_0x0054:
            r8.inBitmap = r2
            if (r10 != 0) goto L_0x005e
            android.graphics.ColorSpace$Named r10 = android.graphics.ColorSpace.Named.SRGB
            android.graphics.ColorSpace r10 = android.graphics.ColorSpace.get(r10)
        L_0x005e:
            r8.inPreferredColorSpace = r10
            androidx.core.util.Pools$Pool r10 = r6.mDecodeBuffers
            java.lang.Object r10 = r10.acquire()
            java.nio.ByteBuffer r10 = (java.nio.ByteBuffer) r10
            if (r10 != 0) goto L_0x0072
            int r10 = com.facebook.common.memory.DecodeBufferHelper.getRecommendedDecodeBufferSize()
            java.nio.ByteBuffer r10 = java.nio.ByteBuffer.allocate(r10)
        L_0x0072:
            byte[] r3 = r10.array()     // Catch:{ IllegalArgumentException -> 0x009d, RuntimeException -> 0x009b }
            r8.inTempStorage = r3     // Catch:{ IllegalArgumentException -> 0x009d, RuntimeException -> 0x009b }
            if (r9 == 0) goto L_0x00b9
            if (r2 == 0) goto L_0x00b9
            android.graphics.Bitmap$Config r3 = r8.inPreferredConfig     // Catch:{ IllegalArgumentException -> 0x009d, RuntimeException -> 0x009b }
            if (r3 == 0) goto L_0x00b9
            r2.reconfigure(r0, r1, r3)     // Catch:{ IOException -> 0x00a1, all -> 0x009f }
            android.graphics.BitmapRegionDecoder r0 = android.graphics.BitmapRegionDecoder.newInstance(r7, r4)     // Catch:{ IOException -> 0x00a1, all -> 0x009f }
            if (r0 == 0) goto L_0x0091
            android.graphics.Bitmap r9 = r0.decodeRegion(r9, r8)     // Catch:{ IOException -> 0x00a2 }
            goto L_0x0092
        L_0x008e:
            r8 = move-exception
            r5 = r0
            goto L_0x00b3
        L_0x0091:
            r9 = r5
        L_0x0092:
            if (r0 == 0) goto L_0x00ba
            r0.recycle()     // Catch:{ IllegalArgumentException -> 0x009d, RuntimeException -> 0x009b }
            goto L_0x00ba
        L_0x0098:
            r7 = move-exception
            goto L_0x0115
        L_0x009b:
            r7 = move-exception
            goto L_0x00ed
        L_0x009d:
            r8 = move-exception
            goto L_0x00f5
        L_0x009f:
            r8 = move-exception
            goto L_0x00b3
        L_0x00a1:
            r0 = r5
        L_0x00a2:
            java.lang.Class r1 = TAG     // Catch:{ all -> 0x008e }
            java.lang.String r3 = "Could not decode region %s, decoding full bitmap instead."
            java.lang.Object[] r9 = new java.lang.Object[]{r9}     // Catch:{ all -> 0x008e }
            com.facebook.common.logging.FLog.e((java.lang.Class) r1, (java.lang.String) r3, (java.lang.Object[]) r9)     // Catch:{ all -> 0x008e }
            if (r0 == 0) goto L_0x00b9
            r0.recycle()     // Catch:{ IllegalArgumentException -> 0x009d, RuntimeException -> 0x009b }
            goto L_0x00b9
        L_0x00b3:
            if (r5 == 0) goto L_0x00b8
            r5.recycle()     // Catch:{ IllegalArgumentException -> 0x009d, RuntimeException -> 0x009b }
        L_0x00b8:
            throw r8     // Catch:{ IllegalArgumentException -> 0x009d, RuntimeException -> 0x009b }
        L_0x00b9:
            r9 = r5
        L_0x00ba:
            if (r9 != 0) goto L_0x00c0
            android.graphics.Bitmap r9 = android.graphics.BitmapFactory.decodeStream(r7, r5, r8)     // Catch:{ IllegalArgumentException -> 0x009d, RuntimeException -> 0x009b }
        L_0x00c0:
            androidx.core.util.Pools$Pool r7 = r6.mDecodeBuffers
            r7.release(r10)
            if (r2 == 0) goto L_0x00d9
            if (r2 == r9) goto L_0x00d9
            com.facebook.imagepipeline.memory.BitmapPool r7 = r6.mBitmapPool
            r7.release(r2)
            if (r9 == 0) goto L_0x00d3
            r9.recycle()
        L_0x00d3:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            r7.<init>()
            throw r7
        L_0x00d9:
            boolean r7 = r6.mAvoidPoolRelease
            if (r7 == 0) goto L_0x00e6
            com.facebook.imagepipeline.platform.DefaultDecoder$NoOpResourceReleaser r7 = com.facebook.imagepipeline.platform.DefaultDecoder.NoOpResourceReleaser.INSTANCE
            com.facebook.common.references.CloseableReference r7 = com.facebook.common.references.CloseableReference.of((java.lang.Object) r9, (com.facebook.common.references.ResourceReleaser) r7)
            return r7
        L_0x00e6:
            com.facebook.imagepipeline.memory.BitmapPool r7 = r6.mBitmapPool
            com.facebook.common.references.CloseableReference r7 = com.facebook.common.references.CloseableReference.of((java.lang.Object) r9, (com.facebook.common.references.ResourceReleaser) r7)
            return r7
        L_0x00ed:
            if (r2 == 0) goto L_0x00f4
            com.facebook.imagepipeline.memory.BitmapPool r8 = r6.mBitmapPool     // Catch:{ all -> 0x0098 }
            r8.release(r2)     // Catch:{ all -> 0x0098 }
        L_0x00f4:
            throw r7     // Catch:{ all -> 0x0098 }
        L_0x00f5:
            if (r2 == 0) goto L_0x00fc
            com.facebook.imagepipeline.memory.BitmapPool r9 = r6.mBitmapPool     // Catch:{ all -> 0x0098 }
            r9.release(r2)     // Catch:{ all -> 0x0098 }
        L_0x00fc:
            r7.reset()     // Catch:{ IOException -> 0x0114 }
            android.graphics.Bitmap r7 = android.graphics.BitmapFactory.decodeStream(r7)     // Catch:{ IOException -> 0x0114 }
            if (r7 == 0) goto L_0x0113
            com.facebook.imagepipeline.bitmaps.SimpleBitmapReleaser r9 = com.facebook.imagepipeline.bitmaps.SimpleBitmapReleaser.getInstance()     // Catch:{ IOException -> 0x0114 }
            com.facebook.common.references.CloseableReference r7 = com.facebook.common.references.CloseableReference.of((java.lang.Object) r7, (com.facebook.common.references.ResourceReleaser) r9)     // Catch:{ IOException -> 0x0114 }
            androidx.core.util.Pools$Pool r8 = r6.mDecodeBuffers
            r8.release(r10)
            return r7
        L_0x0113:
            throw r8     // Catch:{ IOException -> 0x0114 }
        L_0x0114:
            throw r8     // Catch:{ all -> 0x0098 }
        L_0x0115:
            androidx.core.util.Pools$Pool r8 = r6.mDecodeBuffers
            r8.release(r10)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.platform.DefaultDecoder.decodeFromStream(java.io.InputStream, android.graphics.BitmapFactory$Options, android.graphics.Rect, android.graphics.ColorSpace):com.facebook.common.references.CloseableReference");
    }

    private static BitmapFactory.Options getDecodeOptionsForStream(EncodedImage encodedImage, Bitmap.Config config, boolean z) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = encodedImage.getSampleSize();
        options.inJustDecodeBounds = true;
        options.inDither = true;
        boolean z2 = config == Bitmap.Config.HARDWARE;
        if (!z2) {
            options.inPreferredConfig = config;
        }
        options.inMutable = true;
        if (!z) {
            BitmapFactory.decodeStream(encodedImage.getInputStream(), (Rect) null, options);
            if (options.outWidth == -1 || options.outHeight == -1) {
                throw new IllegalArgumentException();
            }
        }
        if (z2) {
            options.inPreferredConfig = config;
        }
        options.inJustDecodeBounds = false;
        return options;
    }

    private static final class NoOpResourceReleaser implements ResourceReleaser {
        /* access modifiers changed from: private */
        public static final NoOpResourceReleaser INSTANCE = new NoOpResourceReleaser();

        public void release(Bitmap bitmap) {
        }

        private NoOpResourceReleaser() {
        }
    }
}
