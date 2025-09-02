package com.facebook.react.views.text;

import android.view.View;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "RCTVirtualText")
public final class ReactVirtualTextViewManager extends BaseViewManager<View, ReactVirtualTextShadowNode> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String REACT_CLASS = "RCTVirtualText";

    public void updateExtraData(View view, Object obj) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(obj, "extraData");
    }

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public View createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        throw new IllegalStateException("Attempt to create a native view for RCTVirtualText");
    }

    public Class<ReactVirtualTextShadowNode> getShadowNodeClass() {
        return ReactVirtualTextShadowNode.class;
    }

    public ReactVirtualTextShadowNode createShadowNodeInstance() {
        return new ReactVirtualTextShadowNode();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
