package com.facebook.react.uimanager.layoutanimation;

import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public enum LayoutAnimationType {
    CREATE,
    UPDATE,
    DELETE;
    
    public static final Companion Companion = null;

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static final String toString(LayoutAnimationType layoutAnimationType) {
        return Companion.toString(layoutAnimationType);
    }

    static {
        LayoutAnimationType[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    public static final class Companion {

        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

            /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
            static {
                /*
                    com.facebook.react.uimanager.layoutanimation.LayoutAnimationType[] r0 = com.facebook.react.uimanager.layoutanimation.LayoutAnimationType.values()
                    int r0 = r0.length
                    int[] r0 = new int[r0]
                    com.facebook.react.uimanager.layoutanimation.LayoutAnimationType r1 = com.facebook.react.uimanager.layoutanimation.LayoutAnimationType.CREATE     // Catch:{ NoSuchFieldError -> 0x0010 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                    r2 = 1
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
                L_0x0010:
                    com.facebook.react.uimanager.layoutanimation.LayoutAnimationType r1 = com.facebook.react.uimanager.layoutanimation.LayoutAnimationType.UPDATE     // Catch:{ NoSuchFieldError -> 0x0019 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                    r2 = 2
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
                L_0x0019:
                    com.facebook.react.uimanager.layoutanimation.LayoutAnimationType r1 = com.facebook.react.uimanager.layoutanimation.LayoutAnimationType.DELETE     // Catch:{ NoSuchFieldError -> 0x0022 }
                    int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                    r2 = 3
                    r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
                L_0x0022:
                    $EnumSwitchMapping$0 = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.layoutanimation.LayoutAnimationType.Companion.WhenMappings.<clinit>():void");
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String toString(LayoutAnimationType layoutAnimationType) {
            Intrinsics.checkNotNullParameter(layoutAnimationType, "type");
            int i = WhenMappings.$EnumSwitchMapping$0[layoutAnimationType.ordinal()];
            if (i == 1) {
                return "create";
            }
            if (i == 2) {
                return "update";
            }
            if (i == 3) {
                return "delete";
            }
            throw new NoWhenBranchMatchedException();
        }
    }
}
