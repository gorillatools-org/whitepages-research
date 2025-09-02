package com.swmansion.rnscreens;

import kotlin.jvm.internal.DefaultConstructorMarker;

public abstract class ChildDrawingOrderStrategyBase implements ChildDrawingOrderStrategy {
    private boolean enabled;

    public ChildDrawingOrderStrategyBase(boolean z) {
        this.enabled = z;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ChildDrawingOrderStrategyBase(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    public void enable() {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }

    public boolean isEnabled() {
        return this.enabled;
    }
}
