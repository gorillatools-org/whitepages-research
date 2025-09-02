package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

@DoNotStrip
public enum ReadableType {
    Null,
    Boolean,
    Number,
    String,
    Map,
    Array;

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    static {
        ReadableType[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
