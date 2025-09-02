package com.facebook.react.uimanager.drawable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ComposeShader;
import android.graphics.DashPathEffect;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.ColorUtils;
import androidx.core.util.Preconditions;
import com.facebook.react.common.annotations.UnstableReactNativeAPI;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.FloatUtil;
import com.facebook.react.uimanager.LengthPercentage;
import com.facebook.react.uimanager.LengthPercentageType;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.Spacing;
import com.facebook.react.uimanager.style.BackgroundImageLayer;
import com.facebook.react.uimanager.style.BorderRadiusProp;
import com.facebook.react.uimanager.style.BorderRadiusStyle;
import com.facebook.react.uimanager.style.BorderStyle;
import com.facebook.react.uimanager.style.ComputedBorderRadius;
import com.facebook.react.uimanager.style.CornerRadii;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@UnstableReactNativeAPI
public class CSSBackgroundDrawable extends Drawable {
    private static final int ALL_BITS_SET = -1;
    private static final int ALL_BITS_UNSET = 0;
    private static final int DEFAULT_BORDER_ALPHA = 255;
    private static final int DEFAULT_BORDER_COLOR = -16777216;
    private static final int DEFAULT_BORDER_RGB = 0;
    private int mAlpha = 255;
    private Path mBackgroundColorRenderPath;
    private List<BackgroundImageLayer> mBackgroundImageLayers = null;
    private Spacing mBorderAlpha;
    private Spacing mBorderRGB;
    private BorderRadiusStyle mBorderRadius = new BorderRadiusStyle();
    private BorderStyle mBorderStyle;
    private Spacing mBorderWidth;
    private Path mCenterDrawPath;
    private int mColor = 0;
    private ComputedBorderRadius mComputedBorderRadius = new ComputedBorderRadius();
    private final Context mContext;
    private final float mGapBetweenPaths = 0.8f;
    private PointF mInnerBottomLeftCorner;
    private PointF mInnerBottomRightCorner;
    private Path mInnerClipPathForBorderRadius;
    private RectF mInnerClipTempRectForBorderRadius;
    private PointF mInnerTopLeftCorner;
    private PointF mInnerTopRightCorner;
    private int mLayoutDirectionOverride = -1;
    private boolean mNeedUpdatePathForBorderRadius = false;
    private Path mOuterClipPathForBorderRadius;
    private RectF mOuterClipTempRectForBorderRadius;
    private final Paint mPaint = new Paint(1);
    private Path mPathForBorder;
    private Path mPathForBorderRadiusOutline;
    private final Path mPathForSingleBorder = new Path();
    private RectF mTempRectForBorderRadiusOutline;
    private RectF mTempRectForCenterDrawPath;

    private static int colorFromAlphaAndRGBComponents(float f, float f2) {
        return ((((int) f) << 24) & DEFAULT_BORDER_COLOR) | (((int) f2) & 16777215);
    }

    private static int fastBorderCompatibleColorOrZero(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
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

    private static int multiplyColorAlpha(int i, int i2) {
        if (i2 == 255) {
            return i;
        }
        if (i2 == 0) {
            return i & 16777215;
        }
        return (i & 16777215) | ((((i >>> 24) * (i2 + (i2 >> 7))) >> 8) << 24);
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    /* renamed from: com.facebook.react.uimanager.drawable.CSSBackgroundDrawable$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$uimanager$style$BorderStyle;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.facebook.react.uimanager.style.BorderStyle[] r0 = com.facebook.react.uimanager.style.BorderStyle.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$react$uimanager$style$BorderStyle = r0
                com.facebook.react.uimanager.style.BorderStyle r1 = com.facebook.react.uimanager.style.BorderStyle.SOLID     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$style$BorderStyle     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.react.uimanager.style.BorderStyle r1 = com.facebook.react.uimanager.style.BorderStyle.DASHED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$facebook$react$uimanager$style$BorderStyle     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.facebook.react.uimanager.style.BorderStyle r1 = com.facebook.react.uimanager.style.BorderStyle.DOTTED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.drawable.CSSBackgroundDrawable.AnonymousClass1.<clinit>():void");
        }
    }

    private static PathEffect getPathEffect(BorderStyle borderStyle, float f) {
        int i = AnonymousClass1.$SwitchMap$com$facebook$react$uimanager$style$BorderStyle[borderStyle.ordinal()];
        if (i == 2) {
            float f2 = f * 3.0f;
            return new DashPathEffect(new float[]{f2, f2, f2, f2}, 0.0f);
        } else if (i != 3) {
            return null;
        } else {
            return new DashPathEffect(new float[]{f, f, f, f}, 0.0f);
        }
    }

    public CSSBackgroundDrawable(Context context) {
        this.mContext = context;
    }

    public void draw(Canvas canvas) {
        updatePathEffect();
        if (!hasRoundedBorders()) {
            drawRectangularBackgroundWithBorders(canvas);
        } else {
            drawRoundedBackgroundWithBorders(canvas);
        }
    }

    public boolean hasRoundedBorders() {
        return this.mBorderRadius.hasRoundedBorders();
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.mNeedUpdatePathForBorderRadius = true;
    }

    public void setAlpha(int i) {
        if (i != this.mAlpha) {
            this.mAlpha = i;
            invalidateSelf();
        }
    }

    public int getAlpha() {
        return this.mAlpha;
    }

    @Deprecated
    public void setLayoutDirectionOverride(int i) {
        if (this.mLayoutDirectionOverride != i) {
            this.mLayoutDirectionOverride = i;
        }
    }

    @SuppressLint({"WrongConstant"})
    public int getLayoutDirection() {
        int i = this.mLayoutDirectionOverride;
        return i == -1 ? super.getLayoutDirection() : i;
    }

    public int getOpacity() {
        int alpha = (Color.alpha(this.mColor) * this.mAlpha) >> 8;
        if (alpha != 0) {
            return alpha != 255 ? -3 : -1;
        }
        return -2;
    }

    public void getOutline(Outline outline) {
        if (hasRoundedBorders()) {
            updatePath();
            outline.setConvexPath((Path) Preconditions.checkNotNull(this.mPathForBorderRadiusOutline));
            return;
        }
        outline.setRect(getBounds());
    }

    public void setBorderWidth(int i, float f) {
        if (this.mBorderWidth == null) {
            this.mBorderWidth = new Spacing();
        }
        if (!FloatUtil.floatsEqual(this.mBorderWidth.getRaw(i), f)) {
            this.mBorderWidth.set(i, f);
            if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 8) {
                this.mNeedUpdatePathForBorderRadius = true;
            }
            invalidateSelf();
        }
    }

    public void setBorderColor(int i, Integer num) {
        float f = Float.NaN;
        float intValue = num == null ? Float.NaN : (float) (num.intValue() & 16777215);
        if (num != null) {
            f = (float) (num.intValue() >>> 24);
        }
        setBorderRGB(i, intValue);
        setBorderAlpha(i, f);
        this.mNeedUpdatePathForBorderRadius = true;
    }

    private void setBorderRGB(int i, float f) {
        if (this.mBorderRGB == null) {
            this.mBorderRGB = new Spacing(0.0f);
        }
        if (!FloatUtil.floatsEqual(this.mBorderRGB.getRaw(i), f)) {
            this.mBorderRGB.set(i, f);
            invalidateSelf();
        }
    }

    private void setBorderAlpha(int i, float f) {
        if (this.mBorderAlpha == null) {
            this.mBorderAlpha = new Spacing(255.0f);
        }
        if (!FloatUtil.floatsEqual(this.mBorderAlpha.getRaw(i), f)) {
            this.mBorderAlpha.set(i, f);
            invalidateSelf();
        }
    }

    public void setBorderStyle(String str) {
        setBorderStyle(str == null ? null : BorderStyle.valueOf(str.toUpperCase(Locale.US)));
    }

    public void setBorderStyle(BorderStyle borderStyle) {
        if (this.mBorderStyle != borderStyle) {
            this.mBorderStyle = borderStyle;
            this.mNeedUpdatePathForBorderRadius = true;
            invalidateSelf();
        }
    }

    public BorderStyle getBorderStyle() {
        return this.mBorderStyle;
    }

    @Deprecated(forRemoval = true, since = "0.75.0")
    public void setRadius(float f) {
        Float valueOf = Float.isNaN(f) ? null : Float.valueOf(f);
        if (valueOf == null) {
            setBorderRadius(BorderRadiusProp.BORDER_RADIUS, (LengthPercentage) null);
        } else {
            setBorderRadius(BorderRadiusProp.BORDER_RADIUS, new LengthPercentage(valueOf.floatValue(), LengthPercentageType.POINT));
        }
    }

    @Deprecated(forRemoval = true, since = "0.75.0")
    public void setRadius(float f, int i) {
        Float valueOf = Float.isNaN(f) ? null : Float.valueOf(f);
        if (valueOf == null) {
            this.mBorderRadius.set(BorderRadiusProp.values()[i], (LengthPercentage) null);
            invalidateSelf();
            return;
        }
        setBorderRadius(BorderRadiusProp.values()[i], new LengthPercentage(valueOf.floatValue(), LengthPercentageType.POINT));
    }

    public void setBorderRadius(BorderRadiusProp borderRadiusProp, LengthPercentage lengthPercentage) {
        if (!Objects.equals(lengthPercentage, this.mBorderRadius.get(borderRadiusProp))) {
            this.mBorderRadius.set(borderRadiusProp, lengthPercentage);
            this.mNeedUpdatePathForBorderRadius = true;
            invalidateSelf();
        }
    }

    public void setBorderRadius(BorderRadiusStyle borderRadiusStyle) {
        this.mBorderRadius = borderRadiusStyle;
    }

    public BorderRadiusStyle getBorderRadius() {
        return this.mBorderRadius;
    }

    public float getInnerBorderRadius(float f, float f2) {
        return Math.max(f - f2, 0.0f);
    }

    public void setColor(int i) {
        this.mColor = i;
        invalidateSelf();
    }

    public void setBackgroundImage(List<BackgroundImageLayer> list) {
        this.mBackgroundImageLayers = list;
        invalidateSelf();
    }

    @VisibleForTesting
    public int getColor() {
        return this.mColor;
    }

    public Path getBorderBoxPath() {
        if (!hasRoundedBorders()) {
            return null;
        }
        updatePath();
        return new Path((Path) Preconditions.checkNotNull(this.mOuterClipPathForBorderRadius));
    }

    public RectF getBorderBoxRect() {
        return new RectF(getBounds());
    }

    public Path getPaddingBoxPath() {
        if (!hasRoundedBorders()) {
            return null;
        }
        updatePath();
        return new Path((Path) Preconditions.checkNotNull(this.mInnerClipPathForBorderRadius));
    }

    public RectF getPaddingBoxRect() {
        RectF directionAwareBorderInsets = getDirectionAwareBorderInsets();
        if (directionAwareBorderInsets == null) {
            return new RectF(0.0f, 0.0f, (float) getBounds().width(), (float) getBounds().height());
        }
        return new RectF(directionAwareBorderInsets.left, directionAwareBorderInsets.top, ((float) getBounds().width()) - directionAwareBorderInsets.right, ((float) getBounds().height()) - directionAwareBorderInsets.bottom);
    }

    private void drawRoundedBackgroundWithBorders(Canvas canvas) {
        int i;
        int i2;
        float f;
        PointF pointF;
        float f2;
        float f3;
        float f4;
        int i3;
        PointF pointF2;
        PointF pointF3;
        PointF pointF4;
        Canvas canvas2 = canvas;
        updatePath();
        canvas.save();
        canvas2.clipPath((Path) Preconditions.checkNotNull(this.mOuterClipPathForBorderRadius), Region.Op.INTERSECT);
        int i4 = this.mColor;
        int alphaComponent = ColorUtils.setAlphaComponent(i4, (Color.alpha(i4) * this.mAlpha) >> 8);
        if (Color.alpha(alphaComponent) != 0) {
            this.mPaint.setColor(alphaComponent);
            this.mPaint.setStyle(Paint.Style.FILL);
            canvas2.drawPath((Path) Preconditions.checkNotNull(this.mBackgroundColorRenderPath), this.mPaint);
        }
        List<BackgroundImageLayer> list = this.mBackgroundImageLayers;
        if (list != null && !list.isEmpty()) {
            this.mPaint.setShader(getBackgroundImageShader());
            this.mPaint.setStyle(Paint.Style.FILL);
            canvas2.drawPath((Path) Preconditions.checkNotNull(this.mBackgroundColorRenderPath), this.mPaint);
            this.mPaint.setShader((Shader) null);
        }
        RectF directionAwareBorderInsets = getDirectionAwareBorderInsets();
        boolean z = false;
        int borderColor = getBorderColor(0);
        int borderColor2 = getBorderColor(1);
        int borderColor3 = getBorderColor(2);
        int borderColor4 = getBorderColor(3);
        int borderColor5 = getBorderColor(9);
        int borderColor6 = getBorderColor(11);
        int borderColor7 = getBorderColor(10);
        if (isBorderColorDefined(9)) {
            borderColor2 = borderColor5;
            borderColor4 = borderColor2;
        }
        if (!isBorderColorDefined(10)) {
            borderColor7 = borderColor4;
        }
        int i5 = isBorderColorDefined(11) ? borderColor6 : borderColor2;
        if (directionAwareBorderInsets.top > 0.0f || directionAwareBorderInsets.bottom > 0.0f || directionAwareBorderInsets.left > 0.0f || directionAwareBorderInsets.right > 0.0f) {
            float fullBorderWidth = getFullBorderWidth();
            int borderColor8 = getBorderColor(8);
            if (directionAwareBorderInsets.top != fullBorderWidth || directionAwareBorderInsets.bottom != fullBorderWidth || directionAwareBorderInsets.left != fullBorderWidth || directionAwareBorderInsets.right != fullBorderWidth || borderColor != borderColor8 || i5 != borderColor8 || borderColor3 != borderColor8 || borderColor7 != borderColor8) {
                this.mPaint.setStyle(Paint.Style.FILL);
                canvas2.clipPath((Path) Preconditions.checkNotNull(this.mInnerClipPathForBorderRadius), Region.Op.DIFFERENCE);
                if (getLayoutDirection() == 1) {
                    z = true;
                }
                int borderColor9 = getBorderColor(4);
                int borderColor10 = getBorderColor(5);
                if (I18nUtil.getInstance().doLeftAndRightSwapInRTL(this.mContext)) {
                    if (isBorderColorDefined(4)) {
                        borderColor = borderColor9;
                    }
                    if (isBorderColorDefined(5)) {
                        borderColor3 = borderColor10;
                    }
                    i2 = z ? borderColor3 : borderColor;
                    if (!z) {
                        borderColor = borderColor3;
                    }
                    i = borderColor;
                } else {
                    int i6 = z ? borderColor10 : borderColor9;
                    if (!z) {
                        borderColor9 = borderColor10;
                    }
                    boolean isBorderColorDefined = isBorderColorDefined(4);
                    boolean isBorderColorDefined2 = isBorderColorDefined(5);
                    boolean z2 = z ? isBorderColorDefined2 : isBorderColorDefined;
                    if (!z) {
                        isBorderColorDefined = isBorderColorDefined2;
                    }
                    if (z2) {
                        borderColor = i6;
                    }
                    if (isBorderColorDefined) {
                        i2 = borderColor;
                        i = borderColor9;
                    } else {
                        i2 = borderColor;
                        i = borderColor3;
                    }
                }
                RectF rectF = (RectF) Preconditions.checkNotNull(this.mOuterClipTempRectForBorderRadius);
                float f5 = rectF.left;
                float f6 = rectF.right;
                float f7 = rectF.top;
                float f8 = rectF.bottom;
                PointF pointF5 = (PointF) Preconditions.checkNotNull(this.mInnerTopLeftCorner);
                PointF pointF6 = (PointF) Preconditions.checkNotNull(this.mInnerTopRightCorner);
                PointF pointF7 = (PointF) Preconditions.checkNotNull(this.mInnerBottomLeftCorner);
                PointF pointF8 = (PointF) Preconditions.checkNotNull(this.mInnerBottomRightCorner);
                if (directionAwareBorderInsets.left > 0.0f) {
                    pointF4 = pointF8;
                    i3 = borderColor7;
                    pointF2 = pointF6;
                    pointF = pointF7;
                    pointF3 = pointF5;
                    float f9 = pointF5.y - 0.8f;
                    f4 = f8;
                    f3 = f7;
                    f2 = f6;
                    f = f5;
                    drawQuadrilateral(canvas, i2, f5, f7 - 0.8f, pointF5.x, f9, pointF7.x, pointF7.y + 0.8f, f5, f8 + 0.8f);
                } else {
                    pointF4 = pointF8;
                    pointF = pointF7;
                    pointF3 = pointF5;
                    f4 = f8;
                    f3 = f7;
                    f2 = f6;
                    f = f5;
                    i3 = borderColor7;
                    pointF2 = pointF6;
                }
                if (directionAwareBorderInsets.top > 0.0f) {
                    drawQuadrilateral(canvas, i5, f - 0.8f, f3, pointF3.x - 0.8f, pointF3.y, pointF2.x + 0.8f, pointF2.y, f2 + 0.8f, f3);
                }
                if (directionAwareBorderInsets.right > 0.0f) {
                    drawQuadrilateral(canvas, i, f2, f3 - 0.8f, pointF2.x, pointF2.y - 0.8f, pointF4.x, pointF4.y + 0.8f, f2, f4 + 0.8f);
                }
                if (directionAwareBorderInsets.bottom > 0.0f) {
                    PointF pointF9 = pointF;
                    drawQuadrilateral(canvas, i3, f - 0.8f, f4, pointF9.x - 0.8f, pointF9.y, pointF4.x + 0.8f, pointF4.y, f2 + 0.8f, f4);
                }
            } else if (fullBorderWidth > 0.0f) {
                this.mPaint.setColor(multiplyColorAlpha(borderColor8, this.mAlpha));
                this.mPaint.setStyle(Paint.Style.STROKE);
                this.mPaint.setStrokeWidth(fullBorderWidth);
                canvas2.drawPath((Path) Preconditions.checkNotNull(this.mCenterDrawPath), this.mPaint);
            }
        }
        canvas.restore();
    }

    private void updatePath() {
        float f;
        if (this.mNeedUpdatePathForBorderRadius) {
            this.mNeedUpdatePathForBorderRadius = false;
            if (this.mInnerClipPathForBorderRadius == null) {
                this.mInnerClipPathForBorderRadius = new Path();
            }
            if (this.mBackgroundColorRenderPath == null) {
                this.mBackgroundColorRenderPath = new Path();
            }
            if (this.mOuterClipPathForBorderRadius == null) {
                this.mOuterClipPathForBorderRadius = new Path();
            }
            if (this.mPathForBorderRadiusOutline == null) {
                this.mPathForBorderRadiusOutline = new Path();
            }
            if (this.mCenterDrawPath == null) {
                this.mCenterDrawPath = new Path();
            }
            if (this.mInnerClipTempRectForBorderRadius == null) {
                this.mInnerClipTempRectForBorderRadius = new RectF();
            }
            if (this.mOuterClipTempRectForBorderRadius == null) {
                this.mOuterClipTempRectForBorderRadius = new RectF();
            }
            if (this.mTempRectForBorderRadiusOutline == null) {
                this.mTempRectForBorderRadiusOutline = new RectF();
            }
            if (this.mTempRectForCenterDrawPath == null) {
                this.mTempRectForCenterDrawPath = new RectF();
            }
            this.mInnerClipPathForBorderRadius.reset();
            this.mBackgroundColorRenderPath.reset();
            this.mOuterClipPathForBorderRadius.reset();
            this.mPathForBorderRadiusOutline.reset();
            this.mCenterDrawPath.reset();
            this.mInnerClipTempRectForBorderRadius.set(getBounds());
            this.mOuterClipTempRectForBorderRadius.set(getBounds());
            this.mTempRectForBorderRadiusOutline.set(getBounds());
            this.mTempRectForCenterDrawPath.set(getBounds());
            RectF directionAwareBorderInsets = getDirectionAwareBorderInsets();
            int borderColor = getBorderColor(0);
            int borderColor2 = getBorderColor(1);
            int borderColor3 = getBorderColor(2);
            int borderColor4 = getBorderColor(3);
            int borderColor5 = getBorderColor(8);
            int borderColor6 = getBorderColor(9);
            int borderColor7 = getBorderColor(11);
            int borderColor8 = getBorderColor(10);
            if (isBorderColorDefined(9)) {
                borderColor2 = borderColor6;
                borderColor4 = borderColor2;
            }
            if (!isBorderColorDefined(10)) {
                borderColor8 = borderColor4;
            }
            if (!isBorderColorDefined(11)) {
                borderColor7 = borderColor2;
            }
            if (Color.alpha(borderColor) == 0 && Color.alpha(borderColor7) == 0 && Color.alpha(borderColor3) == 0 && Color.alpha(borderColor8) == 0 && Color.alpha(borderColor5) == 0) {
                f = 0.0f;
            } else {
                RectF rectF = this.mInnerClipTempRectForBorderRadius;
                rectF.top += directionAwareBorderInsets.top;
                rectF.bottom -= directionAwareBorderInsets.bottom;
                rectF.left += directionAwareBorderInsets.left;
                rectF.right -= directionAwareBorderInsets.right;
                f = 0.8f;
            }
            RectF rectF2 = this.mTempRectForCenterDrawPath;
            rectF2.top += directionAwareBorderInsets.top * 0.5f;
            rectF2.bottom -= directionAwareBorderInsets.bottom * 0.5f;
            rectF2.left += directionAwareBorderInsets.left * 0.5f;
            rectF2.right -= directionAwareBorderInsets.right * 0.5f;
            ComputedBorderRadius resolve = this.mBorderRadius.resolve(getLayoutDirection(), this.mContext, PixelUtil.toDIPFromPixel(this.mOuterClipTempRectForBorderRadius.width()), PixelUtil.toDIPFromPixel(this.mOuterClipTempRectForBorderRadius.height()));
            this.mComputedBorderRadius = resolve;
            CornerRadii pixelFromDIP = resolve.getTopLeft().toPixelFromDIP();
            CornerRadii pixelFromDIP2 = this.mComputedBorderRadius.getTopRight().toPixelFromDIP();
            CornerRadii pixelFromDIP3 = this.mComputedBorderRadius.getBottomLeft().toPixelFromDIP();
            CornerRadii pixelFromDIP4 = this.mComputedBorderRadius.getBottomRight().toPixelFromDIP();
            float innerBorderRadius = getInnerBorderRadius(pixelFromDIP.getHorizontal(), directionAwareBorderInsets.left);
            float innerBorderRadius2 = getInnerBorderRadius(pixelFromDIP.getVertical(), directionAwareBorderInsets.top);
            float innerBorderRadius3 = getInnerBorderRadius(pixelFromDIP2.getHorizontal(), directionAwareBorderInsets.right);
            float innerBorderRadius4 = getInnerBorderRadius(pixelFromDIP2.getVertical(), directionAwareBorderInsets.top);
            float innerBorderRadius5 = getInnerBorderRadius(pixelFromDIP4.getHorizontal(), directionAwareBorderInsets.right);
            float innerBorderRadius6 = getInnerBorderRadius(pixelFromDIP4.getVertical(), directionAwareBorderInsets.bottom);
            float innerBorderRadius7 = getInnerBorderRadius(pixelFromDIP3.getHorizontal(), directionAwareBorderInsets.left);
            float innerBorderRadius8 = getInnerBorderRadius(pixelFromDIP3.getVertical(), directionAwareBorderInsets.bottom);
            RectF rectF3 = directionAwareBorderInsets;
            CornerRadii cornerRadii = pixelFromDIP3;
            CornerRadii cornerRadii2 = pixelFromDIP4;
            Path.Direction direction = Path.Direction.CW;
            this.mInnerClipPathForBorderRadius.addRoundRect(this.mInnerClipTempRectForBorderRadius, new float[]{innerBorderRadius, innerBorderRadius2, innerBorderRadius3, innerBorderRadius4, innerBorderRadius5, innerBorderRadius6, innerBorderRadius7, innerBorderRadius8}, direction);
            Path path = this.mBackgroundColorRenderPath;
            RectF rectF4 = this.mInnerClipTempRectForBorderRadius;
            path.addRoundRect(rectF4.left - f, rectF4.top - f, rectF4.right + f, rectF4.bottom + f, new float[]{innerBorderRadius, innerBorderRadius2, innerBorderRadius3, innerBorderRadius4, innerBorderRadius5, innerBorderRadius6, innerBorderRadius7, innerBorderRadius8}, direction);
            float f2 = innerBorderRadius5;
            float f3 = innerBorderRadius6;
            this.mOuterClipPathForBorderRadius.addRoundRect(this.mOuterClipTempRectForBorderRadius, new float[]{pixelFromDIP.getHorizontal(), pixelFromDIP.getVertical(), pixelFromDIP2.getHorizontal(), pixelFromDIP2.getVertical(), cornerRadii2.getHorizontal(), cornerRadii2.getVertical(), cornerRadii.getHorizontal(), cornerRadii.getVertical()}, direction);
            Spacing spacing = this.mBorderWidth;
            float f4 = spacing != null ? spacing.get(8) / 2.0f : 0.0f;
            this.mPathForBorderRadiusOutline.addRoundRect(this.mTempRectForBorderRadiusOutline, new float[]{pixelFromDIP.getHorizontal() + f4, pixelFromDIP.getVertical() + f4, pixelFromDIP2.getHorizontal() + f4, pixelFromDIP2.getVertical() + f4, cornerRadii2.getHorizontal() + f4, cornerRadii2.getVertical() + f4, cornerRadii.getHorizontal() + f4, cornerRadii.getVertical() + f4}, direction);
            Path path2 = this.mCenterDrawPath;
            RectF rectF5 = this.mTempRectForCenterDrawPath;
            RectF rectF6 = rectF3;
            float horizontal = pixelFromDIP.getHorizontal() - (rectF6.left * 0.5f);
            float vertical = pixelFromDIP.getVertical() - (rectF6.top * 0.5f);
            float f5 = innerBorderRadius4;
            float horizontal2 = pixelFromDIP2.getHorizontal() - (rectF6.right * 0.5f);
            float vertical2 = pixelFromDIP2.getVertical() - (rectF6.top * 0.5f);
            float f6 = innerBorderRadius3;
            float horizontal3 = cornerRadii2.getHorizontal() - (rectF6.right * 0.5f);
            float f7 = innerBorderRadius7;
            float vertical3 = cornerRadii2.getVertical() - (rectF6.bottom * 0.5f);
            float f8 = innerBorderRadius8;
            path2.addRoundRect(rectF5, new float[]{horizontal, vertical, horizontal2, vertical2, horizontal3, vertical3, cornerRadii.getHorizontal() - (rectF6.left * 0.5f), cornerRadii.getVertical() - (rectF6.bottom * 0.5f)}, direction);
            if (this.mInnerTopLeftCorner == null) {
                this.mInnerTopLeftCorner = new PointF();
            }
            PointF pointF = this.mInnerTopLeftCorner;
            PointF pointF2 = pointF;
            RectF rectF7 = this.mInnerClipTempRectForBorderRadius;
            float f9 = rectF7.left;
            pointF.x = f9;
            float f10 = rectF7.top;
            pointF.y = f10;
            RectF rectF8 = this.mOuterClipTempRectForBorderRadius;
            getEllipseIntersectionWithLine((double) f9, (double) f10, (double) ((innerBorderRadius * 2.0f) + f9), (double) ((innerBorderRadius2 * 2.0f) + f10), (double) rectF8.left, (double) rectF8.top, (double) f9, (double) f10, pointF2);
            if (this.mInnerBottomLeftCorner == null) {
                this.mInnerBottomLeftCorner = new PointF();
            }
            PointF pointF3 = this.mInnerBottomLeftCorner;
            PointF pointF4 = pointF3;
            RectF rectF9 = this.mInnerClipTempRectForBorderRadius;
            float f11 = rectF9.left;
            pointF3.x = f11;
            float f12 = rectF9.bottom;
            pointF3.y = f12;
            RectF rectF10 = this.mOuterClipTempRectForBorderRadius;
            getEllipseIntersectionWithLine((double) f11, (double) (f12 - (f8 * 2.0f)), (double) ((f7 * 2.0f) + f11), (double) f12, (double) rectF10.left, (double) rectF10.bottom, (double) f11, (double) f12, pointF4);
            if (this.mInnerTopRightCorner == null) {
                this.mInnerTopRightCorner = new PointF();
            }
            PointF pointF5 = this.mInnerTopRightCorner;
            PointF pointF6 = pointF5;
            RectF rectF11 = this.mInnerClipTempRectForBorderRadius;
            float f13 = rectF11.right;
            pointF5.x = f13;
            float f14 = rectF11.top;
            pointF5.y = f14;
            RectF rectF12 = this.mOuterClipTempRectForBorderRadius;
            getEllipseIntersectionWithLine((double) (f13 - (f6 * 2.0f)), (double) f14, (double) f13, (double) ((f5 * 2.0f) + f14), (double) rectF12.right, (double) rectF12.top, (double) f13, (double) f14, pointF6);
            if (this.mInnerBottomRightCorner == null) {
                this.mInnerBottomRightCorner = new PointF();
            }
            PointF pointF7 = this.mInnerBottomRightCorner;
            PointF pointF8 = pointF7;
            RectF rectF13 = this.mInnerClipTempRectForBorderRadius;
            float f15 = rectF13.right;
            pointF7.x = f15;
            float f16 = rectF13.bottom;
            pointF7.y = f16;
            RectF rectF14 = this.mOuterClipTempRectForBorderRadius;
            getEllipseIntersectionWithLine((double) (f15 - (f2 * 2.0f)), (double) (f16 - (f3 * 2.0f)), (double) f15, (double) f16, (double) rectF14.right, (double) rectF14.bottom, (double) f15, (double) f16, pointF8);
        }
    }

    private static void getEllipseIntersectionWithLine(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, PointF pointF) {
        PointF pointF2 = pointF;
        double d9 = (d + d3) / 2.0d;
        double d10 = (d2 + d4) / 2.0d;
        double d11 = d5 - d9;
        double d12 = d6 - d10;
        double abs = Math.abs(d3 - d) / 2.0d;
        double abs2 = Math.abs(d4 - d2) / 2.0d;
        double d13 = ((d8 - d10) - d12) / ((d7 - d9) - d11);
        double d14 = d12 - (d11 * d13);
        double d15 = abs2 * abs2;
        double d16 = abs * abs;
        double d17 = d15 + (d16 * d13 * d13);
        double d18 = abs * 2.0d * abs * d14 * d13;
        double d19 = (-(d16 * ((d14 * d14) - d15))) / d17;
        double d20 = d17 * 2.0d;
        double sqrt = ((-d18) / d20) - Math.sqrt(d19 + Math.pow(d18 / d20, 2.0d));
        double d21 = (d13 * sqrt) + d14;
        double d22 = sqrt + d9;
        double d23 = d21 + d10;
        if (!Double.isNaN(d22) && !Double.isNaN(d23)) {
            PointF pointF3 = pointF;
            pointF3.x = (float) d22;
            pointF3.y = (float) d23;
        }
    }

    public float getBorderWidthOrDefaultTo(float f, int i) {
        Float borderWidth = getBorderWidth(i);
        if (borderWidth == null) {
            return f;
        }
        return borderWidth.floatValue();
    }

    public Float getBorderWidth(int i) {
        Spacing spacing = this.mBorderWidth;
        if (spacing == null) {
            return null;
        }
        float raw = spacing.getRaw(i);
        if (Float.isNaN(raw)) {
            return null;
        }
        return Float.valueOf(raw);
    }

    private void updatePathEffect() {
        BorderStyle borderStyle = this.mBorderStyle;
        this.mPaint.setPathEffect(borderStyle != null ? getPathEffect(borderStyle, getFullBorderWidth()) : null);
    }

    private void updatePathEffect(int i) {
        BorderStyle borderStyle = this.mBorderStyle;
        this.mPaint.setPathEffect(borderStyle != null ? getPathEffect(borderStyle, (float) i) : null);
    }

    public float getFullBorderWidth() {
        Spacing spacing = this.mBorderWidth;
        if (spacing == null || Float.isNaN(spacing.getRaw(8))) {
            return 0.0f;
        }
        return this.mBorderWidth.getRaw(8);
    }

    private void drawRectangularBackgroundWithBorders(Canvas canvas) {
        int i;
        int i2;
        int i3;
        Canvas canvas2 = canvas;
        this.mPaint.setStyle(Paint.Style.FILL);
        int multiplyColorAlpha = multiplyColorAlpha(this.mColor, this.mAlpha);
        if (Color.alpha(multiplyColorAlpha) != 0) {
            this.mPaint.setColor(multiplyColorAlpha);
            canvas2.drawRect(getBounds(), this.mPaint);
        }
        List<BackgroundImageLayer> list = this.mBackgroundImageLayers;
        if (list != null && !list.isEmpty()) {
            this.mPaint.setShader(getBackgroundImageShader());
            canvas2.drawRect(getBounds(), this.mPaint);
            this.mPaint.setShader((Shader) null);
        }
        RectF directionAwareBorderInsets = getDirectionAwareBorderInsets();
        int round = Math.round(directionAwareBorderInsets.left);
        int round2 = Math.round(directionAwareBorderInsets.top);
        int round3 = Math.round(directionAwareBorderInsets.right);
        int round4 = Math.round(directionAwareBorderInsets.bottom);
        if (round > 0 || round3 > 0 || round2 > 0 || round4 > 0) {
            Rect bounds = getBounds();
            int borderColor = getBorderColor(0);
            int borderColor2 = getBorderColor(1);
            int borderColor3 = getBorderColor(2);
            int borderColor4 = getBorderColor(3);
            int borderColor5 = getBorderColor(9);
            int borderColor6 = getBorderColor(11);
            int borderColor7 = getBorderColor(10);
            if (isBorderColorDefined(9)) {
                borderColor2 = borderColor5;
                borderColor4 = borderColor2;
            }
            if (!isBorderColorDefined(10)) {
                borderColor7 = borderColor4;
            }
            if (!isBorderColorDefined(11)) {
                borderColor6 = borderColor2;
            }
            boolean z = getLayoutDirection() == 1;
            int borderColor8 = getBorderColor(4);
            int borderColor9 = getBorderColor(5);
            if (I18nUtil.getInstance().doLeftAndRightSwapInRTL(this.mContext)) {
                if (isBorderColorDefined(4)) {
                    borderColor = borderColor8;
                }
                if (isBorderColorDefined(5)) {
                    borderColor3 = borderColor9;
                }
                int i4 = z ? borderColor3 : borderColor;
                if (!z) {
                    borderColor = borderColor3;
                }
                i = borderColor;
                i2 = i4;
            } else {
                int i5 = z ? borderColor9 : borderColor8;
                if (!z) {
                    borderColor8 = borderColor9;
                }
                boolean isBorderColorDefined = isBorderColorDefined(4);
                boolean isBorderColorDefined2 = isBorderColorDefined(5);
                boolean z2 = z ? isBorderColorDefined2 : isBorderColorDefined;
                if (!z) {
                    isBorderColorDefined = isBorderColorDefined2;
                }
                if (z2) {
                    borderColor = i5;
                }
                i2 = borderColor;
                i = isBorderColorDefined ? borderColor8 : borderColor3;
            }
            int i6 = bounds.left;
            int i7 = i2;
            int i8 = bounds.top;
            int i9 = round3;
            int i10 = i6;
            int fastBorderCompatibleColorOrZero = fastBorderCompatibleColorOrZero(round, round2, round3, round4, i2, borderColor6, i, borderColor7);
            if (fastBorderCompatibleColorOrZero == 0) {
                this.mPaint.setAntiAlias(false);
                int width = bounds.width();
                int height = bounds.height();
                if (round > 0) {
                    float f = (float) i10;
                    float f2 = (float) (i10 + round);
                    int i11 = i8 + height;
                    i3 = i8;
                    drawQuadrilateral(canvas, i7, f, (float) i8, f2, (float) (i8 + round2), f2, (float) (i11 - round4), f, (float) i11);
                } else {
                    i3 = i8;
                }
                if (round2 > 0) {
                    float f3 = (float) i3;
                    float f4 = (float) (i3 + round2);
                    int i12 = i10 + width;
                    drawQuadrilateral(canvas, borderColor6, (float) i10, f3, (float) (i10 + round), f4, (float) (i12 - i9), f4, (float) i12, f3);
                }
                if (i9 > 0) {
                    int i13 = i10 + width;
                    float f5 = (float) i13;
                    int i14 = i3 + height;
                    float f6 = (float) (i13 - i9);
                    drawQuadrilateral(canvas, i, f5, (float) i3, f5, (float) i14, f6, (float) (i14 - round4), f6, (float) (i3 + round2));
                }
                if (round4 > 0) {
                    int i15 = i3 + height;
                    float f7 = (float) i15;
                    int i16 = i10 + width;
                    float f8 = (float) (i15 - round4);
                    drawQuadrilateral(canvas, borderColor7, (float) i10, f7, (float) i16, f7, (float) (i16 - i9), f8, (float) (i10 + round), f8);
                }
                this.mPaint.setAntiAlias(true);
            } else if (Color.alpha(fastBorderCompatibleColorOrZero) != 0) {
                int i17 = bounds.right;
                int i18 = bounds.bottom;
                this.mPaint.setColor(fastBorderCompatibleColorOrZero);
                this.mPaint.setStyle(Paint.Style.STROKE);
                if (round > 0) {
                    this.mPathForSingleBorder.reset();
                    int round5 = Math.round(directionAwareBorderInsets.left);
                    updatePathEffect(round5);
                    this.mPaint.setStrokeWidth((float) round5);
                    float f9 = (float) (i10 + (round5 / 2));
                    this.mPathForSingleBorder.moveTo(f9, (float) i8);
                    this.mPathForSingleBorder.lineTo(f9, (float) i18);
                    canvas2.drawPath(this.mPathForSingleBorder, this.mPaint);
                }
                if (round2 > 0) {
                    this.mPathForSingleBorder.reset();
                    int round6 = Math.round(directionAwareBorderInsets.top);
                    updatePathEffect(round6);
                    this.mPaint.setStrokeWidth((float) round6);
                    float f10 = (float) (i8 + (round6 / 2));
                    this.mPathForSingleBorder.moveTo((float) i10, f10);
                    this.mPathForSingleBorder.lineTo((float) i17, f10);
                    canvas2.drawPath(this.mPathForSingleBorder, this.mPaint);
                }
                if (i9 > 0) {
                    this.mPathForSingleBorder.reset();
                    int round7 = Math.round(directionAwareBorderInsets.right);
                    updatePathEffect(round7);
                    this.mPaint.setStrokeWidth((float) round7);
                    float f11 = (float) (i17 - (round7 / 2));
                    this.mPathForSingleBorder.moveTo(f11, (float) i8);
                    this.mPathForSingleBorder.lineTo(f11, (float) i18);
                    canvas2.drawPath(this.mPathForSingleBorder, this.mPaint);
                }
                if (round4 > 0) {
                    this.mPathForSingleBorder.reset();
                    int round8 = Math.round(directionAwareBorderInsets.bottom);
                    updatePathEffect(round8);
                    this.mPaint.setStrokeWidth((float) round8);
                    float f12 = (float) (i18 - (round8 / 2));
                    this.mPathForSingleBorder.moveTo((float) i10, f12);
                    this.mPathForSingleBorder.lineTo((float) i17, f12);
                    canvas2.drawPath(this.mPathForSingleBorder, this.mPaint);
                }
            }
        }
    }

    private void drawQuadrilateral(Canvas canvas, int i, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        if (i != 0) {
            if (this.mPathForBorder == null) {
                this.mPathForBorder = new Path();
            }
            this.mPaint.setColor(i);
            this.mPathForBorder.reset();
            this.mPathForBorder.moveTo(f, f2);
            this.mPathForBorder.lineTo(f3, f4);
            this.mPathForBorder.lineTo(f5, f6);
            this.mPathForBorder.lineTo(f7, f8);
            this.mPathForBorder.lineTo(f, f2);
            canvas.drawPath(this.mPathForBorder, this.mPaint);
        }
    }

    private boolean isBorderColorDefined(int i) {
        Spacing spacing = this.mBorderRGB;
        float f = Float.NaN;
        float f2 = spacing != null ? spacing.get(i) : Float.NaN;
        Spacing spacing2 = this.mBorderAlpha;
        if (spacing2 != null) {
            f = spacing2.get(i);
        }
        return !Float.isNaN(f2) && !Float.isNaN(f);
    }

    public int getBorderColor(int i) {
        Spacing spacing = this.mBorderRGB;
        float f = spacing != null ? spacing.get(i) : 0.0f;
        Spacing spacing2 = this.mBorderAlpha;
        return colorFromAlphaAndRGBComponents(spacing2 != null ? spacing2.get(i) : 255.0f, f);
    }

    public RectF getDirectionAwareBorderInsets() {
        float borderWidthOrDefaultTo = getBorderWidthOrDefaultTo(0.0f, 8);
        boolean z = true;
        float borderWidthOrDefaultTo2 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 1);
        float borderWidthOrDefaultTo3 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 3);
        float borderWidthOrDefaultTo4 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 0);
        float borderWidthOrDefaultTo5 = getBorderWidthOrDefaultTo(borderWidthOrDefaultTo, 2);
        if (this.mBorderWidth != null) {
            if (getLayoutDirection() != 1) {
                z = false;
            }
            float raw = this.mBorderWidth.getRaw(4);
            float raw2 = this.mBorderWidth.getRaw(5);
            if (I18nUtil.getInstance().doLeftAndRightSwapInRTL(this.mContext)) {
                if (!Float.isNaN(raw)) {
                    borderWidthOrDefaultTo4 = raw;
                }
                if (!Float.isNaN(raw2)) {
                    borderWidthOrDefaultTo5 = raw2;
                }
                float f = z ? borderWidthOrDefaultTo5 : borderWidthOrDefaultTo4;
                if (z) {
                    borderWidthOrDefaultTo5 = borderWidthOrDefaultTo4;
                }
                borderWidthOrDefaultTo4 = f;
            } else {
                float f2 = z ? raw2 : raw;
                if (!z) {
                    raw = raw2;
                }
                if (!Float.isNaN(f2)) {
                    borderWidthOrDefaultTo4 = f2;
                }
                if (!Float.isNaN(raw)) {
                    borderWidthOrDefaultTo5 = raw;
                }
            }
        }
        return new RectF(borderWidthOrDefaultTo4, borderWidthOrDefaultTo2, borderWidthOrDefaultTo5, borderWidthOrDefaultTo3);
    }

    private Shader getBackgroundImageShader() {
        List<BackgroundImageLayer> list = this.mBackgroundImageLayers;
        ComposeShader composeShader = null;
        if (list == null) {
            return null;
        }
        for (BackgroundImageLayer shader : list) {
            Shader shader2 = shader.getShader(getBounds());
            if (shader2 != null) {
                if (composeShader == null) {
                    composeShader = shader2;
                } else {
                    composeShader = new ComposeShader(shader2, composeShader, PorterDuff.Mode.SRC_OVER);
                }
            }
        }
        return composeShader;
    }
}
