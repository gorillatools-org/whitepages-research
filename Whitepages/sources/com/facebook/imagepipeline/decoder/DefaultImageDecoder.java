package com.facebook.imagepipeline.decoder;

import android.graphics.ColorSpace;
import android.graphics.Rect;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.Suppliers;
import com.facebook.common.references.CloseableReference;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imageformat.ImageFormatChecker;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.platform.PlatformDecoder;
import com.facebook.imagepipeline.transformation.BitmapTransformation;
import com.facebook.imagepipeline.transformation.TransformationUtils;
import java.io.InputStream;
import java.util.Map;

public class DefaultImageDecoder implements ImageDecoder {
    private final ImageDecoder mAnimatedGifDecoder;
    private final ImageDecoder mAnimatedWebPDecoder;
    private final Map mCustomDecoders;
    private final ImageDecoder mDefaultDecoder;
    /* access modifiers changed from: private */
    public final Supplier mEnableEncodedImageColorSpaceUsage;
    private final PlatformDecoder mPlatformDecoder;
    private final ImageDecoder mXmlDecoder;

    public DefaultImageDecoder(ImageDecoder imageDecoder, ImageDecoder imageDecoder2, ImageDecoder imageDecoder3, PlatformDecoder platformDecoder) {
        this(imageDecoder, imageDecoder2, imageDecoder3, platformDecoder, (Map) null);
    }

    public DefaultImageDecoder(ImageDecoder imageDecoder, ImageDecoder imageDecoder2, ImageDecoder imageDecoder3, PlatformDecoder platformDecoder, Map map) {
        this(imageDecoder, imageDecoder2, imageDecoder3, platformDecoder, map, Suppliers.BOOLEAN_FALSE);
    }

    public DefaultImageDecoder(ImageDecoder imageDecoder, ImageDecoder imageDecoder2, ImageDecoder imageDecoder3, PlatformDecoder platformDecoder, Map map, Supplier supplier) {
        this.mDefaultDecoder = new ImageDecoder() {
            public CloseableImage decode(EncodedImage encodedImage, int i, QualityInfo qualityInfo, ImageDecodeOptions imageDecodeOptions) {
                ColorSpace colorSpace;
                ImageFormat imageFormat = encodedImage.getImageFormat();
                if (((Boolean) DefaultImageDecoder.this.mEnableEncodedImageColorSpaceUsage.get()).booleanValue()) {
                    colorSpace = imageDecodeOptions.colorSpace;
                    if (colorSpace == null) {
                        colorSpace = encodedImage.getColorSpace();
                    }
                } else {
                    colorSpace = imageDecodeOptions.colorSpace;
                }
                ColorSpace colorSpace2 = colorSpace;
                if (imageFormat == DefaultImageFormats.JPEG) {
                    return DefaultImageDecoder.this.decodeJpeg(encodedImage, i, qualityInfo, imageDecodeOptions, colorSpace2);
                }
                if (imageFormat == DefaultImageFormats.GIF) {
                    return DefaultImageDecoder.this.decodeGif(encodedImage, i, qualityInfo, imageDecodeOptions);
                }
                if (imageFormat == DefaultImageFormats.WEBP_ANIMATED) {
                    return DefaultImageDecoder.this.decodeAnimatedWebp(encodedImage, i, qualityInfo, imageDecodeOptions);
                }
                if (imageFormat == DefaultImageFormats.BINARY_XML) {
                    return DefaultImageDecoder.this.decodeXml(encodedImage, i, qualityInfo, imageDecodeOptions);
                }
                if (imageFormat != ImageFormat.UNKNOWN) {
                    return DefaultImageDecoder.this.decodeStaticImage(encodedImage, imageDecodeOptions);
                }
                throw new DecodeException("unknown image format", encodedImage);
            }
        };
        this.mAnimatedGifDecoder = imageDecoder;
        this.mAnimatedWebPDecoder = imageDecoder2;
        this.mXmlDecoder = imageDecoder3;
        this.mPlatformDecoder = platformDecoder;
        this.mCustomDecoders = map;
        this.mEnableEncodedImageColorSpaceUsage = supplier;
    }

    public CloseableImage decode(EncodedImage encodedImage, int i, QualityInfo qualityInfo, ImageDecodeOptions imageDecodeOptions) {
        ImageDecoder imageDecoder;
        InputStream inputStream;
        ImageDecoder imageDecoder2 = imageDecodeOptions.customImageDecoder;
        if (imageDecoder2 != null) {
            return imageDecoder2.decode(encodedImage, i, qualityInfo, imageDecodeOptions);
        }
        ImageFormat imageFormat = encodedImage.getImageFormat();
        if ((imageFormat == null || imageFormat == ImageFormat.UNKNOWN) && (inputStream = encodedImage.getInputStream()) != null) {
            imageFormat = ImageFormatChecker.getImageFormat_WrapIOException(inputStream);
            encodedImage.setImageFormat(imageFormat);
        }
        Map map = this.mCustomDecoders;
        if (map == null || (imageDecoder = (ImageDecoder) map.get(imageFormat)) == null) {
            return this.mDefaultDecoder.decode(encodedImage, i, qualityInfo, imageDecodeOptions);
        }
        return imageDecoder.decode(encodedImage, i, qualityInfo, imageDecodeOptions);
    }

    public CloseableImage decodeGif(EncodedImage encodedImage, int i, QualityInfo qualityInfo, ImageDecodeOptions imageDecodeOptions) {
        ImageDecoder imageDecoder;
        if (encodedImage.getWidth() == -1 || encodedImage.getHeight() == -1) {
            throw new DecodeException("image width or height is incorrect", encodedImage);
        } else if (imageDecodeOptions.forceStaticImage || (imageDecoder = this.mAnimatedGifDecoder) == null) {
            return decodeStaticImage(encodedImage, imageDecodeOptions);
        } else {
            return imageDecoder.decode(encodedImage, i, qualityInfo, imageDecodeOptions);
        }
    }

    public CloseableStaticBitmap decodeStaticImage(EncodedImage encodedImage, ImageDecodeOptions imageDecodeOptions) {
        CloseableReference decodeFromEncodedImageWithColorSpace = this.mPlatformDecoder.decodeFromEncodedImageWithColorSpace(encodedImage, imageDecodeOptions.bitmapConfig, (Rect) null, imageDecodeOptions.colorSpace);
        try {
            TransformationUtils.maybeApplyTransformation((BitmapTransformation) null, decodeFromEncodedImageWithColorSpace);
            Preconditions.checkNotNull(decodeFromEncodedImageWithColorSpace);
            CloseableStaticBitmap of = CloseableStaticBitmap.of(decodeFromEncodedImageWithColorSpace, ImmutableQualityInfo.FULL_QUALITY, encodedImage.getRotationAngle(), encodedImage.getExifOrientation());
            of.putExtra("is_rounded", false);
            return of;
        } finally {
            CloseableReference.closeSafely(decodeFromEncodedImageWithColorSpace);
        }
    }

    public CloseableStaticBitmap decodeJpeg(EncodedImage encodedImage, int i, QualityInfo qualityInfo, ImageDecodeOptions imageDecodeOptions, ColorSpace colorSpace) {
        CloseableReference decodeJPEGFromEncodedImageWithColorSpace = this.mPlatformDecoder.decodeJPEGFromEncodedImageWithColorSpace(encodedImage, imageDecodeOptions.bitmapConfig, (Rect) null, i, colorSpace);
        try {
            TransformationUtils.maybeApplyTransformation((BitmapTransformation) null, decodeJPEGFromEncodedImageWithColorSpace);
            Preconditions.checkNotNull(decodeJPEGFromEncodedImageWithColorSpace);
            CloseableStaticBitmap of = CloseableStaticBitmap.of(decodeJPEGFromEncodedImageWithColorSpace, qualityInfo, encodedImage.getRotationAngle(), encodedImage.getExifOrientation());
            of.putExtra("is_rounded", false);
            return of;
        } finally {
            CloseableReference.closeSafely(decodeJPEGFromEncodedImageWithColorSpace);
        }
    }

    public CloseableImage decodeAnimatedWebp(EncodedImage encodedImage, int i, QualityInfo qualityInfo, ImageDecodeOptions imageDecodeOptions) {
        ImageDecoder imageDecoder;
        if (imageDecodeOptions.forceStaticImage || (imageDecoder = this.mAnimatedWebPDecoder) == null) {
            return decodeStaticImage(encodedImage, imageDecodeOptions);
        }
        return imageDecoder.decode(encodedImage, i, qualityInfo, imageDecodeOptions);
    }

    /* access modifiers changed from: private */
    public CloseableImage decodeXml(EncodedImage encodedImage, int i, QualityInfo qualityInfo, ImageDecodeOptions imageDecodeOptions) {
        ImageDecoder imageDecoder = this.mXmlDecoder;
        if (imageDecoder != null) {
            return imageDecoder.decode(encodedImage, i, qualityInfo, imageDecodeOptions);
        }
        return null;
    }
}
