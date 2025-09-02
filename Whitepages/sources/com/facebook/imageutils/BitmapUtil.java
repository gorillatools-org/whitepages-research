package com.facebook.imageutils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Pair;
import androidx.core.util.Pools$SynchronizedPool;
import com.facebook.common.memory.DecodeBufferHelper;
import java.io.InputStream;
import java.nio.ByteBuffer;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;

public final class BitmapUtil {
    private static final Lazy DECODE_BUFFERS$delegate = LazyKt.lazy(new BitmapUtil$$ExternalSyntheticLambda0());
    public static final BitmapUtil INSTANCE = new BitmapUtil();
    private static boolean fixDecodeDrmImageCrash;
    private static boolean useDecodeBufferHelper;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(17:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|17) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                android.graphics.Bitmap$Config[] r0 = android.graphics.Bitmap.Config.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ARGB_8888     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ALPHA_8     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ARGB_4444     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.RGB_565     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.RGBA_F16     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.RGBA_1010102     // Catch:{ NoSuchFieldError -> 0x003f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003f }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003f }
            L_0x003f:
                android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.HARDWARE     // Catch:{ NoSuchFieldError -> 0x0048 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0048 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0048 }
            L_0x0048:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imageutils.BitmapUtil.WhenMappings.<clinit>():void");
        }
    }

    private BitmapUtil() {
    }

    private final Pools$SynchronizedPool getDECODE_BUFFERS() {
        return (Pools$SynchronizedPool) DECODE_BUFFERS$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public static final Pools$SynchronizedPool DECODE_BUFFERS_delegate$lambda$0() {
        return new Pools$SynchronizedPool(12);
    }

    public static final int getSizeInBytes(Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        try {
            return bitmap.getAllocationByteCount();
        } catch (NullPointerException unused) {
            return bitmap.getByteCount();
        }
    }

    public static final Pair decodeDimensions(InputStream inputStream) {
        if (inputStream != null) {
            BitmapUtil bitmapUtil = INSTANCE;
            ByteBuffer obtainByteBuffer = bitmapUtil.obtainByteBuffer();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            try {
                options.inTempStorage = obtainByteBuffer.array();
                Pair pair = null;
                bitmapUtil.decodeStreamInternal(inputStream, (Rect) null, options);
                if (options.outWidth != -1) {
                    if (options.outHeight != -1) {
                        pair = new Pair(Integer.valueOf(options.outWidth), Integer.valueOf(options.outHeight));
                    }
                }
                bitmapUtil.releaseByteBuffer(obtainByteBuffer);
                return pair;
            } catch (Throwable th) {
                INSTANCE.releaseByteBuffer(obtainByteBuffer);
                throw th;
            }
        } else {
            throw new IllegalStateException("Required value was null.");
        }
    }

    public static final ImageMetaData decodeDimensionsAndColorSpace(InputStream inputStream) {
        if (inputStream != null) {
            BitmapUtil bitmapUtil = INSTANCE;
            ByteBuffer obtainByteBuffer = bitmapUtil.obtainByteBuffer();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            try {
                options.inTempStorage = obtainByteBuffer.array();
                bitmapUtil.decodeStreamInternal(inputStream, (Rect) null, options);
                ImageMetaData imageMetaData = new ImageMetaData(options.outWidth, options.outHeight, options.outColorSpace);
                bitmapUtil.releaseByteBuffer(obtainByteBuffer);
                return imageMetaData;
            } catch (Throwable th) {
                INSTANCE.releaseByteBuffer(obtainByteBuffer);
                throw th;
            }
        } else {
            throw new IllegalStateException("Required value was null.");
        }
    }

    public final Bitmap decodeStreamInternal(InputStream inputStream, Rect rect, BitmapFactory.Options options) {
        if (!fixDecodeDrmImageCrash) {
            return BitmapFactory.decodeStream(inputStream, rect, options);
        }
        try {
            return BitmapFactory.decodeStream(inputStream, rect, options);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public static final int getPixelSizeForBitmapConfig(Bitmap.Config config) {
        switch (config == null ? -1 : WhenMappings.$EnumSwitchMapping$0[config.ordinal()]) {
            case 1:
            case 6:
            case 7:
                return 4;
            case 2:
                return 1;
            case 3:
            case 4:
                return 2;
            case 5:
                return 8;
            default:
                throw new UnsupportedOperationException("The provided Bitmap.Config is not supported");
        }
    }

    public static final int getSizeInByteForBitmap(int i, int i2, Bitmap.Config config) {
        if (i <= 0) {
            throw new IllegalArgumentException(("width must be > 0, width is: " + i).toString());
        } else if (i2 > 0) {
            int pixelSizeForBitmapConfig = getPixelSizeForBitmapConfig(config);
            int i3 = i * i2 * pixelSizeForBitmapConfig;
            if (i3 > 0) {
                return i3;
            }
            throw new IllegalStateException(("size must be > 0: size: " + i3 + ", width: " + i + ", height: " + i2 + ", pixelSize: " + pixelSizeForBitmapConfig).toString());
        } else {
            throw new IllegalArgumentException(("height must be > 0, height is: " + i2).toString());
        }
    }

    private final ByteBuffer acquireByteBuffer() {
        if (useDecodeBufferHelper) {
            return DecodeBufferHelper.INSTANCE.acquire();
        }
        return (ByteBuffer) getDECODE_BUFFERS().acquire();
    }

    private final void releaseByteBuffer(ByteBuffer byteBuffer) {
        if (!useDecodeBufferHelper) {
            getDECODE_BUFFERS().release(byteBuffer);
        }
    }

    private final ByteBuffer obtainByteBuffer() {
        ByteBuffer acquireByteBuffer = acquireByteBuffer();
        if (acquireByteBuffer != null) {
            return acquireByteBuffer;
        }
        ByteBuffer allocate = ByteBuffer.allocate(DecodeBufferHelper.getRecommendedDecodeBufferSize());
        Intrinsics.checkNotNullExpressionValue(allocate, "allocate(...)");
        return allocate;
    }
}
