package com.facebook.react.modules.fresco;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public enum ImageCacheControl {
    DEFAULT,
    RELOAD,
    FORCE_CACHE,
    ONLY_IF_CACHED;

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    static {
        ImageCacheControl[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
