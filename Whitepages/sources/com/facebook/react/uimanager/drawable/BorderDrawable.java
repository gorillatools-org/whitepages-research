package com.facebook.react.uimanager.drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.facebook.react.uimanager.FloatUtil;
import com.facebook.react.uimanager.LengthPercentage;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.Spacing;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.style.BorderColors;
import com.facebook.react.uimanager.style.BorderInsets;
import com.facebook.react.uimanager.style.BorderRadiusProp;
import com.facebook.react.uimanager.style.BorderRadiusStyle;
import com.facebook.react.uimanager.style.BorderStyle;
import com.facebook.react.uimanager.style.ColorEdges;
import com.facebook.react.uimanager.style.ComputedBorderRadius;
import com.facebook.react.uimanager.style.CornerRadii;
import com.facebook.react.uimanager.style.LogicalEdge;
import java.util.Locale;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.math.MathKt;
import kotlin.properties.ReadWriteProperty;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;

public final class BorderDrawable extends Drawable {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(BorderDrawable.class, "borderStyle", "getBorderStyle()Lcom/facebook/react/uimanager/style/BorderStyle;", 0))};
    private int borderAlpha = 255;
    private Integer[] borderColors;
    private BorderInsets borderInsets;
    private final Paint borderPaint = new Paint(1);
    private BorderRadiusStyle borderRadius;
    private final ReadWriteProperty borderStyle$delegate;
    private final Spacing borderWidth;
    private Path centerDrawPath;
    private ColorEdges computedBorderColors = new ColorEdges(0, 0, 0, 0, 15, (DefaultConstructorMarker) null);
    private ComputedBorderRadius computedBorderRadius;
    private final Context context;
    private final float gapBetweenPaths = 0.8f;
    private PointF innerBottomLeftCorner;
    private PointF innerBottomRightCorner;
    private Path innerClipPathForBorderRadius;
    private RectF innerClipTempRectForBorderRadius;
    private PointF innerTopLeftCorner;
    private PointF innerTopRightCorner;
    /* access modifiers changed from: private */
    public boolean needUpdatePath = true;
    private Path outerClipPathForBorderRadius;
    private RectF outerClipTempRectForBorderRadius;
    private Path pathForBorder;
    private Path pathForOutline;
    private Path pathForSingleBorder;
    private RectF tempRectForCenterDrawPath;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                com.facebook.react.uimanager.style.BorderStyle[] r0 = com.facebook.react.uimanager.style.BorderStyle.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.react.uimanager.style.BorderStyle r1 = com.facebook.react.uimanager.style.BorderStyle.SOLID     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.react.uimanager.style.BorderStyle r1 = com.facebook.react.uimanager.style.BorderStyle.DASHED     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.facebook.react.uimanager.style.BorderStyle r1 = com.facebook.react.uimanager.style.BorderStyle.DOTTED     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.drawable.BorderDrawable.WhenMappings.<clinit>():void");
        }
    }

    private final int fastBorderCompatibleColorOrZero(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9 = -1;
        int i10 = (i > 0 ? i5 : -1) & (i2 > 0 ? i6 : -1) & (i3 > 0 ? i7 : -1);
        if (i4 > 0) {
            i9 = i8;
        }
        int i11 = i9 & i10;
        if (i <= 0) {
            i5 = 0;
        }
        if (i2 <= 0) {
            i6 = 0;
        }
        int i12 = i5 | i6;
        if (i3 <= 0) {
            i7 = 0;
        }
        int i13 = i12 | i7;
        if (i4 <= 0) {
            i8 = 0;
        }
        if (i11 == (i13 | i8)) {
            return i11;
        }
        return 0;
    }

    private final int multiplyColorAlpha(int i, int i2) {
        if (i2 == 255) {
            return i;
        }
        if (i2 == 0) {
            return i & 16777215;
        }
        return (i & 16777215) | ((((i >>> 24) * ((i2 + (i2 >> 7)) >> 7)) >> 8) << 24);
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public final Spacing getBorderWidth() {
        return this.borderWidth;
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

    public BorderDrawable(Context context2, Spacing spacing, BorderRadiusStyle borderRadiusStyle, BorderInsets borderInsets2, BorderStyle borderStyle) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
        this.borderWidth = spacing;
        this.borderRadius = borderRadiusStyle;
        this.borderInsets = borderInsets2;
        this.borderStyle$delegate = invalidatingAndPathChange(borderStyle);
    }

    private final <T> ReadWriteProperty invalidatingAndPathChange(T t) {
        return new BorderDrawable$invalidatingAndPathChange$1(t, this);
    }

    public final BorderStyle getBorderStyle() {
        return (BorderStyle) this.borderStyle$delegate.getValue(this, $$delegatedProperties[0]);
    }

    public final void setBorderStyle(BorderStyle borderStyle) {
        this.borderStyle$delegate.setValue(this, $$delegatedProperties[0], borderStyle);
    }

    public final Path getInnerClipPathForBorderRadius() {
        return this.innerClipPathForBorderRadius;
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
        this.borderAlpha = i;
        invalidateSelf();
    }

    public int getOpacity() {
        if (ComparisonsKt.maxOf(Color.alpha(multiplyColorAlpha(this.computedBorderColors.getLeft(), this.borderAlpha)), Color.alpha(multiplyColorAlpha(this.computedBorderColors.getTop(), this.borderAlpha)), Color.alpha(multiplyColorAlpha(this.computedBorderColors.getRight(), this.borderAlpha)), Color.alpha(multiplyColorAlpha(this.computedBorderColors.getBottom(), this.borderAlpha))) == 0) {
            return -2;
        }
        return ComparisonsKt.minOf(Color.alpha(multiplyColorAlpha(this.computedBorderColors.getLeft(), this.borderAlpha)), Color.alpha(multiplyColorAlpha(this.computedBorderColors.getTop(), this.borderAlpha)), Color.alpha(multiplyColorAlpha(this.computedBorderColors.getRight(), this.borderAlpha)), Color.alpha(multiplyColorAlpha(this.computedBorderColors.getBottom(), this.borderAlpha))) == 255 ? -1 : -3;
    }

    public void draw(Canvas canvas) {
        ColorEdges colorEdges;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        updatePathEffect();
        Integer[] numArr = this.borderColors;
        if (numArr == null || (colorEdges = BorderColors.m473resolveimpl(numArr, getLayoutDirection(), this.context)) == null) {
            colorEdges = this.computedBorderColors;
        }
        this.computedBorderColors = colorEdges;
        BorderRadiusStyle borderRadiusStyle = this.borderRadius;
        if (borderRadiusStyle == null || !borderRadiusStyle.hasRoundedBorders()) {
            drawRectangularBorders(canvas);
        } else {
            drawRoundedBorders(canvas);
        }
    }

    private final float getInnerBorderRadius(float f, float f2) {
        return RangesKt.coerceAtLeast(f - f2, 0.0f);
    }

    public final void setBorderWidth(int i, float f) {
        Spacing spacing = this.borderWidth;
        if (!FloatUtil.floatsEqual(spacing != null ? Float.valueOf(spacing.getRaw(i)) : null, Float.valueOf(f))) {
            Spacing spacing2 = this.borderWidth;
            if (spacing2 != null) {
                spacing2.set(i, f);
            }
            if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 8) {
                this.needUpdatePath = true;
            }
            invalidateSelf();
        }
    }

    public final void setBorderRadius(BorderRadiusProp borderRadiusProp, LengthPercentage lengthPercentage) {
        Intrinsics.checkNotNullParameter(borderRadiusProp, "property");
        BorderRadiusStyle borderRadiusStyle = this.borderRadius;
        if (!Intrinsics.areEqual((Object) lengthPercentage, (Object) borderRadiusStyle != null ? borderRadiusStyle.get(borderRadiusProp) : null)) {
            BorderRadiusStyle borderRadiusStyle2 = this.borderRadius;
            if (borderRadiusStyle2 != null) {
                borderRadiusStyle2.set(borderRadiusProp, lengthPercentage);
            }
            this.needUpdatePath = true;
            invalidateSelf();
        }
    }

    public final void setBorderStyle(String str) {
        BorderStyle borderStyle;
        if (str == null) {
            borderStyle = null;
        } else {
            String upperCase = str.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            borderStyle = BorderStyle.valueOf(upperCase);
        }
        setBorderStyle(borderStyle);
        this.needUpdatePath = true;
        invalidateSelf();
    }

    public final void setBorderColor(LogicalEdge logicalEdge, Integer num) {
        Intrinsics.checkNotNullParameter(logicalEdge, ViewProps.POSITION);
        Integer[] numArr = this.borderColors;
        if (numArr == null) {
            numArr = BorderColors.m469constructorimpl$default((Integer[]) null, 1, (DefaultConstructorMarker) null);
        }
        this.borderColors = numArr;
        if (numArr != null) {
            numArr[logicalEdge.ordinal()] = num;
        }
        this.needUpdatePath = true;
        invalidateSelf();
    }

    public final int getBorderColor(LogicalEdge logicalEdge) {
        Integer num;
        Intrinsics.checkNotNullParameter(logicalEdge, ViewProps.POSITION);
        Integer[] numArr = this.borderColors;
        if (numArr == null || (num = numArr[logicalEdge.ordinal()]) == null) {
            return -16777216;
        }
        return num.intValue();
    }

    public final void invalidateSelfAndUpdatePath() {
        this.needUpdatePath = true;
        invalidateSelf();
    }

    private final void drawRectangularBorders(Canvas canvas) {
        Canvas canvas2 = canvas;
        RectF computeBorderInsets = computeBorderInsets();
        int roundToInt = MathKt.roundToInt(computeBorderInsets.left);
        int roundToInt2 = MathKt.roundToInt(computeBorderInsets.top);
        int roundToInt3 = MathKt.roundToInt(computeBorderInsets.right);
        int roundToInt4 = MathKt.roundToInt(computeBorderInsets.bottom);
        if (roundToInt > 0 || roundToInt3 > 0 || roundToInt2 > 0 || roundToInt4 > 0) {
            Rect bounds = getBounds();
            Intrinsics.checkNotNullExpressionValue(bounds, "getBounds(...)");
            int i = bounds.left;
            int i2 = bounds.top;
            int left = this.computedBorderColors.getLeft();
            int top = this.computedBorderColors.getTop();
            int right = this.computedBorderColors.getRight();
            int i3 = roundToInt3;
            int i4 = i2;
            int i5 = right;
            int i6 = roundToInt2;
            int i7 = i;
            int fastBorderCompatibleColorOrZero = fastBorderCompatibleColorOrZero(roundToInt, roundToInt2, roundToInt3, roundToInt4, left, top, i5, this.computedBorderColors.getBottom());
            if (fastBorderCompatibleColorOrZero == 0) {
                this.borderPaint.setAntiAlias(false);
                int width = bounds.width();
                int height = bounds.height();
                if (roundToInt > 0) {
                    float f = (float) i7;
                    float f2 = (float) (i7 + roundToInt);
                    int i8 = i4 + height;
                    float f3 = (float) (i8 - roundToInt4);
                    float f4 = (float) i8;
                    int left2 = this.computedBorderColors.getLeft();
                    Canvas canvas3 = canvas;
                    float f5 = f;
                    drawQuadrilateral(canvas3, left2, f5, (float) i4, f2, (float) (i4 + i6), f2, f3, f, f4);
                }
                if (i6 > 0) {
                    float f6 = (float) i4;
                    float f7 = (float) (i4 + i6);
                    int i9 = i7 + width;
                    float f8 = (float) (i9 - i3);
                    float f9 = (float) i9;
                    int top2 = this.computedBorderColors.getTop();
                    Canvas canvas4 = canvas;
                    drawQuadrilateral(canvas4, top2, (float) i7, f6, (float) (i7 + roundToInt), f7, f8, f7, f9, f6);
                }
                if (i3 > 0) {
                    int i10 = i7 + width;
                    float f10 = (float) i10;
                    int i11 = i4 + height;
                    float f11 = (float) (i10 - i3);
                    int right2 = this.computedBorderColors.getRight();
                    Canvas canvas5 = canvas;
                    float f12 = f10;
                    drawQuadrilateral(canvas5, right2, f12, (float) i4, f10, (float) i11, f11, (float) (i11 - roundToInt4), f11, (float) (i4 + i6));
                }
                if (roundToInt4 > 0) {
                    int i12 = i4 + height;
                    float f13 = (float) i12;
                    int i13 = i7 + width;
                    float f14 = (float) (i12 - roundToInt4);
                    int bottom = this.computedBorderColors.getBottom();
                    Canvas canvas6 = canvas;
                    float f15 = f13;
                    drawQuadrilateral(canvas6, bottom, (float) i7, f15, (float) i13, f13, (float) (i13 - i3), f14, (float) (i7 + roundToInt), f14);
                }
                this.borderPaint.setAntiAlias(true);
            } else if (Color.alpha(fastBorderCompatibleColorOrZero) != 0) {
                int i14 = bounds.right;
                int i15 = bounds.bottom;
                this.borderPaint.setColor(multiplyColorAlpha(fastBorderCompatibleColorOrZero, this.borderAlpha));
                this.borderPaint.setStyle(Paint.Style.STROKE);
                Path path = new Path();
                this.pathForSingleBorder = path;
                if (roundToInt > 0) {
                    path.reset();
                    int roundToInt5 = MathKt.roundToInt(computeBorderInsets.left);
                    updatePathEffect(roundToInt5);
                    this.borderPaint.setStrokeWidth((float) roundToInt5);
                    Path path2 = this.pathForSingleBorder;
                    if (path2 != null) {
                        path2.moveTo((float) (i7 + (roundToInt5 / 2)), (float) i4);
                    }
                    Path path3 = this.pathForSingleBorder;
                    if (path3 != null) {
                        path3.lineTo((float) (i7 + (roundToInt5 / 2)), (float) i15);
                    }
                    Path path4 = this.pathForSingleBorder;
                    if (path4 != null) {
                        canvas2.drawPath(path4, this.borderPaint);
                    }
                }
                if (i6 > 0) {
                    Path path5 = this.pathForSingleBorder;
                    if (path5 != null) {
                        path5.reset();
                    }
                    int roundToInt6 = MathKt.roundToInt(computeBorderInsets.top);
                    updatePathEffect(roundToInt6);
                    this.borderPaint.setStrokeWidth((float) roundToInt6);
                    Path path6 = this.pathForSingleBorder;
                    if (path6 != null) {
                        path6.moveTo((float) i7, (float) (i4 + (roundToInt6 / 2)));
                    }
                    Path path7 = this.pathForSingleBorder;
                    if (path7 != null) {
                        path7.lineTo((float) i14, (float) (i4 + (roundToInt6 / 2)));
                    }
                    Path path8 = this.pathForSingleBorder;
                    if (path8 != null) {
                        canvas2.drawPath(path8, this.borderPaint);
                    }
                }
                if (i3 > 0) {
                    Path path9 = this.pathForSingleBorder;
                    if (path9 != null) {
                        path9.reset();
                    }
                    int roundToInt7 = MathKt.roundToInt(computeBorderInsets.right);
                    updatePathEffect(roundToInt7);
                    this.borderPaint.setStrokeWidth((float) roundToInt7);
                    Path path10 = this.pathForSingleBorder;
                    if (path10 != null) {
                        path10.moveTo((float) (i14 - (roundToInt7 / 2)), (float) i4);
                    }
                    Path path11 = this.pathForSingleBorder;
                    if (path11 != null) {
                        path11.lineTo((float) (i14 - (roundToInt7 / 2)), (float) i15);
                    }
                    Path path12 = this.pathForSingleBorder;
                    if (path12 != null) {
                        canvas2.drawPath(path12, this.borderPaint);
                    }
                }
                if (roundToInt4 > 0) {
                    Path path13 = this.pathForSingleBorder;
                    if (path13 != null) {
                        path13.reset();
                    }
                    int roundToInt8 = MathKt.roundToInt(computeBorderInsets.bottom);
                    updatePathEffect(roundToInt8);
                    this.borderPaint.setStrokeWidth((float) roundToInt8);
                    Path path14 = this.pathForSingleBorder;
                    if (path14 != null) {
                        path14.moveTo((float) i7, (float) (i15 - (roundToInt8 / 2)));
                    }
                    Path path15 = this.pathForSingleBorder;
                    if (path15 != null) {
                        path15.lineTo((float) i14, (float) (i15 - (roundToInt8 / 2)));
                    }
                    Path path16 = this.pathForSingleBorder;
                    if (path16 != null) {
                        canvas2.drawPath(path16, this.borderPaint);
                    }
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00a6, code lost:
        r1 = (r1 = r1.getTopLeft()).toPixelFromDIP();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void drawRoundedBorders(android.graphics.Canvas r29) {
        /*
            r28 = this;
            r11 = r28
            r12 = r29
            r28.updatePath()
            r29.save()
            android.graphics.Path r0 = r11.outerClipPathForBorderRadius
            java.lang.String r1 = "Required value was null."
            if (r0 == 0) goto L_0x0223
            r12.clipPath(r0)
            android.graphics.RectF r13 = r28.computeBorderInsets()
            float r0 = r13.top
            r14 = 0
            int r0 = (r0 > r14 ? 1 : (r0 == r14 ? 0 : -1))
            if (r0 > 0) goto L_0x0030
            float r0 = r13.bottom
            int r0 = (r0 > r14 ? 1 : (r0 == r14 ? 0 : -1))
            if (r0 > 0) goto L_0x0030
            float r0 = r13.left
            int r0 = (r0 > r14 ? 1 : (r0 == r14 ? 0 : -1))
            if (r0 > 0) goto L_0x0030
            float r0 = r13.right
            int r0 = (r0 > r14 ? 1 : (r0 == r14 ? 0 : -1))
            if (r0 <= 0) goto L_0x01fb
        L_0x0030:
            float r0 = r28.getFullBorderWidth()
            com.facebook.react.uimanager.style.LogicalEdge r2 = com.facebook.react.uimanager.style.LogicalEdge.ALL
            int r2 = r11.getBorderColor(r2)
            float r3 = r13.top
            int r3 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r3 != 0) goto L_0x00e8
            float r3 = r13.bottom
            int r3 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r3 != 0) goto L_0x00e8
            float r3 = r13.left
            int r3 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r3 != 0) goto L_0x00e8
            float r3 = r13.right
            int r3 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r3 != 0) goto L_0x00e8
            com.facebook.react.uimanager.style.ColorEdges r3 = r11.computedBorderColors
            int r3 = r3.getLeft()
            if (r3 != r2) goto L_0x00e8
            com.facebook.react.uimanager.style.ColorEdges r3 = r11.computedBorderColors
            int r3 = r3.getTop()
            if (r3 != r2) goto L_0x00e8
            com.facebook.react.uimanager.style.ColorEdges r3 = r11.computedBorderColors
            int r3 = r3.getRight()
            if (r3 != r2) goto L_0x00e8
            com.facebook.react.uimanager.style.ColorEdges r3 = r11.computedBorderColors
            int r3 = r3.getBottom()
            if (r3 != r2) goto L_0x00e8
            int r3 = (r0 > r14 ? 1 : (r0 == r14 ? 0 : -1))
            if (r3 <= 0) goto L_0x01fb
            android.graphics.Paint r3 = r11.borderPaint
            int r4 = r11.borderAlpha
            int r2 = r11.multiplyColorAlpha(r2, r4)
            r3.setColor(r2)
            android.graphics.Paint r2 = r11.borderPaint
            android.graphics.Paint$Style r3 = android.graphics.Paint.Style.STROKE
            r2.setStyle(r3)
            android.graphics.Paint r2 = r11.borderPaint
            r2.setStrokeWidth(r0)
            com.facebook.react.uimanager.style.ComputedBorderRadius r0 = r11.computedBorderRadius
            if (r0 == 0) goto L_0x00d7
            boolean r0 = r0.isUniform()
            r2 = 1
            if (r0 != r2) goto L_0x00d7
            android.graphics.RectF r0 = r11.tempRectForCenterDrawPath
            if (r0 == 0) goto L_0x01fb
            com.facebook.react.uimanager.style.ComputedBorderRadius r1 = r11.computedBorderRadius
            if (r1 == 0) goto L_0x00b1
            com.facebook.react.uimanager.style.CornerRadii r1 = r1.getTopLeft()
            if (r1 == 0) goto L_0x00b1
            com.facebook.react.uimanager.style.CornerRadii r1 = r1.toPixelFromDIP()
            if (r1 == 0) goto L_0x00b1
            float r1 = r1.getHorizontal()
            goto L_0x00b2
        L_0x00b1:
            r1 = r14
        L_0x00b2:
            float r2 = r13.left
            r3 = 1056964608(0x3f000000, float:0.5)
            float r2 = r2 * r3
            float r1 = r1 - r2
            com.facebook.react.uimanager.style.ComputedBorderRadius r2 = r11.computedBorderRadius
            if (r2 == 0) goto L_0x00cc
            com.facebook.react.uimanager.style.CornerRadii r2 = r2.getTopLeft()
            if (r2 == 0) goto L_0x00cc
            com.facebook.react.uimanager.style.CornerRadii r2 = r2.toPixelFromDIP()
            if (r2 == 0) goto L_0x00cc
            float r14 = r2.getVertical()
        L_0x00cc:
            float r2 = r13.top
            float r2 = r2 * r3
            float r14 = r14 - r2
            android.graphics.Paint r2 = r11.borderPaint
            r12.drawRoundRect(r0, r1, r14, r2)
            goto L_0x01fb
        L_0x00d7:
            android.graphics.Path r0 = r11.centerDrawPath
            if (r0 == 0) goto L_0x00e2
            android.graphics.Paint r1 = r11.borderPaint
            r12.drawPath(r0, r1)
            goto L_0x01fb
        L_0x00e2:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        L_0x00e8:
            android.graphics.Paint r0 = r11.borderPaint
            android.graphics.Paint$Style r2 = android.graphics.Paint.Style.FILL
            r0.setStyle(r2)
            android.graphics.Path r0 = r11.innerClipPathForBorderRadius
            if (r0 == 0) goto L_0x021d
            r12.clipOutPath(r0)
            android.graphics.RectF r0 = r11.outerClipTempRectForBorderRadius
            if (r0 == 0) goto L_0x0217
            float r15 = r0.left
            float r10 = r0.right
            float r9 = r0.top
            float r8 = r0.bottom
            android.graphics.PointF r7 = r11.innerTopLeftCorner
            if (r7 == 0) goto L_0x0211
            android.graphics.PointF r6 = r11.innerTopRightCorner
            if (r6 == 0) goto L_0x020b
            android.graphics.PointF r5 = r11.innerBottomLeftCorner
            if (r5 == 0) goto L_0x0205
            android.graphics.PointF r4 = r11.innerBottomRightCorner
            if (r4 == 0) goto L_0x01ff
            float r0 = r13.left
            int r0 = (r0 > r14 ? 1 : (r0 == r14 ? 0 : -1))
            if (r0 <= 0) goto L_0x015a
            float r0 = r11.gapBetweenPaths
            float r16 = r9 - r0
            float r3 = r7.x
            float r1 = r7.y
            float r17 = r1 - r0
            float r2 = r5.x
            float r1 = r5.y
            float r18 = r1 + r0
            float r19 = r8 + r0
            com.facebook.react.uimanager.style.ColorEdges r0 = r11.computedBorderColors
            int r20 = r0.getLeft()
            r0 = r28
            r1 = r29
            r21 = r2
            r2 = r20
            r20 = r3
            r3 = r15
            r22 = r4
            r4 = r16
            r23 = r5
            r5 = r20
            r24 = r6
            r6 = r17
            r25 = r7
            r7 = r21
            r16 = r8
            r8 = r18
            r17 = r9
            r9 = r15
            r18 = r10
            r10 = r19
            r0.drawQuadrilateral(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            goto L_0x0168
        L_0x015a:
            r22 = r4
            r23 = r5
            r24 = r6
            r25 = r7
            r16 = r8
            r17 = r9
            r18 = r10
        L_0x0168:
            float r0 = r13.top
            int r0 = (r0 > r14 ? 1 : (r0 == r14 ? 0 : -1))
            if (r0 <= 0) goto L_0x0198
            float r0 = r11.gapBetweenPaths
            float r3 = r15 - r0
            r1 = r25
            float r2 = r1.x
            float r5 = r2 - r0
            float r6 = r1.y
            r10 = r24
            float r1 = r10.x
            float r7 = r1 + r0
            float r8 = r10.y
            float r9 = r18 + r0
            com.facebook.react.uimanager.style.ColorEdges r0 = r11.computedBorderColors
            int r2 = r0.getTop()
            r0 = r28
            r1 = r29
            r4 = r17
            r26 = r10
            r10 = r17
            r0.drawQuadrilateral(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            goto L_0x019a
        L_0x0198:
            r26 = r24
        L_0x019a:
            float r0 = r13.right
            int r0 = (r0 > r14 ? 1 : (r0 == r14 ? 0 : -1))
            if (r0 <= 0) goto L_0x01cc
            float r0 = r11.gapBetweenPaths
            float r4 = r17 - r0
            r1 = r26
            float r5 = r1.x
            float r1 = r1.y
            float r6 = r1 - r0
            r10 = r22
            float r7 = r10.x
            float r1 = r10.y
            float r8 = r1 + r0
            float r17 = r16 + r0
            com.facebook.react.uimanager.style.ColorEdges r0 = r11.computedBorderColors
            int r2 = r0.getRight()
            r0 = r28
            r1 = r29
            r3 = r18
            r9 = r18
            r27 = r10
            r10 = r17
            r0.drawQuadrilateral(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            goto L_0x01ce
        L_0x01cc:
            r27 = r22
        L_0x01ce:
            float r0 = r13.bottom
            int r0 = (r0 > r14 ? 1 : (r0 == r14 ? 0 : -1))
            if (r0 <= 0) goto L_0x01fb
            float r0 = r11.gapBetweenPaths
            float r3 = r15 - r0
            r1 = r23
            float r2 = r1.x
            float r5 = r2 - r0
            float r6 = r1.y
            r1 = r27
            float r2 = r1.x
            float r7 = r2 + r0
            float r8 = r1.y
            float r9 = r18 + r0
            com.facebook.react.uimanager.style.ColorEdges r0 = r11.computedBorderColors
            int r2 = r0.getBottom()
            r0 = r28
            r1 = r29
            r4 = r16
            r10 = r16
            r0.drawQuadrilateral(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
        L_0x01fb:
            r29.restore()
            return
        L_0x01ff:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        L_0x0205:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        L_0x020b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        L_0x0211:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        L_0x0217:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        L_0x021d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        L_0x0223:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.drawable.BorderDrawable.drawRoundedBorders(android.graphics.Canvas):void");
    }

    private final void drawQuadrilateral(Canvas canvas, int i, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if (i != 0) {
            if (this.pathForBorder == null) {
                this.pathForBorder = new Path();
            }
            this.borderPaint.setColor(multiplyColorAlpha(i, this.borderAlpha));
            Path path = this.pathForBorder;
            if (path != null) {
                path.reset();
            }
            Path path2 = this.pathForBorder;
            if (path2 != null) {
                path2.moveTo(f, f2);
            }
            Path path3 = this.pathForBorder;
            if (path3 != null) {
                path3.lineTo(f3, f4);
            }
            Path path4 = this.pathForBorder;
            if (path4 != null) {
                path4.lineTo(f5, f6);
            }
            Path path5 = this.pathForBorder;
            if (path5 != null) {
                path5.lineTo(f7, f8);
            }
            Path path6 = this.pathForBorder;
            if (path6 != null) {
                path6.lineTo(f, f2);
            }
            Path path7 = this.pathForBorder;
            if (path7 != null) {
                canvas.drawPath(path7, this.borderPaint);
            }
        }
    }

    private final RectF computeBorderInsets() {
        RectF resolve;
        BorderInsets borderInsets2 = this.borderInsets;
        float f = 0.0f;
        if (borderInsets2 == null || (resolve = borderInsets2.resolve(getLayoutDirection(), this.context)) == null) {
            return new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        }
        float dpToPx = Float.isNaN(resolve.left) ? 0.0f : PixelUtil.INSTANCE.dpToPx(resolve.left);
        float dpToPx2 = Float.isNaN(resolve.top) ? 0.0f : PixelUtil.INSTANCE.dpToPx(resolve.top);
        float dpToPx3 = Float.isNaN(resolve.right) ? 0.0f : PixelUtil.INSTANCE.dpToPx(resolve.right);
        if (!Float.isNaN(resolve.bottom)) {
            f = PixelUtil.INSTANCE.dpToPx(resolve.bottom);
        }
        return new RectF(dpToPx, dpToPx2, dpToPx3, f);
    }

    private final float getFullBorderWidth() {
        Spacing spacing = this.borderWidth;
        float raw = spacing != null ? spacing.getRaw(8) : Float.NaN;
        if (!Float.isNaN(raw)) {
            return raw;
        }
        return 0.0f;
    }

    private final void updatePathEffect() {
        BorderStyle borderStyle = getBorderStyle();
        if (borderStyle != null) {
            this.borderPaint.setPathEffect(getBorderStyle() != null ? getPathEffect(borderStyle, getFullBorderWidth()) : null);
        }
    }

    private final void updatePathEffect(int i) {
        BorderStyle borderStyle = getBorderStyle();
        if (borderStyle != null) {
            this.borderPaint.setPathEffect(getBorderStyle() != null ? getPathEffect(borderStyle, (float) i) : null);
        }
    }

    private final PathEffect getPathEffect(BorderStyle borderStyle, float f) {
        int i = WhenMappings.$EnumSwitchMapping$0[borderStyle.ordinal()];
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

    private final void getEllipseIntersectionWithLine(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, PointF pointF) {
        PointF pointF2 = pointF;
        double d9 = (double) 2;
        double d10 = (d + d3) / d9;
        double d11 = (d2 + d4) / d9;
        double d12 = d5 - d10;
        double d13 = d6 - d11;
        double abs = Math.abs(d3 - d) / d9;
        double abs2 = Math.abs(d4 - d2) / d9;
        double d14 = ((d8 - d11) - d13) / ((d7 - d10) - d12);
        double d15 = d13 - (d12 * d14);
        double d16 = abs2 * abs2;
        double d17 = abs * abs;
        double d18 = d16 + (d17 * d14 * d14);
        double d19 = d11;
        double d20 = d9 * abs * abs * d15 * d14;
        double d21 = d9 * d18;
        double sqrt = ((-d20) / d21) - Math.sqrt(((-(d17 * ((d15 * d15) - d16))) / d18) + Math.pow(d20 / d21, 2.0d));
        double d22 = sqrt + d10;
        double d23 = (d14 * sqrt) + d15 + d19;
        if (!Double.isNaN(d22) && !Double.isNaN(d23)) {
            PointF pointF3 = pointF;
            pointF3.x = (float) d22;
            pointF3.y = (float) d23;
        }
    }

    private final void updatePath() {
        ComputedBorderRadius computedBorderRadius2;
        CornerRadii cornerRadii;
        CornerRadii cornerRadii2;
        CornerRadii cornerRadii3;
        CornerRadii cornerRadii4;
        Path path;
        Path path2;
        Path path3;
        CornerRadii bottomRight;
        CornerRadii bottomLeft;
        CornerRadii topRight;
        CornerRadii topLeft;
        if (this.needUpdatePath) {
            this.needUpdatePath = false;
            Path path4 = this.innerClipPathForBorderRadius;
            if (path4 == null) {
                path4 = new Path();
            }
            this.innerClipPathForBorderRadius = path4;
            Path path5 = this.outerClipPathForBorderRadius;
            if (path5 == null) {
                path5 = new Path();
            }
            this.outerClipPathForBorderRadius = path5;
            this.pathForOutline = new Path();
            RectF rectF = this.innerClipTempRectForBorderRadius;
            if (rectF == null) {
                rectF = new RectF();
            }
            this.innerClipTempRectForBorderRadius = rectF;
            RectF rectF2 = this.outerClipTempRectForBorderRadius;
            if (rectF2 == null) {
                rectF2 = new RectF();
            }
            this.outerClipTempRectForBorderRadius = rectF2;
            RectF rectF3 = this.tempRectForCenterDrawPath;
            if (rectF3 == null) {
                rectF3 = new RectF();
            }
            this.tempRectForCenterDrawPath = rectF3;
            Path path6 = this.innerClipPathForBorderRadius;
            if (path6 != null) {
                path6.reset();
                Unit unit = Unit.INSTANCE;
            }
            Path path7 = this.outerClipPathForBorderRadius;
            if (path7 != null) {
                path7.reset();
                Unit unit2 = Unit.INSTANCE;
            }
            RectF rectF4 = this.innerClipTempRectForBorderRadius;
            if (rectF4 != null) {
                rectF4.set(getBounds());
                Unit unit3 = Unit.INSTANCE;
            }
            RectF rectF5 = this.outerClipTempRectForBorderRadius;
            if (rectF5 != null) {
                rectF5.set(getBounds());
                Unit unit4 = Unit.INSTANCE;
            }
            RectF rectF6 = this.tempRectForCenterDrawPath;
            if (rectF6 != null) {
                rectF6.set(getBounds());
                Unit unit5 = Unit.INSTANCE;
            }
            RectF computeBorderInsets = computeBorderInsets();
            if (!(Color.alpha(this.computedBorderColors.getLeft()) == 0 && Color.alpha(this.computedBorderColors.getTop()) == 0 && Color.alpha(this.computedBorderColors.getRight()) == 0 && Color.alpha(this.computedBorderColors.getBottom()) == 0)) {
                RectF rectF7 = this.innerClipTempRectForBorderRadius;
                if (rectF7 != null) {
                    rectF7.top = rectF7 != null ? rectF7.top + computeBorderInsets.top : 0.0f;
                    Unit unit6 = Unit.INSTANCE;
                }
                if (rectF7 != null) {
                    rectF7.bottom = rectF7 != null ? rectF7.bottom - computeBorderInsets.bottom : 0.0f;
                    Unit unit7 = Unit.INSTANCE;
                }
                if (rectF7 != null) {
                    rectF7.left = rectF7 != null ? rectF7.left + computeBorderInsets.left : 0.0f;
                    Unit unit8 = Unit.INSTANCE;
                }
                if (rectF7 != null) {
                    rectF7.right = rectF7 != null ? rectF7.right - computeBorderInsets.right : 0.0f;
                    Unit unit9 = Unit.INSTANCE;
                }
            }
            RectF rectF8 = this.tempRectForCenterDrawPath;
            if (rectF8 != null) {
                rectF8.top = rectF8 != null ? rectF8.top + (computeBorderInsets.top * 0.5f) : 0.0f;
                Unit unit10 = Unit.INSTANCE;
            }
            if (rectF8 != null) {
                rectF8.bottom = rectF8 != null ? rectF8.bottom - (computeBorderInsets.bottom * 0.5f) : 0.0f;
                Unit unit11 = Unit.INSTANCE;
            }
            if (rectF8 != null) {
                rectF8.left = rectF8 != null ? rectF8.left + (computeBorderInsets.left * 0.5f) : 0.0f;
                Unit unit12 = Unit.INSTANCE;
            }
            if (rectF8 != null) {
                rectF8.right = rectF8 != null ? rectF8.right - (computeBorderInsets.right * 0.5f) : 0.0f;
                Unit unit13 = Unit.INSTANCE;
            }
            BorderRadiusStyle borderRadiusStyle = this.borderRadius;
            if (borderRadiusStyle != null) {
                int layoutDirection = getLayoutDirection();
                Context context2 = this.context;
                RectF rectF9 = this.outerClipTempRectForBorderRadius;
                float pxToDp = rectF9 != null ? PixelUtil.INSTANCE.pxToDp(rectF9.width()) : 0.0f;
                RectF rectF10 = this.outerClipTempRectForBorderRadius;
                computedBorderRadius2 = borderRadiusStyle.resolve(layoutDirection, context2, pxToDp, rectF10 != null ? PixelUtil.INSTANCE.pxToDp(rectF10.height()) : 0.0f);
            } else {
                computedBorderRadius2 = null;
            }
            this.computedBorderRadius = computedBorderRadius2;
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
            float innerBorderRadius = getInnerBorderRadius(cornerRadii.getHorizontal(), computeBorderInsets.left);
            float innerBorderRadius2 = getInnerBorderRadius(cornerRadii.getVertical(), computeBorderInsets.top);
            float innerBorderRadius3 = getInnerBorderRadius(cornerRadii2.getHorizontal(), computeBorderInsets.right);
            float innerBorderRadius4 = getInnerBorderRadius(cornerRadii2.getVertical(), computeBorderInsets.top);
            float innerBorderRadius5 = getInnerBorderRadius(cornerRadii4.getHorizontal(), computeBorderInsets.right);
            float innerBorderRadius6 = getInnerBorderRadius(cornerRadii4.getVertical(), computeBorderInsets.bottom);
            float innerBorderRadius7 = getInnerBorderRadius(cornerRadii3.getHorizontal(), computeBorderInsets.left);
            float innerBorderRadius8 = getInnerBorderRadius(cornerRadii3.getVertical(), computeBorderInsets.bottom);
            RectF rectF11 = this.innerClipTempRectForBorderRadius;
            if (!(rectF11 == null || (path3 = this.innerClipPathForBorderRadius) == null)) {
                path3.addRoundRect(rectF11, new float[]{innerBorderRadius, innerBorderRadius2, innerBorderRadius3, innerBorderRadius4, innerBorderRadius5, innerBorderRadius6, innerBorderRadius7, innerBorderRadius8}, Path.Direction.CW);
                Unit unit14 = Unit.INSTANCE;
            }
            RectF rectF12 = this.outerClipTempRectForBorderRadius;
            if (!(rectF12 == null || (path2 = this.outerClipPathForBorderRadius) == null)) {
                path2.addRoundRect(rectF12, new float[]{cornerRadii.getHorizontal(), cornerRadii.getVertical(), cornerRadii2.getHorizontal(), cornerRadii2.getVertical(), cornerRadii4.getHorizontal(), cornerRadii4.getVertical(), cornerRadii3.getHorizontal(), cornerRadii3.getVertical()}, Path.Direction.CW);
                Unit unit15 = Unit.INSTANCE;
            }
            Spacing spacing = this.borderWidth;
            float f = spacing != null ? spacing.get(8) / 2.0f : 0.0f;
            Path path8 = this.pathForOutline;
            if (path8 != null) {
                path8.addRoundRect(new RectF(getBounds()), new float[]{cornerRadii.getHorizontal() + f, cornerRadii.getVertical() + f, cornerRadii2.getHorizontal() + f, cornerRadii2.getVertical() + f, cornerRadii4.getHorizontal() + f, cornerRadii4.getVertical() + f, cornerRadii3.getHorizontal() + f, cornerRadii3.getVertical() + f}, Path.Direction.CW);
                Unit unit16 = Unit.INSTANCE;
            }
            ComputedBorderRadius computedBorderRadius6 = this.computedBorderRadius;
            if (computedBorderRadius6 == null || !computedBorderRadius6.isUniform()) {
                Path path9 = this.centerDrawPath;
                if (path9 == null) {
                    path9 = new Path();
                }
                this.centerDrawPath = path9;
                path9.reset();
                Unit unit17 = Unit.INSTANCE;
                RectF rectF13 = this.tempRectForCenterDrawPath;
                if (!(rectF13 == null || (path = this.centerDrawPath) == null)) {
                    path.addRoundRect(rectF13, new float[]{cornerRadii.getHorizontal() - (computeBorderInsets.left * 0.5f), cornerRadii.getVertical() - (computeBorderInsets.top * 0.5f), cornerRadii2.getHorizontal() - (computeBorderInsets.right * 0.5f), cornerRadii2.getVertical() - (computeBorderInsets.top * 0.5f), cornerRadii4.getHorizontal() - (computeBorderInsets.right * 0.5f), cornerRadii4.getVertical() - (computeBorderInsets.bottom * 0.5f), cornerRadii3.getHorizontal() - (computeBorderInsets.left * 0.5f), cornerRadii3.getVertical() - (computeBorderInsets.bottom * 0.5f)}, Path.Direction.CW);
                    Unit unit18 = Unit.INSTANCE;
                }
            }
            RectF rectF14 = this.innerClipTempRectForBorderRadius;
            RectF rectF15 = this.outerClipTempRectForBorderRadius;
            if (rectF14 != null && rectF15 != null) {
                PointF pointF = this.innerTopLeftCorner;
                if (pointF == null) {
                    pointF = new PointF();
                }
                PointF pointF2 = pointF;
                this.innerTopLeftCorner = pointF2;
                pointF2.x = rectF14.left;
                Unit unit19 = Unit.INSTANCE;
                pointF2.y = rectF14.top;
                Unit unit20 = Unit.INSTANCE;
                float f2 = rectF14.left;
                float f3 = rectF14.top;
                float f4 = (float) 2;
                RectF rectF16 = rectF15;
                RectF rectF17 = rectF14;
                getEllipseIntersectionWithLine((double) f2, (double) f3, (double) ((innerBorderRadius * f4) + f2), (double) ((f4 * innerBorderRadius2) + f3), (double) rectF15.left, (double) rectF15.top, (double) f2, (double) f3, pointF2);
                Unit unit21 = Unit.INSTANCE;
                PointF pointF3 = this.innerBottomLeftCorner;
                if (pointF3 == null) {
                    pointF3 = new PointF();
                }
                PointF pointF4 = pointF3;
                this.innerBottomLeftCorner = pointF4;
                RectF rectF18 = rectF17;
                pointF4.x = rectF18.left;
                Unit unit22 = Unit.INSTANCE;
                pointF4.y = rectF18.bottom;
                Unit unit23 = Unit.INSTANCE;
                float f5 = rectF18.left;
                float f6 = rectF18.bottom;
                float f7 = (float) 2;
                RectF rectF19 = rectF16;
                RectF rectF20 = rectF19;
                RectF rectF21 = rectF18;
                RectF rectF22 = rectF20;
                getEllipseIntersectionWithLine((double) f5, (double) (f6 - (innerBorderRadius8 * f7)), (double) ((f7 * innerBorderRadius7) + f5), (double) f6, (double) rectF19.left, (double) rectF20.bottom, (double) f5, (double) f6, pointF4);
                Unit unit24 = Unit.INSTANCE;
                PointF pointF5 = this.innerTopRightCorner;
                if (pointF5 == null) {
                    pointF5 = new PointF();
                }
                PointF pointF6 = pointF5;
                this.innerTopRightCorner = pointF6;
                RectF rectF23 = rectF21;
                pointF6.x = rectF23.right;
                Unit unit25 = Unit.INSTANCE;
                pointF6.y = rectF23.top;
                Unit unit26 = Unit.INSTANCE;
                float f8 = rectF23.right;
                float f9 = (float) 2;
                float f10 = rectF23.top;
                RectF rectF24 = rectF22;
                RectF rectF25 = rectF24;
                RectF rectF26 = rectF23;
                RectF rectF27 = rectF25;
                getEllipseIntersectionWithLine((double) (f8 - (innerBorderRadius3 * f9)), (double) f10, (double) f8, (double) ((f9 * innerBorderRadius4) + f10), (double) rectF24.right, (double) rectF25.top, (double) f8, (double) f10, pointF6);
                Unit unit27 = Unit.INSTANCE;
                PointF pointF7 = this.innerBottomRightCorner;
                if (pointF7 == null) {
                    pointF7 = new PointF();
                }
                PointF pointF8 = pointF7;
                this.innerBottomRightCorner = pointF8;
                RectF rectF28 = rectF26;
                pointF8.x = rectF28.right;
                Unit unit28 = Unit.INSTANCE;
                pointF8.y = rectF28.bottom;
                Unit unit29 = Unit.INSTANCE;
                float f11 = rectF28.right;
                float f12 = (float) 2;
                float f13 = rectF28.bottom;
                RectF rectF29 = rectF27;
                getEllipseIntersectionWithLine((double) (f11 - (innerBorderRadius5 * f12)), (double) (f13 - (f12 * innerBorderRadius6)), (double) f11, (double) f13, (double) rectF29.right, (double) rectF29.bottom, (double) f11, (double) f13, pointF8);
                Unit unit30 = Unit.INSTANCE;
            }
        }
    }
}
