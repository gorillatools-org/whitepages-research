package com.th3rdwave.safeareacontext;

import com.facebook.react.uimanager.ViewProps;
import kotlin.jvm.internal.Intrinsics;

public final class SafeAreaViewEdges {
    private final SafeAreaViewEdgeModes bottom;
    private final SafeAreaViewEdgeModes left;
    private final SafeAreaViewEdgeModes right;
    private final SafeAreaViewEdgeModes top;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SafeAreaViewEdges)) {
            return false;
        }
        SafeAreaViewEdges safeAreaViewEdges = (SafeAreaViewEdges) obj;
        return this.top == safeAreaViewEdges.top && this.right == safeAreaViewEdges.right && this.bottom == safeAreaViewEdges.bottom && this.left == safeAreaViewEdges.left;
    }

    public int hashCode() {
        return (((((this.top.hashCode() * 31) + this.right.hashCode()) * 31) + this.bottom.hashCode()) * 31) + this.left.hashCode();
    }

    public String toString() {
        return "SafeAreaViewEdges(top=" + this.top + ", right=" + this.right + ", bottom=" + this.bottom + ", left=" + this.left + ')';
    }

    public SafeAreaViewEdges(SafeAreaViewEdgeModes safeAreaViewEdgeModes, SafeAreaViewEdgeModes safeAreaViewEdgeModes2, SafeAreaViewEdgeModes safeAreaViewEdgeModes3, SafeAreaViewEdgeModes safeAreaViewEdgeModes4) {
        Intrinsics.checkNotNullParameter(safeAreaViewEdgeModes, ViewProps.TOP);
        Intrinsics.checkNotNullParameter(safeAreaViewEdgeModes2, ViewProps.RIGHT);
        Intrinsics.checkNotNullParameter(safeAreaViewEdgeModes3, ViewProps.BOTTOM);
        Intrinsics.checkNotNullParameter(safeAreaViewEdgeModes4, ViewProps.LEFT);
        this.top = safeAreaViewEdgeModes;
        this.right = safeAreaViewEdgeModes2;
        this.bottom = safeAreaViewEdgeModes3;
        this.left = safeAreaViewEdgeModes4;
    }

    public final SafeAreaViewEdgeModes getTop() {
        return this.top;
    }

    public final SafeAreaViewEdgeModes getRight() {
        return this.right;
    }

    public final SafeAreaViewEdgeModes getBottom() {
        return this.bottom;
    }

    public final SafeAreaViewEdgeModes getLeft() {
        return this.left;
    }
}
