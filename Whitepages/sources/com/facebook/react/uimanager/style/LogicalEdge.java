package com.facebook.react.uimanager.style;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

public enum LogicalEdge {
    ;
    
    public static final Companion Companion = null;

    public static final LogicalEdge fromSpacingType(int i) {
        return Companion.fromSpacingType(i);
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public abstract int toSpacingType();

    static final class ALL extends LogicalEdge {
        public int toSpacingType() {
            return 8;
        }

        ALL(String str, int i) {
            super(str, i, (DefaultConstructorMarker) null);
        }
    }

    static {
        LogicalEdge[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    static final class LEFT extends LogicalEdge {
        public int toSpacingType() {
            return 0;
        }

        LEFT(String str, int i) {
            super(str, i, (DefaultConstructorMarker) null);
        }
    }

    static final class RIGHT extends LogicalEdge {
        public int toSpacingType() {
            return 2;
        }

        RIGHT(String str, int i) {
            super(str, i, (DefaultConstructorMarker) null);
        }
    }

    static final class TOP extends LogicalEdge {
        public int toSpacingType() {
            return 1;
        }

        TOP(String str, int i) {
            super(str, i, (DefaultConstructorMarker) null);
        }
    }

    static final class BOTTOM extends LogicalEdge {
        public int toSpacingType() {
            return 3;
        }

        BOTTOM(String str, int i) {
            super(str, i, (DefaultConstructorMarker) null);
        }
    }

    static final class START extends LogicalEdge {
        public int toSpacingType() {
            return 4;
        }

        START(String str, int i) {
            super(str, i, (DefaultConstructorMarker) null);
        }
    }

    static final class END extends LogicalEdge {
        public int toSpacingType() {
            return 5;
        }

        END(String str, int i) {
            super(str, i, (DefaultConstructorMarker) null);
        }
    }

    static final class HORIZONTAL extends LogicalEdge {
        public int toSpacingType() {
            return 6;
        }

        HORIZONTAL(String str, int i) {
            super(str, i, (DefaultConstructorMarker) null);
        }
    }

    static final class VERTICAL extends LogicalEdge {
        public int toSpacingType() {
            return 7;
        }

        VERTICAL(String str, int i) {
            super(str, i, (DefaultConstructorMarker) null);
        }
    }

    static final class BLOCK_START extends LogicalEdge {
        public int toSpacingType() {
            return 11;
        }

        BLOCK_START(String str, int i) {
            super(str, i, (DefaultConstructorMarker) null);
        }
    }

    static final class BLOCK_END extends LogicalEdge {
        public int toSpacingType() {
            return 10;
        }

        BLOCK_END(String str, int i) {
            super(str, i, (DefaultConstructorMarker) null);
        }
    }

    static final class BLOCK extends LogicalEdge {
        public int toSpacingType() {
            return 9;
        }

        BLOCK(String str, int i) {
            super(str, i, (DefaultConstructorMarker) null);
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LogicalEdge fromSpacingType(int i) {
            switch (i) {
                case 0:
                    return LogicalEdge.LEFT;
                case 1:
                    return LogicalEdge.TOP;
                case 2:
                    return LogicalEdge.RIGHT;
                case 3:
                    return LogicalEdge.BOTTOM;
                case 4:
                    return LogicalEdge.START;
                case 5:
                    return LogicalEdge.END;
                case 6:
                    return LogicalEdge.HORIZONTAL;
                case 7:
                    return LogicalEdge.VERTICAL;
                case 8:
                    return LogicalEdge.ALL;
                case 9:
                    return LogicalEdge.BLOCK;
                case 10:
                    return LogicalEdge.BLOCK_END;
                case 11:
                    return LogicalEdge.BLOCK_START;
                default:
                    throw new IllegalArgumentException("Unknown spacing type: " + i);
            }
        }
    }
}
