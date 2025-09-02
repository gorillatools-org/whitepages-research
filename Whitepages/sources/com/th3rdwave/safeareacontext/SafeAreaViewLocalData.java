package com.th3rdwave.safeareacontext;

import kotlin.jvm.internal.Intrinsics;

public final class SafeAreaViewLocalData {
    private final SafeAreaViewEdges edges;
    private final EdgeInsets insets;
    private final SafeAreaViewMode mode;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SafeAreaViewLocalData)) {
            return false;
        }
        SafeAreaViewLocalData safeAreaViewLocalData = (SafeAreaViewLocalData) obj;
        return Intrinsics.areEqual((Object) this.insets, (Object) safeAreaViewLocalData.insets) && this.mode == safeAreaViewLocalData.mode && Intrinsics.areEqual((Object) this.edges, (Object) safeAreaViewLocalData.edges);
    }

    public int hashCode() {
        return (((this.insets.hashCode() * 31) + this.mode.hashCode()) * 31) + this.edges.hashCode();
    }

    public String toString() {
        return "SafeAreaViewLocalData(insets=" + this.insets + ", mode=" + this.mode + ", edges=" + this.edges + ')';
    }

    public SafeAreaViewLocalData(EdgeInsets edgeInsets, SafeAreaViewMode safeAreaViewMode, SafeAreaViewEdges safeAreaViewEdges) {
        Intrinsics.checkNotNullParameter(edgeInsets, "insets");
        Intrinsics.checkNotNullParameter(safeAreaViewMode, "mode");
        Intrinsics.checkNotNullParameter(safeAreaViewEdges, "edges");
        this.insets = edgeInsets;
        this.mode = safeAreaViewMode;
        this.edges = safeAreaViewEdges;
    }

    public final EdgeInsets getInsets() {
        return this.insets;
    }

    public final SafeAreaViewMode getMode() {
        return this.mode;
    }

    public final SafeAreaViewEdges getEdges() {
        return this.edges;
    }
}
