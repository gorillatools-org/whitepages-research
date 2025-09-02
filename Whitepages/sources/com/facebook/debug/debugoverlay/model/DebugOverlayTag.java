package com.facebook.debug.debugoverlay.model;

import kotlin.jvm.internal.Intrinsics;

public final class DebugOverlayTag {
    private final int color;
    private final String description;
    private final String name;

    public DebugOverlayTag(String str, String str2, int i) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, "description");
        this.name = str;
        this.description = str2;
        this.color = i;
    }
}
