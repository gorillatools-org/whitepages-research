package com.swmansion.rnscreens.bottomsheet;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import kotlin.jvm.internal.Intrinsics;

public abstract class BottomSheetBehaviorExtKt {
    public static /* synthetic */ BottomSheetBehavior useSingleDetent$default(BottomSheetBehavior bottomSheetBehavior, Integer num, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            z = true;
        }
        return useSingleDetent(bottomSheetBehavior, num, z);
    }

    public static final BottomSheetBehavior useSingleDetent(BottomSheetBehavior bottomSheetBehavior, Integer num, boolean z) {
        Intrinsics.checkNotNullParameter(bottomSheetBehavior, "<this>");
        bottomSheetBehavior.setSkipCollapsed(true);
        bottomSheetBehavior.setFitToContents(true);
        if (z) {
            bottomSheetBehavior.setState(3);
        }
        if (num != null) {
            bottomSheetBehavior.setMaxHeight(num.intValue());
        }
        return bottomSheetBehavior;
    }

    public static /* synthetic */ BottomSheetBehavior useTwoDetents$default(BottomSheetBehavior bottomSheetBehavior, Integer num, Integer num2, Integer num3, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            num2 = null;
        }
        if ((i & 4) != 0) {
            num3 = null;
        }
        return useTwoDetents(bottomSheetBehavior, num, num2, num3);
    }

    public static final BottomSheetBehavior useTwoDetents(BottomSheetBehavior bottomSheetBehavior, Integer num, Integer num2, Integer num3) {
        Intrinsics.checkNotNullParameter(bottomSheetBehavior, "<this>");
        bottomSheetBehavior.setSkipCollapsed(false);
        bottomSheetBehavior.setFitToContents(true);
        if (num != null) {
            bottomSheetBehavior.setState(num.intValue());
        }
        if (num2 != null) {
            bottomSheetBehavior.setPeekHeight(num2.intValue());
        }
        if (num3 != null) {
            bottomSheetBehavior.setMaxHeight(num3.intValue());
        }
        return bottomSheetBehavior;
    }

    public static /* synthetic */ BottomSheetBehavior useThreeDetents$default(BottomSheetBehavior bottomSheetBehavior, Integer num, Integer num2, Float f, Integer num3, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            num2 = null;
        }
        if ((i & 4) != 0) {
            f = null;
        }
        if ((i & 8) != 0) {
            num3 = null;
        }
        return useThreeDetents(bottomSheetBehavior, num, num2, f, num3);
    }

    public static final BottomSheetBehavior useThreeDetents(BottomSheetBehavior bottomSheetBehavior, Integer num, Integer num2, Float f, Integer num3) {
        Intrinsics.checkNotNullParameter(bottomSheetBehavior, "<this>");
        bottomSheetBehavior.setSkipCollapsed(false);
        bottomSheetBehavior.setFitToContents(false);
        if (num != null) {
            bottomSheetBehavior.setState(num.intValue());
        }
        if (num2 != null) {
            bottomSheetBehavior.setPeekHeight(num2.intValue());
        }
        if (f != null) {
            bottomSheetBehavior.setHalfExpandedRatio(f.floatValue());
        }
        if (num3 != null) {
            bottomSheetBehavior.setExpandedOffset(num3.intValue());
        }
        return bottomSheetBehavior;
    }
}
