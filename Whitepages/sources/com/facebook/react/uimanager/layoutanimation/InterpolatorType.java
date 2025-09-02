package com.facebook.react.uimanager.layoutanimation;

import java.util.Locale;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public enum InterpolatorType {
    LINEAR,
    EASE_IN,
    EASE_OUT,
    EASE_IN_EASE_OUT,
    SPRING;
    
    public static final Companion Companion = null;

    public static final InterpolatorType fromString(String str) {
        return Companion.fromString(str);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    static {
        InterpolatorType[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final InterpolatorType fromString(String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            String lowerCase = str.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            switch (lowerCase.hashCode()) {
                case -1965056864:
                    if (lowerCase.equals("easeout")) {
                        return InterpolatorType.EASE_OUT;
                    }
                    break;
                case -1310315117:
                    if (lowerCase.equals("easein")) {
                        return InterpolatorType.EASE_IN;
                    }
                    break;
                case -1102672091:
                    if (lowerCase.equals("linear")) {
                        return InterpolatorType.LINEAR;
                    }
                    break;
                case -895679987:
                    if (lowerCase.equals("spring")) {
                        return InterpolatorType.SPRING;
                    }
                    break;
                case 1164546989:
                    if (lowerCase.equals("easeineaseout")) {
                        return InterpolatorType.EASE_IN_EASE_OUT;
                    }
                    break;
            }
            throw new IllegalArgumentException("Unsupported interpolation type : " + str);
        }
    }
}
