package com.facebook.react.defaults;

import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ViewManagerResolver;
import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;

public final class DefaultReactNativeHost$getUIManagerProvider$1$viewManagerRegistry$1 implements ViewManagerResolver {
    final /* synthetic */ DefaultReactNativeHost this$0;

    DefaultReactNativeHost$getUIManagerProvider$1$viewManagerRegistry$1(DefaultReactNativeHost defaultReactNativeHost) {
        this.this$0 = defaultReactNativeHost;
    }

    public ViewManager getViewManager(String str) {
        Intrinsics.checkNotNullParameter(str, "viewManagerName");
        return this.this$0.getReactInstanceManager().createViewManager(str);
    }

    public Collection<String> getViewManagerNames() {
        return this.this$0.getReactInstanceManager().getViewManagerNames();
    }
}
