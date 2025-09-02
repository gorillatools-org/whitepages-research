package com.facebook.react.animated;

import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableMap;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class DiffClampAnimatedNode extends ValueAnimatedNode {
    private final int inputNodeTag;
    private double lastValue;
    private final double maxValue;
    private final double minValue;
    private final NativeAnimatedNodesManager nativeAnimatedNodesManager;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DiffClampAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager2) {
        super((ReadableMap) null, 1, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(readableMap, "config");
        Intrinsics.checkNotNullParameter(nativeAnimatedNodesManager2, "nativeAnimatedNodesManager");
        this.nativeAnimatedNodesManager = nativeAnimatedNodesManager2;
        this.inputNodeTag = readableMap.getInt("input");
        this.minValue = readableMap.getDouble("min");
        this.maxValue = readableMap.getDouble("max");
        this.nodeValue = this.lastValue;
    }

    public void update() {
        double inputNodeValue = getInputNodeValue();
        double d = inputNodeValue - this.lastValue;
        this.lastValue = inputNodeValue;
        this.nodeValue = Math.min(Math.max(this.nodeValue + d, this.minValue), this.maxValue);
    }

    private final double getInputNodeValue() {
        AnimatedNode nodeById = this.nativeAnimatedNodesManager.getNodeById(this.inputNodeTag);
        if (nodeById != null && (nodeById instanceof ValueAnimatedNode)) {
            return ((ValueAnimatedNode) nodeById).getValue();
        }
        throw new JSApplicationCausedNativeException("Illegal node ID set as an input for Animated.DiffClamp node");
    }

    public String prettyPrint() {
        int i = this.tag;
        int i2 = this.inputNodeTag;
        double d = this.minValue;
        double d2 = this.maxValue;
        double d3 = this.lastValue;
        String prettyPrint = super.prettyPrint();
        return "DiffClampAnimatedNode[" + i + "]: InputNodeTag: " + i2 + " min: " + d + " max: " + d2 + " lastValue: " + d3 + " super: " + prettyPrint;
    }
}
