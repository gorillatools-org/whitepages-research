package com.facebook.react.defaults;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.fabric.ComponentFactory;

@DoNotStrip
public final class DefaultComponentsRegistry {
    public static final DefaultComponentsRegistry INSTANCE = new DefaultComponentsRegistry();

    @DoNotStrip
    public static final native void register(ComponentFactory componentFactory);

    private DefaultComponentsRegistry() {
    }

    static {
        DefaultSoLoader.Companion.maybeLoadSoLibrary();
    }
}
