package com.facebook.fresco.ui.common;

import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

public enum VisibilityState {
    UNKNOWN(-1),
    VISIBLE(1),
    INVISIBLE(2);
    
    public static final Companion Companion = null;
    private static final VisibilityState[] VALUES = null;
    private final int value;

    private VisibilityState(int i) {
        this.value = i;
    }

    static {
        VisibilityState[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        Companion = new Companion((DefaultConstructorMarker) null);
        VALUES = values();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
