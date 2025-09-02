package com.swmansion.rnscreens;

import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

public final class ReverseOrderInRange extends ChildDrawingOrderStrategyBase {
    private final IntRange range;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ReverseOrderInRange(IntRange intRange) {
        super(false, 1, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(intRange, "range");
        this.range = intRange;
    }

    public void apply(List list) {
        Intrinsics.checkNotNullParameter(list, "drawingOperations");
        if (isEnabled()) {
            int intValue = this.range.getStart().intValue();
            for (int intValue2 = this.range.getEndInclusive().intValue(); intValue < intValue2; intValue2--) {
                Collections.swap(list, intValue, intValue2);
                intValue++;
            }
        }
    }
}
