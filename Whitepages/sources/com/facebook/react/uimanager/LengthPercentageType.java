package com.facebook.react.uimanager;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public enum LengthPercentageType {
    POINT,
    PERCENT;

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    static {
        LengthPercentageType[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
