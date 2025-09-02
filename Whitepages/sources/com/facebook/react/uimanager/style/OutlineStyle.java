package com.facebook.react.uimanager.style;

import com.facebook.react.uimanager.ViewProps;
import java.util.Locale;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public enum OutlineStyle {
    SOLID,
    DASHED,
    DOTTED;
    
    public static final Companion Companion = null;

    public static final OutlineStyle fromString(String str) {
        return Companion.fromString(str);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    static {
        OutlineStyle[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final OutlineStyle fromString(String str) {
            Intrinsics.checkNotNullParameter(str, ViewProps.OUTLINE_STYLE);
            String lowerCase = str.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            int hashCode = lowerCase.hashCode();
            if (hashCode != -1338941519) {
                if (hashCode != -1325970902) {
                    if (hashCode == 109618859 && lowerCase.equals("solid")) {
                        return OutlineStyle.SOLID;
                    }
                } else if (lowerCase.equals("dotted")) {
                    return OutlineStyle.DOTTED;
                }
            } else if (lowerCase.equals("dashed")) {
                return OutlineStyle.DASHED;
            }
            return null;
        }
    }
}
