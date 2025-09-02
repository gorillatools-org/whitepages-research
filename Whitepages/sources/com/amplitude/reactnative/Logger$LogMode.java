package com.amplitude.reactnative;

import kotlin.enums.EnumEntriesKt;

public enum Logger$LogMode {
    DEBUG(1),
    INFO(2),
    WARN(3),
    ERROR(4),
    OFF(5);

    private Logger$LogMode(int i) {
    }

    static {
        Logger$LogMode[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
