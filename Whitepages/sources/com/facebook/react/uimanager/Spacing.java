package com.facebook.react.uimanager;

import com.facebook.yoga.YogaConstants;
import com.salesforce.marketingcloud.b;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class Spacing {
    public static final int ALL = 8;
    public static final int BLOCK = 9;
    public static final int BLOCK_END = 10;
    public static final int BLOCK_START = 11;
    public static final int BOTTOM = 3;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int END = 5;
    public static final int HORIZONTAL = 6;
    public static final int LEFT = 0;
    public static final int RIGHT = 2;
    public static final int START = 4;
    public static final int TOP = 1;
    public static final int VERTICAL = 7;
    private static final int[] flagsMap = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, b.u};
    private final float defaultValue;
    private boolean hasAliasesSet;
    private final float[] spacing;
    private int valueFlags;

    public Spacing(float f, float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "spacing");
        this.defaultValue = f;
        this.spacing = fArr;
    }

    public Spacing() {
        this(0.0f, Companion.newFullSpacingArray());
    }

    public Spacing(float f) {
        this(f, Companion.newFullSpacingArray());
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Spacing(com.facebook.react.uimanager.Spacing r4) {
        /*
            r3 = this;
            java.lang.String r0 = "original"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            float r0 = r4.defaultValue
            float[] r1 = r4.spacing
            int r2 = r1.length
            float[] r1 = java.util.Arrays.copyOf(r1, r2)
            java.lang.String r2 = "copyOf(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            r3.<init>(r0, r1)
            int r0 = r4.valueFlags
            r3.valueFlags = r0
            boolean r4 = r4.hasAliasesSet
            r3.hasAliasesSet = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.Spacing.<init>(com.facebook.react.uimanager.Spacing):void");
    }

    public final boolean set(int i, float f) {
        int i2;
        boolean z = false;
        if (FloatUtil.floatsEqual(this.spacing[i], f)) {
            return false;
        }
        this.spacing[i] = f;
        if (YogaConstants.isUndefined(f)) {
            i2 = (~flagsMap[i]) & this.valueFlags;
        } else {
            i2 = flagsMap[i] | this.valueFlags;
        }
        this.valueFlags = i2;
        int[] iArr = flagsMap;
        if (!((iArr[8] & i2) == 0 && (iArr[7] & i2) == 0 && (iArr[6] & i2) == 0 && (i2 & iArr[9]) == 0)) {
            z = true;
        }
        this.hasAliasesSet = z;
        return true;
    }

    public final float get(int i) {
        float f;
        if (!(i == 4 || i == 5)) {
            switch (i) {
                case 9:
                case 10:
                case 11:
                    break;
                default:
                    f = this.defaultValue;
                    break;
            }
        }
        f = Float.NaN;
        int i2 = this.valueFlags;
        if (i2 == 0) {
            return f;
        }
        int[] iArr = flagsMap;
        if ((iArr[i] & i2) != 0) {
            return this.spacing[i];
        }
        if (this.hasAliasesSet) {
            char c = (i == 1 || i == 3) ? (char) 7 : 6;
            if ((iArr[c] & i2) != 0) {
                return this.spacing[c];
            }
            if ((i2 & iArr[8]) != 0) {
                return this.spacing[8];
            }
        }
        return f;
    }

    public final float getRaw(int i) {
        return this.spacing[i];
    }

    public final void reset() {
        ArraysKt.fill$default(this.spacing, Float.NaN, 0, 0, 6, (Object) null);
        this.hasAliasesSet = false;
        this.valueFlags = 0;
    }

    public final float getWithFallback(int i, int i2) {
        if ((this.valueFlags & flagsMap[i]) != 0) {
            return this.spacing[i];
        }
        return get(i2);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final float[] newFullSpacingArray() {
            return new float[]{Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN};
        }
    }
}
