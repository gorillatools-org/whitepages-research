package com.facebook.react.views.unimplementedview;

import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.UnimplementedNativeViewManagerDelegate;
import com.facebook.react.viewmanagers.UnimplementedNativeViewManagerInterface;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "UnimplementedNativeView")
public final class ReactUnimplementedViewManager extends ViewGroupManager<ReactUnimplementedView> implements UnimplementedNativeViewManagerInterface<ReactUnimplementedView> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String REACT_CLASS = "UnimplementedNativeView";
    private final ViewManagerDelegate<ReactUnimplementedView> delegate = new UnimplementedNativeViewManagerDelegate(this);

    public ViewManagerDelegate<ReactUnimplementedView> getDelegate() {
        return this.delegate;
    }

    /* access modifiers changed from: protected */
    public ReactUnimplementedView createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        return new ReactUnimplementedView(themedReactContext);
    }

    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(name = "name")
    public void setName(ReactUnimplementedView reactUnimplementedView, String str) {
        Intrinsics.checkNotNullParameter(reactUnimplementedView, "view");
        if (str == null) {
            str = "<null component name>";
        }
        reactUnimplementedView.setName$ReactAndroid_release(str);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
