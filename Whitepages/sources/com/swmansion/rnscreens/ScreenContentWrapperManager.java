package com.swmansion.rnscreens;

import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.viewmanagers.RNSScreenContentWrapperManagerDelegate;
import com.facebook.react.viewmanagers.RNSScreenContentWrapperManagerInterface;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "RNSScreenContentWrapper")
public final class ScreenContentWrapperManager extends ViewGroupManager<ScreenContentWrapper> implements RNSScreenContentWrapperManagerInterface<ScreenContentWrapper> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String REACT_CLASS = "RNSScreenContentWrapper";
    private final ViewManagerDelegate<ScreenContentWrapper> delegate = new RNSScreenContentWrapperManagerDelegate(this);

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public ScreenContentWrapper createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        return new ScreenContentWrapper(themedReactContext);
    }

    /* access modifiers changed from: protected */
    public ViewManagerDelegate<ScreenContentWrapper> getDelegate() {
        return this.delegate;
    }
}
