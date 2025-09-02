package com.facebook.react.views.safeareaview;

import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.viewmanagers.SafeAreaViewManagerDelegate;
import com.facebook.react.viewmanagers.SafeAreaViewManagerInterface;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "RCTSafeAreaView")
public final class ReactSafeAreaViewManager extends ViewGroupManager<ReactSafeAreaView> implements SafeAreaViewManagerInterface<ReactSafeAreaView> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String REACT_CLASS = "RCTSafeAreaView";
    private final ViewManagerDelegate<ReactSafeAreaView> delegate = new SafeAreaViewManagerDelegate(this);

    /* access modifiers changed from: protected */
    public ViewManagerDelegate<ReactSafeAreaView> getDelegate() {
        return this.delegate;
    }

    /* access modifiers changed from: protected */
    public ReactSafeAreaView createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        return new ReactSafeAreaView(themedReactContext);
    }

    public String getName() {
        return REACT_CLASS;
    }

    public LayoutShadowNode createShadowNodeInstance() {
        return new ReactSafeAreaViewShadowNode();
    }

    public Class<? extends LayoutShadowNode> getShadowNodeClass() {
        return ReactSafeAreaViewShadowNode.class;
    }

    public Object updateState(ReactSafeAreaView reactSafeAreaView, ReactStylesDiffMap reactStylesDiffMap, StateWrapper stateWrapper) {
        Intrinsics.checkNotNullParameter(reactSafeAreaView, "view");
        Intrinsics.checkNotNullParameter(reactStylesDiffMap, "props");
        Intrinsics.checkNotNullParameter(stateWrapper, "stateWrapper");
        reactSafeAreaView.setStateWrapper$ReactAndroid_release(stateWrapper);
        return null;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
