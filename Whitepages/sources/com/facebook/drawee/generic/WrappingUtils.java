package com.facebook.drawee.generic;

import android.content.res.Resources;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.drawable.DrawableParent;
import com.facebook.drawee.drawable.ForwardingDrawable;
import com.facebook.drawee.drawable.Rounded;
import com.facebook.drawee.drawable.RoundedBitmapDrawable;
import com.facebook.drawee.drawable.RoundedColorDrawable;
import com.facebook.drawee.drawable.RoundedCornersDrawable;
import com.facebook.drawee.drawable.RoundedNinePatchDrawable;
import com.facebook.drawee.drawable.ScaleTypeDrawable;
import com.facebook.drawee.drawable.ScalingUtils$ScaleType;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.imagepipeline.systrace.FrescoSystrace;

public abstract class WrappingUtils {
    private static final Drawable sEmptyDrawable = new ColorDrawable(0);

    static Drawable maybeWrapWithScaleType(Drawable drawable, ScalingUtils$ScaleType scalingUtils$ScaleType) {
        return maybeWrapWithScaleType(drawable, scalingUtils$ScaleType, (PointF) null);
    }

    static Drawable maybeWrapWithScaleType(Drawable drawable, ScalingUtils$ScaleType scalingUtils$ScaleType, PointF pointF) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("WrappingUtils#maybeWrapWithScaleType");
        }
        if (drawable == null || scalingUtils$ScaleType == null) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            return drawable;
        }
        ScaleTypeDrawable scaleTypeDrawable = new ScaleTypeDrawable(drawable, scalingUtils$ScaleType);
        if (pointF != null) {
            scaleTypeDrawable.setFocusPoint(pointF);
        }
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
        return scaleTypeDrawable;
    }

    static ScaleTypeDrawable wrapChildWithScaleType(DrawableParent drawableParent, ScalingUtils$ScaleType scalingUtils$ScaleType) {
        Drawable maybeWrapWithScaleType = maybeWrapWithScaleType(drawableParent.setDrawable(sEmptyDrawable), scalingUtils$ScaleType);
        drawableParent.setDrawable(maybeWrapWithScaleType);
        Preconditions.checkNotNull(maybeWrapWithScaleType, "Parent has no child drawable!");
        return (ScaleTypeDrawable) maybeWrapWithScaleType;
    }

    static void updateOverlayColorRounding(DrawableParent drawableParent, RoundingParams roundingParams) {
        Drawable drawable = drawableParent.getDrawable();
        if (roundingParams == null || roundingParams.getRoundingMethod() != RoundingParams.RoundingMethod.OVERLAY_COLOR) {
            if (drawable instanceof RoundedCornersDrawable) {
                Drawable drawable2 = sEmptyDrawable;
                drawableParent.setDrawable(((RoundedCornersDrawable) drawable).setCurrent(drawable2));
                drawable2.setCallback((Drawable.Callback) null);
            }
        } else if (drawable instanceof RoundedCornersDrawable) {
            RoundedCornersDrawable roundedCornersDrawable = (RoundedCornersDrawable) drawable;
            applyRoundingParams(roundedCornersDrawable, roundingParams);
            roundedCornersDrawable.setOverlayColor(roundingParams.getOverlayColor());
        } else {
            drawableParent.setDrawable(maybeWrapWithRoundedOverlayColor(drawableParent.setDrawable(sEmptyDrawable), roundingParams));
        }
    }

    static void updateLeafRounding(DrawableParent drawableParent, RoundingParams roundingParams, Resources resources) {
        DrawableParent findDrawableParentForLeaf = findDrawableParentForLeaf(drawableParent);
        Drawable drawable = findDrawableParentForLeaf.getDrawable();
        if (roundingParams == null || roundingParams.getRoundingMethod() != RoundingParams.RoundingMethod.BITMAP_ONLY) {
            if (drawable instanceof Rounded) {
                resetRoundingParams((Rounded) drawable);
            }
        } else if (drawable instanceof Rounded) {
            applyRoundingParams((Rounded) drawable, roundingParams);
        } else if (drawable != null) {
            findDrawableParentForLeaf.setDrawable(sEmptyDrawable);
            findDrawableParentForLeaf.setDrawable(applyLeafRounding(drawable, roundingParams, resources));
        }
    }

    static Drawable maybeWrapWithRoundedOverlayColor(Drawable drawable, RoundingParams roundingParams) {
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("WrappingUtils#maybeWrapWithRoundedOverlayColor");
            }
            if (!(drawable == null || roundingParams == null)) {
                if (roundingParams.getRoundingMethod() == RoundingParams.RoundingMethod.OVERLAY_COLOR) {
                    RoundedCornersDrawable roundedCornersDrawable = new RoundedCornersDrawable(drawable);
                    applyRoundingParams(roundedCornersDrawable, roundingParams);
                    roundedCornersDrawable.setOverlayColor(roundingParams.getOverlayColor());
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                    }
                    return roundedCornersDrawable;
                }
            }
            return drawable;
        } finally {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
        }
    }

    static Drawable maybeApplyLeafRounding(Drawable drawable, RoundingParams roundingParams, Resources resources) {
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("WrappingUtils#maybeApplyLeafRounding");
            }
            if (!(drawable == null || roundingParams == null)) {
                if (roundingParams.getRoundingMethod() == RoundingParams.RoundingMethod.BITMAP_ONLY) {
                    if (drawable instanceof ForwardingDrawable) {
                        DrawableParent findDrawableParentForLeaf = findDrawableParentForLeaf((ForwardingDrawable) drawable);
                        findDrawableParentForLeaf.setDrawable(applyLeafRounding(findDrawableParentForLeaf.setDrawable(sEmptyDrawable), roundingParams, resources));
                        if (FrescoSystrace.isTracing()) {
                            FrescoSystrace.endSection();
                        }
                        return drawable;
                    }
                    Drawable applyLeafRounding = applyLeafRounding(drawable, roundingParams, resources);
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                    }
                    return applyLeafRounding;
                }
            }
            return drawable;
        } finally {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
        }
    }

    private static Drawable applyLeafRounding(Drawable drawable, RoundingParams roundingParams, Resources resources) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            RoundedBitmapDrawable roundedBitmapDrawable = new RoundedBitmapDrawable(resources, bitmapDrawable.getBitmap(), bitmapDrawable.getPaint(), roundingParams.getRepeatEdgePixels());
            applyRoundingParams(roundedBitmapDrawable, roundingParams);
            return roundedBitmapDrawable;
        } else if (drawable instanceof NinePatchDrawable) {
            RoundedNinePatchDrawable roundedNinePatchDrawable = new RoundedNinePatchDrawable((NinePatchDrawable) drawable);
            applyRoundingParams(roundedNinePatchDrawable, roundingParams);
            return roundedNinePatchDrawable;
        } else if (drawable instanceof ColorDrawable) {
            RoundedColorDrawable fromColorDrawable = RoundedColorDrawable.fromColorDrawable((ColorDrawable) drawable);
            applyRoundingParams(fromColorDrawable, roundingParams);
            return fromColorDrawable;
        } else {
            FLog.w("WrappingUtils", "Don't know how to round that drawable: %s", drawable);
            return drawable;
        }
    }

    static void applyRoundingParams(Rounded rounded, RoundingParams roundingParams) {
        rounded.setCircle(roundingParams.getRoundAsCircle());
        rounded.setRadii(roundingParams.getCornersRadii());
        rounded.setBorder(roundingParams.getBorderColor(), roundingParams.getBorderWidth());
        rounded.setPadding(roundingParams.getPadding());
        rounded.setScaleDownInsideBorders(roundingParams.getScaleDownInsideBorders());
        rounded.setPaintFilterBitmap(roundingParams.getPaintFilterBitmap());
        rounded.setRepeatEdgePixels(roundingParams.getRepeatEdgePixels());
    }

    static void resetRoundingParams(Rounded rounded) {
        rounded.setCircle(false);
        rounded.setRadius(0.0f);
        rounded.setBorder(0, 0.0f);
        rounded.setPadding(0.0f);
        rounded.setScaleDownInsideBorders(false);
        rounded.setPaintFilterBitmap(false);
        rounded.setRepeatEdgePixels(RoundedBitmapDrawable.getDefaultRepeatEdgePixels());
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [android.graphics.drawable.Drawable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.facebook.drawee.drawable.DrawableParent findDrawableParentForLeaf(com.facebook.drawee.drawable.DrawableParent r2) {
        /*
        L_0x0000:
            android.graphics.drawable.Drawable r0 = r2.getDrawable()
            if (r0 == r2) goto L_0x000f
            boolean r1 = r0 instanceof com.facebook.drawee.drawable.DrawableParent
            if (r1 != 0) goto L_0x000b
            goto L_0x000f
        L_0x000b:
            r2 = r0
            com.facebook.drawee.drawable.DrawableParent r2 = (com.facebook.drawee.drawable.DrawableParent) r2
            goto L_0x0000
        L_0x000f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.drawee.generic.WrappingUtils.findDrawableParentForLeaf(com.facebook.drawee.drawable.DrawableParent):com.facebook.drawee.drawable.DrawableParent");
    }
}
