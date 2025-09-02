package com.swmansion.rnscreens;

import android.view.View;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.viewmanagers.RNSScreenContainerManagerDelegate;
import com.facebook.react.viewmanagers.RNSScreenContainerManagerInterface;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "RNSScreenContainer")
public final class ScreenContainerViewManager extends ViewGroupManager<ScreenContainer> implements RNSScreenContainerManagerInterface<ScreenContainer> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String REACT_CLASS = "RNSScreenContainer";
    private final ViewManagerDelegate<ScreenContainer> delegate = new RNSScreenContainerManagerDelegate(this);

    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    /* access modifiers changed from: protected */
    public ViewManagerDelegate<ScreenContainer> getDelegate() {
        return this.delegate;
    }

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public ScreenContainer createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        return new ScreenContainer(themedReactContext);
    }

    public void addView(ScreenContainer screenContainer, View view, int i) {
        Intrinsics.checkNotNullParameter(screenContainer, "parent");
        Intrinsics.checkNotNullParameter(view, "child");
        if (view instanceof Screen) {
            screenContainer.addScreen((Screen) view, i);
            return;
        }
        throw new IllegalArgumentException("Attempt attach child that is not of type RNScreens");
    }

    public void removeViewAt(ScreenContainer screenContainer, int i) {
        Intrinsics.checkNotNullParameter(screenContainer, "parent");
        screenContainer.removeScreenAt(i);
    }

    public void removeAllViews(ScreenContainer screenContainer) {
        Intrinsics.checkNotNullParameter(screenContainer, "parent");
        screenContainer.removeAllScreens();
    }

    public int getChildCount(ScreenContainer screenContainer) {
        Intrinsics.checkNotNullParameter(screenContainer, "parent");
        return screenContainer.getScreenCount();
    }

    public View getChildAt(ScreenContainer screenContainer, int i) {
        Intrinsics.checkNotNullParameter(screenContainer, "parent");
        return screenContainer.getScreenAt(i);
    }

    public LayoutShadowNode createShadowNodeInstance(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "context");
        return new ScreensShadowNode(reactApplicationContext);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
