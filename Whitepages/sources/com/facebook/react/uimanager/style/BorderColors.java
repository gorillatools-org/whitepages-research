package com.facebook.react.uimanager.style;

import android.content.Context;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class BorderColors {
    private final Integer[] edgeColors;

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ BorderColors m467boximpl(Integer[] numArr) {
        return new BorderColors(numArr);
    }

    /* renamed from: constructor-impl  reason: not valid java name */
    public static Integer[] m468constructorimpl(Integer[] numArr) {
        Intrinsics.checkNotNullParameter(numArr, "edgeColors");
        return numArr;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m470equalsimpl(Integer[] numArr, Object obj) {
        return (obj instanceof BorderColors) && Intrinsics.areEqual((Object) numArr, (Object) ((BorderColors) obj).m475unboximpl());
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m471equalsimpl0(Integer[] numArr, Integer[] numArr2) {
        return Intrinsics.areEqual((Object) numArr, (Object) numArr2);
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m472hashCodeimpl(Integer[] numArr) {
        return Arrays.hashCode(numArr);
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m474toStringimpl(Integer[] numArr) {
        String arrays = Arrays.toString(numArr);
        return "BorderColors(edgeColors=" + arrays + ")";
    }

    public boolean equals(Object obj) {
        return m470equalsimpl(this.edgeColors, obj);
    }

    public int hashCode() {
        return m472hashCodeimpl(this.edgeColors);
    }

    public String toString() {
        return m474toStringimpl(this.edgeColors);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ Integer[] m475unboximpl() {
        return this.edgeColors;
    }

    private /* synthetic */ BorderColors(Integer[] numArr) {
        this.edgeColors = numArr;
    }

    /* renamed from: constructor-impl$default  reason: not valid java name */
    public static /* synthetic */ Integer[] m469constructorimpl$default(Integer[] numArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            numArr = new Integer[LogicalEdge.values().length];
        }
        return m468constructorimpl(numArr);
    }

    public final Integer[] getEdgeColors() {
        return this.edgeColors;
    }

    /* renamed from: resolve-impl  reason: not valid java name */
    public static final ColorEdges m473resolveimpl(Integer[] numArr, int i, Context context) {
        ColorEdges colorEdges;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        Intrinsics.checkNotNullParameter(context, "context");
        int i11 = -16777216;
        if (i == 0) {
            Integer num = numArr[LogicalEdge.START.ordinal()];
            if (num == null && (num = numArr[LogicalEdge.LEFT.ordinal()]) == null && (num = numArr[LogicalEdge.HORIZONTAL.ordinal()]) == null && (num = numArr[LogicalEdge.ALL.ordinal()]) == null) {
                i2 = -16777216;
            } else {
                i2 = num.intValue();
            }
            Integer num2 = numArr[LogicalEdge.BLOCK_START.ordinal()];
            if (num2 == null && (num2 = numArr[LogicalEdge.TOP.ordinal()]) == null && (num2 = numArr[LogicalEdge.BLOCK.ordinal()]) == null && (num2 = numArr[LogicalEdge.VERTICAL.ordinal()]) == null && (num2 = numArr[LogicalEdge.ALL.ordinal()]) == null) {
                i3 = -16777216;
            } else {
                i3 = num2.intValue();
            }
            Integer num3 = numArr[LogicalEdge.END.ordinal()];
            if (num3 == null && (num3 = numArr[LogicalEdge.RIGHT.ordinal()]) == null && (num3 = numArr[LogicalEdge.HORIZONTAL.ordinal()]) == null && (num3 = numArr[LogicalEdge.ALL.ordinal()]) == null) {
                i4 = -16777216;
            } else {
                i4 = num3.intValue();
            }
            Integer num4 = numArr[LogicalEdge.BLOCK_END.ordinal()];
            if (num4 == null && (num4 = numArr[LogicalEdge.BOTTOM.ordinal()]) == null && (num4 = numArr[LogicalEdge.BLOCK.ordinal()]) == null && (num4 = numArr[LogicalEdge.VERTICAL.ordinal()]) == null) {
                Integer num5 = numArr[LogicalEdge.ALL.ordinal()];
                if (num5 != null) {
                    i11 = num5.intValue();
                }
            } else {
                i11 = num4.intValue();
            }
            colorEdges = new ColorEdges(i2, i3, i4, i11);
        } else if (i != 1) {
            throw new IllegalArgumentException("Expected resolved layout direction");
        } else if (I18nUtil.Companion.getInstance().doLeftAndRightSwapInRTL(context)) {
            Integer num6 = numArr[LogicalEdge.END.ordinal()];
            if (num6 == null && (num6 = numArr[LogicalEdge.RIGHT.ordinal()]) == null && (num6 = numArr[LogicalEdge.HORIZONTAL.ordinal()]) == null && (num6 = numArr[LogicalEdge.ALL.ordinal()]) == null) {
                i8 = -16777216;
            } else {
                i8 = num6.intValue();
            }
            Integer num7 = numArr[LogicalEdge.BLOCK_START.ordinal()];
            if (num7 == null && (num7 = numArr[LogicalEdge.TOP.ordinal()]) == null && (num7 = numArr[LogicalEdge.BLOCK.ordinal()]) == null && (num7 = numArr[LogicalEdge.VERTICAL.ordinal()]) == null && (num7 = numArr[LogicalEdge.ALL.ordinal()]) == null) {
                i9 = -16777216;
            } else {
                i9 = num7.intValue();
            }
            Integer num8 = numArr[LogicalEdge.START.ordinal()];
            if (num8 == null && (num8 = numArr[LogicalEdge.LEFT.ordinal()]) == null && (num8 = numArr[LogicalEdge.HORIZONTAL.ordinal()]) == null && (num8 = numArr[LogicalEdge.ALL.ordinal()]) == null) {
                i10 = -16777216;
            } else {
                i10 = num8.intValue();
            }
            Integer num9 = numArr[LogicalEdge.BLOCK_END.ordinal()];
            if (num9 == null && (num9 = numArr[LogicalEdge.BOTTOM.ordinal()]) == null && (num9 = numArr[LogicalEdge.BLOCK.ordinal()]) == null && (num9 = numArr[LogicalEdge.VERTICAL.ordinal()]) == null) {
                Integer num10 = numArr[LogicalEdge.ALL.ordinal()];
                if (num10 != null) {
                    i11 = num10.intValue();
                }
            } else {
                i11 = num9.intValue();
            }
            colorEdges = new ColorEdges(i8, i9, i10, i11);
        } else {
            Integer num11 = numArr[LogicalEdge.END.ordinal()];
            if (num11 == null && (num11 = numArr[LogicalEdge.LEFT.ordinal()]) == null && (num11 = numArr[LogicalEdge.HORIZONTAL.ordinal()]) == null && (num11 = numArr[LogicalEdge.ALL.ordinal()]) == null) {
                i5 = -16777216;
            } else {
                i5 = num11.intValue();
            }
            Integer num12 = numArr[LogicalEdge.BLOCK_START.ordinal()];
            if (num12 == null && (num12 = numArr[LogicalEdge.TOP.ordinal()]) == null && (num12 = numArr[LogicalEdge.BLOCK.ordinal()]) == null && (num12 = numArr[LogicalEdge.VERTICAL.ordinal()]) == null && (num12 = numArr[LogicalEdge.ALL.ordinal()]) == null) {
                i6 = -16777216;
            } else {
                i6 = num12.intValue();
            }
            Integer num13 = numArr[LogicalEdge.START.ordinal()];
            if (num13 == null && (num13 = numArr[LogicalEdge.RIGHT.ordinal()]) == null && (num13 = numArr[LogicalEdge.HORIZONTAL.ordinal()]) == null && (num13 = numArr[LogicalEdge.ALL.ordinal()]) == null) {
                i7 = -16777216;
            } else {
                i7 = num13.intValue();
            }
            Integer num14 = numArr[LogicalEdge.BLOCK_END.ordinal()];
            if (num14 == null && (num14 = numArr[LogicalEdge.BOTTOM.ordinal()]) == null && (num14 = numArr[LogicalEdge.BLOCK.ordinal()]) == null && (num14 = numArr[LogicalEdge.VERTICAL.ordinal()]) == null) {
                Integer num15 = numArr[LogicalEdge.ALL.ordinal()];
                if (num15 != null) {
                    i11 = num15.intValue();
                }
            } else {
                i11 = num14.intValue();
            }
            colorEdges = new ColorEdges(i5, i6, i7, i11);
        }
        return colorEdges;
    }
}
