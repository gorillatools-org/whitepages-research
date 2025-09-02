package com.facebook.react.views.scroll;

import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public enum ScrollEventType {
    BEGIN_DRAG,
    END_DRAG,
    SCROLL,
    MOMENTUM_BEGIN,
    MOMENTUM_END;
    
    public static final Companion Companion = null;

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static final String getJSEventName(ScrollEventType scrollEventType) {
        return Companion.getJSEventName(scrollEventType);
    }

    static {
        ScrollEventType[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    public static final class Companion {

        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

            /* JADX WARNING: Can't wrap try/catch for region: R(13:0|1|2|3|4|5|6|7|8|9|10|11|13) */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
            static {
                /*
                    com.facebook.react.views.scroll.ScrollEventType[] r0 = com.facebook.react.views.scroll.ScrollEventType.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    com.facebook.react.views.scroll.ScrollEventType r1 = com.facebook.react.views.scroll.ScrollEventType.BEGIN_DRAG     // Catch:{ NoSuchFieldError -> 0x0010 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                    r2 = 1
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                L_0x0010:
                    com.facebook.react.views.scroll.ScrollEventType r1 = com.facebook.react.views.scroll.ScrollEventType.END_DRAG     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r2 = 2
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    com.facebook.react.views.scroll.ScrollEventType r1 = com.facebook.react.views.scroll.ScrollEventType.SCROLL     // Catch:{ NoSuchFieldError -> 0x0022 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                    r2 = 3
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
                L_0x0022:
                    com.facebook.react.views.scroll.ScrollEventType r1 = com.facebook.react.views.scroll.ScrollEventType.MOMENTUM_BEGIN     // Catch:{ NoSuchFieldError -> 0x002b }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                    r2 = 4
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
                L_0x002b:
                    com.facebook.react.views.scroll.ScrollEventType r1 = com.facebook.react.views.scroll.ScrollEventType.MOMENTUM_END     // Catch:{ NoSuchFieldError -> 0x0034 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                    r2 = 5
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
                L_0x0034:
                    $EnumSwitchMapping$0 = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.scroll.ScrollEventType.Companion.WhenMappings.<clinit>():void");
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getJSEventName(ScrollEventType scrollEventType) {
            Intrinsics.checkNotNullParameter(scrollEventType, "type");
            int i = WhenMappings.$EnumSwitchMapping$0[scrollEventType.ordinal()];
            if (i == 1) {
                return "topScrollBeginDrag";
            }
            if (i == 2) {
                return "topScrollEndDrag";
            }
            if (i == 3) {
                return "topScroll";
            }
            if (i == 4) {
                return "topMomentumScrollBegin";
            }
            if (i == 5) {
                return "topMomentumScrollEnd";
            }
            throw new NoWhenBranchMatchedException();
        }
    }
}
