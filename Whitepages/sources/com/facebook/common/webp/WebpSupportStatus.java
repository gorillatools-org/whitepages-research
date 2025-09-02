package com.facebook.common.webp;

import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0;
import java.io.UnsupportedEncodingException;

public abstract class WebpSupportStatus {
    private static final byte[] WEBP_NAME_BYTES = asciiBytes("WEBP");
    private static final byte[] WEBP_RIFF_BYTES = asciiBytes("RIFF");
    private static final byte[] WEBP_VP8L_BYTES = asciiBytes("VP8L");
    private static final byte[] WEBP_VP8X_BYTES = asciiBytes("VP8X");
    private static final byte[] WEBP_VP8_BYTES = asciiBytes("VP8 ");
    public static final boolean sIsExtendedWebpSupported = isExtendedWebpSupported();
    public static final boolean sIsSimpleWebpSupported = true;
    private static boolean sWebpLibraryChecked = false;

    private static boolean isExtendedWebpSupported() {
        return true;
    }

    public static WebpBitmapFactory loadWebpBitmapFactoryIfExists() {
        if (sWebpLibraryChecked) {
            return null;
        }
        try {
            MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(Class.forName("com.facebook.webpsupport.WebpBitmapFactoryImpl").newInstance());
        } catch (Throwable unused) {
        }
        sWebpLibraryChecked = true;
        return null;
    }

    private static byte[] asciiBytes(String str) {
        try {
            return str.getBytes("ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("ASCII not found!", e);
        }
    }

    public static boolean isAnimatedWebpHeader(byte[] bArr, int i) {
        return matchBytePattern(bArr, i + 12, WEBP_VP8X_BYTES) && ((bArr[i + 20] & 2) == 2);
    }

    public static boolean isSimpleWebpHeader(byte[] bArr, int i) {
        return matchBytePattern(bArr, i + 12, WEBP_VP8_BYTES);
    }

    public static boolean isLosslessWebpHeader(byte[] bArr, int i) {
        return matchBytePattern(bArr, i + 12, WEBP_VP8L_BYTES);
    }

    public static boolean isExtendedWebpHeader(byte[] bArr, int i, int i2) {
        return i2 >= 21 && matchBytePattern(bArr, i + 12, WEBP_VP8X_BYTES);
    }

    public static boolean isExtendedWebpHeaderWithAlpha(byte[] bArr, int i) {
        return matchBytePattern(bArr, i + 12, WEBP_VP8X_BYTES) && ((bArr[i + 20] & 16) == 16);
    }

    public static boolean isWebpHeader(byte[] bArr, int i, int i2) {
        return i2 >= 20 && matchBytePattern(bArr, i, WEBP_RIFF_BYTES) && matchBytePattern(bArr, i + 8, WEBP_NAME_BYTES);
    }

    private static boolean matchBytePattern(byte[] bArr, int i, byte[] bArr2) {
        if (bArr2 == null || bArr == null || bArr2.length + i > bArr.length) {
            return false;
        }
        for (int i2 = 0; i2 < bArr2.length; i2++) {
            if (bArr[i2 + i] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }
}
