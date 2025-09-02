package com.facebook.react.uimanager.style;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public enum ComputedBorderRadiusProp {
    COMPUTED_BORDER_TOP_LEFT_RADIUS,
    COMPUTED_BORDER_TOP_RIGHT_RADIUS,
    COMPUTED_BORDER_BOTTOM_RIGHT_RADIUS,
    COMPUTED_BORDER_BOTTOM_LEFT_RADIUS;

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    static {
        ComputedBorderRadiusProp[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
