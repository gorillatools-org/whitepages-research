package com.facebook.imagepipeline.core;

import kotlin.enums.EnumEntriesKt;

public enum DownsampleMode {
    ALWAYS,
    AUTO,
    NEVER;

    static {
        DownsampleMode[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
