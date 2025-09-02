package com.facebook.react.uimanager.style;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public enum BorderRadiusProp {
    BORDER_RADIUS,
    BORDER_TOP_LEFT_RADIUS,
    BORDER_TOP_RIGHT_RADIUS,
    BORDER_BOTTOM_RIGHT_RADIUS,
    BORDER_BOTTOM_LEFT_RADIUS,
    BORDER_TOP_START_RADIUS,
    BORDER_TOP_END_RADIUS,
    BORDER_BOTTOM_START_RADIUS,
    BORDER_BOTTOM_END_RADIUS,
    BORDER_END_END_RADIUS,
    BORDER_END_START_RADIUS,
    BORDER_START_END_RADIUS,
    BORDER_START_START_RADIUS;

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    static {
        BorderRadiusProp[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
