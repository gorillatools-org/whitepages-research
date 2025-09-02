package com.facebook.react.bridge;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public enum MemoryPressure {
    UI_HIDDEN,
    MODERATE,
    CRITICAL;

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    static {
        MemoryPressure[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
