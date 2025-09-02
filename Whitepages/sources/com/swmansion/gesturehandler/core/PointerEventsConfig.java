package com.swmansion.gesturehandler.core;

import kotlin.enums.EnumEntriesKt;

public enum PointerEventsConfig {
    NONE,
    BOX_NONE,
    BOX_ONLY,
    AUTO;

    static {
        PointerEventsConfig[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
