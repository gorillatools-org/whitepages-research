package com.airbnb.lottie.model.content;

import androidx.core.graphics.BlendModeCompat;

public enum LBlendMode {
    NORMAL,
    MULTIPLY,
    SCREEN,
    OVERLAY,
    DARKEN,
    LIGHTEN,
    COLOR_DODGE,
    COLOR_BURN,
    HARD_LIGHT,
    SOFT_LIGHT,
    DIFFERENCE,
    EXCLUSION,
    HUE,
    SATURATION,
    COLOR,
    LUMINOSITY,
    ADD,
    HARD_MIX;

    /* renamed from: com.airbnb.lottie.model.content.LBlendMode$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$model$content$LBlendMode = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(36:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|(3:35|36|38)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(38:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|38) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00a8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00b4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00c0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00cc */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.airbnb.lottie.model.content.LBlendMode[] r0 = com.airbnb.lottie.model.content.LBlendMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$airbnb$lottie$model$content$LBlendMode = r0
                com.airbnb.lottie.model.content.LBlendMode r1 = com.airbnb.lottie.model.content.LBlendMode.NORMAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$content$LBlendMode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.airbnb.lottie.model.content.LBlendMode r1 = com.airbnb.lottie.model.content.LBlendMode.MULTIPLY     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$content$LBlendMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.airbnb.lottie.model.content.LBlendMode r1 = com.airbnb.lottie.model.content.LBlendMode.SCREEN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$content$LBlendMode     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.airbnb.lottie.model.content.LBlendMode r1 = com.airbnb.lottie.model.content.LBlendMode.OVERLAY     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$content$LBlendMode     // Catch:{ NoSuchFieldError -> 0x003e }
                com.airbnb.lottie.model.content.LBlendMode r1 = com.airbnb.lottie.model.content.LBlendMode.DARKEN     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$content$LBlendMode     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.airbnb.lottie.model.content.LBlendMode r1 = com.airbnb.lottie.model.content.LBlendMode.LIGHTEN     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$content$LBlendMode     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.airbnb.lottie.model.content.LBlendMode r1 = com.airbnb.lottie.model.content.LBlendMode.ADD     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$content$LBlendMode     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.airbnb.lottie.model.content.LBlendMode r1 = com.airbnb.lottie.model.content.LBlendMode.COLOR_DODGE     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$content$LBlendMode     // Catch:{ NoSuchFieldError -> 0x006c }
                com.airbnb.lottie.model.content.LBlendMode r1 = com.airbnb.lottie.model.content.LBlendMode.COLOR_BURN     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$content$LBlendMode     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.airbnb.lottie.model.content.LBlendMode r1 = com.airbnb.lottie.model.content.LBlendMode.HARD_LIGHT     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$content$LBlendMode     // Catch:{ NoSuchFieldError -> 0x0084 }
                com.airbnb.lottie.model.content.LBlendMode r1 = com.airbnb.lottie.model.content.LBlendMode.SOFT_LIGHT     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$content$LBlendMode     // Catch:{ NoSuchFieldError -> 0x0090 }
                com.airbnb.lottie.model.content.LBlendMode r1 = com.airbnb.lottie.model.content.LBlendMode.DIFFERENCE     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$content$LBlendMode     // Catch:{ NoSuchFieldError -> 0x009c }
                com.airbnb.lottie.model.content.LBlendMode r1 = com.airbnb.lottie.model.content.LBlendMode.EXCLUSION     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$content$LBlendMode     // Catch:{ NoSuchFieldError -> 0x00a8 }
                com.airbnb.lottie.model.content.LBlendMode r1 = com.airbnb.lottie.model.content.LBlendMode.HUE     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$content$LBlendMode     // Catch:{ NoSuchFieldError -> 0x00b4 }
                com.airbnb.lottie.model.content.LBlendMode r1 = com.airbnb.lottie.model.content.LBlendMode.SATURATION     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$content$LBlendMode     // Catch:{ NoSuchFieldError -> 0x00c0 }
                com.airbnb.lottie.model.content.LBlendMode r1 = com.airbnb.lottie.model.content.LBlendMode.COLOR     // Catch:{ NoSuchFieldError -> 0x00c0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c0 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c0 }
            L_0x00c0:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$content$LBlendMode     // Catch:{ NoSuchFieldError -> 0x00cc }
                com.airbnb.lottie.model.content.LBlendMode r1 = com.airbnb.lottie.model.content.LBlendMode.LUMINOSITY     // Catch:{ NoSuchFieldError -> 0x00cc }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cc }
                r2 = 17
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00cc }
            L_0x00cc:
                int[] r0 = $SwitchMap$com$airbnb$lottie$model$content$LBlendMode     // Catch:{ NoSuchFieldError -> 0x00d8 }
                com.airbnb.lottie.model.content.LBlendMode r1 = com.airbnb.lottie.model.content.LBlendMode.HARD_MIX     // Catch:{ NoSuchFieldError -> 0x00d8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d8 }
                r2 = 18
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00d8 }
            L_0x00d8:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.model.content.LBlendMode.AnonymousClass1.<clinit>():void");
        }
    }

    public BlendModeCompat toNativeBlendMode() {
        switch (AnonymousClass1.$SwitchMap$com$airbnb$lottie$model$content$LBlendMode[ordinal()]) {
            case 2:
                return BlendModeCompat.MODULATE;
            case 3:
                return BlendModeCompat.SCREEN;
            case 4:
                return BlendModeCompat.OVERLAY;
            case 5:
                return BlendModeCompat.DARKEN;
            case 6:
                return BlendModeCompat.LIGHTEN;
            case 7:
                return BlendModeCompat.PLUS;
            default:
                return null;
        }
    }
}
