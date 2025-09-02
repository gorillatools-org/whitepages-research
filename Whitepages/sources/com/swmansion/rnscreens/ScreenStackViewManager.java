package com.swmansion.rnscreens;

import android.view.View;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.viewmanagers.RNSScreenStackManagerDelegate;
import com.facebook.react.viewmanagers.RNSScreenStackManagerInterface;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "RNSScreenStack")
public final class ScreenStackViewManager extends ViewGroupManager<ScreenStack> implements RNSScreenStackManagerInterface<ScreenStack> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String REACT_CLASS = "RNSScreenStack";
    private final ViewManagerDelegate<ScreenStack> delegate = new RNSScreenStackManagerDelegate(this);

    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public ScreenStack createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        return new ScreenStack(themedReactContext);
    }

    public void addView(ScreenStack screenStack, View view, int i) {
        Intrinsics.checkNotNullParameter(screenStack, "parent");
        Intrinsics.checkNotNullParameter(view, "child");
        if (view instanceof Screen) {
            Screen screen = (Screen) view;
            NativeProxy.Companion.addScreenToMap(screen.getId(), screen);
            screenStack.addScreen(screen, i);
            return;
        }
        throw new IllegalArgumentException("Attempt attach child that is not of type Screen");
    }

    public void removeViewAt(ScreenStack screenStack, int i) {
        Intrinsics.checkNotNullParameter(screenStack, "parent");
        Screen screenAt = screenStack.getScreenAt(i);
        prepareOutTransition(screenAt);
        screenStack.removeScreenAt(i);
        NativeProxy.Companion.removeScreenFromMap(screenAt.getId());
    }

    private final void prepareOutTransition(Screen screen) {
        if (screen != null) {
            screen.startRemovalTransition();
        }
    }

    public void invalidate() {
        super.invalidate();
        NativeProxy.Companion.clearMapOnInvalidate();
    }

    public int getChildCount(ScreenStack screenStack) {
        Intrinsics.checkNotNullParameter(screenStack, "parent");
        return screenStack.getScreenCount();
    }

    public View getChildAt(ScreenStack screenStack, int i) {
        Intrinsics.checkNotNullParameter(screenStack, "parent");
        return screenStack.getScreenAt(i);
    }

    public LayoutShadowNode createShadowNodeInstance(ReactApplicationContext reactApplicationContext) {
        Intrinsics.checkNotNullParameter(reactApplicationContext, "context");
        return new ScreensShadowNode(reactApplicationContext);
    }

    /* access modifiers changed from: protected */
    public ViewManagerDelegate<ScreenStack> getDelegate() {
        return this.delegate;
    }

    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapsKt.mutableMapOf(TuplesKt.to("topFinishTransitioning", MapsKt.mutableMapOf(TuplesKt.to("registrationName", "onFinishTransitioning"))));
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
