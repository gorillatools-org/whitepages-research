package com.facebook.react.uimanager.style;

import com.facebook.react.uimanager.ViewProps;
import java.util.Locale;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public enum Overflow {
    VISIBLE,
    HIDDEN,
    SCROLL;
    
    public static final Companion Companion = null;

    public static final Overflow fromString(String str) {
        return Companion.fromString(str);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    static {
        Overflow[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Overflow fromString(String str) {
            Intrinsics.checkNotNullParameter(str, ViewProps.OVERFLOW);
            String lowerCase = str.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            int hashCode = lowerCase.hashCode();
            if (hashCode != -1217487446) {
                if (hashCode != -907680051) {
                    if (hashCode == 466743410 && lowerCase.equals(ViewProps.VISIBLE)) {
                        return Overflow.VISIBLE;
                    }
                } else if (lowerCase.equals(ViewProps.SCROLL)) {
                    return Overflow.SCROLL;
                }
            } else if (lowerCase.equals(ViewProps.HIDDEN)) {
                return Overflow.HIDDEN;
            }
            return null;
        }
    }
}
