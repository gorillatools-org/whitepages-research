package com.swmansion.rnscreens;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.swmansion.rnscreens.utils.PaddingBundle;
import kotlin.jvm.internal.Intrinsics;

public final class ScreenStackHeaderConfigShadowNode extends LayoutShadowNode {
    private ReactContext context;
    private float height;
    private float paddingEnd;
    private float paddingStart;

    public ScreenStackHeaderConfigShadowNode(ReactContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "context");
        this.context = reactContext;
    }

    public void setLocalData(Object obj) {
        if (obj instanceof PaddingBundle) {
            PaddingBundle paddingBundle = (PaddingBundle) obj;
            this.paddingStart = paddingBundle.getPaddingStart();
            this.paddingEnd = paddingBundle.getPaddingEnd();
            this.height = paddingBundle.getHeight();
            setPadding(4, this.paddingStart);
            setPadding(5, this.paddingEnd);
            setPosition(1, -this.height);
            return;
        }
        super.setLocalData(obj);
    }
}
