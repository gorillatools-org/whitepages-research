package kotlin.reflect;

import kotlin.enums.EnumEntriesKt;

public enum KVisibility {
    PUBLIC,
    PROTECTED,
    INTERNAL,
    PRIVATE;

    static {
        KVisibility[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
