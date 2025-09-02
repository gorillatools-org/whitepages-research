package com.facebook.imagepipeline.nativecode;

import android.graphics.ColorSpace;
import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.Preconditions;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.transcoder.DownsampleUtil;
import com.facebook.imagepipeline.transcoder.ImageTranscodeResult;
import com.facebook.imagepipeline.transcoder.ImageTranscoder;
import com.facebook.imagepipeline.transcoder.JpegTranscoderUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@DoNotStrip
public class NativeJpegTranscoder implements ImageTranscoder {
    private int mMaxBitmapSize;
    private boolean mResizingEnabled;
    private boolean mUseDownsamplingRatio;

    @DoNotStrip
    private static native void nativeTranscodeJpeg(InputStream inputStream, OutputStream outputStream, int i, int i2, int i3) throws IOException;

    @DoNotStrip
    private static native void nativeTranscodeJpegWithExifOrientation(InputStream inputStream, OutputStream outputStream, int i, int i2, int i3) throws IOException;

    public NativeJpegTranscoder(boolean z, int i, boolean z2, boolean z3) {
        this.mResizingEnabled = z;
        this.mMaxBitmapSize = i;
        this.mUseDownsamplingRatio = z2;
        if (z3) {
            NativeJpegTranscoderSoLoader.ensure();
        }
    }

    public boolean canResize(EncodedImage encodedImage, RotationOptions rotationOptions, ResizeOptions resizeOptions) {
        if (rotationOptions == null) {
            rotationOptions = RotationOptions.autoRotate();
        }
        return JpegTranscoderUtils.getSoftwareNumerator(rotationOptions, resizeOptions, encodedImage, this.mResizingEnabled) < 8;
    }

    public boolean canTranscode(ImageFormat imageFormat) {
        return imageFormat == DefaultImageFormats.JPEG;
    }

    public String getIdentifier() {
        return "NativeJpegTranscoder";
    }

    /* JADX INFO: finally extract failed */
    public ImageTranscodeResult transcode(EncodedImage encodedImage, OutputStream outputStream, RotationOptions rotationOptions, ResizeOptions resizeOptions, ImageFormat imageFormat, Integer num, ColorSpace colorSpace) {
        if (num == null) {
            num = 85;
        }
        if (rotationOptions == null) {
            rotationOptions = RotationOptions.autoRotate();
        }
        int determineSampleSize = DownsampleUtil.determineSampleSize(rotationOptions, resizeOptions, encodedImage, this.mMaxBitmapSize);
        InputStream inputStream = null;
        try {
            int softwareNumerator = JpegTranscoderUtils.getSoftwareNumerator(rotationOptions, resizeOptions, encodedImage, this.mResizingEnabled);
            int calculateDownsampleNumerator = JpegTranscoderUtils.calculateDownsampleNumerator(determineSampleSize);
            if (this.mUseDownsamplingRatio) {
                softwareNumerator = calculateDownsampleNumerator;
            }
            inputStream = encodedImage.getInputStream();
            if (JpegTranscoderUtils.INVERTED_EXIF_ORIENTATIONS.contains(Integer.valueOf(encodedImage.getExifOrientation()))) {
                transcodeJpegWithExifOrientation((InputStream) Preconditions.checkNotNull(inputStream, "Cannot transcode from null input stream!"), outputStream, JpegTranscoderUtils.getForceRotatedInvertedExifOrientation(rotationOptions, encodedImage), softwareNumerator, num.intValue());
            } else {
                transcodeJpeg((InputStream) Preconditions.checkNotNull(inputStream, "Cannot transcode from null input stream!"), outputStream, JpegTranscoderUtils.getRotationAngle(rotationOptions, encodedImage), softwareNumerator, num.intValue());
            }
            Closeables.closeQuietly(inputStream);
            int i = 1;
            if (determineSampleSize != 1) {
                i = 0;
            }
            return new ImageTranscodeResult(i);
        } catch (Throwable th) {
            Closeables.closeQuietly(inputStream);
            throw th;
        }
    }

    public static void transcodeJpeg(InputStream inputStream, OutputStream outputStream, int i, int i2, int i3) {
        NativeJpegTranscoderSoLoader.ensure();
        boolean z = false;
        Preconditions.checkArgument(Boolean.valueOf(i2 >= 1));
        Preconditions.checkArgument(Boolean.valueOf(i2 <= 16));
        Preconditions.checkArgument(Boolean.valueOf(i3 >= 0));
        Preconditions.checkArgument(Boolean.valueOf(i3 <= 100));
        Preconditions.checkArgument(Boolean.valueOf(JpegTranscoderUtils.isRotationAngleAllowed(i)));
        if (!(i2 == 8 && i == 0)) {
            z = true;
        }
        Preconditions.checkArgument(z, "no transformation requested");
        nativeTranscodeJpeg((InputStream) Preconditions.checkNotNull(inputStream), (OutputStream) Preconditions.checkNotNull(outputStream), i, i2, i3);
    }

    public static void transcodeJpegWithExifOrientation(InputStream inputStream, OutputStream outputStream, int i, int i2, int i3) {
        NativeJpegTranscoderSoLoader.ensure();
        boolean z = false;
        Preconditions.checkArgument(Boolean.valueOf(i2 >= 1));
        Preconditions.checkArgument(Boolean.valueOf(i2 <= 16));
        Preconditions.checkArgument(Boolean.valueOf(i3 >= 0));
        Preconditions.checkArgument(Boolean.valueOf(i3 <= 100));
        Preconditions.checkArgument(Boolean.valueOf(JpegTranscoderUtils.isExifOrientationAllowed(i)));
        if (!(i2 == 8 && i == 1)) {
            z = true;
        }
        Preconditions.checkArgument(z, "no transformation requested");
        nativeTranscodeJpegWithExifOrientation((InputStream) Preconditions.checkNotNull(inputStream), (OutputStream) Preconditions.checkNotNull(outputStream), i, i2, i3);
    }
}
