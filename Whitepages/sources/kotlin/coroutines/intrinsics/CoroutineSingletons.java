package kotlin.coroutines.intrinsics;

import kotlin.enums.EnumEntriesKt;

public enum CoroutineSingletons {
    COROUTINE_SUSPENDED,
    UNDECIDED,
    RESUMED;

    static {
        CoroutineSingletons[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }
}
