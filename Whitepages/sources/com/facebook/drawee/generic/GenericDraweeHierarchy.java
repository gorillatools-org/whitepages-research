package com.facebook.drawee.generic;

import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.Preconditions;
import com.facebook.drawee.drawable.DrawableParent;
import com.facebook.drawee.drawable.FadeDrawable;
import com.facebook.drawee.drawable.ForwardingDrawable;
import com.facebook.drawee.drawable.ScaleTypeDrawable;
import com.facebook.drawee.drawable.ScalingUtils$ScaleType;
import com.facebook.drawee.interfaces.SettableDraweeHierarchy;
import com.facebook.imagepipeline.systrace.FrescoSystrace;

public class GenericDraweeHierarchy implements SettableDraweeHierarchy {
    private final ForwardingDrawable mActualImageWrapper;
    private final Drawable mEmptyActualImageDrawable;
    private final FadeDrawable mFadeDrawable;
    private final Resources mResources;
    private RoundingParams mRoundingParams;
    private final RootDrawable mTopLevelDrawable;

    GenericDraweeHierarchy(GenericDraweeHierarchyBuilder genericDraweeHierarchyBuilder) {
        ColorDrawable colorDrawable = new ColorDrawable(0);
        this.mEmptyActualImageDrawable = colorDrawable;
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("GenericDraweeHierarchy()");
        }
        this.mResources = genericDraweeHierarchyBuilder.getResources();
        this.mRoundingParams = genericDraweeHierarchyBuilder.getRoundingParams();
        ForwardingDrawable forwardingDrawable = new ForwardingDrawable(colorDrawable);
        this.mActualImageWrapper = forwardingDrawable;
        int i = 1;
        int size = genericDraweeHierarchyBuilder.getOverlays() != null ? genericDraweeHierarchyBuilder.getOverlays().size() : 1;
        int i2 = (size == 0 ? 1 : size) + (genericDraweeHierarchyBuilder.getPressedStateOverlay() != null ? 1 : 0);
        Drawable[] drawableArr = new Drawable[(i2 + 6)];
        drawableArr[0] = buildBranch(genericDraweeHierarchyBuilder.getBackground(), (ScalingUtils$ScaleType) null);
        drawableArr[1] = buildBranch(genericDraweeHierarchyBuilder.getPlaceholderImage(), genericDraweeHierarchyBuilder.getPlaceholderImageScaleType());
        drawableArr[2] = buildActualImageBranch(forwardingDrawable, genericDraweeHierarchyBuilder.getActualImageScaleType(), genericDraweeHierarchyBuilder.getActualImageFocusPoint(), genericDraweeHierarchyBuilder.getActualImageColorFilter());
        drawableArr[3] = buildBranch(genericDraweeHierarchyBuilder.getProgressBarImage(), genericDraweeHierarchyBuilder.getProgressBarImageScaleType());
        drawableArr[4] = buildBranch(genericDraweeHierarchyBuilder.getRetryImage(), genericDraweeHierarchyBuilder.getRetryImageScaleType());
        drawableArr[5] = buildBranch(genericDraweeHierarchyBuilder.getFailureImage(), genericDraweeHierarchyBuilder.getFailureImageScaleType());
        if (i2 > 0) {
            if (genericDraweeHierarchyBuilder.getOverlays() != null) {
                i = 0;
                for (Drawable buildBranch : genericDraweeHierarchyBuilder.getOverlays()) {
                    drawableArr[i + 6] = buildBranch(buildBranch, (ScalingUtils$ScaleType) null);
                    i++;
                }
            }
            if (genericDraweeHierarchyBuilder.getPressedStateOverlay() != null) {
                drawableArr[i + 6] = buildBranch(genericDraweeHierarchyBuilder.getPressedStateOverlay(), (ScalingUtils$ScaleType) null);
            }
        }
        FadeDrawable fadeDrawable = new FadeDrawable(drawableArr, false, 2);
        this.mFadeDrawable = fadeDrawable;
        fadeDrawable.setTransitionDuration(genericDraweeHierarchyBuilder.getFadeDuration());
        RootDrawable rootDrawable = new RootDrawable(WrappingUtils.maybeWrapWithRoundedOverlayColor(fadeDrawable, this.mRoundingParams));
        this.mTopLevelDrawable = rootDrawable;
        rootDrawable.mutate();
        resetFade();
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }

    private Drawable buildActualImageBranch(Drawable drawable, ScalingUtils$ScaleType scalingUtils$ScaleType, PointF pointF, ColorFilter colorFilter) {
        drawable.setColorFilter(colorFilter);
        return WrappingUtils.maybeWrapWithScaleType(drawable, scalingUtils$ScaleType, pointF);
    }

    private Drawable buildBranch(Drawable drawable, ScalingUtils$ScaleType scalingUtils$ScaleType) {
        return WrappingUtils.maybeWrapWithScaleType(WrappingUtils.maybeApplyLeafRounding(drawable, this.mRoundingParams, this.mResources), scalingUtils$ScaleType);
    }

    private void resetActualImages() {
        this.mActualImageWrapper.setDrawable(this.mEmptyActualImageDrawable);
    }

    private void resetFade() {
        FadeDrawable fadeDrawable = this.mFadeDrawable;
        if (fadeDrawable != null) {
            fadeDrawable.beginBatchMode();
            this.mFadeDrawable.fadeInAllLayers();
            fadeOutBranches();
            fadeInLayer(1);
            this.mFadeDrawable.finishTransitionImmediately();
            this.mFadeDrawable.endBatchMode();
        }
    }

    private void fadeOutBranches() {
        fadeOutLayer(1);
        fadeOutLayer(2);
        fadeOutLayer(3);
        fadeOutLayer(4);
        fadeOutLayer(5);
    }

    private void fadeInLayer(int i) {
        if (i >= 0) {
            this.mFadeDrawable.fadeInLayer(i);
        }
    }

    private void fadeOutLayer(int i) {
        if (i >= 0) {
            this.mFadeDrawable.fadeOutLayer(i);
        }
    }

    private void setProgress(float f) {
        Drawable drawable = this.mFadeDrawable.getDrawable(3);
        if (drawable != null) {
            if (f >= 0.999f) {
                if (drawable instanceof Animatable) {
                    ((Animatable) drawable).stop();
                }
                fadeOutLayer(3);
            } else {
                if (drawable instanceof Animatable) {
                    ((Animatable) drawable).start();
                }
                fadeInLayer(3);
            }
            drawable.setLevel(Math.round(f * 10000.0f));
        }
    }

    public Drawable getTopLevelDrawable() {
        return this.mTopLevelDrawable;
    }

    public void reset() {
        resetActualImages();
        resetFade();
    }

    public void setImage(Drawable drawable, float f, boolean z) {
        Drawable maybeApplyLeafRounding = WrappingUtils.maybeApplyLeafRounding(drawable, this.mRoundingParams, this.mResources);
        maybeApplyLeafRounding.mutate();
        this.mActualImageWrapper.setDrawable(maybeApplyLeafRounding);
        this.mFadeDrawable.beginBatchMode();
        fadeOutBranches();
        fadeInLayer(2);
        setProgress(f);
        if (z) {
            this.mFadeDrawable.finishTransitionImmediately();
        }
        this.mFadeDrawable.endBatchMode();
    }

    public void setProgress(float f, boolean z) {
        if (this.mFadeDrawable.getDrawable(3) != null) {
            this.mFadeDrawable.beginBatchMode();
            setProgress(f);
            if (z) {
                this.mFadeDrawable.finishTransitionImmediately();
            }
            this.mFadeDrawable.endBatchMode();
        }
    }

    public void setFailure(Throwable th) {
        this.mFadeDrawable.beginBatchMode();
        fadeOutBranches();
        if (this.mFadeDrawable.getDrawable(5) != null) {
            fadeInLayer(5);
        } else {
            fadeInLayer(1);
        }
        this.mFadeDrawable.endBatchMode();
    }

    public void setRetry(Throwable th) {
        this.mFadeDrawable.beginBatchMode();
        fadeOutBranches();
        if (this.mFadeDrawable.getDrawable(4) != null) {
            fadeInLayer(4);
        } else {
            fadeInLayer(1);
        }
        this.mFadeDrawable.endBatchMode();
    }

    public void setControllerOverlay(Drawable drawable) {
        this.mTopLevelDrawable.setControllerOverlay(drawable);
    }

    public Rect getBounds() {
        return this.mTopLevelDrawable.getBounds();
    }

    private DrawableParent getParentDrawableAtIndex(int i) {
        DrawableParent drawableParentForIndex = this.mFadeDrawable.getDrawableParentForIndex(i);
        drawableParentForIndex.getDrawable();
        return drawableParentForIndex.getDrawable() instanceof ScaleTypeDrawable ? (ScaleTypeDrawable) drawableParentForIndex.getDrawable() : drawableParentForIndex;
    }

    private void setChildDrawableAtIndex(int i, Drawable drawable) {
        if (drawable == null) {
            this.mFadeDrawable.setDrawable(i, (Drawable) null);
            return;
        }
        getParentDrawableAtIndex(i).setDrawable(WrappingUtils.maybeApplyLeafRounding(drawable, this.mRoundingParams, this.mResources));
    }

    private ScaleTypeDrawable getScaleTypeDrawableAtIndex(int i) {
        DrawableParent parentDrawableAtIndex = getParentDrawableAtIndex(i);
        if (parentDrawableAtIndex instanceof ScaleTypeDrawable) {
            return (ScaleTypeDrawable) parentDrawableAtIndex;
        }
        return WrappingUtils.wrapChildWithScaleType(parentDrawableAtIndex, ScalingUtils$ScaleType.FIT_XY);
    }

    private boolean hasScaleTypeDrawableAtIndex(int i) {
        return getParentDrawableAtIndex(i) instanceof ScaleTypeDrawable;
    }

    public void setFadeDuration(int i) {
        this.mFadeDrawable.setTransitionDuration(i);
    }

    public void setActualImageScaleType(ScalingUtils$ScaleType scalingUtils$ScaleType) {
        Preconditions.checkNotNull(scalingUtils$ScaleType);
        getScaleTypeDrawableAtIndex(2).setScaleType(scalingUtils$ScaleType);
    }

    public ScalingUtils$ScaleType getActualImageScaleType() {
        if (!hasScaleTypeDrawableAtIndex(2)) {
            return null;
        }
        return getScaleTypeDrawableAtIndex(2).getScaleType();
    }

    public PointF getActualImageFocusPoint() {
        if (!hasScaleTypeDrawableAtIndex(2)) {
            return null;
        }
        return getScaleTypeDrawableAtIndex(2).getFocusPoint();
    }

    public void setPlaceholderImage(Drawable drawable, ScalingUtils$ScaleType scalingUtils$ScaleType) {
        setChildDrawableAtIndex(1, drawable);
        getScaleTypeDrawableAtIndex(1).setScaleType(scalingUtils$ScaleType);
    }

    public void setProgressBarImage(Drawable drawable) {
        setChildDrawableAtIndex(3, drawable);
    }

    public void setRoundingParams(RoundingParams roundingParams) {
        this.mRoundingParams = roundingParams;
        WrappingUtils.updateOverlayColorRounding(this.mTopLevelDrawable, roundingParams);
        for (int i = 0; i < this.mFadeDrawable.getNumberOfLayers(); i++) {
            WrappingUtils.updateLeafRounding(getParentDrawableAtIndex(i), this.mRoundingParams, this.mResources);
        }
    }

    public RoundingParams getRoundingParams() {
        return this.mRoundingParams;
    }
}
