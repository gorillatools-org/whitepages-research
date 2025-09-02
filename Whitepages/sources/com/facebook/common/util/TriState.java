package com.facebook.common.util;

public enum TriState {
    YES,
    NO,
    UNSET;

    public boolean isSet() {
        return this != UNSET;
    }

    public static TriState valueOf(boolean z) {
        return z ? YES : NO;
    }

    /* renamed from: com.facebook.common.util.TriState$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$common$util$TriState = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.facebook.common.util.TriState[] r0 = com.facebook.common.util.TriState.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$facebook$common$util$TriState = r0
                com.facebook.common.util.TriState r1 = com.facebook.common.util.TriState.YES     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$facebook$common$util$TriState     // Catch:{ NoSuchFieldError -> 0x001d }
                com.facebook.common.util.TriState r1 = com.facebook.common.util.TriState.NO     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$facebook$common$util$TriState     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.facebook.common.util.TriState r1 = com.facebook.common.util.TriState.UNSET     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.util.TriState.AnonymousClass1.<clinit>():void");
        }
    }

    public boolean asBoolean() {
        int i = AnonymousClass1.$SwitchMap$com$facebook$common$util$TriState[ordinal()];
        if (i == 1) {
            return true;
        }
        if (i == 2) {
            return false;
        }
        if (i != 3) {
            throw new IllegalStateException("Unrecognized TriState value: " + this);
        }
        throw new IllegalStateException("No boolean equivalent for UNSET");
    }
}
