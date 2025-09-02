package com.facebook.react.uimanager;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public enum NativeKind {
    PARENT,
    LEAF,
    NONE;

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    static {
        NativeKind[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
