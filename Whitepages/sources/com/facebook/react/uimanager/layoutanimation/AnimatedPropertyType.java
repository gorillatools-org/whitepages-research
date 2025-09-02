package com.facebook.react.uimanager.layoutanimation;

import com.facebook.react.uimanager.ViewProps;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public enum AnimatedPropertyType {
    OPACITY,
    SCALE_X,
    SCALE_Y,
    SCALE_XY;
    
    public static final Companion Companion = null;

    public static final AnimatedPropertyType fromString(String str) {
        return Companion.fromString(str);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    static {
        AnimatedPropertyType[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AnimatedPropertyType fromString(String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            switch (str.hashCode()) {
                case -1267206133:
                    if (str.equals(ViewProps.OPACITY)) {
                        return AnimatedPropertyType.OPACITY;
                    }
                    break;
                case -908189618:
                    if (str.equals(ViewProps.SCALE_X)) {
                        return AnimatedPropertyType.SCALE_X;
                    }
                    break;
                case -908189617:
                    if (str.equals(ViewProps.SCALE_Y)) {
                        return AnimatedPropertyType.SCALE_Y;
                    }
                    break;
                case 1910893003:
                    if (str.equals("scaleXY")) {
                        return AnimatedPropertyType.SCALE_XY;
                    }
                    break;
            }
            throw new IllegalArgumentException("Unsupported animated property: " + str);
        }
    }
}
