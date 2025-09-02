package com.facebook.react.animated;

import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.uimanager.ViewProps;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class TransformAnimatedNode extends AnimatedNode {
    private final NativeAnimatedNodesManager nativeAnimatedNodesManager;
    private final List<TransformConfig> transformConfigs;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: com.facebook.react.animated.TransformAnimatedNode$StaticTransformConfig} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: com.facebook.react.animated.TransformAnimatedNode$AnimatedTransformConfig} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: com.facebook.react.animated.TransformAnimatedNode$StaticTransformConfig} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: com.facebook.react.animated.TransformAnimatedNode$StaticTransformConfig} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TransformAnimatedNode(com.facebook.react.bridge.ReadableMap r7, com.facebook.react.animated.NativeAnimatedNodesManager r8) {
        /*
            r6 = this;
            java.lang.String r0 = "config"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "nativeAnimatedNodesManager"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            r6.<init>()
            r6.nativeAnimatedNodesManager = r8
            java.lang.String r8 = "transforms"
            com.facebook.react.bridge.ReadableArray r7 = r7.getArray(r8)
            if (r7 != 0) goto L_0x001c
            java.util.List r7 = kotlin.collections.CollectionsKt.emptyList()
            goto L_0x0074
        L_0x001c:
            int r8 = r7.size()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>(r8)
            r1 = 0
        L_0x0026:
            if (r1 >= r8) goto L_0x0073
            com.facebook.react.bridge.ReadableMap r2 = r7.getMap(r1)
            if (r2 == 0) goto L_0x006b
            java.lang.String r3 = "property"
            java.lang.String r3 = r2.getString(r3)
            java.lang.String r4 = "type"
            java.lang.String r4 = r2.getString(r4)
            java.lang.String r5 = "animated"
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 == 0) goto L_0x0054
            com.facebook.react.animated.TransformAnimatedNode$AnimatedTransformConfig r4 = new com.facebook.react.animated.TransformAnimatedNode$AnimatedTransformConfig
            r4.<init>()
            r4.setProperty(r3)
            java.lang.String r3 = "nodeTag"
            int r2 = r2.getInt(r3)
            r4.setNodeTag(r2)
            goto L_0x0065
        L_0x0054:
            com.facebook.react.animated.TransformAnimatedNode$StaticTransformConfig r4 = new com.facebook.react.animated.TransformAnimatedNode$StaticTransformConfig
            r4.<init>()
            r4.setProperty(r3)
            java.lang.String r3 = "value"
            double r2 = r2.getDouble(r3)
            r4.setValue(r2)
        L_0x0065:
            r0.add(r4)
            int r1 = r1 + 1
            goto L_0x0026
        L_0x006b:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "Required value was null."
            r7.<init>(r8)
            throw r7
        L_0x0073:
            r7 = r0
        L_0x0074:
            r6.transformConfigs = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.animated.TransformAnimatedNode.<init>(com.facebook.react.bridge.ReadableMap, com.facebook.react.animated.NativeAnimatedNodesManager):void");
    }

    public final void collectViewUpdates(JavaOnlyMap javaOnlyMap) {
        double d;
        Intrinsics.checkNotNullParameter(javaOnlyMap, "propsMap");
        int size = this.transformConfigs.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            TransformConfig transformConfig = this.transformConfigs.get(i);
            if (transformConfig instanceof AnimatedTransformConfig) {
                AnimatedNode nodeById = this.nativeAnimatedNodesManager.getNodeById(((AnimatedTransformConfig) transformConfig).getNodeTag());
                if (nodeById == null) {
                    throw new IllegalArgumentException("Mapped style node does not exist");
                } else if (nodeById instanceof ValueAnimatedNode) {
                    d = ((ValueAnimatedNode) nodeById).getValue();
                } else {
                    Class<?> cls = nodeById.getClass();
                    throw new IllegalArgumentException("Unsupported type of node used as a transform child node " + cls);
                }
            } else {
                Intrinsics.checkNotNull(transformConfig, "null cannot be cast to non-null type com.facebook.react.animated.TransformAnimatedNode.StaticTransformConfig");
                d = ((StaticTransformConfig) transformConfig).getValue();
            }
            arrayList.add(JavaOnlyMap.Companion.of(transformConfig.getProperty(), Double.valueOf(d)));
        }
        javaOnlyMap.putArray(ViewProps.TRANSFORM, JavaOnlyArray.Companion.from(arrayList));
    }

    public String prettyPrint() {
        int i = this.tag;
        List<TransformConfig> list = this.transformConfigs;
        return "TransformAnimatedNode[" + i + "]: transformConfigs: " + list;
    }

    private class TransformConfig {
        private String property;

        public TransformConfig() {
        }

        public final String getProperty() {
            return this.property;
        }

        public final void setProperty(String str) {
            this.property = str;
        }
    }

    private final class AnimatedTransformConfig extends TransformConfig {
        private int nodeTag;

        public AnimatedTransformConfig() {
            super();
        }

        public final int getNodeTag() {
            return this.nodeTag;
        }

        public final void setNodeTag(int i) {
            this.nodeTag = i;
        }
    }

    private final class StaticTransformConfig extends TransformConfig {
        private double value;

        public StaticTransformConfig() {
            super();
        }

        public final double getValue() {
            return this.value;
        }

        public final void setValue(double d) {
            this.value = d;
        }
    }
}
