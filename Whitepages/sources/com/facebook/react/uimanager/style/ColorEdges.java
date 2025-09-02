package com.facebook.react.uimanager.style;

import kotlin.jvm.internal.DefaultConstructorMarker;

public final class ColorEdges {
    private final int bottom;
    private final int left;
    private final int right;
    private final int top;

    public ColorEdges() {
        this(0, 0, 0, 0, 15, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ ColorEdges copy$default(ColorEdges colorEdges, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = colorEdges.left;
        }
        if ((i5 & 2) != 0) {
            i2 = colorEdges.top;
        }
        if ((i5 & 4) != 0) {
            i3 = colorEdges.right;
        }
        if ((i5 & 8) != 0) {
            i4 = colorEdges.bottom;
        }
        return colorEdges.copy(i, i2, i3, i4);
    }

    public final int component1() {
        return this.left;
    }

    public final int component2() {
        return this.top;
    }

    public final int component3() {
        return this.right;
    }

    public final int component4() {
        return this.bottom;
    }

    public final ColorEdges copy(int i, int i2, int i3, int i4) {
        return new ColorEdges(i, i2, i3, i4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ColorEdges)) {
            return false;
        }
        ColorEdges colorEdges = (ColorEdges) obj;
        return this.left == colorEdges.left && this.top == colorEdges.top && this.right == colorEdges.right && this.bottom == colorEdges.bottom;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.left) * 31) + Integer.hashCode(this.top)) * 31) + Integer.hashCode(this.right)) * 31) + Integer.hashCode(this.bottom);
    }

    public String toString() {
        int i = this.left;
        int i2 = this.top;
        int i3 = this.right;
        int i4 = this.bottom;
        return "ColorEdges(left=" + i + ", top=" + i2 + ", right=" + i3 + ", bottom=" + i4 + ")";
    }

    public ColorEdges(int i, int i2, int i3, int i4) {
        this.left = i;
        this.top = i2;
        this.right = i3;
        this.bottom = i4;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ColorEdges(int i, int i2, int i3, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this((i5 & 1) != 0 ? -16777216 : i, (i5 & 2) != 0 ? -16777216 : i2, (i5 & 4) != 0 ? -16777216 : i3, (i5 & 8) != 0 ? -16777216 : i4);
    }

    public final int getLeft() {
        return this.left;
    }

    public final int getTop() {
        return this.top;
    }

    public final int getRight() {
        return this.right;
    }

    public final int getBottom() {
        return this.bottom;
    }
}
