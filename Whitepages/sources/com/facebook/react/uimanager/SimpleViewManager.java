package com.facebook.react.uimanager;

import android.view.View;
import kotlin.jvm.internal.Intrinsics;

public abstract class SimpleViewManager<T extends View> extends BaseViewManager<T, LayoutShadowNode> {
    public void updateExtraData(T t, Object obj) {
        Intrinsics.checkNotNullParameter(t, "root");
    }

    public LayoutShadowNode createShadowNodeInstance() {
        return new LayoutShadowNode();
    }

    public Class<LayoutShadowNode> getShadowNodeClass() {
        return LayoutShadowNode.class;
    }
}
