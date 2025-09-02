package com.th3rdwave.safeareacontext;

import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.viewmanagers.RNCSafeAreaProviderManagerDelegate;
import com.facebook.react.viewmanagers.RNCSafeAreaProviderManagerInterface;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@ReactModule(name = "RNCSafeAreaProvider")
public final class SafeAreaProviderManager extends ViewGroupManager<SafeAreaProvider> implements RNCSafeAreaProviderManagerInterface<SafeAreaProvider> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String REACT_CLASS = "RNCSafeAreaProvider";
    private final RNCSafeAreaProviderManagerDelegate<SafeAreaProvider, SafeAreaProviderManager> mDelegate = new RNCSafeAreaProviderManagerDelegate<>(this);

    /* access modifiers changed from: protected */
    public RNCSafeAreaProviderManagerDelegate<SafeAreaProvider, SafeAreaProviderManager> getDelegate() {
        return this.mDelegate;
    }

    public String getName() {
        return REACT_CLASS;
    }

    public SafeAreaProvider createViewInstance(ThemedReactContext themedReactContext) {
        Intrinsics.checkNotNullParameter(themedReactContext, "context");
        return new SafeAreaProvider(themedReactContext);
    }

    public Map<String, Map<String, String>> getExportedCustomDirectEventTypeConstants() {
        return MapsKt.mutableMapOf(TuplesKt.to("topInsetsChange", MapsKt.mutableMapOf(TuplesKt.to("registrationName", "onInsetsChange"))));
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext themedReactContext, SafeAreaProvider safeAreaProvider) {
        Intrinsics.checkNotNullParameter(themedReactContext, "reactContext");
        Intrinsics.checkNotNullParameter(safeAreaProvider, "view");
        super.addEventEmitters(themedReactContext, safeAreaProvider);
        safeAreaProvider.setOnInsetsChangeHandler(SafeAreaProviderManager$addEventEmitters$1.INSTANCE);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
