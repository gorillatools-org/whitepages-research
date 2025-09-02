package com.facebook.react.views.text;

import android.view.View;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManager;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "RCTRawText")
public final class ReactRawTextManager extends ViewManager<View, ReactRawTextShadowNode> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String REACT_CLASS = "RCTRawText";

    public void updateExtraData(View view, Object obj) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(obj, "extraData");
    }

    public String getName() {
        return REACT_CLASS;
    }

    public ReactTextView createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        throw new IllegalStateException("Attempt to create a native view for RCTRawText");
    }

    /* access modifiers changed from: protected */
    public View prepareToRecycleView(ThemedReactContext themedReactContext, View view) {
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        Intrinsics.checkNotNullParameter(view, "view");
        throw new IllegalStateException("Attempt to recycle a native view for RCTRawText");
    }

    public Class<ReactRawTextShadowNode> getShadowNodeClass() {
        return ReactRawTextShadowNode.class;
    }

    public ReactRawTextShadowNode createShadowNodeInstance() {
        return new ReactRawTextShadowNode();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
