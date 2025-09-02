package com.facebook.react.views.text;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

public enum TextTransform {
    NONE,
    UPPERCASE,
    LOWERCASE,
    CAPITALIZE,
    UNSET;
    
    public static final Companion Companion = null;

    public static final String apply(String str, TextTransform textTransform) {
        return Companion.apply(str, textTransform);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    static {
        TextTransform[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String apply(String str, TextTransform textTransform) {
            if (str != null) {
                return TextTransformKt.applyTextTransform(str, textTransform);
            }
            return null;
        }
    }
}
