package kotlin.ranges;

import kotlin.jvm.internal.Intrinsics;

abstract class RangesKt__RangesKt {
    public static final void checkStepIsPositive(boolean z, Number number) {
        Intrinsics.checkNotNullParameter(number, "step");
        if (!z) {
            throw new IllegalArgumentException("Step must be positive, was: " + number + '.');
        }
    }
}
