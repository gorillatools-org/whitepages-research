package com.facebook.react.uimanager.drawable;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.facebook.react.uimanager.FilterHelper;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.style.BorderInsets;
import com.facebook.react.uimanager.style.BorderRadiusStyle;
import com.facebook.react.uimanager.style.ComputedBorderRadius;
import com.facebook.react.uimanager.style.CornerRadii;
import java.util.ArrayList;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

public final class InsetBoxShadowDrawable extends Drawable {
    private final float blurRadius;
    private BorderInsets borderInsets;
    private BorderRadiusStyle borderRadius;
    private final Context context;
    private final float offsetX;
    private final float offsetY;
    private final int shadowColor;
    private final Paint shadowPaint;
    private final float spread;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ InsetBoxShadowDrawable(android.content.Context r13, int r14, float r15, float r16, float r17, float r18, com.facebook.react.uimanager.style.BorderInsets r19, com.facebook.react.uimanager.style.BorderRadiusStyle r20, int r21, kotlin.jvm.internal.DefaultConstructorMarker r22) {
        /*
            r12 = this;
            r0 = r21
            r1 = r0 & 64
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r10 = r2
            goto L_0x000b
        L_0x0009:
            r10 = r19
        L_0x000b:
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x0011
            r11 = r2
            goto L_0x0013
        L_0x0011:
            r11 = r20
        L_0x0013:
            r3 = r12
            r4 = r13
            r5 = r14
            r6 = r15
            r7 = r16
            r8 = r17
            r9 = r18
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.drawable.InsetBoxShadowDrawable.<init>(android.content.Context, int, float, float, float, float, com.facebook.react.uimanager.style.BorderInsets, com.facebook.react.uimanager.style.BorderRadiusStyle, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final BorderInsets getBorderInsets() {
        return this.borderInsets;
    }

    public final void setBorderInsets(BorderInsets borderInsets2) {
        this.borderInsets = borderInsets2;
    }

    public final BorderRadiusStyle getBorderRadius() {
        return this.borderRadius;
    }

    public final void setBorderRadius(BorderRadiusStyle borderRadiusStyle) {
        this.borderRadius = borderRadiusStyle;
    }

    public InsetBoxShadowDrawable(Context context2, int i, float f, float f2, float f3, float f4, BorderInsets borderInsets2, BorderRadiusStyle borderRadiusStyle) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
        this.shadowColor = i;
        this.offsetX = f;
        this.offsetY = f2;
        this.blurRadius = f3;
        this.spread = f4;
        this.borderInsets = borderInsets2;
        this.borderRadius = borderRadiusStyle;
        Paint paint = new Paint();
        paint.setColor(i);
        if (f3 > 0.0f) {
            paint.setMaskFilter(new BlurMaskFilter(FilterHelper.INSTANCE.sigmaToRadius$ReactAndroid_release(f3 * 0.5f), BlurMaskFilter.Blur.NORMAL));
        }
        this.shadowPaint = paint;
    }

    public void setAlpha(int i) {
        this.shadowPaint.setAlpha(MathKt.roundToInt((((float) i) / 255.0f) * (((float) Color.alpha(this.shadowColor)) / 255.0f) * 255.0f));
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.shadowPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public int getOpacity() {
        int alpha = this.shadowPaint.getAlpha();
        if (alpha == 255) {
            return -1;
        }
        return (1 > alpha || alpha >= 255) ? -2 : -3;
    }

    public void draw(Canvas canvas) {
        float[] fArr;
        Canvas canvas2 = canvas;
        Intrinsics.checkNotNullParameter(canvas2, "canvas");
        ComputedBorderRadius computeBorderRadii = computeBorderRadii();
        RectF computeBorderInsets = computeBorderInsets();
        RectF rectF = new RectF(((float) getBounds().left) + (computeBorderInsets != null ? computeBorderInsets.left : 0.0f), ((float) getBounds().top) + (computeBorderInsets != null ? computeBorderInsets.top : 0.0f), ((float) getBounds().right) - (computeBorderInsets != null ? computeBorderInsets.right : 0.0f), ((float) getBounds().bottom) - (computeBorderInsets != null ? computeBorderInsets.bottom : 0.0f));
        if (computeBorderRadii != null) {
            fArr = new float[]{innerRadius(computeBorderRadii.getTopLeft().getHorizontal(), computeBorderInsets != null ? Float.valueOf(computeBorderInsets.left) : null), innerRadius(computeBorderRadii.getTopLeft().getVertical(), computeBorderInsets != null ? Float.valueOf(computeBorderInsets.top) : null), innerRadius(computeBorderRadii.getTopRight().getHorizontal(), computeBorderInsets != null ? Float.valueOf(computeBorderInsets.right) : null), innerRadius(computeBorderRadii.getTopRight().getVertical(), computeBorderInsets != null ? Float.valueOf(computeBorderInsets.top) : null), innerRadius(computeBorderRadii.getBottomRight().getHorizontal(), computeBorderInsets != null ? Float.valueOf(computeBorderInsets.right) : null), innerRadius(computeBorderRadii.getBottomRight().getVertical(), computeBorderInsets != null ? Float.valueOf(computeBorderInsets.bottom) : null), innerRadius(computeBorderRadii.getBottomLeft().getHorizontal(), computeBorderInsets != null ? Float.valueOf(computeBorderInsets.left) : null), innerRadius(computeBorderRadii.getBottomLeft().getVertical(), computeBorderInsets != null ? Float.valueOf(computeBorderInsets.bottom) : null)};
        } else {
            fArr = null;
        }
        PixelUtil pixelUtil = PixelUtil.INSTANCE;
        float dpToPx = pixelUtil.dpToPx(this.offsetX);
        float dpToPx2 = pixelUtil.dpToPx(this.offsetY);
        float dpToPx3 = pixelUtil.dpToPx(this.spread);
        RectF rectF2 = new RectF(rectF);
        rectF2.inset(dpToPx3, dpToPx3);
        rectF2.offset(dpToPx, dpToPx2);
        float sigmaToRadius$ReactAndroid_release = FilterHelper.INSTANCE.sigmaToRadius$ReactAndroid_release(this.blurRadius);
        RectF rectF3 = new RectF(rectF2);
        float f = -sigmaToRadius$ReactAndroid_release;
        rectF3.inset(f, f);
        if (dpToPx3 < 0.0f) {
            rectF3.inset(dpToPx3, dpToPx3);
        }
        RectF rectF4 = new RectF(rectF3);
        rectF4.offset(-dpToPx, -dpToPx2);
        rectF3.union(rectF4);
        int save = canvas.save();
        if (fArr != null) {
            Path path = new Path();
            path.addRoundRect(rectF, fArr, Path.Direction.CW);
            canvas2.clipPath(path);
            ArrayList arrayList = new ArrayList(fArr.length);
            for (float adjustRadiusForSpread : fArr) {
                arrayList.add(Float.valueOf(BoxShadowBorderRadiusKt.adjustRadiusForSpread(adjustRadiusForSpread, -dpToPx3)));
            }
            canvas.drawDoubleRoundRect(rectF3, InsetBoxShadowDrawableKt.ZERO_RADII, rectF2, CollectionsKt.toFloatArray(arrayList), this.shadowPaint);
        } else {
            canvas2.clipRect(rectF);
            canvas.drawDoubleRoundRect(rectF3, InsetBoxShadowDrawableKt.ZERO_RADII, rectF2, InsetBoxShadowDrawableKt.ZERO_RADII, this.shadowPaint);
        }
        canvas2.restoreToCount(save);
    }

    private final ComputedBorderRadius computeBorderRadii() {
        ComputedBorderRadius computedBorderRadius;
        BorderRadiusStyle borderRadiusStyle = this.borderRadius;
        if (borderRadiusStyle != null) {
            int layoutDirection = getLayoutDirection();
            Context context2 = this.context;
            PixelUtil pixelUtil = PixelUtil.INSTANCE;
            computedBorderRadius = borderRadiusStyle.resolve(layoutDirection, context2, pixelUtil.pxToDp((float) getBounds().width()), pixelUtil.pxToDp((float) getBounds().height()));
        } else {
            computedBorderRadius = null;
        }
        if (computedBorderRadius == null || !computedBorderRadius.hasRoundedBorders()) {
            return null;
        }
        PixelUtil pixelUtil2 = PixelUtil.INSTANCE;
        return new ComputedBorderRadius(new CornerRadii(pixelUtil2.dpToPx(computedBorderRadius.getTopLeft().getHorizontal()), pixelUtil2.dpToPx(computedBorderRadius.getTopLeft().getVertical())), new CornerRadii(pixelUtil2.dpToPx(computedBorderRadius.getTopRight().getHorizontal()), pixelUtil2.dpToPx(computedBorderRadius.getTopRight().getVertical())), new CornerRadii(pixelUtil2.dpToPx(computedBorderRadius.getBottomLeft().getHorizontal()), pixelUtil2.dpToPx(computedBorderRadius.getBottomLeft().getVertical())), new CornerRadii(pixelUtil2.dpToPx(computedBorderRadius.getBottomRight().getHorizontal()), pixelUtil2.dpToPx(computedBorderRadius.getBottomRight().getVertical())));
    }

    private final RectF computeBorderInsets() {
        RectF resolve;
        BorderInsets borderInsets2 = this.borderInsets;
        if (borderInsets2 == null || (resolve = borderInsets2.resolve(getLayoutDirection(), this.context)) == null) {
            return null;
        }
        PixelUtil pixelUtil = PixelUtil.INSTANCE;
        return new RectF(pixelUtil.dpToPx(resolve.left), pixelUtil.dpToPx(resolve.top), pixelUtil.dpToPx(resolve.right), pixelUtil.dpToPx(resolve.bottom));
    }

    private final float innerRadius(float f, Float f2) {
        return RangesKt.coerceAtLeast(f - (f2 != null ? f2.floatValue() : 0.0f), 0.0f);
    }
}
