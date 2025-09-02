package com.facebook.react.uimanager;

import com.facebook.proguard.annotations.DoNotStripAny;
import com.facebook.react.bridge.RuntimeExecutor;
import com.facebook.soloader.SoLoader;

@DoNotStripAny
public final class ComponentNameResolverBinding {
    public static final ComponentNameResolverBinding INSTANCE = new ComponentNameResolverBinding();

    public static final native void install(RuntimeExecutor runtimeExecutor, Object obj);

    private ComponentNameResolverBinding() {
    }

    static {
        SoLoader.loadLibrary("uimanagerjni");
    }
}
