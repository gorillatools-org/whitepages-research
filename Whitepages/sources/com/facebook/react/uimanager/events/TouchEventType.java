package com.facebook.react.uimanager.events;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public enum TouchEventType {
    START("topTouchStart"),
    END("topTouchEnd"),
    MOVE("topTouchMove"),
    CANCEL("topTouchCancel");
    
    public static final Companion Companion = null;
    private final String jsName;

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static final String getJSEventName(TouchEventType touchEventType) {
        return Companion.getJSEventName(touchEventType);
    }

    private TouchEventType(String str) {
        this.jsName = str;
    }

    static {
        TouchEventType[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    public final String getJsName() {
        return this.jsName;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getJSEventName(TouchEventType touchEventType) {
            Intrinsics.checkNotNullParameter(touchEventType, "type");
            return touchEventType.getJsName();
        }
    }
}
