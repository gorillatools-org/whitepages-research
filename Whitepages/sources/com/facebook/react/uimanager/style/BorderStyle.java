package com.facebook.react.uimanager.style;

import java.util.Locale;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public enum BorderStyle {
    SOLID,
    DASHED,
    DOTTED;
    
    public static final Companion Companion = null;

    public static final BorderStyle fromString(String str) {
        return Companion.fromString(str);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    static {
        BorderStyle[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final BorderStyle fromString(String str) {
            Intrinsics.checkNotNullParameter(str, "borderStyle");
            String lowerCase = str.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            int hashCode = lowerCase.hashCode();
            if (hashCode != -1338941519) {
                if (hashCode != -1325970902) {
                    if (hashCode == 109618859 && lowerCase.equals("solid")) {
                        return BorderStyle.SOLID;
                    }
                } else if (lowerCase.equals("dotted")) {
                    return BorderStyle.DOTTED;
                }
            } else if (lowerCase.equals("dashed")) {
                return BorderStyle.DASHED;
            }
            return null;
        }
    }
}
