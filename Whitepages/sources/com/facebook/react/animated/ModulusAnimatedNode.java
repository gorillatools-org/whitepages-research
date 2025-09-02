package com.facebook.react.animated;

import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableMap;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ModulusAnimatedNode extends ValueAnimatedNode {
    private final int inputNode;
    private final double modulus;
    private final NativeAnimatedNodesManager nativeAnimatedNodesManager;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ModulusAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager2) {
        super((ReadableMap) null, 1, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(readableMap, "config");
        Intrinsics.checkNotNullParameter(nativeAnimatedNodesManager2, "nativeAnimatedNodesManager");
        this.nativeAnimatedNodesManager = nativeAnimatedNodesManager2;
        this.inputNode = readableMap.getInt("input");
        this.modulus = readableMap.getDouble("modulus");
    }

    public void update() {
        AnimatedNode nodeById = this.nativeAnimatedNodesManager.getNodeById(this.inputNode);
        if (nodeById instanceof ValueAnimatedNode) {
            double value = ((ValueAnimatedNode) nodeById).getValue();
            double d = this.modulus;
            this.nodeValue = ((value % d) + d) % d;
            return;
        }
        throw new JSApplicationCausedNativeException("Illegal node ID set as an input for Animated.modulus node");
    }

    public String prettyPrint() {
        int i = this.tag;
        int i2 = this.inputNode;
        double d = this.modulus;
        String prettyPrint = super.prettyPrint();
        return "NativeAnimatedNodesManager[" + i + "] inputNode: " + i2 + " modulus: " + d + " super: " + prettyPrint;
    }
}
