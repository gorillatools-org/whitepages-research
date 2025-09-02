package com.facebook.react.animated;

import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ObjectAnimatedNode extends AnimatedNode {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String NODE_TAG_KEY = "nodeTag";
    private static final String VALUE_KEY = "value";
    private final JavaOnlyMap configClone;
    private final NativeAnimatedNodesManager nativeAnimatedNodesManager;

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(15:0|1|2|3|4|5|6|7|8|9|10|11|12|13|15) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0034 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002b */
        static {
            /*
                com.facebook.react.bridge.ReadableType[] r0 = com.facebook.react.bridge.ReadableType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Null     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Boolean     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Number     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.String     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Map     // Catch:{ NoSuchFieldError -> 0x0034 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0034 }
            L_0x0034:
                com.facebook.react.bridge.ReadableType r1 = com.facebook.react.bridge.ReadableType.Array     // Catch:{ NoSuchFieldError -> 0x003d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.animated.ObjectAnimatedNode.WhenMappings.<clinit>():void");
        }
    }

    public ObjectAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager2) {
        Intrinsics.checkNotNullParameter(readableMap, "config");
        Intrinsics.checkNotNullParameter(nativeAnimatedNodesManager2, "nativeAnimatedNodesManager");
        this.nativeAnimatedNodesManager = nativeAnimatedNodesManager2;
        this.configClone = JavaOnlyMap.Companion.deepClone(readableMap);
    }

    public final void collectViewUpdates(String str, JavaOnlyMap javaOnlyMap) {
        Intrinsics.checkNotNullParameter(str, "propKey");
        Intrinsics.checkNotNullParameter(javaOnlyMap, "propsMap");
        ReadableType type = this.configClone.getType("value");
        if (type == ReadableType.Map) {
            javaOnlyMap.putMap(str, collectViewUpdatesHelper(this.configClone.getMap("value")));
        } else if (type == ReadableType.Array) {
            javaOnlyMap.putArray(str, collectViewUpdatesHelper(this.configClone.getArray("value")));
        } else {
            throw new IllegalArgumentException("Invalid value type for ObjectAnimatedNode");
        }
    }

    private final JavaOnlyArray collectViewUpdatesHelper(ReadableArray readableArray) {
        if (readableArray == null) {
            return null;
        }
        JavaOnlyArray javaOnlyArray = new JavaOnlyArray();
        int size = readableArray.size();
        for (int i = 0; i < size; i++) {
            switch (WhenMappings.$EnumSwitchMapping$0[readableArray.getType(i).ordinal()]) {
                case 1:
                    javaOnlyArray.pushNull();
                    break;
                case 2:
                    javaOnlyArray.pushBoolean(readableArray.getBoolean(i));
                    break;
                case 3:
                    javaOnlyArray.pushDouble(readableArray.getDouble(i));
                    break;
                case 4:
                    javaOnlyArray.pushString(readableArray.getString(i));
                    break;
                case 5:
                    ReadableMap map = readableArray.getMap(i);
                    if (map == null || !map.hasKey(NODE_TAG_KEY) || map.getType(NODE_TAG_KEY) != ReadableType.Number) {
                        javaOnlyArray.pushMap(collectViewUpdatesHelper(readableArray.getMap(i)));
                        break;
                    } else {
                        AnimatedNode nodeById = this.nativeAnimatedNodesManager.getNodeById(map.getInt(NODE_TAG_KEY));
                        if (nodeById != null) {
                            if (!(nodeById instanceof ValueAnimatedNode)) {
                                if (!(nodeById instanceof ColorAnimatedNode)) {
                                    break;
                                } else {
                                    javaOnlyArray.pushInt(((ColorAnimatedNode) nodeById).getColor());
                                    break;
                                }
                            } else {
                                ValueAnimatedNode valueAnimatedNode = (ValueAnimatedNode) nodeById;
                                Object animatedObject = valueAnimatedNode.getAnimatedObject();
                                if (!(animatedObject instanceof Integer)) {
                                    if (!(animatedObject instanceof String)) {
                                        javaOnlyArray.pushDouble(valueAnimatedNode.getValue());
                                        break;
                                    } else {
                                        javaOnlyArray.pushString((String) animatedObject);
                                        break;
                                    }
                                } else {
                                    javaOnlyArray.pushInt(((Number) animatedObject).intValue());
                                    break;
                                }
                            }
                        } else {
                            throw new IllegalArgumentException("Mapped value node does not exist");
                        }
                    }
                    break;
                case 6:
                    javaOnlyArray.pushArray(collectViewUpdatesHelper(readableArray.getArray(i)));
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
        return javaOnlyArray;
    }

    private final JavaOnlyMap collectViewUpdatesHelper(ReadableMap readableMap) {
        if (readableMap == null) {
            return null;
        }
        JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            switch (WhenMappings.$EnumSwitchMapping$0[readableMap.getType(nextKey).ordinal()]) {
                case 1:
                    javaOnlyMap.putNull(nextKey);
                    break;
                case 2:
                    javaOnlyMap.putBoolean(nextKey, readableMap.getBoolean(nextKey));
                    break;
                case 3:
                    javaOnlyMap.putDouble(nextKey, readableMap.getDouble(nextKey));
                    break;
                case 4:
                    javaOnlyMap.putString(nextKey, readableMap.getString(nextKey));
                    break;
                case 5:
                    ReadableMap map = readableMap.getMap(nextKey);
                    if (map == null || !map.hasKey(NODE_TAG_KEY) || map.getType(NODE_TAG_KEY) != ReadableType.Number) {
                        javaOnlyMap.putMap(nextKey, collectViewUpdatesHelper(map));
                        break;
                    } else {
                        AnimatedNode nodeById = this.nativeAnimatedNodesManager.getNodeById(map.getInt(NODE_TAG_KEY));
                        if (nodeById != null) {
                            if (!(nodeById instanceof ValueAnimatedNode)) {
                                if (!(nodeById instanceof ColorAnimatedNode)) {
                                    break;
                                } else {
                                    javaOnlyMap.putInt(nextKey, ((ColorAnimatedNode) nodeById).getColor());
                                    break;
                                }
                            } else {
                                ValueAnimatedNode valueAnimatedNode = (ValueAnimatedNode) nodeById;
                                Object animatedObject = valueAnimatedNode.getAnimatedObject();
                                if (!(animatedObject instanceof Integer)) {
                                    if (!(animatedObject instanceof String)) {
                                        javaOnlyMap.putDouble(nextKey, valueAnimatedNode.getValue());
                                        break;
                                    } else {
                                        javaOnlyMap.putString(nextKey, (String) animatedObject);
                                        break;
                                    }
                                } else {
                                    javaOnlyMap.putInt(nextKey, ((Number) animatedObject).intValue());
                                    break;
                                }
                            }
                        } else {
                            throw new IllegalArgumentException("Mapped value node does not exist");
                        }
                    }
                case 6:
                    javaOnlyMap.putArray(nextKey, collectViewUpdatesHelper(readableMap.getArray(nextKey)));
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
        return javaOnlyMap;
    }

    public String prettyPrint() {
        int i = this.tag;
        JavaOnlyMap javaOnlyMap = this.configClone;
        return "ObjectAnimatedNode[" + i + "]: mConfig: " + javaOnlyMap;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
