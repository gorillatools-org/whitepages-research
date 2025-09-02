package com.swmansion.rnscreens;

import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.viewmanagers.RNSScreenFooterManagerDelegate;
import com.facebook.react.viewmanagers.RNSScreenFooterManagerInterface;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "RNSScreenFooter")
public final class ScreenFooterManager extends ViewGroupManager<ScreenFooter> implements RNSScreenFooterManagerInterface<ScreenFooter> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String REACT_CLASS = "RNSScreenFooter";
    private final ViewManagerDelegate<ScreenFooter> delegate = new RNSScreenFooterManagerDelegate(this);

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public ScreenFooter createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        return new ScreenFooter(themedReactContext);
    }

    /* access modifiers changed from: protected */
    public ViewManagerDelegate<ScreenFooter> getDelegate() {
        return this.delegate;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
