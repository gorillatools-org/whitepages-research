package com.salesforce.marketingcloud.toggles;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public enum a {
    UNKNOWN,
    ENABLED,
    DISABLED;

    static {
        a[] a;
        e = EnumEntriesKt.enumEntries(a);
    }

    public static EnumEntries b() {
        return e;
    }
}
