package com.facebook.imagepipeline.transcoder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.graphics.Matrix;
import android.graphics.Rect;
import com.facebook.common.logging.FLog;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import java.io.OutputStream;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SimpleImageTranscoder implements ImageTranscoder {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final String identifier = "SimpleImageTranscoder";
    private final int maxBitmapSize;
    private final boolean resizingEnabled;

    public SimpleImageTranscoder(boolean z, int i) {
        this.resizingEnabled = z;
        this.maxBitmapSize = i;
    }

    public ImageTranscodeResult transcode(EncodedImage encodedImage, OutputStream outputStream, RotationOptions rotationOptions, ResizeOptions resizeOptions, ImageFormat imageFormat, Integer num, ColorSpace colorSpace) {
        SimpleImageTranscoder simpleImageTranscoder;
        RotationOptions rotationOptions2;
        Bitmap bitmap;
        ImageTranscodeResult imageTranscodeResult;
        EncodedImage encodedImage2 = encodedImage;
        OutputStream outputStream2 = outputStream;
        ColorSpace colorSpace2 = colorSpace;
        Intrinsics.checkNotNullParameter(encodedImage2, "encodedImage");
        Intrinsics.checkNotNullParameter(outputStream2, "outputStream");
        Integer num2 = num == null ? 85 : num;
        if (rotationOptions == null) {
            rotationOptions2 = RotationOptions.Companion.autoRotate();
            simpleImageTranscoder = this;
        } else {
            simpleImageTranscoder = this;
            rotationOptions2 = rotationOptions;
        }
        int sampleSize = simpleImageTranscoder.getSampleSize(encodedImage2, rotationOptions2, resizeOptions);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = sampleSize;
        if (colorSpace2 != null) {
            options.inPreferredColorSpace = colorSpace2;
        }
        try {
            Bitmap decodeStream = BitmapFactory.decodeStream(encodedImage.getInputStream(), (Rect) null, options);
            if (decodeStream == null) {
                FLog.e("SimpleImageTranscoder", "Couldn't decode the EncodedImage InputStream ! ");
                return new ImageTranscodeResult(2);
            }
            Matrix transformationMatrix = JpegTranscoderUtils.getTransformationMatrix(encodedImage2, rotationOptions2);
            if (transformationMatrix != null) {
                try {
                    bitmap = Bitmap.createBitmap(decodeStream, 0, 0, decodeStream.getWidth(), decodeStream.getHeight(), transformationMatrix, false);
                } catch (OutOfMemoryError e) {
                    e = e;
                    bitmap = decodeStream;
                    try {
                        FLog.e("SimpleImageTranscoder", "Out-Of-Memory during transcode", (Throwable) e);
                        imageTranscodeResult = new ImageTranscodeResult(2);
                        bitmap.recycle();
                        decodeStream.recycle();
                        return imageTranscodeResult;
                    } catch (Throwable th) {
                        th = th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bitmap = decodeStream;
                    bitmap.recycle();
                    decodeStream.recycle();
                    throw th;
                }
            } else {
                bitmap = decodeStream;
            }
            try {
                bitmap.compress(Companion.getOutputFormat(imageFormat), num2.intValue(), outputStream2);
                int i = 1;
                if (sampleSize > 1) {
                    i = 0;
                }
                imageTranscodeResult = new ImageTranscodeResult(i);
            } catch (OutOfMemoryError e2) {
                e = e2;
                FLog.e("SimpleImageTranscoder", "Out-Of-Memory during transcode", (Throwable) e);
                imageTranscodeResult = new ImageTranscodeResult(2);
                bitmap.recycle();
                decodeStream.recycle();
                return imageTranscodeResult;
            }
            bitmap.recycle();
            decodeStream.recycle();
            return imageTranscodeResult;
        } catch (OutOfMemoryError e3) {
            FLog.e("SimpleImageTranscoder", "Out-Of-Memory during transcode", (Throwable) e3);
            return new ImageTranscodeResult(2);
        }
    }

    public boolean canResize(EncodedImage encodedImage, RotationOptions rotationOptions, ResizeOptions resizeOptions) {
        Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
        if (rotationOptions == null) {
            rotationOptions = RotationOptions.Companion.autoRotate();
        }
        return this.resizingEnabled && DownsampleUtil.determineSampleSize(rotationOptions, resizeOptions, encodedImage, this.maxBitmapSize) > 1;
    }

    public boolean canTranscode(ImageFormat imageFormat) {
        Intrinsics.checkNotNullParameter(imageFormat, "imageFormat");
        return imageFormat == DefaultImageFormats.HEIF || imageFormat == DefaultImageFormats.JPEG;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    private final int getSampleSize(EncodedImage encodedImage, RotationOptions rotationOptions, ResizeOptions resizeOptions) {
        if (!this.resizingEnabled) {
            return 1;
        }
        return DownsampleUtil.determineSampleSize(rotationOptions, resizeOptions, encodedImage, this.maxBitmapSize);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final Bitmap.CompressFormat getOutputFormat(ImageFormat imageFormat) {
            if (imageFormat == null) {
                return Bitmap.CompressFormat.JPEG;
            }
            if (imageFormat == DefaultImageFormats.JPEG) {
                return Bitmap.CompressFormat.JPEG;
            }
            if (imageFormat == DefaultImageFormats.PNG) {
                return Bitmap.CompressFormat.PNG;
            }
            if (DefaultImageFormats.isStaticWebpFormat(imageFormat)) {
                return Bitmap.CompressFormat.WEBP;
            }
            return Bitmap.CompressFormat.JPEG;
        }
    }
}
