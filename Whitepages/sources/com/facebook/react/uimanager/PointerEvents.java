package com.facebook.react.uimanager;

import java.util.Locale;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public enum PointerEvents {
    NONE,
    BOX_NONE,
    BOX_ONLY,
    AUTO;
    
    public static final Companion Companion = null;

    public static final boolean canBeTouchTarget(PointerEvents pointerEvents) {
        return Companion.canBeTouchTarget(pointerEvents);
    }

    public static final boolean canChildrenBeTouchTarget(PointerEvents pointerEvents) {
        return Companion.canChildrenBeTouchTarget(pointerEvents);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static final PointerEvents parsePointerEvents(String str) {
        return Companion.parsePointerEvents(str);
    }

    static {
        PointerEvents[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final PointerEvents parsePointerEvents(String str) {
            if (str == null) {
                return PointerEvents.AUTO;
            }
            Locale locale = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale, "US");
            String upperCase = str.toUpperCase(locale);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            return PointerEvents.valueOf(StringsKt.replace$default(upperCase, "-", "_", false, 4, (Object) null));
        }

        public final boolean canBeTouchTarget(PointerEvents pointerEvents) {
            Intrinsics.checkNotNullParameter(pointerEvents, ViewProps.POINTER_EVENTS);
            return pointerEvents == PointerEvents.AUTO || pointerEvents == PointerEvents.BOX_ONLY;
        }

        public final boolean canChildrenBeTouchTarget(PointerEvents pointerEvents) {
            Intrinsics.checkNotNullParameter(pointerEvents, ViewProps.POINTER_EVENTS);
            return pointerEvents == PointerEvents.AUTO || pointerEvents == PointerEvents.BOX_NONE;
        }
    }
}
