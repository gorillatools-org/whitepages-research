package com.swmansion.rnscreens;

import java.util.Collections;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SwapLastTwo extends ChildDrawingOrderStrategyBase {
    public SwapLastTwo() {
        super(false, 1, (DefaultConstructorMarker) null);
    }

    public void apply(List list) {
        Intrinsics.checkNotNullParameter(list, "drawingOperations");
        if (isEnabled() && list.size() >= 2) {
            Collections.swap(list, CollectionsKt.getLastIndex(list), CollectionsKt.getLastIndex(list) - 1);
        }
    }
}
