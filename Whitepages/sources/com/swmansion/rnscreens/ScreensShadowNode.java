package com.swmansion.rnscreens;

import android.view.View;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.NativeViewHierarchyOptimizer;
import com.facebook.react.uimanager.UIManagerModule;
import kotlin.jvm.internal.Intrinsics;

public final class ScreensShadowNode extends LayoutShadowNode {
    private ReactContext context;

    public ScreensShadowNode(ReactContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "context");
        this.context = reactContext;
    }

    public void onBeforeLayout(NativeViewHierarchyOptimizer nativeViewHierarchyOptimizer) {
        Intrinsics.checkNotNullParameter(nativeViewHierarchyOptimizer, "nativeViewHierarchyOptimizer");
        super.onBeforeLayout(nativeViewHierarchyOptimizer);
        UIManagerModule uIManagerModule = (UIManagerModule) this.context.getNativeModule(UIManagerModule.class);
        if (uIManagerModule != null) {
            uIManagerModule.addUIBlock(new ScreensShadowNode$$ExternalSyntheticLambda0(this));
        }
    }

    /* access modifiers changed from: private */
    public static final void onBeforeLayout$lambda$0(ScreensShadowNode screensShadowNode, NativeViewHierarchyManager nativeViewHierarchyManager) {
        if (nativeViewHierarchyManager != null) {
            View resolveView = nativeViewHierarchyManager.resolveView(screensShadowNode.getReactTag());
            if (resolveView instanceof ScreenContainer) {
                ((ScreenContainer) resolveView).performUpdates();
            }
        }
    }
}
