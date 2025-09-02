package com.facebook.react.animated;

import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SubtractionAnimatedNode extends ValueAnimatedNode {
    private final int[] inputNodes;
    private final NativeAnimatedNodesManager nativeAnimatedNodesManager;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SubtractionAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager2) {
        super((ReadableMap) null, 1, (DefaultConstructorMarker) null);
        int[] iArr;
        Intrinsics.checkNotNullParameter(readableMap, "config");
        Intrinsics.checkNotNullParameter(nativeAnimatedNodesManager2, "nativeAnimatedNodesManager");
        this.nativeAnimatedNodesManager = nativeAnimatedNodesManager2;
        ReadableArray array = readableMap.getArray("input");
        if (array == null) {
            iArr = new int[0];
        } else {
            int size = array.size();
            int[] iArr2 = new int[size];
            for (int i = 0; i < size; i++) {
                iArr2[i] = array.getInt(i);
            }
            iArr = iArr2;
        }
        this.inputNodes = iArr;
    }

    public void update() {
        int length = this.inputNodes.length;
        for (int i = 0; i < length; i++) {
            AnimatedNode nodeById = this.nativeAnimatedNodesManager.getNodeById(this.inputNodes[i]);
            if (nodeById == null || !(nodeById instanceof ValueAnimatedNode)) {
                throw new JSApplicationCausedNativeException("Illegal node ID set as an input for Animated.subtract node");
            }
            double value = ((ValueAnimatedNode) nodeById).getValue();
            if (i == 0) {
                this.nodeValue = value;
            } else {
                this.nodeValue -= value;
            }
        }
    }

    public String prettyPrint() {
        int i = this.tag;
        int[] iArr = this.inputNodes;
        String prettyPrint = super.prettyPrint();
        return "SubtractionAnimatedNode[" + i + "]: input nodes: " + iArr + " - super: " + prettyPrint;
    }
}
