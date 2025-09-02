package com.facebook.react.devsupport.interfaces;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public enum ErrorType {
    JS("JS"),
    NATIVE("Native");
    
    private final String displayName;

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    private ErrorType(String str) {
        this.displayName = str;
    }

    public final String getDisplayName() {
        return this.displayName;
    }

    static {
        ErrorType[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    public String toString() {
        return this.displayName;
    }
}
