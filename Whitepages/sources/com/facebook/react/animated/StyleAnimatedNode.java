package com.facebook.react.animated;

import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import java.util.Map;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

public final class StyleAnimatedNode extends AnimatedNode {
    private final NativeAnimatedNodesManager nativeAnimatedNodesManager;
    private final Map<String, Integer> propMapping;

    public StyleAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager2) {
        Intrinsics.checkNotNullParameter(readableMap, "config");
        Intrinsics.checkNotNullParameter(nativeAnimatedNodesManager2, "nativeAnimatedNodesManager");
        this.nativeAnimatedNodesManager = nativeAnimatedNodesManager2;
        ReadableMap map = readableMap.getMap("style");
        ReadableMapKeySetIterator keySetIterator = map != null ? map.keySetIterator() : null;
        Map createMapBuilder = MapsKt.createMapBuilder();
        while (keySetIterator != null && keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            createMapBuilder.put(nextKey, Integer.valueOf(map.getInt(nextKey)));
        }
        this.propMapping = MapsKt.build(createMapBuilder);
    }

    public final void collectViewUpdates(JavaOnlyMap javaOnlyMap) {
        Intrinsics.checkNotNullParameter(javaOnlyMap, "propsMap");
        for (Map.Entry next : this.propMapping.entrySet()) {
            String str = (String) next.getKey();
            AnimatedNode nodeById = this.nativeAnimatedNodesManager.getNodeById(((Number) next.getValue()).intValue());
            if (nodeById == null) {
                throw new IllegalArgumentException("Mapped style node does not exist");
            } else if (nodeById instanceof TransformAnimatedNode) {
                ((TransformAnimatedNode) nodeById).collectViewUpdates(javaOnlyMap);
            } else if (nodeById instanceof ValueAnimatedNode) {
                ValueAnimatedNode valueAnimatedNode = (ValueAnimatedNode) nodeById;
                Object animatedObject = valueAnimatedNode.getAnimatedObject();
                if (animatedObject instanceof Integer) {
                    javaOnlyMap.putInt(str, ((Number) animatedObject).intValue());
                } else if (animatedObject instanceof String) {
                    javaOnlyMap.putString(str, (String) animatedObject);
                } else {
                    javaOnlyMap.putDouble(str, valueAnimatedNode.getValue());
                }
            } else if (nodeById instanceof ColorAnimatedNode) {
                javaOnlyMap.putInt(str, ((ColorAnimatedNode) nodeById).getColor());
            } else if (nodeById instanceof ObjectAnimatedNode) {
                ((ObjectAnimatedNode) nodeById).collectViewUpdates(str, javaOnlyMap);
            } else {
                Class<?> cls = nodeById.getClass();
                throw new IllegalArgumentException("Unsupported type of node used in property node " + cls);
            }
        }
    }

    public String prettyPrint() {
        int i = this.tag;
        Map<String, Integer> map = this.propMapping;
        return "StyleAnimatedNode[" + i + "] mPropMapping: " + map;
    }
}
