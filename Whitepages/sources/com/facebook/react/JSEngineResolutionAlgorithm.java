package com.facebook.react;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public enum JSEngineResolutionAlgorithm {
    JSC,
    HERMES;

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    static {
        JSEngineResolutionAlgorithm[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
