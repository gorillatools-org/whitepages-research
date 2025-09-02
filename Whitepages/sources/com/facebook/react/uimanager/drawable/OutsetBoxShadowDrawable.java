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
import com.facebook.react.uimanager.style.BorderRadiusStyle;
import com.facebook.react.uimanager.style.ComputedBorderRadius;
import com.facebook.react.uimanager.style.CornerRadii;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

public final class OutsetBoxShadowDrawable extends Drawable {
    private final float blurRadius;
    private BorderRadiusStyle borderRadius;
    private final Context context;
    private final float offsetX;
    private final float offsetY;
    private final int shadowColor;
    private final Paint shadowPaint;
    private final float spread;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ OutsetBoxShadowDrawable(Context context2, int i, float f, float f2, float f3, float f4, BorderRadiusStyle borderRadiusStyle, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context2, i, f, f2, f3, f4, (i2 & 64) != 0 ? null : borderRadiusStyle);
    }

    public final BorderRadiusStyle getBorderRadius() {
        return this.borderRadius;
    }

    public final void setBorderRadius(BorderRadiusStyle borderRadiusStyle) {
        this.borderRadius = borderRadiusStyle;
    }

    public OutsetBoxShadowDrawable(Context context2, int i, float f, float f2, float f3, float f4, BorderRadiusStyle borderRadiusStyle) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
        this.shadowColor = i;
        this.offsetX = f;
        this.offsetY = f2;
        this.blurRadius = f3;
        this.spread = f4;
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
        ComputedBorderRadius computedBorderRadius;
        ComputedBorderRadius resolve;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        PixelUtil pixelUtil = PixelUtil.INSTANCE;
        float pxToDp = pixelUtil.pxToDp((float) getBounds().width());
        float pxToDp2 = pixelUtil.pxToDp((float) getBounds().height());
        BorderRadiusStyle borderRadiusStyle = this.borderRadius;
        if (borderRadiusStyle == null || (resolve = borderRadiusStyle.resolve(getLayoutDirection(), this.context, pxToDp, pxToDp2)) == null) {
            computedBorderRadius = null;
        } else {
            computedBorderRadius = new ComputedBorderRadius(new CornerRadii(pixelUtil.dpToPx(resolve.getTopLeft().getHorizontal()), pixelUtil.dpToPx(resolve.getTopLeft().getVertical())), new CornerRadii(pixelUtil.dpToPx(resolve.getTopRight().getHorizontal()), pixelUtil.dpToPx(resolve.getTopRight().getVertical())), new CornerRadii(pixelUtil.dpToPx(resolve.getBottomLeft().getHorizontal()), pixelUtil.dpToPx(resolve.getBottomLeft().getVertical())), new CornerRadii(pixelUtil.dpToPx(resolve.getBottomRight().getHorizontal()), pixelUtil.dpToPx(resolve.getBottomRight().getVertical())));
        }
        float dpToPx = pixelUtil.dpToPx(this.spread);
        RectF rectF = new RectF(getBounds());
        float f = -dpToPx;
        rectF.inset(f, f);
        rectF.offset(pixelUtil.dpToPx(this.offsetX), pixelUtil.dpToPx(this.offsetY));
        int save = canvas.save();
        if (computedBorderRadius == null || !computedBorderRadius.hasRoundedBorders()) {
            drawShadowRect(canvas, rectF);
        } else {
            drawShadowRoundRect(canvas, rectF, dpToPx, computedBorderRadius);
        }
        canvas.restoreToCount(save);
    }

    private final void drawShadowRoundRect(Canvas canvas, RectF rectF, float f, ComputedBorderRadius computedBorderRadius) {
        Canvas canvas2 = canvas;
        float f2 = f;
        RectF rectF2 = new RectF(getBounds());
        rectF2.inset(0.4f, 0.4f);
        Path path = new Path();
        float[] fArr = {computedBorderRadius.getTopLeft().getHorizontal(), computedBorderRadius.getTopLeft().getVertical(), computedBorderRadius.getTopRight().getHorizontal(), computedBorderRadius.getTopRight().getVertical(), computedBorderRadius.getBottomRight().getHorizontal(), computedBorderRadius.getBottomRight().getVertical(), computedBorderRadius.getBottomLeft().getHorizontal(), computedBorderRadius.getBottomLeft().getVertical()};
        Path.Direction direction = Path.Direction.CW;
        path.addRoundRect(rectF2, fArr, direction);
        canvas2.clipOutPath(path);
        Path path2 = new Path();
        path2.addRoundRect(rectF, new float[]{BoxShadowBorderRadiusKt.adjustRadiusForSpread(computedBorderRadius.getTopLeft().getHorizontal(), f2), BoxShadowBorderRadiusKt.adjustRadiusForSpread(computedBorderRadius.getTopLeft().getVertical(), f2), BoxShadowBorderRadiusKt.adjustRadiusForSpread(computedBorderRadius.getTopRight().getHorizontal(), f2), BoxShadowBorderRadiusKt.adjustRadiusForSpread(computedBorderRadius.getTopRight().getVertical(), f2), BoxShadowBorderRadiusKt.adjustRadiusForSpread(computedBorderRadius.getBottomRight().getHorizontal(), f2), BoxShadowBorderRadiusKt.adjustRadiusForSpread(computedBorderRadius.getBottomRight().getVertical(), f2), BoxShadowBorderRadiusKt.adjustRadiusForSpread(computedBorderRadius.getBottomLeft().getHorizontal(), f2), BoxShadowBorderRadiusKt.adjustRadiusForSpread(computedBorderRadius.getBottomLeft().getVertical(), f2)}, direction);
        canvas2.drawPath(path2, this.shadowPaint);
    }

    private final void drawShadowRect(Canvas canvas, RectF rectF) {
        canvas.clipOutRect(getBounds());
        canvas.drawRect(rectF, this.shadowPaint);
    }
}
