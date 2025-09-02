package com.facebook.react.animated;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableMap;
import kotlin.jvm.internal.Intrinsics;

public final class TrackingAnimatedNode extends AnimatedNode {
    private final JavaOnlyMap animationConfig;
    private final int animationId;
    private final NativeAnimatedNodesManager nativeAnimatedNodesManager;
    private final int toValueNode;
    private final int valueNode;

    public TrackingAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager2) {
        Intrinsics.checkNotNullParameter(readableMap, "config");
        Intrinsics.checkNotNullParameter(nativeAnimatedNodesManager2, "nativeAnimatedNodesManager");
        this.nativeAnimatedNodesManager = nativeAnimatedNodesManager2;
        this.animationConfig = JavaOnlyMap.Companion.deepClone(readableMap.getMap("animationConfig"));
        this.animationId = readableMap.getInt("animationId");
        this.toValueNode = readableMap.getInt("toValue");
        this.valueNode = readableMap.getInt("value");
    }

    public void update() {
        AnimatedNode nodeById = this.nativeAnimatedNodesManager.getNodeById(this.toValueNode);
        ValueAnimatedNode valueAnimatedNode = nodeById instanceof ValueAnimatedNode ? (ValueAnimatedNode) nodeById : null;
        if (valueAnimatedNode != null) {
            this.animationConfig.putDouble("toValue", valueAnimatedNode.getValue());
        } else {
            this.animationConfig.putNull("toValue");
        }
        this.nativeAnimatedNodesManager.startAnimatingNode(this.animationId, this.valueNode, this.animationConfig, (Callback) null);
    }

    public String prettyPrint() {
        int i = this.tag;
        int i2 = this.animationId;
        int i3 = this.toValueNode;
        int i4 = this.valueNode;
        JavaOnlyMap javaOnlyMap = this.animationConfig;
        return "TrackingAnimatedNode[" + i + "]: animationID: " + i2 + " toValueNode: " + i3 + " valueNode: " + i4 + " animationConfig: " + javaOnlyMap;
    }
}
