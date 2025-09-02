package com.facebook.react.uimanager.style;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

public final class ComputedBorderRadius {
    private final CornerRadii bottomLeft;
    private final CornerRadii bottomRight;
    private final CornerRadii topLeft;
    private final CornerRadii topRight;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        static {
            /*
                com.facebook.react.uimanager.style.ComputedBorderRadiusProp[] r0 = com.facebook.react.uimanager.style.ComputedBorderRadiusProp.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.react.uimanager.style.ComputedBorderRadiusProp r1 = com.facebook.react.uimanager.style.ComputedBorderRadiusProp.COMPUTED_BORDER_TOP_LEFT_RADIUS     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.react.uimanager.style.ComputedBorderRadiusProp r1 = com.facebook.react.uimanager.style.ComputedBorderRadiusProp.COMPUTED_BORDER_TOP_RIGHT_RADIUS     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.facebook.react.uimanager.style.ComputedBorderRadiusProp r1 = com.facebook.react.uimanager.style.ComputedBorderRadiusProp.COMPUTED_BORDER_BOTTOM_LEFT_RADIUS     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.facebook.react.uimanager.style.ComputedBorderRadiusProp r1 = com.facebook.react.uimanager.style.ComputedBorderRadiusProp.COMPUTED_BORDER_BOTTOM_RIGHT_RADIUS     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.style.ComputedBorderRadius.WhenMappings.<clinit>():void");
        }
    }

    public static /* synthetic */ ComputedBorderRadius copy$default(ComputedBorderRadius computedBorderRadius, CornerRadii cornerRadii, CornerRadii cornerRadii2, CornerRadii cornerRadii3, CornerRadii cornerRadii4, int i, Object obj) {
        if ((i & 1) != 0) {
            cornerRadii = computedBorderRadius.topLeft;
        }
        if ((i & 2) != 0) {
            cornerRadii2 = computedBorderRadius.topRight;
        }
        if ((i & 4) != 0) {
            cornerRadii3 = computedBorderRadius.bottomLeft;
        }
        if ((i & 8) != 0) {
            cornerRadii4 = computedBorderRadius.bottomRight;
        }
        return computedBorderRadius.copy(cornerRadii, cornerRadii2, cornerRadii3, cornerRadii4);
    }

    public final CornerRadii component1() {
        return this.topLeft;
    }

    public final CornerRadii component2() {
        return this.topRight;
    }

    public final CornerRadii component3() {
        return this.bottomLeft;
    }

    public final CornerRadii component4() {
        return this.bottomRight;
    }

    public final ComputedBorderRadius copy(CornerRadii cornerRadii, CornerRadii cornerRadii2, CornerRadii cornerRadii3, CornerRadii cornerRadii4) {
        Intrinsics.checkNotNullParameter(cornerRadii, "topLeft");
        Intrinsics.checkNotNullParameter(cornerRadii2, "topRight");
        Intrinsics.checkNotNullParameter(cornerRadii3, "bottomLeft");
        Intrinsics.checkNotNullParameter(cornerRadii4, "bottomRight");
        return new ComputedBorderRadius(cornerRadii, cornerRadii2, cornerRadii3, cornerRadii4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ComputedBorderRadius)) {
            return false;
        }
        ComputedBorderRadius computedBorderRadius = (ComputedBorderRadius) obj;
        return Intrinsics.areEqual((Object) this.topLeft, (Object) computedBorderRadius.topLeft) && Intrinsics.areEqual((Object) this.topRight, (Object) computedBorderRadius.topRight) && Intrinsics.areEqual((Object) this.bottomLeft, (Object) computedBorderRadius.bottomLeft) && Intrinsics.areEqual((Object) this.bottomRight, (Object) computedBorderRadius.bottomRight);
    }

    public int hashCode() {
        return (((((this.topLeft.hashCode() * 31) + this.topRight.hashCode()) * 31) + this.bottomLeft.hashCode()) * 31) + this.bottomRight.hashCode();
    }

    public String toString() {
        CornerRadii cornerRadii = this.topLeft;
        CornerRadii cornerRadii2 = this.topRight;
        CornerRadii cornerRadii3 = this.bottomLeft;
        CornerRadii cornerRadii4 = this.bottomRight;
        return "ComputedBorderRadius(topLeft=" + cornerRadii + ", topRight=" + cornerRadii2 + ", bottomLeft=" + cornerRadii3 + ", bottomRight=" + cornerRadii4 + ")";
    }

    public ComputedBorderRadius(CornerRadii cornerRadii, CornerRadii cornerRadii2, CornerRadii cornerRadii3, CornerRadii cornerRadii4) {
        Intrinsics.checkNotNullParameter(cornerRadii, "topLeft");
        Intrinsics.checkNotNullParameter(cornerRadii2, "topRight");
        Intrinsics.checkNotNullParameter(cornerRadii3, "bottomLeft");
        Intrinsics.checkNotNullParameter(cornerRadii4, "bottomRight");
        this.topLeft = cornerRadii;
        this.topRight = cornerRadii2;
        this.bottomLeft = cornerRadii3;
        this.bottomRight = cornerRadii4;
    }

    public final CornerRadii getTopLeft() {
        return this.topLeft;
    }

    public final CornerRadii getTopRight() {
        return this.topRight;
    }

    public final CornerRadii getBottomLeft() {
        return this.bottomLeft;
    }

    public final CornerRadii getBottomRight() {
        return this.bottomRight;
    }

    public final boolean hasRoundedBorders() {
        return this.topLeft.getHorizontal() > 0.0f || this.topLeft.getVertical() > 0.0f || this.topRight.getHorizontal() > 0.0f || this.topRight.getVertical() > 0.0f || this.bottomLeft.getHorizontal() > 0.0f || this.bottomLeft.getVertical() > 0.0f || this.bottomRight.getHorizontal() > 0.0f;
    }

    public final boolean isUniform() {
        return Intrinsics.areEqual((Object) this.topLeft, (Object) this.topRight) && Intrinsics.areEqual((Object) this.topLeft, (Object) this.bottomLeft) && Intrinsics.areEqual((Object) this.topLeft, (Object) this.bottomRight);
    }

    public final CornerRadii get(ComputedBorderRadiusProp computedBorderRadiusProp) {
        Intrinsics.checkNotNullParameter(computedBorderRadiusProp, "property");
        int i = WhenMappings.$EnumSwitchMapping$0[computedBorderRadiusProp.ordinal()];
        if (i == 1) {
            return this.topLeft;
        }
        if (i == 2) {
            return this.topRight;
        }
        if (i == 3) {
            return this.bottomLeft;
        }
        if (i == 4) {
            return this.bottomRight;
        }
        throw new NoWhenBranchMatchedException();
    }

    public ComputedBorderRadius() {
        this(new CornerRadii(0.0f, 0.0f), new CornerRadii(0.0f, 0.0f), new CornerRadii(0.0f, 0.0f), new CornerRadii(0.0f, 0.0f));
    }
}
