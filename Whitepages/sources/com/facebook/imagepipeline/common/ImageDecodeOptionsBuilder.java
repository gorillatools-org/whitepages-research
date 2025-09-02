package com.facebook.imagepipeline.common;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.transformation.BitmapTransformation;

public class ImageDecodeOptionsBuilder {
    private Bitmap.Config mAnimatedBitmapConfig;
    private Bitmap.Config mBitmapConfig;
    private ColorSpace mColorSpace;
    private ImageDecoder mCustomImageDecoder;
    private boolean mDecodeAllFrames;
    private boolean mDecodePreviewFrame;
    private boolean mExcludeBitmapConfigFromComparison;
    private boolean mForceStaticImage;
    private int mMaxDimensionPx = Integer.MAX_VALUE;
    private int mMinDecodeIntervalMs = 100;
    private boolean mUseEncodedImageForPreivew;
    private boolean mUseLastFrameForPreview;

    public BitmapTransformation getBitmapTransformation() {
        return null;
    }

    public ImageDecodeOptionsBuilder() {
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        this.mBitmapConfig = config;
        this.mAnimatedBitmapConfig = config;
    }

    public int getMinDecodeIntervalMs() {
        return this.mMinDecodeIntervalMs;
    }

    public int getMaxDimensionPx() {
        return this.mMaxDimensionPx;
    }

    public boolean getDecodePreviewFrame() {
        return this.mDecodePreviewFrame;
    }

    public boolean getUseLastFrameForPreview() {
        return this.mUseLastFrameForPreview;
    }

    public boolean getUseEncodedImageForPreview() {
        return this.mUseEncodedImageForPreivew;
    }

    public boolean getDecodeAllFrames() {
        return this.mDecodeAllFrames;
    }

    public ImageDecoder getCustomImageDecoder() {
        return this.mCustomImageDecoder;
    }

    public boolean getForceStaticImage() {
        return this.mForceStaticImage;
    }

    public Bitmap.Config getBitmapConfig() {
        return this.mBitmapConfig;
    }

    public Bitmap.Config getAnimatedBitmapConfig() {
        return this.mAnimatedBitmapConfig;
    }

    public ColorSpace getColorSpace() {
        return this.mColorSpace;
    }

    public boolean getExcludeBitmapConfigFromComparison() {
        return this.mExcludeBitmapConfigFromComparison;
    }

    public ImageDecodeOptions build() {
        return new ImageDecodeOptions(this);
    }
}
