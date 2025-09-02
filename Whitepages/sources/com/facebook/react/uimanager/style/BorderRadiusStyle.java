package com.facebook.react.uimanager.style;

import com.facebook.react.uimanager.LengthPercentage;
import com.salesforce.marketingcloud.b;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class BorderRadiusStyle {
    private LengthPercentage bottomEnd;
    private LengthPercentage bottomLeft;
    private LengthPercentage bottomRight;
    private LengthPercentage bottomStart;
    private LengthPercentage endEnd;
    private LengthPercentage endStart;
    private LengthPercentage startEnd;
    private LengthPercentage startStart;
    private LengthPercentage topEnd;
    private LengthPercentage topLeft;
    private LengthPercentage topRight;
    private LengthPercentage topStart;
    private LengthPercentage uniform;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|(2:25|26)|27|29) */
        /* JADX WARNING: Can't wrap try/catch for region: R(29:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|29) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0046 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0050 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x005a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0064 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                com.facebook.react.uimanager.style.BorderRadiusProp[] r0 = com.facebook.react.uimanager.style.BorderRadiusProp.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.react.uimanager.style.BorderRadiusProp r1 = com.facebook.react.uimanager.style.BorderRadiusProp.BORDER_RADIUS     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.react.uimanager.style.BorderRadiusProp r1 = com.facebook.react.uimanager.style.BorderRadiusProp.BORDER_TOP_LEFT_RADIUS     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.facebook.react.uimanager.style.BorderRadiusProp r1 = com.facebook.react.uimanager.style.BorderRadiusProp.BORDER_TOP_RIGHT_RADIUS     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.facebook.react.uimanager.style.BorderRadiusProp r1 = com.facebook.react.uimanager.style.BorderRadiusProp.BORDER_BOTTOM_LEFT_RADIUS     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                com.facebook.react.uimanager.style.BorderRadiusProp r1 = com.facebook.react.uimanager.style.BorderRadiusProp.BORDER_BOTTOM_RIGHT_RADIUS     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                com.facebook.react.uimanager.style.BorderRadiusProp r1 = com.facebook.react.uimanager.style.BorderRadiusProp.BORDER_TOP_START_RADIUS     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                com.facebook.react.uimanager.style.BorderRadiusProp r1 = com.facebook.react.uimanager.style.BorderRadiusProp.BORDER_TOP_END_RADIUS     // Catch:{ NoSuchFieldError -> 0x0046 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0046 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0046 }
            L_0x0046:
                com.facebook.react.uimanager.style.BorderRadiusProp r1 = com.facebook.react.uimanager.style.BorderRadiusProp.BORDER_BOTTOM_START_RADIUS     // Catch:{ NoSuchFieldError -> 0x0050 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0050 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0050 }
            L_0x0050:
                com.facebook.react.uimanager.style.BorderRadiusProp r1 = com.facebook.react.uimanager.style.BorderRadiusProp.BORDER_BOTTOM_END_RADIUS     // Catch:{ NoSuchFieldError -> 0x005a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005a }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005a }
            L_0x005a:
                com.facebook.react.uimanager.style.BorderRadiusProp r1 = com.facebook.react.uimanager.style.BorderRadiusProp.BORDER_START_START_RADIUS     // Catch:{ NoSuchFieldError -> 0x0064 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0064 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0064 }
            L_0x0064:
                com.facebook.react.uimanager.style.BorderRadiusProp r1 = com.facebook.react.uimanager.style.BorderRadiusProp.BORDER_START_END_RADIUS     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                com.facebook.react.uimanager.style.BorderRadiusProp r1 = com.facebook.react.uimanager.style.BorderRadiusProp.BORDER_END_START_RADIUS     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                com.facebook.react.uimanager.style.BorderRadiusProp r1 = com.facebook.react.uimanager.style.BorderRadiusProp.BORDER_END_END_RADIUS     // Catch:{ NoSuchFieldError -> 0x0082 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0082 }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0082 }
            L_0x0082:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.style.BorderRadiusStyle.WhenMappings.<clinit>():void");
        }
    }

    public BorderRadiusStyle() {
        this((LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, 8191, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ BorderRadiusStyle copy$default(BorderRadiusStyle borderRadiusStyle, LengthPercentage lengthPercentage, LengthPercentage lengthPercentage2, LengthPercentage lengthPercentage3, LengthPercentage lengthPercentage4, LengthPercentage lengthPercentage5, LengthPercentage lengthPercentage6, LengthPercentage lengthPercentage7, LengthPercentage lengthPercentage8, LengthPercentage lengthPercentage9, LengthPercentage lengthPercentage10, LengthPercentage lengthPercentage11, LengthPercentage lengthPercentage12, LengthPercentage lengthPercentage13, int i, Object obj) {
        BorderRadiusStyle borderRadiusStyle2 = borderRadiusStyle;
        int i2 = i;
        return borderRadiusStyle.copy((i2 & 1) != 0 ? borderRadiusStyle2.uniform : lengthPercentage, (i2 & 2) != 0 ? borderRadiusStyle2.topLeft : lengthPercentage2, (i2 & 4) != 0 ? borderRadiusStyle2.topRight : lengthPercentage3, (i2 & 8) != 0 ? borderRadiusStyle2.bottomLeft : lengthPercentage4, (i2 & 16) != 0 ? borderRadiusStyle2.bottomRight : lengthPercentage5, (i2 & 32) != 0 ? borderRadiusStyle2.topStart : lengthPercentage6, (i2 & 64) != 0 ? borderRadiusStyle2.topEnd : lengthPercentage7, (i2 & 128) != 0 ? borderRadiusStyle2.bottomStart : lengthPercentage8, (i2 & 256) != 0 ? borderRadiusStyle2.bottomEnd : lengthPercentage9, (i2 & 512) != 0 ? borderRadiusStyle2.startStart : lengthPercentage10, (i2 & 1024) != 0 ? borderRadiusStyle2.startEnd : lengthPercentage11, (i2 & b.u) != 0 ? borderRadiusStyle2.endStart : lengthPercentage12, (i2 & b.v) != 0 ? borderRadiusStyle2.endEnd : lengthPercentage13);
    }

    public final LengthPercentage component1() {
        return this.uniform;
    }

    public final LengthPercentage component10() {
        return this.startStart;
    }

    public final LengthPercentage component11() {
        return this.startEnd;
    }

    public final LengthPercentage component12() {
        return this.endStart;
    }

    public final LengthPercentage component13() {
        return this.endEnd;
    }

    public final LengthPercentage component2() {
        return this.topLeft;
    }

    public final LengthPercentage component3() {
        return this.topRight;
    }

    public final LengthPercentage component4() {
        return this.bottomLeft;
    }

    public final LengthPercentage component5() {
        return this.bottomRight;
    }

    public final LengthPercentage component6() {
        return this.topStart;
    }

    public final LengthPercentage component7() {
        return this.topEnd;
    }

    public final LengthPercentage component8() {
        return this.bottomStart;
    }

    public final LengthPercentage component9() {
        return this.bottomEnd;
    }

    public final BorderRadiusStyle copy(LengthPercentage lengthPercentage, LengthPercentage lengthPercentage2, LengthPercentage lengthPercentage3, LengthPercentage lengthPercentage4, LengthPercentage lengthPercentage5, LengthPercentage lengthPercentage6, LengthPercentage lengthPercentage7, LengthPercentage lengthPercentage8, LengthPercentage lengthPercentage9, LengthPercentage lengthPercentage10, LengthPercentage lengthPercentage11, LengthPercentage lengthPercentage12, LengthPercentage lengthPercentage13) {
        return new BorderRadiusStyle(lengthPercentage, lengthPercentage2, lengthPercentage3, lengthPercentage4, lengthPercentage5, lengthPercentage6, lengthPercentage7, lengthPercentage8, lengthPercentage9, lengthPercentage10, lengthPercentage11, lengthPercentage12, lengthPercentage13);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BorderRadiusStyle)) {
            return false;
        }
        BorderRadiusStyle borderRadiusStyle = (BorderRadiusStyle) obj;
        return Intrinsics.areEqual((Object) this.uniform, (Object) borderRadiusStyle.uniform) && Intrinsics.areEqual((Object) this.topLeft, (Object) borderRadiusStyle.topLeft) && Intrinsics.areEqual((Object) this.topRight, (Object) borderRadiusStyle.topRight) && Intrinsics.areEqual((Object) this.bottomLeft, (Object) borderRadiusStyle.bottomLeft) && Intrinsics.areEqual((Object) this.bottomRight, (Object) borderRadiusStyle.bottomRight) && Intrinsics.areEqual((Object) this.topStart, (Object) borderRadiusStyle.topStart) && Intrinsics.areEqual((Object) this.topEnd, (Object) borderRadiusStyle.topEnd) && Intrinsics.areEqual((Object) this.bottomStart, (Object) borderRadiusStyle.bottomStart) && Intrinsics.areEqual((Object) this.bottomEnd, (Object) borderRadiusStyle.bottomEnd) && Intrinsics.areEqual((Object) this.startStart, (Object) borderRadiusStyle.startStart) && Intrinsics.areEqual((Object) this.startEnd, (Object) borderRadiusStyle.startEnd) && Intrinsics.areEqual((Object) this.endStart, (Object) borderRadiusStyle.endStart) && Intrinsics.areEqual((Object) this.endEnd, (Object) borderRadiusStyle.endEnd);
    }

    public int hashCode() {
        LengthPercentage lengthPercentage = this.uniform;
        int i = 0;
        int hashCode = (lengthPercentage == null ? 0 : lengthPercentage.hashCode()) * 31;
        LengthPercentage lengthPercentage2 = this.topLeft;
        int hashCode2 = (hashCode + (lengthPercentage2 == null ? 0 : lengthPercentage2.hashCode())) * 31;
        LengthPercentage lengthPercentage3 = this.topRight;
        int hashCode3 = (hashCode2 + (lengthPercentage3 == null ? 0 : lengthPercentage3.hashCode())) * 31;
        LengthPercentage lengthPercentage4 = this.bottomLeft;
        int hashCode4 = (hashCode3 + (lengthPercentage4 == null ? 0 : lengthPercentage4.hashCode())) * 31;
        LengthPercentage lengthPercentage5 = this.bottomRight;
        int hashCode5 = (hashCode4 + (lengthPercentage5 == null ? 0 : lengthPercentage5.hashCode())) * 31;
        LengthPercentage lengthPercentage6 = this.topStart;
        int hashCode6 = (hashCode5 + (lengthPercentage6 == null ? 0 : lengthPercentage6.hashCode())) * 31;
        LengthPercentage lengthPercentage7 = this.topEnd;
        int hashCode7 = (hashCode6 + (lengthPercentage7 == null ? 0 : lengthPercentage7.hashCode())) * 31;
        LengthPercentage lengthPercentage8 = this.bottomStart;
        int hashCode8 = (hashCode7 + (lengthPercentage8 == null ? 0 : lengthPercentage8.hashCode())) * 31;
        LengthPercentage lengthPercentage9 = this.bottomEnd;
        int hashCode9 = (hashCode8 + (lengthPercentage9 == null ? 0 : lengthPercentage9.hashCode())) * 31;
        LengthPercentage lengthPercentage10 = this.startStart;
        int hashCode10 = (hashCode9 + (lengthPercentage10 == null ? 0 : lengthPercentage10.hashCode())) * 31;
        LengthPercentage lengthPercentage11 = this.startEnd;
        int hashCode11 = (hashCode10 + (lengthPercentage11 == null ? 0 : lengthPercentage11.hashCode())) * 31;
        LengthPercentage lengthPercentage12 = this.endStart;
        int hashCode12 = (hashCode11 + (lengthPercentage12 == null ? 0 : lengthPercentage12.hashCode())) * 31;
        LengthPercentage lengthPercentage13 = this.endEnd;
        if (lengthPercentage13 != null) {
            i = lengthPercentage13.hashCode();
        }
        return hashCode12 + i;
    }

    public String toString() {
        LengthPercentage lengthPercentage = this.uniform;
        LengthPercentage lengthPercentage2 = this.topLeft;
        LengthPercentage lengthPercentage3 = this.topRight;
        LengthPercentage lengthPercentage4 = this.bottomLeft;
        LengthPercentage lengthPercentage5 = this.bottomRight;
        LengthPercentage lengthPercentage6 = this.topStart;
        LengthPercentage lengthPercentage7 = this.topEnd;
        LengthPercentage lengthPercentage8 = this.bottomStart;
        LengthPercentage lengthPercentage9 = this.bottomEnd;
        LengthPercentage lengthPercentage10 = this.startStart;
        LengthPercentage lengthPercentage11 = this.startEnd;
        LengthPercentage lengthPercentage12 = this.endStart;
        LengthPercentage lengthPercentage13 = this.endEnd;
        return "BorderRadiusStyle(uniform=" + lengthPercentage + ", topLeft=" + lengthPercentage2 + ", topRight=" + lengthPercentage3 + ", bottomLeft=" + lengthPercentage4 + ", bottomRight=" + lengthPercentage5 + ", topStart=" + lengthPercentage6 + ", topEnd=" + lengthPercentage7 + ", bottomStart=" + lengthPercentage8 + ", bottomEnd=" + lengthPercentage9 + ", startStart=" + lengthPercentage10 + ", startEnd=" + lengthPercentage11 + ", endStart=" + lengthPercentage12 + ", endEnd=" + lengthPercentage13 + ")";
    }

    public BorderRadiusStyle(LengthPercentage lengthPercentage, LengthPercentage lengthPercentage2, LengthPercentage lengthPercentage3, LengthPercentage lengthPercentage4, LengthPercentage lengthPercentage5, LengthPercentage lengthPercentage6, LengthPercentage lengthPercentage7, LengthPercentage lengthPercentage8, LengthPercentage lengthPercentage9, LengthPercentage lengthPercentage10, LengthPercentage lengthPercentage11, LengthPercentage lengthPercentage12, LengthPercentage lengthPercentage13) {
        this.uniform = lengthPercentage;
        this.topLeft = lengthPercentage2;
        this.topRight = lengthPercentage3;
        this.bottomLeft = lengthPercentage4;
        this.bottomRight = lengthPercentage5;
        this.topStart = lengthPercentage6;
        this.topEnd = lengthPercentage7;
        this.bottomStart = lengthPercentage8;
        this.bottomEnd = lengthPercentage9;
        this.startStart = lengthPercentage10;
        this.startEnd = lengthPercentage11;
        this.endStart = lengthPercentage12;
        this.endEnd = lengthPercentage13;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ BorderRadiusStyle(com.facebook.react.uimanager.LengthPercentage r15, com.facebook.react.uimanager.LengthPercentage r16, com.facebook.react.uimanager.LengthPercentage r17, com.facebook.react.uimanager.LengthPercentage r18, com.facebook.react.uimanager.LengthPercentage r19, com.facebook.react.uimanager.LengthPercentage r20, com.facebook.react.uimanager.LengthPercentage r21, com.facebook.react.uimanager.LengthPercentage r22, com.facebook.react.uimanager.LengthPercentage r23, com.facebook.react.uimanager.LengthPercentage r24, com.facebook.react.uimanager.LengthPercentage r25, com.facebook.react.uimanager.LengthPercentage r26, com.facebook.react.uimanager.LengthPercentage r27, int r28, kotlin.jvm.internal.DefaultConstructorMarker r29) {
        /*
            r14 = this;
            r0 = r28
            r1 = r0 & 1
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r1 = r2
            goto L_0x000a
        L_0x0009:
            r1 = r15
        L_0x000a:
            r3 = r0 & 2
            if (r3 == 0) goto L_0x0010
            r3 = r2
            goto L_0x0012
        L_0x0010:
            r3 = r16
        L_0x0012:
            r4 = r0 & 4
            if (r4 == 0) goto L_0x0018
            r4 = r2
            goto L_0x001a
        L_0x0018:
            r4 = r17
        L_0x001a:
            r5 = r0 & 8
            if (r5 == 0) goto L_0x0020
            r5 = r2
            goto L_0x0022
        L_0x0020:
            r5 = r18
        L_0x0022:
            r6 = r0 & 16
            if (r6 == 0) goto L_0x0028
            r6 = r2
            goto L_0x002a
        L_0x0028:
            r6 = r19
        L_0x002a:
            r7 = r0 & 32
            if (r7 == 0) goto L_0x0030
            r7 = r2
            goto L_0x0032
        L_0x0030:
            r7 = r20
        L_0x0032:
            r8 = r0 & 64
            if (r8 == 0) goto L_0x0038
            r8 = r2
            goto L_0x003a
        L_0x0038:
            r8 = r21
        L_0x003a:
            r9 = r0 & 128(0x80, float:1.794E-43)
            if (r9 == 0) goto L_0x0040
            r9 = r2
            goto L_0x0042
        L_0x0040:
            r9 = r22
        L_0x0042:
            r10 = r0 & 256(0x100, float:3.59E-43)
            if (r10 == 0) goto L_0x0048
            r10 = r2
            goto L_0x004a
        L_0x0048:
            r10 = r23
        L_0x004a:
            r11 = r0 & 512(0x200, float:7.175E-43)
            if (r11 == 0) goto L_0x0050
            r11 = r2
            goto L_0x0052
        L_0x0050:
            r11 = r24
        L_0x0052:
            r12 = r0 & 1024(0x400, float:1.435E-42)
            if (r12 == 0) goto L_0x0058
            r12 = r2
            goto L_0x005a
        L_0x0058:
            r12 = r25
        L_0x005a:
            r13 = r0 & 2048(0x800, float:2.87E-42)
            if (r13 == 0) goto L_0x0060
            r13 = r2
            goto L_0x0062
        L_0x0060:
            r13 = r26
        L_0x0062:
            r0 = r0 & 4096(0x1000, float:5.74E-42)
            if (r0 == 0) goto L_0x0067
            goto L_0x0069
        L_0x0067:
            r2 = r27
        L_0x0069:
            r15 = r14
            r16 = r1
            r17 = r3
            r18 = r4
            r19 = r5
            r20 = r6
            r21 = r7
            r22 = r8
            r23 = r9
            r24 = r10
            r25 = r11
            r26 = r12
            r27 = r13
            r28 = r2
            r15.<init>(r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.style.BorderRadiusStyle.<init>(com.facebook.react.uimanager.LengthPercentage, com.facebook.react.uimanager.LengthPercentage, com.facebook.react.uimanager.LengthPercentage, com.facebook.react.uimanager.LengthPercentage, com.facebook.react.uimanager.LengthPercentage, com.facebook.react.uimanager.LengthPercentage, com.facebook.react.uimanager.LengthPercentage, com.facebook.react.uimanager.LengthPercentage, com.facebook.react.uimanager.LengthPercentage, com.facebook.react.uimanager.LengthPercentage, com.facebook.react.uimanager.LengthPercentage, com.facebook.react.uimanager.LengthPercentage, com.facebook.react.uimanager.LengthPercentage, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final LengthPercentage getUniform() {
        return this.uniform;
    }

    public final void setUniform(LengthPercentage lengthPercentage) {
        this.uniform = lengthPercentage;
    }

    public final LengthPercentage getTopLeft() {
        return this.topLeft;
    }

    public final void setTopLeft(LengthPercentage lengthPercentage) {
        this.topLeft = lengthPercentage;
    }

    public final LengthPercentage getTopRight() {
        return this.topRight;
    }

    public final void setTopRight(LengthPercentage lengthPercentage) {
        this.topRight = lengthPercentage;
    }

    public final LengthPercentage getBottomLeft() {
        return this.bottomLeft;
    }

    public final void setBottomLeft(LengthPercentage lengthPercentage) {
        this.bottomLeft = lengthPercentage;
    }

    public final LengthPercentage getBottomRight() {
        return this.bottomRight;
    }

    public final void setBottomRight(LengthPercentage lengthPercentage) {
        this.bottomRight = lengthPercentage;
    }

    public final LengthPercentage getTopStart() {
        return this.topStart;
    }

    public final void setTopStart(LengthPercentage lengthPercentage) {
        this.topStart = lengthPercentage;
    }

    public final LengthPercentage getTopEnd() {
        return this.topEnd;
    }

    public final void setTopEnd(LengthPercentage lengthPercentage) {
        this.topEnd = lengthPercentage;
    }

    public final LengthPercentage getBottomStart() {
        return this.bottomStart;
    }

    public final void setBottomStart(LengthPercentage lengthPercentage) {
        this.bottomStart = lengthPercentage;
    }

    public final LengthPercentage getBottomEnd() {
        return this.bottomEnd;
    }

    public final void setBottomEnd(LengthPercentage lengthPercentage) {
        this.bottomEnd = lengthPercentage;
    }

    public final LengthPercentage getStartStart() {
        return this.startStart;
    }

    public final void setStartStart(LengthPercentage lengthPercentage) {
        this.startStart = lengthPercentage;
    }

    public final LengthPercentage getStartEnd() {
        return this.startEnd;
    }

    public final void setStartEnd(LengthPercentage lengthPercentage) {
        this.startEnd = lengthPercentage;
    }

    public final LengthPercentage getEndStart() {
        return this.endStart;
    }

    public final void setEndStart(LengthPercentage lengthPercentage) {
        this.endStart = lengthPercentage;
    }

    public final LengthPercentage getEndEnd() {
        return this.endEnd;
    }

    public final void setEndEnd(LengthPercentage lengthPercentage) {
        this.endEnd = lengthPercentage;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BorderRadiusStyle(List<? extends Pair> list) {
        this((LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, (LengthPercentage) null, 8191, (DefaultConstructorMarker) null);
        List<? extends Pair> list2 = list;
        Intrinsics.checkNotNullParameter(list2, "properties");
        for (Pair pair : list2) {
            set((BorderRadiusProp) pair.component1(), (LengthPercentage) pair.component2());
        }
    }

    public final void set(BorderRadiusProp borderRadiusProp, LengthPercentage lengthPercentage) {
        Intrinsics.checkNotNullParameter(borderRadiusProp, "property");
        switch (WhenMappings.$EnumSwitchMapping$0[borderRadiusProp.ordinal()]) {
            case 1:
                this.uniform = lengthPercentage;
                return;
            case 2:
                this.topLeft = lengthPercentage;
                return;
            case 3:
                this.topRight = lengthPercentage;
                return;
            case 4:
                this.bottomLeft = lengthPercentage;
                return;
            case 5:
                this.bottomRight = lengthPercentage;
                return;
            case 6:
                this.topStart = lengthPercentage;
                return;
            case 7:
                this.topEnd = lengthPercentage;
                return;
            case 8:
                this.bottomStart = lengthPercentage;
                return;
            case 9:
                this.bottomEnd = lengthPercentage;
                return;
            case 10:
                this.startStart = lengthPercentage;
                return;
            case 11:
                this.startEnd = lengthPercentage;
                return;
            case 12:
                this.endStart = lengthPercentage;
                return;
            case 13:
                this.endEnd = lengthPercentage;
                return;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final LengthPercentage get(BorderRadiusProp borderRadiusProp) {
        Intrinsics.checkNotNullParameter(borderRadiusProp, "property");
        switch (WhenMappings.$EnumSwitchMapping$0[borderRadiusProp.ordinal()]) {
            case 1:
                return this.uniform;
            case 2:
                return this.topLeft;
            case 3:
                return this.topRight;
            case 4:
                return this.bottomLeft;
            case 5:
                return this.bottomRight;
            case 6:
                return this.topStart;
            case 7:
                return this.topEnd;
            case 8:
                return this.bottomStart;
            case 9:
                return this.bottomEnd;
            case 10:
                return this.startStart;
            case 11:
                return this.startEnd;
            case 12:
                return this.endStart;
            case 13:
                return this.endEnd;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final boolean hasRoundedBorders() {
        return (this.uniform == null && this.topLeft == null && this.topRight == null && this.bottomLeft == null && this.bottomRight == null && this.topStart == null && this.topEnd == null && this.bottomStart == null && this.bottomEnd == null && this.startStart == null && this.startEnd == null && this.endStart == null && this.endEnd == null) ? false : true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002c, code lost:
        r9 = r9.resolve(r11, r12);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.facebook.react.uimanager.style.ComputedBorderRadius resolve(int r9, android.content.Context r10, float r11, float r12) {
        /*
            r8 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            com.facebook.react.uimanager.style.CornerRadii r0 = new com.facebook.react.uimanager.style.CornerRadii
            r1 = 0
            r0.<init>(r1, r1)
            if (r9 == 0) goto L_0x0106
            r1 = 1
            if (r9 != r1) goto L_0x00fe
            com.facebook.react.modules.i18nmanager.I18nUtil$Companion r9 = com.facebook.react.modules.i18nmanager.I18nUtil.Companion
            com.facebook.react.modules.i18nmanager.I18nUtil r9 = r9.getInstance()
            boolean r9 = r9.doLeftAndRightSwapInRTL(r10)
            if (r9 == 0) goto L_0x008d
            com.facebook.react.uimanager.LengthPercentage r9 = r8.endStart
            if (r9 != 0) goto L_0x002a
            com.facebook.react.uimanager.LengthPercentage r9 = r8.topEnd
            if (r9 != 0) goto L_0x002a
            com.facebook.react.uimanager.LengthPercentage r9 = r8.topRight
            if (r9 != 0) goto L_0x002a
            com.facebook.react.uimanager.LengthPercentage r9 = r8.uniform
        L_0x002a:
            if (r9 == 0) goto L_0x0035
            com.facebook.react.uimanager.style.CornerRadii r9 = r9.resolve(r11, r12)
            if (r9 != 0) goto L_0x0033
            goto L_0x0035
        L_0x0033:
            r2 = r9
            goto L_0x0036
        L_0x0035:
            r2 = r0
        L_0x0036:
            com.facebook.react.uimanager.LengthPercentage r9 = r8.startStart
            if (r9 != 0) goto L_0x0044
            com.facebook.react.uimanager.LengthPercentage r9 = r8.topStart
            if (r9 != 0) goto L_0x0044
            com.facebook.react.uimanager.LengthPercentage r9 = r8.topLeft
            if (r9 != 0) goto L_0x0044
            com.facebook.react.uimanager.LengthPercentage r9 = r8.uniform
        L_0x0044:
            if (r9 == 0) goto L_0x004f
            com.facebook.react.uimanager.style.CornerRadii r9 = r9.resolve(r11, r12)
            if (r9 != 0) goto L_0x004d
            goto L_0x004f
        L_0x004d:
            r3 = r9
            goto L_0x0050
        L_0x004f:
            r3 = r0
        L_0x0050:
            com.facebook.react.uimanager.LengthPercentage r9 = r8.endEnd
            if (r9 != 0) goto L_0x005e
            com.facebook.react.uimanager.LengthPercentage r9 = r8.bottomStart
            if (r9 != 0) goto L_0x005e
            com.facebook.react.uimanager.LengthPercentage r9 = r8.bottomRight
            if (r9 != 0) goto L_0x005e
            com.facebook.react.uimanager.LengthPercentage r9 = r8.uniform
        L_0x005e:
            if (r9 == 0) goto L_0x0069
            com.facebook.react.uimanager.style.CornerRadii r9 = r9.resolve(r11, r12)
            if (r9 != 0) goto L_0x0067
            goto L_0x0069
        L_0x0067:
            r4 = r9
            goto L_0x006a
        L_0x0069:
            r4 = r0
        L_0x006a:
            com.facebook.react.uimanager.LengthPercentage r9 = r8.startEnd
            if (r9 != 0) goto L_0x0078
            com.facebook.react.uimanager.LengthPercentage r9 = r8.bottomEnd
            if (r9 != 0) goto L_0x0078
            com.facebook.react.uimanager.LengthPercentage r9 = r8.bottomLeft
            if (r9 != 0) goto L_0x0078
            com.facebook.react.uimanager.LengthPercentage r9 = r8.uniform
        L_0x0078:
            if (r9 == 0) goto L_0x0083
            com.facebook.react.uimanager.style.CornerRadii r9 = r9.resolve(r11, r12)
            if (r9 != 0) goto L_0x0081
            goto L_0x0083
        L_0x0081:
            r5 = r9
            goto L_0x0084
        L_0x0083:
            r5 = r0
        L_0x0084:
            r1 = r8
            r6 = r11
            r7 = r12
            com.facebook.react.uimanager.style.ComputedBorderRadius r9 = r1.ensureNoOverlap(r2, r3, r4, r5, r6, r7)
            goto L_0x0175
        L_0x008d:
            com.facebook.react.uimanager.LengthPercentage r9 = r8.endStart
            if (r9 != 0) goto L_0x009b
            com.facebook.react.uimanager.LengthPercentage r9 = r8.topEnd
            if (r9 != 0) goto L_0x009b
            com.facebook.react.uimanager.LengthPercentage r9 = r8.topLeft
            if (r9 != 0) goto L_0x009b
            com.facebook.react.uimanager.LengthPercentage r9 = r8.uniform
        L_0x009b:
            if (r9 == 0) goto L_0x00a6
            com.facebook.react.uimanager.style.CornerRadii r9 = r9.resolve(r11, r12)
            if (r9 != 0) goto L_0x00a4
            goto L_0x00a6
        L_0x00a4:
            r2 = r9
            goto L_0x00a7
        L_0x00a6:
            r2 = r0
        L_0x00a7:
            com.facebook.react.uimanager.LengthPercentage r9 = r8.startStart
            if (r9 != 0) goto L_0x00b5
            com.facebook.react.uimanager.LengthPercentage r9 = r8.topStart
            if (r9 != 0) goto L_0x00b5
            com.facebook.react.uimanager.LengthPercentage r9 = r8.topRight
            if (r9 != 0) goto L_0x00b5
            com.facebook.react.uimanager.LengthPercentage r9 = r8.uniform
        L_0x00b5:
            if (r9 == 0) goto L_0x00c0
            com.facebook.react.uimanager.style.CornerRadii r9 = r9.resolve(r11, r12)
            if (r9 != 0) goto L_0x00be
            goto L_0x00c0
        L_0x00be:
            r3 = r9
            goto L_0x00c1
        L_0x00c0:
            r3 = r0
        L_0x00c1:
            com.facebook.react.uimanager.LengthPercentage r9 = r8.endEnd
            if (r9 != 0) goto L_0x00cf
            com.facebook.react.uimanager.LengthPercentage r9 = r8.bottomStart
            if (r9 != 0) goto L_0x00cf
            com.facebook.react.uimanager.LengthPercentage r9 = r8.bottomLeft
            if (r9 != 0) goto L_0x00cf
            com.facebook.react.uimanager.LengthPercentage r9 = r8.uniform
        L_0x00cf:
            if (r9 == 0) goto L_0x00da
            com.facebook.react.uimanager.style.CornerRadii r9 = r9.resolve(r11, r12)
            if (r9 != 0) goto L_0x00d8
            goto L_0x00da
        L_0x00d8:
            r4 = r9
            goto L_0x00db
        L_0x00da:
            r4 = r0
        L_0x00db:
            com.facebook.react.uimanager.LengthPercentage r9 = r8.startEnd
            if (r9 != 0) goto L_0x00e9
            com.facebook.react.uimanager.LengthPercentage r9 = r8.bottomEnd
            if (r9 != 0) goto L_0x00e9
            com.facebook.react.uimanager.LengthPercentage r9 = r8.bottomRight
            if (r9 != 0) goto L_0x00e9
            com.facebook.react.uimanager.LengthPercentage r9 = r8.uniform
        L_0x00e9:
            if (r9 == 0) goto L_0x00f4
            com.facebook.react.uimanager.style.CornerRadii r9 = r9.resolve(r11, r12)
            if (r9 != 0) goto L_0x00f2
            goto L_0x00f4
        L_0x00f2:
            r5 = r9
            goto L_0x00f5
        L_0x00f4:
            r5 = r0
        L_0x00f5:
            r1 = r8
            r6 = r11
            r7 = r12
            com.facebook.react.uimanager.style.ComputedBorderRadius r9 = r1.ensureNoOverlap(r2, r3, r4, r5, r6, r7)
            goto L_0x0175
        L_0x00fe:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r10 = "Expected?.resolved layout direction"
            r9.<init>(r10)
            throw r9
        L_0x0106:
            com.facebook.react.uimanager.LengthPercentage r9 = r8.startStart
            if (r9 != 0) goto L_0x0114
            com.facebook.react.uimanager.LengthPercentage r9 = r8.topStart
            if (r9 != 0) goto L_0x0114
            com.facebook.react.uimanager.LengthPercentage r9 = r8.topLeft
            if (r9 != 0) goto L_0x0114
            com.facebook.react.uimanager.LengthPercentage r9 = r8.uniform
        L_0x0114:
            if (r9 == 0) goto L_0x011f
            com.facebook.react.uimanager.style.CornerRadii r9 = r9.resolve(r11, r12)
            if (r9 != 0) goto L_0x011d
            goto L_0x011f
        L_0x011d:
            r2 = r9
            goto L_0x0120
        L_0x011f:
            r2 = r0
        L_0x0120:
            com.facebook.react.uimanager.LengthPercentage r9 = r8.endStart
            if (r9 != 0) goto L_0x012e
            com.facebook.react.uimanager.LengthPercentage r9 = r8.topEnd
            if (r9 != 0) goto L_0x012e
            com.facebook.react.uimanager.LengthPercentage r9 = r8.topRight
            if (r9 != 0) goto L_0x012e
            com.facebook.react.uimanager.LengthPercentage r9 = r8.uniform
        L_0x012e:
            if (r9 == 0) goto L_0x0139
            com.facebook.react.uimanager.style.CornerRadii r9 = r9.resolve(r11, r12)
            if (r9 != 0) goto L_0x0137
            goto L_0x0139
        L_0x0137:
            r3 = r9
            goto L_0x013a
        L_0x0139:
            r3 = r0
        L_0x013a:
            com.facebook.react.uimanager.LengthPercentage r9 = r8.startEnd
            if (r9 != 0) goto L_0x0148
            com.facebook.react.uimanager.LengthPercentage r9 = r8.bottomStart
            if (r9 != 0) goto L_0x0148
            com.facebook.react.uimanager.LengthPercentage r9 = r8.bottomLeft
            if (r9 != 0) goto L_0x0148
            com.facebook.react.uimanager.LengthPercentage r9 = r8.uniform
        L_0x0148:
            if (r9 == 0) goto L_0x0153
            com.facebook.react.uimanager.style.CornerRadii r9 = r9.resolve(r11, r12)
            if (r9 != 0) goto L_0x0151
            goto L_0x0153
        L_0x0151:
            r4 = r9
            goto L_0x0154
        L_0x0153:
            r4 = r0
        L_0x0154:
            com.facebook.react.uimanager.LengthPercentage r9 = r8.endEnd
            if (r9 != 0) goto L_0x0162
            com.facebook.react.uimanager.LengthPercentage r9 = r8.bottomEnd
            if (r9 != 0) goto L_0x0162
            com.facebook.react.uimanager.LengthPercentage r9 = r8.bottomRight
            if (r9 != 0) goto L_0x0162
            com.facebook.react.uimanager.LengthPercentage r9 = r8.uniform
        L_0x0162:
            if (r9 == 0) goto L_0x016d
            com.facebook.react.uimanager.style.CornerRadii r9 = r9.resolve(r11, r12)
            if (r9 != 0) goto L_0x016b
            goto L_0x016d
        L_0x016b:
            r5 = r9
            goto L_0x016e
        L_0x016d:
            r5 = r0
        L_0x016e:
            r1 = r8
            r6 = r11
            r7 = r12
            com.facebook.react.uimanager.style.ComputedBorderRadius r9 = r1.ensureNoOverlap(r2, r3, r4, r5, r6, r7)
        L_0x0175:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.style.BorderRadiusStyle.resolve(int, android.content.Context, float, float):com.facebook.react.uimanager.style.ComputedBorderRadius");
    }

    private final ComputedBorderRadius ensureNoOverlap(CornerRadii cornerRadii, CornerRadii cornerRadii2, CornerRadii cornerRadii3, CornerRadii cornerRadii4, float f, float f2) {
        float vertical = cornerRadii.getVertical() + cornerRadii3.getVertical();
        float horizontal = cornerRadii.getHorizontal() + cornerRadii2.getHorizontal();
        float vertical2 = cornerRadii2.getVertical() + cornerRadii4.getVertical();
        float horizontal2 = cornerRadii3.getHorizontal() + cornerRadii4.getHorizontal();
        float f3 = 0.0f;
        float min = vertical > 0.0f ? Math.min(f2 / vertical, 1.0f) : 0.0f;
        float min2 = horizontal > 0.0f ? Math.min(f / horizontal, 1.0f) : 0.0f;
        float min3 = vertical2 > 0.0f ? Math.min(f2 / vertical2, 1.0f) : 0.0f;
        if (horizontal2 > 0.0f) {
            f3 = Math.min(f / horizontal2, 1.0f);
        }
        return new ComputedBorderRadius(new CornerRadii(cornerRadii.getHorizontal() * Math.min(min2, min), cornerRadii.getVertical() * Math.min(min2, min)), new CornerRadii(cornerRadii2.getHorizontal() * Math.min(min3, min2), cornerRadii2.getVertical() * Math.min(min3, min2)), new CornerRadii(cornerRadii3.getHorizontal() * Math.min(f3, min), cornerRadii3.getVertical() * Math.min(f3, min)), new CornerRadii(cornerRadii4.getHorizontal() * Math.min(f3, min3), cornerRadii4.getVertical() * Math.min(f3, min3)));
    }
}
