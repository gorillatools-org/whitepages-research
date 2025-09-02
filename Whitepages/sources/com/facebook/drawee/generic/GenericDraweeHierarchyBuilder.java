package com.facebook.drawee.generic;

import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import com.facebook.common.internal.Preconditions;
import com.facebook.drawee.drawable.ScalingUtils$ScaleType;
import com.facebook.react.views.image.ReactImageView;
import java.util.Arrays;
import java.util.List;

public class GenericDraweeHierarchyBuilder {
    public static final ScalingUtils$ScaleType DEFAULT_ACTUAL_IMAGE_SCALE_TYPE = ScalingUtils$ScaleType.CENTER_CROP;
    public static final ScalingUtils$ScaleType DEFAULT_SCALE_TYPE = ScalingUtils$ScaleType.CENTER_INSIDE;
    private ColorFilter mActualImageColorFilter;
    private PointF mActualImageFocusPoint;
    private Matrix mActualImageMatrix;
    private ScalingUtils$ScaleType mActualImageScaleType;
    private Drawable mBackground;
    private float mDesiredAspectRatio;
    private int mFadeDuration;
    private Drawable mFailureImage;
    private ScalingUtils$ScaleType mFailureImageScaleType;
    private List mOverlays;
    private Drawable mPlaceholderImage;
    private ScalingUtils$ScaleType mPlaceholderImageScaleType;
    private Drawable mPressedStateOverlay;
    private Drawable mProgressBarImage;
    private ScalingUtils$ScaleType mProgressBarImageScaleType;
    private Resources mResources;
    private Drawable mRetryImage;
    private ScalingUtils$ScaleType mRetryImageScaleType;
    private RoundingParams mRoundingParams;

    public GenericDraweeHierarchyBuilder(Resources resources) {
        this.mResources = resources;
        init();
    }

    public static GenericDraweeHierarchyBuilder newInstance(Resources resources) {
        return new GenericDraweeHierarchyBuilder(resources);
    }

    private void init() {
        this.mFadeDuration = ReactImageView.REMOTE_IMAGE_FADE_DURATION_MS;
        this.mDesiredAspectRatio = 0.0f;
        this.mPlaceholderImage = null;
        ScalingUtils$ScaleType scalingUtils$ScaleType = DEFAULT_SCALE_TYPE;
        this.mPlaceholderImageScaleType = scalingUtils$ScaleType;
        this.mRetryImage = null;
        this.mRetryImageScaleType = scalingUtils$ScaleType;
        this.mFailureImage = null;
        this.mFailureImageScaleType = scalingUtils$ScaleType;
        this.mProgressBarImage = null;
        this.mProgressBarImageScaleType = scalingUtils$ScaleType;
        this.mActualImageScaleType = DEFAULT_ACTUAL_IMAGE_SCALE_TYPE;
        this.mActualImageMatrix = null;
        this.mActualImageFocusPoint = null;
        this.mActualImageColorFilter = null;
        this.mBackground = null;
        this.mOverlays = null;
        this.mPressedStateOverlay = null;
        this.mRoundingParams = null;
    }

    public Resources getResources() {
        return this.mResources;
    }

    public GenericDraweeHierarchyBuilder setFadeDuration(int i) {
        this.mFadeDuration = i;
        return this;
    }

    public int getFadeDuration() {
        return this.mFadeDuration;
    }

    public GenericDraweeHierarchyBuilder setDesiredAspectRatio(float f) {
        this.mDesiredAspectRatio = f;
        return this;
    }

    public float getDesiredAspectRatio() {
        return this.mDesiredAspectRatio;
    }

    public GenericDraweeHierarchyBuilder setPlaceholderImage(Drawable drawable) {
        this.mPlaceholderImage = drawable;
        return this;
    }

    public Drawable getPlaceholderImage() {
        return this.mPlaceholderImage;
    }

    public GenericDraweeHierarchyBuilder setPlaceholderImageScaleType(ScalingUtils$ScaleType scalingUtils$ScaleType) {
        this.mPlaceholderImageScaleType = scalingUtils$ScaleType;
        return this;
    }

    public ScalingUtils$ScaleType getPlaceholderImageScaleType() {
        return this.mPlaceholderImageScaleType;
    }

    public GenericDraweeHierarchyBuilder setRetryImage(Drawable drawable) {
        this.mRetryImage = drawable;
        return this;
    }

    public Drawable getRetryImage() {
        return this.mRetryImage;
    }

    public GenericDraweeHierarchyBuilder setRetryImageScaleType(ScalingUtils$ScaleType scalingUtils$ScaleType) {
        this.mRetryImageScaleType = scalingUtils$ScaleType;
        return this;
    }

    public ScalingUtils$ScaleType getRetryImageScaleType() {
        return this.mRetryImageScaleType;
    }

    public GenericDraweeHierarchyBuilder setFailureImage(Drawable drawable) {
        this.mFailureImage = drawable;
        return this;
    }

    public Drawable getFailureImage() {
        return this.mFailureImage;
    }

    public GenericDraweeHierarchyBuilder setFailureImageScaleType(ScalingUtils$ScaleType scalingUtils$ScaleType) {
        this.mFailureImageScaleType = scalingUtils$ScaleType;
        return this;
    }

    public ScalingUtils$ScaleType getFailureImageScaleType() {
        return this.mFailureImageScaleType;
    }

    public GenericDraweeHierarchyBuilder setProgressBarImage(Drawable drawable) {
        this.mProgressBarImage = drawable;
        return this;
    }

    public Drawable getProgressBarImage() {
        return this.mProgressBarImage;
    }

    public GenericDraweeHierarchyBuilder setProgressBarImageScaleType(ScalingUtils$ScaleType scalingUtils$ScaleType) {
        this.mProgressBarImageScaleType = scalingUtils$ScaleType;
        return this;
    }

    public ScalingUtils$ScaleType getProgressBarImageScaleType() {
        return this.mProgressBarImageScaleType;
    }

    public GenericDraweeHierarchyBuilder setActualImageScaleType(ScalingUtils$ScaleType scalingUtils$ScaleType) {
        this.mActualImageScaleType = scalingUtils$ScaleType;
        this.mActualImageMatrix = null;
        return this;
    }

    public ScalingUtils$ScaleType getActualImageScaleType() {
        return this.mActualImageScaleType;
    }

    public PointF getActualImageFocusPoint() {
        return this.mActualImageFocusPoint;
    }

    public ColorFilter getActualImageColorFilter() {
        return this.mActualImageColorFilter;
    }

    public GenericDraweeHierarchyBuilder setBackground(Drawable drawable) {
        this.mBackground = drawable;
        return this;
    }

    public Drawable getBackground() {
        return this.mBackground;
    }

    public GenericDraweeHierarchyBuilder setOverlay(Drawable drawable) {
        if (drawable == null) {
            this.mOverlays = null;
        } else {
            this.mOverlays = Arrays.asList(new Drawable[]{drawable});
        }
        return this;
    }

    public List getOverlays() {
        return this.mOverlays;
    }

    public GenericDraweeHierarchyBuilder setPressedStateOverlay(Drawable drawable) {
        if (drawable == null) {
            this.mPressedStateOverlay = null;
        } else {
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{16842919}, drawable);
            this.mPressedStateOverlay = stateListDrawable;
        }
        return this;
    }

    public Drawable getPressedStateOverlay() {
        return this.mPressedStateOverlay;
    }

    public GenericDraweeHierarchyBuilder setRoundingParams(RoundingParams roundingParams) {
        this.mRoundingParams = roundingParams;
        return this;
    }

    public RoundingParams getRoundingParams() {
        return this.mRoundingParams;
    }

    private void validate() {
        List<Drawable> list = this.mOverlays;
        if (list != null) {
            for (Drawable checkNotNull : list) {
                Preconditions.checkNotNull(checkNotNull);
            }
        }
    }

    public GenericDraweeHierarchy build() {
        validate();
        return new GenericDraweeHierarchy(this);
    }
}
