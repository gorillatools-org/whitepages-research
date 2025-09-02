package com.th3rdwave.safeareacontext;

import kotlin.enums.EnumEntriesKt;

public enum SafeAreaViewEdgeModes {
    OFF,
    ADDITIVE,
    MAXIMUM;

    static {
        SafeAreaViewEdgeModes[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
