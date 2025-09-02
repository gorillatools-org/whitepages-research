package com.facebook.imageformat;

import com.facebook.common.webp.WebpSupportStatus;
import com.facebook.imageformat.ImageFormat;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class DefaultImageFormatChecker implements ImageFormat.FormatChecker {
    /* access modifiers changed from: private */
    public static final byte[] AVIF_HEADER_PREFIX = ImageFormatCheckerUtils.asciiBytes("ftyp");
    /* access modifiers changed from: private */
    public static final byte[] AVIF_HEADER_SUFFIX = ImageFormatCheckerUtils.asciiBytes("avif");
    /* access modifiers changed from: private */
    public static final byte[] BINARY_XML_HEADER = {3, 0, 8, 0};
    /* access modifiers changed from: private */
    public static final byte[] BMP_HEADER;
    private static final int BMP_HEADER_LENGTH;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final byte[] DNG_HEADER_II;
    /* access modifiers changed from: private */
    public static final int DNG_HEADER_LENGTH;
    /* access modifiers changed from: private */
    public static final byte[] DNG_HEADER_MM = {77, 77, 0, 42};
    /* access modifiers changed from: private */
    public static final byte[] GIF_HEADER_87A = ImageFormatCheckerUtils.asciiBytes("GIF87a");
    /* access modifiers changed from: private */
    public static final byte[] GIF_HEADER_89A = ImageFormatCheckerUtils.asciiBytes("GIF89a");
    /* access modifiers changed from: private */
    public static final byte[] HEIF_HEADER_PREFIX = ImageFormatCheckerUtils.asciiBytes("ftyp");
    /* access modifiers changed from: private */
    public static final byte[][] HEIF_HEADER_SUFFIXES = {ImageFormatCheckerUtils.asciiBytes("heic"), ImageFormatCheckerUtils.asciiBytes("heix"), ImageFormatCheckerUtils.asciiBytes("hevc"), ImageFormatCheckerUtils.asciiBytes("hevx"), ImageFormatCheckerUtils.asciiBytes("mif1"), ImageFormatCheckerUtils.asciiBytes("msf1")};
    /* access modifiers changed from: private */
    public static final byte[] ICO_HEADER;
    private static final int ICO_HEADER_LENGTH;
    /* access modifiers changed from: private */
    public static final byte[] JPEG_HEADER;
    private static final int JPEG_HEADER_LENGTH;
    /* access modifiers changed from: private */
    public static final byte[] PNG_HEADER;
    private static final int PNG_HEADER_LENGTH;
    private final int headerSize;

    public DefaultImageFormatChecker() {
        Comparable maxOrNull = ArraysKt.maxOrNull((Comparable[]) new Integer[]{21, 20, Integer.valueOf(JPEG_HEADER_LENGTH), Integer.valueOf(PNG_HEADER_LENGTH), 6, Integer.valueOf(BMP_HEADER_LENGTH), Integer.valueOf(ICO_HEADER_LENGTH), 12, 4, 12});
        if (maxOrNull != null) {
            this.headerSize = ((Number) maxOrNull).intValue();
            return;
        }
        throw new IllegalStateException("Required value was null.");
    }

    public int getHeaderSize() {
        return this.headerSize;
    }

    public ImageFormat determineFormat(byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(bArr, "headerBytes");
        if (WebpSupportStatus.isWebpHeader(bArr, 0, i)) {
            return Companion.getWebpFormat(bArr, i);
        }
        Companion companion = Companion;
        if (companion.isJpegHeader(bArr, i)) {
            return DefaultImageFormats.JPEG;
        }
        if (companion.isPngHeader(bArr, i)) {
            return DefaultImageFormats.PNG;
        }
        if (companion.isGifHeader(bArr, i)) {
            return DefaultImageFormats.GIF;
        }
        if (companion.isBmpHeader(bArr, i)) {
            return DefaultImageFormats.BMP;
        }
        if (companion.isIcoHeader(bArr, i)) {
            return DefaultImageFormats.ICO;
        }
        if (companion.isAvifHeader(bArr, i)) {
            return DefaultImageFormats.AVIF;
        }
        if (companion.isHeifHeader(bArr, i)) {
            return DefaultImageFormats.HEIF;
        }
        if (companion.isBinaryXmlHeader(bArr, i)) {
            return DefaultImageFormats.BINARY_XML;
        }
        if (companion.isDngHeader(bArr, i)) {
            return DefaultImageFormats.DNG;
        }
        return ImageFormat.UNKNOWN;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final ImageFormat getWebpFormat(byte[] bArr, int i) {
            if (!WebpSupportStatus.isWebpHeader(bArr, 0, i)) {
                throw new IllegalStateException("Check failed.");
            } else if (WebpSupportStatus.isSimpleWebpHeader(bArr, 0)) {
                return DefaultImageFormats.WEBP_SIMPLE;
            } else {
                if (WebpSupportStatus.isLosslessWebpHeader(bArr, 0)) {
                    return DefaultImageFormats.WEBP_LOSSLESS;
                }
                if (!WebpSupportStatus.isExtendedWebpHeader(bArr, 0, i)) {
                    return ImageFormat.UNKNOWN;
                }
                if (WebpSupportStatus.isAnimatedWebpHeader(bArr, 0)) {
                    return DefaultImageFormats.WEBP_ANIMATED;
                }
                if (WebpSupportStatus.isExtendedWebpHeaderWithAlpha(bArr, 0)) {
                    return DefaultImageFormats.WEBP_EXTENDED_WITH_ALPHA;
                }
                return DefaultImageFormats.WEBP_EXTENDED;
            }
        }

        /* access modifiers changed from: private */
        public final boolean isJpegHeader(byte[] bArr, int i) {
            return i >= DefaultImageFormatChecker.JPEG_HEADER.length && ImageFormatCheckerUtils.startsWithPattern(bArr, DefaultImageFormatChecker.JPEG_HEADER);
        }

        /* access modifiers changed from: private */
        public final boolean isPngHeader(byte[] bArr, int i) {
            return i >= DefaultImageFormatChecker.PNG_HEADER.length && ImageFormatCheckerUtils.startsWithPattern(bArr, DefaultImageFormatChecker.PNG_HEADER);
        }

        /* access modifiers changed from: private */
        public final boolean isGifHeader(byte[] bArr, int i) {
            if (i < 6) {
                return false;
            }
            if (ImageFormatCheckerUtils.startsWithPattern(bArr, DefaultImageFormatChecker.GIF_HEADER_87A) || ImageFormatCheckerUtils.startsWithPattern(bArr, DefaultImageFormatChecker.GIF_HEADER_89A)) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: private */
        public final boolean isBmpHeader(byte[] bArr, int i) {
            if (i < DefaultImageFormatChecker.BMP_HEADER.length) {
                return false;
            }
            return ImageFormatCheckerUtils.startsWithPattern(bArr, DefaultImageFormatChecker.BMP_HEADER);
        }

        /* access modifiers changed from: private */
        public final boolean isIcoHeader(byte[] bArr, int i) {
            if (i < DefaultImageFormatChecker.ICO_HEADER.length) {
                return false;
            }
            return ImageFormatCheckerUtils.startsWithPattern(bArr, DefaultImageFormatChecker.ICO_HEADER);
        }

        /* access modifiers changed from: private */
        public final boolean isHeifHeader(byte[] bArr, int i) {
            if (i < 12 || bArr[3] < 8 || !ImageFormatCheckerUtils.hasPatternAt(bArr, DefaultImageFormatChecker.HEIF_HEADER_PREFIX, 4)) {
                return false;
            }
            for (byte[] hasPatternAt : DefaultImageFormatChecker.HEIF_HEADER_SUFFIXES) {
                if (ImageFormatCheckerUtils.hasPatternAt(bArr, hasPatternAt, 8)) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: private */
        public final boolean isDngHeader(byte[] bArr, int i) {
            return i >= DefaultImageFormatChecker.DNG_HEADER_LENGTH && (ImageFormatCheckerUtils.startsWithPattern(bArr, DefaultImageFormatChecker.DNG_HEADER_II) || ImageFormatCheckerUtils.startsWithPattern(bArr, DefaultImageFormatChecker.DNG_HEADER_MM));
        }

        /* access modifiers changed from: private */
        public final boolean isBinaryXmlHeader(byte[] bArr, int i) {
            return i >= 4 && ImageFormatCheckerUtils.startsWithPattern(bArr, DefaultImageFormatChecker.BINARY_XML_HEADER);
        }

        /* access modifiers changed from: private */
        public final boolean isAvifHeader(byte[] bArr, int i) {
            if (i >= 12 && getBoxLength(bArr) >= 8 && ImageFormatCheckerUtils.hasPatternAt(bArr, DefaultImageFormatChecker.AVIF_HEADER_PREFIX, 4)) {
                return ImageFormatCheckerUtils.hasPatternAt(bArr, DefaultImageFormatChecker.AVIF_HEADER_SUFFIX, 8);
            }
            return false;
        }

        private final int getBoxLength(byte[] bArr) {
            if (bArr.length < 4) {
                return -1;
            }
            return (bArr[3] & 255) | ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8);
        }
    }

    static {
        byte[] bArr = {-1, -40, -1};
        JPEG_HEADER = bArr;
        JPEG_HEADER_LENGTH = bArr.length;
        byte[] bArr2 = {-119, 80, 78, 71, 13, 10, 26, 10};
        PNG_HEADER = bArr2;
        PNG_HEADER_LENGTH = bArr2.length;
        byte[] asciiBytes = ImageFormatCheckerUtils.asciiBytes("BM");
        BMP_HEADER = asciiBytes;
        BMP_HEADER_LENGTH = asciiBytes.length;
        byte[] bArr3 = {0, 0, 1, 0};
        ICO_HEADER = bArr3;
        ICO_HEADER_LENGTH = bArr3.length;
        byte[] bArr4 = {73, 73, 42, 0};
        DNG_HEADER_II = bArr4;
        DNG_HEADER_LENGTH = bArr4.length;
    }
}
