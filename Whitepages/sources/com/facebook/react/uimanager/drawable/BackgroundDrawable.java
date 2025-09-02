package com.facebook.react.uimanager.drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.style.BackgroundImageLayer;
import com.facebook.react.uimanager.style.BorderInsets;
import com.facebook.react.uimanager.style.BorderRadiusStyle;
import com.facebook.react.uimanager.style.ComputedBorderRadius;
import com.facebook.react.uimanager.style.CornerRadii;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

public final class BackgroundDrawable extends Drawable {
    private int backgroundColor;
    private List<BackgroundImageLayer> backgroundImageLayers;
    private final Paint backgroundPaint;
    private RectF backgroundRect;
    private Path backgroundRenderPath;
    private BorderInsets borderInsets;
    private BorderRadiusStyle borderRadius;
    private RectF computedBorderInsets;
    private ComputedBorderRadius computedBorderRadius;
    private final Context context;
    private boolean needUpdatePath;
    private final float pathAdjustment;

    public void setColorFilter(ColorFilter colorFilter) {
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BackgroundDrawable(Context context2, BorderRadiusStyle borderRadiusStyle, BorderInsets borderInsets2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context2, (i & 2) != 0 ? null : borderRadiusStyle, (i & 4) != 0 ? null : borderInsets2);
    }

    public final BorderRadiusStyle getBorderRadius() {
        return this.borderRadius;
    }

    public final void setBorderRadius(BorderRadiusStyle borderRadiusStyle) {
        this.borderRadius = borderRadiusStyle;
    }

    public final BorderInsets getBorderInsets() {
        return this.borderInsets;
    }

    public final void setBorderInsets(BorderInsets borderInsets2) {
        this.borderInsets = borderInsets2;
    }

    public BackgroundDrawable(Context context2, BorderRadiusStyle borderRadiusStyle, BorderInsets borderInsets2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
        this.borderRadius = borderRadiusStyle;
        this.borderInsets = borderInsets2;
        this.pathAdjustment = 0.8f;
        this.needUpdatePath = true;
        this.backgroundRect = new RectF();
        Paint paint = new Paint(1);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(this.backgroundColor);
        this.backgroundPaint = paint;
    }

    public final int getBackgroundColor() {
        return this.backgroundColor;
    }

    public final void setBackgroundColor(int i) {
        if (this.backgroundColor != i) {
            this.backgroundColor = i;
            this.backgroundPaint.setColor(i);
            invalidateSelf();
        }
    }

    public final List<BackgroundImageLayer> getBackgroundImageLayers() {
        return this.backgroundImageLayers;
    }

    public final void setBackgroundImageLayers(List<BackgroundImageLayer> list) {
        if (!Intrinsics.areEqual((Object) this.backgroundImageLayers, (Object) list)) {
            this.backgroundImageLayers = list;
            invalidateSelf();
        }
    }

    public void invalidateSelf() {
        this.needUpdatePath = true;
        super.invalidateSelf();
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "bounds");
        super.onBoundsChange(rect);
        this.needUpdatePath = true;
    }

    public void setAlpha(int i) {
        this.backgroundPaint.setAlpha(MathKt.roundToInt((((float) i) / 255.0f) * (((float) Color.alpha(this.backgroundColor)) / 255.0f) * 255.0f));
        invalidateSelf();
    }

    public int getOpacity() {
        int alpha = this.backgroundPaint.getAlpha();
        if (alpha == 255) {
            return -1;
        }
        return (1 > alpha || alpha >= 255) ? -2 : -3;
    }

    public void draw(Canvas canvas) {
        BorderRadiusStyle borderRadiusStyle;
        float f;
        CornerRadii topLeft;
        CornerRadii topLeft2;
        BorderRadiusStyle borderRadiusStyle2;
        float f2;
        float f3;
        CornerRadii topLeft3;
        CornerRadii topLeft4;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        updatePath();
        canvas.save();
        float f4 = 0.0f;
        if (this.backgroundPaint.getAlpha() != 0) {
            ComputedBorderRadius computedBorderRadius2 = this.computedBorderRadius;
            if (computedBorderRadius2 == null || !computedBorderRadius2.isUniform() || (borderRadiusStyle2 = this.borderRadius) == null || !borderRadiusStyle2.hasRoundedBorders()) {
                BorderRadiusStyle borderRadiusStyle3 = this.borderRadius;
                if (borderRadiusStyle3 == null || !borderRadiusStyle3.hasRoundedBorders()) {
                    canvas.drawRect(this.backgroundRect, this.backgroundPaint);
                } else {
                    Path path = this.backgroundRenderPath;
                    if (path != null) {
                        canvas.drawPath(path, this.backgroundPaint);
                    } else {
                        throw new IllegalStateException("Required value was null.");
                    }
                }
            } else {
                RectF rectF = this.backgroundRect;
                ComputedBorderRadius computedBorderRadius3 = this.computedBorderRadius;
                if (computedBorderRadius3 == null || (topLeft4 = computedBorderRadius3.getTopLeft()) == null) {
                    f2 = 0.0f;
                } else {
                    f2 = PixelUtil.INSTANCE.dpToPx(topLeft4.getHorizontal());
                }
                ComputedBorderRadius computedBorderRadius4 = this.computedBorderRadius;
                if (computedBorderRadius4 == null || (topLeft3 = computedBorderRadius4.getTopLeft()) == null) {
                    f3 = 0.0f;
                } else {
                    f3 = PixelUtil.INSTANCE.dpToPx(topLeft3.getVertical());
                }
                canvas.drawRoundRect(rectF, f2, f3, this.backgroundPaint);
            }
        }
        List<BackgroundImageLayer> list = this.backgroundImageLayers;
        if (!(list == null || list == null || !(!list.isEmpty()))) {
            this.backgroundPaint.setShader(getBackgroundImageShader());
            ComputedBorderRadius computedBorderRadius5 = this.computedBorderRadius;
            if (computedBorderRadius5 == null || !computedBorderRadius5.isUniform() || (borderRadiusStyle = this.borderRadius) == null || !borderRadiusStyle.hasRoundedBorders()) {
                BorderRadiusStyle borderRadiusStyle4 = this.borderRadius;
                if (borderRadiusStyle4 == null || !borderRadiusStyle4.hasRoundedBorders()) {
                    canvas.drawRect(this.backgroundRect, this.backgroundPaint);
                } else {
                    Path path2 = this.backgroundRenderPath;
                    if (path2 != null) {
                        canvas.drawPath(path2, this.backgroundPaint);
                    } else {
                        throw new IllegalStateException("Required value was null.");
                    }
                }
            } else {
                RectF rectF2 = this.backgroundRect;
                ComputedBorderRadius computedBorderRadius6 = this.computedBorderRadius;
                if (computedBorderRadius6 == null || (topLeft2 = computedBorderRadius6.getTopLeft()) == null) {
                    f = 0.0f;
                } else {
                    f = PixelUtil.INSTANCE.dpToPx(topLeft2.getHorizontal());
                }
                ComputedBorderRadius computedBorderRadius7 = this.computedBorderRadius;
                if (!(computedBorderRadius7 == null || (topLeft = computedBorderRadius7.getTopLeft()) == null)) {
                    f4 = PixelUtil.INSTANCE.dpToPx(topLeft.getVertical());
                }
                canvas.drawRoundRect(rectF2, f, f4, this.backgroundPaint);
            }
            this.backgroundPaint.setShader((Shader) null);
        }
        canvas.restore();
    }

    private final RectF computeBorderInsets() {
        BorderInsets borderInsets2 = this.borderInsets;
        RectF resolve = borderInsets2 != null ? borderInsets2.resolve(getLayoutDirection(), this.context) : null;
        float f = 0.0f;
        float dpToPx = resolve != null ? PixelUtil.INSTANCE.dpToPx(resolve.left) : 0.0f;
        float dpToPx2 = resolve != null ? PixelUtil.INSTANCE.dpToPx(resolve.top) : 0.0f;
        float dpToPx3 = resolve != null ? PixelUtil.INSTANCE.dpToPx(resolve.right) : 0.0f;
        if (resolve != null) {
            f = PixelUtil.INSTANCE.dpToPx(resolve.bottom);
        }
        return new RectF(dpToPx, dpToPx2, dpToPx3, f);
    }

    private final Shader getBackgroundImageShader() {
        List<BackgroundImageLayer> list = this.backgroundImageLayers;
        ComposeShader composeShader = null;
        if (list != null) {
            for (BackgroundImageLayer shader : list) {
                Rect bounds = getBounds();
                Intrinsics.checkNotNullExpressionValue(bounds, "getBounds(...)");
                Shader shader2 = shader.getShader(bounds);
                if (shader2 != null) {
                    if (composeShader == null) {
                        composeShader = shader2;
                    } else {
                        composeShader = new ComposeShader(shader2, composeShader, PorterDuff.Mode.SRC_OVER);
                    }
                }
            }
        }
        return composeShader;
    }

    /* JADX WARNING: Removed duplicated region for block: B:110:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00e0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void updatePath() {
        /*
            r13 = this;
            r0 = 1
            r1 = 0
            boolean r2 = r13.needUpdatePath
            if (r2 != 0) goto L_0x0007
            return
        L_0x0007:
            r13.needUpdatePath = r1
            android.graphics.RectF r2 = r13.backgroundRect
            android.graphics.Rect r3 = r13.getBounds()
            r2.set(r3)
            android.graphics.RectF r2 = r13.computeBorderInsets()
            r13.computedBorderInsets = r2
            com.facebook.react.uimanager.style.BorderRadiusStyle r2 = r13.borderRadius
            r3 = 0
            if (r2 == 0) goto L_0x0042
            int r4 = r13.getLayoutDirection()
            android.content.Context r5 = r13.context
            com.facebook.react.uimanager.PixelUtil r6 = com.facebook.react.uimanager.PixelUtil.INSTANCE
            android.graphics.Rect r7 = r13.getBounds()
            int r7 = r7.width()
            float r7 = r6.pxToDp((int) r7)
            android.graphics.Rect r8 = r13.getBounds()
            int r8 = r8.height()
            float r6 = r6.pxToDp((int) r8)
            com.facebook.react.uimanager.style.ComputedBorderRadius r2 = r2.resolve(r4, r5, r7, r6)
            goto L_0x0043
        L_0x0042:
            r2 = r3
        L_0x0043:
            r13.computedBorderRadius = r2
            android.graphics.RectF r2 = r13.computedBorderInsets
            if (r2 == 0) goto L_0x0050
            float r2 = r2.left
            java.lang.Float r2 = java.lang.Float.valueOf(r2)
            goto L_0x0051
        L_0x0050:
            r2 = r3
        L_0x0051:
            r4 = 0
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Float) r2, (float) r4)
            if (r2 == 0) goto L_0x008f
            android.graphics.RectF r2 = r13.computedBorderInsets
            if (r2 == 0) goto L_0x0063
            float r2 = r2.top
            java.lang.Float r2 = java.lang.Float.valueOf(r2)
            goto L_0x0064
        L_0x0063:
            r2 = r3
        L_0x0064:
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Float) r2, (float) r4)
            if (r2 == 0) goto L_0x008f
            android.graphics.RectF r2 = r13.computedBorderInsets
            if (r2 == 0) goto L_0x0075
            float r2 = r2.right
            java.lang.Float r2 = java.lang.Float.valueOf(r2)
            goto L_0x0076
        L_0x0075:
            r2 = r3
        L_0x0076:
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Float) r2, (float) r4)
            if (r2 == 0) goto L_0x008f
            android.graphics.RectF r2 = r13.computedBorderInsets
            if (r2 == 0) goto L_0x0086
            float r2 = r2.bottom
            java.lang.Float r3 = java.lang.Float.valueOf(r2)
        L_0x0086:
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Float) r3, (float) r4)
            if (r2 != 0) goto L_0x008d
            goto L_0x008f
        L_0x008d:
            r2 = r1
            goto L_0x0090
        L_0x008f:
            r2 = r0
        L_0x0090:
            com.facebook.react.uimanager.style.ComputedBorderRadius r3 = r13.computedBorderRadius
            if (r3 == 0) goto L_0x00b2
            boolean r3 = r3.hasRoundedBorders()
            if (r3 != r0) goto L_0x00b2
            com.facebook.react.uimanager.style.ComputedBorderRadius r3 = r13.computedBorderRadius
            if (r3 == 0) goto L_0x00b2
            boolean r3 = r3.isUniform()
            if (r3 != 0) goto L_0x00b2
            android.graphics.Path r3 = r13.backgroundRenderPath
            if (r3 != 0) goto L_0x00ad
            android.graphics.Path r3 = new android.graphics.Path
            r3.<init>()
        L_0x00ad:
            r13.backgroundRenderPath = r3
            r3.reset()
        L_0x00b2:
            if (r2 == 0) goto L_0x00d6
            com.facebook.react.uimanager.style.BorderRadiusStyle r2 = r13.borderRadius
            if (r2 == 0) goto L_0x00d6
            boolean r2 = r2.hasRoundedBorders()
            if (r2 != r0) goto L_0x00d6
            android.graphics.RectF r2 = r13.backgroundRect
            float r3 = r2.left
            float r5 = r13.pathAdjustment
            float r3 = r3 + r5
            r2.left = r3
            float r3 = r2.top
            float r3 = r3 + r5
            r2.top = r3
            float r3 = r2.right
            float r3 = r3 - r5
            r2.right = r3
            float r3 = r2.bottom
            float r3 = r3 - r5
            r2.bottom = r3
        L_0x00d6:
            com.facebook.react.uimanager.style.BorderRadiusStyle r2 = r13.borderRadius
            if (r2 == 0) goto L_0x01bf
            boolean r2 = r2.hasRoundedBorders()
            if (r2 != r0) goto L_0x01bf
            com.facebook.react.uimanager.style.ComputedBorderRadius r2 = r13.computedBorderRadius
            if (r2 == 0) goto L_0x00ec
            boolean r2 = r2.isUniform()
            if (r2 != r0) goto L_0x00ec
            goto L_0x01bf
        L_0x00ec:
            android.graphics.Path r2 = r13.backgroundRenderPath
            if (r2 == 0) goto L_0x01bf
            android.graphics.RectF r3 = r13.backgroundRect
            com.facebook.react.uimanager.style.ComputedBorderRadius r5 = r13.computedBorderRadius
            if (r5 == 0) goto L_0x0107
            com.facebook.react.uimanager.style.CornerRadii r5 = r5.getTopLeft()
            if (r5 == 0) goto L_0x0107
            float r5 = r5.getHorizontal()
            com.facebook.react.uimanager.PixelUtil r6 = com.facebook.react.uimanager.PixelUtil.INSTANCE
            float r5 = r6.dpToPx((float) r5)
            goto L_0x0108
        L_0x0107:
            r5 = r4
        L_0x0108:
            com.facebook.react.uimanager.style.ComputedBorderRadius r6 = r13.computedBorderRadius
            if (r6 == 0) goto L_0x011d
            com.facebook.react.uimanager.style.CornerRadii r6 = r6.getTopLeft()
            if (r6 == 0) goto L_0x011d
            float r6 = r6.getVertical()
            com.facebook.react.uimanager.PixelUtil r7 = com.facebook.react.uimanager.PixelUtil.INSTANCE
            float r6 = r7.dpToPx((float) r6)
            goto L_0x011e
        L_0x011d:
            r6 = r4
        L_0x011e:
            com.facebook.react.uimanager.style.ComputedBorderRadius r7 = r13.computedBorderRadius
            if (r7 == 0) goto L_0x0133
            com.facebook.react.uimanager.style.CornerRadii r7 = r7.getTopRight()
            if (r7 == 0) goto L_0x0133
            float r7 = r7.getHorizontal()
            com.facebook.react.uimanager.PixelUtil r8 = com.facebook.react.uimanager.PixelUtil.INSTANCE
            float r7 = r8.dpToPx((float) r7)
            goto L_0x0134
        L_0x0133:
            r7 = r4
        L_0x0134:
            com.facebook.react.uimanager.style.ComputedBorderRadius r8 = r13.computedBorderRadius
            if (r8 == 0) goto L_0x0149
            com.facebook.react.uimanager.style.CornerRadii r8 = r8.getTopRight()
            if (r8 == 0) goto L_0x0149
            float r8 = r8.getVertical()
            com.facebook.react.uimanager.PixelUtil r9 = com.facebook.react.uimanager.PixelUtil.INSTANCE
            float r8 = r9.dpToPx((float) r8)
            goto L_0x014a
        L_0x0149:
            r8 = r4
        L_0x014a:
            com.facebook.react.uimanager.style.ComputedBorderRadius r9 = r13.computedBorderRadius
            if (r9 == 0) goto L_0x015f
            com.facebook.react.uimanager.style.CornerRadii r9 = r9.getBottomRight()
            if (r9 == 0) goto L_0x015f
            float r9 = r9.getHorizontal()
            com.facebook.react.uimanager.PixelUtil r10 = com.facebook.react.uimanager.PixelUtil.INSTANCE
            float r9 = r10.dpToPx((float) r9)
            goto L_0x0160
        L_0x015f:
            r9 = r4
        L_0x0160:
            com.facebook.react.uimanager.style.ComputedBorderRadius r10 = r13.computedBorderRadius
            if (r10 == 0) goto L_0x0175
            com.facebook.react.uimanager.style.CornerRadii r10 = r10.getBottomRight()
            if (r10 == 0) goto L_0x0175
            float r10 = r10.getVertical()
            com.facebook.react.uimanager.PixelUtil r11 = com.facebook.react.uimanager.PixelUtil.INSTANCE
            float r10 = r11.dpToPx((float) r10)
            goto L_0x0176
        L_0x0175:
            r10 = r4
        L_0x0176:
            com.facebook.react.uimanager.style.ComputedBorderRadius r11 = r13.computedBorderRadius
            if (r11 == 0) goto L_0x018b
            com.facebook.react.uimanager.style.CornerRadii r11 = r11.getBottomLeft()
            if (r11 == 0) goto L_0x018b
            float r11 = r11.getHorizontal()
            com.facebook.react.uimanager.PixelUtil r12 = com.facebook.react.uimanager.PixelUtil.INSTANCE
            float r11 = r12.dpToPx((float) r11)
            goto L_0x018c
        L_0x018b:
            r11 = r4
        L_0x018c:
            com.facebook.react.uimanager.style.ComputedBorderRadius r12 = r13.computedBorderRadius
            if (r12 == 0) goto L_0x01a0
            com.facebook.react.uimanager.style.CornerRadii r12 = r12.getBottomLeft()
            if (r12 == 0) goto L_0x01a0
            float r4 = r12.getVertical()
            com.facebook.react.uimanager.PixelUtil r12 = com.facebook.react.uimanager.PixelUtil.INSTANCE
            float r4 = r12.dpToPx((float) r4)
        L_0x01a0:
            r12 = 8
            float[] r12 = new float[r12]
            r12[r1] = r5
            r12[r0] = r6
            r0 = 2
            r12[r0] = r7
            r0 = 3
            r12[r0] = r8
            r0 = 4
            r12[r0] = r9
            r0 = 5
            r12[r0] = r10
            r0 = 6
            r12[r0] = r11
            r0 = 7
            r12[r0] = r4
            android.graphics.Path$Direction r0 = android.graphics.Path.Direction.CW
            r2.addRoundRect(r3, r12, r0)
        L_0x01bf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.drawable.BackgroundDrawable.updatePath():void");
    }
}
