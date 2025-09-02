package com.facebook.react.animated;

import android.view.View;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.uimanager.common.ViewUtil;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;

public final class PropsAnimatedNode extends AnimatedNode {
    private int connectedViewTag = -1;
    private UIManager connectedViewUIManager;
    private final NativeAnimatedNodesManager nativeAnimatedNodesManager;
    private final JavaOnlyMap propMap = new JavaOnlyMap();
    private final Map<String, Integer> propNodeMapping;

    public PropsAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager2) {
        Intrinsics.checkNotNullParameter(readableMap, "config");
        Intrinsics.checkNotNullParameter(nativeAnimatedNodesManager2, "nativeAnimatedNodesManager");
        this.nativeAnimatedNodesManager = nativeAnimatedNodesManager2;
        ReadableMap map = readableMap.getMap("props");
        ReadableMapKeySetIterator keySetIterator = map != null ? map.keySetIterator() : null;
        this.propNodeMapping = new LinkedHashMap();
        while (keySetIterator != null && keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            this.propNodeMapping.put(nextKey, Integer.valueOf(map.getInt(nextKey)));
        }
    }

    public final void connectToView(int i, UIManager uIManager) {
        if (this.connectedViewTag == -1) {
            this.connectedViewTag = i;
            this.connectedViewUIManager = uIManager;
            return;
        }
        int i2 = this.tag;
        int i3 = this.connectedViewTag;
        throw new JSApplicationIllegalArgumentException("Animated node " + i2 + " is already attached to a view: " + i3);
    }

    public final void disconnectFromView(int i) {
        int i2 = this.connectedViewTag;
        if (i2 == i || i2 == -1) {
            this.connectedViewTag = -1;
            return;
        }
        int i3 = this.connectedViewTag;
        throw new JSApplicationIllegalArgumentException("Attempting to disconnect view that has not been connected with the given animated node: " + i + " but is connected to view " + i3);
    }

    public final void restoreDefaultValues() {
        int i = this.connectedViewTag;
        if (i != -1 && ViewUtil.getUIManagerType(i) != 2) {
            ReadableMapKeySetIterator keySetIterator = this.propMap.keySetIterator();
            while (keySetIterator.hasNextKey()) {
                this.propMap.putNull(keySetIterator.nextKey());
            }
            UIManager uIManager = this.connectedViewUIManager;
            if (uIManager != null) {
                uIManager.synchronouslyUpdateViewOnUIThread(this.connectedViewTag, this.propMap);
            }
        }
    }

    public final void updateView() {
        if (this.connectedViewTag != -1) {
            for (Map.Entry next : this.propNodeMapping.entrySet()) {
                String str = (String) next.getKey();
                AnimatedNode nodeById = this.nativeAnimatedNodesManager.getNodeById(((Number) next.getValue()).intValue());
                if (nodeById == null) {
                    throw new IllegalArgumentException("Mapped property node does not exist");
                } else if (nodeById instanceof StyleAnimatedNode) {
                    ((StyleAnimatedNode) nodeById).collectViewUpdates(this.propMap);
                } else if (nodeById instanceof ValueAnimatedNode) {
                    ValueAnimatedNode valueAnimatedNode = (ValueAnimatedNode) nodeById;
                    Object animatedObject = valueAnimatedNode.getAnimatedObject();
                    if (animatedObject instanceof Integer) {
                        this.propMap.putInt(str, ((Number) animatedObject).intValue());
                    } else if (animatedObject instanceof String) {
                        this.propMap.putString(str, (String) animatedObject);
                    } else {
                        this.propMap.putDouble(str, valueAnimatedNode.getValue());
                    }
                } else if (nodeById instanceof ColorAnimatedNode) {
                    this.propMap.putInt(str, ((ColorAnimatedNode) nodeById).getColor());
                } else if (nodeById instanceof ObjectAnimatedNode) {
                    ((ObjectAnimatedNode) nodeById).collectViewUpdates(str, this.propMap);
                } else {
                    Class<?> cls = nodeById.getClass();
                    throw new IllegalArgumentException("Unsupported type of node used in property node " + cls);
                }
            }
            UIManager uIManager = this.connectedViewUIManager;
            if (uIManager != null) {
                uIManager.synchronouslyUpdateViewOnUIThread(this.connectedViewTag, this.propMap);
            }
        }
    }

    public final View getConnectedView() {
        Object obj;
        View view = null;
        try {
            Result.Companion companion = Result.Companion;
            UIManager uIManager = this.connectedViewUIManager;
            obj = Result.m866constructorimpl(uIManager != null ? uIManager.resolveView(this.connectedViewTag) : null);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m866constructorimpl(ResultKt.createFailure(th));
        }
        if (!Result.m868isFailureimpl(obj)) {
            view = obj;
        }
        return view;
    }

    public String prettyPrint() {
        int i = this.tag;
        int i2 = this.connectedViewTag;
        Map<String, Integer> map = this.propNodeMapping;
        JavaOnlyMap javaOnlyMap = this.propMap;
        return "PropsAnimatedNode[" + i + "] connectedViewTag: " + i2 + " propNodeMapping: " + map + " propMap: " + javaOnlyMap;
    }
}
