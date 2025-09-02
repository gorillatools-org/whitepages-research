package com.facebook.react.uimanager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.uimanager.common.ViewUtil;
import com.facebook.react.uimanager.drawable.BackgroundDrawable;
import com.facebook.react.uimanager.drawable.BorderDrawable;
import com.facebook.react.uimanager.drawable.CSSBackgroundDrawable;
import com.facebook.react.uimanager.drawable.CompositeBackgroundDrawable;
import com.facebook.react.uimanager.drawable.InsetBoxShadowDrawable;
import com.facebook.react.uimanager.drawable.OutlineDrawable;
import com.facebook.react.uimanager.drawable.OutsetBoxShadowDrawable;
import com.facebook.react.uimanager.style.BackgroundImageLayer;
import com.facebook.react.uimanager.style.BorderInsets;
import com.facebook.react.uimanager.style.BorderRadiusProp;
import com.facebook.react.uimanager.style.BorderRadiusStyle;
import com.facebook.react.uimanager.style.BorderStyle;
import com.facebook.react.uimanager.style.BoxShadow;
import com.facebook.react.uimanager.style.ComputedBorderRadius;
import com.facebook.react.uimanager.style.CornerRadii;
import com.facebook.react.uimanager.style.LogicalEdge;
import com.facebook.react.uimanager.style.OutlineStyle;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

public final class BackgroundStyleApplicator {
    public static final BackgroundStyleApplicator INSTANCE = new BackgroundStyleApplicator();

    private BackgroundStyleApplicator() {
    }

    public static final void setBackgroundColor(View view, Integer num) {
        Intrinsics.checkNotNullParameter(view, "view");
        if ((num != null && num.intValue() != 0) || (view.getBackground() instanceof CompositeBackgroundDrawable)) {
            int i = 0;
            if (ReactNativeFeatureFlags.enableNewBackgroundAndBorderDrawables()) {
                BackgroundDrawable ensureBackgroundDrawable = INSTANCE.ensureBackgroundDrawable(view);
                if (num != null) {
                    i = num.intValue();
                }
                ensureBackgroundDrawable.setBackgroundColor(i);
                return;
            }
            CSSBackgroundDrawable ensureCSSBackground = INSTANCE.ensureCSSBackground(view);
            if (num != null) {
                i = num.intValue();
            }
            ensureCSSBackground.setColor(i);
        }
    }

    public static final void setBackgroundImage(View view, List<BackgroundImageLayer> list) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (ReactNativeFeatureFlags.enableNewBackgroundAndBorderDrawables()) {
            INSTANCE.ensureBackgroundDrawable(view).setBackgroundImageLayers(list);
        } else {
            INSTANCE.ensureCSSBackground(view).setBackgroundImage(list);
        }
    }

    public static final Integer getBackgroundColor(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (ReactNativeFeatureFlags.enableNewBackgroundAndBorderDrawables()) {
            BackgroundDrawable background = INSTANCE.getBackground(view);
            if (background != null) {
                return Integer.valueOf(background.getBackgroundColor());
            }
            return null;
        }
        CSSBackgroundDrawable cSSBackground = INSTANCE.getCSSBackground(view);
        if (cSSBackground != null) {
            return Integer.valueOf(cSSBackground.getColor());
        }
        return null;
    }

    public static final void setBorderWidth(View view, LogicalEdge logicalEdge, Float f) {
        LayerDrawable innerShadows;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(logicalEdge, "edge");
        BackgroundStyleApplicator backgroundStyleApplicator = INSTANCE;
        CompositeBackgroundDrawable ensureCompositeBackgroundDrawable = backgroundStyleApplicator.ensureCompositeBackgroundDrawable(view);
        BorderInsets borderInsets = ensureCompositeBackgroundDrawable.getBorderInsets();
        if (borderInsets == null) {
            borderInsets = new BorderInsets();
        }
        ensureCompositeBackgroundDrawable.setBorderInsets(borderInsets);
        BorderInsets borderInsets2 = ensureCompositeBackgroundDrawable.getBorderInsets();
        if (borderInsets2 != null) {
            borderInsets2.setBorderWidth(logicalEdge, f);
        }
        float f2 = Float.NaN;
        if (ReactNativeFeatureFlags.enableNewBackgroundAndBorderDrawables()) {
            BorderDrawable ensureBorderDrawable = backgroundStyleApplicator.ensureBorderDrawable(view);
            int spacingType = logicalEdge.toSpacingType();
            if (f != null) {
                f2 = PixelUtil.INSTANCE.dpToPx(f.floatValue());
            }
            ensureBorderDrawable.setBorderWidth(spacingType, f2);
            BackgroundDrawable background = ensureCompositeBackgroundDrawable.getBackground();
            if (background != null) {
                background.setBorderInsets(ensureCompositeBackgroundDrawable.getBorderInsets());
            }
            BorderDrawable border = ensureCompositeBackgroundDrawable.getBorder();
            if (border != null) {
                border.setBorderInsets(ensureCompositeBackgroundDrawable.getBorderInsets());
            }
            BackgroundDrawable background2 = ensureCompositeBackgroundDrawable.getBackground();
            if (background2 != null) {
                background2.invalidateSelf();
            }
            BorderDrawable border2 = ensureCompositeBackgroundDrawable.getBorder();
            if (border2 != null) {
                border2.invalidateSelf();
            }
        } else {
            CSSBackgroundDrawable ensureCSSBackground = backgroundStyleApplicator.ensureCSSBackground(view);
            int spacingType2 = logicalEdge.toSpacingType();
            if (f != null) {
                f2 = PixelUtil.INSTANCE.dpToPx(f.floatValue());
            }
            ensureCSSBackground.setBorderWidth(spacingType2, f2);
        }
        BorderInsets borderInsets3 = ensureCompositeBackgroundDrawable.getBorderInsets();
        if (borderInsets3 == null) {
            borderInsets3 = new BorderInsets();
        }
        ensureCompositeBackgroundDrawable.setBorderInsets(borderInsets3);
        BorderInsets borderInsets4 = ensureCompositeBackgroundDrawable.getBorderInsets();
        if (borderInsets4 != null) {
            borderInsets4.setBorderWidth(logicalEdge, f);
        }
        if (Build.VERSION.SDK_INT >= 29 && (innerShadows = ensureCompositeBackgroundDrawable.getInnerShadows()) != null) {
            int numberOfLayers = innerShadows.getNumberOfLayers();
            for (int i = 0; i < numberOfLayers; i++) {
                Drawable drawable = innerShadows.getDrawable(i);
                Intrinsics.checkNotNull(drawable, "null cannot be cast to non-null type com.facebook.react.uimanager.drawable.InsetBoxShadowDrawable");
                InsetBoxShadowDrawable insetBoxShadowDrawable = (InsetBoxShadowDrawable) drawable;
                insetBoxShadowDrawable.setBorderInsets(ensureCompositeBackgroundDrawable.getBorderInsets());
                insetBoxShadowDrawable.invalidateSelf();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0019, code lost:
        r2 = r2.getBorderWidth();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Float getBorderWidth(android.view.View r2, com.facebook.react.uimanager.style.LogicalEdge r3) {
        /*
            java.lang.String r0 = "view"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "edge"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            boolean r0 = com.facebook.react.internal.featureflags.ReactNativeFeatureFlags.enableNewBackgroundAndBorderDrawables()
            r1 = 0
            if (r0 == 0) goto L_0x0049
            com.facebook.react.uimanager.BackgroundStyleApplicator r0 = INSTANCE
            com.facebook.react.uimanager.drawable.BorderDrawable r2 = r0.getBorder(r2)
            if (r2 == 0) goto L_0x002c
            com.facebook.react.uimanager.Spacing r2 = r2.getBorderWidth()
            if (r2 == 0) goto L_0x002c
            int r3 = r3.toSpacingType()
            float r2 = r2.getRaw(r3)
            java.lang.Float r2 = java.lang.Float.valueOf(r2)
            goto L_0x002d
        L_0x002c:
            r2 = r1
        L_0x002d:
            if (r2 == 0) goto L_0x0076
            float r3 = r2.floatValue()
            boolean r3 = java.lang.Float.isNaN(r3)
            if (r3 == 0) goto L_0x003a
            goto L_0x0076
        L_0x003a:
            com.facebook.react.uimanager.PixelUtil r3 = com.facebook.react.uimanager.PixelUtil.INSTANCE
            float r2 = r2.floatValue()
            float r2 = r3.pxToDp((float) r2)
            java.lang.Float r1 = java.lang.Float.valueOf(r2)
            goto L_0x0076
        L_0x0049:
            com.facebook.react.uimanager.BackgroundStyleApplicator r0 = INSTANCE
            com.facebook.react.uimanager.drawable.CSSBackgroundDrawable r2 = r0.getCSSBackground(r2)
            if (r2 == 0) goto L_0x005a
            int r3 = r3.toSpacingType()
            java.lang.Float r2 = r2.getBorderWidth(r3)
            goto L_0x005b
        L_0x005a:
            r2 = r1
        L_0x005b:
            if (r2 == 0) goto L_0x0076
            float r3 = r2.floatValue()
            boolean r3 = java.lang.Float.isNaN(r3)
            if (r3 == 0) goto L_0x0068
            goto L_0x0076
        L_0x0068:
            com.facebook.react.uimanager.PixelUtil r3 = com.facebook.react.uimanager.PixelUtil.INSTANCE
            float r2 = r2.floatValue()
            float r2 = r3.pxToDp((float) r2)
            java.lang.Float r1 = java.lang.Float.valueOf(r2)
        L_0x0076:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.BackgroundStyleApplicator.getBorderWidth(android.view.View, com.facebook.react.uimanager.style.LogicalEdge):java.lang.Float");
    }

    public static final void setBorderColor(View view, LogicalEdge logicalEdge, Integer num) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(logicalEdge, "edge");
        if (ReactNativeFeatureFlags.enableNewBackgroundAndBorderDrawables()) {
            INSTANCE.ensureBorderDrawable(view).setBorderColor(logicalEdge, num);
        } else {
            INSTANCE.ensureCSSBackground(view).setBorderColor(logicalEdge.toSpacingType(), num);
        }
    }

    public static final Integer getBorderColor(View view, LogicalEdge logicalEdge) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(logicalEdge, "edge");
        if (ReactNativeFeatureFlags.enableNewBackgroundAndBorderDrawables()) {
            BorderDrawable border = INSTANCE.getBorder(view);
            if (border != null) {
                return Integer.valueOf(border.getBorderColor(logicalEdge));
            }
            return null;
        }
        CSSBackgroundDrawable cSSBackground = INSTANCE.getCSSBackground(view);
        if (cSSBackground != null) {
            return Integer.valueOf(cSSBackground.getBorderColor(logicalEdge.toSpacingType()));
        }
        return null;
    }

    public static final void setBorderRadius(View view, BorderRadiusProp borderRadiusProp, LengthPercentage lengthPercentage) {
        LayerDrawable innerShadows;
        LayerDrawable outerShadows;
        View view2 = view;
        BorderRadiusProp borderRadiusProp2 = borderRadiusProp;
        LengthPercentage lengthPercentage2 = lengthPercentage;
        Intrinsics.checkNotNullParameter(view2, "view");
        Intrinsics.checkNotNullParameter(borderRadiusProp2, "corner");
        BackgroundStyleApplicator backgroundStyleApplicator = INSTANCE;
        CompositeBackgroundDrawable ensureCompositeBackgroundDrawable = backgroundStyleApplicator.ensureCompositeBackgroundDrawable(view2);
        BorderRadiusStyle borderRadius = ensureCompositeBackgroundDrawable.getBorderRadius();
        if (borderRadius == null) {
            borderRadius = new BorderRadiusStyle((LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, 8191, (DefaultConstructorMarker) null);
        }
        ensureCompositeBackgroundDrawable.setBorderRadius(borderRadius);
        BorderRadiusStyle borderRadius2 = ensureCompositeBackgroundDrawable.getBorderRadius();
        if (borderRadius2 != null) {
            borderRadius2.set(borderRadiusProp2, lengthPercentage2);
        }
        if (ReactNativeFeatureFlags.enableNewBackgroundAndBorderDrawables()) {
            if (view2 instanceof ImageView) {
                backgroundStyleApplicator.ensureBackgroundDrawable(view2);
            }
            BackgroundDrawable background = ensureCompositeBackgroundDrawable.getBackground();
            if (background != null) {
                background.setBorderRadius(ensureCompositeBackgroundDrawable.getBorderRadius());
            }
            BorderDrawable border = ensureCompositeBackgroundDrawable.getBorder();
            if (border != null) {
                border.setBorderRadius(ensureCompositeBackgroundDrawable.getBorderRadius());
            }
            BackgroundDrawable background2 = ensureCompositeBackgroundDrawable.getBackground();
            if (background2 != null) {
                background2.invalidateSelf();
            }
            BorderDrawable border2 = ensureCompositeBackgroundDrawable.getBorder();
            if (border2 != null) {
                border2.invalidateSelf();
            }
        } else {
            backgroundStyleApplicator.ensureCSSBackground(view2).setBorderRadius(borderRadiusProp2, lengthPercentage2);
        }
        if (Build.VERSION.SDK_INT >= 28 && (outerShadows = ensureCompositeBackgroundDrawable.getOuterShadows()) != null) {
            int numberOfLayers = outerShadows.getNumberOfLayers();
            for (int i = 0; i < numberOfLayers; i++) {
                Drawable drawable = outerShadows.getDrawable(i);
                if (drawable instanceof OutsetBoxShadowDrawable) {
                    OutsetBoxShadowDrawable outsetBoxShadowDrawable = (OutsetBoxShadowDrawable) drawable;
                    BorderRadiusStyle borderRadius3 = outsetBoxShadowDrawable.getBorderRadius();
                    if (borderRadius3 == null) {
                        borderRadius3 = new BorderRadiusStyle((LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, 8191, (DefaultConstructorMarker) null);
                    }
                    outsetBoxShadowDrawable.setBorderRadius(borderRadius3);
                    BorderRadiusStyle borderRadius4 = outsetBoxShadowDrawable.getBorderRadius();
                    if (borderRadius4 != null) {
                        borderRadius4.set(borderRadiusProp2, lengthPercentage2);
                    }
                    outsetBoxShadowDrawable.invalidateSelf();
                }
            }
        }
        if (Build.VERSION.SDK_INT >= 29 && (innerShadows = ensureCompositeBackgroundDrawable.getInnerShadows()) != null) {
            int numberOfLayers2 = innerShadows.getNumberOfLayers();
            for (int i2 = 0; i2 < numberOfLayers2; i2++) {
                Drawable drawable2 = innerShadows.getDrawable(i2);
                if (drawable2 instanceof InsetBoxShadowDrawable) {
                    InsetBoxShadowDrawable insetBoxShadowDrawable = (InsetBoxShadowDrawable) drawable2;
                    BorderRadiusStyle borderRadius5 = insetBoxShadowDrawable.getBorderRadius();
                    if (borderRadius5 == null) {
                        borderRadius5 = new BorderRadiusStyle((LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, 8191, (DefaultConstructorMarker) null);
                    }
                    insetBoxShadowDrawable.setBorderRadius(borderRadius5);
                    BorderRadiusStyle borderRadius6 = insetBoxShadowDrawable.getBorderRadius();
                    if (borderRadius6 != null) {
                        borderRadius6.set(borderRadiusProp2, lengthPercentage2);
                    }
                    insetBoxShadowDrawable.invalidateSelf();
                }
            }
        }
        OutlineDrawable outline = ensureCompositeBackgroundDrawable.getOutline();
        if (outline != null) {
            outline.setBorderRadius(ensureCompositeBackgroundDrawable.getBorderRadius());
        }
        ensureCompositeBackgroundDrawable.invalidateSelf();
    }

    public static final LengthPercentage getBorderRadius(View view, BorderRadiusProp borderRadiusProp) {
        BorderRadiusStyle borderRadius;
        BorderRadiusStyle borderRadius2;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(borderRadiusProp, "corner");
        if (ReactNativeFeatureFlags.enableNewBackgroundAndBorderDrawables()) {
            CompositeBackgroundDrawable compositeBackgroundDrawable = INSTANCE.getCompositeBackgroundDrawable(view);
            if (compositeBackgroundDrawable == null || (borderRadius2 = compositeBackgroundDrawable.getBorderRadius()) == null) {
                return null;
            }
            return borderRadius2.get(borderRadiusProp);
        }
        CSSBackgroundDrawable cSSBackground = INSTANCE.getCSSBackground(view);
        if (cSSBackground == null || (borderRadius = cSSBackground.getBorderRadius()) == null) {
            return null;
        }
        return borderRadius.get(borderRadiusProp);
    }

    public static final void setBorderStyle(View view, BorderStyle borderStyle) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (ReactNativeFeatureFlags.enableNewBackgroundAndBorderDrawables()) {
            INSTANCE.ensureBorderDrawable(view).setBorderStyle(borderStyle);
        } else {
            INSTANCE.ensureCSSBackground(view).setBorderStyle(borderStyle);
        }
    }

    public static final BorderStyle getBorderStyle(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (ReactNativeFeatureFlags.enableNewBackgroundAndBorderDrawables()) {
            BorderDrawable border = INSTANCE.getBorder(view);
            if (border != null) {
                return border.getBorderStyle();
            }
            return null;
        }
        CSSBackgroundDrawable cSSBackground = INSTANCE.getCSSBackground(view);
        if (cSSBackground != null) {
            return cSSBackground.getBorderStyle();
        }
        return null;
    }

    public static final void setOutlineColor(View view, Integer num) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (ViewUtil.getUIManagerType(view) == 2) {
            OutlineDrawable ensureOutlineDrawable = INSTANCE.ensureOutlineDrawable(view);
            if (num != null) {
                ensureOutlineDrawable.setOutlineColor(num.intValue());
            }
        }
    }

    public static final Integer getOutlineColor(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        OutlineDrawable outlineDrawable = INSTANCE.getOutlineDrawable(view);
        if (outlineDrawable != null) {
            return Integer.valueOf(outlineDrawable.getOutlineColor());
        }
        return null;
    }

    public static final void setOutlineOffset(View view, float f) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (ViewUtil.getUIManagerType(view) == 2) {
            INSTANCE.ensureOutlineDrawable(view).setOutlineOffset(PixelUtil.INSTANCE.dpToPx(f));
        }
    }

    public final Float getOutlineOffset(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        OutlineDrawable outlineDrawable = getOutlineDrawable(view);
        if (outlineDrawable != null) {
            return Float.valueOf(outlineDrawable.getOutlineOffset());
        }
        return null;
    }

    public static final void setOutlineStyle(View view, OutlineStyle outlineStyle) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (ViewUtil.getUIManagerType(view) == 2) {
            OutlineDrawable ensureOutlineDrawable = INSTANCE.ensureOutlineDrawable(view);
            if (outlineStyle != null) {
                ensureOutlineDrawable.setOutlineStyle(outlineStyle);
            }
        }
    }

    public final OutlineStyle getOutlineStyle(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        OutlineDrawable outlineDrawable = getOutlineDrawable(view);
        if (outlineDrawable != null) {
            return outlineDrawable.getOutlineStyle();
        }
        return null;
    }

    public static final void setOutlineWidth(View view, float f) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (ViewUtil.getUIManagerType(view) == 2) {
            INSTANCE.ensureOutlineDrawable(view).setOutlineWidth(PixelUtil.INSTANCE.dpToPx(f));
        }
    }

    public final Float getOutlineWidth(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        OutlineDrawable outlineDrawable = getOutlineDrawable(view);
        if (outlineDrawable != null) {
            return Float.valueOf(outlineDrawable.getOutlineOffset());
        }
        return null;
    }

    public static final void setBoxShadow(View view, List<BoxShadow> list) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(list, "shadows");
        if (ViewUtil.getUIManagerType(view) == 2) {
            CompositeBackgroundDrawable ensureCompositeBackgroundDrawable = INSTANCE.ensureCompositeBackgroundDrawable(view);
            BorderInsets borderInsets = ensureCompositeBackgroundDrawable.getBorderInsets();
            BorderRadiusStyle borderRadius = ensureCompositeBackgroundDrawable.getBorderRadius();
            LayerDrawable layerDrawable = null;
            LayerDrawable layerDrawable2 = null;
            for (BoxShadow boxShadow : CollectionsKt.asReversed(list)) {
                float offsetX = boxShadow.getOffsetX();
                float offsetY = boxShadow.getOffsetY();
                Integer color = boxShadow.getColor();
                int intValue = color != null ? color.intValue() : -16777216;
                Float blurRadius = boxShadow.getBlurRadius();
                float floatValue = blurRadius != null ? blurRadius.floatValue() : 0.0f;
                Float spreadDistance = boxShadow.getSpreadDistance();
                float floatValue2 = spreadDistance != null ? spreadDistance.floatValue() : 0.0f;
                Boolean inset = boxShadow.getInset();
                boolean booleanValue = inset != null ? inset.booleanValue() : false;
                if (booleanValue && Build.VERSION.SDK_INT >= 29) {
                    LayerDrawable layerDrawable3 = layerDrawable == null ? new LayerDrawable(new Drawable[0]) : layerDrawable;
                    Context context = view.getContext();
                    Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
                    layerDrawable3.addLayer(new InsetBoxShadowDrawable(context, intValue, offsetX, offsetY, floatValue, floatValue2, borderInsets, borderRadius));
                    layerDrawable = layerDrawable3;
                } else if (!booleanValue && Build.VERSION.SDK_INT >= 28) {
                    if (layerDrawable2 == null) {
                        layerDrawable2 = new LayerDrawable(new Drawable[0]);
                    }
                    Context context2 = view.getContext();
                    Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                    layerDrawable2.addLayer(new OutsetBoxShadowDrawable(context2, intValue, offsetX, offsetY, floatValue, floatValue2, borderRadius));
                }
            }
            BackgroundStyleApplicator backgroundStyleApplicator = INSTANCE;
            view.setBackground(backgroundStyleApplicator.ensureCompositeBackgroundDrawable(view).withNewOuterShadow(layerDrawable2));
            view.setBackground(backgroundStyleApplicator.ensureCompositeBackgroundDrawable(view).withNewInnerShadow(layerDrawable));
        }
    }

    public static final void setBoxShadow(View view, ReadableArray readableArray) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (readableArray == null) {
            setBoxShadow(view, (List<BoxShadow>) CollectionsKt.emptyList());
            return;
        }
        ArrayList arrayList = new ArrayList();
        int size = readableArray.size();
        int i = 0;
        while (i < size) {
            BoxShadow.Companion companion = BoxShadow.Companion;
            ReadableMap map = readableArray.getMap(i);
            Context context = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            BoxShadow parse = companion.parse(map, context);
            if (parse != null) {
                arrayList.add(parse);
                i++;
            } else {
                throw new IllegalStateException("Required value was null.");
            }
        }
        setBoxShadow(view, (List<BoxShadow>) arrayList);
    }

    public static final void setFeedbackUnderlay(View view, Drawable drawable) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (ReactNativeFeatureFlags.enableNewBackgroundAndBorderDrawables()) {
            INSTANCE.ensureCompositeBackgroundDrawable(view).withNewFeedbackUnderlay(drawable);
        } else {
            view.setBackground(INSTANCE.ensureCompositeBackgroundDrawable(view).withNewFeedbackUnderlay(drawable));
        }
    }

    public static final void clipToPaddingBox(View view, Canvas canvas) {
        RectF rectF;
        float f;
        float f2;
        float f3;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if (ReactNativeFeatureFlags.enableNewBackgroundAndBorderDrawables()) {
            Rect rect = new Rect();
            view.getDrawingRect(rect);
            BackgroundStyleApplicator backgroundStyleApplicator = INSTANCE;
            CompositeBackgroundDrawable ensureCompositeBackgroundDrawable = backgroundStyleApplicator.ensureCompositeBackgroundDrawable(view);
            RectF rectF2 = new RectF();
            BorderInsets borderInsets = ensureCompositeBackgroundDrawable.getBorderInsets();
            if (borderInsets != null) {
                int layoutDirection = ensureCompositeBackgroundDrawable.getLayoutDirection();
                Context context = view.getContext();
                Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
                rectF = borderInsets.resolve(layoutDirection, context);
            } else {
                rectF = null;
            }
            float f4 = (float) ensureCompositeBackgroundDrawable.getBounds().left;
            float f5 = 0.0f;
            if (rectF != null) {
                f = PixelUtil.INSTANCE.dpToPx(rectF.left);
            } else {
                f = 0.0f;
            }
            rectF2.left = f4 + f;
            float f6 = (float) ensureCompositeBackgroundDrawable.getBounds().top;
            if (rectF != null) {
                f2 = PixelUtil.INSTANCE.dpToPx(rectF.top);
            } else {
                f2 = 0.0f;
            }
            rectF2.top = f6 + f2;
            float f7 = (float) ensureCompositeBackgroundDrawable.getBounds().right;
            if (rectF != null) {
                f3 = PixelUtil.INSTANCE.dpToPx(rectF.right);
            } else {
                f3 = 0.0f;
            }
            rectF2.right = f7 - f3;
            float f8 = (float) ensureCompositeBackgroundDrawable.getBounds().bottom;
            if (rectF != null) {
                f5 = PixelUtil.INSTANCE.dpToPx(rectF.bottom);
            }
            rectF2.bottom = f8 - f5;
            BorderRadiusStyle borderRadius = ensureCompositeBackgroundDrawable.getBorderRadius();
            if (borderRadius == null || !borderRadius.hasRoundedBorders()) {
                rectF2.offset((float) rect.left, (float) rect.top);
                canvas.clipRect(rectF2);
                return;
            }
            Path createPaddingBoxPath = backgroundStyleApplicator.createPaddingBoxPath(view, ensureCompositeBackgroundDrawable, rectF2, rectF);
            createPaddingBoxPath.offset((float) rect.left, (float) rect.top);
            canvas.clipPath(createPaddingBoxPath);
            return;
        }
        Rect rect2 = new Rect();
        view.getDrawingRect(rect2);
        CSSBackgroundDrawable cSSBackground = INSTANCE.getCSSBackground(view);
        if (cSSBackground == null) {
            canvas.clipRect(rect2);
            return;
        }
        Path paddingBoxPath = cSSBackground.getPaddingBoxPath();
        if (paddingBoxPath != null) {
            paddingBoxPath.offset((float) rect2.left, (float) rect2.top);
            canvas.clipPath(paddingBoxPath);
            return;
        }
        RectF paddingBoxRect = cSSBackground.getPaddingBoxRect();
        Intrinsics.checkNotNullExpressionValue(paddingBoxRect, "getPaddingBoxRect(...)");
        paddingBoxRect.offset((float) rect2.left, (float) rect2.top);
        canvas.clipRect(paddingBoxRect);
    }

    public static final void reset(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (view.getBackground() instanceof CompositeBackgroundDrawable) {
            Drawable background = view.getBackground();
            Intrinsics.checkNotNull(background, "null cannot be cast to non-null type com.facebook.react.uimanager.drawable.CompositeBackgroundDrawable");
            view.setBackground(((CompositeBackgroundDrawable) background).getOriginalBackground());
        }
    }

    private final CompositeBackgroundDrawable ensureCompositeBackgroundDrawable(View view) {
        if (view.getBackground() instanceof CompositeBackgroundDrawable) {
            Drawable background = view.getBackground();
            Intrinsics.checkNotNull(background, "null cannot be cast to non-null type com.facebook.react.uimanager.drawable.CompositeBackgroundDrawable");
            return (CompositeBackgroundDrawable) background;
        }
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        CompositeBackgroundDrawable compositeBackgroundDrawable = new CompositeBackgroundDrawable(context, view.getBackground(), (LayerDrawable) null, (CSSBackgroundDrawable) null, (BackgroundDrawable) null, (BorderDrawable) null, (Drawable) null, (LayerDrawable) null, (OutlineDrawable) null, 508, (DefaultConstructorMarker) null);
        view.setBackground(compositeBackgroundDrawable);
        return compositeBackgroundDrawable;
    }

    private final CompositeBackgroundDrawable getCompositeBackgroundDrawable(View view) {
        Drawable background = view.getBackground();
        if (background instanceof CompositeBackgroundDrawable) {
            return (CompositeBackgroundDrawable) background;
        }
        return null;
    }

    private final CSSBackgroundDrawable ensureCSSBackground(View view) {
        CompositeBackgroundDrawable ensureCompositeBackgroundDrawable = ensureCompositeBackgroundDrawable(view);
        CSSBackgroundDrawable cssBackground = ensureCompositeBackgroundDrawable.getCssBackground();
        if (cssBackground != null) {
            return cssBackground;
        }
        CSSBackgroundDrawable cSSBackgroundDrawable = new CSSBackgroundDrawable(view.getContext());
        view.setBackground(ensureCompositeBackgroundDrawable.withNewCssBackground(cSSBackgroundDrawable));
        return cSSBackgroundDrawable;
    }

    private final BackgroundDrawable ensureBackgroundDrawable(View view) {
        CompositeBackgroundDrawable ensureCompositeBackgroundDrawable = ensureCompositeBackgroundDrawable(view);
        BackgroundDrawable background = ensureCompositeBackgroundDrawable.getBackground();
        if (background != null) {
            return background;
        }
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        BackgroundDrawable backgroundDrawable = new BackgroundDrawable(context, ensureCompositeBackgroundDrawable.getBorderRadius(), ensureCompositeBackgroundDrawable.getBorderInsets());
        view.setBackground(ensureCompositeBackgroundDrawable.withNewBackground(backgroundDrawable));
        return backgroundDrawable;
    }

    private final CSSBackgroundDrawable getCSSBackground(View view) {
        CompositeBackgroundDrawable compositeBackgroundDrawable = getCompositeBackgroundDrawable(view);
        if (compositeBackgroundDrawable != null) {
            return compositeBackgroundDrawable.getCssBackground();
        }
        return null;
    }

    private final BackgroundDrawable getBackground(View view) {
        CompositeBackgroundDrawable compositeBackgroundDrawable = getCompositeBackgroundDrawable(view);
        if (compositeBackgroundDrawable != null) {
            return compositeBackgroundDrawable.getBackground();
        }
        return null;
    }

    private final BorderDrawable getBorder(View view) {
        CompositeBackgroundDrawable compositeBackgroundDrawable = getCompositeBackgroundDrawable(view);
        if (compositeBackgroundDrawable != null) {
            return compositeBackgroundDrawable.getBorder();
        }
        return null;
    }

    private final BorderDrawable ensureBorderDrawable(View view) {
        CompositeBackgroundDrawable ensureCompositeBackgroundDrawable = ensureCompositeBackgroundDrawable(view);
        BorderDrawable border = ensureCompositeBackgroundDrawable.getBorder();
        if (border != null) {
            return border;
        }
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        BorderRadiusStyle borderRadius = ensureCompositeBackgroundDrawable.getBorderRadius();
        BorderDrawable borderDrawable = new BorderDrawable(context, new Spacing(0.0f), borderRadius, ensureCompositeBackgroundDrawable.getBorderInsets(), BorderStyle.SOLID);
        view.setBackground(ensureCompositeBackgroundDrawable.withNewBorder(borderDrawable));
        return borderDrawable;
    }

    private final OutlineDrawable ensureOutlineDrawable(View view) {
        BorderRadiusStyle borderRadius;
        CompositeBackgroundDrawable ensureCompositeBackgroundDrawable = ensureCompositeBackgroundDrawable(view);
        OutlineDrawable outline = ensureCompositeBackgroundDrawable.getOutline();
        if (outline != null) {
            return outline;
        }
        if (ReactNativeFeatureFlags.enableNewBackgroundAndBorderDrawables()) {
            borderRadius = ensureCompositeBackgroundDrawable.getBorderRadius();
        } else {
            borderRadius = ensureCSSBackground(view).getBorderRadius();
        }
        BorderRadiusStyle borderRadiusStyle = borderRadius;
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        OutlineDrawable outlineDrawable = new OutlineDrawable(context, borderRadiusStyle, -16777216, 0.0f, OutlineStyle.SOLID, 0.0f);
        view.setBackground(ensureCompositeBackgroundDrawable.withNewOutline(outlineDrawable));
        return outlineDrawable;
    }

    private final OutlineDrawable getOutlineDrawable(View view) {
        CompositeBackgroundDrawable compositeBackgroundDrawable = getCompositeBackgroundDrawable(view);
        if (compositeBackgroundDrawable != null) {
            return compositeBackgroundDrawable.getOutline();
        }
        return null;
    }

    private final float getInnerBorderRadius(Float f, Float f2) {
        return RangesKt.coerceAtLeast((f != null ? f.floatValue() : 0.0f) - (f2 != null ? f2.floatValue() : 0.0f), 0.0f);
    }

    private final Path createPaddingBoxPath(View view, CompositeBackgroundDrawable compositeBackgroundDrawable, RectF rectF, RectF rectF2) {
        ComputedBorderRadius computedBorderRadius;
        Float f;
        Float f2;
        Float f3;
        Float f4;
        Float f5;
        Float f6;
        Float f7;
        Float f8;
        Float f9;
        Float f10;
        Float f11;
        Float f12;
        Float f13;
        Float f14;
        Float f15;
        CornerRadii bottomLeft;
        CornerRadii bottomLeft2;
        CornerRadii bottomRight;
        CornerRadii bottomRight2;
        CornerRadii topRight;
        CornerRadii topRight2;
        CornerRadii topLeft;
        CornerRadii topLeft2;
        BorderRadiusStyle borderRadius = compositeBackgroundDrawable.getBorderRadius();
        Float f16 = null;
        if (borderRadius != null) {
            int layoutDirection = compositeBackgroundDrawable.getLayoutDirection();
            Context context = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            computedBorderRadius = borderRadius.resolve(layoutDirection, context, PixelUtil.toDIPFromPixel((float) compositeBackgroundDrawable.getBounds().width()), PixelUtil.toDIPFromPixel((float) compositeBackgroundDrawable.getBounds().height()));
        } else {
            computedBorderRadius = null;
        }
        Path path = new Path();
        if (computedBorderRadius == null || (topLeft2 = computedBorderRadius.getTopLeft()) == null) {
            f = null;
        } else {
            f = Float.valueOf(PixelUtil.INSTANCE.dpToPx(topLeft2.getHorizontal()));
        }
        if (rectF2 != null) {
            f2 = Float.valueOf(PixelUtil.INSTANCE.dpToPx(rectF2.left));
        } else {
            f2 = null;
        }
        float innerBorderRadius = getInnerBorderRadius(f, f2);
        if (computedBorderRadius == null || (topLeft = computedBorderRadius.getTopLeft()) == null) {
            f3 = null;
        } else {
            f3 = Float.valueOf(PixelUtil.INSTANCE.dpToPx(topLeft.getVertical()));
        }
        if (rectF2 != null) {
            f4 = Float.valueOf(PixelUtil.INSTANCE.dpToPx(rectF2.top));
        } else {
            f4 = null;
        }
        float innerBorderRadius2 = getInnerBorderRadius(f3, f4);
        if (computedBorderRadius == null || (topRight2 = computedBorderRadius.getTopRight()) == null) {
            f5 = null;
        } else {
            f5 = Float.valueOf(PixelUtil.INSTANCE.dpToPx(topRight2.getHorizontal()));
        }
        if (rectF2 != null) {
            f6 = Float.valueOf(PixelUtil.INSTANCE.dpToPx(rectF2.right));
        } else {
            f6 = null;
        }
        float innerBorderRadius3 = getInnerBorderRadius(f5, f6);
        if (computedBorderRadius == null || (topRight = computedBorderRadius.getTopRight()) == null) {
            f7 = null;
        } else {
            f7 = Float.valueOf(PixelUtil.INSTANCE.dpToPx(topRight.getVertical()));
        }
        if (rectF2 != null) {
            f8 = Float.valueOf(PixelUtil.INSTANCE.dpToPx(rectF2.top));
        } else {
            f8 = null;
        }
        float innerBorderRadius4 = getInnerBorderRadius(f7, f8);
        if (computedBorderRadius == null || (bottomRight2 = computedBorderRadius.getBottomRight()) == null) {
            f9 = null;
        } else {
            f9 = Float.valueOf(PixelUtil.INSTANCE.dpToPx(bottomRight2.getHorizontal()));
        }
        if (rectF2 != null) {
            f10 = Float.valueOf(PixelUtil.INSTANCE.dpToPx(rectF2.right));
        } else {
            f10 = null;
        }
        float innerBorderRadius5 = getInnerBorderRadius(f9, f10);
        if (computedBorderRadius == null || (bottomRight = computedBorderRadius.getBottomRight()) == null) {
            f11 = null;
        } else {
            f11 = Float.valueOf(PixelUtil.INSTANCE.dpToPx(bottomRight.getVertical()));
        }
        if (rectF2 != null) {
            f12 = Float.valueOf(PixelUtil.INSTANCE.dpToPx(rectF2.bottom));
        } else {
            f12 = null;
        }
        float innerBorderRadius6 = getInnerBorderRadius(f11, f12);
        if (computedBorderRadius == null || (bottomLeft2 = computedBorderRadius.getBottomLeft()) == null) {
            f13 = null;
        } else {
            f13 = Float.valueOf(PixelUtil.INSTANCE.dpToPx(bottomLeft2.getHorizontal()));
        }
        if (rectF2 != null) {
            f14 = Float.valueOf(PixelUtil.INSTANCE.dpToPx(rectF2.left));
        } else {
            f14 = null;
        }
        float innerBorderRadius7 = getInnerBorderRadius(f13, f14);
        if (computedBorderRadius == null || (bottomLeft = computedBorderRadius.getBottomLeft()) == null) {
            f15 = null;
        } else {
            f15 = Float.valueOf(PixelUtil.INSTANCE.dpToPx(bottomLeft.getVertical()));
        }
        if (rectF2 != null) {
            f16 = Float.valueOf(PixelUtil.INSTANCE.dpToPx(rectF2.bottom));
        }
        path.addRoundRect(rectF, new float[]{innerBorderRadius, innerBorderRadius2, innerBorderRadius3, innerBorderRadius4, innerBorderRadius5, innerBorderRadius6, innerBorderRadius7, getInnerBorderRadius(f15, f16)}, Path.Direction.CW);
        return path;
    }
}
