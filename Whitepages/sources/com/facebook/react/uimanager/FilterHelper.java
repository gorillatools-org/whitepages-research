package com.facebook.react.uimanager;

import android.annotation.TargetApi;
import android.graphics.BlendMode;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import kotlin.jvm.internal.Intrinsics;

@TargetApi(31)
public final class FilterHelper {
    public static final FilterHelper INSTANCE = new FilterHelper();

    private FilterHelper() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0150, code lost:
        r2 = r2 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final android.graphics.RenderEffect parseFilters(com.facebook.react.bridge.ReadableArray r7) {
        /*
            r0 = 0
            if (r7 != 0) goto L_0x0004
            return r0
        L_0x0004:
            int r1 = r7.size()
            r2 = 0
        L_0x0009:
            if (r2 >= r1) goto L_0x0173
            com.facebook.react.bridge.ReadableMap r3 = r7.getMap(r2)
            if (r3 == 0) goto L_0x016b
            java.util.Iterator r3 = r3.getEntryIterator()
            java.lang.Object r3 = r3.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r3.getKey()
            java.lang.String r4 = (java.lang.String) r4
            int r5 = r4.hashCode()
            java.lang.String r6 = "null cannot be cast to non-null type kotlin.Double"
            switch(r5) {
                case -2114203985: goto L_0x0134;
                case -1267206133: goto L_0x0117;
                case -1183703082: goto L_0x00fa;
                case -905411385: goto L_0x00dd;
                case -566947070: goto L_0x00bf;
                case 3027047: goto L_0x00a1;
                case 109324790: goto L_0x0083;
                case 648162385: goto L_0x0065;
                case 650888307: goto L_0x0047;
                case 906978543: goto L_0x002c;
                default: goto L_0x002a;
            }
        L_0x002a:
            goto L_0x0154
        L_0x002c:
            java.lang.String r5 = "dropShadow"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0154
            com.facebook.react.uimanager.FilterHelper r4 = INSTANCE
            java.lang.Object r3 = r3.getValue()
            java.lang.String r5 = "null cannot be cast to non-null type com.facebook.react.bridge.ReadableMap"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r5)
            com.facebook.react.bridge.ReadableMap r3 = (com.facebook.react.bridge.ReadableMap) r3
            android.graphics.RenderEffect r0 = r4.parseAndCreateDropShadowEffect(r3, r0)
            goto L_0x0150
        L_0x0047:
            java.lang.String r5 = "hueRotate"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0154
            com.facebook.react.uimanager.FilterHelper r4 = INSTANCE
            java.lang.Object r3 = r3.getValue()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r6)
            java.lang.Double r3 = (java.lang.Double) r3
            double r5 = r3.doubleValue()
            float r3 = (float) r5
            android.graphics.RenderEffect r0 = r4.createHueRotateEffect(r3, r0)
            goto L_0x0150
        L_0x0065:
            java.lang.String r5 = "brightness"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0154
            com.facebook.react.uimanager.FilterHelper r4 = INSTANCE
            java.lang.Object r3 = r3.getValue()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r6)
            java.lang.Double r3 = (java.lang.Double) r3
            double r5 = r3.doubleValue()
            float r3 = (float) r5
            android.graphics.RenderEffect r0 = r4.createBrightnessEffect(r3, r0)
            goto L_0x0150
        L_0x0083:
            java.lang.String r5 = "sepia"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0154
            com.facebook.react.uimanager.FilterHelper r4 = INSTANCE
            java.lang.Object r3 = r3.getValue()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r6)
            java.lang.Double r3 = (java.lang.Double) r3
            double r5 = r3.doubleValue()
            float r3 = (float) r5
            android.graphics.RenderEffect r0 = r4.createSepiaEffect(r3, r0)
            goto L_0x0150
        L_0x00a1:
            java.lang.String r5 = "blur"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0154
            com.facebook.react.uimanager.FilterHelper r4 = INSTANCE
            java.lang.Object r3 = r3.getValue()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r6)
            java.lang.Double r3 = (java.lang.Double) r3
            double r5 = r3.doubleValue()
            float r3 = (float) r5
            android.graphics.RenderEffect r0 = r4.createBlurEffect(r3, r0)
            goto L_0x0150
        L_0x00bf:
            java.lang.String r5 = "contrast"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0154
            com.facebook.react.uimanager.FilterHelper r4 = INSTANCE
            java.lang.Object r3 = r3.getValue()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r6)
            java.lang.Double r3 = (java.lang.Double) r3
            double r5 = r3.doubleValue()
            float r3 = (float) r5
            android.graphics.RenderEffect r0 = r4.createContrastEffect(r3, r0)
            goto L_0x0150
        L_0x00dd:
            java.lang.String r5 = "grayscale"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0154
            com.facebook.react.uimanager.FilterHelper r4 = INSTANCE
            java.lang.Object r3 = r3.getValue()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r6)
            java.lang.Double r3 = (java.lang.Double) r3
            double r5 = r3.doubleValue()
            float r3 = (float) r5
            android.graphics.RenderEffect r0 = r4.createGrayscaleEffect(r3, r0)
            goto L_0x0150
        L_0x00fa:
            java.lang.String r5 = "invert"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0154
            com.facebook.react.uimanager.FilterHelper r4 = INSTANCE
            java.lang.Object r3 = r3.getValue()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r6)
            java.lang.Double r3 = (java.lang.Double) r3
            double r5 = r3.doubleValue()
            float r3 = (float) r5
            android.graphics.RenderEffect r0 = r4.createInvertEffect(r3, r0)
            goto L_0x0150
        L_0x0117:
            java.lang.String r5 = "opacity"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0154
            com.facebook.react.uimanager.FilterHelper r4 = INSTANCE
            java.lang.Object r3 = r3.getValue()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r6)
            java.lang.Double r3 = (java.lang.Double) r3
            double r5 = r3.doubleValue()
            float r3 = (float) r5
            android.graphics.RenderEffect r0 = r4.createOpacityEffect(r3, r0)
            goto L_0x0150
        L_0x0134:
            java.lang.String r5 = "saturate"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x0154
            com.facebook.react.uimanager.FilterHelper r4 = INSTANCE
            java.lang.Object r3 = r3.getValue()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r6)
            java.lang.Double r3 = (java.lang.Double) r3
            double r5 = r3.doubleValue()
            float r3 = (float) r5
            android.graphics.RenderEffect r0 = r4.createSaturateEffect(r3, r0)
        L_0x0150:
            int r2 = r2 + 1
            goto L_0x0009
        L_0x0154:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Invalid filter name: "
            r0.append(r1)
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            r7.<init>(r0)
            throw r7
        L_0x016b:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "Required value was null."
            r7.<init>(r0)
            throw r7
        L_0x0173:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.FilterHelper.parseFilters(com.facebook.react.bridge.ReadableArray):android.graphics.RenderEffect");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00b6, code lost:
        r0.preConcat(r3);
        r2 = r2 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final android.graphics.ColorMatrixColorFilter parseColorMatrixFilters(com.facebook.react.bridge.ReadableArray r7) {
        /*
            if (r7 != 0) goto L_0x0004
            r7 = 0
            return r7
        L_0x0004:
            android.graphics.ColorMatrix r0 = new android.graphics.ColorMatrix
            r0.<init>()
            int r1 = r7.size()
            r2 = 0
        L_0x000e:
            if (r2 >= r1) goto L_0x00dc
            com.facebook.react.bridge.ReadableMap r3 = r7.getMap(r2)
            if (r3 == 0) goto L_0x00d4
            java.util.Iterator r3 = r3.getEntryIterator()
            java.lang.Object r3 = r3.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r3.getKey()
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r3 = r3.getValue()
            java.lang.String r5 = "null cannot be cast to non-null type kotlin.Double"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r5)
            java.lang.Double r3 = (java.lang.Double) r3
            double r5 = r3.doubleValue()
            float r3 = (float) r5
            int r5 = r4.hashCode()
            switch(r5) {
                case -2114203985: goto L_0x00a8;
                case -1267206133: goto L_0x0099;
                case -1183703082: goto L_0x008a;
                case -905411385: goto L_0x007b;
                case -566947070: goto L_0x006c;
                case 109324790: goto L_0x005d;
                case 648162385: goto L_0x004e;
                case 650888307: goto L_0x003f;
                default: goto L_0x003d;
            }
        L_0x003d:
            goto L_0x00bd
        L_0x003f:
            java.lang.String r5 = "hueRotate"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x00bd
            com.facebook.react.uimanager.FilterHelper r4 = INSTANCE
            android.graphics.ColorMatrix r3 = r4.createHueRotateColorMatrix(r3)
            goto L_0x00b6
        L_0x004e:
            java.lang.String r5 = "brightness"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x00bd
            com.facebook.react.uimanager.FilterHelper r4 = INSTANCE
            android.graphics.ColorMatrix r3 = r4.createBrightnessColorMatrix(r3)
            goto L_0x00b6
        L_0x005d:
            java.lang.String r5 = "sepia"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x00bd
            com.facebook.react.uimanager.FilterHelper r4 = INSTANCE
            android.graphics.ColorMatrix r3 = r4.createSepiaColorMatrix(r3)
            goto L_0x00b6
        L_0x006c:
            java.lang.String r5 = "contrast"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x00bd
            com.facebook.react.uimanager.FilterHelper r4 = INSTANCE
            android.graphics.ColorMatrix r3 = r4.createContrastColorMatrix(r3)
            goto L_0x00b6
        L_0x007b:
            java.lang.String r5 = "grayscale"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x00bd
            com.facebook.react.uimanager.FilterHelper r4 = INSTANCE
            android.graphics.ColorMatrix r3 = r4.createGrayscaleColorMatrix(r3)
            goto L_0x00b6
        L_0x008a:
            java.lang.String r5 = "invert"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x00bd
            com.facebook.react.uimanager.FilterHelper r4 = INSTANCE
            android.graphics.ColorMatrix r3 = r4.createInvertColorMatrix(r3)
            goto L_0x00b6
        L_0x0099:
            java.lang.String r5 = "opacity"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x00bd
            com.facebook.react.uimanager.FilterHelper r4 = INSTANCE
            android.graphics.ColorMatrix r3 = r4.createOpacityColorMatrix(r3)
            goto L_0x00b6
        L_0x00a8:
            java.lang.String r5 = "saturate"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x00bd
            com.facebook.react.uimanager.FilterHelper r4 = INSTANCE
            android.graphics.ColorMatrix r3 = r4.createSaturateColorMatrix(r3)
        L_0x00b6:
            r0.preConcat(r3)
            int r2 = r2 + 1
            goto L_0x000e
        L_0x00bd:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Invalid color matrix filter: "
            r0.append(r1)
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            r7.<init>(r0)
            throw r7
        L_0x00d4:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "Required value was null."
            r7.<init>(r0)
            throw r7
        L_0x00dc:
            android.graphics.ColorMatrixColorFilter r7 = new android.graphics.ColorMatrixColorFilter
            r7.<init>(r0)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.FilterHelper.parseColorMatrixFilters(com.facebook.react.bridge.ReadableArray):android.graphics.ColorMatrixColorFilter");
    }

    public static final boolean isOnlyColorMatrixFilters(ReadableArray readableArray) {
        if (readableArray == null || readableArray.size() == 0) {
            return false;
        }
        int size = readableArray.size();
        for (int i = 0; i < size; i++) {
            ReadableMap map = readableArray.getMap(i);
            Intrinsics.checkNotNull(map);
            String str = (String) map.getEntryIterator().next().getKey();
            if (Intrinsics.areEqual((Object) str, (Object) "blur") || Intrinsics.areEqual((Object) str, (Object) "dropShadow")) {
                return false;
            }
        }
        return true;
    }

    public static /* synthetic */ RenderEffect createBlurEffect$default(FilterHelper filterHelper, float f, RenderEffect renderEffect, int i, Object obj) {
        if ((i & 2) != 0) {
            renderEffect = null;
        }
        return filterHelper.createBlurEffect(f, renderEffect);
    }

    public final RenderEffect createBlurEffect(float f, RenderEffect renderEffect) {
        if (((double) f) <= 0.5d) {
            return null;
        }
        float sigmaToRadius$ReactAndroid_release = sigmaToRadius$ReactAndroid_release(f);
        if (renderEffect == null) {
            return RenderEffect.createBlurEffect(sigmaToRadius$ReactAndroid_release, sigmaToRadius$ReactAndroid_release, Shader.TileMode.DECAL);
        }
        return RenderEffect.createBlurEffect(sigmaToRadius$ReactAndroid_release, sigmaToRadius$ReactAndroid_release, renderEffect, Shader.TileMode.DECAL);
    }

    public static /* synthetic */ RenderEffect createBrightnessEffect$default(FilterHelper filterHelper, float f, RenderEffect renderEffect, int i, Object obj) {
        if ((i & 2) != 0) {
            renderEffect = null;
        }
        return filterHelper.createBrightnessEffect(f, renderEffect);
    }

    public final RenderEffect createBrightnessEffect(float f, RenderEffect renderEffect) {
        return createColorMatrixEffect(createBrightnessColorMatrix(f), renderEffect);
    }

    private final ColorMatrix createBrightnessColorMatrix(float f) {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setScale(f, f, f, 1.0f);
        return colorMatrix;
    }

    public static /* synthetic */ RenderEffect createOpacityEffect$default(FilterHelper filterHelper, float f, RenderEffect renderEffect, int i, Object obj) {
        if ((i & 2) != 0) {
            renderEffect = null;
        }
        return filterHelper.createOpacityEffect(f, renderEffect);
    }

    public final RenderEffect createOpacityEffect(float f, RenderEffect renderEffect) {
        return createColorMatrixEffect(createOpacityColorMatrix(f), renderEffect);
    }

    public static /* synthetic */ RenderEffect createDropShadowEffect$default(FilterHelper filterHelper, float f, float f2, float f3, int i, RenderEffect renderEffect, int i2, Object obj) {
        if ((i2 & 16) != 0) {
            renderEffect = null;
        }
        return filterHelper.createDropShadowEffect(f, f2, f3, i, renderEffect);
    }

    public final RenderEffect createDropShadowEffect(float f, float f2, float f3, int i, RenderEffect renderEffect) {
        RenderEffect renderEffect2;
        RenderEffect renderEffect3;
        if (renderEffect == null) {
            renderEffect2 = RenderEffect.createOffsetEffect(0.0f, 0.0f);
            renderEffect3 = RenderEffect.createOffsetEffect(f, f2);
        } else {
            RenderEffect m = RenderEffect.createOffsetEffect(0.0f, 0.0f, renderEffect);
            renderEffect3 = RenderEffect.createOffsetEffect(f, f2, renderEffect);
            renderEffect2 = m;
        }
        FilterHelper$$ExternalSyntheticApiModelOutline11.m();
        RenderEffect m2 = RenderEffect.createColorFilterEffect(FilterHelper$$ExternalSyntheticApiModelOutline10.m(i, BlendMode.SRC_IN), renderEffect3);
        Intrinsics.checkNotNullExpressionValue(m2, "createColorFilterEffect(...)");
        RenderEffect m3 = RenderEffect.createBlurEffect(f3, f3, m2, Shader.TileMode.DECAL);
        Intrinsics.checkNotNullExpressionValue(m3, "createBlurEffect(...)");
        RenderEffect m4 = RenderEffect.createBlendModeEffect(m3, renderEffect2, BlendMode.SRC_OVER);
        Intrinsics.checkNotNullExpressionValue(m4, "createBlendModeEffect(...)");
        return m4;
    }

    public static /* synthetic */ RenderEffect parseAndCreateDropShadowEffect$default(FilterHelper filterHelper, ReadableMap readableMap, RenderEffect renderEffect, int i, Object obj) {
        if ((i & 2) != 0) {
            renderEffect = null;
        }
        return filterHelper.parseAndCreateDropShadowEffect(readableMap, renderEffect);
    }

    public final RenderEffect parseAndCreateDropShadowEffect(ReadableMap readableMap, RenderEffect renderEffect) {
        Intrinsics.checkNotNullParameter(readableMap, "filterValues");
        PixelUtil pixelUtil = PixelUtil.INSTANCE;
        return createDropShadowEffect(pixelUtil.dpToPx(readableMap.getDouble("offsetX")), pixelUtil.dpToPx(readableMap.getDouble("offsetY")), readableMap.hasKey("standardDeviation") ? sigmaToRadius$ReactAndroid_release((float) readableMap.getDouble("standardDeviation")) : 0.0f, readableMap.hasKey(ViewProps.COLOR) ? readableMap.getInt(ViewProps.COLOR) : -16777216, renderEffect);
    }

    public final ColorMatrix createOpacityColorMatrix(float f) {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setScale(1.0f, 1.0f, 1.0f, f);
        return colorMatrix;
    }

    public static /* synthetic */ RenderEffect createContrastEffect$default(FilterHelper filterHelper, float f, RenderEffect renderEffect, int i, Object obj) {
        if ((i & 2) != 0) {
            renderEffect = null;
        }
        return filterHelper.createContrastEffect(f, renderEffect);
    }

    public final RenderEffect createContrastEffect(float f, RenderEffect renderEffect) {
        return createColorMatrixEffect(createContrastColorMatrix(f), renderEffect);
    }

    private final ColorMatrix createContrastColorMatrix(float f) {
        float f2 = ((float) 255) * ((-(f / 2.0f)) + 0.5f);
        return new ColorMatrix(new float[]{f, 0.0f, 0.0f, 0.0f, f2, 0.0f, f, 0.0f, 0.0f, f2, 0.0f, 0.0f, f, 0.0f, f2, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
    }

    public static /* synthetic */ RenderEffect createGrayscaleEffect$default(FilterHelper filterHelper, float f, RenderEffect renderEffect, int i, Object obj) {
        if ((i & 2) != 0) {
            renderEffect = null;
        }
        return filterHelper.createGrayscaleEffect(f, renderEffect);
    }

    public final RenderEffect createGrayscaleEffect(float f, RenderEffect renderEffect) {
        return createColorMatrixEffect(createGrayscaleColorMatrix(f), renderEffect);
    }

    private final ColorMatrix createGrayscaleColorMatrix(float f) {
        float f2 = ((float) 1) - f;
        float f3 = 0.7152f - (f2 * 0.7152f);
        float f4 = 0.0722f - (f2 * 0.0722f);
        float f5 = 0.2126f - (f2 * 0.2126f);
        return new ColorMatrix(new float[]{(0.7874f * f2) + 0.2126f, f3, f4, 0.0f, 0.0f, f5, (0.2848f * f2) + 0.7152f, f4, 0.0f, 0.0f, f5, f3, (f2 * 0.9278f) + 0.0722f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
    }

    public static /* synthetic */ RenderEffect createSepiaEffect$default(FilterHelper filterHelper, float f, RenderEffect renderEffect, int i, Object obj) {
        if ((i & 2) != 0) {
            renderEffect = null;
        }
        return filterHelper.createSepiaEffect(f, renderEffect);
    }

    public final RenderEffect createSepiaEffect(float f, RenderEffect renderEffect) {
        return createColorMatrixEffect(createSepiaColorMatrix(f), renderEffect);
    }

    private final ColorMatrix createSepiaColorMatrix(float f) {
        float f2 = ((float) 1) - f;
        return new ColorMatrix(new float[]{(0.607f * f2) + 0.393f, 0.769f - (f2 * 0.769f), 0.189f - (f2 * 0.189f), 0.0f, 0.0f, 0.349f - (f2 * 0.349f), (0.314f * f2) + 0.686f, 0.168f - (f2 * 0.168f), 0.0f, 0.0f, 0.272f - (f2 * 0.272f), 0.534f - (f2 * 0.534f), (f2 * 0.869f) + 0.131f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
    }

    public static /* synthetic */ RenderEffect createSaturateEffect$default(FilterHelper filterHelper, float f, RenderEffect renderEffect, int i, Object obj) {
        if ((i & 2) != 0) {
            renderEffect = null;
        }
        return filterHelper.createSaturateEffect(f, renderEffect);
    }

    public final RenderEffect createSaturateEffect(float f, RenderEffect renderEffect) {
        return createColorMatrixEffect(createSaturateColorMatrix(f), renderEffect);
    }

    private final ColorMatrix createSaturateColorMatrix(float f) {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(f);
        return colorMatrix;
    }

    public static /* synthetic */ RenderEffect createHueRotateEffect$default(FilterHelper filterHelper, float f, RenderEffect renderEffect, int i, Object obj) {
        if ((i & 2) != 0) {
            renderEffect = null;
        }
        return filterHelper.createHueRotateEffect(f, renderEffect);
    }

    public final RenderEffect createHueRotateEffect(float f, RenderEffect renderEffect) {
        return createColorMatrixEffect(createHueRotateColorMatrix(f), renderEffect);
    }

    private final ColorMatrix createHueRotateColorMatrix(float f) {
        double radians = Math.toRadians((double) f);
        float cos = (float) Math.cos(radians);
        float sin = (float) Math.sin(radians);
        float f2 = 0.715f - (cos * 0.715f);
        float f3 = sin * 0.715f;
        float f4 = 0.072f - (cos * 0.072f);
        float f5 = 0.213f - (cos * 0.213f);
        return new ColorMatrix(new float[]{((cos * 0.787f) + 0.213f) - (sin * 0.213f), f2 - f3, (sin * 0.928f) + f4, 0.0f, 0.0f, (0.143f * sin) + f5, (0.285f * cos) + 0.715f + (0.14f * sin), f4 - (0.283f * sin), 0.0f, 0.0f, f5 - (0.787f * sin), f2 + f3, (cos * 0.928f) + 0.072f + (sin * 0.072f), 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
    }

    public static /* synthetic */ RenderEffect createInvertEffect$default(FilterHelper filterHelper, float f, RenderEffect renderEffect, int i, Object obj) {
        if ((i & 2) != 0) {
            renderEffect = null;
        }
        return filterHelper.createInvertEffect(f, renderEffect);
    }

    public final RenderEffect createInvertEffect(float f, RenderEffect renderEffect) {
        return createColorMatrixEffect(createInvertColorMatrix(f), renderEffect);
    }

    private final ColorMatrix createInvertColorMatrix(float f) {
        float f2 = ((float) 1) - (((float) 2) * f);
        float f3 = f * ((float) 255);
        return new ColorMatrix(new float[]{f2, 0.0f, 0.0f, 0.0f, f3, 0.0f, f2, 0.0f, 0.0f, f3, 0.0f, 0.0f, f2, 0.0f, f3, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
    }

    static /* synthetic */ RenderEffect createColorMatrixEffect$default(FilterHelper filterHelper, ColorMatrix colorMatrix, RenderEffect renderEffect, int i, Object obj) {
        if ((i & 2) != 0) {
            renderEffect = null;
        }
        return filterHelper.createColorMatrixEffect(colorMatrix, renderEffect);
    }

    private final RenderEffect createColorMatrixEffect(ColorMatrix colorMatrix, RenderEffect renderEffect) {
        if (renderEffect == null) {
            RenderEffect m = RenderEffect.createColorFilterEffect(new ColorMatrixColorFilter(colorMatrix));
            Intrinsics.checkNotNull(m);
            return m;
        }
        RenderEffect m2 = RenderEffect.createColorFilterEffect(new ColorMatrixColorFilter(colorMatrix), renderEffect);
        Intrinsics.checkNotNull(m2);
        return m2;
    }

    public final float sigmaToRadius$ReactAndroid_release(float f) {
        return (PixelUtil.toPixelFromDIP(f) - 0.5f) / 0.57735f;
    }
}
