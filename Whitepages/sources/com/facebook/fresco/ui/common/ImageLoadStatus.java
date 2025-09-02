package com.facebook.fresco.ui.common;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.messages.iam.j;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

public enum ImageLoadStatus {
    UNKNOWN(-1),
    REQUESTED(0),
    INTERMEDIATE_AVAILABLE(2),
    SUCCESS(3),
    ERROR(5),
    EMPTY_EVENT(7),
    RELEASED(8);
    
    public static final Companion Companion = null;
    private static final ImageLoadStatus[] VALUES = null;
    private final int value;

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
                com.facebook.fresco.ui.common.ImageLoadStatus[] r0 = com.facebook.fresco.ui.common.ImageLoadStatus.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.fresco.ui.common.ImageLoadStatus r1 = com.facebook.fresco.ui.common.ImageLoadStatus.REQUESTED     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.fresco.ui.common.ImageLoadStatus r1 = com.facebook.fresco.ui.common.ImageLoadStatus.SUCCESS     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.facebook.fresco.ui.common.ImageLoadStatus r1 = com.facebook.fresco.ui.common.ImageLoadStatus.INTERMEDIATE_AVAILABLE     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.facebook.fresco.ui.common.ImageLoadStatus r1 = com.facebook.fresco.ui.common.ImageLoadStatus.ERROR     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                com.facebook.fresco.ui.common.ImageLoadStatus r1 = com.facebook.fresco.ui.common.ImageLoadStatus.RELEASED     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.fresco.ui.common.ImageLoadStatus.WhenMappings.<clinit>():void");
        }
    }

    private ImageLoadStatus(int i) {
        this.value = i;
    }

    static {
        ImageLoadStatus[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
        Companion = new Companion((DefaultConstructorMarker) null);
        VALUES = values();
    }

    public String toString() {
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return "requested";
        }
        if (i == 2) {
            return FirebaseAnalytics.Param.SUCCESS;
        }
        if (i == 3) {
            return "intermediate_available";
        }
        if (i == 4) {
            return "error";
        }
        if (i != 5) {
            return j.h;
        }
        return "released";
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
