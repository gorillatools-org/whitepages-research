package com.facebook.imagepipeline.common;

import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public enum Priority {
    LOW,
    MEDIUM,
    HIGH;
    
    public static final Companion Companion = null;

    public static final Priority getHigherPriority(Priority priority, Priority priority2) {
        return Companion.getHigherPriority(priority, priority2);
    }

    static {
        Priority[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Priority getHigherPriority(Priority priority, Priority priority2) {
            Intrinsics.checkNotNullParameter(priority, "priority1");
            Intrinsics.checkNotNullParameter(priority2, "priority2");
            return priority.ordinal() > priority2.ordinal() ? priority : priority2;
        }
    }
}
