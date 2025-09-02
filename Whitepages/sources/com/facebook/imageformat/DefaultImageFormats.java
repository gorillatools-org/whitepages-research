package com.facebook.imageformat;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public final class DefaultImageFormats {
    public static final ImageFormat AVIF;
    public static final ImageFormat BINARY_XML;
    public static final ImageFormat BMP;
    public static final ImageFormat DNG = new ImageFormat("DNG", "dng");
    public static final ImageFormat GIF;
    public static final ImageFormat HEIF;
    public static final ImageFormat ICO;
    public static final DefaultImageFormats INSTANCE = new DefaultImageFormats();
    public static final ImageFormat JPEG;
    public static final ImageFormat PNG;
    public static final ImageFormat WEBP_ANIMATED;
    public static final ImageFormat WEBP_EXTENDED;
    public static final ImageFormat WEBP_EXTENDED_WITH_ALPHA;
    public static final ImageFormat WEBP_LOSSLESS;
    public static final ImageFormat WEBP_SIMPLE;
    public static final List defaultFormats;

    private DefaultImageFormats() {
    }

    static {
        ImageFormat imageFormat = new ImageFormat("JPEG", "jpeg");
        JPEG = imageFormat;
        ImageFormat imageFormat2 = new ImageFormat("PNG", "png");
        PNG = imageFormat2;
        ImageFormat imageFormat3 = new ImageFormat("GIF", "gif");
        GIF = imageFormat3;
        ImageFormat imageFormat4 = new ImageFormat("BMP", "bmp");
        BMP = imageFormat4;
        ImageFormat imageFormat5 = new ImageFormat("ICO", "ico");
        ICO = imageFormat5;
        ImageFormat imageFormat6 = new ImageFormat("WEBP_SIMPLE", "webp");
        WEBP_SIMPLE = imageFormat6;
        ImageFormat imageFormat7 = new ImageFormat("WEBP_LOSSLESS", "webp");
        WEBP_LOSSLESS = imageFormat7;
        ImageFormat imageFormat8 = new ImageFormat("WEBP_EXTENDED", "webp");
        WEBP_EXTENDED = imageFormat8;
        ImageFormat imageFormat9 = new ImageFormat("WEBP_EXTENDED_WITH_ALPHA", "webp");
        WEBP_EXTENDED_WITH_ALPHA = imageFormat9;
        ImageFormat imageFormat10 = new ImageFormat("WEBP_ANIMATED", "webp");
        WEBP_ANIMATED = imageFormat10;
        ImageFormat imageFormat11 = new ImageFormat("HEIF", "heif");
        HEIF = imageFormat11;
        ImageFormat imageFormat12 = new ImageFormat("BINARY_XML", "xml");
        BINARY_XML = imageFormat12;
        ImageFormat imageFormat13 = new ImageFormat("AVIF", "avif");
        AVIF = imageFormat13;
        defaultFormats = CollectionsKt.listOf(imageFormat, imageFormat2, imageFormat3, imageFormat4, imageFormat5, imageFormat6, imageFormat7, imageFormat8, imageFormat9, imageFormat10, imageFormat11, imageFormat12, imageFormat13);
    }

    public static final boolean isWebpFormat(ImageFormat imageFormat) {
        Intrinsics.checkNotNullParameter(imageFormat, "imageFormat");
        return isStaticWebpFormat(imageFormat) || imageFormat == WEBP_ANIMATED;
    }

    public static final boolean isStaticWebpFormat(ImageFormat imageFormat) {
        Intrinsics.checkNotNullParameter(imageFormat, "imageFormat");
        return imageFormat == WEBP_SIMPLE || imageFormat == WEBP_LOSSLESS || imageFormat == WEBP_EXTENDED || imageFormat == WEBP_EXTENDED_WITH_ALPHA;
    }
}
