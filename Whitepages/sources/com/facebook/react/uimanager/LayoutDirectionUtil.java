package com.facebook.react.uimanager;

import com.facebook.yoga.YogaDirection;
import kotlin.jvm.internal.Intrinsics;

public final class LayoutDirectionUtil {
    public static final LayoutDirectionUtil INSTANCE = new LayoutDirectionUtil();

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                com.facebook.yoga.YogaDirection[] r0 = com.facebook.yoga.YogaDirection.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.yoga.YogaDirection r1 = com.facebook.yoga.YogaDirection.LTR     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.yoga.YogaDirection r1 = com.facebook.yoga.YogaDirection.RTL     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.LayoutDirectionUtil.WhenMappings.<clinit>():void");
        }
    }

    private LayoutDirectionUtil() {
    }

    public static final int toAndroidFromYoga(YogaDirection yogaDirection) {
        Intrinsics.checkNotNullParameter(yogaDirection, "direction");
        int i = WhenMappings.$EnumSwitchMapping$0[yogaDirection.ordinal()];
        if (i != 1) {
            return i != 2 ? 2 : 1;
        }
        return 0;
    }

    public static final YogaDirection toYogaFromAndroid(int i) {
        if (i == 0) {
            return YogaDirection.LTR;
        }
        if (i != 1) {
            return YogaDirection.INHERIT;
        }
        return YogaDirection.RTL;
    }
}
