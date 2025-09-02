package kotlin;

import kotlin.enums.EnumEntriesKt;

public enum LazyThreadSafetyMode {
    SYNCHRONIZED,
    PUBLICATION,
    NONE;

    static {
        LazyThreadSafetyMode[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
