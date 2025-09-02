package com.facebook.react.views.image;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public enum ImageResizeMethod {
    AUTO,
    RESIZE,
    SCALE,
    NONE;

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    static {
        ImageResizeMethod[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
