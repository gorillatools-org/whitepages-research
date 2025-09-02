package com.facebook.react.animated;

import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class AdditionAnimatedNode extends ValueAnimatedNode {
    private final int[] inputNodes;
    private final NativeAnimatedNodesManager nativeAnimatedNodesManager;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AdditionAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager2) {
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
        this.nodeValue = 0.0d;
        int[] iArr = this.inputNodes;
        int length = iArr.length;
        int i = 0;
        double d = 0.0d;
        while (i < length) {
            AnimatedNode nodeById = this.nativeAnimatedNodesManager.getNodeById(iArr[i]);
            if (nodeById instanceof ValueAnimatedNode) {
                d += ((ValueAnimatedNode) nodeById).getValue();
                i++;
            } else {
                throw new JSApplicationCausedNativeException("Illegal node ID set as an input for Animated.Add node");
            }
        }
        this.nodeValue = 0.0d + d;
    }

    public String prettyPrint() {
        int i = this.tag;
        String joinToString$default = ArraysKt.joinToString$default(this.inputNodes, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null);
        String prettyPrint = super.prettyPrint();
        return "AdditionAnimatedNode[" + i + "]: input nodes: " + joinToString$default + " - super: " + prettyPrint;
    }
}
