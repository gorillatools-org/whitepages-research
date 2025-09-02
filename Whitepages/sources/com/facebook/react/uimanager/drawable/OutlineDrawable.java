package com.facebook.react.uimanager.drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.style.BorderRadiusStyle;
import com.facebook.react.uimanager.style.ComputedBorderRadius;
import com.facebook.react.uimanager.style.CornerRadii;
import com.facebook.react.uimanager.style.OutlineStyle;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

public final class OutlineDrawable extends Drawable {
    private BorderRadiusStyle borderRadius;
    private ComputedBorderRadius computedBorderRadius;
    private final Context context;
    private final float gapBetweenPaths;
    private int outlineColor;
    private float outlineOffset;
    private final Paint outlinePaint;
    private OutlineStyle outlineStyle;
    private float outlineWidth;
    private final Path pathForOutline;
    private RectF tempRectForOutline;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                com.facebook.react.uimanager.style.OutlineStyle[] r0 = com.facebook.react.uimanager.style.OutlineStyle.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.react.uimanager.style.OutlineStyle r1 = com.facebook.react.uimanager.style.OutlineStyle.SOLID     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.react.uimanager.style.OutlineStyle r1 = com.facebook.react.uimanager.style.OutlineStyle.DASHED     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.facebook.react.uimanager.style.OutlineStyle r1 = com.facebook.react.uimanager.style.OutlineStyle.DOTTED     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.drawable.OutlineDrawable.WhenMappings.<clinit>():void");
        }
    }

    private final float calculateRadius(float f, float f2) {
        if (f == 0.0f) {
            return 0.0f;
        }
        return f + (f2 * 0.5f);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ OutlineDrawable(Context context2, BorderRadiusStyle borderRadiusStyle, int i, float f, OutlineStyle outlineStyle2, float f2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context2, (i2 & 2) != 0 ? null : borderRadiusStyle, i, f, outlineStyle2, f2);
    }

    public final BorderRadiusStyle getBorderRadius() {
        return this.borderRadius;
    }

    public final void setBorderRadius(BorderRadiusStyle borderRadiusStyle) {
        this.borderRadius = borderRadiusStyle;
    }

    public OutlineDrawable(Context context2, BorderRadiusStyle borderRadiusStyle, int i, float f, OutlineStyle outlineStyle2, float f2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(outlineStyle2, ViewProps.OUTLINE_STYLE);
        this.context = context2;
        this.borderRadius = borderRadiusStyle;
        this.gapBetweenPaths = 0.8f;
        this.outlineOffset = f;
        this.outlineStyle = outlineStyle2;
        this.outlineColor = i;
        this.outlineWidth = f2;
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(i);
        paint.setStrokeWidth(f2);
        paint.setPathEffect(getPathEffect(outlineStyle2, f2));
        this.outlinePaint = paint;
        this.tempRectForOutline = new RectF();
        this.pathForOutline = new Path();
    }

    public final float getOutlineOffset() {
        return this.outlineOffset;
    }

    public final void setOutlineOffset(float f) {
        if (f != this.outlineOffset) {
            this.outlineOffset = f;
            invalidateSelf();
        }
    }

    public final OutlineStyle getOutlineStyle() {
        return this.outlineStyle;
    }

    public final void setOutlineStyle(OutlineStyle outlineStyle2) {
        Intrinsics.checkNotNullParameter(outlineStyle2, "value");
        if (outlineStyle2 != this.outlineStyle) {
            this.outlineStyle = outlineStyle2;
            this.outlinePaint.setPathEffect(getPathEffect(outlineStyle2, this.outlineWidth));
            invalidateSelf();
        }
    }

    public final int getOutlineColor() {
        return this.outlineColor;
    }

    public final void setOutlineColor(int i) {
        if (i != this.outlineColor) {
            this.outlineColor = i;
            this.outlinePaint.setColor(i);
            invalidateSelf();
        }
    }

    public final float getOutlineWidth() {
        return this.outlineWidth;
    }

    public final void setOutlineWidth(float f) {
        if (f != this.outlineWidth) {
            this.outlineWidth = f;
            this.outlinePaint.setStrokeWidth(f);
            this.outlinePaint.setPathEffect(getPathEffect(this.outlineStyle, f));
            invalidateSelf();
        }
    }

    public void setAlpha(int i) {
        this.outlinePaint.setAlpha(MathKt.roundToInt((((float) i) / 255.0f) * (((float) Color.alpha(this.outlineColor)) / 255.0f) * 255.0f));
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.outlinePaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public int getOpacity() {
        int alpha = this.outlinePaint.getAlpha();
        if (alpha == 255) {
            return -1;
        }
        return (1 > alpha || alpha >= 255) ? -2 : -3;
    }

    public void draw(Canvas canvas) {
        ComputedBorderRadius computedBorderRadius2;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if (this.outlineWidth != 0.0f) {
            this.pathForOutline.reset();
            BorderRadiusStyle borderRadiusStyle = this.borderRadius;
            if (borderRadiusStyle != null) {
                int layoutDirection = getLayoutDirection();
                Context context2 = this.context;
                PixelUtil pixelUtil = PixelUtil.INSTANCE;
                computedBorderRadius2 = borderRadiusStyle.resolve(layoutDirection, context2, pixelUtil.pxToDp(getBounds().width()), pixelUtil.pxToDp(getBounds().height()));
            } else {
                computedBorderRadius2 = null;
            }
            this.computedBorderRadius = computedBorderRadius2;
            updateOutlineRect();
            ComputedBorderRadius computedBorderRadius3 = this.computedBorderRadius;
            if (computedBorderRadius3 == null || computedBorderRadius3 == null || !computedBorderRadius3.hasRoundedBorders()) {
                drawRectangularOutline(canvas);
            } else {
                drawRoundedOutline(canvas);
            }
        }
    }

    private final void updateOutlineRect() {
        this.tempRectForOutline.set(getBounds());
        RectF rectF = this.tempRectForOutline;
        float f = rectF.top;
        float f2 = this.outlineWidth;
        float f3 = this.outlineOffset;
        float f4 = this.gapBetweenPaths;
        rectF.top = f - (((f2 * 0.5f) + f3) - f4);
        rectF.bottom += ((f2 * 0.5f) + f3) - f4;
        rectF.left -= ((f2 * 0.5f) + f3) - f4;
        rectF.right += ((f2 * 0.5f) + f3) - f4;
    }

    private final PathEffect getPathEffect(OutlineStyle outlineStyle2, float f) {
        int i = WhenMappings.$EnumSwitchMapping$0[outlineStyle2.ordinal()];
        if (i == 1) {
            return null;
        }
        if (i == 2) {
            float f2 = f * ((float) 3);
            return new DashPathEffect(new float[]{f2, f2, f2, f2}, 0.0f);
        } else if (i == 3) {
            return new DashPathEffect(new float[]{f, f, f, f}, 0.0f);
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }

    private final void drawRectangularOutline(Canvas canvas) {
        this.pathForOutline.addRect(this.tempRectForOutline, Path.Direction.CW);
        canvas.drawPath(this.pathForOutline, this.outlinePaint);
    }

    private final void drawRoundedOutline(Canvas canvas) {
        CornerRadii cornerRadii;
        CornerRadii cornerRadii2;
        CornerRadii cornerRadii3;
        CornerRadii cornerRadii4;
        CornerRadii bottomRight;
        CornerRadii bottomLeft;
        CornerRadii topRight;
        CornerRadii topLeft;
        ComputedBorderRadius computedBorderRadius2 = this.computedBorderRadius;
        if (computedBorderRadius2 == null || (topLeft = computedBorderRadius2.getTopLeft()) == null || (cornerRadii = topLeft.toPixelFromDIP()) == null) {
            cornerRadii = new CornerRadii(0.0f, 0.0f);
        }
        ComputedBorderRadius computedBorderRadius3 = this.computedBorderRadius;
        if (computedBorderRadius3 == null || (topRight = computedBorderRadius3.getTopRight()) == null || (cornerRadii2 = topRight.toPixelFromDIP()) == null) {
            cornerRadii2 = new CornerRadii(0.0f, 0.0f);
        }
        ComputedBorderRadius computedBorderRadius4 = this.computedBorderRadius;
        if (computedBorderRadius4 == null || (bottomLeft = computedBorderRadius4.getBottomLeft()) == null || (cornerRadii3 = bottomLeft.toPixelFromDIP()) == null) {
            cornerRadii3 = new CornerRadii(0.0f, 0.0f);
        }
        ComputedBorderRadius computedBorderRadius5 = this.computedBorderRadius;
        if (computedBorderRadius5 == null || (bottomRight = computedBorderRadius5.getBottomRight()) == null || (cornerRadii4 = bottomRight.toPixelFromDIP()) == null) {
            cornerRadii4 = new CornerRadii(0.0f, 0.0f);
        }
        this.pathForOutline.addRoundRect(this.tempRectForOutline, new float[]{calculateRadius(cornerRadii.getHorizontal(), this.outlineWidth), calculateRadius(cornerRadii.getVertical(), this.outlineWidth), calculateRadius(cornerRadii2.getHorizontal(), this.outlineWidth), calculateRadius(cornerRadii2.getVertical(), this.outlineWidth), calculateRadius(cornerRadii4.getHorizontal(), this.outlineWidth), calculateRadius(cornerRadii4.getVertical(), this.outlineWidth), calculateRadius(cornerRadii3.getHorizontal(), this.outlineWidth), calculateRadius(cornerRadii3.getVertical(), this.outlineWidth)}, Path.Direction.CW);
        canvas.drawPath(this.pathForOutline, this.outlinePaint);
    }
}
