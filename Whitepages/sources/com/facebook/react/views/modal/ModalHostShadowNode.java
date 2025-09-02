package com.facebook.react.views.modal;

import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ReactShadowNodeImpl;
import kotlin.jvm.internal.Intrinsics;

public final class ModalHostShadowNode extends LayoutShadowNode {
    public void addChildAt(ReactShadowNodeImpl reactShadowNodeImpl, int i) {
        Intrinsics.checkNotNullParameter(reactShadowNodeImpl, "child");
        super.addChildAt(reactShadowNodeImpl, i);
    }
}
