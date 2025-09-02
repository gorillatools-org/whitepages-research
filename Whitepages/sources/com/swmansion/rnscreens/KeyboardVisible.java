package com.swmansion.rnscreens;

import kotlin.jvm.internal.DefaultConstructorMarker;

public final class KeyboardVisible extends KeyboardState {
    private final int height;

    public final int getHeight() {
        return this.height;
    }

    public KeyboardVisible(int i) {
        super((DefaultConstructorMarker) null);
        this.height = i;
    }
}
