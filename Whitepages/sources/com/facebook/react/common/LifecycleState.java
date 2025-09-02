package com.facebook.react.common;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public enum LifecycleState {
    BEFORE_CREATE,
    BEFORE_RESUME,
    RESUMED;

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    static {
        LifecycleState[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
