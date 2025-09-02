package com.facebook.drawee.generic;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.facebook.drawee.drawable.ScalingUtils$ScaleType;
import com.facebook.imagepipeline.systrace.FrescoSystrace;

public abstract class GenericDraweeHierarchyInflater {
    public static GenericDraweeHierarchyBuilder inflateBuilder(Context context, AttributeSet attributeSet) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("GenericDraweeHierarchyBuilder#inflateBuilder");
        }
        GenericDraweeHierarchyBuilder updateBuilder = updateBuilder(new GenericDraweeHierarchyBuilder(context.getResources()), context, attributeSet);
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
        return updateBuilder;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:115:0x01c4, code lost:
        if (r13 != false) goto L_0x01c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x01e4, code lost:
        if (r15 != false) goto L_0x01c6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.facebook.drawee.generic.GenericDraweeHierarchyBuilder updateBuilder(com.facebook.drawee.generic.GenericDraweeHierarchyBuilder r17, android.content.Context r18, android.util.AttributeSet r19) {
        /*
            r0 = r17
            r1 = r18
            r2 = r19
            if (r2 == 0) goto L_0x0201
            int[] r6 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy
            android.content.res.TypedArray r2 = r1.obtainStyledAttributes(r2, r6)
            int r6 = r2.getIndexCount()     // Catch:{ all -> 0x01e7 }
            r5 = 0
            r7 = 0
            r8 = 1
            r9 = 1
            r10 = 1
            r11 = 1
            r12 = 1
            r13 = 1
            r14 = 1
            r15 = 1
            r16 = 0
        L_0x001e:
            if (r7 >= r6) goto L_0x019a
            int r3 = r2.getIndex(r7)     // Catch:{ all -> 0x0031 }
            int r4 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_actualImageScaleType     // Catch:{ all -> 0x0031 }
            if (r3 != r4) goto L_0x0034
            com.facebook.drawee.drawable.ScalingUtils$ScaleType r3 = getScaleTypeFromXml(r2, r3)     // Catch:{ all -> 0x0031 }
            r0.setActualImageScaleType(r3)     // Catch:{ all -> 0x0031 }
            goto L_0x00c5
        L_0x0031:
            r0 = move-exception
            goto L_0x01f0
        L_0x0034:
            int r4 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_placeholderImage     // Catch:{ all -> 0x0031 }
            if (r3 != r4) goto L_0x0041
            android.graphics.drawable.Drawable r3 = getDrawable(r1, r2, r3)     // Catch:{ all -> 0x0031 }
            r0.setPlaceholderImage(r3)     // Catch:{ all -> 0x0031 }
            goto L_0x00c5
        L_0x0041:
            int r4 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_pressedStateOverlayImage     // Catch:{ all -> 0x0031 }
            if (r3 != r4) goto L_0x004e
            android.graphics.drawable.Drawable r3 = getDrawable(r1, r2, r3)     // Catch:{ all -> 0x0031 }
            r0.setPressedStateOverlay(r3)     // Catch:{ all -> 0x0031 }
            goto L_0x00c5
        L_0x004e:
            int r4 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_progressBarImage     // Catch:{ all -> 0x0031 }
            if (r3 != r4) goto L_0x005b
            android.graphics.drawable.Drawable r3 = getDrawable(r1, r2, r3)     // Catch:{ all -> 0x0031 }
            r0.setProgressBarImage(r3)     // Catch:{ all -> 0x0031 }
            goto L_0x00c5
        L_0x005b:
            int r4 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_fadeDuration     // Catch:{ all -> 0x0031 }
            if (r3 != r4) goto L_0x0068
            r4 = 0
            int r3 = r2.getInt(r3, r4)     // Catch:{ all -> 0x0031 }
            r0.setFadeDuration(r3)     // Catch:{ all -> 0x0031 }
            goto L_0x00c5
        L_0x0068:
            int r4 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_viewAspectRatio     // Catch:{ all -> 0x0031 }
            if (r3 != r4) goto L_0x0075
            r4 = 0
            float r3 = r2.getFloat(r3, r4)     // Catch:{ all -> 0x0031 }
            r0.setDesiredAspectRatio(r3)     // Catch:{ all -> 0x0031 }
            goto L_0x00c5
        L_0x0075:
            int r4 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_placeholderImageScaleType     // Catch:{ all -> 0x0031 }
            if (r3 != r4) goto L_0x0081
            com.facebook.drawee.drawable.ScalingUtils$ScaleType r3 = getScaleTypeFromXml(r2, r3)     // Catch:{ all -> 0x0031 }
            r0.setPlaceholderImageScaleType(r3)     // Catch:{ all -> 0x0031 }
            goto L_0x00c5
        L_0x0081:
            int r4 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_retryImage     // Catch:{ all -> 0x0031 }
            if (r3 != r4) goto L_0x008d
            android.graphics.drawable.Drawable r3 = getDrawable(r1, r2, r3)     // Catch:{ all -> 0x0031 }
            r0.setRetryImage(r3)     // Catch:{ all -> 0x0031 }
            goto L_0x00c5
        L_0x008d:
            int r4 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_retryImageScaleType     // Catch:{ all -> 0x0031 }
            if (r3 != r4) goto L_0x0099
            com.facebook.drawee.drawable.ScalingUtils$ScaleType r3 = getScaleTypeFromXml(r2, r3)     // Catch:{ all -> 0x0031 }
            r0.setRetryImageScaleType(r3)     // Catch:{ all -> 0x0031 }
            goto L_0x00c5
        L_0x0099:
            int r4 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_failureImage     // Catch:{ all -> 0x0031 }
            if (r3 != r4) goto L_0x00a5
            android.graphics.drawable.Drawable r3 = getDrawable(r1, r2, r3)     // Catch:{ all -> 0x0031 }
            r0.setFailureImage(r3)     // Catch:{ all -> 0x0031 }
            goto L_0x00c5
        L_0x00a5:
            int r4 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_failureImageScaleType     // Catch:{ all -> 0x0031 }
            if (r3 != r4) goto L_0x00b1
            com.facebook.drawee.drawable.ScalingUtils$ScaleType r3 = getScaleTypeFromXml(r2, r3)     // Catch:{ all -> 0x0031 }
            r0.setFailureImageScaleType(r3)     // Catch:{ all -> 0x0031 }
            goto L_0x00c5
        L_0x00b1:
            int r4 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_progressBarImageScaleType     // Catch:{ all -> 0x0031 }
            if (r3 != r4) goto L_0x00bd
            com.facebook.drawee.drawable.ScalingUtils$ScaleType r3 = getScaleTypeFromXml(r2, r3)     // Catch:{ all -> 0x0031 }
            r0.setProgressBarImageScaleType(r3)     // Catch:{ all -> 0x0031 }
            goto L_0x00c5
        L_0x00bd:
            int r4 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_progressBarAutoRotateInterval     // Catch:{ all -> 0x0031 }
            if (r3 != r4) goto L_0x00c8
            int r5 = r2.getInteger(r3, r5)     // Catch:{ all -> 0x0031 }
        L_0x00c5:
            r4 = 0
            goto L_0x0194
        L_0x00c8:
            int r4 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_backgroundImage     // Catch:{ all -> 0x0031 }
            if (r3 != r4) goto L_0x00d4
            android.graphics.drawable.Drawable r3 = getDrawable(r1, r2, r3)     // Catch:{ all -> 0x0031 }
            r0.setBackground(r3)     // Catch:{ all -> 0x0031 }
            goto L_0x00c5
        L_0x00d4:
            int r4 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_overlayImage     // Catch:{ all -> 0x0031 }
            if (r3 != r4) goto L_0x00e0
            android.graphics.drawable.Drawable r3 = getDrawable(r1, r2, r3)     // Catch:{ all -> 0x0031 }
            r0.setOverlay(r3)     // Catch:{ all -> 0x0031 }
            goto L_0x00c5
        L_0x00e0:
            int r4 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_roundAsCircle     // Catch:{ all -> 0x0031 }
            if (r3 != r4) goto L_0x00f1
            com.facebook.drawee.generic.RoundingParams r4 = getRoundingParams(r17)     // Catch:{ all -> 0x0031 }
            r1 = 0
            boolean r3 = r2.getBoolean(r3, r1)     // Catch:{ all -> 0x0031 }
            r4.setRoundAsCircle(r3)     // Catch:{ all -> 0x0031 }
            goto L_0x00c5
        L_0x00f1:
            int r1 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_roundedCornerRadius     // Catch:{ all -> 0x0031 }
            if (r3 != r1) goto L_0x00fc
            r4 = r16
            int r16 = r2.getDimensionPixelSize(r3, r4)     // Catch:{ all -> 0x0031 }
            goto L_0x00c5
        L_0x00fc:
            r4 = r16
            int r1 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_roundTopLeft     // Catch:{ all -> 0x0031 }
            if (r3 != r1) goto L_0x0109
            boolean r8 = r2.getBoolean(r3, r8)     // Catch:{ all -> 0x0031 }
        L_0x0106:
            r16 = r4
            goto L_0x00c5
        L_0x0109:
            int r1 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_roundTopRight     // Catch:{ all -> 0x0031 }
            if (r3 != r1) goto L_0x0112
            boolean r10 = r2.getBoolean(r3, r10)     // Catch:{ all -> 0x0031 }
            goto L_0x0106
        L_0x0112:
            int r1 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_roundBottomLeft     // Catch:{ all -> 0x0031 }
            if (r3 != r1) goto L_0x011b
            boolean r14 = r2.getBoolean(r3, r14)     // Catch:{ all -> 0x0031 }
            goto L_0x0106
        L_0x011b:
            int r1 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_roundBottomRight     // Catch:{ all -> 0x0031 }
            if (r3 != r1) goto L_0x0124
            boolean r12 = r2.getBoolean(r3, r12)     // Catch:{ all -> 0x0031 }
            goto L_0x0106
        L_0x0124:
            int r1 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_roundTopStart     // Catch:{ all -> 0x0031 }
            if (r3 != r1) goto L_0x012d
            boolean r9 = r2.getBoolean(r3, r9)     // Catch:{ all -> 0x0031 }
            goto L_0x0106
        L_0x012d:
            int r1 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_roundTopEnd     // Catch:{ all -> 0x0031 }
            if (r3 != r1) goto L_0x0136
            boolean r11 = r2.getBoolean(r3, r11)     // Catch:{ all -> 0x0031 }
            goto L_0x0106
        L_0x0136:
            int r1 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_roundBottomStart     // Catch:{ all -> 0x0031 }
            if (r3 != r1) goto L_0x013f
            boolean r15 = r2.getBoolean(r3, r15)     // Catch:{ all -> 0x0031 }
            goto L_0x0106
        L_0x013f:
            int r1 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_roundBottomEnd     // Catch:{ all -> 0x0031 }
            if (r3 != r1) goto L_0x0148
            boolean r13 = r2.getBoolean(r3, r13)     // Catch:{ all -> 0x0031 }
            goto L_0x0106
        L_0x0148:
            int r1 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_roundWithOverlayColor     // Catch:{ all -> 0x0031 }
            if (r3 != r1) goto L_0x015c
            com.facebook.drawee.generic.RoundingParams r1 = getRoundingParams(r17)     // Catch:{ all -> 0x0031 }
            r16 = r4
            r4 = 0
            int r3 = r2.getColor(r3, r4)     // Catch:{ all -> 0x0031 }
            r1.setOverlayColor(r3)     // Catch:{ all -> 0x0031 }
            goto L_0x00c5
        L_0x015c:
            r16 = r4
            int r1 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_roundingBorderWidth     // Catch:{ all -> 0x0031 }
            if (r3 != r1) goto L_0x0171
            com.facebook.drawee.generic.RoundingParams r1 = getRoundingParams(r17)     // Catch:{ all -> 0x0031 }
            r4 = 0
            int r3 = r2.getDimensionPixelSize(r3, r4)     // Catch:{ all -> 0x0031 }
            float r3 = (float) r3     // Catch:{ all -> 0x0031 }
            r1.setBorderWidth(r3)     // Catch:{ all -> 0x0031 }
            goto L_0x00c5
        L_0x0171:
            int r1 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_roundingBorderColor     // Catch:{ all -> 0x0031 }
            if (r3 != r1) goto L_0x0183
            com.facebook.drawee.generic.RoundingParams r1 = getRoundingParams(r17)     // Catch:{ all -> 0x0031 }
            r4 = 0
            int r3 = r2.getColor(r3, r4)     // Catch:{ all -> 0x0031 }
            r1.setBorderColor(r3)     // Catch:{ all -> 0x0031 }
            goto L_0x00c5
        L_0x0183:
            int r1 = com.facebook.drawee.R$styleable.GenericDraweeHierarchy_roundingBorderPadding     // Catch:{ all -> 0x0031 }
            if (r3 != r1) goto L_0x00c5
            com.facebook.drawee.generic.RoundingParams r1 = getRoundingParams(r17)     // Catch:{ all -> 0x0031 }
            r4 = 0
            int r3 = r2.getDimensionPixelSize(r3, r4)     // Catch:{ all -> 0x0031 }
            float r3 = (float) r3     // Catch:{ all -> 0x0031 }
            r1.setPadding(r3)     // Catch:{ all -> 0x0031 }
        L_0x0194:
            int r7 = r7 + 1
            r1 = r18
            goto L_0x001e
        L_0x019a:
            r4 = 0
            r2.recycle()
            android.content.res.Resources r1 = r18.getResources()
            android.content.res.Configuration r1 = r1.getConfiguration()
            int r1 = r1.getLayoutDirection()
            r2 = 1
            if (r1 != r2) goto L_0x01cd
            if (r8 == 0) goto L_0x01b3
            if (r11 == 0) goto L_0x01b3
            r1 = 1
            goto L_0x01b4
        L_0x01b3:
            r1 = r4
        L_0x01b4:
            if (r10 == 0) goto L_0x01ba
            if (r9 == 0) goto L_0x01ba
            r2 = 1
            goto L_0x01bb
        L_0x01ba:
            r2 = r4
        L_0x01bb:
            if (r12 == 0) goto L_0x01c1
            if (r15 == 0) goto L_0x01c1
            r3 = 1
            goto L_0x01c2
        L_0x01c1:
            r3 = r4
        L_0x01c2:
            if (r14 == 0) goto L_0x01c7
            if (r13 == 0) goto L_0x01c7
        L_0x01c6:
            r4 = 1
        L_0x01c7:
            r6 = r4
            r4 = r5
            r5 = r1
            r1 = r16
            goto L_0x0207
        L_0x01cd:
            if (r8 == 0) goto L_0x01d3
            if (r9 == 0) goto L_0x01d3
            r1 = 1
            goto L_0x01d4
        L_0x01d3:
            r1 = r4
        L_0x01d4:
            if (r10 == 0) goto L_0x01da
            if (r11 == 0) goto L_0x01da
            r2 = 1
            goto L_0x01db
        L_0x01da:
            r2 = r4
        L_0x01db:
            if (r12 == 0) goto L_0x01e1
            if (r13 == 0) goto L_0x01e1
            r3 = 1
            goto L_0x01e2
        L_0x01e1:
            r3 = r4
        L_0x01e2:
            if (r14 == 0) goto L_0x01c7
            if (r15 == 0) goto L_0x01c7
            goto L_0x01c6
        L_0x01e7:
            r0 = move-exception
            r8 = 1
            r9 = 1
            r10 = 1
            r11 = 1
            r12 = 1
            r13 = 1
            r14 = 1
            r15 = 1
        L_0x01f0:
            r2.recycle()
            android.content.res.Resources r1 = r18.getResources()
            android.content.res.Configuration r1 = r1.getConfiguration()
            int r1 = r1.getLayoutDirection()
            r2 = 1
            throw r0
        L_0x0201:
            r2 = 1
            r4 = 0
            r3 = r2
            r5 = r3
            r6 = r5
            r1 = r4
        L_0x0207:
            android.graphics.drawable.Drawable r7 = r17.getProgressBarImage()
            if (r7 == 0) goto L_0x021b
            if (r4 <= 0) goto L_0x021b
            com.facebook.drawee.drawable.AutoRotateDrawable r7 = new com.facebook.drawee.drawable.AutoRotateDrawable
            android.graphics.drawable.Drawable r8 = r17.getProgressBarImage()
            r7.<init>(r8, r4)
            r0.setProgressBarImage(r7)
        L_0x021b:
            if (r1 <= 0) goto L_0x0238
            com.facebook.drawee.generic.RoundingParams r4 = getRoundingParams(r17)
            if (r5 == 0) goto L_0x0225
            float r5 = (float) r1
            goto L_0x0226
        L_0x0225:
            r5 = 0
        L_0x0226:
            if (r2 == 0) goto L_0x022a
            float r2 = (float) r1
            goto L_0x022b
        L_0x022a:
            r2 = 0
        L_0x022b:
            if (r3 == 0) goto L_0x022f
            float r3 = (float) r1
            goto L_0x0230
        L_0x022f:
            r3 = 0
        L_0x0230:
            if (r6 == 0) goto L_0x0234
            float r1 = (float) r1
            goto L_0x0235
        L_0x0234:
            r1 = 0
        L_0x0235:
            r4.setCornersRadii(r5, r2, r3, r1)
        L_0x0238:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.drawee.generic.GenericDraweeHierarchyInflater.updateBuilder(com.facebook.drawee.generic.GenericDraweeHierarchyBuilder, android.content.Context, android.util.AttributeSet):com.facebook.drawee.generic.GenericDraweeHierarchyBuilder");
    }

    private static RoundingParams getRoundingParams(GenericDraweeHierarchyBuilder genericDraweeHierarchyBuilder) {
        if (genericDraweeHierarchyBuilder.getRoundingParams() == null) {
            genericDraweeHierarchyBuilder.setRoundingParams(new RoundingParams());
        }
        return genericDraweeHierarchyBuilder.getRoundingParams();
    }

    public static Drawable getDrawable(Context context, TypedArray typedArray, int i) {
        int resourceId = typedArray.getResourceId(i, 0);
        if (resourceId == 0) {
            return null;
        }
        return context.getDrawable(resourceId);
    }

    public static ScalingUtils$ScaleType getScaleTypeFromXml(TypedArray typedArray, int i) {
        switch (typedArray.getInt(i, -2)) {
            case -1:
                return null;
            case 0:
                return ScalingUtils$ScaleType.FIT_XY;
            case 1:
                return ScalingUtils$ScaleType.FIT_START;
            case 2:
                return ScalingUtils$ScaleType.FIT_CENTER;
            case 3:
                return ScalingUtils$ScaleType.FIT_END;
            case 4:
                return ScalingUtils$ScaleType.CENTER;
            case 5:
                return ScalingUtils$ScaleType.CENTER_INSIDE;
            case 6:
                return ScalingUtils$ScaleType.CENTER_CROP;
            case 7:
                return ScalingUtils$ScaleType.FOCUS_CROP;
            case 8:
                return ScalingUtils$ScaleType.FIT_BOTTOM_START;
            default:
                throw new RuntimeException("XML attribute not specified!");
        }
    }
}
