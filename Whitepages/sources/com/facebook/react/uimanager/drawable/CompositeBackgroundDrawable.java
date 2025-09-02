package com.facebook.react.uimanager.drawable;

import android.content.Context;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.style.BorderInsets;
import com.facebook.react.uimanager.style.BorderRadiusStyle;
import com.facebook.react.uimanager.style.ComputedBorderRadius;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class CompositeBackgroundDrawable extends LayerDrawable {
    private static final int BACKGROUND_ID = 3;
    private static final int BORDER_ID = 4;
    private static final int CSS_BACKGROUND_ID = 2;
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int FEEDBACK_UNDERLAY_ID = 5;
    private static final int INNER_SHADOWS_ID = 6;
    private static final int ORIGINAL_BACKGROUND_ID = 0;
    private static final int OUTER_SHADOWS_ID = 1;
    private static final int OUTLINE_ID = 7;
    /* access modifiers changed from: private */
    public BackgroundDrawable background;
    /* access modifiers changed from: private */
    public BorderDrawable border;
    private BorderInsets borderInsets;
    private BorderRadiusStyle borderRadius;
    private final Context context;
    private final CSSBackgroundDrawable cssBackground;
    /* access modifiers changed from: private */
    public Drawable feedbackUnderlay;
    /* access modifiers changed from: private */
    public LayerDrawable innerShadows;
    private final Drawable originalBackground;
    /* access modifiers changed from: private */
    public LayerDrawable outerShadows;
    /* access modifiers changed from: private */
    public OutlineDrawable outline;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ CompositeBackgroundDrawable(android.content.Context r10, android.graphics.drawable.Drawable r11, android.graphics.drawable.LayerDrawable r12, com.facebook.react.uimanager.drawable.CSSBackgroundDrawable r13, com.facebook.react.uimanager.drawable.BackgroundDrawable r14, com.facebook.react.uimanager.drawable.BorderDrawable r15, android.graphics.drawable.Drawable r16, android.graphics.drawable.LayerDrawable r17, com.facebook.react.uimanager.drawable.OutlineDrawable r18, int r19, kotlin.jvm.internal.DefaultConstructorMarker r20) {
        /*
            r9 = this;
            r0 = r19
            r1 = r0 & 2
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r1 = r2
            goto L_0x000a
        L_0x0009:
            r1 = r11
        L_0x000a:
            r3 = r0 & 4
            if (r3 == 0) goto L_0x0010
            r3 = r2
            goto L_0x0011
        L_0x0010:
            r3 = r12
        L_0x0011:
            r4 = r0 & 8
            if (r4 == 0) goto L_0x0017
            r4 = r2
            goto L_0x0018
        L_0x0017:
            r4 = r13
        L_0x0018:
            r5 = r0 & 16
            if (r5 == 0) goto L_0x001e
            r5 = r2
            goto L_0x001f
        L_0x001e:
            r5 = r14
        L_0x001f:
            r6 = r0 & 32
            if (r6 == 0) goto L_0x0025
            r6 = r2
            goto L_0x0026
        L_0x0025:
            r6 = r15
        L_0x0026:
            r7 = r0 & 64
            if (r7 == 0) goto L_0x002c
            r7 = r2
            goto L_0x002e
        L_0x002c:
            r7 = r16
        L_0x002e:
            r8 = r0 & 128(0x80, float:1.794E-43)
            if (r8 == 0) goto L_0x0034
            r8 = r2
            goto L_0x0036
        L_0x0034:
            r8 = r17
        L_0x0036:
            r0 = r0 & 256(0x100, float:3.59E-43)
            if (r0 == 0) goto L_0x003b
            goto L_0x003d
        L_0x003b:
            r2 = r18
        L_0x003d:
            r11 = r9
            r12 = r10
            r13 = r1
            r14 = r3
            r15 = r4
            r16 = r5
            r17 = r6
            r18 = r7
            r19 = r8
            r20 = r2
            r11.<init>(r12, r13, r14, r15, r16, r17, r18, r19, r20)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.drawable.CompositeBackgroundDrawable.<init>(android.content.Context, android.graphics.drawable.Drawable, android.graphics.drawable.LayerDrawable, com.facebook.react.uimanager.drawable.CSSBackgroundDrawable, com.facebook.react.uimanager.drawable.BackgroundDrawable, com.facebook.react.uimanager.drawable.BorderDrawable, android.graphics.drawable.Drawable, android.graphics.drawable.LayerDrawable, com.facebook.react.uimanager.drawable.OutlineDrawable, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final Drawable getOriginalBackground() {
        return this.originalBackground;
    }

    public final CSSBackgroundDrawable getCssBackground() {
        return this.cssBackground;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CompositeBackgroundDrawable(Context context2, Drawable drawable, LayerDrawable layerDrawable, CSSBackgroundDrawable cSSBackgroundDrawable, BackgroundDrawable backgroundDrawable, BorderDrawable borderDrawable, Drawable drawable2, LayerDrawable layerDrawable2, OutlineDrawable outlineDrawable) {
        super(new Drawable[0]);
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
        this.originalBackground = drawable;
        this.cssBackground = cSSBackgroundDrawable;
        this.outerShadows = layerDrawable;
        this.background = backgroundDrawable;
        this.border = borderDrawable;
        this.feedbackUnderlay = drawable2;
        this.innerShadows = layerDrawable2;
        this.outline = outlineDrawable;
        setPaddingMode(1);
        addLayer(drawable, 0);
        addLayer(layerDrawable, 1);
        addLayer(cSSBackgroundDrawable, 2);
        addLayer(backgroundDrawable, 3);
        addLayer(borderDrawable, 4);
        addLayer(drawable2, 5);
        addLayer(layerDrawable2, 6);
        addLayer(outlineDrawable, 7);
    }

    public final LayerDrawable getOuterShadows() {
        return this.outerShadows;
    }

    public final BackgroundDrawable getBackground() {
        return this.background;
    }

    public final BorderDrawable getBorder() {
        return this.border;
    }

    public final Drawable getFeedbackUnderlay() {
        return this.feedbackUnderlay;
    }

    public final LayerDrawable getInnerShadows() {
        return this.innerShadows;
    }

    public final OutlineDrawable getOutline() {
        return this.outline;
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

    public final CompositeBackgroundDrawable withNewCssBackground(CSSBackgroundDrawable cSSBackgroundDrawable) {
        CompositeBackgroundDrawable compositeBackgroundDrawable = new CompositeBackgroundDrawable(this.context, this.originalBackground, this.outerShadows, cSSBackgroundDrawable, this.background, this.border, this.feedbackUnderlay, this.innerShadows, this.outline);
        compositeBackgroundDrawable.borderInsets = this.borderInsets;
        compositeBackgroundDrawable.borderRadius = this.borderRadius;
        return compositeBackgroundDrawable;
    }

    public final CompositeBackgroundDrawable withNewOuterShadow(LayerDrawable layerDrawable) {
        return withNewLayer(layerDrawable, 1, new CompositeBackgroundDrawable$withNewOuterShadow$1(new CompositeBackgroundDrawable$withNewOuterShadow$2(this)));
    }

    public final CompositeBackgroundDrawable withNewBackground(BackgroundDrawable backgroundDrawable) {
        return withNewLayer(backgroundDrawable, 3, new CompositeBackgroundDrawable$withNewBackground$1(new CompositeBackgroundDrawable$withNewBackground$2(this)));
    }

    public final CompositeBackgroundDrawable withNewBorder(BorderDrawable borderDrawable) {
        return withNewLayer(borderDrawable, 4, new CompositeBackgroundDrawable$withNewBorder$1(new CompositeBackgroundDrawable$withNewBorder$2(this)));
    }

    public final CompositeBackgroundDrawable withNewFeedbackUnderlay(Drawable drawable) {
        return withNewLayer(drawable, 5, new CompositeBackgroundDrawable$withNewFeedbackUnderlay$1(new CompositeBackgroundDrawable$withNewFeedbackUnderlay$2(this)));
    }

    public final CompositeBackgroundDrawable withNewInnerShadow(LayerDrawable layerDrawable) {
        return withNewLayer(layerDrawable, 6, new CompositeBackgroundDrawable$withNewInnerShadow$1(new CompositeBackgroundDrawable$withNewInnerShadow$2(this)));
    }

    public final CompositeBackgroundDrawable withNewOutline(OutlineDrawable outlineDrawable) {
        return withNewLayer(outlineDrawable, 7, new CompositeBackgroundDrawable$withNewOutline$1(new CompositeBackgroundDrawable$withNewOutline$2(this)));
    }

    private final boolean updateLayer(Drawable drawable, int i) {
        if (drawable == null) {
            return findDrawableByLayerId(i) == null;
        }
        if (findDrawableByLayerId(i) == null) {
            insertNewLayer(drawable, i);
        } else {
            setDrawableByLayerId(i, drawable);
        }
        invalidateSelf();
        return true;
    }

    private final void insertNewLayer(Drawable drawable, int i) {
        if (drawable != null) {
            if (getNumberOfLayers() == 0) {
                addLayer(drawable, i);
                return;
            }
            int numberOfLayers = getNumberOfLayers();
            int i2 = 0;
            while (i2 < numberOfLayers) {
                if (i < getId(i2)) {
                    Drawable drawable2 = getDrawable(i2);
                    Intrinsics.checkNotNullExpressionValue(drawable2, "getDrawable(...)");
                    int id = getId(i2);
                    setDrawable(i2, drawable);
                    setId(i2, i);
                    insertNewLayer(drawable2, id);
                    return;
                } else if (i2 == getNumberOfLayers() - 1) {
                    addLayer(drawable, i);
                    return;
                } else {
                    i2++;
                }
            }
        }
    }

    private final void addLayer(Drawable drawable, int i) {
        if (drawable != null) {
            addLayer(drawable);
            drawable.setCallback(this);
            setId(getNumberOfLayers() - 1, i);
            invalidateSelf();
        }
    }

    public void getOutline(Outline outline2) {
        Outline outline3 = outline2;
        Intrinsics.checkNotNullParameter(outline3, "outline");
        BorderRadiusStyle borderRadiusStyle = this.borderRadius;
        if (borderRadiusStyle == null || !borderRadiusStyle.hasRoundedBorders()) {
            outline3.setRect(getBounds());
            return;
        }
        Path path = new Path();
        BorderRadiusStyle borderRadiusStyle2 = this.borderRadius;
        RectF rectF = null;
        ComputedBorderRadius resolve = borderRadiusStyle2 != null ? borderRadiusStyle2.resolve(getLayoutDirection(), this.context, (float) getBounds().width(), (float) getBounds().height()) : null;
        BorderInsets borderInsets2 = this.borderInsets;
        if (borderInsets2 != null) {
            rectF = borderInsets2.resolve(getLayoutDirection(), this.context);
        }
        if (resolve != null) {
            RectF rectF2 = new RectF(getBounds());
            PixelUtil pixelUtil = PixelUtil.INSTANCE;
            path.addRoundRect(rectF2, new float[]{pixelUtil.dpToPx(resolve.getTopLeft().getHorizontal() + (rectF != null ? rectF.left : 0.0f)), pixelUtil.dpToPx(resolve.getTopLeft().getVertical() + (rectF != null ? rectF.top : 0.0f)), pixelUtil.dpToPx(resolve.getTopRight().getHorizontal() + (rectF != null ? rectF.right : 0.0f)), pixelUtil.dpToPx(resolve.getTopRight().getVertical() + (rectF != null ? rectF.top : 0.0f)), pixelUtil.dpToPx(resolve.getBottomRight().getHorizontal() + (rectF != null ? rectF.right : 0.0f)), pixelUtil.dpToPx(resolve.getBottomRight().getVertical() + (rectF != null ? rectF.bottom : 0.0f)), pixelUtil.dpToPx(resolve.getBottomLeft().getHorizontal() + (rectF != null ? rectF.left : 0.0f)), pixelUtil.dpToPx(resolve.getBottomLeft().getVertical() + (rectF != null ? rectF.bottom : 0.0f))}, Path.Direction.CW);
        }
        if (Build.VERSION.SDK_INT >= 30) {
            outline3.setPath(path);
        } else {
            outline3.setConvexPath(path);
        }
    }

    private final <T extends Drawable> CompositeBackgroundDrawable withNewLayer(T t, int i, Function1 function1) {
        function1.invoke(t);
        if (ReactNativeFeatureFlags.enableNewBackgroundAndBorderDrawables() && updateLayer(t, i)) {
            return this;
        }
        CompositeBackgroundDrawable compositeBackgroundDrawable = new CompositeBackgroundDrawable(this.context, this.originalBackground, this.outerShadows, this.cssBackground, this.background, this.border, this.feedbackUnderlay, this.innerShadows, this.outline);
        compositeBackgroundDrawable.borderInsets = this.borderInsets;
        compositeBackgroundDrawable.borderRadius = this.borderRadius;
        return compositeBackgroundDrawable;
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
